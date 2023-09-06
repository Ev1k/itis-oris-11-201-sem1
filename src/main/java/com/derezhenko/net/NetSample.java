package com.derezhenko.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetSample {
    public static void main(String[] args) {

        //get

        try{
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder stringBuilder = new StringBuilder();
                String input;
                while ((input = br.readLine()) != null) {
                    stringBuilder.append(input);
                }
                System.out.println(stringBuilder);
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }

        //post
        try{
            URL url = new URL("https://gorest.co.in/public/v2/users");
            HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();

            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer 1b809256d3ce935519c3356a3540531aaef98371f9310d54ad6109c073c841e0");

            postConnection.setDoOutput(true);

            String jsonInput = "{\"name\":\"Test Test\",\"email\":\"gfgfg@gfh.ru\",\"gender\":\"female\",\"status\":\"active\"}";

            try (OutputStream os = postConnection.getOutputStream()) {
                byte [] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader br = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))){
                StringBuilder stringBuilder = new StringBuilder();
                String inp;
                while ((inp = br.readLine()) != null) {
                    stringBuilder.append(inp);
                }
                System.out.println(stringBuilder);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
