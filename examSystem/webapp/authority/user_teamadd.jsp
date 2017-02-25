<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加考点信息</title>
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<base target="_self">
<script type="text/javascript">
	var check = false;
	function isTeamNameRepeat() {
		var tname = document.getElementById("teamName").value;
		tname = encodeURI(encodeURI(tname));
		$.ajax({
			type : 'post',
			async: false,
			url : 'chekcTeamByName.action?teamName=' + tname,
			success : function(result) {
				if (result == "success") {
					alert("该考点名称已经存在,请勿重复添加！");
					check = false;
					return;
				}else if(result=="notFound"){
					check = true;
					return;
				}
			},
			error : function() {
				check = false;
				alert("程序出错！");
			}
		});
	}
	function checkSubmit(){
		isTeamNameRepeat();
		if(check){
			$("#sbt").attr("disabled",true);
			document.addTeam.submit();
		}else{
			alert("请检查考点名称是否正确！");
		}
	}
</script>
</head>
<body class="nrbj">
	<form id="form1" name="addTeam" method="post" action="addTeam.action">
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
			<tr>
				<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="middle" class="header1"></td>
							<td class="header2">添加考点信息</td>
							<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
						</tr>
					</table></td>
				<td width="53%" align="left"></td>
			</tr>
			<tr>
				<td colspan="2" valign="top"><div id="content1" class="borader">
						<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="40%" height="28" align="right">考点名称：</td>
								<td width="79%" align="left">
									<input type=text id="teamName" name="userteam.teamName" onblur="isTeamNameRepeat()">
									<font color="#FF0000" >*</font>
								</td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">联系人姓名：</td>
								<td width="79%" align="left"><input type=text id="contactname" name="userteam.contactname"></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">联系人电话：</td>
								<td width="79%" align=""left""><input type=text id="contactph" name="userteam.contactph"></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">考点地址：</td>
								<td width="79%" align="left"><input type=text id="teamaddress" name="userteam.teamaddress"></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">考点描述：</td>
								<td width="79%" align="left"><input type=text id="description" name="userteam.description"></td>
							</tr>
							<tr>
								<td height="3" height="28" colspan="2" align="center">
									<input id="sbt" type="button" value="提 交" class="submit_2" onclick="checkSubmit()">
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>