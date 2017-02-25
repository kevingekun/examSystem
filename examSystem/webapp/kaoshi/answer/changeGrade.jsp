<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改成绩</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
function changeGrade(){
	var sjid = <%=request.getParameter("sjid")%>;
	var userid = <%=request.getParameter("userid")%>;
	var grade = $("#grade").val();
	if(!isNaN(grade)){
		if(grade<101&&grade>-1){
			$.ajax({
				type:'post',
				async: false,
				url:'changeGrade.action?sjid='+sjid+'&grade='+grade+'&userid='+userid,
				success:function(result){
					if(result=='1'){
						alert("修改成功！");
					}else{
						alert("修改失败！");
					}
				},
				error:function(){
					alert("出错");
				}
			});
			this.close();
			window.opener.location.reload();
		}else{
			alert("只能为0-100")
		}
	}else{
		alert("格式不正确！")
	}
}
function closewindow(){
	this.close();
}
</script>
  </head>
  
  <body style="background-color: rgba(243, 246, 248, 1)">
    <div>
    	<div>
    		<a style="font-size: 14">原分数：</a>
    		<input type="text" value="<%=request.getParameter("grade")%>" readonly="readonly" style="width: 140px;"/>
    	</div>
    	<div style="margin-top:5px;">
    		<a style="font-size: 14">修改为：</a>
    		<input type="text" id="grade" style="width: 140px;"/>
    	</div>
    	<div style="margin-top:10px;">
    		<div style="margin-left:60px;float:left">
    			<input type="button" value="确定" onclick="changeGrade()" />
    		</div>
    		<div style="margin-left:140px">
				<input type="button" value="取消" onclick="closewindow()" />
	    	</div>
    	
    	</div>
    </div>
  </body>
</html>
