package com.profiler_service.profiler.core.http;

import java.util.Map;

public interface HttpServicesPost {
    HttpResponse sendPost(String url, String jsonBody, Map<String, String> headers) throws Exception;
    HttpResponse sendPost(String url, String jsonBody) throws Exception;
}
