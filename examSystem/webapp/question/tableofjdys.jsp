<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<title>选择鉴定要素表</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->
	<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#table_jdys tr:gt(0)").remove();//清空table
	var gzid = <%=request.getParameter("gzid") %>;
	var dj = <%=request.getParameter("dj") %>;
	$.ajax({
		type: 'post',
		async: false,
		url: 'findJdys.action?gzid='+gzid+'&dj='+dj,
		success:function(result){
			var data = eval(result);
			var theTable = document.getElementById("table_jdys");
			$.each(data, function(i, n) {
				var rowCount = theTable.rows.length;
				var row = theTable.insertRow(rowCount);
				var cell1 = row.insertCell(0);
				cell1.innerHTML="<td><input name=\"checkb\" type=\"radio\" onclick=\"checkjdys("+data[i][0]+",\'"+data[i][3]+"\')\"/></td>";
				var cell5 = row.insertCell(1);
				cell5.innerHTML=i+1;
				var cell2 = row.insertCell(2);
				cell2.innerHTML=data[i][1];
				var cell3 = row.insertCell(3);
				cell3.innerHTML=data[i][2];
				var cell4 = row.insertCell(4);
				cell4.innerHTML=data[i][3];
				
			});
		},
		error:function(){
			alert("error");
		}
	});
})
function checkjdys(id,name){
	$("#id").val(id);
	$("#name").val(name);
}
function submt(){
	var id=$("#id").val();
	var name=$("#name").val();
	if(id==""||name==""){
		alert("请选择鉴定要素");
	}else{
		window.opener.document.getElementById("jdysid").value=$("#id").val();
		window.opener.document.getElementById("jdys").value=$("#name").val();
		this.close();
	}
}
</script>
</head>

<body>
	<div style="height:460px; overflow:auto;border:1px solid silver;">
		<table id="table_jdys" class="table table-striped table-bordered table-hover table-condensed text-center">
			<tr class="info">
				<td width="5%"><span class="out">选择 </span></td>
				<td width="5%"><span class="out">序号 </span></td>
				<td width="20%"><span class="out">行为领域 </span></td>
				<td width="20%"><span class="out">鉴定范围</span></td>
				<td width="50%"><span class="out">鉴定点</span></td>
			</tr>
		</table>
	</div>
	<div style="width: 100%;text-align:center;margin-top: 4px">
		<input class="btn btn-success btn-sm" type="button" value="确定" onclick="submt()"/>
		<input id="id" type="hidden"/>
		<input id="name" type="hidden"/>
	</div>
</body>
</html>
