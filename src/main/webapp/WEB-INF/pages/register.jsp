<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<style type="text/css">
form {
	margin: 10% auto;
	width: 400px
}
</style>
</head>
<body>
	<form action="/bbs/register" method="post">
		<h3 align="center">新用户注册</h3>
		<br>
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label"
				name="nickname">昵称</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="" name="nickname"
					placeholder="昵称">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">邮箱</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="inputEmail3"
					name="email" placeholder="邮箱">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputPassword3" class="col-sm-2 col-form-label">密码</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="inputPassword" name="password"
					placeholder="密码">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputPassword3" class="col-sm-2 col-form-label">确认</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="inputPasswordAgain"
					placeholder="确认密码">
			</div>
		</div>
		<fieldset class="form-group">
			<div class="row">
				<legend class="col-form-label col-sm-2 pt-0">性别</legend>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="sex"
							id="gridRadios1" value="1" checked> <label
							class="form-check-label" for="gridRadios1"> 男 </label>&emsp;&emsp;
						<input class="form-check-input" type="radio" name="sex"
							id="gridRadios2" value="0"> <label
							class="form-check-label" for="gridRadios2"> 女 </label>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="form-group row">
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary">注册</button>
			</div>
		</div>
	</form>



</body>
</html>