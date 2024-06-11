package com.master.master_node.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.master.master_node.core.MasterNodeServices;
import com.master.master_node.core.http.HttpResponse;
import com.master.master_node.core.http.HttpServices;
import com.master.master_node.models.exceptions.BadGatewayException;
import com.master.master_node.models.exceptions.BadRequestException;

@Service
public class MasterNodeServicesData implements MasterNodeServices{

    @Autowired
    private HttpServices httpServices;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ResponseEntity<Void> salvarArquivo(String fileName, String fileBase64) throws BadRequestException, BadGatewayException {
        String url = urlRandomToSend();
        HttpResponse response = sendRequestToProfilerWrite(url, fileBase64, fileName);
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
    
    private String urlRandomToSend() throws BadGatewayException{
        Random rd = new Random();
        String finalRandom[] = new String[2]; 
        List<String> dfs_b = getDnsUrl("dfs_b");
        finalRandom[0] = dfs_b.get(rd.nextInt(dfs_b.size()));
        List<String> dfs_c = getDnsUrl("dfs_c");
        finalRandom[1] = dfs_c.get(rd.nextInt(dfs_c.size()));
        return finalRandom[rd.nextInt(finalRandom.length)];
    }

    private HttpResponse sendRequestToProfilerWrite(String urlProfilerServer, String fileBase64, String fileName) throws BadRequestException, BadGatewayException{
        HttpResponse response = null;
        Map<String, String> headers = new HashMap<>(){{
            put("Content-Type", "application/json");
        }};
        String jsonBulder = "{\"file64\":\"%s\", \"fileName\":\"%s\"}";
        String jsonBody = String.format(jsonBulder, fileBase64, fileName);
        
        String url = urlProfilerServer+"/salvarArquivo";
        try {
            response = httpServices.sendPost(url, jsonBody, headers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

}
