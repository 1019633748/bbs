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
<title>content</title>
<style type="text/css">
body{
}

p {
	width: 70%;
}

.click-up, .click-down ,.prefix-suffix,#pageNum{
	cursor: pointer
}

.up-down {
	display: inline-block;
	width: 20px
}

.prefix-suffix,#pageNum{
margin-left:10px;
margin-right:10px;
text-decoration:underline
}

#pageNum{
color:red;
text-decoration:none
}

.prev-or-next,.first-or-last{
border:none;
background:none;
cursor:pointer;
font-size:20px;
}

.content-image{
max-width:500px;
max-height:500px; 
}
.avatar{
width:40px;
height:40px;
border-radius:50%;
position:absolute;
margin-left:30px;
top:50%;
transform:translateY(-50%);
cursor:pointer
}
.author-name{
margin-left:10px
}
.by{
margin-left:70px
}
.content-buttom{
height:50px;
line-height:50px;
position:relative;
margin-left:30%
}

hr{
width:70%;
margin-left:0
}

#page-div{
height:60px;
line-height:60px;
margin-left:20px;
}
.author-name{
cursor:pointer
}

.nof{
margin-right:100px
}

#first-floor-bottom{
height:50px;
line-height:50px;
position:relative;
margin-left:30%
}

.reply-span{
margin-left:50px;
cursor:pointer
}

.reply-div{
width:70%;
height:200px;
border:1px solid black;
display:none
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
<span id="currentUserId" hidden>${bbs.id }</span>
	<h3>${title.name }</h3>
	<h4>${title.firstFloor }</h4>
	<div id="first-floor-bottom">
		1&nbsp;#&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 楼主:<img class="avatar" src="/images/avatar/${title.uri }"><span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span><span>${title.author }</span>&emsp;
		<fmt:formatDate value="${title.createDate}" pattern="yyyy-MM-dd" /> <span class="reply-span" onselectstart="return false;">回复</span>
	</div>
	<div class="reply-div">
	<form action="/bbs/content/post/content" method="post" enctype="multipart/form-data">
		<input hidden type="text" name="titleId" value="${title.id }">
		<input hidden type="text" name="authorId" value="${bbs.id }">
		<input name="contentImg" type="file">
		<textarea name="content" rows="10" cols="160" maxlength="10" placeholder="请输入内容"></textarea>
		<input type="submit" value="提交">
	</form>
	</div>
	<br>
	<br>
	<hr>
	<br>
	<c:forEach items="${content }" var="obj">
	
		<c:forEach items="${obj.uris }" var="uri">
		<img class="content-image" src="/images/content/${uri }">
		<br>
		</c:forEach>
		
		
		<p>&emsp;&emsp;${obj.content }</p>
		<div class="content-buttom">
		<span class="num-of-floors"></span>
		<span class="nof">#</span>
		<span hidden>${obj.id }</span>
		<span class="click-up" onselectstart="return false">👍</span>
		<span class="up-down">${obj.up }</span>
		&emsp;
		<span class="click-down" onselectstart="return false">👎</span>
		<span class="up-down">${obj.down }</span>&emsp;<img class="avatar" src="/images/avatar/${obj.uri }">&emsp;<span class="by">by:</span><span class="author-name">${obj.authorName }</span>&emsp;<fmt:formatDate
			value="${obj.createDate}" pattern="yyyy-MM-dd" /><span class="reply-span" onselectstart="return false;">回复</span>
			</div>
			<div class="reply-div"></div>
			<hr>
	</c:forEach>
	<div id="page-div">
		<input id="first-page" class="first-or-last" type="button" value="<"><input id="prev" class="prev-or-next" type="button" value="<<">&emsp;<span id="pageNum">${pageNum }</span>&emsp;<input
			id="next" class="prev-or-next" type="button" value=">>"><input id="last-page" class="first-or-last" type="button" value=">">
	</div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				var currentUserId = $('#currentUserId').html()
				var pageSize = 5
				var total = ${totalPageSize}
				var totalPage = Math.ceil(total/pageSize)
				var pageNum = parseInt($('#pageNum').html())
				var titleId = ${title.id}
				var oldPageNum = pageNum
				var prefix = 2
				var suffix = 2
				/* $(document).click(function(e){
					console.log($(e.target).prop('id'))
				}) */
				
				$('.num-of-floors').each(function(index){
					if(pageNum==1){
						$(this).html(index+2)
					}else{
						$(this).html(pageNum*5+index-3);
					}
				})
				
				//当前页前缀、后缀
				for(var i=0;i<prefix;i++){
					if(pageNum-i>1){
						var prefixPage = pageNum-i-1
						$('#pageNum').siblings().first().next().next().before("<span class='prefix-suffix'>"+prefixPage+"</span>")
					}
				}
				for(var i=0;i<suffix;i++){
					if(pageNum+i<totalPage){
						var suffixPage = pageNum+i+1
						$('#pageNum').siblings().last().prev().prev().after("<span class='prefix-suffix'>"+suffixPage+"</span>")
					}
				}
				
				//首页，末页
				$('.first-or-last').click(function(e){
					var flag = $(e.target).prop('id')
					var toPageNum = 1;
				    if(flag=="last-page"){
						toPageNum = totalPage
					}
					window.location.href="/bbs/content/get/content/"+titleId+"/"+toPageNum
				})
				
				//上一页，下一页
				$('.prev-or-next').click(function(e){
					var flag = $(e.target).prop('id')		
						if(flag=="prev"&&pageNum>1){
							pageNum--;
							$('#pageNum').html(pageNum)
						}else if(flag=="next"&&pageNum<totalPage){
							pageNum++;
							$('#pageNum').html(pageNum)
						}
					if(oldPageNum!=pageNum){
						window.location.href="/bbs/content/get/content/"+titleId+"/"+pageNum
					}					
				})
				
				//跳页
				$('.prefix-suffix').click(function(){
					var toPageNum = $(this).html()
					window,location.href="/bbs/content/get/content/"+titleId+"/"+toPageNum
				})
				
				
				
				/* //上一页
				$('#prev').click(function() {
					pageNum--;
					$('#pageNum').html(pageNum)
				})

				//下一页
				$('#next').click(function() {
					pageNum++;
					$('#pageNum').html(pageNum)
				}) */

				//顶
				$('.click-up').click(
						function() {
							if(currentUserId==''){
								alert("请登录")
								return false
							}
							var up = $(this).next()
							userId = 1;
							contentId = $(this).prev().html()
							$.post('/bbs/content/put/up', 'userId=' + currentUserId
									+ '&contentId=' + contentId,
									function(data) {
										up.html(data)
									})
						})

				//踩
				$('.click-down').click(
						function() {
							if(currentUserId==''){
								alert("请登录")
								return false
							}
							var down = $(this).next()
							userId = 1;
							contentId = $(this).prev().prev().prev().html()
							$.post('/bbs/content/put/down', 'userId=' + currentUserId
									+ '&contentId=' + contentId,
									function(data) {
										down.html(data)
									})
						})
						
						//点击头像跳转
						$('.avatar').click(function(){
							var name=$(this).next().next().html();
							window.open("/bbs/user/get/users/"+name)
						})
						
						//点击名字跳转
						$('.author-name').click(function(){
							var name=$(this).html();
							window.open("/bbs/user/get/users/"+name)
						})
						
					
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
						
						
			})
</script>
</html>