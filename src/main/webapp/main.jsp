<%@ page import="java.util.Objects" %>
<%@ page import="com.derezhenko.net.dto.InfoDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Эвелина
  Date: 19.09.2023
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/jquery@3.6/dist/jquery.min.js" rel="stylesheet">
    <script>
        $(document).on("click", "#ajax-button", function () {
            console.log("Debug");
            city = $("#city-parameter").val();
            console.log(city);
            $.get("/ajax/hello?city="+city, function (response){
                $("#ajax-response").text(response);
            })
        })
    </script>
</head>
<body>
    <%
        String user = null;
        String sessionUser = String.valueOf(session.getAttribute("username"));
        if (sessionUser == null) {
            response.sendRedirect("login.html");
        } else {
            user = sessionUser;
        }
        String cookieUser = null;
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if ("username".equalsIgnoreCase(cookie.getName())) {
                    cookieUser = cookie.getValue();
                } else if ("jsessionid".equalsIgnoreCase(cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
        } else {
            sessionId = session.getId();
        }


        Double temperature = 0.0;
        String humidity = null;
        String description = null;
        String city = null;

    List<InfoDto> info = null;
    info = (List<InfoDto>) request.getAttribute("info");
    if (info != null) {
        city = info.get(0).getCity();
        humidity = info.get(0).getHumidity();
        temperature = info.get(0).getTemperature() - 273;
        description = info.get(0).getDescription();
    }

//        Cookie[] cookies1 = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies1) {
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
    %>

<h3>
    Hello, <%=user%>! Login successfully
    <br>
    Session ID = <%=sessionId%>
    <br>
    cookie username = <%=cookieUser%>
</h3>
    <form action="/weather.jsp" method="post">
        City:
        <input id="city-parameter" type="text" name="city"/>
<%--        <br>--%>
<%--        <input type="submit" value="Get Weather">--%>
    </form>
    <div id="ajax-response"></div>
    <form>
        <input type="button" id="ajax-button" value="Get weather">
    </form>
    <br>


</body>
</html>
