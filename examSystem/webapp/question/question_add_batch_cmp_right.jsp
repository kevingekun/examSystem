<%@ page import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();
String sjid = request.getParameter("sjid");
String sjmc = "";
if(sjid!=null&&!"".equals(sjid)){
	EPapersSet e = professionBean.getPapersBySjid(sjid);
	sjmc = e.getSj_mc();
}
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>批量导入试题</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function fileChange_cmp(){
		var sjid = $("#sjid").val();
		if(sjid=="null"){
			alert("请选择试卷");
			$("#file").val("");
		}else{
			document.fileupload.submit();
		}
	}
	function batchSubmit(){
		$("#bcsmt").attr("disabled","disabled");
		var listCount = $("#list1_id").val();
		if(listCount=='1'){
			var batchNumber = $("#batchNumber").val();
			$.ajax({
				type: 'post',
				url: 'batchAddAction_batchSubmit.action?batchNumber='+batchNumber+'&flag=batchSet',
				success:function(result){
					alert(result);
					window.location.href="question/question_add_batch_cmp_right.jsp";
					window.parent['LeftTree2'].location.reload();
				},
				error:function(){
					alert("导入失败！");
				}
			});
		}else{
			alert("没有可导入的试题！");
		}
	}
	function templateDownload(){
		window.location.href="templateDownload.action?flag=cmp";
		<%-- window.location.href="<%=request.getContextPath() %>/templates/questionTemplate_cmp.xlsx"; --%>
	}
	function checkPaper(){
		var sjid = $("#sjid").val();
		if(sjid=='null'){
			$("#file").attr("onchange","");
			alert("请先选择试卷！");
		}
	}
	</script>
</head>

<body>
<div class="panel panel-info">
	<div class="panel-heading">
		<%
		if(!"".equals(sjid)&&sjid!=null){
			out.print("<span class=\"label label-success\">"+sjmc+"_机考</span>");
		}else{
			out.print("<span class=\"label label-warning\">请选择试卷</span>");
		}
		%>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-3 col-md-offset-4" >
				<form name="fileupload" action="batchAddAction_displayExcel_cmp.action" method="post" enctype="multipart/form-data">
					<input id="sjid" name="sjid" type="hidden" value="<%=sjid%>"/>
					<input class="form-control input-sm" type="file" id="file" name="file_excel" onchange="fileChange_cmp()" onclick="checkPaper()" style="width:280px" />
				</form>
			</div>
		</div>
	</div>
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active">
			<a href="#success" aria-controls="success" role="tab" data-toggle="tab">正确试题<span class="badge" style="background-color:green"><c:out value="${list_1_size}"></c:out></span></a>
		</li>
		<li role="presentation">
			<a href="#failed" aria-controls="failed" role="tab" data-toggle="tab">错误试题<span class="badge" style="background-color:red"><c:out value="${list_2_size}"></c:out></span></a>
		</li>
		<li style="float:right">
			<input id="batchNumber" value="<c:out value="${batchNumber}"/>" type="hidden"> 
			<input id="sjid" name="sjid" value="<c:out value="${sjid}"/>" type="hidden"> 
			<button class="btn btn-primary btn-sm" id="bcsmt" type="button" onclick="batchSubmit()" >
				<span class="glyphicon glyphicon-upload" aria-hidden="true"></span>  提交
			</button>
		</li>
		<li style="float:right;margin-right:8px">
			<button class="btn btn-success btn-sm" type="button" onclick="templateDownload()">
				<span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>  试题模板下载
			</button>
		</li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active panel-body" id="success">
			<table class="table table-striped table-bordered table-hover table-condensed text-center">
					<tr class="info">
						<td width="5%"><span class="out">序号</span></td>
						<td width="30%"><span class="out">题目</span></td>
						<td width="10%"><span class="out">试题类型</span></td>
						<td width="20%"><span class="out">选项</span></td>
						<td width="10%"><span class="out">答案</span></td>
						<td width="10%"><span class="out">试题出处</span></td>
						 <td width="7%"><span class="out">专家</span></td>
						 <td width="7%"><span class="out">分值</span></td>
					<c:forEach var="aBean" items="${list1}" varStatus="status">
						<tr>
							<td><c:out value="${status.index+1}" /> <input type="hidden" id="list1_id" value="<c:if test="${list1!= null}">1</c:if><c:if test="${list1== null}">0</c:if>"></td>
							<td class='num_font alignleft'>
								<a style="display:block;width:250;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stTg}"/>"><c:out value="${aBean.stTg}"/></a>
							</td>
							<td><c:out value="${aBean.equestiontype.name}" /></td>
							<td>
								<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stXx}"/>"><c:out value="${aBean.stXx}"/></a>
							</td>
							<td><c:out value="${aBean.stDa}" /></td>
							<td>
								<a style="display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stCc}"/>">
									<c:out value="${aBean.stCc}" />
								</a>
							</td>
							<td><c:out value="${aBean.stSyryId}"/></td>
							<td><c:out value="${aBean.stFz}"/></td>
						</tr>
					</c:forEach>
				</table>
		</div>
		<div role="tabpanel" class="tab-pane panel-body" id="failed">
			<table class="table table-striped table-bordered table-hover table-condensed text-center">
				<tr class="info">
					<td width="5%"><span class="out">序号</span></td>
					<td width="30%"><span class="out">题目</span></td>
					<td width="10%"><span class="out">试题类型</span></td>
					<td width="20%"><span class="out">选项</span></td>
					<td width="10%"><span class="out">答案</span></td>
					<td width="10%"><span class="out">试题出处</span></td>
					<td width="7%"><span class="out">专家</span></td>
					<td width="7%"><span class="out">分值</span></td>
				</tr>
				<c:forEach var="aBean" items="${list2}" varStatus="status">
					<tr>
						<td><c:out value="${status.index+1}" /></td>
						<td class='num_font alignleft'>
							<a style="display:block;width:250;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stTg}"/>"><c:out value="${aBean.stTg}"/></a>
						</td>
						<td><c:out value="${aBean.equestiontype.name}" /></td>
						<td>
							<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stXx}"/>"><c:out value="${aBean.stXx}"/></a>
						</td>
						<td><c:out value="${aBean.stDa}" /></td>
						<td>
							<a style="display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stCc}"/>">
								<c:out value="${aBean.stCc}" />
							</a>
						</td>
						<td><c:out value="${aBean.stSyryId}"/></td>
						<td><c:out value="${aBean.stFz}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>
