<%--
  Created by IntelliJ IDEA.
  User: dqf
  Date: 2015/8/14
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>send mail</title>
</head>
<body>
<form method="post" action="/sendMail">
  send to:<input type="text" name="mailAddress"/><br/>
  replay to:<input type="text" name="replayAddress"/><br/>
  from:<input type="text" name="fromAddress"/><br/>
  subject:<input type="text" name="subject"/><br/>
  message:<input type="text" name="message"/><br/>
  <input type="submit" value="submit"/> <input type="reset" value="cancel"/>
</form>
state:${msg}
</body>
</html>
