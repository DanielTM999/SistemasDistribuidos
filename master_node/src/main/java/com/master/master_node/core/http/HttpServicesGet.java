package com.master.master_node.core.http;

import java.util.Map;

public interface HttpServicesGet {
    HttpResponse sendGet(String url, Map<String, String> headers) throws Exception;
    HttpResponse sendGet(String url) throws Exception;
}
