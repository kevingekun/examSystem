<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>试卷从一体化下载</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js" ></script>

<script type="text/javascript">
function downloadPaper(sjid){
	$(".SmallButton").attr('disabled',"true");
	$.ajax({
		type:'post',
		url:'downloadPaperBySjid.action?sjid='+sjid,
		success:function(result){
			alert(result);
			window.location.reload();
		},
		error:function(){
			alert("系统出错！请联系管理员");
			window.location.reload();
		}
	});
}
function deletePaper(sjid,sjmc){
	if(confirm("确定要删除  "+sjmc+"  试卷及考生信息吗？")){
	$(".BigButton").attr('disabled',"true");
		$.ajax({
			type:'post',
			url:'deletePaperBySjid.action?sjid='+sjid,
			success:function(result){
				alert(result);
				window.location.reload();
			},
			error:function(){
				alert("系统出错！请联系管理员");
				window.location.reload();
			}
		});
    }else{
    }
}
function deleteUser(sjid,sjmc){
	if(confirm("确定要删除  "+sjmc+"  下的考生信息吗？")){
	$(".BigButton").attr('disabled',"true");
		$.ajax({
			type:'post',
			url:'deleteUserBySjid.action?sjid='+sjid,
			success:function(result){
				alert(result);
				window.location.reload();
			},
			error:function(){
				alert("系统出错！请联系管理员");
				window.location.reload();
			}
		});
    }else{
    }
}
</script>
</head>

<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">下载试卷</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
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
										<!-- <td width="3%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()"></td> -->
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">总分</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">答卷时限</span></td>
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">参考人数</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">有效开始时间</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">有效结束时间</span></td>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
									</tr>
									<c:forEach var="aBean" items="${list}" varStatus="status">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<%-- <td align='center' class='num_font'><input type="checkbox" id="checkbox" name="checkedid" value="<c:out value='${aBean.sjId}'/>" /></td> --%>
											<td align='center' class='num_font'><c:out value="${status.index+1}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.sjmc}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.sjzf}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.djsx}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.ksrs}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.kksj}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.jssj}" /></td>
											<td align='center' class='num_font'>
												<button class="SmallButton" onclick='downloadPaper(<c:out value="${ aBean.sjid}"/>);'>下载</button>
												<button class="BigButton" onclick="deletePaper('<c:out value="${ aBean.sjid}"/>','<c:out value="${aBean.sjmc}"/>');">删除全部信息</button>
												<button class="BigButton" onclick="deleteUser(<c:out value="${ aBean.sjid}"/>,'<c:out value="${aBean.sjmc}"/>');">删除考生信息</button>
											</td>
										</tr>
									</c:forEach>
								</table> 
							</td>
						</tr>
					</table>

				</div></td>
		</tr>
	</table>

</body>
</html>
