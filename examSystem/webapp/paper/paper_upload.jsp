<%@page import="com.wondersgroup.kaoshi.action.QuestionAction"%>
<%@page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
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
 ProfessionBean professionBean = new ProfessionBean();
 List<EPapers> p_list = professionBean.getUnUploadPapersOnPaper();

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>试卷上传一体化</title>

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
		alert("请选择试卷！");
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
			document.aForm.action="relatePaperAndTeam.action?checkid="+checkedid;
			document.aForm.submit();
	    }else{
	    	alert("请选择鉴定所！");
	    }
	}
}
function checkSjmc(){
	var sjmc = $("#sjmc").val();
	sjmc = sjmc.replace(/\s/g,"");
	var u = 'checksjmc.action?sjmc='+sjmc;
	var url = encodeURI(encodeURI(u));
	var bool = false;
	$.ajax({
		type: 'post',
		async: false,
		url: url,
		success:function(result){
			if(result==""){
				alert("试卷不存在！");
				bool = false;
			}else{
				$("#sjid").val(result);
				//alert($("#sjid").val())
				bool = true;
			}
		},
		error:function(){
			alert("程序出错！");
			bool = false;
		}
	});
	return bool;
}
function checkPaper(v){
	$("#sjid").val(v);
	//alert(v)
}
function alreadyRelated(){
	window.open ("<%=request.getContextPath() %>/paper/paper_upload_alreadyRelated.jsp", "newwindow", "height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
}
function alreadyUpload(){
	window.open ("<%=request.getContextPath() %>/paper/paper_upload_alreadyUpload.jsp", "newwindow", "height=500, width=1000, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
}
</script>
<script type="text/javascript">
<%
	String info = (String)request.getAttribute("info");
	String info2 = (String)session.getAttribute("info2");//取消关联页面alreadyRelated.jsp修改此参数
	if(info=="1"&&!"1".equals(info2)){ %>
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
						<td class="header2">上传试卷</td>
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
													<td class="header8">试卷</td>
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
											<td width="13%" height="28" align="right">试卷名称：</td>
											<!-- <td width="18%" align="left"><input id="sjmc" name="" onblur="checkSjmc()"/> </td> -->
											<td width="18%" align="left">
												<select id="paper" name="paper" onchange="checkPaper(this.value)">
													<option value="">请选择</option>
													<%
														for(int i=0;i<p_list.size();i++){
															EPapers paper = p_list.get(i);
															out.print("<option value="+paper.getSjId()+">"+paper.getSjMc()+"</option>");
														}
													%>
												</select>
											</td>
											<td align="left">
												<input type="button" class="submit_2" onClick="javascript:sbt();" value="关 联"/>
											</td>
											<td height="8">
												<input type="button" class="submit_2" onClick="alreadyRelated()" value="查看已关联"/>
											</td>
											<td height="8">
												<input type="button" class="submit_2" onClick="alreadyUpload()" value="查看已上传"/>
											</td>
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
													<td class="header8">鉴定所 &nbsp;&nbsp;<!-- <a href="javascript:void();" onClick="sbt();" ><font color=red>关联</font></a> -->&nbsp;&nbsp;&nbsp;</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td width="3%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()"></td>
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定所名称</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">联系人</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">电话</span></td>
										<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">地址</span></td>
									</tr>
									<c:forEach var="aBean" items="${list}" varStatus="status">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type="checkbox" id="checkbox" name="checkedid" value="<c:out value='${aBean.teamId}'/>" /></td>
											<td align='center' class='num_font'><c:out value="${status.index+1}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.teamName}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.contactname}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.contactph}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.teamaddress}" /></td>
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
