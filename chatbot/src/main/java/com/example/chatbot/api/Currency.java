package com.example.chatbot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Currency {
    static double us;
    static double eur;
    public void getCurrency() throws IOException {
        String apiKey = "8bbcc6af5614d0a287cec1d38ed88ead";
        String link = "https://currate.ru/api/?get=rates&pairs=USDRUB,EURRUB&key=" + apiKey;
        URL url = new URL(link);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String json = br.readLine();
        JSONObject jobj = new JSONObject(json);
        if(!jobj.has("data")){
            System.err.println("error");
        };
        JSONArray data = jobj.getJSONArray("data");
        JSONObject jinfo = jar.getJSONObject(0);
        us = jinfo.getDouble("USDRUB");
        eur = jinfo.getDouble("EURRUB");
    }

    public static double getUs() {
        return us;
    }

    public static void setUs(double us) {
        Currency.us = us;
    }

    public static double getEur() {
        return eur;
    }

    public static void setEur(double eur) {
        Currency.eur = eur;
    }
}
