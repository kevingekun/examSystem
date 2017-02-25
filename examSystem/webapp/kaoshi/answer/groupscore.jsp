<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>
医保信息查询系统
</title>
<link href="../css.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td height="50"></td>
</tr>
<tr>
<td class="blacktitle" align=center>组间成绩报表</td>
</tr>
<tr>
<td height="10"></td>
</tr>

<tr>
<td height="20"></td>
</tr>
</table>
<form action='findAllGroupAnswerpaper.action' method="POST">
<table border="0" align="center" cellpadding="0" cellspacing="0" class=strongblack>

	<tr>	
		<td align=right height="35" style="width:120px;">起 始 日 期 ： </td>
		<td>
			<input type="text" value='<s:date name="startctime" format="yyyy-MM-dd HH:mm:ss"/>' style="width:139px" class="Wdate" name="startctime"  onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
		</td>
	</tr>
	<tr>	
		<td align=right style="width:120px;">结 束 日 期 ： </td>
		<td>
			<input type="text" value='<s:date name="startctime" format="yyyy-MM-dd HH:mm:ss"/>' style="width:139px" class="Wdate" name="startctime"  onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
	   <td></td>
       <td><input name="button" type="submit" class="submit_2"  value="查 询" /></td>
    </tr>          
</table>
</form>
</BODY>
</HTML>