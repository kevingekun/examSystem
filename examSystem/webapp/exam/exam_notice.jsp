<%@page contentType="text/html;charset=GBK"%>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamNoticeServiceImple" />
<%@page import="java.util.List"%>
<%@page import="com.wondersgroup.falcon.model.exam.ExamAnnoucement" %>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<% 
	 List list = examservice.findAllAnnoucement();
%>
<link href="../inc/all.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr> 
	  <td height="5" colspan="4"></td>
	</tr>
	<tr> 
		<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
		<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
		<td width="98%" background="../images/k1_03.gif">����&nbsp;	
			
		</td>
		<td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
	</tr>
  	<tr> 
	    <td background="../images/k1_05.gif"></td>
	    <td  colspan="2" valign="top"> 
			<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD" id="tables">
		        <tr bgcolor="#FFFFFF"> 
					<td width="5%" height="23"><div align="center">���</div></td>
					<td ><div align="center">����</div></td>	
					<td width="15%"><div align="center">������</div></td> 
					<td width="15%"><div align="center">������</div></td> 
					<td width="15%"><div align="center">ʱ��</div></td> 
					<td width="15%"><div align="center">����</div></td> 		          
		        <tr bgcolor="#FFFFFF"> 
		          	<td height="5" colspan="6" bgcolor="#F1F8FE"></td>
		        </tr>
		        <% 
		        	for(int i=0;i<list.size();i++){
		        		Object[] ea = (Object[])list.get(i);
		        %>
				<tr bgcolor="#FFFFFF"> 
				    <td align=center><%=i+1 %></td>
				    <td ><%=ea[1]%></td>
				    <td align=center><%=ea[2] %></td>
				    <td align=center><%=ea[3] %></td>
				    <td align=center><%=ea[4] %></td>			    
				    <td align=center><a href="examonenotice.jsp?eaid=<%=ea[0] %>" >�鿴</a></td>						    
				</tr>			        
		       	<% 
		        	}
		        %>
	            <tr bgcolor="#FFFFFF"> 
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

%>