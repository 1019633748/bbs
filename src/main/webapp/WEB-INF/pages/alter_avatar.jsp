<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>头像上传</title>
  <link href="/bbs/css/cropper-demo.css" rel="stylesheet" />
  <link href="/bbs/css/cropper.css" rel="stylesheet"/>
  <script type="text/javascript" src="/bbs/js/cropper.js"></script>
</head>
<body>
<form method="post" action="/bbs/user/post/avatar" enctype="multipart/form-data">
  <p>
    <button class="btn-upload btn-lg">Select</button>
    <input type="file" name="avatar" id="cropper-input"/>
    Support formats: JPG, PNG
  </p>
  <div class="preview-container">
    <div class="image-container target" id="cropper-target">
      <img id="source-img" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" class="noavatar" />
    </div>
    <div class="large-wrapper">
      <div class="image-container large" id="preview-large">
        <img src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" class="noavatar" />
      </div>
      <p>Large</p>
    </div>
    <div>
      <div class="image-container medium" id="preview-medium">
        <img src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" class="noavatar" />
      </div>
      <p>Medium</p>
      <div class="image-container small" id="preview-small">
        <img src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" class="noavatar" />
      </div>
      <p>Small</p>
    </div>
    <canvas hidden id="destination-canvas">
  </div>

  <input type="hidden" name="x" value="{{cropContext.left}}"/>
  <input type="hidden" name="y" value="{{cropContext.top}}"/>
  <input type="hidden" name="w" value="{{cropContext.width}}"/>
  <input type="hidden" name="h" value="{{cropContext.height}}"/>
  <input type="button" value="上传" onclick="upload()">
</form>
<script>
  var cropper = new Cropper({
    element: document.getElementById('cropper-target'),
    previews: [
      document.getElementById('preview-large'),
      document.getElementById('preview-medium'),
      document.getElementById('preview-small')
    ],
    onCroppedRectChange: function(rect) {
     sourceConfig=rect;
    }
  });
  var input = document.getElementById('cropper-input');
  input.onchange = function() {
    if (typeof FileReader !== 'undefined') {
      var reader = new FileReader();
      reader.onload = function (event) {
        cropper.setImage(event.target.result);
      };
      if (input.files && input.files[0]) {
        reader.readAsDataURL(input.files[0]);
      }
    } else { // IE10-
      input.select();
      input.blur();

      var src = document.selection.createRange().text;
      cropper.setImage(src);
    }
  };
  
  function upload(){
	  var canvas = document.getElementById('destination-canvas');
	  var image = document.getElementById('source-img');
	  var ctx = canvas.getContext('2d');
	  var size = sourceConfig.width; 
	  var sx = sourceConfig.left;
	  var sy = sourceConfig.top;
	  canvas.height=canvas.width=200;
	  ctx.drawImage(image,sx,sy,size,size,0,0,200,200)
	  var src = canvas.toDataURL();//canvas转base64
	  var filename = document.getElementById('cropper-input').files[0].name//获取文件名
	  var index1 = filename.lastIndexOf('.');
	  var index2 = filename.length;
	  filename = filename.substring(index1,index2);//获取后缀名
	  var uploadFile = convertBase64UrlToBlob(src);//base64转blob
	  var formData = new FormData();
	  formData.append("avatar", uploadFile,filename);//后台的file名，需要上传的文件，后缀名
	  var request = new XMLHttpRequest();
	  request.open("POST", "/bbs/user/post/avatar");
	  request.send(formData);
	  request.onreadystatechange = function(){
		    //若响应完成且请求成功
		    if(request.readyState === 4 && request.status === 200){
		        alert("上传成功")
		    }
	  }
  }
  
  function convertBase64UrlToBlob(urlData){
	     
	    var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte
	     
	    //处理异常,将ascii码小于0的转换为大于0
	    var ab = new ArrayBuffer(bytes.length);
	    var ia = new Uint8Array(ab);
	    for (var i = 0; i < bytes.length; i++) {
	        ia[i] = bytes.charCodeAt(i);
	    }
	 
	    return new Blob( [ab] , {type : 'image/png'});
	}
</script>
</body>
</html>