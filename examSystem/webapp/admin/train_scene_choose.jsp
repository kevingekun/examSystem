<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.Date"%>
<jsp:useBean id="authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
<%@page import="com.wondersgroup.falcon.acegi.AcegiUtil,java.util.List"%>
<%@page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
<%@page import="com.wondersgroup.falcon.model.auth.User" %>
<%@page import="com.wondersgroup.falcon.beans.common.CommFunc" %>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<% 
	User users = ((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser(); 
	Date date = new Date();
	String datestring = CommFunc.convertDT(date); 
	
	String username = request.getParameter("username");
	if(username==null)username="";
	String starttime = request.getParameter("starttime");	
	if(starttime==null) starttime="";
	String endtime = request.getParameter("endtime");	
	if(endtime==null) endtime="";

%>

<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script>
		var fujianshu = 1;
		function addAccessory(){
			var fjdiv = document.getElementById("fujiandiv");
			var div = document.createElement("div");
			var namediv = document.createElement("div");
			div.id = "upload"+fujianshu;
			div.innerHTML="<input type=file name=uploadfile"+fujianshu+">&nbsp;&nbsp;"+
						  "<a href=# onclick=\"removeDiv('upload"+fujianshu+"','uploadname"+fujianshu+"')\">删除</a>";
			
			fjdiv.appendChild(div);
			if(fjdiv.innerText==""){
				fjdiv.style.display="none";
			}else{
				fjdiv.style.display="block";
			}
			fujianshu++;
		}
		
		function removeDiv(id,nameid){
			var fjdiv = document.getElementById("fujiandiv");
			var div = document.getElementById(id);
			fjdiv.removeChild(div);

			if(fjdiv.innerText==""){
				fjdiv.style.display="none";
			}else{
				fjdiv.style.display="block";
			}
		}
		
		function checkSubmit(){
			if(document.trainfileform.title.value==""){
				alert("请填写标题！");
				return ;
			}
			if(document.trainfileform.content.value==""){
				alert("请填写内容！");
				return ;
			}			
			if(document.trainfileform.uploadfile0.value==""){
				alert("请至少选择一个要上传的文件！");
				return ;
			}				
			document.trainfileform.submit();
			
		}
	</script>
	<BODY >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td height="3" colspan="3"></td>
		</tr>
		<tr> 
			<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
			<td background="../images/min_02.gif"><div align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
						<td><strong> &nbsp;系统反馈提交</strong> 
		 				<div align="left"></div></td>
					</tr>
				</table>
			</div>
			<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
		</tr>
	 	<tr> 
	    	<td height="5" colspan="3"></td>
	  	</tr>
	</table>
	
	<form name="queryForm" action="<%=request.getContextPath() %>/admin/train_scene_choose.jsp" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="5"><img src="<%=request.getContextPath() %>/images/k_01.gif" width="5" height="4"></td>
			<td background="<%=request.getContextPath() %>/images/k_02.gif"></td>
			<td width="6"><img src="<%=request.getContextPath() %>/images/k_03.gif" width="6" height="4"></td>
		</tr>
		<tr>
			<td background="<%=request.getContextPath() %>/images/k_04.gif"></td>
			<td height="50" bgcolor="#F2F8FD"> 
				<table width="100%" border="0" align="center" cellpadding="1" cellspacing="2">
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
		  <td background="<%=request.getContextPath() %>/images/k_05.gif"></td>
		</tr>
		<tr>
			<td><img src="<%=request.getContextPath() %>/images/k_06.gif" width="5" height="4"></td>
			<td background="<%=request.getContextPath() %>/images/k_07.gif"></td>
			<td><img src="<%=request.getContextPath() %>/images/k_08.gif" width="6" height="4"></td>
		</tr>
	</table>
	</form>	
	
	<% 
		//HistoryService pit = new HistoryService();
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td height="5" colspan="4"></td>
		</tr>
	    <tr> 
			<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
			<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
			<td width="98%" background="../images/k1_03.gif">来电历史</td>
		    <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
		</tr>       
		<tr> 
			<td background="../images/k1_05.gif"></td>
			<td  colspan="2" valign="top"> 
			 <table width="100%" height=100% border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
					<tr bgcolor=#ffffff >
						<td align="center" width=5%>
							序号
						</td>			
						<td align="center" width=9%>
							服务流水号
						</td>
						<td align="center">
							服务类型
						</td>
						<td align="center" width=8%>
							座席工号
						</td>
						<td align="center" width=12%>
							来电号码
						</td>
						<td align="center" width=8%>
							结束时间
						</td>	
						<td align="center" width=8%>
							操作
						</td>								
					</tr>
			        <tr bgcolor="#FFFFFF"> 
			          <td height="2" colspan="9" bgcolor="#F1F8FE"></td>
			        </tr>
					<%
					String endtime2 = null;
					String[][] time2 = null;
					List list2 = callservice.findHistoryByCrit(username,starttime,endtime);
					if(list2!=null&&list2.size()!=0){
						int j = list2.size();
						if(j>10) j=10;
						for (int i = 0; i < j; i++) {
							//History hospital = (History) list2.get(i);
							Object[] obj = (Object[])list2.get(i);
							if(obj[1]!=null)
							endtime2 = obj[1].toString();		
							out.println("<tr bgcolor=\"#FFFFFF\">");
							out.println("<td align=center>"+(i+1)+"</td>");					
							out.println("<td align=center>");
							out.println("<a class='b' href=train_scene_query.jsp?id="
									+ obj[2] + "&callid="+obj[0]+"&username="+obj[5]+" target=\"_self\">"
									+ obj[2] + "</a>");
							out.println("</td>");
							out.println("<td align='left'>");
							out.println(obj[4] == null ? "" : obj[4]);
							out.println("</td>");
							out.println("<td align='center'>");
							out.println(obj[5]);
							out.println("</td>");
							out.println("<td align='center'>");
							out.println(obj[6]);
							out.println("</td>");
							out.println("<td align='center'>");
							out.println(endtime2==null?"":endtime2.substring(11,endtime2.length()-2));
							out.println("</td><td>");
							out.println("<a href=\"train_scene_query.jsp?id="+obj[2]+"&callid="+obj[0]+"&username="+obj[5]+"\">查看</a>");
							out.println("</td></tr>");
							
						}
					
					}		
					%>
					<tr>
						<td colspan=9></td>
					</tr>
			</table>
			</td>
	    	<td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
	    </tr>
		<tr> 
			<td><img src="../images/k1_07.gif" width="2" height="5"></td>
			<td colspan="2" background="../images/k1_08.gif"> </td>
			<td><img src="../images/k1_09.gif"></td>
		</tr>
	</table> 		
	</BODY>
</HTML>
<% 
	//HibernateUtil.closeSession();
%>
