<%--
  Created by IntelliJ IDEA.
  User: dqf
  Date: 2015/8/14
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>customer</title>
</head>
<body>
<c:forEach items="${customers}" var="list">
  ${list.getFirstName()}-${list.getLastName()}<br/>
<%--<c:out value="${list.toString()}"/><br/>--%>
</c:forEach>
</body>
</html>
