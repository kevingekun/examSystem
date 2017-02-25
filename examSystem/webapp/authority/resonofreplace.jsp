<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page
	import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@page import="com.wondersgroup.falcon.question.beans.*"%>

<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>

<html>
<head>
<title>替换信息</title>
<style>
.required {
	color: red;
}
</style>
<link href="<%=request.getContextPath()%>/newcss/style.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webapp/authority/js/utils.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
	function doreset() {

		document.duty.reset();

	}
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:20px; margin-left:10px; ">

		<tr>
			<td align="left"><table border="0" align="left" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专家替换信息</td>
						<td class="header3" width="24"><img
							src="<%=request.getContextPath()%>/newimages/content_right_bj.gif "
							width="24" height="22"></td>
					</tr>
				</table></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="borader3"><table width="100%" border="0">
									<tr>
										<td><table width="100%" border="0" align="left">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">替换信息</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<form id="duty" name="duty" method="post" action="rep.action">
									<%-- <input id="committeeId" name="committeeId" type="hidden" value="<c:out value="${committeeid}"/>" /> 
									<input id="KCID" name="KCID" type="hidden" value="<c:out value="${Kcid}"/>" />
									<input id="ZJID" name="ZJID" type="hidden" value="<c:out value="${zjId}"/>" /> --%>
									
									<input id="KCID" name="expertid" type="hidden" value="<c:out value="${expertid}"/>" />
									<input id="ZJID" name="idof92" type="hidden" value="<c:out value="${idof92}"/>" />
									<table width="99%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr align="center">
											<td height="28" align="right"><span class="required">*</span>本次担任的职务：</td>
											<td align="left"><input type="text" id="nduty" name="nduty"></td>
										</tr>										
										<tr>
											<td height="58" width="13%" align="right">替换原因：</td>
											<td width="18%" align="left"><textarea rows="3"
													cols="16" name="reson" id="reson"></textarea></td>
										</tr>
										<tr height="75">
											<td><div style="margin-left:400px">
													<input type="submit" class="submit_2" value="确定"
														name="button_submit" />
												</div></td>
											<td><div style="margin-left:70px">
													<input type="button" class="submit_2" value="重置"
														onclick="doreset()" name="button_reset" />
												</div></td>

										</tr>
									</table>
								</form>
</body>
</html>

