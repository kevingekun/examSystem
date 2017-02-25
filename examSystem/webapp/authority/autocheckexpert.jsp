<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>" />

<title>修改专家</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
function delet(hz92id){
	$.ajax({
		type:'post',
		url:'deleteuseofexpert.action?idof92='+hz92id,
		success:function(result){
			alert(result);
			window.location.reload();
		},
		error:function(){
			alert("程序出错！");
		}
	});
}
function replac(hz92id){
	$("#hz92id").val(hz92id);
	document.replace.submit();
}
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left"
					cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专家信息</td>
						<td class="header3" width="24"><img
							src="<%=request.getContextPath()%>/newimages/content_right_bj.gif "
							width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left"
												cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8">鉴定批次信息</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="1" align="center" cellpadding="0"
									cellspacing="0" class="table_list" id="tb">
									<tr class="title_font">
										<%-- <td width="6%" align="center" bgcolor="#C7E2F8"><span
											class="out">操作 </span></td> --%>
										<td width="11%" align="center" bgcolor="#C7E2F8"><span
											class="out">试卷名称</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">考场名称</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">专家姓名</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">联系电话</span></td>
										<td width="14%" align="center" bgcolor="#C7E2F8"><span
											class="out">擅长专业</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span
											class="out">职称</span></td>
										<%-- <td width="6%" align="center" bgcolor="#C7E2F8"><span
											class="out">操作 </span></td> --%>
									</tr>
									<s:iterator value="expertInfos" id="list" status="state">
										<tr onMouseOver="this.className='td_over'"
											onMouseOut="this.className=''" id='r1'>
											<%-- <td align='center' class='num_font'>
												<input type='button' value="替换" onclick="replac(<s:property value="#list.hz92id"/>)"/></td> --%>
											<td align='center' class='num_font'><s:property
													value="#list.sjmc" /></td>
											<td align='center' class='num_font'><s:property
													value="#list.kcmc" /></td>
											<td align='center' class='num_font'><s:property
													value="#list.expertname" /></td>
											<td align='center' class='num_font'><s:property
													value="#list.phone" /></td>
											<td align='center' class='num_font'><s:property
													value="#list.major" /></td>
											<td align='center' class='num_font'><s:property
													value="#list.zc" /></td>
											<%-- <td align='center' class='num_font'>
												<input type='button' value="删除" onclick="delet(<s:property value="#list.hz92id"/>)"/></td> --%>
										</tr>
									</s:iterator>
								</table>
								<%-- <c:if test="${expertInfos!=null}">
									<s:form action="arangeexpert.action" name="chaForm">
										<elile:navigateBar navigateform="navigateform"
											actionName="arangeexpert.action" formName="chaForm" />
									</s:form>
								</c:if> --%>
								<s:form action="replaceexpert.action" name="replace" >
									<input id="hz92id" name="idof92" type="hidden" />
								</s:form>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
