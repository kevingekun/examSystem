<%@page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.Util.*,java.util.*"%>
<%@page import="com.wondersgroup.popedom.bo.EUser"%>
<%@page import="com.wondersgroup.popedom.dao.EuserDao"%>
<%@page import="com.wondersgroup.popedom.bo.ELogMonitor"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String userid = request.getParameter("userid");
	String examid = request.getParameter("examid");
	EuserDao eDao = new EuserDao();
	EUser user = eDao.getUserByID(userid);
	List<Object> list = eDao.getELogMonitorByUserId(userid, examid);
	ELogMonitor eLogMonitor = (ELogMonitor)list.get(0);
	EPapers ePapers = (EPapers)list.get(1);
	Date kssj = eLogMonitor.getStartdate();
	Date dqsj = new Date();
	long sysj =ePapers.getSjDjsx()-(dqsj.getTime()-kssj.getTime())/1000/60;
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>延时操作</title>
<link href="inc/all.css" rel="stylesheet" type="text/css">
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.11.3.min.js"></script>
<script type="text/javascript">
	function submitDelayed() {
		var examid=$("#examid").val();
		var userid = $("#userid").val();
		var delayTime = $("#delay").val();
		var remarks = $("#remarks").val();
		var url = 'delayTime.action?examid='+examid+'&userid='+userid+'&delayTime='+delayTime+'&remarks='+remarks;
		url = encodeURI(encodeURI(url));
		$.ajax({
			type:'post',
			url: url,
			success:function(result){
				alert(result);
				window.close();
				window.opener.location.reload();
			},
			error:function(){
				alert("系统出错，请联系管理员！");
			}
		});
	}

	
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">延时操作</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>

		<tr>
			<input type="hidden" name="examid" id="examid" value="<%=examid%>">
			<input type="hidden" name="userid" id="userid" value="<%=userid%>">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right" name="password">准考证号：</td>
							<td><%=user.getPassword()%></td>
						</tr>
						<tr height="8px"></tr>
						<tr>
							<td align="right" name="username">姓名：</td>
							<td><%=user.getRealname()%></td>
						</tr>
						<tr height="8px"></tr>
						<tr>
							<td align="right">剩余时间：</td>
							<td><%=String.valueOf(sysj)%>分钟</td>
						</tr>
						<tr height="8px"></tr>
						<tr>
							<td align="right">延长时间：</td>
							<td><select name="delay" id="delay">
									<option value="5">5分钟</option>
									<option value="10">10分钟</option>
									<option value="20" selected="selected">20分钟</option>
									<option value="30">30分钟</option>
									<option value="40">40分钟</option>
									<option value="50">50分钟</option>
									<option value="60">60分钟</option>
							</select></td>
						</tr>
						<tr height="8px"></tr>
						<tr>
							<td align="right">备&nbsp;&nbsp;&nbsp;注：</td>
							<td><textarea rows="3" cols="18" id="remarks" name="remarks" style="width:200;height:50"></textarea></td>
						</tr>
						<tr height="8px"></tr>
						<tr>
							<table width="49" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td><input type="button" value="提 交" onclick="submitDelayed()" style="padding: 3px;"></td>
								</tr>
							</table>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>