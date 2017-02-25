<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@page import="com.wondersgroup.falcon.question.beans.*"%>

<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>

<html>
<head>
<title>专家新增</title>
<style>
.required {
	color: red;
}
</style>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/webapp/authority/js/utils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:20px; margin-left:10px; ">

		<tr>
			<td align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">详细信息</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0">
									<tr>
										<td><table width="100%" border="0" align="left">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">专家详细信息</td>
												</tr>
											</table></td>
									</tr>
								</table>


								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width="13%" align="right">专家基本信息ID：</td>
										<td width="18%" align="left"><input id="eid" name="expert.eid" disabled="true" value="<c:out value="${expertinfo1.hzz001}"/>"></td>
										<td align="right" height="28">证件类型：</td>
										<td align="left"><select id="idstyle" name="expert.idstyle" style="width: 130px" disabled="true">
												<option value="0" <c:if test="${expertinfo1.idstyle=='0'}" >selected </c:if>>请选择</option>
												<option value="1" <c:if test="${expertinfo1.idstyle=='1'}" >selected </c:if>>身份证</option>
												<option value="2" <c:if test="${expertinfo1.idstyle=='2'}" >selected </c:if>>解放军证</option>
												<option value="3" <c:if test="${expertinfo1.idstyle=='3'}" >selected </c:if>>身份证</option>
												<option value="4" <c:if test="${expertinfo1.idstyle=='4'}" >selected </c:if>>护照</option>
										</select></td>

										<td height="28" align="right">证件号码：</td>
										<td align="left"><input type="text" id="idnumber" name="expert.idnumber" disabled="true" value="<c:out value="${expertinfo1.idnumber}"/>"></td>

									</tr>
									<tr height="28">
										<td align="right">专家姓名：</td>
										<td align="left"><input type="text" id="name" disabled="true" value="<c:out value="${expertinfo1.name}"/>"></td>
										<td width="13%" align="right" height="28">性别：</td>
										<td><input type="text" id="displaysex" disabled="true" name="displaysex" value='<c:if test="${expertinfo1.sex=='1'}">男</c:if><c:if test="${expertinfo1.sex=='2'}">女</c:if>'></td>
										<td width="13%" align="right">出生日期：</td>
										<td width="18%" align="left"><input type="text" id="date" name="expert.date" disabled="true" value="<c:out value="${expertinfo1.date}"/>"></td>

									</tr>
									<tr height="28">
										<td width="13%" align="right">学历：</td>
										<td><select id="education" name="expert.education" style="width: 130px" disabled="true">
												<option value="0" <c:if test="${expertinfo1.education=='0'}" >selected </c:if>>请选择</option>
												<option value="1" <c:if test="${expertinfo1.education=='1'}" >selected </c:if>>博士研究生</option>
												<option value="2" <c:if test="${expertinfo1.education=='2'}" >selected </c:if>>硕士</option>
												<option value="3" <c:if test="${expertinfo1.education=='3'}" >selected </c:if>>本科</option>
												<option value="4" <c:if test="${expertinfo1.education=='4'}" >selected </c:if>>专科</option>
										</select></td>
										<td width="13%" align="right">所属单位：</td>
										<td width="18%" align="left"><input type="text" id="org" name="expert.org" disabled="true" value="<c:out value="${expertinfo1.org}"/>"></td>
										<td width="13%" align="right">审核状态：</td>
										<td width="18%" align="left"><input type="text" id="state" name="expert.state" disabled="true" value="<c:out value="${expertinfo1.state}"/>"></td>
									</tr>
									<tr height="28">
										<td width="13%" align="right">职称：</td>
										<td width="18%" align="left"><input type="text" id="jtitle" name="expert.jtitle" disabled="true" value="<c:out value="${expertinfo1.jtitle}"/>"></td>
										<td width="13%" align="right">擅长专业：</td>
										<td width="18%" align="left"><input type="text" id="major" name="expert.major" disabled="true" value="<c:out value="${expertinfo1.major}"/>"></td>
										<td width="13%" align="right">住址：</td>
										<td width="18%" align="left"><input type="text" id="address" name="expert.eaddress" disabled="true" value="<c:out value="${expertinfo1.eaddress}"/>"></td>

									</tr>
									<tr height="28">
										<td width="13%" align="right">联系电话：</td>
										<td width="18%" align="left"><input type="text" id="phone" name="expert.phone" disabled="true" value="<c:out value="${expertinfo1.phone}"/>"></td>
										<td width="13%" align="right">职务：</td>
										<td width="18%" align="left"><input type="text" id="duty" name="experts.duty" disabled="true" value="<c:out value="${expertinfo2.duty}"/>"></td>
										<td width="13%" align="right">安排频次：</td>
										<td width="18%" align="left"><input type="text" id="frequency" name="experts.frequency" disabled="true" value="<c:out value="${expertinfo2.frequency}"/>"></td>
									</tr>
									<tr height="28">
										<td width="13%" align="right">命题次数：</td>
										<td width="18%" align="left"><input type="text" id="count" name="experts.count" disabled="true" value="<c:out value="${expertinfo2.count}"/>"></td>
										<%--<td width="13%" align="right">评价等级：</td>
	            <td width="18%" align="left"    >
	             <input type="text" id="order" name="expert.order"   disabled="true"    value="<c:out value="${expertinfo1.order}"/>"  >
	             </td>
			   ---%>
										<td width="13%" align="right">委员会名称：</td>
										<td width="18%" align="left"><input type="text" id="committee" name="experts.committee" disabled="true" value="<c:out value="${expertinfo2.committee}"/>"></td>
										<td width="13%" align="right">是否超龄：</td>
										<td><select id="overage" name="expert.overage" style="width: 130px" disabled="true">
												<option value="0" <c:if test="${expertinfo1.overage=='0'}" >selected </c:if>></option>
												<option value="1" <c:if test="${expertinfo1.overage=='1'}" >selected </c:if>>是</option>
												<option value="2" <c:if test="${expertinfo1.overage=='2'}" >selected </c:if>>否</option>
										</select></td>
									</tr>
									<tr height="28">
										<td width="13%" align="right">有效标示：</td>
										<td width="18%" align="left"><input type="text" id="indicate" name="experts.indicate" disabled="true" value="<c:out value="${expertinfo2.indicate}"/>"></td>
										<td width="13%" align="right">组别：</td>
										<td width="18%" align="left"><input type="text" id="group" name="experts.group" disabled="true" value="<c:out value="${expertinfo2.group}"/>"></td>
										<td align="right">专家类别：</td>
										<td><input type="text" id="hzz911" name="hzz911" disabled="true" value="<c:out value="${expertinfo3}"/>"></td>
									</tr>
									<tr height="28px">
										<td width="13%" align="right">备注：</td>
										<td width="18%" align="left"><input name="experts.remark" id="remark" disabled="true" value="<c:out value="${expertinfo2.remark}"/>"></td>
									</tr>
									<tr height="8px"></tr>
								</table>
							</td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>

