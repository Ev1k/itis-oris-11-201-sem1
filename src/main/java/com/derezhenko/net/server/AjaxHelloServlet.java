package com.derezhenko.net.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/ajax/hello")
public class AjaxHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        Double temperature = 0.0;
        String humidity = null;
        String description = null;
        String city = null;

//    List<InfoDto> info = null;
//    info = (List<InfoDto>) request.getAttribute("info");
//    if (info != null) {
//        city = info.get(0).getCity();
//        humidity = info.get(0).getHumidity();
//        temperature = Double.parseDouble(info.get(0).getTemperature()) - 273;
//        description = info.get(0).getDescription();
//    }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cockie: "+cookie.getName());
                if (Objects.equals(cookie.getName(), "temperature")) {
                    temperature = ((Double.parseDouble(cookie.getValue())) - 273);
                }
                if (Objects.equals(cookie.getName(), "humidity")) {
                    humidity = cookie.getValue();
                }
                if (Objects.equals(cookie.getName(), "description")) {
                    description = cookie.getValue();
                }
                if (Objects.equals(cookie.getName(), "city")) {
                    city = cookie.getValue();
                }
            }
        } else {
            String strErr = "error";
        }
        resp.getWriter().write(String.format("Тeмпература в " + city+ ": " + temperature + "\n"));
        resp.getWriter().write(String.format("Влажность: " + humidity + "\n"));
        resp.getWriter().write(String.format("Описание погоды: " + description));
    }
}
