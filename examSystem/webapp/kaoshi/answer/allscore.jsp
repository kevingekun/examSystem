<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="java.util.List,com.wondersgroup.kaoshi.bo.EKaoshiGroup" %>
<%@page import="com.wondersgroup.falcon.question.beans.EBusinesstypeService"%>
<%EBusinesstypeService  eBusinesstypeService =new EBusinesstypeService(); 
	List<EKaoshiGroup> groupList=(List<EKaoshiGroup>)eBusinesstypeService.queryGroupList();
	request.setAttribute("groupList",groupList);
%>
<html>
<head>
<title>
全体成绩报表
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
<td class="blacktitle" align=center>全体成绩报表</td>
</tr>
<tr>
<td height="10"></td>
</tr>

<tr>
<td height="20"></td>
</tr>
</table>
<form action="findAllAnswerpaper.action" method="POST">
<table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="8" colspan="7"></td>
    </tr>
    <tr>
   		<td height="35" align="right">小 组 名 称 ：</td>
   		<td align="left" style="width:120px;">
				<select name="paperid"  style="width:139px">
					<option value="">请选择</option>
					<c:forEach items="${groupList}" var="group">
						<option value='<c:out value="${group.id}" />'><c:out value="${group.name}" /></option>
					</c:forEach>
				</select>
			</td>
    </tr>
	<tr>	
		<td  height="35">起 始 日 期 ： </td>
		<td style="width:120px;">
			<input type="text" name="startctime"  style="width:139px" class="Wdate" name="startctime"  onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
		</td>
	</tr>
	<tr>	
		<td align=right>结 束 日 期 ： </td>
		<td style="width:120px;">
			<input type="text" name="endctime"  style="width:139px" class="Wdate" name="startctime"  onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>

	<tr>
	   <td></td>
       <td><input name="button" type="submit" class="submit_2" onclick=""  value="查 询" /></td>
    </tr>    
</table>
</form>
</BODY>
</HTML>
