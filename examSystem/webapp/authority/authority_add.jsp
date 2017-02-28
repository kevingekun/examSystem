<%@ page contentType = "text/html;charset=utf-8" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1"><!-- 为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport 元数据标签。 -->
	<title>增加角色</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<%-- <link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/> --%>
</head>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
function checkform(form){
	if(form.name.value==""){
		alert("请输入角色名称!"); return false;
	}
	if(form.description.value==""){
		alert("请输入角色说明!"); return false;
	}
	return true;
}
function cancel(){
	history.go(-1);
}
</script>
<body>
<div class="container-fluid">
<div class="panel panel-primary" style="width:99%; margin: 2px;">
	<div class="panel-heading">
		<h3 class="panel-title">新增角色</h3>
	</div>
	<form class="form-horizontal" action="addNewEauthority.action" name="aForm" method="post" onsubmit="return checkform(this);">
		<div class="row" style="margin: 15px;">
			<div class="form-group">
				<label class="col-md-2 control-label">角色名称</label>
				<div class="col-md-3">
				    <input type="text" class="form-control" name="name">
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-md-2 control-label">说明</label>
				<div class="col-md-3">
				    <input type="text" class="form-control" name="description">
				</div>
	  		</div>
	  		<div class="form-group">
	  			<div class="col-md-offset-4 col-md-1">
	  				<button type="submit" class="btn btn-success">
	  					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 确定
	  				</button>
	  			</div>
	  			<div class="col-md-offset-1 col-md-1">
	  				<button type="submit" class="btn btn-warning" onclick="cancel()">
	  					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 返回
	  				</button>
	  			</div>
	  		</div>
  		</div>
	</form>
</div>
</div>
</body>
</html>
