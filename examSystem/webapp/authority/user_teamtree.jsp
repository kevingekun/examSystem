<%@ page language="java" pageEncoding="gb2312"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.auth.UserTeamBean" %>
<%@ page import="com.wondersgroup.falcon.model.auth.UserTeam" %>

<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>��������Ϣ��</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="STYLESHEET" type="text/css" href="css/dhtmlXTree.css">
	<link href="../inc/all.css" rel="stylesheet" type="text/css">
	<!--<link href="css/all.css" rel="stylesheet" type="text/css">-->
	<script  src="js/dhtmlXCommon.js"></script>
	<script  src="js/dhtmlXTree.js"></script>
<link rel="StyleSheet" href="js/dtree.css" type="text/css" />
<script language="javascript" src="js/dtree.js"></script>

  </head>
  
  <body bgcolor="#ebf3f6" scroll="yes">
	<table width="100%" border="0">
		<tr>
			<td width="98%" bgcolor="#ebf3f6" >
			<script type="text/javascript">
		<!--
		t = new dTree('t');
		t.add(0,-1,'��Ա����','user_teamadd.jsp','','MainWin');
		<%
		

			UserTeamBean team = new UserTeamBean();
			List tree = team.getAllTeams();
		  for (Iterator it = tree.iterator(); it.hasNext();) 
		  {
		    UserTeam node =(UserTeam)it.next();
		     //������Ľ��
		     out.println("t.add("+node.getTeamId()+",0,'"
		     				+node.getTeamName()+"'"
		     				+",'user_team.jsp?teamId="+node.getTeamId()+"','','MainWin')");
		  }%>
		  
		 <%--��ҳ���������ṹ--%>		  
		document.write(t);
		//-->
	</script>
		
			</td>
			</tr>
</table>
	
	
	
	
  </body>
</html>
