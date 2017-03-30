<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
.panel{
	margin-bottom:0px;
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
			<h3 class="panel-title">试题录入<span class="label label-success">整套</span></h3>
		</div>
	</div>
	<div>
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">试卷信息录入</a></li>
	    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">机考试题录入</a></li>
	    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">笔答试题录入</a></li>
	  </ul>
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="home">
	    	<div class="row" style="margin-right:0px">
				<div class="col-md-3">
					<iframe name="LeftTree1" src="question/papers_set_add_left.jsp" scrolling="auto" width="100%" height="600px"></iframe>
				</div>
				<div class="col-md-9">
					<iframe name="MainWin1" src="question/papers_set_add_right.jsp" scrolling="auto" width="100%" height="600px"></iframe>
				</div>
			</div>
	    </div>
	    <div role="tabpanel" class="tab-pane" id="profile">
	    	<div class="row" style="margin-right:0px">
				<div class="col-md-3">
					<iframe name="LeftTree2" src="question/questions_set_add_left.jsp" scrolling="auto" width="100%" height="600px"></iframe>
				</div>
				<div class="col-md-9">
					<iframe name="MainWin2" src="question/questions_set_add_right.jsp" scrolling="auto" width="100%" height="600px"></iframe>
				</div>
			</div>
	    </div>
	    <div role="tabpanel" class="tab-pane" id="messages">
	    	<div class="row" style="margin-right:0px">
				<div class="col-md-3">
					<iframe name="LeftTree3" src="question/questions_set_add_written_left.jsp" scrolling="auto" width="100%" height="600px"></iframe>
				</div>
				<div class="col-md-9">
					<iframe name="MainWin3" src="question/questions_set_add_written_right.jsp" scrolling="auto" width="100%" height="600px"></iframe>
				</div>
			</div>
	    </div>
	  </div>
	</div>
</div>
</body>
</html>
