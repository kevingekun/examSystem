<%@page contentType="text/html;charset=gbk"%>
<%@page import="java.util.List" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamQuestionType" %> 
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script language="javascript">
		
	  function fPopUpCalendarDlg(textname,startYear,endYear,q){
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
			
			retval = window.showModalDialog("../8.htm", arguments , features );
			
			if( retval != null ){
				textname.value = retval;
			}
		}
		
		function checkon(id,chec){
			if(chec==true){
				document.getElementById(id).style.display="block";		
			}else{
				document.getElementById(id).style.display="none";			
			}
		}
		
		function checksubmit(){
			var subtypechecks = document.getElementsByName("subtypecheck");
			var count=0;
			for(var i=0;i<subtypechecks.length;i++){
				if(subtypechecks[i].checked==true)count++;
			}
			if(count==0){
				alert("��û��ѡ���κ�����!");
				return ;
			}
			document.exam_autocreform.submit();	
		}	
		
	</script>
	<% 
		List subtypelist = examservice.findQuestionType(1); 
	%>
	<BODY >
		<br>
		<form name="exam_autocreform" action="<%=request.getContextPath() %>/paperServlet" method="post">
		<input type=hidden name="actionType" value="autocreate">
		<table width=99% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD" align=center>
			<tr bgcolor="#ffffff"  height=30>
				<td align=center><b><font size=3>�Զ�����Ծ��굥</font></b></td>
			</tr>
			<tr bgcolor="#ffffff"  height=30>
				<td >&nbsp;˵�����Զ�����������ͼ�����ĸ��ֲ����Զ������Ծ��������͵���Ŀ�����ݸ���Ŀ����Ҫ�̶ȶ������ȡ��</td>
			</tr>			
			<tr ><td>&nbsp;<b>�Ծ��������</b></td></tr>
			<tr bgcolor=#ffffff>
				<td>
					<table width=100% border=0>
						<tr align=center>
							<td align=right width=80>
								��&nbsp;��&nbsp;����
							</td>
							<td align=left>
								<input type=text name="examname" value="�����Ծ�һ">
							</td>
						</tr>
						<tr>
							<td align=right>
								�Ծ��ܷ֣�
							</td>
							<td align=left>
								<input type=text name="exammark" size="2" value="100">
							</td>
						</tr>
						<tr>
							<td align=right>
								����ʱ�䣺
							</td >
							<td align=left>
								<input type=text name="examtime" value="60">(����)
							</td>
						</tr>
						<tr >
							<td  align=right>
								��Ч���ޣ�</td>
							<td colspan=5 align=left>
								<input type=text name="effectstarttime" value="2008-1-1">&nbsp;<img border="0" src="../images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(effectstarttime,1949,2010,1)">
								-
								<input type=text name="effectendtime" value="2008-12-31">&nbsp;<img border="0" src="../images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(effectendtime,1949,2010,1)">
							</td>
						</tr>						
					</table>
				</td>
			</tr>
			<tr>
			<td>
			<table width=100%��cellspadding=0 cellspacing =0 border=0>
			
			<% 
				for(int i=0;i<subtypelist.size();i++){
					ExamQuestionType est = (ExamQuestionType)subtypelist.get(i);
			%>
				<tr bgcolor="#E0F0FD" height=20>		
					<td colspan=3>&nbsp;&nbsp;<b><%=est.getTypename() %></b></td>
				</tr>
				<tr id="onechoice" bgcolor="#ffffff" style="display:block" height=50>		
					<td width=110 align=right>
						&nbsp;����: <input type=text name="<%=est.getIdentiname() %>num" style="width:30" value="10">(��)
					</td>
					<td width=150>
						&nbsp;&nbsp;&nbsp;&nbsp;ÿ�������<input type=text name="<%=est.getIdentiname() %>mark" style="width:30" value="1">(��)
					</td>
					<td >	
						&nbsp;&nbsp;&nbsp;&nbsp; ��Ҫ�Ա������Զ�
					</td>
				</tr>					
			<%
				}
			%>
			<tr id="dialog"  style="display:block" height=50>		
				<td align=center colspan=3 ><input type=submit  value="�Ծ�Ԥ��"></td>
			</tr>	
			</table>
			</td></tr>													
		</table>
		
					
		</form>
	</BODY>
</HTML>
