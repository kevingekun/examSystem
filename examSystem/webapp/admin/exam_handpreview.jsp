<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.List,java.util.Set" %>

<%@page import="com.wondersgroup.falcon.model.exam.ExamPaper" %>
<%@page import="com.wondersgroup.falcon.beans.common.CommFunc" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
</HEAD>
<% 
	List oclist = (List)request.getAttribute("oclist"); 
	List mclist = (List)request.getAttribute("mclist"); 
	List jdlist = (List)request.getAttribute("jdlist"); 
	List dllist = (List)request.getAttribute("dllist"); 
	
	Integer onechoicemark=Integer.valueOf(request.getAttribute("onechoicemark"));
	Integer manychoicemark=Integer.valueOf(request.getAttribute("manychoicemark"));		
	Integer judgemark=Integer.valueOf(request.getAttribute("judgemark"));	
	Integer dialogmark=Integer.valueOf(request.getAttribute("dialogmark"));

	
	ExamPaper exampaper = new ExamPaper();
	exampaper = (ExamPaper)request.getAttribute("exampaper");
				
%>
<script>
	var time=parseInt(<%=exampaper.getExamtime()%>)
	var now1 = new Date();
	var m1 = now1.getMinutes()+(time-1);
	var s1 = now1.getSeconds()+(60);
	
	function getRemainTime(){
		var now = new Date();
		var m = now.getMinutes();
		var s = now.getSeconds();
		document.getElementById("remaintime").innerHTML=(m1-m)+":"+(s1-s);
		setTimeout("getRemainTime()",1000);
	}
	function count(){

	}
	
	function savePaper(){
		document.crepaperform.submit();
	}
</script>
<BODY >
<br>
<form name="crepaperform" action="examServlet" method="post">
<input type=hidden name="actionType" value="savepaper">
<input type=hidden name="exammark" value="<%=exampaper.getExammark() %>">
<input type=hidden name="examname" value="<%=exampaper.getExamname() %>">
<input type=hidden name="examtime" value="<%=exampaper.getExamtime()%>">
<input type=hidden name="effectstarttime" value="<%=CommFunc.dateToStr(exampaper.getEffectstarttime())%>">
<input type=hidden name="effectendtime" value="<%=CommFunc.dateToStr(exampaper.getEffectendtime())%>">

<input type=hidden name="onechoicemark" value="<%=onechoicemark%>">
<input type=hidden name="manychoicemark" value="<%=manychoicemark%>">
<input type=hidden name="judgemark" value="<%=judgemark%>">
<input type=hidden name="dialogmark" value="<%=dialogmark%>">

<TABLE border=0 align=center width=99% height=100% cellpadding=1 cellspacing=1 bgcolor="#D2E8FF">
	<tr bgcolor="#ffffff"><td align=center></td></tr>
	<tr bgcolor="#ffffff"><td align=center><font style="font-size:16px"><b><%=exampaper.getExamname() %></b></font></td></tr>
	<tr bgcolor="#ffffff">
		<td align=left>
			试卷分数:<%=exampaper.getExammark() %> 分&nbsp;&nbsp;&nbsp;
			考试时间:<%=exampaper.getExamtime()%> 分钟&nbsp;&nbsp;&nbsp;
			剩余时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="savePaper()" class="b">保存试卷</a>
		</td>
	</tr>	
	<TR bgcolor="#ffffff">
		<TD>
			<%

				if(oclist!=null){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;单选题 每题"+onechoicemark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;

					for(int j=0;j<oclist.size();j++){
						ExamRealSubject mrs = (ExamRealSubject)oclist.get(j);
						out.println("&nbsp;"+k+". "+mrs.getExamsubject().getSubjectname()+"");
						out.println("<input type=hidden name='subjectid' value='"+mrs.getExamsubject().getSubjectid()+"'>");
						Set keyset = mrs.getExamrsubkeys();
						out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						for(int h =0;h<keyset.toArray().length;h++){
							ExamRsubKeys erk = (ExamRsubKeys)keyset.toArray()[h];
							out.println("<li style='margin-left:7;padding-left:0'><input type='radio' name='oc"+j+"' value='"+h+"'>"+erk.getExamkey().getKeycontent()+"<br>");
						}
						out.println("</ul><br>");
						k++;
					}	
					out.println("</TD>"); 
					out.println("</TR>"); 
					out.println("</TABLE>"); 
				}
			%>
		</TD>
	</TR>
	<TR bgcolor="#ffffff">
		<TD>
			<%

				if(mclist!=null){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;多选题 每题"+manychoicemark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;

					for(int j=1;j<=mclist.size();j++){
						ExamRealSubject mrs = (ExamRealSubject)mclist.get(j-1);
						out.println("&nbsp;"+k+". "+mrs.getExamsubject().getSubjectname()+"<br>");
						out.println("<input type=hidden name='subjectid' value='"+mrs.getExamsubject().getSubjectid()+"'>");
						out.println("<table></tr><td></td></table>");
						Set keyset = mrs.getExamrsubkeys();
						out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						for(int h =0;h<keyset.toArray().length;h++){
							ExamRsubKeys erk = (ExamRsubKeys)keyset.toArray()[h];
							out.println("<li style='margin-left:7;padding-left:0'><input type=checkbox name='mc"+j+"' value='"+h+"'>"+erk.getExamkey().getKeycontent()+"<br>");
						}
						out.println("</ul>");
						k++;
					}
	
					out.println("</TD>"); 
					out.println("</TR>"); 
					out.println("</TABLE>"); 
				}
			%>		
		</TD>
	</TR>
	<TR bgcolor="#ffffff">
		<TD>
			<%
				if(jdlist!=null){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;判断题 每题"+judgemark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;

					for(int j=1;j<=jdlist.size();j++){
						ExamRealSubject mrs = (ExamRealSubject)jdlist.get(j-1);
						out.println("&nbsp;"+k+". "+mrs.getExamsubject().getSubjectname()+"&nbsp;&nbsp;&nbsp;<input type=checkbox ><br>");
						out.println("<input type=hidden name='subjectid' value='"+mrs.getExamsubject().getSubjectid()+"'>");
					//	Set keyset = mrs.getExamrsubkeys();
					//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
					//	for(int h =0;h<keyset.toArray().length;h++){
						//	ExamRsubKeys erk = (ExamRsubKeys)keyset.toArray()[h];
						//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
						//}
						//out.println("</ul>");
						k++;
					}
					out.println("</TD>"); 
					out.println("</TR>"); 
					out.println("</TABLE>"); 
				}
			%>		
		</TD>
	</TR>
	<TR bgcolor="#ffffff">
		<TD>
			<%

				if(dllist!=null){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;四. 问答题 每题"+dialogmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;

					for(int j=1;j<=dllist.size();j++){
						ExamRealSubject mrs = (ExamRealSubject)dllist.get(j-1);
						out.println("&nbsp;"+k+". "+mrs.getExamsubject().getSubjectname()+"<br>");
						out.println("<input type=hidden name='subjectid' value='"+mrs.getExamsubject().getSubjectid()+"'>");
						out.println("<table></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
						out.println("<td><textarea style='overflow:auto;width:500;height:100'></textarea></td>");
						out.println("</tr></table>");
					//	Set keyset = mrs.getExamrsubkeys();
					//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
					//	for(int h =0;h<keyset.toArray().length;h++){
						//	ExamRsubKeys erk = (ExamRsubKeys)keyset.toArray()[h];
						//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
						//}
						//out.println("</ul>");
						k++;
					}

					out.println("</TD>"); 
					out.println("</TR>"); 
					out.println("</TABLE>"); 
				}
			%>		
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>