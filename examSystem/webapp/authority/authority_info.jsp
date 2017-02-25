<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人员分组</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />

</head>
<script type="text/JavaScript">

	function add(){
   		window.location.href="<%=request.getContextPath()%>/authority/authority_add.jsp";
	}
	function mod() {
		var row = 0;
		var t = window.document.getElementsByName('dealid');
		for ( var i = 0; i < t.length; i++) {
			if (t[i].type == 'checkbox' && t[i].checked == true) {
				row = row + 1;
			}
		}
		if (row < 1) {
			alert("请选择要修改的记录！");
			return false;
		}
		if (row > 1) {
			alert("请选择一个记录进行修改！");
			return false;
		}
		document.aForm.action = "authority_mod.jsp";
		document.aForm.myaction.value = "mod";
		document.aForm.submit();
	}
	function del() {
		var row = 0;
		var t = window.document.getElementsByName('deleEauthories');
		for ( var i = 0; i < t.length; i++) {
			if (t[i].type == 'checkbox' && t[i].checked == true) {
				row = row + 1;
			}
		}
		if (row < 1) {
			alert("请选择要删除的记录！");
			return false;
		}
		var tt = confirm("确定要删除吗？"); //确认是否删除
		if (tt) {
			document.aForm.submit();
		} else {
			return false;
		}

	}
</script>
<body class="nrbj">
	<%-- <s:form action="deleteEauthority" name="aForm" method="POST"> --%>
		<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
			<tr>
				<td width="45%" align="left">
					<table border="0" align="left" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="middle" class="header1"></td>
							<td class="header2">角色管理</td>
							<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
						</tr>
					</table>
				</td>
				<td width="53%" align="left"></td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div id="content1" class="borader">
						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
							<tr>
								<td class="borader3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
													<tr>
														<td align="left" valign="middle" class="header7"></td>
														<td class="header8">角色列表</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<s:form action="deleteEauthority" name="aForm" method="POST">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
										<tr class="title_font">
											<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">选择 </span></td>
											<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
											<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">角色名称</span></td>
											<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">说明</span></td>
											<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
										</tr>
										<s:iterator value="eauthoritys" id="eauthority" status="state">
											<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
												<td align='center' class='num_font'><input type='checkbox' name='deleEauthories' value='<s:property value="#eauthority.id"/>'></td>
												<td align='center' class='num_font'><s:property value="#state.index+1" /></td>
												<td align='center' class='num_font'><s:property value="#eauthority.name" /></td>
												<td align='center' class='num_font'><s:property value="#eauthority.description" /></td>
												<td align='center' class='num_font'><a href='toupdateEauthority.action?authorityid=<s:property value="#eauthority.id"/>'>修改</a> <a href='userMeanuTreeView.action?authorityid=<s:property value="#eauthority.id"/>'>菜单授权</a></td>
											</tr>
										</s:iterator>
									</table>
									</s:form>
									<s:form action="eauthority.action" name="aForm1" method="post">
										<elile:navigateBar navigateform="navigateform" actionName="eauthority.action" formName="aForm1" />
									</s:form>
							</tr>
						</table>
						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
							<tr>
								<td align="center">
									<input type="button" class="submit_2" name="button_editfile" value="新增" onclick="add()"> 
									<input type="button" class="submit_2" name="button_editfile2" value="删除" onclick="del()">
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	<%-- </s:form> --%>
	
</body>
</html>
