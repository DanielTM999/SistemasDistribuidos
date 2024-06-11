package com.isp_server.isp.core.http;

import java.util.Map;

public interface HttpServices {
    HttpResponse sendGet(String url, Map<String, String> headers) throws Exception;
    HttpResponse sendGet(String url) throws Exception;
    HttpResponse sendPost(String url, String jsonBody, Map<String, String> headers) throws Exception;
    HttpResponse sendPost(String url, String jsonBody) throws Exception;
    HttpResponse sendPut(String url, String jsonBody, Map<String, String> headers) throws Exception;
    HttpResponse sendPut(String url, String jsonBody) throws Exception;
    HttpResponse sendDelete(String url, Map<String, String> headers) throws Exception;
    HttpResponse sendDelete(String url) throws Exception;
}
