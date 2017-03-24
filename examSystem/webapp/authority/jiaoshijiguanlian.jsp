<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
	<title>教师机关联</title>
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
				<div class="col-md-1">
					<button class="btn btn-primary btn-sm" onclick="jsjrelate()">
						<span class="glyphicon glyphicon-random" aria-hidden="true"></span>&nbsp;试卷关联
					</button>
				</div>
				<div class="col-md-1 col-md-offset-3">
					<button class="btn btn-info btn-sm" data-toggle="modal" data-target=".bs-example-modal-lg" onclick="alreadyRelatedsj()">
						<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span> 查看已关联
					</button>
				</div>
			</div>
		</div>
		<form id="aForm" name="aForm" action="" method="post">
			<table class="table table-striped table-bordered table-hover table-condensed" id="tb" style="text-align:center;">
				<tr class="info">
					<td width="3%"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()"></td>
					<td width="5%"><span class="out">序号 </span></td>
					<td width="50%"><span class="out">试卷名称</span></td>
					<td width="40%"><span class="out">考生人数</span></td>
				</tr>
				<c:forEach var="aBean" items="${list2}" varStatus="status">
					<tr id='r1'>
						<td><input type="checkbox" id="checkbox" name="checkedid" value="<c:out value='${aBean.sj_id}'/>" /></td>
						<td><c:out value="${status.index+1}" /></td>
						<td><c:out value="${aBean.sjMc}" /></td>
						<td><c:out value="${aBean.ks_rs}" /></td>
					</tr>
				</c:forEach>
			</table>
		</form>
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
				<table id="jdpcTable" class="table table-striped table-bordered table-hover table-condensed" style="text-align:center;">
	    			<tr class="info">
						<td width="8%"><span class="out">序号 </span></td>
						<td width="40%"><span class="out">试卷名称</span></td>
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
	function selectall() {
		var check = document.getElementById("checkAll");
		var values = document.getElementsByName("checkedid");
		for (var i = 0; i < values.length; i++)
			values[i].checked = check.checked;
	}
	function jsjrelate() {
		var row = 0;
		var checkedid = "";
		var values = document.getElementsByName("checkedid");
		for (var i = 0; i < values.length; i++) {
			if (values[i].checked == true) {
				checkedid += values[i].value + ",";
				row++;
			}
		}
		if (row > 0) {
			checkedid = checkedid.substring(0, checkedid.length - 1);
			document.aForm.action = "relatesj.action?checkid=" + checkedid;
			document.aForm.submit();
		} else {
			alert("请选择要关联的试卷！");
		}
	}

	function alreadyRelatedsj() {
		$("#alreadyTable tr:gt(0)").remove();//清空table
		$.ajax({
			type: 'post',
			async: false,
			url: 'getRelatekc.action',
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("alreadyTable");
				$.each(data, function(i, n) {
					
					 $("#alreadyTable").append("<tr align='center' onclick='checksj("+data[i]+")'>"
						+"<td>"+(i+1)+"</td>"
					//	+"<td><a href='javascript:void(0)'>"+data[i]+"</a></td>"      
						+"<td><a href='javascript:void(0)'>考场"+(i+1)+"</a></td>" 
						+"<td style='display:none;'>"+data[i]+"</td>"
						+"</tr>");
					
				});
			},
			error:function(){
				alert("error1");
			}
		});
		<%-- window.open("<%=request.getContextPath()%>/authority/alreadyRelatedsj.jsp", "newwindow", "height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no"); --%>
	}
	function checksj(v){
		$("#jdpcTable tr:gt(0)").remove();//清空table
		$.ajax({
			type: 'post',
			async: false,
			url: 'getRelatedsj.action?kcid='+v,
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
					var cell4 = row.insertCell(3);
					cell4.innerHTML="<td><button name=\"checkb\" class=\"btn btn-primary btn-xs\" onclick=\"deletesj(this,"+data[i][2]+")\">取消</button></td>";
				});
			},
			error:function(){
				alert("error");
			}
		});
	}
	function deletesj(obj,v){
		$.ajax({
			type:'post',
			async: false,
			url:'removesj.action?sjid='+v,
			success:function(result){
				if(result=="success"){
					$(obj).closest('tr').remove();
					<%
					session.setAttribute("info_kc", "1");
					%>
					window.location.reload();
					alert("取消成功！");
				}else if(result=="error"){
					alert("取消失败！");
				}
			},
			error:function(){
				alert("系统出错，请稍后再试！");			
			}
		});
	}
	function closeRefresh(){
		window.location.reload();
	}
	</script>
	<script type="text/javascript">
	<%String info_kc = (String) session.getAttribute("info_kc");
			String info4 = (String) request.getAttribute("info4");
			if (info4 == "1" && !"1".equals(info_kc)) {%>
		alert("关联成功");
	<%}
			if (info4 == "2") {%>
		alert("关联失败");
	<%}
			if (info4 == "0") {%>
		
	<%}%>
	</script>
</body>
</html>
