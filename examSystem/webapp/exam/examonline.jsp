<%@page contentType="text/html;charset=GBK"%>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />
<%@page import="java.util.List"%>
<%@ page import="com.wondersgroup.falcon.model.exam.ExamPaper"%>
<%@page import="com.wondersgroup.falcon.model.exam.ExamAnswers" %>
<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil"%>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<% 
	String username = AcegiUtil.getUserDetails().getUsername();
	List list = examservice.findExamPaper(); 
	
	String refuse = request.getParameter("refuse");
	

%>
<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script>
		function goToCommFlat(advername){
			document.flatform.adverName.value=advername;
			document.flatform.submit();
		}
	</script>
	<BODY style="font-size: 12px;background-image: url(<%=request.getContextPath()%>/images/m_bg.gif);background-repeat: repeat-x;MARGIN: 5px;"
		scroll="no">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td height="5" colspan="4"></td>
		</tr>
	    <tr> 
			<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
			<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
			<td width="98%" background="../images/k1_03.gif">试卷列表</td>
		    <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
		</tr>       
		<tr> 
			<td background="../images/k1_05.gif"></td>
			<td  colspan="2" valign="top"> 
			 <table width="100%" height=455 border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
					<% 
						if(refuse!=null){
					%>
					<tr bgcolor="#Ffffff" height=20> 
						<td colspan=4 align=center>
							<font color=#ff0000>您已经参加过2次了，不能再次参加！</font>
						</td>
					</tr>
					<%
						}
					%>
					<tr bgcolor="#F1F8FE" > 
						<td align=center width=50 height=10>编号</td>
						<td align=center width=>试卷名称</td>
						<td align=center width=150>有效日期至</td>	
						<td align = center width=100>操作</td>					
					</tr>
					<% 
						if(list!=null){
							for(int i=0;i<list.size();i++){
								ExamPaper ep = (ExamPaper)list.get(i);
								ExamAnswers realanswerpage = examservice.finByPaperAndUser(ep,username);   
								out.println("<tr bgcolor=#ffffff align=center>");
								out.println("<td width=50 height=10>"+(i+1)+"</td>");
								out.println("<td>"+ep.getExamname()+"</td>");
								out.println("<td>"+(ep.getEffectendtime()==null?"":ep.getEffectendtime().toString().substring(0,10))+"</td>");	
								if(realanswerpage==null){
									out.println("<td><a class=b href='examEnter.jsp?examid="+ep.getExamid()+"'>参加考试</a></td>");
								}else{
									out.println("<td><a class=b href='examReview.jsp?examanswerid="+realanswerpage.getExamanswerid()+"'>查看答卷</a></td>");
								}							
								
							}
						}
					%>
					<tr><td colspan=2></td></tr>
			</table>
			</td>
	    	<td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
	    </tr>
		<tr> 
			<td><img src="../images/k1_07.gif" width="2" height="5"></td>
			<td colspan="2" background="../images/k1_08.gif"> </td>
			<td><img src="../images/k1_09.gif"></td>
		</tr>
	</table>    

	<form name="flatform" method="post" action="../messLoginServlet">
		<input type="hidden" name="adverName" value="">
	</form>
	</BODY>
</HTML>
<%
	HibernateUtil.commitTransaction(); 
	HibernateUtil.closeSession();
%>