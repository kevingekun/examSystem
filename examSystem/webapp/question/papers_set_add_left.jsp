<%@ page import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tkcategory"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>加载工种信息树</title>
	<link rel="StyleSheet" type="text/css" href="js/dtree2/dtree.css"/>
	<script language="javascript" src="js/dtree2/dtree.js"></script>
</head>
<body bgcolor="#ebf3f6" scroll="yes">
	<div style="height:580px;overflow:auto">
	<table width="100%" border="0">
		<tr>
			<td width="98%" bgcolor="#ebf3f6">
				<script type="text/javascript">
					var t = new dTree('t');
					t.add(0, -1, '工种', '', '', 'LeftTree');
					<%
					ProfessionBean professionBean = new ProfessionBean();
					List<Tjobsubject> tree = professionBean.getDistinctProfessions();
					List<Tkcategory> t = professionBean.getAllTkcategories();
					for (Iterator<Tkcategory> it = t.iterator(); it.hasNext();) 
				  	{
				  		Tkcategory node =(Tkcategory)it.next();
				     	//添加树的结点
				     	out.println("t.add('"+node.getId()+"','"+node.getParentid()+"','"+node.getName()+"','','','LeftTree')");
				  	}
				  	for (Iterator<Tjobsubject> it = tree.iterator(); it.hasNext();) 
				  	{
				  		Tjobsubject node =(Tjobsubject)it.next();
				     	//添加树的结点
				     	out.println("t.add('"+node.getId()+"','"+node.getCategory()+"','"+node.getJobname()+"'"+",'question/papers_set_add_right.jsp?id_job="+node.getId_job()+"&gzid="+node.getId()+"','','MainWin1')");
				  	}
				  	
				  	%>
								
					<%--在页面生成树结构--%>
					document.write(t);
					//t.openAll();
					//+node.getCcz137()+
				</script>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
