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
.title-div {
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

.title-div:hover{
cursor:pointer;
font-size:18px;
}

.content-bottom {
	margin-left: 30px;
	font-size: 13px;
	letter-spacing: 2px;
}
.title-bottom {
	margin-left: 30px;
	font-size: 13px;
	letter-spacing: 2px;
}

#contents-div{
width:50%;
/* display:inline-block; */
float:left;
}
#titles-div{
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
width:50%;
}
#more-title-span{
cursor:pointer;
height:40px;
line-height:40px;
display:block;
width:50%;
margin-left:50%
}
.content-hr{
width:70%;
margin-left:0
}

#info-div{
font-size:16px;
width:340px;
margin:20px auto;
}
#attention-div{
width:150px;
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

#attention-list-div{
width: 30%;
border:1px solid #000;
position:absolute;
top:0;
right:0;
display:none
}

#fans-list-div{
width: 30%;
border:1px solid #000;
position:absolute;
top:0;
left:0;
display:none
}

.name-uri{
position:relative;
}

.attention-uri{
max-height:30px;
max-width:30px;
border-radius:50%;
position:absolute;
top:50%;
transform:translateY(-50%);
}

.attention-name-span{
margin-left:30px
}

.name-uri:hover{
cursor:pointer;
border-top:1px #000 solid;
border-bottom:1px #000 solid;
}


#panel{
border: solid 1px black;
width:400px;
height:400px;
z-index: 1;
margin-top:-20px;
}

.user-info-input{
border:none
}

#post-user-submit{
display:none
}

#username-hint-span{
font-size:5px
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${user.name }的主页</title>
</head>
<body>
	<span id="currentUserId" hidden>${bbs.id }</span>
	<div id="avatar-div">
		<img id="avatar" src="/images/avatar/${uri }">
	</div>
	
	
	<div id="attention-div">
	关&emsp;注:<span id="attention-span">&nbsp;${attention }</span> <c:choose>
	<c:when test="${user.id != bbs.id && !isAttention}"><button id="attention-btn">点击关注</button></c:when>
	<c:when test= "${user.id != bbs.id }"><button id="remove-attention-btn">取消关注</button></c:when>
	</c:choose>
	<br>被关注:<span id="fans-span">&nbsp;${fans }</span>
	</div>
	
	
	<div id="info-div">
	<c:if test="${user.id==bbs.id }"><button id="edit-btn">编辑信息</button><br><a href="/bbs/user/get/password">修改密码</a></c:if>
	<br>
	<form action="/bbs/user/put/user" method="post">
	<input hidden name="id" value="${user.id }">
	用户名：${user.name }
	<br> 性&emsp;别：<input id="sex-input" name="sex" class = "user-info-input" type = "text" readonly value="${user.sex }">
	<br> 邮&emsp;箱：<input class = "user-info-input" name="email" type = "text" readonly value="${user.email }">
	<br> 电&emsp;话：<input class = "user-info-input" name="phone" type = "text" readonly value="${user.phone }">
	<br> 签&emsp;名：<input class = "user-info-input" name="sign" type = "text" readonly value="${user.sign }">
	<br><input id="post-user-submit" type="submit" value="提交"> 
	</form>
	<br>
	</div>
	<hr>
	<div id="contents-div"><br>该用户的回复:</div>
	<div id="titles-div"><br>该用户的话题:</div>
	<span id="more-content-span">查看更多回复</span>
	<span id="more-title-span">查看更多话题</span>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var currentUserId = $('#currentUserId').html()
		var userId = ${user.id}
		var contentPageNum=1;
		var titlePageNum=1;
		var pageSize=3;
		var uri='/bbs/user/get/';
		var lastContentId=0;
		var lastTitleId=0;
		var abc = 0;
		var oldUsername=$('#username-input').val()
		getContent(userId,contentPageNum,pageSize);
		getTitle(userId,titlePageNum,pageSize);

<%--获得评论--%>	
		function getContent(userId,contentPageNum,pageSize){
			tempContentUri=uri.concat("content/").concat(userId+"/").concat(contentPageNum+"/").concat(pageSize)
			$.post(tempContentUri,function(data){
				if(data!=''){
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
				}
				if($('.content-div').length>3){
					document.getElementById("more-content-span").scrollIntoView();
				}
				
			})
		}
		
		function sleep(t){
			while(Date.now()-t<=tempTime);
		}
<%--更多评论 --%>
		$('#more-content-span').click(function(){
			contentPageNum+=1;
			//pageSize+=1;
			getContent(userId,contentPageNum,pageSize);
		})
<%--更多话题 --%>
		$('#more-title-span').click(function(){
			titlePageNum+=1;
			//pageSize+=1;
			getTitle(userId,titlePageNum,pageSize);
		})
<%--跳转到某版块下的标题页面--%>	
		$('body').on('click','.section-span',function(){
			window.open('/bbs/user/get/titles/'+$(this).html())
		})
<%--跳转到某话题页面--%>		
		$('body').on('click','.title-span',function(){
			window.open('/bbs/user/get/content/'+$(this).html())
		}) 
		$('body').on('click','.title-div',function(){
			window.open('/bbs/user/get/content/'+$(this).html())
		}) 
<%--关注列表--%>
		$('#attention-span').click(function(){
			if($('#attention-list-div').length<=0){
				$('body').append("<div id='attention-list-div'>关注列表:</div>")	
				$.post('/bbs/user/get/attention/'+userId,function(data){
					for(var name in data){
						$('#attention-list-div').append("<div class='name-uri'>"+"<img class='attention-uri' src='/images/avatar/"+data[name]+"'>"+"<span class='attention-name-span'>"+":"+name+"</span>"+"</div>")
					}
				})
				$('#attention-list-div').slideDown(200)
			}else{
				$('#attention-list-div').slideUp(200)
				setTimeout("$('#attention-list-div').remove()",200)
				
			}
			
		})
		
		<%--被关注列表--%>
		$('#fans-span').click(function(){
			if($('#fans-list-div').length<=0){
				$('body').append("<div id='fans-list-div'>被关注列表:</div>")
				$.post('/bbs/user/get/fans/'+userId,function(data){
					for(var name in data){
						$('#fans-list-div').append("<div class='name-uri'>"+"<img class='attention-uri' src='/images/avatar/"+data[name]+"'>"+"<span class='attention-name-span'>"+":"+name+"</span>"+"</div>")
					}
				})
				$('#fans-list-div').slideDown(200)
			}else{
				$('#fans-list-div').slideUp(200)
				setTimeout("$('#fans-list-div').remove()",200)
			}
			
		})
		
		//点击头像跳转
						$('body').on('click','.name-uri',function(){
							var name=$(this).children().last().html().substring(1);
							window.open("/bbs/user/get/users/"+name)
						})

		
						
	/*获得话题列表  */					
	
		function getTitle(userId,titlePageNum,pageSize){
			tempTitleUri=uri.concat("titles/").concat(userId+"/").concat(titlePageNum+"/")
			$.post(tempTitleUri,function(data){
				if(data!=''){
					if(lastTitleId==data[data.length-1].id){
						$('#more-title-span').html('没有更多了')
						return false;
					}else{
						lastTitleId = data[data.length-1].id
					}
					
					for(var i=0;i<data.length;i++){
						tempTime = Date.now()
						//sleep(100);
						$('#titles-div').append("<div class='title-div' title='点击跳转'>"+data[i].name+"</div>")
						$('#titles-div').append("<div class='title-bottom'>于"+data[i].createDate+"在<span class='section-span' title='点击跳转'>"+data[i].sectionName+"</span>版块中提出")					
						$('#titles-div').append("<hr class='title-hr'>")
					}
					$('#titles-div').append("<p>. . .</p>")
				}
				if($('.title-div').length>3){
					document.getElementById("more-title-span").scrollIntoView();
				}
				
			})
		}
	
	
	//alter avatar
		
		$('#avatar').mouseover(function(){
			if(currentUserId == userId)
			$(this).prop('title','点击更改头象')
		})
		
		$('#avatar').click(function(){
			if(currentUserId == userId){
			window.open("/bbs/user/get/avatar")
			}
		})
	
		
	//attention
	
		$('#attention-btn').click(function(){
			$.post('/bbs/user/post/friend/'+userId,function(data){
				if(data=="SUC")
				window.location.reload()
			})
		})
	
	//remove attention
	
		$('#remove-attention-btn').click(function(){
			$.post('/bbs/user/delete/friend/'+userId,function(data){
				if(data=="SUC")
				window.location.reload()
			})
		})
	
		//edit 
		$('#edit-btn').click(function(){
			$('.user-info-input').prop('readonly',false)
			$('.user-info-input').css('border','1px black solid')
			$('#post-user-submit').show()
			var oldSex = $('#sex-input').val()
			$('#sex-input').prop('type','radio')
			$('#sex-input').val('男')
			$('#sex-input').before("男")
			$('#sex-input').after("<input name='sex' type='radio' value='女'>")
			$('#sex-input').after("女")
			$("input[type='radio'][value='"+oldSex+"']").prop("checked", "checked")
			$(this).prop('disabled',true)
		})
	
	
		
	
	
	
	
	
	
		
	//----------------------
	})
	
	</script>
</body>
</html>