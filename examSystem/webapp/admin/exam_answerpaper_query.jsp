<%@page contentType="text/html;charset=GBK"%>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />
<%@page import="java.util.List"%>
<%@ page import="com.wondersgroup.falcon.model.exam.ExamPaper"%>
<% 
	List list = examservice.findExamPaper();
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
	<body scroll="no">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
			  <td height="3" colspan="3"></td>
			</tr>
			<tr> 
				<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
				<td background="../images/min_02.gif">
					<div align="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
			            	<tr> 
				            	<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
				                <td><strong> &nbsp;答卷查询</strong>
			              			<div align="left"></div></td>
			                </tr>
			             </table>
		             </div>
		        </td>
		    	<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
			</tr>
		    <tr> 
				<td height="5" colspan="3"></td>
		    </tr>
		</table>   
		<table><tr height=5><td></td></tr></table>
		
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
									out.println("<tr bgcolor=#ffffff align=center>");
									out.println("<td width=50 height=10>"+(i+1)+"</td>");
									out.println("<td>"+ep.getExamname()+"</td>");
									out.println("<td>"+(ep.getEffectendtime()==null?"":ep.getEffectendtime().toString().substring(0,10))+"</td>");								
									out.println("<td><a class=b href='exam_answerpage.jsp?examid="+ep.getExamid()+"'>答卷列表</a></td>");
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
