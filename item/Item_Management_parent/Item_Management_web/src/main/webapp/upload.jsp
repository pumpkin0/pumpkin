<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic FileBox - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="js/test/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/test/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/test/demo.css">
	<script type="text/javascript" src="js/test/jquery.min.js"></script>
	<script type="text/javascript" src="js/test/jquery.easyui.min.js"></script>
</head>
<body>

	<h1>文件上传测试</h1>
	<form id="ff" action="${pageContext.request.contextPath}/shangchuan.action" method="post" enctype="multipart/form-data">   
	    <div>   
	        <label for="name">Name:</label>   
	        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="email">Email:</label>   
	        <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" />   
	    </div> 
	    <div>
	    	<label for="fileUpload">文件上传:</label>
			<input  class="easyui-filebox" name="fileResource" >
		</div> 
		<div>   
	        <input type="submit" value="提交"/>   
	    </div>  
	</form> 











	<!-- <h2>Basic FileBox</h2>
	<p>The filebox component represents a file field of the forms.</p>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="Upload File" style="width:100%;max-width:400px;padding:30px 60px;">
		<div style="margin-bottom:20px">
			<input class="easyui-textbox" label="Name:" labelPosition="top" style="width:100%">
		</div>
		<div style="margin-bottom:20px">
			<input class="easyui-filebox" label="File1:" labelPosition="top" data-options="prompt:'Choose a file...'" style="width:100%">
		</div>
		<div style="margin-bottom:40px">
			<input class="easyui-filebox" label="File2:" labelPosition="top" data-options="prompt:'Choose another file...'" style="width:100%">
		</div>
		<div>
			<a href="#" class="easyui-linkbutton" style="width:100%">Upload</a>
		</div>
	</div> -->
</body>
</html>