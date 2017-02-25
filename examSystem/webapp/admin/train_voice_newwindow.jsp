<%@page contentType = "text/html;charset=gbk" %>
<%@page import="java.util.List,java.util.Iterator" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<html>
<head>
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
</head>

<%
	String username = request.getParameter("username");
	if(username==null) username="";
	String starttime = request.getParameter("starttime");
	if(starttime==null) starttime="";
	String endtime = request.getParameter("endtime");
	if(endtime==null) endtime="";
	
	List urllist = callservice.findRecordByCrit(username,starttime,endtime);	
%>

<script>

	function fPopUpCalendarDlg(textname,startYear,endYear,q)
	{
		var pattern = /^(19|20)([0-9]){2}$/;
		flag=pattern.test(startYear);
		if(!flag)startYear=1900;
		flag=pattern.test(endYear);
		if(!flag)endYear=2050;
	
		today=new Date();
		
		var currentDate = today.getYear() + "-" + today.getMonth() + "-" + today.getDay();
	
		var arguments = new Array(startYear,endYear,0,0,0);
		
		var pattern = /^(19|20)([0-9]){2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
		flag=pattern.test(currentDate);
		if(flag)
		{
			iYear=currentDate.substring(0,4);
			iMonth=currentDate.substring(5,7);
			iDay=currentDate.substring(8,10);
			arguments = new Array(startYear,endYear,iYear,iMonth,iDay);
		}
	
		showx = event.screenX - event.offsetX + 18; 
		showy = event.screenY - event.offsetY - 210; 
		
		var features =
			'dialogWidth:'  + 180 + 'px;' +
			'dialogHeight:' + 230 + 'px;' +
			'dialogLeft:'   + showx     + 'px;' +
			'dialogTop:'    + showy     + 'px;' +
			'directories:no; location:no; status:no; menubar:no; toolbar=no;scrollbars=no;Resizeable=no; help:0';
		
		retval = window.showModalDialog("<%=request.getContextPath()%>/8.htm", arguments , features );
		
		if( retval != null ){
			textname.value = retval;
		}
	}

	function mediaplayer(wav_name){
		MediaPlayer.Filename = wav_name;
	}
	
	function addToParent(name){
		opener.document.trainfileform.voice.value=name;
		closeWindow();
	}
	setTimeout("closeWindow()",4000);
	function closeWindow(){
		window.close();
	}
</script>

<body>
	<form method = "post" action = "train_voice_newwindow.jsp" name="voiceForm">
		<table cellspacing="0" cellpadding="0" width=100% height=8><tr><td></td></tr></table>	
 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="5"><img src="../images/k_01.gif" width="5" height="4"></td>
				<td background="../images/k_02.gif"></td>
				<td width="6"><img src="../images/k_03.gif" width="6" height="4"></td>
			</tr>
			<tr>
				<td background="../images/k_04.gif"></td>
				<td height="50" bgcolor="#F2F8FD"> 
               		<table width="98%" border="0" align="center" cellpadding="1" cellspacing="2">
         	 			<tr>
	           				<td width="62" rowspan="2"><div align="center"><img src="../images/n_02.gif" width="43" height="53"></div></td>
					        <td align=right width=50>工号:</td>
					        <td><input type=text name="username"></td>
					        <td align=right width=100>时间:</td>
					        <td align=left width=180>
					        	<input type=text name="starttime">
					        	&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(starttime,1949,2010,1)">
					        </td>
					        <td align=center>-</td>
					        <td align=left>
					        	<input type=text name="endtime">
					        	&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(endtime,1949,2010,1)">
					        </td>
						</tr>
						<tr>
							  <td colspan=7 align=center>
							  	<input type=submit class="SmallButton"  value="查询">
							  </td>    				
						</tr>
					</table>
				</td>
			  <td background="../images/k_05.gif"></td>
			</tr>
			<tr>
				<td><img src="../images/k_06.gif" width="5" height="4"></td>
				<td background="../images/k_07.gif"></td>
				<td><img src="../images/k_08.gif" width="6" height="4"></td>
			</tr>
		</table>
	</form>

		<object align="middle"
			classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer"
			width="400" height="69">
			<param name="ShowStatusBar" value="-1">
			<param name="AutoStart" value="0">
			<param name="Filename" value="">
			<embed type="application/x-oleobject"
				codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
				flename="mp" src="\\172.16.31.16\voice\65470" width=200 height=50></embed>
		</object>

	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
				<td height="5" colspan="4"></td>
			</tr>
			<tr> 
				<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
				<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
				<td width="98%" background="../images/k1_03.gif"><strong>定点药店查询结果</strong>
					<a href="medi_yaodian.jsp"><< 返回上一页</a>&nbsp;<a href="medi_lookup.jsp">&nbsp;&nbsp;&nbsp;&nbsp;往查询页>></a>
				</td>
				<td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
			</tr>
			<tr> 
				<td background="../images/k1_05.gif"> </td>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C6E2FB">
						<tr bgcolor="#E7F3FE" height="23">
							<td align="center" width=30>序号</td>
							<td align="center" width=270>名称</td>
							<td align="center" width=170>时间</td>
							<td align="center" width=50>工号</td>
							<td align="center" colspan=2>操作</td>
						</tr>
					<%
						int i=1;
						for (Iterator lt = urllist.iterator(); lt.hasNext();) {
							String[] array = (String[])lt.next();
							out.println("<tr bgcolor=#ffffff align=center>");
							out.print("<td width='30'>");
							out.print(i);
							out.println("</td>");	
							out.print("<td width='270'>");
							out.print(array[1]);
							out.println("</td>");							
							out.print("<td width='170'>");
							out.print(array[2]);
							out.println("</td>");
							out.print("<td width='50'>");
							out.print(array[3]);
							out.println("</td>");
							out.print("<td >");
							out.println("<a href='#' onclick = 'return mediaplayer(\""+array[0]+"\")'>播放</a>");
							out.println("</td>");
							out.print("<td >");
							out.println("<a href='#' onclick = \"addToParent('"+array[1]+"')\">添加</a>");
							out.println("</td>");							
							out.println("</tr>");
							i++;
						}
			   		%>
					</table>
				</td>
				<td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
			</tr>
		   <tr> 
				<td><img src="../images/k1_07.gif" width="3" height="5"></td>
				<td colspan="2" background="../images/k1_08.gif"> </td>
				<td><img src="../images/k1_09.gif"></td>
		   </tr>
		</table>
</body>
</html>
