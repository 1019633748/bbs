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