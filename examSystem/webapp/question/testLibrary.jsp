<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>题库维护</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<!-- base library -->
<link rel="stylesheet" type="text/css" href="js/editgrid/resc/ext-all.css" />
<!-- overrides to base library -->

<!-- page specific -->
<!-- <link rel="stylesheet" type="text/css" href="resc/examples.css" />
<link rel="stylesheet" type="text/css" href="resc/grid-examples.css" /> -->

<!-- ** Javascript ** -->
<!-- ExtJS library: base/adapter -->
<script type="text/javascript" src="js/editgrid/resc/ext-base.js"></script>

<!-- ExtJS library: all widgets -->
<script type="text/javascript" src="js/editgrid/resc/ext-all.js"></script>

<!-- overrides to base library -->

<!-- extensions -->
<script type="text/javascript" src="js/editgrid/resc/CheckColumn.js"></script>
<!-- page specific -->
<script type="text/javascript" src="js/editgrid/resc/edit-grid.js"></script>

</head>
<script>
	function checkGz(){
		var jobname = document.getElementById("gzmc").value;
		var ret = false;
		if(jobname==''){
			alert("请输入工种名称");
			ret = false;
			return ret;
		}
		jobname = encodeURI(encodeURI(jobname));
		$.ajax({
			type:'post',
			async:false,
			url:'findProfessionByName.action?jobname='+jobname,
			success:function(result){
				if(result=='0'){
					alert("工种不存在");
					ret =  false;
				}else{
					document.getElementById("id_job").value=result;
					ret =  true;
				}
			},
			error:function(){
				alert("error");
				ret =  false;
			}
		});
		return ret;
	}
	function checkDj(){
		var jobname = document.getElementById("gzmc").value;
		var dj = document.getElementById("dj").value;
		jobname = encodeURI(encodeURI(jobname));
		dj = encodeURI(encodeURI(dj));
		var ret = false;
		if(dj=='0'){
			alert("请选择等级");
			ret =  false;
			return ret;
		}else{
			$.ajax({
				type:'post',
				async:false,
				url:'findTkByjobnameAndDj.action?jobname='+jobname+'&dj='+dj,
				success:function(result){
					if(result=='1'){
						alert("题库已经存在！");
						ret =  false;
					}else{
						ret =  true;
					}
				},
				error:function(){
					alert("error");
					ret =  false;
				}
			});
		}
		return ret;
	}
</script>

<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left"
					cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">题库管理</td>
						<td class="header3" width="24"><img
							src="<%=request.getContextPath() %>/newimages/content_right_bj.gif "
							width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
			<div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left"
												cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">新增题库</td>
												</tr>
											</table></td>
									</tr>
								</table> 
								<s:form method="post" action="userview.action" name="lookup">
									<table width="99%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="8" colspan="4"></td>
										</tr>
										<tr>
											<input id="id_job" type="hidden" />
											<td width="13%" height="28" align="right">工种名称：</td>
											<td width="18%" align="left">
												<s:textfield id="gzmc" name="gz" onblur="checkGz()" ></s:textfield>
											</td>
											<td width="13%" align="right">等级：</td>
											<td width="18%">
												<select id="dj" name="grade" style="width: 100px;" onchange="checkDj()">
								    				<option value="0">请选择</option>
								    				<option value="一级">一级</option>
								    				<option value="二级">二级</option>
								    				<option value="三级">三级</option>
								    				<option value="四级">四级</option>
								    				<option value="五级">五级</option>
								    				<option value="专项">专项</option>
								    			</select>
											</td>
										</tr>
									</table>
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<!-- <tr>
											<td align="center">
												<input name="button_editfile" type="submit" class="submit_2" value="查 询" />
											</td>
										</tr> -->
										<tr>
											<td height="8"></td>
										</tr>
									</table>
								</s:form></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="editor-grid"></div>
			</td>
		</tr>
	</table>
</body>

</html>
