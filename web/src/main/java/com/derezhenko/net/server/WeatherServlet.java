package com.derezhenko.net.server;

import com.derezhenko.net.client.NetSample;
import com.derezhenko.net.dto.InfoDto;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "weather", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {
    private List<InfoDto> info = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        NetSample ns = new NetSample();
        Map<String, String> map = new HashMap();
        map.put("q", city);
        map.put("appid", "6386e645eb72e6792f173b9e26a5567f");
//        map.put("appid", "8d8d37ffe8dd312b5c5295632bf4ad67");

        long start = System.currentTimeMillis();
        req.setAttribute("startTime", start);
        String str = ns.get("https://api.openweathermap.org/data/2.5/weather", map);
        long end = System.currentTimeMillis();
        req.setAttribute("endTime", end);

        JSONObject js = new JSONObject(str);
        Double temperature = Double.parseDouble(js.getJSONObject("main").getString("temp"));
        String humidity = js.getJSONObject("main").getString("humidity");
        String description = js.getJSONArray("weather").getJSONObject(0).getString("main");

        info.add(new InfoDto(city, temperature, humidity, description));
//        LOGGER.info("user: " + req.getParameter("login"));
        System.out.println(description);
        System.out.println(humidity);
        System.out.println(temperature);
//        Cookie cookie1 = new Cookie("temperature", temperature);
//        Cookie cookie2 = new Cookie("humidity", humidity);
//        Cookie cookie3 = new Cookie("description", description);
//        Cookie cookie4 = new Cookie("city", city);
//        resp.addCookie(cookie1);
//        resp.addCookie(cookie2);
//        resp.addCookie(cookie3);
//        resp.addCookie(cookie4);

        resp.sendRedirect("main.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("info", info);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
