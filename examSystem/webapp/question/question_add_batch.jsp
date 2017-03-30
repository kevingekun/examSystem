<%@ page import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();//工种
List<Tjobsubject> professions = professionBean.getDistinctProfessions();
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
<style type="text/css">
.panel {
	margin-bottom: 0px;
}
.row{
	margin-left:0px;
	margin-right:0px;
}
.col-md-3 {
	padding-right: 0px;
	padding-left: 0px;
}

.col-md-9 {
	padding-left: 0px;
	padding-right:0px;
}
</style>
<script type="text/javascript">
function gzChange(v){
	var value = v.value;
	$("#jobId").val(value);
	var dj = document.getElementById("gzdj");
	dj.length = 1;
	$.ajax({
		type:'post',
		async : false,
		url:'findDjByGzid.action?gzid='+value,
		success:function(result){
			var data = eval(result);
			$.each(data, function(i, n) {
				document.getElementById("gzdj").options
				.add(new Option(data[i][1],
						data[i][1]));
			});
		},
		error:function(){
			alert("error");
		}
	});
}
function djChange(v){
	var dj = v.value;
	$("#dj").val(dj);
}
function fileChange(){
	var dj = $("#gzdj").val();
	if(dj==""){
		alert("请选择工种等级");
		$("#file").val("");
	}else{
		document.fileupload.submit();
	}
}
function batchSubmit(){
	var listCount = $("#list1_id").val();
	if(listCount=='1'){
		$("#bcsmt").attr("disabled","disabled");
		var batchNumber = $("#batchNumber").val();
		$.ajax({
			type: 'post',
			url: 'batchAddAction_batchSubmit.action?batchNumber='+batchNumber+'&flag=batch',
			success:function(result){
				alert(result);
				window.location.href="question/question_add_batch.jsp";
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
	window.location.href="templateDownload.action";
}
function info_jdys_Download(){
	var gzid = $("#jobId").val();
	var gzmc = $("#gzid").find("option:selected").text();
	if(gzid==""){
		alert("请选择工种！");
	}else{
		var url="info_jdys_Download.action?gzid="+gzid+"&gzmc="+gzmc;
		url = encodeURI(encodeURI(url));
		window.location.href=url;
	}
}
</script>
</head>
<body>
<div class="container-fluid">
	<div>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">批量录入</a></li>
			<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">机考试题整套录入</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active panel" id="home">
				<div class="row form-inline panel-body">
					<div class="col-md-3 form-group">
						<label for="gzid">工种</label>
		    			<select class="form-control input-sm" id="gzid" name="gzid" onchange="gzChange(this)">
		    				<option value="">全部</option>
								<%
									for (int i = 0; i < professions.size(); i++) {
										Tjobsubject tj = professions.get(i);
										out.println("<option value="+tj.getId_job()+">"+tj.getJobname()+"</option>");
									}
								%>
						</select>
					</div>
					<div class="col-md-2 form-group">
						<label for="gzdj">等级</label>
						<select class="form-control input-sm" id="gzdj" name="gzdj" onchange="djChange(this)">
							<option value="">全部</option>
						</select>
					</div>
					<div class="col-md-3 form-group">
						<form name="fileupload" action="batchAddAction_displayExcel.action" method="post" enctype="multipart/form-data">
							<input id="jobId" name="id_job" type="hidden" /> 
							<input id="dj" name="rankname" type="hidden" /> 
							<input class="form-control input-sm" type="file" id="file" name="file_excel" onchange="fileChange()" style="width: 280px" />
						</form>
					</div>
					<div class="col-md-3 col-md-offset-1 form-group">
						<button class="btn btn-success btn-sm" type="button" onclick="info_jdys_Download()">
							<span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>  鉴定要素信息下载
						</button>
					</div>
				</div>
				<div class="row"><div class="col-md-12">&nbsp;</div></div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active">
						<a href="#success" aria-controls="success" role="tab" data-toggle="tab">正确试题<span class="badge" style="background-color:green"><c:out value="${list_1_size}"></c:out></span></a>
					</li>
					<li role="presentation">
						<a href="#failed" aria-controls="failed" role="tab" data-toggle="tab">错误试题<span class="badge" style="background-color:red"><c:out value="${list_2_size}"></c:out></span></a>
					</li>
					<li style="float:right;margin-right:15px">
						<input type=hidden name="actionType" value="query">
						<input id="batchNumber" value="<c:out value="${batchNumber}"/>" type="hidden">
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
				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active panel-body" id="success">
						<table class="table table-striped table-bordered table-hover table-condensed text-center">
								<tr class="info">
									<td width="40"><span class="out">序号</span></td>
									<td width="160"><span class="out">题目</span></td>
									<td width="70"><span class="out">试题类型</span></td>
									<td width="120"><span class="out">选项</span></td>
									<td width="70"><span class="out">答案</span></td>
									<td width="70"><span class="out">重要程度</span></td>
									<td width="50"><span class="out">难易度</span></td>
									<td width="70"><span class="out">试题出处</span></td>
									<td width="100"><span class="out">工种</span></td>
									<td width="40"><span class="out">等级</span></td>
									<td width="100"><span class="out">鉴定要素编号</span></td>
								</tr>
								<c:forEach var="aBean" items="${list1}" varStatus="status">
									<tr>
										<td><c:out value="${status.index+1}" /> <input type="hidden" id="list1_id" value="<c:if test="${list1!= null}">1</c:if><c:if test="${list1== null}">0</c:if>"></td>
										<td class='num_font alignleft'>
											<a style="display:block;width:250;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stTg}"/>"><c:out value="${aBean.stTg}"/></a>
										</td>
										<td><c:out value="${aBean.equestiontype.name}" /></td>
										<td>
											<a style="display:block;width:250;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stXx}"/>"><c:out value="${aBean.stXx}"/></a>
										</td>
										<td><c:out value="${aBean.stDa}" /></td>
										<td><c:out value="${aBean.bxType}" /></td>
										<td><c:out value="${aBean.eimportance.name}" /></td>
										<td><c:out value="${aBean.stCc}" /></td>
										<td><c:out value="${jobname}" /></td>
										<td><c:out value="${rankname}" /></td>
										<td><c:out value="${aBean.jdysId}" /></td>
									</tr>
								</c:forEach>
							</table>
					</div>
					<div role="tabpanel" class="tab-pane panel-body" id="failed">
						<table class="table table-striped table-bordered table-hover table-condensed text-center">
							<tr class="info">
								<td width="40"><span class="out">序号</span></td>
								<td width="160"><span class="out">题目</span></td>
								<td width="70"><span class="out">试题类型</span></td>
								<td width="120"><span class="out">选项</span></td>
								<td width="70"><span class="out">答案</span></td>
								<td width="70"><span class="out">重要程度</span></td>
								<td width="50"><span class="out">难易度</span></td>
								<td width="70"><span class="out">试题出处</span></td>
								<td width="100"><span class="out">工种</span></td>
								<td width="40"><span class="out">等级</span></td>
								<td width="100"><span class="out">鉴定要素编号</span></td>
							</tr>
							<c:forEach var="aBean" items="${list2}" varStatus="status">
								<tr>
									<td><c:out value="${status.index+1}" /></td>
									<td><a><c:out value="${aBean.stTg}" /></a></td>
									<td><c:out value="${aBean.equestiontype.name}" /></td>
									<td><c:out value="${aBean.stXx}" /></td>
									<td><c:out value="${aBean.stDa}" /></td>
									<td><c:out value="${aBean.bxType}" /></td>
									<td><c:out value="${aBean.eimportance.name}" /></td>
									<td><c:out value="${aBean.stCc}" /></td>
									<td><c:out value="${jobname}" /></td>
									<td><c:out value="${rankname}" /></td>
									<td><c:out value="${aBean.jdysId}" /></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">
				<div class="row">
					<div class="col-md-3">
						<iframe name="LeftTree1" src="question/question_add_batch_cmp_left.jsp" scrolling="auto" width="100%" height="600px"></iframe>
					</div>
					<div class="col-md-9">
						<iframe name="MainWin2" src="question/question_add_batch_cmp_right.jsp" scrolling="auto" width="100%" height="600px"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
