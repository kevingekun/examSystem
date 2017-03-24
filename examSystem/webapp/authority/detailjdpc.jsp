<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已关联鉴定批次</title>
    
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
		$("#jdpcTable tr:gt(0)").remove();//清空table
		var id = "<%= request.getParameter("jdid")%>";
		
		$.ajax({
			type: 'post',
			async: false,
			url: 'getDetailJdpc.action?jdid='+id,
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
		$("#jdpcTable tr").mouseover(function(){
			$(this).addClass("td_over").siblings("tr").removeClass("td_over");
		});
		$("#jdpcTable tr").click(function(){
			$(this).addClass("td_click").siblings("tr").removeClass("td_click");
		});
	});
	
	
	</script>

  </head>
  
  <body style="background-color: rgba(243, 246, 248, 1)">
    <div style="width: 800px;height: 500px;margin-top: 3px">
    	<div style="width: 600px;height: 500px;float: left;margin-left: 3px">
    		<table id="jdpcTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
    			<tr class="title_font" onclick="tdclick()">
					<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
					<td width="35%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
					<td width="15%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
					<td width="15%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号</span></td>
				</tr>
    		</table>
    	</div>
    </div>
  </body>
</html>
