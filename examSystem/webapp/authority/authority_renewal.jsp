<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限更新</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.11.3.min.js"></script>
</head>
<script>
	function authorityRenewal(){
		$.ajax({
			type:'post',
			url:'authorityRenewal.action',
			success:function(result){
				alert(result);
				window.parent.parent.location.reload();
			},
			error:function(){
				alert("程序出错！");	
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
						<td class="header2">权限更新</td>
						<td class="header3"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" align="center" valign="top">
				<div id="content1" class="borader">
					<input type="submit" name="button_editfile" value="更新权限" class="submit_2" onclick="authorityRenewal()"/>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
