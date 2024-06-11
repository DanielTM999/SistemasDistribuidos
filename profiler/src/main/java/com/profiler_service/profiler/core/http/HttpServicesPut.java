package com.profiler_service.profiler.core.http;

import java.util.Map;

public interface HttpServicesPut {
    HttpResponse sendPut(String url, String jsonBody, Map<String, String> headers) throws Exception;
    HttpResponse sendPut(String url, String jsonBody) throws Exception;
}
