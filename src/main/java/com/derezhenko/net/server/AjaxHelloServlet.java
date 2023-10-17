package com.derezhenko.net.server;

import com.derezhenko.net.client.NetSample;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/ajax/hello")
public class AjaxHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        String temperature = "";
        String humidity = "";
        String description = "";
        String city = "";

        city = req.getParameter("city");
        System.out.println(city);
        NetSample ns = new NetSample();
        Map<String, String> map = new HashMap();
        map.put("q", city);
        map.put("appid", "6386e645eb72e6792f173b9e26a5567f");
        String str = ns.get("https://api.openweathermap.org/data/2.5/weather", map);

        JSONObject js = new JSONObject(str);
        temperature = js.getJSONObject("main").getString("temp");
        humidity = js.getJSONObject("main").getString("humidity");
        description = js.getJSONArray("weather").getJSONObject(0).getString("main");

//        Cookie[] cookies = req.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                System.out.println("Cockie: "+cookie.getName());
//                if (Objects.equals(cookie.getName(), "temperature")) {
//                    temperature = ((Double.parseDouble(cookie.getValue())) - 273);
//                }
//                if (Objects.equals(cookie.getName(), "humidity")) {
//                    humidity = cookie.getValue();
//                }
//                if (Objects.equals(cookie.getName(), "description")) {
//                    description = cookie.getValue();
//                }
//                if (Objects.equals(cookie.getName(), "city")) {
//                    city = cookie.getValue();
//                }
//            }
//        } else {
//            String strErr = "error";
//        }
        resp.getWriter().write(String.format("Тeмпература в " + city + ": " + temperature + "\n "));
        resp.getWriter().write(String.format("Влажность: " + humidity + "\n "));
        resp.getWriter().write(String.format("Описание погоды: " + description));
    }
}
