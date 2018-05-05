<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
    <script src="/bbs/js/jquery-3.2.1.min.js"></script>
    <style type="text/css">
     form{
      margin: 10% auto;
      width: 30%
     }
    </style>
  </head>
 <body>
   <form action="/bbs/login" method="post">
    <h3 align="center">论坛登录</h3>
  <div class="form-group">
    <label for="exampleInputEmail1">用户名</label>
    <input type="text" class="form-control" id="exampleInputEmail1" name="nickname" placeholder="用户名">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">密码</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="输入密码">
  </div>
  <div class="form-check">
    <a href="/bbs/get/register">注册</a>丨<a href="/bbs/alter/password">忘记密码</a>
  </div>
  <br>
  <button type="submit" class="btn btn-primary">登录</button>
    ${failMsg }
</form>


</body>
</html>