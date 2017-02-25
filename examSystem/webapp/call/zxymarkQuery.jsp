l<%@ page contentType="text/html;charset=UTF-8"%>
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
	 User uuser = ((UserDetailsImpl)util.getUserDetails()).getUser();
 	String name=uuser.getUsername();
 	System.out.println("22222222===========>"+name);
 	
 	
   		String username="";
   		Users users = authbean.findByUsername(util.getUserDetails().getUsername());
   		
   		String temp= users.getUsertype().getUsertypeid();
		String gonghao =users.getUsername();
		String mutex="";
		String time="";
		mutex=request.getParameter("mutex");
		if(mutex==null)mutex="";
		int prepage=0;
		int nextpage=15;
		Integer count=new Integer(0);
		
		username=request.getParameter("username");
		if(username==null)username="";

		String callid=request.getParameter("callid");
		if(callid==null)callid="";
		String starttime=request.getParameter("starttime");
		if(starttime==null)starttime="";
		String endtime=request.getParameter("endtime");
		if(endtime==null)endtime="";
		String start=request.getParameter("start");
		if(start==null)start="";
		String end=request.getParameter("end");
		if(end==null)end="";
		String dn=request.getParameter("dn");
		if(dn==null)dn="";

		
		ZhijianDAO pit = new ZhijianDAO();
		
		
		int pa = 1;
		if (request.getParameter("pa") != null) {
			pa = Integer.parseInt(request.getParameter("pa"));
			prepage=Integer.parseInt(request.getParameter("prepage")==null?"0":request.getParameter("prepage"));
			nextpage=Integer.parseInt(request.getParameter("nextpage")==null?"15":request.getParameter("nextpage"));
			prepage=(pa-1)*15+1;
			nextpage=pa*15;
		}
		int ye = 1;
		List list=null;
		List list111=null;
		History pn = new History();
		Zhijianpingfen pf = new Zhijianpingfen();
		
		
		
	
		list111 = pit.findAll1(name);
			if(list111!=null&&list111.size()!=0){
			System.out.println("list.get(0)"+list111.get(0));
						Integer obj= Integer.valueOf(list111.get(0).toString());
						count=obj;
			}
		ye=count.intValue()/15+1;
		System.out.println("count===>"+count);
		if (list != null && list.size() != 0) {
			ye = list.size() / 15 + 1;
		}
		
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
		
		
		
	</script>

	<body>
	<form name="queryform" method="post" action="main_right.jsp">
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
			<td align=center>工号：</td>
			<td align=left><input type=text name="username" size=15></td>
			<td align=right width=10%>时长：</td>
			<td align=left>
				<input type=text name="start">
				&nbsp;
				&nbsp;至&nbsp;
				<input type=text name="end">
				&nbsp;
			</td>
		</tr>
		<tr bgcolor=#ffffff>
			<td align=right width=10%>来电号码：</td>
			<td align=left width=13%><input type=text name="callid" size=15></td>	
			<td align=right width=10%>时间：</td>
			<td align=left colspan=3>
				<input type=text name="starttime">
				&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(starttime,1999,2050,1)">
				&nbsp;至&nbsp;
				<input type=text name="endtime">
				&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(endtime,1999,2050,1)">
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
					查看分数明细
				</td>
				
				
				
			</tr>

			<%
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formatime = sdf.format(date);
			if(temp.equals("2")||temp.equals("1")){
			username=gonghao;
			endtime=formatime;
			starttime=formatime;
			}
			String endtime2 = null;
			String[][] time2 = null;
			List list2=null;
			List list3=null;
			if(mutex.equals("")){
			list2 = pit.findTopHistory21(name,prepage,nextpage);
			}
			else {
			list2=pit.findHistory21(name,callid,starttime,endtime,start,end,prepage,nextpage);
			list3=pit.countHistory(username,callid,starttime,endtime,start,end,name);
			Integer obj1= Integer.valueOf(list3.get(0).toString());
						count=obj1;
			ye=obj1.intValue()/15;
			}
			if(list2!=null&&list2.size()!=0){
				int j = list2.size();
				for (int i = 0; i < j; i++) {
					Select1 hospital = (Select1) list2.get(i);
					
							
					HibernateUtil.commitTransaction();		
					String mark = "";
					String dafenmemo="";
					String id = hospital.getId();
					System.out.println("id===>"+id);
					if( id != null ){
					pf = pit.getMarkByID(id);
					}
					if( pf!=null&&pf.getMark() != null ){
					 mark = pf.getMark();
					}
				if( pf!=null&&pf.getDafenmemo() != null ){
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
					out.println("<td align=center>");
					out.println("<a class='b' href='zxy_luyin.jsp?callid="
							+ hospital.getId() + "&time="+hospital.getStartrecordtime().toString()+"&dafenmemo1="+dafenmemo+"' target=\"_self\">"+
							"查看分数明细 </a>");
					out.println("</td>");
					
					
					
					
					//out.println("<td align='center'>");
					//out.println(time2==null||time2.length==0?"":time2[0][1]);
					//out.println("</td>");
					
				}
			
			}		
			%>
			
		</table>
		</form>
		<form action="tjhistroy_query.jsp" name="zhonglei">
			<input type="hidden" name="mutex" value="1">
			<input type="hidden" name="pa">
			<input type="hidden" name="prepage" value=<%=prepage %>>
			<input type="hidden" name="nextpage" value=<%=nextpage %>>
			<input type="hidden" name="username" value=<%=username %>>
			<input type="hidden" name="callid" value=<%=callid %>>
			<input type="hidden" name="starttime" value=<%=starttime %>>
			<input type="hidden" name="endtime" value=<%=endtime %>>
		</form>
	</body>
</html>
