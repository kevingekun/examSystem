<%@page import="java.util.List,com.wondersgroup.popedom.bo.ExamStaff"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>公告</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
</head>
<%--<%
List<ExamStaff> list=(List<ExamStaff>)request.getAttribute("infolist");
%>
--%>
<script>
	
</script>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">公告维护</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">修改考生须知</td>
												</tr>
											</table></td>
									</tr>
								</table> <s:form method="post" action="saveadd">
									<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<tr>
											<td width="5%" height="28" align="left">考生须知：</td>
											<td width="20%" align="left">
												<textarea rows="10" cols="55" name="adds.hzz092" id="hzz092" " ><c:out value="${adds.hzz092}"/></textarea>
												<%-- <input id="hzz092" name="adds.hzz092" value="<c:out value="${adds.hzz092}"/>"/> --%>
											</td>
											<!-- <td width="3%"></td> -->
										</tr>
										<tr height="8px"></tr>
										<tr>
											<td style="text-align: center;" width="100%" colspan="2">
												<div style="margin-left:auto;margin-right: auto;width: 100%">
													<input style="margin-left:80px" name="button_submit" type="submit" class="submit_2" value="确定" />
												</div>
											</td>
										</tr>
										<tr height="8px"></tr>
									</table>
								</s:form>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
