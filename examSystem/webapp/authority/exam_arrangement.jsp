<%@page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
 ProfessionBean professionBean = new ProfessionBean();
 List<EPapers> p_list = professionBean.getExamingPapers();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>考务信息查询导出</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">考试安排</h3>
		</div>
		<div class="panel-body">
			<form method="post" action="examArrangement.action" name="addstaffForm" class="form-inline">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="sjmc">试卷名称：</label>
							<select class="form-control" id="paper" name="paper" onchange="checkPaper()">
								<option value="">请选择</option>
								<%
									for(int i=0;i<p_list.size();i++){
										EPapers paper = p_list.get(i);
										out.print("<option value="+paper.getSjId()+">"+paper.getSjMc()+"</option>");
									}
								%>
							</select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="sjmc">试卷名称：</label>
							<input type="text" class="form-control" name="examId" id="examId" value="<s:property value='examId'/>"/>
						</div>
					</div>
					<div class="col-md-1">
						<button type="submit" class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询
						</button>
					</div>
					<div class="col-md-1 col-md-offset-1">
						<div class="form-group">
							<button type="button" class="btn btn-success btn-sm" onclick="exportSinfo()">
								<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> 导出考场信息
							</button>
						</div>
					</div>
				</div>
			</form>
			<form action="" name="exportInfo"></form>
		</div>
			<div class="panel panel-success">
				<div class="panel-heading"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>  考生信息</div>
				<table
					class="table table-striped table-bordered table-hover table-condensed"
					style="text-align: center; width: 100%">
					<tr class="info">
						<td width="5%"><span class="out">准考证号 </span></td>
						<td width="12%"><span class="out">考生姓名</span></td>
						<td width="9%"><span class="out">身份证号</span></td>
						<td width="9%"><span class="out">考卷</span></td>
						<td width="9%"><span class="out">考场</span></td>
						<td width="9%"><span class="out">考场地址</span></td>
					</tr>
					<s:iterator value="staff" id="user" status="state">
						<tr>
							<td><s:property value="#user.zkh" /></td>
							<td><s:property value="#user.examineename" /></td>
							<td><s:property value="#user.IDnumber" /></td>
							<td><s:property value="#user.examid" /></td>
							<td><s:property value="#user.examroom" /></td>
							<td><s:property value="#user.examroomadress" /></td>
						</tr>
					</s:iterator>
				</table>
				<c:if test="${staff!=null }">
					<s:form action="examArrangement.action" name="chaForm">
						<s:hidden name="examId"></s:hidden>
						<elile:navigateBar navigateform="navigateform"
							actionName="examArrangement.action" formName="chaForm" />
					</s:form>
				</c:if>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>  教师机信息</div>
				<table
					class="table table-striped table-bordered table-hover table-condensed"
					style="text-align: center;">
					<tr class="info">
						<td width="5%"><span class="out">考点名称 </span></td>
						<td width="12%"><span class="out">考场名称</span></td>
						<td width="9%"><span class="out">教师机用户名</span></td>
						<td width="9%"><span class="out">教师机密码</span></td>
					</tr>
					<s:iterator value="eArrangement" id="info" status="state">
						<tr>
							<td><s:property value="#info.kdid" /></td>
							<td><s:property value="#info.kcid" /></td>
							<td><s:property value="#info.username" /></td>
							<td><s:property value="#info.password" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
</div>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function exportSinfo(){
	var id = $("#examId").val();
	if(id!=""){
		document.exportInfo.action="sinfoExport.action";
		document.exportInfo.submit();
	}else{
		alert("请查询出考场信息！")
	}
}
function checkPaper(){
	var sjmc = $("#paper").find("option:selected").text();
	$("#examId").val(sjmc);
	document.addstaffForm.submit();
}
</script>
</body>
</html>
