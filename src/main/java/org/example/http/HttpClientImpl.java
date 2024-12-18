package org.example.http;

import java.util.Map;

public interface HttpClientImpl {
    String get(String url, Map<String, String> headers, Map<String, String> params);
    String post(String url, Map<String, String> headers, Map<String, String> data);
    String put(String url, Map<String, String> headers, Map<String, String> data);
    String delete(String url, Map<String, String> headers, Map<String, String> data);
}
