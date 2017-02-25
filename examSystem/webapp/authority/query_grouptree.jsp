<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.auth.UserTeamBean" %>
<%@ page import="com.wondersgroup.falcon.model.auth.UserTeam" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<html>
  <head>
  <base href="<%=basePath%>">
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>加载组信息树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="STYLESHEET" type="text/css" href="css/dhtmlXTree.css">
	<link href="inc/all.css" rel="stylesheet" type="text/css">

<link rel="StyleSheet" type="text/css" href="js/dtree2/dtree.css"/>
<script language="javascript" src="js/dtree2/dtree.js"></script>

  </head>
  
  <body bgcolor="#ebf3f6" scroll="yes">
	<table width="100%" border="0">
		<tr>
			<td width="98%" bgcolor="#ebf3f6" >
			<script type="text/javascript">
		<!--
		t = new dTree('t');
		t.add(0,-1,'考点分组','authority/user_teamadd.jsp','','MainWin');
		<%
		

			UserTeamBean team = new UserTeamBean();
			List<UserTeam> tree = team.getAllTeams();
		  for (int i =0;i<tree.size();i++) 
		  {
		    UserTeam node =(UserTeam)tree.get(i);
		     //添加树的结点
		     out.println("t.add("+node.getTeamId()+","+node.getPteamid()+",'"
		     				+node.getTeamName()+"'"
		     				+",'queryteam.action?teamid="+node.getTeamId()+"','','MainWin')");
		  }%>
		  
		 <%--在页面生成树结构--%>		  
		document.write(t);
		//-->
	</script>
		
			</td>
			</tr>
</table>
	
	
	
	
  </body>
</html>
