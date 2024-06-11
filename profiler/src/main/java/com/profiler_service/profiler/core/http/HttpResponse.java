package com.profiler_service.profiler.core.http;

public abstract class HttpResponse {
    public abstract String getBody();
    public abstract int getStatusCode();
}
