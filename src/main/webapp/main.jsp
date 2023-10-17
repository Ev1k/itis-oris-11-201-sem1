<%--
  Created by IntelliJ IDEA.
  User: Эвелина
  Date: 17.10.2023
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Masters</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div align="center">
    <h2>Выберите мастера:</h2>
    <form action="service.jsp" method="post">
        <select id="countries">
            <option value="apple">яблоко</option>
        </select>
        <br>
        <br>
        <input type="submit" value="select"/>
    </form>
    <script>
        $(document).ready(function () {
            $.ajax({
                url: '/masters',
                method: 'GET',
                success: function (data) {
                    $.each(data, function (id, master) {
                        var option = $('<option>').val(id).text(master);
                        $('#masters').append(option);
                    });
                }
            });
        });
    </script>
</div>
</body>
</html>
