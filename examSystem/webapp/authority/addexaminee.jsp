<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>人员新增</title>
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<base target="_self">
<script type="text/javascript">
	function closewindow() {
		var inputpassword = document.getElementById("inputpassword").value;
		var confirmpassword = document.getElementById("confirmpassword").value;
		if (inputpassword == confirmpassword) {
			document.getElementById("form1").submit();

		} else {
			alert("输入密码和确认密码不一致！");
		}
	}

	function isidrepeat() {
		var id = document.getElementById("username").value; //证件号码
		$.ajax({
			type : 'post',
			async : false,
			url : 'queryuser.action?username=' + id,
			success : function(result) {
				if (result == "1") {
					alert("该身份证号已经存在,请勿重复添加!");
					return;
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
	
	function roleChange(v){
		var t = v.value;
		if(t=='263'){
			$("#team").css("display","block");
		}else{
			$("#team").find("option[text='请选择']").attr("selected",true);
			$("#team").css("display","none");
		}
	}
</script>
</head>
<body class="nrbj">
	<form id="form1" name="addgg" method="post" action="addexaminee.action">
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
			<tr>
				<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="middle" class="header1"></td>
							<td class="header2">添加人员信息</td>
							<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
						</tr>
					</table></td>
				<td width="53%" align="left"></td>
			</tr>
			<tr>
				<td colspan="2" valign="top"><div id="content1" class="borader">
						<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="40%" height="28" align="right">身份证号：</td>
								<td width="79%" align="left"><input type=text id="username" name="euser.username" onblur="isidrepeat()"></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">姓名：</td>
								<td width="79%" align="left"><input type=text id="realname" name="euser.realname"></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">输入密码：</td>
								<td width="79%" align=""left""><input type=password id="inputpassword" name="inputpassword"></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">确认密码：</td>
								<td width="79%" align="left"><input type=password id="confirmpassword" name="euser.password""></td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right">角色：</td>
								<td width="20%" align="left">
									<select id="role" name="role" style="width: 140px" onchange="roleChange(this)">
										<c:forEach var="m" items="${addlist}" varStatus="status">
											<option value="<c:out value="${m[0]}"/>">
												<c:out value="${m[1]}" />
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td width="40%" height="28" align="right"></td>
								<td width="20%" align="left">
									<div id="team" style="display:none;margin-left: -60;">
										所属考点：
										<select id="teamId" name="euser.color" style="width: 140px">
											<option value="">请选择</option>
											<c:forEach var="m" items="${uTeams}" varStatus="status">
												<option value="<c:out value="${m.teamId}"/>">
													<c:out value="${m.teamName}" />
												</option>
											</c:forEach>
										</select>
									</div>
								</td>
							</tr>

							<tr>
								<td height="3" height="28" colspan="2" align="center"><input type="button" value="提 交" class="submit_2" onclick="closewindow()"></td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>