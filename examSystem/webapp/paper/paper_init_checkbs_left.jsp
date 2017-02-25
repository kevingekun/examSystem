<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>加载工种信息树</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="StyleSheet" type="text/css" href="js/dtree2/dtree.css" />
<script language="javascript" src="js/dtree2/dtree.js"></script>

</head>


<body bgcolor="#ebf3f6" scroll="yes">
	<div style="height:100%;overflow:auto">
	<table width="100%" border="0">
		<tr>
			<td width="98%" bgcolor="#ebf3f6">
				<script type="text/javascript">
					var t = new dTree('t');
					t.add(0, -1, '笔试工种', '', '', 'LeftTree');
					<%
					ProfessionBean professionBean = new ProfessionBean();
					List<Tjobsubject> tree = professionBean.getztgz_bs();
				  	for (Iterator<Tjobsubject> it = tree.iterator(); it.hasNext();) 
				  	{
				  		Tjobsubject node =(Tjobsubject)it.next();
				     	//添加树的结点
				     	out.println("t.add('"+node.getId()+"',0,'"+node.getJobname()+"'"+",'paper/paper_init_checkbs_right.jsp?id_job="+node.getId_job()+"&gzid="+node.getId()+"','','MainWin99')");
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
