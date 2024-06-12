package com.isp_server.isp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isp_server.isp.core.IspServices;
import com.isp_server.isp.core.http.HttpResponse;
import com.isp_server.isp.core.http.HttpServices;
import com.isp_server.isp.models.exceptions.BadGatewayException;
import com.isp_server.isp.models.exceptions.BadRequestException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({"unused"})
@Service
public class IspServiceData implements IspServices {
    private static int version = 1;

    @Autowired
    private HttpServices httpServices;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ResponseEntity<Void> salvarArquivo(String fileName, MultipartFile fileBytes) throws BadRequestException, BadGatewayException {
        validArquivo(fileBytes);
        byte[] fileByteStream = new byte[0];
        try {
            fileByteStream = fileBytes.getBytes();
        } catch (IOException e) {
            throw new BadRequestException("Erro ao obter dados do arquivo: "+ fileBytes.getName(), 400);
        }

        List<String> urlProfilerServer = getDnsUrl("profiler");
        fileName = formatFileName(fileName);
        HttpResponse response = sendRequestToProfilerWrite(urlProfilerServer, fileByteStream, fileName);
        
        if(response.getStatusCode() != 200){
            throw new BadRequestException(response.getBody(), response.getStatusCode());
        }
        
        return ResponseEntity.ok().build();
    }

    @Override
    public void obterArquivo(String fileName, HttpServletResponse response) throws BadRequestException, BadGatewayException {
        List<String> urlProfilerServer = getDnsUrl("profiler");
        fileName = formatFileName(fileName);
        HttpResponse responseReq = sendRequestToProfilerReade(urlProfilerServer, fileName);

        if(responseReq.getStatusCode() != 200){
            throw new BadRequestException(responseReq.getBody(), responseReq.getStatusCode());
        }
        System.out.println(responseReq.getBody());
    }

    private List<String> getDnsUrl(String appName, int size) throws BadGatewayException{
        List<String> urls = new ArrayList<>();
        List<ServiceInstance> instances = discoveryClient.getInstances(appName);
        if (instances != null && !instances.isEmpty()) {
            Iterator<ServiceInstance> serviceInstanceIterator = instances.iterator();
            while (serviceInstanceIterator.hasNext()) {
                String url = serviceInstanceIterator.next().getUri().toString();
                urls.add(url);
            }
            return urls;
        }
        throw new BadGatewayException("DNS not found", 502);
    }

    private List<String> getDnsUrl(String appName) throws BadGatewayException{
        List<String> urls = new ArrayList<>();
        List<ServiceInstance> instances = discoveryClient.getInstances(appName);
        if (instances != null && !instances.isEmpty()) {
            Iterator<ServiceInstance> serviceInstanceIterator = instances.iterator();
            while (serviceInstanceIterator.hasNext()) {
                String url = serviceInstanceIterator.next().getUri().toString();
                urls.add(url);
            }
            return urls;
        }
        throw new BadGatewayException("DNS not found", 502);
    }

    private String toCharFile(byte[] fileByteStream){
        return Base64.getEncoder().encodeToString(fileByteStream);
    }
    
    private void validArquivo(MultipartFile fileBytes) throws BadRequestException{
        if(fileBytes == null) throw new BadRequestException("Arquivo vazio informe um arquivo", 400);
    
        int indexExt = fileBytes.getOriginalFilename().indexOf(".");
        if(indexExt == -1) throw new BadRequestException("Arquivo tem que possuir extenção", 400);
        String ext = fileBytes.getOriginalFilename().substring(indexExt, fileBytes.getOriginalFilename().length());
    
        if(!ext.equalsIgnoreCase(".txt")){
            throw new BadRequestException("Devido a motivos de segurança e injeção de scripts apenas é permitido arquivos .TXT", 400);
        }
    }

    private HttpResponse sendRequestToProfilerWrite(List<String> urlProfilerServer, byte[] fileByteStreamm, String fileName) throws BadRequestException, BadGatewayException{
        HttpResponse response = null;
        Map<String, String> headers = new HashMap<>(){{
            put("Content-Type", "application/json");
        }};
        String jsonBulder = "{\"file64\":\"%s\"}";
        String jsonBody = String.format(jsonBulder, toCharFile(fileByteStreamm));
        Iterator<String> urlIterator = urlProfilerServer.iterator();
        
        while (urlIterator.hasNext()) {
            String url = urlIterator.next()+"/salvarArquivo/"+fileName;
            try {
                response = httpServices.sendPost(url, jsonBody, headers);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return response;
    }

    private HttpResponse sendRequestToProfilerReade(List<String> urlProfilerServer, String fileName)throws BadRequestException, BadGatewayException{
        HttpResponse response = null;
        Iterator<String> urlIterator = urlProfilerServer.iterator();
        
        while (urlIterator.hasNext()) {
            String url = urlIterator.next()+"/obterArquivo/"+fileName;
            try {
                response = httpServices.sendGet(url);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return response;
    }

    private String formatFileName(String fileName){
        int indexExt = fileName.indexOf(".");
        String name = fileName;
        String ext = ".txt";
        if(indexExt != -1 ){
            ext = fileName.substring(indexExt, fileName.length());
            name = fileName.substring(0, indexExt);
        }
        fileName = name + "_V"+version+ext;
        version++;
        return fileName;
    }
}
