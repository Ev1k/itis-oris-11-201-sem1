package com.example.chatbot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Weather {
    static Double weather;
    public static Double getWeather(String town) throws IOException {
        String str = URLEncoder.encode(town, "UTF-8");
        System.out.println(str);
        String link = "https://geocoding-api.open-meteo.com/v1/search?name=" + str + "&count=1&language=ru&format=json";
        URL url = new URL(link);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String json = br.readLine();
        JSONObject jobj = new JSONObject(json);
        if(!jobj.has("results")){
            System.err.println("This town isn't exist. Please, write correct town");
            System.exit(1);
        };
        JSONArray jar = jobj.getJSONArray("results");
        JSONObject jinfo = jar.getJSONObject(0);
        double lat = jinfo.getDouble("latitude");
        double lon = jinfo.getDouble("longitude");
        is.close();

        String link1 = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon + "&current_weather=true";
        URL url1 = new URL(link1);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(url1.openStream()));
        JSONObject jo = new JSONObject(br1.readLine());
        JSONObject jo1 = jo.getJSONObject("current_weather");
        weather = jo1.getDouble("temperature");
        br.close();
        br1.close();
        return weather;
    }
}
