<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>缺考考生信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="newcss/style.css" rel="stylesheet" type="text/css" />
	<link href="inc/all.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#alreadyTable tr:gt(0)").remove();//清空table
		$.ajax({
			type: 'post',
			async: false,
			url: 'getUnExamUser.action',
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("unExamedTable");
				var state;
				$.each(data, function(i, n) {
					if(data[i][3]=='2'){
						state="缺考";
					}else if(data[i][3]=='0'){
						state="正常";
					}else if(data[i][3]=='1'){
						state="作弊";
					}
					 $("#unExamedTable").append("<tr align='center'>"
						+"<td>"+(i+1)+"</td>"
						+"<td><a>"+data[i][0]+"</a></td>"
						+"<td><a>"+data[i][1]+"</a></td>"
						+"<td><a>"+data[i][2]+"</a></td>"
						+"<td><a>"+state+"</a></td>"
						+"</tr>");
				});
			},
			error:function(){
				alert("error");
			}
		});
		$("#unExamedTable tr").mouseover(function(){
			$(this).addClass("td_over").siblings("tr").removeClass("td_over");
		});
		$("#unExamedTable tr").click(function(){
			$(this).addClass("td_click").siblings("tr").removeClass("td_click");
		});
	});
	function wClose(){
		this.close();
	}
</script>
  </head>
  
  <body style="background-color: rgba(243, 246, 248, 1)">
    <div style="width: 600px;margin-top: 3px">
    	<div style="width: 580px;margin-left: 3px">
    		<table id="unExamedTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
    			<tr class="title_font">
					<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
					<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号</span></td>
					<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">姓名</span></td>
					<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
					<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
				</tr>
    		</table>
    	</div>
     	<div style="text-align: center; margin-top: 20px">
     		<input type="button" class="submit_2" value="确认" onclick="wClose()"/>
     	</div>
    </div>
  </body>
</html>
