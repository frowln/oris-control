package org.example.http;

import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        // get
        System.out.println(httpClient.get("http://localhost:8080/hello", new HashMap<>(), new HashMap<>()));
        // post
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        System.out.println(httpClient.post("http://localhost:8080/hello", new HashMap<>(), data));
        // put
        Map<String, String> putData = new HashMap<>();
        putData.put("putKey", "putValue");
        System.out.println(httpClient.put("http://localhost:8080/hello", new HashMap<>(), putData));
        // delete
        System.out.println(httpClient.delete("http://localhost:8080/hello", new HashMap<>(), new HashMap<>()));
    }
}
