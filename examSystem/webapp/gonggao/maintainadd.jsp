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
	function addmgg() {
		var notice1 = $("#notice").val();
		var identifynumber1 = $("#identifynumber").val();
		$.ajax({
			type : 'post',
			async : false,
			url : 'maintains.action?notice=' + notice1 + '&identifynumber='
					+ identifynumber1,
			success : function(result) {
				if (result == "1") {
					alert("不存在!");
					return;

				}
			},
			error : function() {
				alert("error");
			}
		});

	}
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
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">新增考生须知</td>
												</tr>
											</table>
										</td>
									</tr>
								</table> 
									<s:form method="post" action="maintains.action">
									<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<tr>
										</tr>
										<tr>
											<td width="5%" height="28" align="left">试卷名称：</td>
											<td width="20%" align="left">
												<select id="sjmc" name="sjmcname" style="width: 240px">
													<option>请选择</option>
													<c:forEach var="m" items="${addlist}" varStatus="status">
														<option value="<c:out value="${m[0]}"/>">
															<c:out value="${m[1]}" />
														</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td width="5%" height="28" align="left">考生须知：</td>
											<td width="20%" align="left">
												<textarea rows="10" cols="50" name="notice" id="notice"></textarea>
											</td>
											<!-- <td width="3%"></td> -->
										</tr>
										<tr height="8px"></tr>
										<tr>
											<td style="text-align: center;" width="100%" colspan="2">
												<div style="margin-left:auto;margin-right: auto;width: 100%">
													<input name="button_submit" type="submit" class="submit_2" value="新增" />
												</div>
											</td>
										</tr>
										<tr height="8px"></tr>
									</table>
								</s:form>
							</td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>
