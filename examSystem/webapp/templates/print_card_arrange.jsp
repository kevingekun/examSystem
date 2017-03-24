<%@page import="com.wondersgroup.kaoshi.bo.Admission_card_pc"%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
 
 List<Admission_card_pc> acpList = (List<Admission_card_pc>)request.getAttribute("acpList");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>考试信息与考生关联</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js" ></script>

<script type="text/javascript">
function selectall(){
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("checkedid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}
function sbt(){
    var row =0;
    var checkedid="";
	var values = document.getElementsByName("checkedid");
	var sjid = $("#paper").val();
	if(sjid==""){
		alert("请选择考试批次！");
	}else{
		for (var i = 0; i < values.length; i++){
	       	if(values[i].checked == true){
	       		checkedid+=values[i].value+",";
	       	   row++;
	       	}
	    }
	    if(row>0)
	    {
	    	checkedid=checkedid.substring(0,checkedid.length-1);
			document.aForm.action="relateksAndkspc.action?checkid="+checkedid;
			document.aForm.submit();
	    }else{
	    	alert("请选择考生批次！");
	    }
	}
}

function checkPaper(v){
	$("#sjid").val(v);
}
function alreadyRelated(){
	window.open ("<%=request.getContextPath() %>/authority/alreadyRelated_printCard.jsp", "newwindow", "height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
}
function deletejdpc(jdid){
    var checkedid=jdid;
	    	$.ajax({
				type:'post',
				async: false,
				url:'deletePrintCard.action?checkid='+checkedid,
				success:function(result){
					if(result=="success"){
						alert("删除成功");
						window.location.reload();
					}else if(result=="error"){
						alert("删除失败！");
					}
				},
				error:function(){
					alert("系统出错，请稍后再试！");			
				}
			});
}
function detailjdpc(jdid){
	window.open ("<%=request.getContextPath() %>/authority/detail_printcard.jsp?jdid="+jdid, "newwindow", "height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
}
</script>
<script type="text/javascript">
<%
	String info = (String)request.getAttribute("info");
	if(info=="1"){ %>
		alert("关联成功");
	<%}
	if(info=="2"){ %>
	alert("关联失败");
	<%}
	if(info=="0"){ %>
	<%}%>
</script>
</head>

<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">考试信息与考生关联</td>
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
													<td class="header8">考试批次</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<form id="aForm" name="aForm" method="post">
									<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="8"></td>
										</tr>
										<tr>
											<td height="8">&nbsp;</td>
											<td width="13%" height="28" align="right">批次名称：</td>
											<!-- <td width="18%" align="left"><input id="sjmc" name="" onblur="checkSjmc()"/> </td> -->
											<td width="18%" align="left">
												<select id="paper" name="paper" onchange="checkPaper(this.value)">
													<option value="">请选择</option>
													<%
														for(int i=0;i<acpList.size();i++){
															Admission_card_pc acp = acpList.get(i);
															out.print("<option value="+acp.getId()+">"+acp.getPc_name()+"</option>");
														}
													%>
												</select>
											</td>
											<td align="left">
												<input type="button" class="submit_2" onClick="javascript:sbt();" value="关 联"/>
											</td>
											<!-- <td height="8">
												<input type="button" class="submit_2" onClick="alreadyRelated()" value="查看已关联"/>
											</td> -->
										</tr>
										<tr>
											<td height="8">
												<input type="hidden" name="sjmc" />
												<input type="hidden" id="sjid" name="sjid" />
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">考生批次 &nbsp;&nbsp;<!-- <a href="javascript:void();" onClick="sbt();" ><font color=red>关联</font></a> -->&nbsp;&nbsp;&nbsp;</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td width="3%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()"></td>
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
										<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">批次名称</span></td>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">人数</span></td>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
									</tr>
									<c:forEach var="aBean" items="${acfList}" varStatus="status">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type="checkbox" id="checkbox" name="checkedid" value="<c:out value='${aBean.id}'/>" /></td>
											<td align='center' class='num_font'><c:out value="${status.index+1}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.name}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.num}" /></td>
											<td>
											<input type="button" class="submit_2" onClick="detailjdpc(<c:out value='${aBean.id}'/>)"  value="查看详情"/>
											<input type="button" class="submit_2" onClick="deletejdpc(<c:out value='${aBean.id}'/>)"  value="删除"/></td>
										</tr>
									</c:forEach>
								</table> 
								<%-- <c:if test="${professions!= null }">
									<s:form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post">
										<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm" />
									</s:form>
								</c:if> --%>
							</td>
						</tr>
					</table>

				</div></td>
		</tr>
	</table>

</body>
</html>
