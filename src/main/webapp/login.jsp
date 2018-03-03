<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>section</title>
</head>
<body>
<form action="login/login" method="post">
用户名：<input type="text" name="name" value="1"><br>
密&emsp;码：<input type="text" name="password" value="1">
<br><input type="submit" value="登录">
</form>
<br>
<a href="user/register">注册</a>丨<a href="/bbs/user/get/password">忘记密码</a>
</body>
</html>