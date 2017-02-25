<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>选择鉴定要素表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	$("#table_jdys tr").mouseover(function(){
		this.className="td_over";
	});
	$("#table_jdys tr").mouseout(function(){
		this.className="";
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

<body style="background-color: rgba(243, 246, 248, 1)">
	<div style="height:460px; overflow:auto;border:1px solid silver;">
		<table id="table_jdys" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
			<tr class="title_font">
				<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">选择 </span></td>
				<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
				<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">行为领域 </span></td>
				<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定范围</span></td>
				<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定点</span></td>
			</tr>
		</table>
	</div>
	<div style="width: 100%;text-align:center;margin-top: 8px">
		<input class="submit_2" type="button" value="确定" onclick="submt()"/>
		<input id="id" type="hidden"/>
		<input id="name" type="hidden"/>
	</div>
</body>
</html>
