package com.master.master_node.core.http;

public abstract class HttpResponse {
    public abstract String getBody();
    public abstract int getStatusCode();
}
