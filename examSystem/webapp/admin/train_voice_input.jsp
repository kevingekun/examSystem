<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.Date"%>
<jsp:useBean id="authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
<%@page import="com.wondersgroup.falcon.acegi.AcegiUtil,java.util.List"%>
<%@page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
<%@page import="com.wondersgroup.falcon.model.auth.User" %>
<%@page import="com.wondersgroup.falcon.beans.common.CommFunc" %>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<% 
	User users = ((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser(); 
	Date date = new Date();
	String datestring = CommFunc.convertDT(date);
%>
<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script>
		function addVoice(){
			window.open("train_voice_newwindow.jsp");
		}
		
		function checkSubmit(){
			if(document.trainfileform.title.value==""){
				alert("请填写标题！");
				return ;
			}
			if(document.trainfileform.content.value==""){
				alert("请填写内容！");
				return ;
			}			
			if(document.trainfileform.voice.value==""){
				alert("请选择一个录音文件！");
				return ;
			}				
			document.trainfileform.submit();
			
		}
	</script>
	<BODY >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td height="3" colspan="3"></td>
		</tr>
		<tr> 
			<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
			<td background="../images/min_02.gif"><div align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
						<td><strong> &nbsp;系统反馈提交</strong> 
		 				<div align="left"></div></td>
					</tr>
				</table>
			</div>
			<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
		</tr>
	 	<tr> 
	    	<td height="5" colspan="3"></td>
	  	</tr>
	</table>
	<% 
	%>
	<form name="trainfileform" method="post" action="<%=request.getContextPath() %>/trainfileservlet" >
	<input type=hidden name=actionType value="addVoice">
	<input type=hidden name="sender" value="<%=users.getUsername()%>">
	<input type=hidden name="type" value="1">
	<table width="100%" height="200" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
	<tr><td bgcolor="#FFFFFF">反馈提交区：</td></tr>
	<tr> 
		<td bgcolor="#FFFFFF" height="495" valign=top align=center>
			<table width="80%" border="0" align="center" cellpadding="1" cellspacing="2">
				<tr height=50><td colspan=2></td></tr>								
				<tr>
	  				<td align="right" >标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
	  				<td>
	  					<input type="text" name = "title" size=90 >
	  				</td>
				</tr>
				<tr id="fujian" >
						<td align=right>录&nbsp;&nbsp;&nbsp;&nbsp;音：</td>	
						<td >
							<input type=text name=voice value="">
							&nbsp;&nbsp;
							<a href=# onclick="addVoice()">添加录音</a>
						</td>	
					</tr>				
				<tr>
	  				<td align="right" >内&nbsp;&nbsp;&nbsp;&nbsp;容：</td><td><TEXTAREA id=content name=content style = "width:553;height:200"></TEXTAREA></td>
				</tr>
				<tr><td colspan=2 align=center><input type="button" class="SmallButton" onclick="checkSubmit()" value="提交" ></td></tr>
				<tr><td height="55"></td></tr>
			</table>
		</td>
	</tr>
</table> 
	</form>   
	</BODY>
</HTML>
<% 
	//HibernateUtil.closeSession();
%>
