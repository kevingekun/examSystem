<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%
 ProfessionBean professionBean = new ProfessionBean();
 List<EPapers> p_list = professionBean.getNoUsePaper();
%>
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
	<title>鉴定批次考试安排</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">考试安排</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form id="aForm" name="aForm" method="post" class="form-inline">
					<div class="col-md-4">
						<div class="form-group">
							<label for="sjmc_id">试卷名称：</label>
							<select class="form-control" id="paper" name="paper" onchange="checkPaper(this.value)">
								<option value="">请选择</option>
								<%
									for(int i=0;i<p_list.size();i++){
										EPapers paper = p_list.get(i);
										out.print("<option value="+paper.getSjId()+">"+paper.getSjMc()+"</option>");
									}
								%>
							</select>
							<input type="hidden" id="sjid" name="sjid" />
	    				</div>
					</div>
				</form>
				<div class="col-md-1">
					<button class="btn btn-primary btn-sm" type="button" onClick="sbt()">
						<span class="glyphicon glyphicon-random" aria-hidden="true"></span>&nbsp;关联
					</button>
				</div>
				<div class="col-md-1 col-md-offset-5">
					<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target=".bs-example-modal-lg" onclick="alreadyRelated()">
						<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span> 查看已关联
					</button>
				</div>
			</div>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" id="tb" style="text-align:center;">
			<tr class="info">
				<td width="3%"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()"></td>
				<td width="5%"><span class="out">序号 </span></td>
				<td width="40%"><span class="out">鉴定批次号</span></td>
				<td width="20%"><span class="out">考生人数</span></td>
				<td width="20%"><span class="out">操作</span></td>
			</tr>
			<c:forEach var="aBean" items="${list}" varStatus="status">
				<tr id='r1'>
					<td align='center'><input type="checkbox" id="checkbox" name="checkedid" value="<c:out value='${aBean.jd_id}'/>" /></td>
					<td align='center'><c:out value="${status.index+1}" /></td>
					<td align='center'><c:out value="${aBean.jd_mc}" /></td>
					<td align='center'><c:out value="${aBean.ks_rs}" /></td>
					<td align='center'>
						<button class="btn btn-info btn-xs" type="button" onClick="detailjdpc(<c:out value='${aBean.jd_id}'/>)" data-toggle="modal" data-target="#myModal">
							<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>查看详情
						</button>
						<button class="btn btn-danger btn-xs" type="button" onClick="deletejdpc(<c:out value='${aBean.jd_id}'/>)">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeRefresh()"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">批次详情</h4>
      </div>
      <div class="modal-body">
        <table id="jdpcTable" class="table table-striped table-bordered table-hover table-condensed" style="text-align:center;">
   			<tr class="info">
				<td width="5%"><span class="out">序号 </span></td>
				<td width="20%"><span class="out">考生姓名</span></td>
				<td width="15%"><span class="out">身份证号</span></td>
				<td width="15%"><span class="out">准考证号</span></td>
			</tr>
   		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeRefresh()">Close</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeRefresh()"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">已关联</h4>
      </div>
      <div class="modal-body">
      	<div class="row">
      		<div class="col-md-6">
				<table id="alreadyTable" class="table table-striped table-bordered table-hover table-condensed" style="text-align:center;">
	    			<tr class="info">
						<td width="10%"><span class="out">序号 </span></td>
						<td width="92%"><span class="out">试卷名称</span></td>
					</tr>
	    		</table>
			</div>
      		<div class="col-md-6">
				<table id="alreadyJdpcTable" class="table table-striped table-bordered table-hover table-condensed" style="text-align:center;">
	    			<tr class="info">
						<td width="8%"><span class="out">序号 </span></td>
						<td width="40%"><span class="out">鉴定批次号</span></td>
						<td width="10%"><span class="out">人数</span></td>
						<td width="10%"><span class="out">操作</span></td>
					</tr>
	    		</table>
			</div>
      	</div>
      </div>
    </div>
  </div>
</div>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function selectall(){
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("checkedid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}
function sbt(){
    var row =0;
    var checkedid="";
	var values = document.getElementsByName("checkedid");
	var sjid = $("#paper").val();
	if(sjid==""){
		alert("请选择试卷！");
	}else{
		for (var i = 0; i < values.length; i++){
	       	if(values[i].checked == true){
	       		checkedid+=values[i].value+",";
	       	   row++;
	       	}
	    }
	    if(row>0)
	    {
	    	checkedid=checkedid.substring(0,checkedid.length-1);
			document.aForm.action="relateJdpcAndSjmc.action?checkid="+checkedid;
			document.aForm.submit();
	    }else{
	    	alert("请选择鉴定批次号！");
	    }
	}
}
function checkPaper(v){
	$("#sjid").val(v);
}
function alreadyRelated(){
	$("#alreadyTable tr:gt(0)").remove();//清空table
	$("#alreadyJdpcTable tr:gt(0)").remove();//清空table
	$.ajax({
		type: 'post',
		async: false,
		url: 'getRelatedPaper.action',
		success:function(result){
			var data = eval(result);
			var theTable = document.getElementById("alreadyTable");
			$.each(data, function(i, n) {
				 $("#alreadyTable").append("<tr align='center' onclick='checkPaperJdpc("+data[i][1]+")'>"
					+"<td>"+(i+1)+"</td>"
					+"<td><a href='javascript:void(0)'>"+data[i][0]+"</a></td>"
					+"<td style='display:none;'>"+data[i][1]+"</td>"
					+"</tr>");
			});
		},
		error:function(){
			alert("error");
		}
	});
	<%-- window.open ("<%=request.getContextPath() %>/authority/alreadyRelated.jsp", "newwindow", "height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no"); --%>
}
function checkPaperJdpc(v){
	$("#alreadyJdpcTable tr:gt(0)").remove();//清空table
	$.ajax({
		type: 'post',
		async: false,
		url: 'getRelatedJdpc.action?sjid='+v,
		success:function(result){
			var data = eval(result);
			var theTable = document.getElementById("alreadyJdpcTable");
			$.each(data, function(i, n) {
				var rowCount = theTable.rows.length;
				var row = theTable.insertRow(rowCount);
				var cell1 = row.insertCell(0);
				cell1.innerHTML=i+1;
				var cell2 = row.insertCell(1);
				cell2.innerHTML=data[i][1];
				var cell3 = row.insertCell(2);
				cell3.innerHTML=data[i][3];
				var cell4 = row.insertCell(3);
				cell4.innerHTML="<td><button name=\"checkb\" class=\"btn btn-xs btn-warning\" onclick=\"removeJdpc(this,"+data[i][0]+")\"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>移除</button></td>";
			});
		},
		error:function(){
			alert("error");
		}
	});
}
function removeJdpc(obj,v){
	$.ajax({
		type:'post',
		async: false,
		url:'removeJdpc.action?jdid='+v,
		success:function(result){
			if(result=="success"){
				$(obj).closest('tr').remove();
				<%
				session.setAttribute("info2", "1");
				%>
				window.location.reload();
				
			}else if(result=="error"){
				alert("移除失败！");
			}
		},
		error:function(){
			alert("系统出错，请稍后再试！");			
		}
	});
}
function deletejdpc(jdid){
    var checkedid=jdid;
	    	$.ajax({
				type:'post',
				async: false,
				url:'deleteJdpc.action?checkid='+checkedid,
				success:function(result){
					if(result=="success"){
						alert("删除成功");
						window.location.reload();
					}else if(result=="error"){
						alert("删除失败！");
					}
				},
				error:function(){
					alert("系统出错，请稍后再试！");			
				}
			});
}
function detailjdpc(jdid){
	$("#jdpcTable tr:gt(0)").remove();//清空table
	$.ajax({
		type: 'post',
		async: false,
		url: 'getDetailJdpc.action?jdid='+jdid,
		success:function(result){
			var data = eval(result);
			var theTable = document.getElementById("jdpcTable");
			$.each(data, function(i, n) {
				var rowCount = theTable.rows.length;
				var row = theTable.insertRow(rowCount);
				var cell1 = row.insertCell(0);
				cell1.innerHTML=i+1;
				var cell2 = row.insertCell(1);
				cell2.innerHTML=data[i][0];
				var cell3 = row.insertCell(2);
				cell3.innerHTML=data[i][1];
				var cell3 = row.insertCell(3);
				cell3.innerHTML=data[i][2];
			});
		},
		error:function(){
			alert("error");
		}
	});
	<%-- window.open ("<%=request.getContextPath() %>/authority/detailjdpc.jsp?jdid="+jdid, "newwindow", "height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no"); --%>
}
function closeRefresh(){
	window.location.reload();
}
</script>
<script type="text/javascript">
<%
	String flag = (String)request.getAttribute("flag");
	String info = (String)request.getAttribute("info");
	String info2 = (String)session.getAttribute("info2");//取消关联页面alreadyRelated.jsp修改此参数
	if(info=="1"&&!"1".equals(info2)){ %>
		alert("关联成功");
	<%}
	if(info=="2"){ %>
	alert("关联失败");
	<%}
	if(info=="0"){ %>
	<%}%>
</script>
</body>
</html>
