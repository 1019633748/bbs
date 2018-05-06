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
<title>注册</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<style type="text/css">
form {
	margin: 10% auto;
	width: 400px
}
</style>
</head>
<body>
	<form action="/bbs/register" method="post">
		<h3 align="center">新用户注册</h3>
		<br>
		<div class="form-group row">
			<label for="name-input" class="col-sm-2 col-form-label" style="margin-top:7px"
				name="nickname">昵称</label>
			<div class="col-sm-10">
				<input required type="text" class="form-control" id="name-input" name="nickname" minlength="1" maxlength="20" onkeyup="this.value=this.value.replace(/\s+/g,'')""
 					placeholder="20个字符以内，不能包括特殊字符" >
			</div>
		</div>
		<div class="form-group row">
			<label for="email-input" class="col-sm-2 col-form-label" style="margin-top:7px">邮箱</label>
			<div class="col-sm-10">
				<input required type="email" class="form-control" id="email-input" maxlength="20"
					name="email" placeholder="邮箱">
			</div>
		</div>
		<div class="form-group row">
			<label for="password-input" class="col-sm-2 col-form-label" style="margin-top:7px">密码</label> 
			<div class="col-sm-10">
				<input required type="password" class="form-control" id="password-input" name="password" maxlength="20" minlength="6"
					placeholder="6到20个字符，不能包括特殊字符"">
			</div>
		</div>
		<div class="form-group row">
			<label for="password-input2" class="col-sm-2 col-form-label" style="margin-top:7px">确认</label>
			<div class="col-sm-10">
				<input required type="password" class="form-control" id="password-input2" maxlength="20" minlength="6"
					placeholder="确认密码">
			</div>
		</div>
		<fieldset class="form-group">
			<div class="row">
			<label for="" class="col-sm-2 col-form-label">性别</label>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="sex"
							id="gridRadios1" value="1" checked> <label
							class="form-check-label" for="gridRadios1"> 男 </label>&emsp;&emsp;
						<input class="form-check-input" type="radio" name="sex"
							id="gridRadios2" value="0"> <label
							class="form-check-label" for="gridRadios2"> 女 </label>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="form-group row">
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary" onclick="verify()">注册</button>
			</div>
		</div>
	</form>


<script type="text/javascript">

function verify(){
	
	if(!illegalChar($('#name-input').val())){
		alert('请不要在昵称输入特殊字符')
		return false
	}else if(!illegalChar($('#password-input').val())){
		alert('请不要在密码输入特殊字符')
		return false
	}else if(!illegalChar($('#password-input2').val())){
		alert('请不要在密码输入特殊字符')
		return false
	}
	
}


function illegalChar(str)
{
    var pattern=/[`~!@#\$%\^\&\*\(\)_\+<>\?:"\{\},\.\\\/;'\[\]]/im;  
    if(pattern.test(str)){  
        return false;     
    }     
    return true;  
}

$('#name-input').on('blur',function(){
	$.post('/bbs/verify/name','name='+$(this).val(),function(data){
		if(data==0){
		
		}else{
			var name = $('#name-input').val()
			$('#name-input').val('')
			$('#name-input').prop('placeholder',name+'已存在')
			bl = false
		}
	})
})

$('#email-input').on('blur',function(){
	$.post('/bbs/verify/email','email='+$(this).val(),function(data){
		if(data==0){
			
		}else{
			var email = $('#email-input').val()
			$('#email-input').val('')
			$('#email-input').prop('placeholder',email+'已存在')
			bl = false
		}
	})
})

$('#password-input2').on('blur',function(){
	if($('#password-input').val()!=$('#password-input2').val()){
		alert('前后密码不一致')
	}else{
		bl= false;
	}
})


</script>
</body>
</html>