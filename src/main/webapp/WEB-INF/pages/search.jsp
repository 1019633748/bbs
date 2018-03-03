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
<title>搜索</title>
<style type="text/css">

#big-info-div{
border:1px black solid;
width:60%;
height:800px;
margin-top:50px;
display:flex;
justify-content:space-between;
}

.info-div{
border:1px solid black;
width:30%;
}

.title-div,.content-div,.user-div{

}

.title-div:hover{
border:1px solid black;
cursor:pointer
}
.content-div:hover{
border:1px solid black;
cursor:pointer
}
.user-div:hover{
border:1px solid black;
cursor:pointer
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
<form action="/bbs/home/post/search" method="post">
<input name="search" type="text">&emsp;<input type="submit" value="搜索">
</form>
<div id="big-info-div">
<div class="info-div">
标题：
<c:forEach items="${title }" var="obj">
<div class="title-div click">${obj.name }</div>
<br>
</c:forEach>
</div>
<div class="info-div">
内容：
<c:forEach items="${content }" var="obj">
<div class="content-div click">${obj.content }</div>
<br>
</c:forEach>
</div>
<div class="info-div">
用户：
<c:forEach items="${user }" var="obj">
<div class="user-div click">${obj.name }</div>
<br>
</c:forEach>
</div>
</div>

</body>
<script type="text/javascript">
	$(document).ready(
			function() {
			
				$('.click').click(function(){
					$(this).html()
					var flag = $(this).prop('class').split(' ')[0]
					if("title-div"==flag){
						window.open('/bbs/user/get/content/'+$(this).html())
					}else if("content-div"==flag){
						
					}else if("user-div"==flag){
						window.open("/bbs/user/get/users/"+$(this).html())
					}
				})	
				
						
					
					
					
						
			})
</script>
</html>