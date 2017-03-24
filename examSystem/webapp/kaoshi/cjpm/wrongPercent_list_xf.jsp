<%@page language="java" contentType="text/html;charset=utf-8"%>
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
	<title>试题错误率</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">错误率  <span class="label label-info"><s:property value="sjMc"/></span></h3>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" id="tb">
			<tr class="info">
				<td align="center"><span class="out">试题题目</span></td>
				<td align="center"><span class="out">试题类型</span></td>
				<td align="center"><span class="out">试题错误率</span></td>
			</tr>
			<s:iterator value="wpList" id="wpl" status="index">
				<tr id='r1'>
					<td align='center'>
						<a style="display:block;width:840px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="#wpl.st_tg" />">
							<s:property value="#wpl.st_tg" />
						</a>
					</td>
					<td align='center'>
						<%-- <s:property value="#wpl.st_lx" /> --%>
						<s:if test="#wpl.st_lx==2">单选题</s:if>
						<s:if test="#wpl.st_lx==3">判断题</s:if>
						<s:if test="#wpl.st_lx==8">多选题</s:if>
					</td>
					<td align='center'><s:property value="#wpl.wrong_persent"/>%</td>
				</tr>
			</s:iterator>
		</table>
		<div class="row" style="margin:5px;">
			<div class="col-md-1 col-md-offset-5">
				<button class="btn btn-success btn-sm" onclick="excel(<s:property value="paperId"/>);">导出excel</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//导出excel
function excel(paperid){
	window.location.href="wrongPercentExcelAction_xf.action?paperid="+paperid;
}
</script>
</body>
</html>

