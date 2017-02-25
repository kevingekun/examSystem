<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>考点下放记录</title>
    
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
		var id=<%=request.getParameter("id")%>;
		$("#alreadyTable tr:gt(0)").remove();//清空table
		$.ajax({
			type: 'post',
			async: false,
			url: 'getStationRecordOfXf.action?station_id='+id,
			success:function(result){
				var data = eval(result);
				var theTable = document.getElementById("alreadyTable");
				$.each(data, function(i, n) {
					 $("#alreadyTable").append("<tr align='center'>"
						+"<td>"+(i+1)+"</td>"
						+"<td>"+data[i][0]+"</td>"
						+"<td>"+data[i][1]+"</td>"
						+"<td>"+data[i][2]+"</td>"
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
	</script>

  </head>
  
  <body style="background-color: rgba(243, 246, 248, 1)">
    <div style="width: 1000px;height: 400px;margin-top: 3px">
   		<table id="alreadyTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
   			<tr class="title_font">
				<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
				<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span>
				<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">下放时间</span>
				<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">参考人数</span>
		   </td>
			</tr>
   		</table>
    </div>
    <!-- <div style="width: 800px;height: 30px;" >
    	<div style="text-align:center;">
    		<input class="submit_2" type="button" value="上传一体化" onclick="upLoadYth()">
    	</div>
    </div> -->
  </body>
</html>
