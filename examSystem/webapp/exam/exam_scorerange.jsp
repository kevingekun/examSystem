<%@page contentType="text/html;charset=GBK"%>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />
<%@page import="java.util.List"%>
<%@ page import="com.wondersgroup.falcon.model.exam.ExamPaper"%>
<%@page import="com.wondersgroup.falcon.model.exam.ExamAnswers" %>
<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil"%>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<% 
	String exampaperid = request.getParameter("exampaperid");	
	System.out.println("exampaperid==================>>>"+exampaperid);
	ExamPaper exampaper = examservice.findExamPaperById(exampaperid); 
	List list = examservice.findAnswerPageByPaper(exampaper,true); 
%>
<link href="../inc/all.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr> 
	  <td height="5" colspan="4"></td>
	</tr>
	<tr> 
		<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
		<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
		<td width="98%" background="../images/k1_03.gif">答卷列表&nbsp;	
			
		</td>
		<td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
	</tr>
  	<tr> 
	    <td background="../images/k1_05.gif"></td>
	    <td  colspan="2" valign="top"> 
			<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD" id="tables">
		        <tr bgcolor="#FFFFFF"> 
		          <td width="5%" height="23"><div align="center">序号</div></td>
		          <td width="10%"><div align="center">编号</div></td>
		          <td ><div align="center">试卷名</div></td>	
		          <td width="15%"><div align="center">时间</div></td>
		          <td width="15%"><div align="center">参考人</div></td>
		          <td width="15%"><div align="center">分数</div></td> 
		          <td width="15%"><div align="center">排名</div></td> 	          
		        <tr bgcolor="#FFFFFF"> 
		          <td height="5" colspan="7" bgcolor="#F1F8FE"></td>
		        </tr>
		        <% 
			    	if(list!=null){
						for(int i=0;i<list.size();i++){
							ExamAnswers ep = (ExamAnswers)list.get(i);
							//ExamAnswers realanswerpage = examservice.finByPaperAndUser(ep,username); 
				%>
					<tr bgcolor="#FFFFFF"> 
					    <td align=center><%=i+1 %></td>
					    <td align=center>0000</td>
					    <td ><%=ep.getExampaper().getExamname() %></td>
					    <td align=center><%=ep.getEndtime().toString().substring(0,10)%></td>
					    <td ><%=ep.getUsername() %></td>				    
					    <td align=center><%=ep.getWholemark()%></td>
					    <td align=center><%=i+1 %></td>						    
					</tr>					
				<%	
						}	
					}        	
		        %>
	            <tr bgcolor="#FFFFFF"> 
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>				    
				    <td>&nbsp;</td>	
				    <td>&nbsp;</td>				    
				    <td>&nbsp;</td>					    
	            </tr>
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
<%
	HibernateUtil.commitTransaction(); 
	HibernateUtil.closeSession();
%>