<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<style>
#avatar-div {
	width: 210px;
	height: 210px;
	margin: 0 auto
}

#avatar {
	width: 200px;
	height: 200px;
	border-radius: 50%;
}

.content-div {
	width: 400px;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
	margin-top: 20px;
	margin-bottom: 20px;
	letter-spacing: 4px;
	font-size: 14px;
}

.content-div:hover{
cursor:pointer;
font-size:18px;
}

.content-bottom {
	margin-left: 30px;
	font-size: 13px;
	letter-spacing: 2px;
}

#contents-div{
width:50%;
/* display:inline-block; */
float:left;
}
#title-div{
width:50%;
/* display:inline-block; */
float:right
}
.section-span,.title-span{
margin-left:5px;
margin-right:5px;
font-weight: 600;
cursor:pointer;
}
.title-span:hover{
font-size:15px
}
.section-span:hover{
font-size:15px
}

.section-span{
color:red;
}
.title-span{
color:blue;
}

#more-content-span{
cursor:pointer;
height:40px;
line-height:40px;
display:block;
width:50%
}
#more-title-span{
cursor:pointer;
height:40px;
line-height:40px;
display:block;
width:50%;
float:right
}
.content-hr{
width:70%;
margin-left:0
}

#info-div{
font-size:16px;
width:300px;
margin:20px auto;
}
#attention-div{
width:100px;
margin:0 auto;
font-size:12px;
}
#attention-span,#fans-span{
cursor:pointer
}
#fans-span:hover{
text-decoration: underline
}
#attention-span:hover{
text-decoration: underline
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${user.name }的主页</title>
</head>
<body>
	<div id="avatar-div">
		<img id="avatar" src="/bbs/image/avatar/${uri }">
	</div>
	
	<div id="attention-div">
	关&emsp;注:<span id="attention-span">&nbsp;${attention }</span>
	<br>被关注:<span id="fans-span">&nbsp;${fans }</span>
	</div>
	
	<div id="info-div">
	用户名：${user.name }
	<br> 性&emsp;别：${user.sex }
	<br> 邮&emsp;箱：${user.email }
	<br> 电&emsp;话：${user.phone }
	<br> 签&emsp;名：${user.sign }
	<br>
	</div>
	<hr>
	<div id="contents-div"><br>该用户的回复:</div>
	<div id="title-div"><br>该用户的话题:</div>
	<span id="more-content-span">查看更多回复</span>
	<span id="more-title-span">查看更多话题</span>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var userId = ${user.id}
		var pageNum=1;
		var pageSize=3;
		var uri='/bbs/user/get/content/';
		var lastContentId=0;
		var abc = 0;
		
		getContent(userId,pageNum,pageSize);

<%--获得评论--%>	
		function getContent(userId,pageNum,pageSize){
			tempUri=uri.concat(userId+"/").concat(pageNum+"/").concat(pageSize)
			$.post(tempUri,function(data){
				if(lastContentId==data[data.length-1].id){
					$('#more-content-span').html('没有更多了')
					return false;
				}else{
					lastContentId = data[data.length-1].id
				}
				
				for(var i=0;i<data.length;i++){
					tempTime = Date.now()
					//sleep(100);
					$('#contents-div').append("<div class='content-div' title='点击跳转'>"+data[i].content+"</div>")
					$('#contents-div').append("<div class='content-bottom'>于"+data[i].createDate+"在<span class='section-span' title='点击跳转'>"+data[i].section+"</span>版块的<span class='title-span' title='点击跳转'>"+data[i].title+"</span>话题中回复</div>")					
					$('#contents-div').append("<hr class='content-hr'>")
				}
				$('#contents-div').append("<p>. . .</p>")
				document.getElementById("more-content-span").scrollIntoView();
			})
		}
		
		function sleep(t){
			while(Date.now()-t<=tempTime);
		}
<%--更多评论 --%>
		$('#more-content-span').click(function(){
			pageNum+=1;
			//pageSize+=1;
			getContent(userId,pageNum,pageSize);
		})
<%--跳转到某版块下的标题页面--%>	
		$('body').on('click','.section-span',function(){
			window.open('/bbs/user/get/titles/'+$(this).html())
		})
<%--跳转到某话题页面--%>		
		$('body').on('click','.title-span',function(){
			window.open('/bbs/user/get/content/'+$(this).html())
		}) 
		
	
	})
	
	</script>
</body>
</html>