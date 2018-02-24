<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新用户注册</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div>
<form action="/bbs/user/post/user" method="post">
昵称：<input id="username-input" type="text" name="name"> <span id="username-hint-span"></span>
<br>密码：<input id="-input" type="text" name="password"> <span id=""></span>
<br>确认：<input id="-input" type="text" name=""> <span id=""></span>
<br>邮箱：<input id="-input" type="text" name="email"> <span id=""></span>
<br>电话：<input id="-input" type="text" name="phone"> <span id=""></span>
<br>性别：♂<input type="radio" name="sex" checked="checked" value="男">♀<input type="radio" name="sex" value="女">
<br><input id="test" type="submit" value="提交">
</form>
</div>





<script type="text/javascript">
$(document).ready(function(){
	
	$('#username-input').bind('input propertychange',function(){
		var username = $(this).val()
		if(username.length>0){
			var tempUri= "/bbs/user/verify/"+username
			$.post(tempUri,function(data){
				$('#username-hint-span').html(data)
				if(data!="可以使用"){
					$('#test').prop('disabled',true)
				}
			})
		}else{
			$('#username-hint-span').html("")
		}
	})
	
	
	
	
	
	
	
	
})
</script>
</body>
</html>