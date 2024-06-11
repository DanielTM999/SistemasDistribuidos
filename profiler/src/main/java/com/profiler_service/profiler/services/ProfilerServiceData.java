package com.profiler_service.profiler.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.profiler_service.profiler.core.ProfilerService;
import com.profiler_service.profiler.core.http.HttpResponse;
import com.profiler_service.profiler.core.http.HttpServices;
import com.profiler_service.profiler.models.exceptions.BadGatewayException;
import com.profiler_service.profiler.models.exceptions.BadRequestException;

@Service
public class ProfilerServiceData implements ProfilerService{

    @Autowired
    private HttpServices httpServices;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ResponseEntity<Void> salvarArquivo(String fileName, String fileBase64) throws BadRequestException, BadGatewayException {
        List<String> urlMasterNode = getDnsUrl("master_node");
        HttpResponse response = sendRequestToProfilerWrite(urlMasterNode, fileBase64, fileName);
        if(response.getStatusCode() != 200){
            throw new BadRequestException(response.getBody(), response.getStatusCode());
        }
        
        return ResponseEntity.ok().build();
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

    private HttpResponse sendRequestToProfilerWrite(List<String> urlProfilerServer, String fileBase64, String fileName) throws BadRequestException, BadGatewayException{
        HttpResponse response = null;
        Map<String, String> headers = new HashMap<>(){{
            put("Content-Type", "application/json");
        }};
        String jsonBulder = "{\"file64\":\"%s\", \"fileName\":\"%s\"}";
        String jsonBody = String.format(jsonBulder, fileBase64, fileName);
        Iterator<String> urlIterator = urlProfilerServer.iterator();
        
        while (urlIterator.hasNext()) {
            String url = urlIterator.next()+"/salvarArquivo";
            try {
                response = httpServices.sendPost(url, jsonBody, headers);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return response;
    }
}
