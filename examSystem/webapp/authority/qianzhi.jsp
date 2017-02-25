<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.Util.*,java.util.*"%>
<%@page import="com.wondersgroup.popedom.bo.EUser"%>
<%@page import="com.wondersgroup.popedom.dao.EuserDao"%>
<%
	String userid = request.getParameter("userid");
	EuserDao eDao = new EuserDao();
	EUser user = eDao.getUserByID(userid);
	String examid = request.getParameter("examid");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>强制交卷</title>
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var returnObj = new Object();
returnObj.code = "false";
returnObj.text = "fou";
window.returnValue = returnObj;
function closewindow()
{ 
 	var userid =document.getElementById("userid").value;
 	var examid =document.getElementById("examid").value;
 	var cheatflag =document.getElementById("cheatflag").value;
 	var flag =document.getElementById("flag").value;
 	var remarks =document.getElementById("remarks").value;
 	var sUrl = '<%=request.getContextPath()%>/updateqiangzhi.action?userid='+ userid+ 
 			'&examid='+ examid+ '&cheatflag='+ cheatflag+ '&flag=' + flag + '&remarks=' + remarks;
	var oRequest = new XMLHttpRequest();
	oRequest.onreadystatechange = function() {
		if (oRequest.readyState == 4) {
			submitform();
		}
	}
	oRequest.open('POST', sUrl);
	oRequest.send(null);
}

function submitform() {
	returnObj.code = "true";
	returnObj.text = "shi";
	window.close();
}
</script>
</head>
<body class="nrbj">
	<s:form id="form1" action="updateqiangzhi.action" method="post" name="popedom">
		<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
			<tr>
				<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="middle" class="header1"></td>
							<td class="header2">强制交卷信息</td>
							<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
						</tr>
					</table></td>
				<td width="53%" align="left"></td>
			</tr>

			<tr>
				<input type="hidden" name="examid" id="examid" value="<%=examid%>">
				<input type="hidden" name="userid" id="userid" value="<%=userid%>">
				<td colspan="2" valign="top">
					<div id="content1" class="borader">
						<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td align="right" name="password">准考证号：</td>
								<td><%=user.getPassword()%></td>
							</tr>
							<tr height="8px"></tr>
							<tr>
								<td align="right" name="username">姓名：</td>
								<td><%=user.getRealname()%></td>
							</tr>
							<tr height="8px"></tr>
							<tr>
								<td align="right">是否作弊：</td>
								<td><select style="width:70px" id="cheatflag" name="cheatflag">
										<option value="0">&nbsp;&nbsp;否&nbsp;&nbsp;</option>
										<option value="1">&nbsp;&nbsp;是&nbsp;&nbsp;</option>
								</select></td>
							</tr>
							<tr height="8px"></tr>
							<tr>
								<td align="right">是否清零：</td>
								<td><select style="width:70px" name="flag" id="flag">
										<option value="0">&nbsp;&nbsp;否&nbsp;&nbsp;</option>
										<option value="1">&nbsp;&nbsp;是&nbsp;&nbsp;</option>
								</select></td>
							</tr>
							<tr height="8px"></tr>
							<tr>
								<td align="right">备&nbsp;&nbsp;&nbsp;注：</td>
								<td><textarea rows="3" cols="18" id="remarks" name="remarks" style="width:200;height:50"></textarea></td>
							</tr>
							<tr height="8px"></tr>
							<tr>
								<table width="49" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input type="button" value="提 交" onclick="closewindow()" style="padding: 3px;"></td>
									</tr>
								</table>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>