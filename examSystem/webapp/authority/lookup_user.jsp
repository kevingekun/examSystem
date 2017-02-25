<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
</head>
<script>
	function adduser() {
		var oBol = /[^\d\.]/g.test(adduser_form.id.value);
		//window.alert(oBol ? '错误' : '正确');
		if (oBol)
			alert("ID 字段只能输入数字");
		else
			adduser_form.submit();
	}

	function setdisVisible(id) {
		setdisvisible_form.userid.value = id;
		setdisvisible_form.submit();
	}

	function edituser(id) {
		edituser_form.userid.value = id;
		edituser_form.submit();
	}
	function individualdistribute(id) {
		individualdistribute_form.userid.value = id;
		individualdistribute_form.submit();
	}
	function openAdd() {
		document.addrole.submit();

		// var return_val = window.showModalDialog("authority/addexaminee.jsp","","dialogWidth=800px;dialogHeight=500px;center:yes;scroll:no;status:no");
		//if(return_val == "refresh")
		//window.location.reload();

	}
</script>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">人员管理</td>
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
													<td class="header8">查询条件</td>
												</tr>
											</table></td>
									</tr>
								</table> <s:form method="post" action="userview.action" name="lookup">
									<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="8" colspan="7"></td>
										</tr>
										<tr>
											<td width="13%" height="28" align="right">用户名：</td>
											<td width="18%" align="left"><s:textfield name="username" size="20" /></td>
											<td width="13%" align="right">人员姓名：</td>
											<td width="18%"><s:textfield name="realname" size="20" /></td>
											<td width="13%" align="right">帐户状态：</td>
											<td width="18%">
											<select name="userstar">
													<option value="" >请选择</option>
													<option value="0" <s:if test="userstar==0">selected="selected"</s:if>>业务用户</option>
													<!-- <option value="1" <s:if test="userstar==1">selected="selected"</s:if>>考生</option>
													<option value="2" <s:if test="userstar==2">selected="selected"</s:if>>考场</option>
													<option value="3" <s:if test="userstar==3">selected="selected"</s:if>>考点</option> -->
											</select> <%-- <s:select list="userState" name="enabled" listKey="key" listValue="value" headerKey="0" headerValue="--全部--">
                      		</s:select> --%></td>
											<td>&nbsp;</td>
										</tr>
									</table>
									<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<tr>
											<td align="center"><input name="button_editfile" type="submit" class="submit_2" value="查 询" /></td>
										</tr>
										<tr height="8px"></tr>
									</table>
								</s:form></td>
						</tr>
					</table>

					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8">查询结果</td>
													<td class="header8" width="90%"><input name="button_editfile" type="button" class="submit_2" value="人员新增" onclick="openAdd()" /></td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">用户名 </span></td>
										<td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">姓名</span></td>
										<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">帐户状态</span></td>
										<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
									</tr>
									<s:iterator value="users" id="user" status="state">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><s:property value="#user.username" /></td>
											<td align='center' class='num_font'><s:property value="#user.realname" /></td>
											<td align='center' class='num_font'><s:if test="#user.enabled==1">
										正常
									</s:if> <s:elseif test="#user.enabled==2">
										锁定
									</s:elseif> <s:elseif test="#user.enabled==3">
										不可用
									</s:elseif></td>
											<td align="center"><a href="viewAuthUser.action?userid=<s:property value='#user.id'/>">角色分配</a></td>
										</tr>


									</s:iterator>
								</table> 
								<form action="userview.action" name="userViewForm" style="margin-top: 6px;">
									<s:hidden name="username"></s:hidden>
									<s:hidden name="realname"></s:hidden>
									<s:hidden name="enabled"></s:hidden>
									<s:hidden name="userstar"></s:hidden>
									<elile:navigateBar navigateform="navigateform" actionName="userview.action" formName="userViewForm" />
								</form>
								<s:form action="addrole.action" name="addrole" method="post">

								</s:form></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>
