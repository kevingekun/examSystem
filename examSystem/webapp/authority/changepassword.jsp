<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>"/>

<title>密码修改</title>

<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>

<link href="inc/all.css" rel="stylesheet" type="text/css"/>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.pass{
	width: 70px;
	float: left;
	text-align: right;
}
.inpt{
	width: 140px;
	float: left
}
.div{
	margin: 10px auto 1px auto; 
	width: 210px
}
</style>
<script type="text/javascript">
var state = false;
function checkpass(){
	var pass = $("#vpass").val();
	$.ajax({
		type: 'post',
		url: 'checkPassword.action?vpass='+pass,
		success:function(result){
			if(result==1){
				state = true;
			}else{
				state = false;
				alert("原始密码错误！");
			}
		},
		error:function(){
			alert("程序出错！");
		}
	});
}
function sub(){
	var vpass = $("#vpass").val();
	var npass = $("#npass").val();
	var rnpass = $("#rnpass").val();
	if(vpass==""||npass==""||rnpass==""){
		alert("不能为空！");
		return false;
	}
	if(npass!=rnpass){
		alert("两次密码不一致！");
		return false;
	}
	if(state=false){
		return false;
	}
	$.ajax({
		type: 'post',
		async: false,
		url: 'updateUser.action?npass='+npass+'&vpass='+vpass,
		success:function(result){
			alert(result);
		},
		error:function(){
			alert("程序出错！");
		}
	});
	document.getElementById("form1").reset();
}
</script>
</head>

<body style="text-align: center;">
	<form id="form1">
		<table width="99%" border="0" align="right" cellpadding="0"
			cellspacing="0" style="margin-top:10px; margin-left:8px; ">
			<tr>
				<td width="45%" align="left">
					<table border="0" align="left" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="middle" class="header1"></td>
							<td class="header2">密码修改</td>
							<td class="header3" width="24"><img
								src="<%=request.getContextPath()%>/newimages/content_right_bj.gif "
								width="24" height="22"/></td>
						</tr>
					</table></td>
				<td width="53%" align="left"></td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div id="content1" class="borader">
						<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr align="center">
								<td width="100%">
									<div class="div">
										<div class="pass">原密码：</div>
										<div class="inpt">
											<input id="vpass" name="vpass" type="password" onblur="checkpass()"/>
										</div>
									</div>
								</td>
							</tr>
							<tr align="center">
								<td width="100%">
									<div class="div">
										<div class="pass">新密码：</div>
										<div class="inpt">
											<input id="npass" name="npass" type="password" />
										</div>
									</div>
								</td>
							</tr>
							<tr align="center">
								<td width="100%">
									<div class="div">
										<div class="pass">确认密码：</div>
										<div class="inpt">
											<input id="rnpass" name="rnpass" type="password" />
										</div>
									</div>
								</td>
							</tr>
							<tr align="center">
								<td width="100%">
									<div class="div">
										<input type="button" class="submit_2" value="提 交" onclick="sub()"/>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
