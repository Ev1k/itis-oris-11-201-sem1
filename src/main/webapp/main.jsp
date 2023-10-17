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
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Masters</title>
</head>
<body>

<div align="center">
    <h2>Choise the master</h2>
    <form action="list" method="post">
        Select the master:&nbsp;
        <select name="master">
            <c:forEach items="${listMaster}" var="master">
                <option value="${master.name}"
<%--                        <c:if test="${master.id eq selectedMasterId}">selected="selected"</c:if>--%>
                >
                        ${master.name}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
