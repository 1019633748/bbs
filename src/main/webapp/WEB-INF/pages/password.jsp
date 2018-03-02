<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
	
	<style type="text/css">
	#input-code-div,#input-password-div{
	display:none
	}
	</style>
</head>
<body>
<div id="input-name-or-email-div">
填写你的用户名或邮箱<br>
	<input id="name-or-email" name="nameOrEmail" type="text">
	<input id="noe-submit" type="button" value="提交">
</div>
<div id="input-code-div">
输入验证码<br>
	<input id="code-input" name="code" type="text">
	<input id="code-submit" type="button" value="提交">
</div>
<div id="input-password-div">
输入新的密码<br>
	<input id="password-input" name="password" type="text">
	<input id="password-submit" type="button" value="提交">
</div>





<script type="text/javascript">
$(document).ready(function(){
	//
	$('#noe-submit').click(function(){
		$.post('/bbs/user/post/nameoremail','nameOrEmail='+$('#name-or-email').val(),function(data){
			if(data=="SUC"){
				$('#input-name-or-email-div').hide()
				$('#input-code-div').show()
			}else{
				alert("没有找到用户名或邮箱")
			}
		})
	})
	
	//
	$('#code-submit').click(function(){
		$.post('/bbs/user/post/code','code='+$('#code-input').val(),function(data){
			if(data=="correct"){
				$('#input-code-div').hide()
				$('#input-password-div').show()
			}else{
				alert("验证码错误")
			}
		})
	})
	
	//
	$('#password-submit').click(function(){
		$.post('/bbs/user/post/password','password='+$('#password-input').val(),function(data){
			alert(data+"的密码修改成功")
		})
	})
	
	
	
	
	
	
	
	
	
	
	
})
</script>
</body>
</html>