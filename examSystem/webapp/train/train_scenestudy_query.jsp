<%@ page contentType="text/html;charset=gbK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�ޱ����ĵ�</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>
	<%@ page import="java.util.List,java.util.Date,java.util.Iterator"%>
	<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>

	<script type="text/javascript">
 		function save(){
 		document.save_his.submit();
 		}

		function mediaplayer(wav_name){
			MediaPlayer.Filename = wav_name;
			//alert(MediaPlayer.Filename);
		}
	</script>
	<%
		request.setCharacterEncoding("GBK");
		String id = request.getParameter("id");
		String callid = request.getParameter("callid");
		String username = request.getParameter("username");
		List h = callservice.getDataByID(new Long(id));
		
		List readhistory = callservice.getArichivelog(callid);
		Object[] objs= (Object[])h.get(0);
		
		List urllist = callservice.getHisRecordUrl(callid);	
	%>
	<body>
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
			                <td><strong> &nbsp;�ֳ����</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="history.back(1)"><< ����</a>
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
	<br>
	<fieldset>
	<legend>�����Ҫ��¼</legend>
		<table width="99%" border="0"  cellpadding="3"
						cellspacing="1" bgcolor="#ffffff">
			<tr bgcolor="#FFFFFF">
				<td height="1" colspan="6" bgcolor="#ffffff"></td>
			</tr>
			<tr>
				<td align="right" width=65>
					��&nbsp;ˮ&nbsp;�ţ�
				</td>
				<td align="left">
					<%=id%>
				</td>
				<td align="right" >
					�������ͣ�
				</td>
				<td align="left">
					<%=objs[1]%>
				</td>

				<td align="right" >
					��ϯ���ţ�
				</td>
				<td align="left" >
					<%=objs[0]%>
				</td>
			</tr>

			<tr>
				<td align="right" >
					�绰���룺
				</td>
				<td align="left">
					<%=objs[4]%>
				</td>
				<td align="right">
					�������ͣ�
				</td>
				<td align="left" colspan=3><%=objs[2]%></td>
			</tr>
			<tr>
				<td align="right">
					�����Ҫ��
				</td>
				<td align="left" colspan=5>
				<%=objs[3]%>
				</td>
			</tr>
		</table>
		</fieldset>
		<br>
	<fieldset  >
	<legend>����ļ���¼</legend>
		<table width="99%" border="0"  cellpadding="3"
						cellspacing="1" bgcolor="#ffffff">
			<tr bgcolor="#FFFFFF">
				<td height="1" colspan="6" bgcolor="#ffffff"></td>
			</tr>
			<tr>
				<td >
					���
				</td>			
				<td >
					��ϯ����
				</td>
				<td >
					�ļ���
				</td>
				<td>
					ʱ��
				</td>				
			</tr>
			<% 
				for(int i=0;i<readhistory.size();i++){
					Object[] archivelog = (Object[])readhistory.get(i);
			%>
				<tr>
					<td >
						<%=(i+1) %>
					</td>			
					<td >
						<%=username%>
					</td>
					<td >
						<%=archivelog[0]%>
					</td>
					<td>
						<%=archivelog[1]%>
					</td>				
				</tr>
			<%		
				}
			%>
			
		</table>
		</fieldset>
		<br>
	<fieldset  >
	<legend>¼��</legend>
		<table width="99%" border="0"  cellpadding="3"
						cellspacing="1" bgcolor="#ffffff">
			<tr bgcolor="#FFFFFF">
				<td height="1" colspan="6" bgcolor="#ffffff"></td>
			</tr>
			<tr>
				<td align="right" width=65>
					��&nbsp;&nbsp;�ţ�
				</td>
			</tr>
			<%
				for (Iterator lt = urllist.iterator(); lt.hasNext();) {
					String[] array = (String[])lt.next();
					out.println("<tr>");
					out.println("<td width='10%' align='center'>����</td>");
					out.print("<td width='10%'>");
					out.print(array[1]);
					out.println("</td>");							
					out.println("<td width='20%' align='center'>ʱ��</td>");
					out.print("<td width='20%'>");
					out.print(array[2]);
					out.println("</td>");
					out.println("<td width='10%' align='center'>����</td>");
					out.print("<td width='10%'>");
					out.print(array[3]);
					out.println("</td>");
					out.print("<td >");
					out.println("<a href='#' onclick = 'return mediaplayer(\"" + array[0] + "\")'>����</a>");
					out.println("</td>");
					out.println("<tr>");
				}
	   		%>		
		</table>
		</fieldset>				
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
	</body>
</html>
