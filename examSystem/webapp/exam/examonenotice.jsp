<%@ page contentType="text/html;charset=gbk"%>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamNoticeServiceImple" />
<%@page import="com.wondersgroup.falcon.model.exam.ExamAnnoucement" %>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<% 
	String eaid = request.getParameter("eaid");
	System.out.println("eaid===========>>>"+eaid);
	ExamAnnoucement ea = new ExamAnnoucement();
	ea = examservice.findAnnoucementById(eaid); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">

</head>

<body>
<form name="formvip" method="post" action="exam_noticesend.jsp">
	<input type=hidden name="exampaperid" value="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr> 
	      <td height="5" colspan="4"></td>
	    </tr>
        <tr> 
          <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
          <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
          <td width="98%" background="../images/k1_03.gif"><strong>发送通告</strong></td>
          <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
        </tr>
        <tr>
          <td background="../images/k1_05.gif"></td>
          <td height="5" colspan="2"></td>
          <td background="../images/k1_06.gif">&nbsp;</td>
        </tr>
        <tr> 
			<td background="../images/k1_05.gif"></td>
			<td height="380" colspan="2" valign="top">
				<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
					<tr bgcolor="#FFFFFF"> 
	            		<td colspan="5" align=center><div style="color:#ff0000" id="errormessage" name="errormessage"></div></td>
	          		</tr>
					<tr bgcolor="#FFFFFF"> 
						<td align="right" width=5%>标 &nbsp;&nbsp;&nbsp;题</td>
				    	<td colspan=4><%=ea.getTitle() %></td>
					</tr>   
					<tr bgcolor="#FFFFFF"> 
				  		<td align=right>
				  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生效时间
				  		</td>
				  		<td width=10%>
				  			<%=ea.getEffectstarttime() %>
				  		</td>
				  		<td width=2% align=center>
				  			－－
				  		</td>
				  		<td width=4% align=right>
				  			失效时间
				  		</td>
				  		<td width=20%>
				  			<%=ea.getEffectendtime() %>
				  		</td>
					</tr>									                 
					<tr bgcolor="#FFFFFF"> 
				  		<td align="right">内 &nbsp;&nbsp;&nbsp;容
				    	</td>
				  		<td colspan=4><textarea readonly id="content" name="content" style="width:600;overflow:visible;border:0;"><%=ea.getContent() %></textarea> </td>
					</tr>
      			</table>
      		</td>
        	<td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
		</tr>
		<tr> 
	<!--  
		<a HREF="../sms_send.htm">发送消息</a>
	-->
                    
			<td><img src="../images/k1_07.gif" width="2" height="5"></td>
	  		<td colspan="2" background="../images/k1_08.gif"> </td>
	  		<td><img src="../images/k1_09.gif"></td>
		</tr>
	</table>
</form> 
             
</body>
</html>
<%
	HibernateUtil.commitTransaction(); 
	HibernateUtil.closeSession();
%>