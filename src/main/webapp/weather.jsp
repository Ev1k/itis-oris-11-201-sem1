<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  Date: 25.09.2023
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather</title>
</head>
<body>
<%
    String temperature = null;
    String humidity = null;
    String description = null;
    String city = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            System.out.println("Cockie: "+cookie.getName());
            if (Objects.equals(cookie.getName(), "temperature")) {
                temperature = cookie.getValue();
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
%>

<h3>
    Weather in <%=city%>:
    <br>
    Temperature: <%=temperature%>
    <br>
    Humidity: <%=humidity%>
    <br>
    <%=description%>
</h3>

</body>
</html>
