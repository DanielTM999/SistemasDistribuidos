package com.isp_server.isp.core.http;

public abstract class HttpResponse {
    public abstract String getBody();
    public abstract int getStatusCode();
}
