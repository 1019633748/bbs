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
<title>${post.post }</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bbs/js/wangEditor.js"></script>
<style type="text/css">

body{
background:#F7F7F7
}

.img-circle {
	border-radius: 50%;
	height: 40px;
	width: 40px;
	margin-right: 10px;
}

#reply-btn{
display:none
}
.container {
	width: 65%;
}

.replier, .floor {
	width: 300px;
	height: 40px;
	line-height: 40px
}

.reply {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	margin-top:20px;
	background:#FFF;
	padding-top:15px;
	padding-left:15px;
	padding-right:15px
}

.reply-bottom {
	display: flex;
	justify-content: space-between;
	margin-top:10px;
	margin-bottom:10px;
	padding-top:10px
}

.top, .down, .reply-span, .click-span,.thumb {
	cursor: pointer;
	user-select: none;
}

</style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="margin-bottom:0">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">网络论坛</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class=""><a href="/bbs/get/home"><span class="glyphicon glyphicon-home"></span>&nbsp;主页</a></li>
			<li><a href="/bbs/get/post"><span class="glyphicon glyphicon-pencil"></span>&nbsp;发帖</a></li>
		</ul>
	</div>
	<div class="pull-right" id="user-info">
			<img class="img-circle logined"
				src="/images/avatar/${bbs.url}"> <span id="username"
				class="logined"><a href="/bbs/get/users/${bbs.id }">${bbs.nickname }</a></span> <a class="logout"
				href="/bbs/get/login" target="_blank">登录</a><a class="logined" href="/bbs/logout">退出</a>
		</div>
	</div>
	</nav>
	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a
			href="/bbs/get/sections/${section.id }">${section.section }版块</a></li>
		<li class="breadcrumb-item active" aria-current="page">${post.post } （${total }）<a class="breadcrumb-item report-a" href="/bbs/report/posts/${post.id }">举报</a></li>
	</ol>
	</nav>
	<input hidden id="post-id" value="${post.id }">
	<div class="container">
		<c:forEach items="${reply}" var="item">
			<c:if test="${item.status==0 || item.status == 9 }">
			<div class="reply">
				<p>${item.reply}</p>
				<div class="reply-bottom">
					<div class="floor">
						#${item.no }&emsp;
						<c:choose>
						
							<c:when test="${item.topOrDown == 0 }">
							<span class="thumb"
								onclick="clickUp(${item.id},this)">已顶</span>&nbsp;<span>${item.top }</span>&nbsp;<span
								class="thumb" onclick="clickDown(${item.id},this)">踩</span>
							</c:when>
							
							<c:when test="${item.topOrDown == 1 }">
							<span class="thumb" 
								onclick="clickUp(${item.id},this)">顶</span>&nbsp;<span>${item.top }</span>&nbsp;<span
								class="thumb" onclick="clickDown(${item.id},this)">已踩</span>
							</c:when>
							
							<c:otherwise>
							<span class="thumb" 
								onclick="clickUp(${item.id},this)">顶</span>&nbsp;<span>${item.top }</span>&nbsp;<span
								class="thumb" onclick="clickDown(${item.id},this)">踩</span>
							</c:otherwise>
						</c:choose>	
							
							
							&emsp;<a class="report-a" href="/bbs/report/replys/${item.id }/${item.postId}">举报</a>
					</div>
					<div class="replier">
						<img class="img-circle img-thumbnail"
							src="/images/avatar/male.png"> <a
							href="/bbs/get/users/${item.userId }"><span>${item.author }</span></a>&emsp;
						<span><fmt:formatDate value="${item.createDate }"
								type="both" /></span> &emsp;<span class="glyphicon glyphicon-comment" onclick="reply(${item.no})"></span>
					</div>
				</div>
			</div>
			<br>
			</c:if>
		</c:forEach>
		
		<div class="container" style="width:400px">
		<input type="button"  class='btn btn-link' value="上一页" onclick="prevPage(${pageNum-1 })"/> <span>共&nbsp;${totalPage }&nbsp;页，当前&nbsp;<select>
			<c:forEach begin="1" end="${totalPage }" var="i">
				<c:choose>
					<c:when test="${pageNum==i }">
						<option selected value="${i }">第${i }页</option>
					</c:when>
					<c:otherwise>
						<option value="${i }">第${i }页</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></span>&nbsp;<input class='btn btn-link' type="button" value="下一页" onclick="nextPage(${pageNum+1 })"/>
		</div>
		<br>
		<div id="editor"></div>
		<button id="reply-btn">提交</button>
	
		
		
		
	</div>


	<script src="/bbs/js/common.js"></script>
	<script type="text/javascript">
	var total=${total}
	var url = '/bbs/get/posts/'+$('#post-id').val()
	$('select').change(function(){
		location.href=url+"?pageNum="+$(this).val()
	})	
		
	    function nextPage(pageNum){
		var pageIndex = pageNum;
		pageIndex = (pageIndex-1)*5>=total?pageIndex-1:pageIndex
	    	location.href=url+"?pageNum="+pageIndex
	    }
		
		function prevPage(pageNum){
			var pageIndex = pageNum;
			pageIndex = pageIndex==0?1:pageIndex
	    	location.href=url+"?pageNum="+pageIndex
	    }
	
		function isLogin(){
			if($('#username').text().length==0){
				return false
			}else{
				return true
			}
		}
	
		
		if (isLogin()) {
			$('.logined').show()
			$('.logout').hide()
		}

		function reply(floor){
			if (isLogin()) {
				if ($('#editor').html() != "") {
					$('#editor').html("") 
					showEditor(floor)
				} else {
					showEditor(floor)
				}
				scrollToEnd()
			}else{
				alert("请登录")
			}
		}
		
		function set_focus()
		{
		    el=document.getElementsByClassName('w-e-text')[0];
		    //el=el[0];  //jquery 对象转dom对象
		    el.focus();
		    if($.support.msie)
		    {
		        var range = document.selection.createRange();
		        this.last = range;
		        range.moveToElementText(el);
		        range.select();
		        document.selection.empty(); //取消选中
		    }
		    else
		    {
		        var range = document.createRange();
		        range.selectNodeContents(el);
		        range.collapse(false);
		        var sel = window.getSelection();
		        sel.removeAllRanges();
		        sel.addRange(range);
		    }
		}
		
		function showEditor(floor){
			var E = window.wangEditor
			var editor = new E('#editor')
			editor.create()
			$('.w-e-text').html('回复'+floor+'#：&nbsp;')
			$('#reply-btn').show()
			set_focus()
		}
		
		function clickUp(replyId,obj){
			if(isLogin()){
			var top = parseInt($(obj).next().html())
			//
			$.post('/bbs/put/top/'+replyId,function success(data){
			
					$(obj).next().html(data)
					if($(obj).html()=="顶"){
						$(obj).html("已顶")
						$(obj).next().next().html('踩')
					}else{
						$(obj).html("顶")
					}
			})
			
			}else{
				alert("请登录")
			}
		}
		
		function clickDown(replyId,obj){
			if(isLogin()){
			var down = parseInt($(obj).prev().html())
			//
			$.post('/bbs/put/down/'+replyId,function success(data){
				
					$(obj).prev().html(data)
					//$(obj).html("已顶")
					if($(obj).html()=="踩"){
						$(obj).html("已踩")
						$(obj).prev().prev().html('顶')
					}else{
						$(obj).html("踩")
					}
			})
			
			}else{
				alert("请登录")
			}
		}
		$('.report-a').click(function(){
			if(isLogin()){
				alert('举报成功！')
			}
		})
		
		
		$('#reply-btn').click(
				function() {
					var data ="&postId=" + $('#post-id').val()
							+ "&reply=" + $('.w-e-text').html()
					$.post('/bbs/post/reply', data, function(data) {
						if (data == "SUC") {
							alert("回复成功")
							window.location.href=""
						} else {
							alert("回复失败")
						}
					})
				})
				
			function scrollToEnd(){//滚动到底部
            var h = $(document).height()-$(window).height();
            $(document).scrollTop(h); 
}
	</script>

</body>
</html>