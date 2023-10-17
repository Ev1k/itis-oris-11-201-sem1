<%--
  Created by IntelliJ IDEA.
  User: Эвелина
  Date: 18.10.2023
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <title>Masters</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div align="center">
  <h2>Выберите услугу:</h2>
  <select id="countries"></select>
  <script>
    $(document).ready(function() {
      $.ajax({
        url: '/services',
        method: 'GET',
        success: function(data) {
          $.each(data, function(id, master) {
            var option = $('<option>').val(id).text(service);
            $('#services').append(option);
          });
        }
      });
    });
  </script>
</div>
</body>
</html>