package org.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Sample {

    public static void main(String[] args) {
        // get
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userId=2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            System.out.println(readResponse(connection));
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        // post
            try {
                URL postUrl = new URL("https://jsonplaceholder.typicode.com/posts");
                HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
                postConnection.setRequestMethod("POST");
                postConnection.setRequestProperty("Content-Type", "application/json");
                postConnection.setRequestProperty("Accept", "application/json");
                postConnection.setDoOutput(true);
                String jsonInput = "{\"userId\": \"10\",\"id\": \"101\",\"title\": \"hello\",\"body\": \"world!\"}";
                try (OutputStream outputStream = postConnection.getOutputStream()) {
                    byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                    outputStream.write(input, 0, input.length);
                }
                System.out.println(postConnection.getResponseCode());
                System.out.println(readResponse(postConnection));
                postConnection.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//        // put
        try {
            URL putUrl = new URL("https://jsonplaceholder.typicode.com/posts/2");
            HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();
            putConnection.setRequestMethod("PUT");
            putConnection.setRequestProperty("Content-Type", "application/json");
            putConnection.setRequestProperty("Accept", "application/json");
            putConnection.setDoOutput(true);
            String jsonInput = "{\"userId\": \"1\",\"id\": \"2\",\"title\": \"hello\",\"body\": \"world!\"}";
            try (OutputStream outputStream = putConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(putConnection.getResponseCode());
            System.out.println(readResponse(putConnection));
            putConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        // delete
        try {
            URL deleteUrl = new URL("https://jsonplaceholder.typicode.com/posts/2");
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();
            deleteConnection.setRequestMethod("DELETE");
            System.out.println(deleteConnection.getResponseCode());
            System.out.println(readResponse(deleteConnection));
            deleteConnection.disconnect();
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

}

