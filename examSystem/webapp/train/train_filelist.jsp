<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.List" %>
<%@page import="com.wondersgroup.falcon.model.exam.TrainFile" %>
<%@page import="com.wondersgroup.falcon.beans.exam.TrainFileService" %>
<% 
	TrainFileService tfs = new TrainFileService();
	List list = tfs.findTrainFile(0,10);
%>
<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script>
		function goToCommFlat(advername){
			document.flatform.adverName.value=advername;
			document.flatform.submit();
		}
	</script>
	<BODY >

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td height="5" colspan="4"></td>
		</tr>
	    <tr> 
			<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
			<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
			<td width="98%" background="../images/k1_03.gif">培训文件列表</td>
		    <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
		</tr>       
		<tr> 
			<td background="../images/k1_05.gif"></td>
			<td  colspan="2" valign="top"> 
			 <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
					<tr bgcolor="#F1F8FE" > 
						<td align=center width=50 height=10>序号</td>
						<td align=center >文件主题</td>
						<td align=center width=150>创建者</td>
						<td align=center width=150>时间</td>	
						<td align=center width=100>操作</td>					
					</tr>
					<% 
						for(int i=0;i<list.size();i++){
							TrainFile tf = (TrainFile)list.get(i);
					%>
					<tr bgcolor=#ffffff>
						<td align=center width=50 height=10><%=i+1 %></td>
						<td align=center ><%=tf.getTitle() %></td>
						<td align=center width=150><%=tf.getSender().getRealname() %></td>
						<td align=center width=150><%=tf.getSendtime() %></td>	
						<td align=center width=100><a href="train_filequery.jsp?tfid=<%=tf.getId() %>" >查看</a></td>		
					</tr>
					<%
						}						
					%>
					<tr><td colspan=2></td></tr>
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

	<form name="flatform" method="post" action="../messLoginServlet">
		<input type="hidden" name="adverName" value="">
	</form>
	</BODY>
</HTML>