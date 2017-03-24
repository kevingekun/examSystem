<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSetVo"%>
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
	<title>加载机考试题信息树</title>
	<link rel="StyleSheet" type="text/css" href="js/dtree2/dtree.css" />
	<script language="javascript" src="js/dtree2/dtree.js"></script>
</head>
<body bgcolor="#ebf3f6" scroll="yes">
	<div style="overflow:auto;width: 100%">
	<table width="100%" border="0">
		<tr>
			<td width="98%" bgcolor="#ebf3f6">
			<script type="text/javascript">
					var t = new dTree('t');
					t.add(0, -1, '工种', '', '', 'LeftTree');
					<%
					ProfessionBean professionBean = new ProfessionBean();
					List<Tkcategory> t = professionBean.getAllTkcategories();
					List<Tjobsubject> tree = professionBean.getProfessionsWithPapers();
					List<EPapersSetVo> tree2 = professionBean.geteEPapersSets(1);
					for (Iterator<Tkcategory> it = t.iterator(); it.hasNext();) 
				  	{
				  		Tkcategory node =(Tkcategory)it.next();
				     	//添加树的结点
				     	Integer id= Integer.valueOf( "010" + String.valueOf(node.getId()));
				     	Integer parentid = 0;
				     	if(node.getParentid()==0){
				     	}else{
					     	parentid = Integer.valueOf( "010" + String.valueOf(node.getParentid()));
				     	}
				     	out.println("t.add('"+id+"','"+parentid+"','"+node.getName()+"','','','LeftTree')");
				  	}
				  	for (Iterator<Tjobsubject> it = tree.iterator(); it.hasNext();) 
				  	{
				  		Tjobsubject node =(Tjobsubject)it.next();
				     	//添加树的结点
				     	Integer id= Integer.valueOf( "020" + String.valueOf(node.getId()));
				     	String catg = node.getCategory();
				     	if(catg==null){
				     		catg="0";
				     	}
				     	Integer parentid = Integer.valueOf( "010" + catg);
				     	out.println("t.add('"+id+"','"+parentid+"','"+node.getJobname()+"','','','LeftTree')");
				  	}
				  	for (Iterator<EPapersSetVo> it = tree2.iterator(); it.hasNext();)
				  	{
				  		EPapersSetVo node =(EPapersSetVo)it.next();
				     	//添加树的结点
				     	//out.println("t.add('"+node.getSj_id()+"',0,'"+node.getSj_mc()+"','question/questions_set_add_right.jsp?sjid="+node.getSj_id()+"&sjmc="+node.getSj_mc()+"&tkid="+node.getSj_tkid()+"','','MainWin2')");
				     	Integer id= Integer.valueOf( "030" + String.valueOf(node.getSj_id()));
				     	String tk_id = node.getSj_tkid();
				     	if(tk_id==null){
					     	tk_id="0";
				     	}
				     	Integer parentid = Integer.valueOf( "020" +tk_id );
				     	out.println("t.add('"+id+"','"+parentid+"','"+node.getSj_mc()+"("+node.getQuestionNum()+"题)','question/question_add_batch_cmp_right.jsp?sjid="+node.getSj_id()+"','','MainWin2')");
				  	}
				  	
				  	%>
								
					<%--在页面生成树结构--%>
					document.write(t);
				</script>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
