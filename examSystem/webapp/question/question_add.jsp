<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
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
	<title>组卷</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<style type="text/css">
.panel-body{
	padding:0px;
}
.col-md-3{
	padding-right:0px;
}
.col-md-9{
	padding-left:0px;
	padding-right:0px;
}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">试题录入<span class="label label-success">单个</span></h3>
		</div>
		<div class="panel-body">
			<div class="row" style="margin-right:0px">
				<div class="col-md-3">
					<iframe name="LeftTree" src="question/question_add_left.jsp" scrolling="auto" width="100%" height="700px"></iframe>
				</div>
				<div class="col-md-9">
					<iframe name="MainWin" src="question/question_add_right.jsp" scrolling="auto" width="100%" height="700px"></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
