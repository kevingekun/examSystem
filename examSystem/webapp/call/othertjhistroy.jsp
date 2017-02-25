<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>无标题文档</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>

	<jsp:useBean id="Wonders"
		class="com.wondersgroup.falcon.beans.archives.HisTree" />
	<jsp:useBean id="Comm"
		class="com.wondersgroup.falcon.beans.common.CommFunc" />
		<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
		<jsp:useBean id = "authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
	<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
	<%@ page import="com.wondersgroup.falcon.model.archives.Users" %>
	<%@ page import="com.wondersgroup.falcon.model.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.Date"%>
	<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
	<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil" %>
	<%@ page import="com.wondersgroup.falcon.beans.archives.HisTree"  %>
	<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
	<%@ page import="com.wondersgroup.falcon.model.select.*" %>
	<%@ page import="com.wondersgroup.falcon.model.call.Zhijianpingfen" %>
	<%
	 request.setCharacterEncoding("UTF-8");
	
 	
 		String A=request.getParameter("A");
		if(A==null)A="";
		String starttime=request.getParameter("starttime");
		if(starttime==null)starttime="";
		String endtime=request.getParameter("endtime");
		if(endtime==null)endtime="";
		String username=request.getParameter("username");
		if(username==null)username="";
		String callid=request.getParameter("callid");
		if(callid==null)callid="";
		ZhijianDAO pit = new ZhijianDAO();
		String agentid = request.getParameter("agentname");
		if (agentid==null||agentid.equals(""))
				agentid = "";
	
	%>
	<script type="text/javascript">
		function go(){
			document.zhonglei.pa.value=document.getElementById("page").value;
			document.zhonglei.submit();
		}
	
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
			
			retval = window.showModalDialog("8.htm", arguments , features );
				
			if( retval != null ){
				textname.value = retval;
			}
		}
			
		function checksubmit_2(){
			document.lookup2.submit();
		}	
		
		function queryHistory(){
			document.queryform.submit();
		}
		
		function getZhijian(){
			//window.open("fenpeiselect_zhijian.jsp","","height=200,width=500,top=220,left=220,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes");
		var members = showModalDialog('fenpeiselect_zhijian.jsp',window,'dialogWidth:500px;dialogHeight:200px;');
		if(members!=null){
		document.queryform.username.value=members.value;
		}
		
		}
		function getMembers(){
	var members = showModalDialog('message_receiver2.jsp',window,'dialogWidth:600px;dialogHeight:500px;');
	var value1 = "";
	if(members!=null){
		document.getElementById("agentid").value=members.value;
		value1=members.id;
		value1=value1.substring(0,value1.length-1);
		
		document.queryform.agentname.value=value1;
		//alert(value1);
	}
	
}
	</script>

	<body>
	<form name="queryform" method="post" action="othertjhistroy.jsp">
	<input type=hidden name="A" value="1">
	<input type=hidden name="agentname" value="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
		  <td height="3" colspan="3"></td>
		</tr>
		<tr> 
			<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
			<td background="../images/min_02.gif">
				<div align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
		            	<tr> 
			            	<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
			                <td><strong> &nbsp;录音列表</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		              			<div align="left"></div></td>
		                </tr>
		             </table>
	             </div>
	        </td>
	    	<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
		</tr>
	    <tr> 
			<td height="5" colspan="3"></td>
	    </tr>
	</table>

	<table width="100%" border="0" align="center" cellpadding="3"
		cellspacing="1" bgcolor="#9CDBF6">
				<tr bgcolor=#ffffff>
		
			<td align=center >咨询员：</td>
			<td colspan=5><textarea id="agentid" name="agentid" readonly style="width:780;overflow:visible;line-height:20px;color:#0000ff"></textarea> 
					<input type=button onclick="getMembers()" class="BigButton" value="选择咨询员">
				   		</td>
		</tr>
		<tr bgcolor=#ffffff>
			<td align=center>质检：</td>
			<td align=left>
			<textarea id="username" name="username" readonly style="width:300;overflow:visible;line-height:20px;color:#0000ff"></textarea> 
					<input type=button onclick="getZhijian()" class="BigButton" value="选择质检">
			
		
			<td align=right width=10%>时间：</td>
			<td align=left colspan=3>
				<input type=text name="starttime">
				&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(starttime,1949,2050,1)">
				&nbsp;至&nbsp;
				<input type=text name="endtime">
				&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(endtime,1949,2050,1)">
			</td>
		</tr>
		<tr bgcolor=#ffffff>
		<td align=center colspan=7><input type=button class="SmallButton" value="查询" onclick="queryHistory()">
		</td></tr>
		<input type="hidden" name="mutex" value="1">
	</table>	
	<table><tr height=20><td>
	
	</td></tr></table>
	<table width="100%" border="0" align="center" cellpadding="3"
		cellspacing="1" bgcolor="#9CDBF6">

			<tr bgcolor="#FFFFFF">
				<td height="1" colspan="9" bgcolor="#C9ECFA"></td>
			</tr>
			<tr bgcolor="#E4F5FC" height="23">
				<td align="center">
					序号
				</td>			
				<td align="center">
					工号
				</td>
				<td align="center">
					来电时间
				</td>
				<td align="center">
					来电号码
				</td>
				<td align="center">
					通话时间
				</td>
				<td align="center">
					分数
				</td>
				<td align="center">
					质检
				</td>
				<td align="center">
					听录音

				</td>
				
				
				
			</tr>

			<%
			
			List list2=null;
		
			String zhijianid[]=username.split(";");
			

			if(A.equals("1")){
			if(zhijianid.length>0){
			for(int k=0;k<zhijianid.length;k++){
				list2 = pit.queryOthers(agentid,starttime,endtime,zhijianid[k]);
			
			Zhijianpingfen pf = new Zhijianpingfen();
			if(list2!=null&&list2.size()!=0){
				int j = list2.size();
				for (int i = 0; i < j; i++) {
					Select1 hospital = (Select1) list2.get(i);
					
							
					HibernateUtil.commitTransaction();		
					String mark = "";
					String dafenmemo="";
					String id = hospital.getId();
					
					if( id != null ){
					pf = pit.getMarkByID(id);
					}
					if( pf!=null&&pf.getMark() != null ){
					 mark = pf.getMark();
					}
				
				if( pf!=null&&pf.getDafenmemo()!= null ){
					 dafenmemo = pf.getDafenmemo();
					 
					}
			

						
					out.println("<tr bgcolor=\"#FFFFFF\">");
					out.println("<td align=center>");
					out.println("<a class='b' href=manualcall_log.jsp?id="
							+ hospital.getCallerid() + " target=\"_self\">"
							+ (i+1) + "</a>");
					out.println("<td align='center'>");
					out.println(hospital.getAgentid());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getStartrecordtime().toString().substring(0,19));
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getCallerid());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getRecordlength()==null?"":hospital.getRecordlength());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(mark);
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getZhijianid()==null?"":hospital.getZhijianid());
					out.println("</td>");
					out.println("<td align=center>");
					out.println("<a href='histroyluyin.jsp?callid="
							+ hospital.getId() + "&time="+hospital.getStartrecordtime().toString()+"&dafenmemo1="+dafenmemo+"' target=\"_self\">"+
							"听录音 </a>");
					out.println("</td>");
					out.println("</tr>");
				}
			}	
			}
			}
			}	
			%>
			
		</table>
		</form>
		<form action="othertjhistroy.jsp.jsp" name="zhonglei">
			<input type="hidden" name="mutex" value="1">
			<input type="hidden" name="pa">
		
			<input type="hidden" name="username" value=<%=username %>>
			<input type="hidden" name="callid" value=<%=callid %>>
			<input type="hidden" name="starttime" value=<%=starttime %>>
			<input type="hidden" name="endtime" value=<%=endtime %>>
		</form>
	</body>
</html>
