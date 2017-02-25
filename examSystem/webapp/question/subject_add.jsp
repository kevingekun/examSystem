<%@page import="com.wondersgroup.kaoshi.bo.Tkcategory"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	ProfessionBean professionBean = new ProfessionBean();
	List<Tkcategory> list = professionBean.getYjTkcategories();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工种维护</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
	function sbt() {
		var gzmc = $("#gzmc").val();
		var gzid = $("#gzid").val();

		gzmc = gzmc.replace(/\s/g, "");
		gzid = gzid.replace(/\s/g, "");
		if (gzmc == "") {
			alert("请填写工种名称！");
			return;
		}
		if (gzid == "") {
			alert("请填写工种编号！");
			return;
		} else if (gzid.length != 7) {
			alert("工种编号应该为7位！");
			return;
		}
		var state = true;

		var url = 'gzCheck.action?gzmc=' + gzmc + '&gzid=' + gzid;
		url = encodeURI(encodeURI(url));
		$.ajax({
			type : 'post',
			async : false,
			url : url,
			success : function(result) {
				if (result == '01') {
					alert("工种编号已存在，请修改！");
					state = false;
					return;
				} else if (result == '02') {
					alert("工种名称已存在，请修改！");
					state = false;
					return;
				} else if (result == '012') {
					alert("工种编号和工种名称均已存在！");
					state = false;
					return;
				}
			},
			error : function() {
				alert("系统出错");
			}
		});
		if (state) {
			var url2 = 'gzAdd.action?gzmc=' + gzmc + '&gzid=' + gzid;
			url2 = encodeURI(encodeURI(url2));
			$.ajax({
				type : 'post',
				url : url2,
				success : function(result) {
					if (result == 'success') {
						alert("新增成功！");
						window.location.reload();
					} else {
						alert("新增失败！");
					}
				},
				error : function() {
					alert("系统出错！");
				}
			});
		}
	}
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td colspan="2" valign="top">
				<table width="99%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="8" colspan="4"></td>
					</tr>
					<form>
						<tr>
							<td height="6" colspan="6"></td>
						</tr>
						<tr>
							<td width="25%" height="28" align="right">工种名称：</td>
							<td width="25%" align="left"><input id="gzmc" name="gzmc"
								style="width: 150px;" /></td>
							<td width="25%" align="right">工种编号(7位数字)：</td>
							<td width="25%"><input id="gzid" name="gzid"
								style="width: 150px;" /></td>
						</tr>
						<tr>
							<td height="20" colspan="6"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="center"><button class="submit_2" type="button"
									onclick="sbt()">确定</button></td>
							<td align="center"><button class="submit_2" type="reset">重置</button></td>
							<td>&nbsp;</td>
						</tr>
					</form>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
