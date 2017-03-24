<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>密码修改</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">密码修改</h3>
		</div>
		<div class="panel-body">
			<form id="form1">
				<div class="row">
					<div class="col-md-1 col-md-offset-4 text-right">
						<label for="vpass">原密码</label>
					</div>
					<div class="col-md-2">
						<input class="form-control" id="vpass" name="vpass"
							type="password" onblur="checkpass()" />
					</div>
				</div>
				<div class="row" style="margin-top:5px;">
					<div class="col-md-1 col-md-offset-4 text-right">
						<label>新密码</label>
					</div>
					<div class="col-md-2">
						<input class="form-control" id="npass" name="npass" type="password" />
					</div>
				</div>
				<div class="row" style="margin-top:5px;">
					<div class="col-md-1 col-md-offset-4 text-right">
						<label>确认密码</label>
					</div>
					<div class="col-md-2">
						<input class="form-control" id="rnpass" name="rnpass" type="password" />
					</div>
				</div>
				<div class="row" style="margin-top:5px;">
					<div class="col-md-1 col-md-offset-5">
						<button type="button" class="btn btn-success btn-sm" onclick="sub()">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
var state = false;
function checkpass(){
	var pass = $("#vpass").val();
	$.ajax({
		type: 'post',
		url: 'checkPassword.action?vpass='+pass,
		success:function(result){
			if(result==1){
				state = true;
			}else{
				state = false;
				alert("原始密码错误！");
			}
		},
		error:function(){
			alert("程序出错！");
		}
	});
}
function sub(){
	var vpass = $("#vpass").val();
	var npass = $("#npass").val();
	var rnpass = $("#rnpass").val();
	if(vpass==""||npass==""||rnpass==""){
		alert("不能为空！");
		return false;
	}
	if(npass!=rnpass){
		alert("两次密码不一致！");
		return false;
	}
	if(state=false){
		return false;
	}
	$.ajax({
		type: 'post',
		async: false,
		url: 'updateUser.action?npass='+npass+'&vpass='+vpass,
		success:function(result){
			alert(result);
		},
		error:function(){
			alert("程序出错！");
		}
	});
	document.getElementById("form1").reset();
}
</script>
</body>
</html>
