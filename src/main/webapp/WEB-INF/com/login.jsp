<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	login page
	<form action="login" method="post">
		<div class="form-group">
			user name：<input type="text" name="userid" class="form-control"
				placeholder="User ID" />
		</div>
		<div class="form-group">
			password： <input type="password" name="password" class="form-control"
				placeholder="Password" />
		</div>
		<div class="form-group">
			<input type="checkbox" name="rememberme" /> Remember me
		</div>
		<div class="form-group">
			<input type="submit" value="提交" />
		</div>
	</form>
</body>
</html>