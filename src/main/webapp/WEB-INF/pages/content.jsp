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
<title>content</title>
<style type="text/css">
p {
	width: 70%
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
max-weight:500px; 
}
</style>
</head>
<body>
	<h3>${title.name }</h3>
	<h4>${title.firstFloor }</h4>
	<h5>
		楼主:${title.author }&emsp;
		<fmt:formatDate value="${title.createDate}" pattern="yyyy-MM-dd" />
	</h5>
	<c:forEach items="${content }" var="obj">
	
		<c:forEach items="${obj.uris }" var="uri">
		<img class="content-image" src="/bbs/image/content/${uri }">
		</c:forEach>
		
		
		<p>&emsp;&emsp;${obj.content }</p>
		<span hidden>${obj.id }</span>
		<span class="click-up" onselectstart="return false">👍</span>
		<span class="up-down">${obj.up }</span>
		&emsp;
		<span class="click-down" onselectstart="return false">👎</span>
		<span class="up-down">${obj.down }</span>&emsp;by:${obj.authorName }&emsp;<fmt:formatDate
			value="${obj.createDate}" pattern="yyyy-MM-dd" />
	</c:forEach>
	<div>
		<input id="first-page" class="first-or-last" type="button" value="<"><input id="prev" class="prev-or-next" type="button" value="<<">&emsp;<span id="pageNum">${pageNum }</span>&emsp;<input
			id="next" class="prev-or-next" type="button" value=">>"><input id="last-page" class="first-or-last" type="button" value=">">
	</div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
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
				
				//当前页前缀、后缀
				for(var i=0;i<prefix;i++){
					if(pageNum-i>1){
						var prefixPage = pageNum-i-1
						$('#pageNum').before("<span class='prefix-suffix'>"+prefixPage+"</span>")
					}
				}
				for(var i=0;i<suffix;i++){
					if(pageNum+i<totalPage){
						var suffixPage = pageNum+i+1
						$('#pageNum').after("<span class='prefix-suffix'>"+suffixPage+"</span>")
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
							var up = $(this).next()
							userId = 1;
							contentId = $(this).prev().html()
							$.post('/bbs/content/put/up', 'userId=' + userId
									+ '&contentId=' + contentId,
									function(data) {
										up.html(data)
									})
						})

				//踩
				$('.click-down').click(
						function() {
							var down = $(this).next()
							userId = 1;
							contentId = $(this).prev().prev().prev().html()
							$.post('/bbs/content/put/down', 'userId=' + userId
									+ '&contentId=' + contentId,
									function(data) {
										down.html(data)
									})
						})

			})
</script>
</html>