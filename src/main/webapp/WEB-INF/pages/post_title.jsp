<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/bbs/js/common.js"></script>
	<link rel="stylesheet" href="/bbs/css/common.css">
<title>发帖</title>
<style type="text/css">
.reply-span{
margin-left:50px;
cursor:pointer
}

.reply-div{
width:70%;
height:250px;
border:1px solid black;
}

</style>
</head>
<body>
<header>
<span id="page-title-span">这里写标题</span><br>
<div id="user-div">
<span id="username-span">${bbs.name }</span>
<span id="idd" hidden>${bbs.id }</span>
<a id="login-logout" href=""></a>
<a href="/bbs/login/logout">logout</a>
</div>
</header>
	<div class="reply-div">
	<form action="/bbs/content/post/title" method="post">
		选择版块：<select id="section-select" name="sectionId"></select>
		<br>
		输入标题：<input name="name" type="text">
		<textarea name="firstFloor" rows="10" cols="160" maxlength="10" placeholder="请输入内容"></textarea>
		<input type="submit" value="提交">
	</form>
	</div>
	
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
			
					$('.reply-span').click(function(){
						/* if($(this).next().prop('class')!="reply-div"){
						$(this).after("<div class='reply-div'></div>")
						}else{
							$(this).next().remove()
						} */
						if(currentUserId==''){
							alert("请登录")
							return false
						}
						
						if($(this).parent().next().css('display')=="none"){
							$(this).parent().next().show()		
						}else{
							$(this).parent().next().hide()
						}
					})
						
					//
						$.post('/bbs/content/get/sections',function(data){
							for(var i=0;i<data.length;i++){
								$('#section-select').append("<option value='"+data[i].id+"'>"+data[i].sectionName+"</option>")
							}
						})
					
						$('#section-select').change(function(){
							
						})
					
					
					
						
			})
</script>
</html>