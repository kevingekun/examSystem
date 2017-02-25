<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>试题详细信息</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<%-- <script src="<%=request.getContextPath() %>/js/customer.js"></script> --%>
		<link href="<%=request.getContextPath() %>/css/all.css" rel="stylesheet" type="text/css">

</HEAD>	
<BODY > 
<%
String []arry=new String[]{"A","B","C","D","E","F"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);	
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr> 
		<td height="3" colspan="3"></td>
	</tr>
	<tr> 
		<td width="8"><img src="<%=request.getContextPath() %>/images/min_01.gif" width="8" height="32"></td>
		<td background="<%=request.getContextPath() %>/images/min_02.gif">
		<div align="center">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
			<td width="15"><strong><img src="<%=request.getContextPath() %>/images/ico/search.gif" width="16" height="16"></strong></td>
			<td><strong> &nbsp;录音信息</strong>
			<div align="left"></div></td>
			</tr>
		</table>
		</div>
		</td>
		<td width="8"><img src="<%=request.getContextPath() %>/images/min_03.gif" width="8" height="25"></td>
	</tr>
	<tr> 
		<td height="1" colspan="3"></td>
	</tr>
</table>   		
<table width=100% border=0 cellspadding=2 cellspacing =2 >
				<tr bgcolor=#ffffff>
					<td>
						<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
					
						<tr bgcolor="#ffffff">
							<td align=right width="25%">业务类型：</td> 
						  	<td align=left width="25%"s>
							 <s:property value="equestions.ebusinesstype.name"/>
							</td>
							<td align=right width="25%">重要性：</td>
					      	<td align=left width="25%"><s:property value="equestions.eimportance.name"/></td>
						</tr>


					</table>
					</td>
				</tr>
				
					</td>
				</tr>
			</table>
				<div align="center"><button class="BigButton" onclick="window.close()">关闭</button></div>
</BODY>
</HTML>
