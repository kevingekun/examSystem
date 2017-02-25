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
    
	<link rel="stylesheet" type="text/css" href="inc/all.css" />
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
		t.add(0,-1,'考场信息','','','MainWin');
		<%
		

			UserTeamBean team = new UserTeamBean();
			List<Object[]> tree = team.getExam();
		  for (int i =0;i<tree.size();i++) 
		  {
		     //添加树的结点
		     out.println("t.add("+tree.get(i)[0]+",0,'"+tree.get(i)[1]+i+"','','','MainWin')");
		  	List arrangelist = team.getArrange((tree.get(i)[0]).toString());
		 	 for (Iterator it = arrangelist.iterator(); it.hasNext();) 
		  		{
		   			 UserTeam node =(UserTeam)it.next();
				     //添加树的结点
				     if("0".equals((node.getPteamid()).toString())){
				     out.println("t.add("+(tree.get(i)[0]).toString()+node.getTeamId()+","+tree.get(i)[0]+",'"+node.getTeamName()+"'"+",'','','MainWin')");
				     }else{
				     out.println("t.add("+(tree.get(i)[0]).toString()+node.getTeamId()+","+(tree.get(i)[0]).toString()+node.getPteamid()+",'"+node.getTeamName()+"'"+",'arrangeuser.action?teamid="+node.getTeamId()+"&examid="+tree.get(i)[0]+"','','MainWin')");
				     }
		  		}
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
