<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
header{
width:100%;
height:100px;

}
#user-div{
position:relative;
height:45px;
width:500px;
line-height:45px;
}
#avatar{
width:40px;
height:40px;
border-radius:50%;
position:absolute;
margin-left:30px;
top:50%;
transform:translateY(-50%);
cursor:pointer
}
#username-span{
margin-left:100px;
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
<a href="/bbs/login/logout">fuck</a>
</div>
</header>
	<c:forEach items="${sections }" var="obj">
		<h3><a href="${pageContext.request.contextPath}/section/get/section/${obj.id }">${obj.sectionName }</a></h3>
		<c:forEach items="${obj.titles }" var="subObj">
		<h5><a href="${pageContext.request.contextPath }/content/get/content/${subObj.id}/1">${subObj.name }</a> ${subObj.author } <fmt:formatDate value="${subObj.createDate }" pattern="yyyy-MM-dd"/></h5>
		</c:forEach>
	</c:forEach>
</body>
<script type="text/javascript">
$(document).ready(function(){
	
	if($('#username-span').html().length==0){
		$('#username-span').html("旅客你好")
		$('#login-logout').html("登录")
		$('#login-logout').prop('href','/bbs/login/get/login')
	}else{
		var id = $('#idd').html()
		var uri = '/bbs/home/get/avatar/'+id
		$.post(uri,function(data){
			$('#username-span').before("<img id='avatar' src='/bbs/image/avatar/"+data+"'>")
		})
		$('#login-logout').html("退出")
		$('#login-logout').prop('href','/bbs/login/logout')
	}
})
</script>
</html>