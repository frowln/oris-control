package org.example.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClient implements HttpClientImpl {
    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            StringBuilder uri = new StringBuilder(url);
            if (params != null && !params.isEmpty()) {
                uri.append("?");
                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (!first) {
                        uri.append("&");
                    }
                    uri.append(entry.getKey()).append("=").append(entry.getValue());
                    first = false;
                }
            }
            URL urlGet = new URL(uri.toString());
            HttpURLConnection connection = (HttpURLConnection) urlGet.openConnection();
            connection.setRequestMethod("GET");
            headers.forEach(connection::setRequestProperty);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            String result = readResponse(connection);
            connection.disconnect();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            headers.forEach(postConnection::setRequestProperty);
            postConnection.setDoOutput(true);
            jsonData(postConnection, data);
            String result = readResponse(postConnection);
            postConnection.disconnect();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL putUrl = new URL(url);
            HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();
            putConnection.setRequestMethod("PUT");
            headers.forEach(putConnection::setRequestProperty);
            putConnection.setDoOutput(true);
            jsonData(putConnection, data);
            String result = readResponse(putConnection);
            putConnection.disconnect();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL deleteUrl = new URL(url);
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();
            deleteConnection.setRequestMethod("DELETE");
            headers.forEach(deleteConnection::setRequestProperty);
            String result = readResponse(deleteConnection);
            deleteConnection.disconnect();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }

    private void jsonData(HttpURLConnection connection, Map<String, String> data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(data);
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
    }
}
