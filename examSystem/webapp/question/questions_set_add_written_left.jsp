<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSetVo"%>
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
	<title>加载机考试题信息树</title>
	<link rel="StyleSheet" type="text/css" href="js/dtree2/dtree.css" />
	<script language="javascript" src="js/dtree2/dtree.js"></script>
</head>
<body bgcolor="#ebf3f6" scroll="yes">
	<div style="height:580px;overflow:auto">
	<table width="100%" border="0">
		<tr>
			<td width="98%" bgcolor="#ebf3f6">
				<script type="text/javascript">
					var t = new dTree('t');
					t.add(0, -1, '笔答试卷', '', '', 'LeftTree');
					<%
					ProfessionBean professionBean = new ProfessionBean();
					List<EPapersSetVo> tree = professionBean.geteEPapersSets(2);
				  	for (Iterator<EPapersSetVo> it = tree.iterator(); it.hasNext();) 
				  	{
				  		EPapersSetVo node =(EPapersSetVo)it.next();
				     	//添加树的结点
				     	//out.println("t.add('"+node.getSj_id()+"',0,'"+node.getSj_mc()+"','question/questions_set_add_right.jsp?sjid="+node.getSj_id()+"&sjmc="+node.getSj_mc()+"&tkid="+node.getSj_tkid()+"','','MainWin2')");
				     	out.println("t.add('"+node.getSj_id()+"',0,'"+node.getSj_mc()+"("+node.getQuestionNum()+"题)','question/questions_set_add_written_right.jsp?sjid="+node.getSj_id()+"&sjmc="+node.getSj_mc()+"&tkid="+node.getSj_tkid()+"','','MainWin3')");
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
