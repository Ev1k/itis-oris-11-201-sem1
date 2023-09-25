package com.derezhenko.net.server;

import com.derezhenko.net.client.NetSample;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "weather", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        NetSample ns = new NetSample();
        Map<String, String> map = new HashMap();
        map.put("q", city);
        map.put("appid", "c6854b4eccb370e41dfaf2ebdb3e3bcb");
        String str = ns.get("https://api.openweathermap.org/data/2.5/weather", map);
        JSONObject js = new JSONObject(str);
        String temperature = js.getJSONObject("main").getString("temp");
        String humidity = js.getJSONObject("main").getString("humidity");
        String description = js.getJSONArray("weather").getJSONObject(0).getString("main");
        System.out.println(description);
        System.out.println(humidity);
        System.out.println(temperature);

        Cookie cookie1 = new Cookie("temperature", temperature);
        Cookie cookie2 = new Cookie("humidity", humidity);
        Cookie cookie3 = new Cookie("description", description);
        Cookie cookie4 = new Cookie("city", city);
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        resp.addCookie(cookie3);
        resp.addCookie(cookie4);

        resp.sendRedirect("weather.jsp");
    }
}
