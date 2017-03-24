 <%@ page contentType = "text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List,com.wondersgroup.popedom.bo.ExamStaff"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
	<title>用户列表</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">导入考生<span class="label label-success">模板</span></h3>
		</div>
		<div class="panel-body">
			<form name="fileupload" action="batchAddAction_displayExcel_manual.action" method="post" enctype="multipart/form-data" class="form-inline">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="jdmc">鉴定批次名称：</label>
		    				<input type="text" class="form-control" name="jdmc" id="jdmc" >
	    				</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<input type="file" id="file" name="file_excel" onclick="checkPaper()"/>
	    				</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<button class="btn btn-sm btn-primary" type="button" onclick="submit()">
								<span class="glyphicon glyphicon-import" aria-hidden="true"></span> 导入
							</button>
	    				</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<button class="btn btn-sm btn-success" type="button" onclick="templateDownload()">
								<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> 手工导入考生模板下载
							</button>
	    				</div>
					</div>
				</div>
			</form>
		</div>
		<div>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active">
					<a href="#home" aria-controls="home" role="tab" data-toggle="tab">导入正确考生<span class="badge" style="background-color:green"><s:property value="list_1_size"/></span></a>
				</li>
				<li role="presentation">
					<a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">导入错误考生<span class="badge" style="background-color:red"><s:property value="list_2_size"/></span></a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">
					<table class="table table-striped table-bordered table-hover table-condensed">
						<tr class="info">
							<td width="3%" align="center"><span class="out">序号</span></td>
							<td width="10%" align="center"><span class="out">准考证号 </span></td>
							<td width="8%" align="center"><span class="out">考生姓名</span></td>
							<td width="10%" align="center"><span class="out">身份证号</span></td>
						</tr>
						<s:iterator value="list_users" id="user" status="state">
							<tr id='r1'>
								<td align='center'><s:property value="#state.index+1" /></td>
								<td align='center'><s:property value="#user.password" /></td>
								<td align='center'><s:property value="#user.realname" /></td>
								<td align='center'><s:property value="#user.username" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div role="tabpanel" class="tab-pane" id="profile">
					<table class="table table-striped table-bordered table-hover table-condensed">
						<tr class="info">
							<td width="3%" align="center"><span class="out">序号</span></td>
							<td width="10%" align="center"><span class="out">准考证号 </span></td>
							<td width="8%" align="center"><span class="out">考生姓名</span></td>
							<td width="10%" align="center"><span class="out">身份证号</span></td>
						</tr>
						<s:iterator value="list_error" id="user" status="state">
							<tr id='r1'>
								<td align='center'><s:property value="#state.index+1" /></td>
								<td align='center'><s:property value="#user.password" /></td>
								<td align='center'><s:property value="#user.realname" /></td>
								<td align='center'><s:property value="#user.username" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script>
	function submit() {
		if (document.all.jdmc.value == "") {
			alert("请填写鉴定批次名称");
			$("#file").val("");
			return;
		}
		if (document.all.file.value == "") {
			alert("请择导入文件");
			return;
		} else {
			document.fileupload.submit();
		}
	}

	function checkPaper() {
		if (document.all.jdmc.value == "") {
			alert("请填写鉴定批次名称");
			$("#file").val("");
		}
	}
	function showT() {
		$("#tb2").css("display", "none");
		$("#tb1").css("display", "block");
	}
	function showF() {
		$("#tb1").css("display", "none");
		$("#tb2").css("display", "block");
	}
	function templateDownload() {
		window.location.href = "templateDownload.action?flag=manual";
	}
</script>
</body>
</html>

