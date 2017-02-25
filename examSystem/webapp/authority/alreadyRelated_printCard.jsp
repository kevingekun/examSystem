<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已关联考生—准考证打印</title>
    
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
		$("#alreadyTable tr:gt(0)").remove();//清空table
		$.ajax({
			type: 'post',
			async: false,
			url: 'getRelatedPaper.action',
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("alreadyTable");
				$.each(data, function(i, n) {
					 $("#alreadyTable").append("<tr align='center' onclick='checkPaper("+data[i][1]+")'>"
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
		$("#alreadyTable tr").mouseover(function(){
			$(this).addClass("td_over").siblings("tr").removeClass("td_over");
		});
		$("#alreadyTable tr").click(function(){
			$(this).addClass("td_click").siblings("tr").removeClass("td_click");
		});
	});
	function checkPaper(v){
		$("#jdpcTable tr:gt(0)").remove();//清空table
		$.ajax({
			type: 'post',
			async: false,
			url: 'getRelatedJdpc.action?sjid='+v,
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("jdpcTable");
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
					cell4.innerHTML="<td><button name=\"checkb\" onclick=\"deleteJdpc(this,"+data[i][0]+")\">移除</button></td>";
				});
			},
			error:function(){
				alert("error");
			}
		});
		$("#jdpcTable tr").mouseover(function(){
			this.className="td_over";
		});
		$("#jdpcTable tr").mouseout(function(){
			this.className="";
		});
	}
	function deleteJdpc(obj,v){
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
					window.opener.location.reload();
					
				}else if(result=="error"){
					alert("移除失败！");
				}
			},
			error:function(){
				alert("系统出错，请稍后再试！");			
			}
		});
		//alert(v)
		//$(obj).closest('tr').remove();
	}
	
	</script>

  </head>
  
  <body style="background-color: rgba(243, 246, 248, 1)">
    <div style="width: 800px;height: 500px;margin-top: 3px">
    	<div style="width: 380px;height: 500px;float: left;margin-left: 3px">
    		<table id="alreadyTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
    			<tr class="title_font">
					<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
					<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">考试信息批次名称</span></td>
				</tr>
    		</table>
    	</div>
    	<div style="width: 400px;height: 500px;float: left;margin-left: 3px">
    		<table id="jdpcTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
    			<tr class="title_font" onclick="tdclick()">
					<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
					<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">导入考生批次名</span></td>
					<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">人数</span></td>
					<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
				</tr>
    		</table>
    	</div>
    </div>
  </body>
</html>
