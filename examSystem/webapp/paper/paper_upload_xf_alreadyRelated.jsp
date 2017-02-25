<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已关联鉴定所试卷</title>
    
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
			url: 'getAlready_paper_xf.action',
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("alreadyTable");
				$.each(data, function(i, n) {
					 $("#alreadyTable").append("<tr align='center' onclick='checkjds("+data[i][1]+")'>"
						+"<td>"+(i+1)+"</td>"
						+"<td><a href='javascript:void(0)'>"+data[i][0]+"</a></td>"      
						+"<td style='display:none;'>"+data[i][0]+"</td>"
						+"<td><a>"+data[i][2]+"</a></td>"
						+"<td><button name=\"checkb\" onclick=\"jds_load(this,"+data[i][1]+")\">下放</button></td>"
						+"</tr>");	
				});
			},
			error:function(){
				alert("error1");
			}
		});
		$("#alreadyTable tr").mouseover(function(){
			$(this).addClass("td_over").siblings("tr").removeClass("td_over");
		});
		$("#alreadyTable tr").click(function(){
			$(this).addClass("td_click").siblings("tr").removeClass("td_click");
		});
	});
	function checkjds(v){
		$("#jdsTable tr:gt(0)").remove();//清空table
		$("#sjid").val(v);
		$.ajax({
			type: 'post',
			async: false,
			url: 'getAlready_team_xf.action?sjid='+v,
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("jdsTable");
				$.each(data, function(i, n) {
					var rowCount = theTable.rows.length;
					var row = theTable.insertRow(rowCount);
					var cell1 = row.insertCell(0);
					cell1.innerHTML=i+1;
					var cell2 = row.insertCell(1);
					cell2.innerHTML=data[i][0];
					var cell3 = row.insertCell(2);
					cell3.innerHTML="<td><button name=\"checkb\" onclick=\"delete_team(this,"+data[i][1]+")\">取消</button></td>";
				});
			},
			error:function(){
				alert("error");
			}
		});
		$("#jdsTable tr").mouseover(function(){
			this.className="td_over";
		});
		$("#jdsTable tr").mouseout(function(){
			this.className="";
		});
	}
	function delete_team(obj,v){
		var  sjid = $("#sjid").val();
		
		$.ajax({
			type:'post',
			async: false,
			url:'remove_team_xf.action?teamid='+v+'&sjid='+sjid,
			success:function(result){
				if(result=="success"){
					$(obj).closest('tr').remove();
					<%
					session.setAttribute("info2", "1");
					%>
					window.opener.location.reload();
					alert("取消成功！");
				}else if(result=="error"){
					alert("取消失败！");
				}
			},
			error:function(){
				alert("系统出错，请稍后再试！");			
			}
		});
		//alert(v)
		//$(obj).closest('tr').remove();
	}
	function jds_load(obj,v){
		$.ajax({
			type:'post',
			async: false,
			url:'xf_paper.action?sjid='+v,
			success:function(result){
				if(result=="success"){
					$(obj).closest('tr').remove();
					<%
					session.setAttribute("info2", "1");
					%>
					window.opener.location.reload();
					alert("下放成功！");
				}else if(result=="error"){
					alert("下放失败！");
					return;
				}
				else if (result =="No_users"){
					alert("该试卷未导入考生无法下放");
					return;
				}
			},
			error:function(){
				alert("系统出错，请稍后再试！");			
			}
		});
		
		
	}
	</script>

  </head>
  
  <body style="background-color: rgba(243, 246, 248, 1)">
    <div style="width: 800px;height: 400px;margin-top: 3px">
    	<div style="width: 380px;height: 400px;float: left;margin-left: 3px">
    		<table id="alreadyTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
    			<tr class="title_font">
					<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
					<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span>
					<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">参考人数</span>
					<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
					<input type="hidden" id="sjid" name="sjid" />
			   </td>
				</tr>
    		</table>
    	</div>
    	<div style="width: 400px;height: 400px;float: left;margin-left: 3px">
    		<table id="jdsTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
    			<tr class="title_font" onclick="tdclick()">
					<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
					<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定所名称</span></td>
					<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
				</tr>
    		</table>
    	</div>
    </div>
 
  </body>
</html>
