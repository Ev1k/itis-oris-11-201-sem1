<%--
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
    %>

<h3>
    Hello, <%=user%>! Login successfull
    <br>
    Session ID = <%=sessionId%>
    <br>
    cookie username = <%=cookieUser%>
</h3>

    <form action="/weather" method="post">
        City:
        <input type="text" name="city"/>
        <br>
        <input type="submit" value="Get Weather">
    </form>

</body>
</html>
