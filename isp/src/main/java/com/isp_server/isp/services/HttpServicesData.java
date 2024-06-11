package com.isp_server.isp.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.isp_server.isp.core.http.HttpResponse;
import com.isp_server.isp.core.http.HttpServices;
import com.isp_server.isp.core.http.HttpServicesDelete;
import com.isp_server.isp.core.http.HttpServicesGet;
import com.isp_server.isp.core.http.HttpServicesPost;
import com.isp_server.isp.core.http.HttpServicesPut;


@Service
public class HttpServicesData implements HttpServicesGet, HttpServicesPost, HttpServicesPut, HttpServicesDelete, HttpServices{

    @Override
    public HttpResponse sendGet(String url, Map<String, String> headers) throws Exception{
        return makeGetRequest(url, headers);
    }

    @Override
    public HttpResponse sendGet(String url) throws Exception{
        return makeGetRequest(url, null);
    }

    @Override
    public HttpResponse sendPost(String url, String jsonBody, Map<String, String> headers) throws Exception{
        return makePostRequest(url, jsonBody, headers);
    }

    @Override
    public HttpResponse sendPost(String url, String jsonBody) throws Exception{
        return makePostRequest(url, jsonBody, null);
    }
    
    @Override
    public HttpResponse sendPut(String url, String jsonBody, Map<String, String> headers) throws Exception{
        return makePutRequest(url, jsonBody, headers);
    }

    @Override
    public HttpResponse sendPut(String url, String jsonBody) throws Exception{
        return makePutRequest(url, jsonBody, null);
    }

    @Override
    public HttpResponse sendDelete(String url, Map<String, String> headers) throws Exception {
        return makeDeleteRequest(url, headers);
    }

    @Override
    public HttpResponse sendDelete(String url) throws Exception {
       return makeDeleteRequest(url, null);
    }


    private HttpResponse makeGetRequest(String url, Map<String, String> headers) throws Exception{
        HttpResponse respHttpResponse = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        requestBuilder.uri(URI.create(url));
        requestBuilder.GET();

        if(headers != null){
            for(Map.Entry<String,String> entry : headers.entrySet()){
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        HttpRequest request = requestBuilder.build();

        java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        respHttpResponse = new HttpResponseData(response.body(), response.statusCode());

        return respHttpResponse;
    }

    private HttpResponse makePostRequest(String url, String jsonBody, Map<String, String> headers) throws Exception{
        HttpResponse respHttpResponse = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        requestBuilder.uri(URI.create(url));
        requestBuilder.POST(BodyPublishers.ofString(jsonBody));
        if(headers != null){
            for(Map.Entry<String,String> entry : headers.entrySet()){
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        HttpRequest request = requestBuilder.build();
        java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        respHttpResponse = new HttpResponseData(response.body(), response.statusCode());

        return respHttpResponse;
    }

    private HttpResponse makePutRequest(String url, String jsonBody, Map<String, String> headers) throws Exception{
        HttpResponse respHttpResponse = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        requestBuilder.uri(URI.create(url));
        requestBuilder.PUT(BodyPublishers.ofString(jsonBody));
        if(headers != null){
            for(Map.Entry<String,String> entry : headers.entrySet()){
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        HttpRequest request = requestBuilder.build();
        java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        respHttpResponse = new HttpResponseData(response.body(), response.statusCode());

        return respHttpResponse;  
    }

    private HttpResponse makeDeleteRequest(String url, Map<String, String> headers) throws Exception{
        HttpResponse respHttpResponse = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        requestBuilder.uri(URI.create(url));
        requestBuilder.DELETE();
        if(headers != null){
            for(Map.Entry<String,String> entry : headers.entrySet()){
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        HttpRequest request = requestBuilder.build();
        java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        respHttpResponse = new HttpResponseData(response.body(), response.statusCode());

        return respHttpResponse;  
    }

    private static class HttpResponseData extends HttpResponse{
        private String body;
        private int statusCode;

        public HttpResponseData(String body, int statusCode){
            this.body = body;
            this.statusCode = statusCode;
        }

        @Override
        public String getBody() {
            return body;
        }

        @Override
        public int getStatusCode() {
            return statusCode;
        }
    }

}
