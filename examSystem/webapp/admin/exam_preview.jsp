<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.List,java.util.Set,java.util.Map,java.util.ArrayList,java.util.Iterator" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRealQuestions" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRqueKeys" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamPaper" %>
<%@page import="com.wondersgroup.falcon.beans.common.CommFunc" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamQuestionType" %>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple"/>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<script src="../Scripts/AC_ActiveX.js" type="text/javascript"></script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</HEAD>
<% 
	List oclist = new ArrayList();
	List mclist = new ArrayList();
	List jdlist = new ArrayList();
	List dllist = new ArrayList();
	List rclist = new ArrayList();
	
//	Integer onechoicemark=(Integer)request.getAttribute("onechoicemark");
//	Integer manychoicemark=(Integer)request.getAttribute("manychoicemark");		
//	Integer judgemark=(Integer)request.getAttribute("judgemark");	
//	Integer dialogmark=(Integer)request.getAttribute("dialogmark");

	int onechoicemark=0;
	int manychoicemark=0;		
	int judgemark=0;	
	int dialogmark=0;
	int recordmark=0;
	
	Map groupmap = (Map)request.getAttribute("groupmap");
	List markmap = (List)request.getAttribute("markmap");
	

	List subtypelist = examservice.findQuestionType(1);
	System.out.println("====================="+subtypelist.size());
	for(int i=0;i<subtypelist.size();i++){
		ExamQuestionType est = (ExamQuestionType)subtypelist.get(i);
		if(est.getIdentiname().equals("single")){
			oclist = (List)groupmap.get("single");
			onechoicemark = (Integer.valueOf(markmap.get(i).toString())).intValue() ;
		}else if(est.getIdentiname().equals("multiple")){
			mclist = (List)groupmap.get("multiple");
			manychoicemark = (Integer.valueOf(markmap.get(i).toString())).intValue() ;
		}else if(est.getIdentiname().equals("judge")){
			jdlist = (List)groupmap.get("judge");
			judgemark = (Integer.valueOf(markmap.get(i).toString())).intValue() ;
		}else if(est.getIdentiname().equals("question")){
			dllist = (List)groupmap.get("question");
			dialogmark = (Integer.valueOf(markmap.get(i).toString())).intValue() ;
		}else if(est.getIdentiname().equals("record")){
			rclist = (List)groupmap.get("record");
			recordmark = (Integer.valueOf(markmap.get(i).toString())).intValue() ;
		}
		
		
	}

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
	
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}
</script>
<BODY >
<br>
<form name="crepaperform" action="paperServlet" method="post">
<input type=hidden name="actionType" value="savepaper">
<input type=hidden name="exammark" value="<%=exampaper.getExammark() %>">
<input type=hidden name="examname" value="<%=exampaper.getExamname() %>">
<input type=hidden name="examtime" value="<%=exampaper.getExamtime()%>">
<input type=hidden name="effectstarttime" value="<%=CommFunc.dateToStr(exampaper.getEffectstarttime())%>">
<input type=hidden name="effectendtime" value="<%=CommFunc.dateToStr(exampaper.getEffectendtime())%>">

<input type=hidden name="recordmark" value="<%=recordmark%>">
<input type=hidden name="onechoicemark" value="<%=onechoicemark%>">
<input type=hidden name="manychoicemark" value="<%=manychoicemark%>">
<input type=hidden name="judgemark" value="<%=judgemark%>">
<input type=hidden name="dialogmark" value="<%=dialogmark%>">
<input type=hidden name="recordmark" value="<%=recordmark%>">
<TABLE border=0 align=center width=99% height=100% cellpadding=1 cellspacing=1 bgcolor="#D2E8FF">
	<tr bgcolor="#ffffff"><td align=center></td></tr>
	<tr bgcolor="#ffffff"><td align=center><font style="font-size:16px"><b><%=exampaper.getExamname() %></b></font></td></tr>
	<tr bgcolor="#ffffff">
		<td align=left>
			试卷分数:<%=exampaper.getExammark() %> 分&nbsp;&nbsp;&nbsp;
			考试时间:<%=exampaper.getExamtime()%> 分钟&nbsp;&nbsp;&nbsp;
			剩余时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onClick="savePaper()" class="b">保存试卷</a>
		</td>
	</tr>	
	<TR bgcolor="#ffffff">
		<TD>

			<%
				if(rclist!=null){

					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;四. 录音题 每题"+recordmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td>");
			%>
				<script type="text/javascript">
AC_AX_RunContent( 'align','middle','classid','CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95','id','MediaPlayer','name','MediaPlayer','width','600','height','69','type','application/x-oleobject','codebase','http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701','flename','mp','src','\\\\172.16.31.16\\voice\\65470','showstatusbar','-1','autostart','0','filename','' ); //end AC code
</script><noscript><object align="middle"
					classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer" name="MediaPlayer"
					width="600" height="69">
					<param name="ShowStatusBar" value="-1">
					<param name="AutoStart" value="0">
					<param name="Filename" value="">
					<embed type="application/x-oleobject"
						codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
						flename="mp" src="\\172.16.31.16\voice\65470" width=200 height=50></embed>
				</object></noscript><br>
			<%					
					int k=1;
					for(int i=1;i<=rclist.size();i++){
						List list = (List)rclist.get(i-1);
						for(int j=1;j<=list.size();j++){
							
							ExamRealQuestions mrs = (ExamRealQuestions)list.get(j-1);
							System.out.println("mrs.getid and mrs.getsubjtect=====>>>>"+mrs.getRealquesid()+" and "+mrs.getExamquestions());
							out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"");
							
						//	Set keyset = mrs.getExamRqueKeys();
						//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						//	for(int h =0;h<keyset.toArray().length;h++){
							//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
							//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
							//}
							//out.println("</ul>");
							
							//liangkd
								//List urllist = callservice.getRecordUrl(mrs.getExamquestions().getQuestionname()); 	
								//liangkd
								//for (Iterator lt = urllist.iterator(); lt.hasNext();) {
								//liangkd
								//	String[] array = (String[])lt.next();
								
								
								
//									out.println("<tr>");
//									out.println("<td width='10%' align='center'>名称</td>");
//									out.print("<td width='10%'>");
//									out.print(array[1]);
//									out.println("</td>");							
//									out.println("<td width='20%' align='center'>时间</td>");
//									out.print("<td width='20%'>");
//									out.print(array[2]);
//									out.println("</td>");
//									out.println("<td width='10%' align='center'>工号</td>");
//									out.print("<td width='10%'>");
//									out.print(array[3]);
//									out.println("</td>");
	//								out.print("<td >");
				//liangkd
				//					out.println("<a href='#' onclick = 'return mediaplayer(\"" + array[0] + "\")'>播放</a><br>");
//									out.println("</td>");
	//								out.println("<tr>");
								//}
							out.println("<input type=hidden name='subjectid' value='"+mrs.getRealquesid()+"'>");
							out.println("<table></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
							out.println("<td><textarea style='overflow:auto;width:500;height:100'></textarea></td>");
							out.println("</tr></table>");	
							k++;
						}
					}	
					%>

					<%
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
				if(oclist!=null){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;单选题 每题"+onechoicemark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					for(int i=1;i<=oclist.size();i++){
						List list = (List)oclist.get(i-1);
						for(int j=1;j<=list.size();j++){
							ExamRealQuestions mrs = (ExamRealQuestions)list.get(j-1);
							out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"");
							out.println("<input type=hidden name='subjectid' value='"+mrs.getRealquesid()+"'>");
							List  keyset = examservice.findRquesKeysByExamrquesid(mrs.getRealquesid());
							out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
							for(int h =0;h<keyset.size();h++){
								ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
								out.println("<li style='margin-left:7;padding-left:0'><input type='radio' id='oc"+j+"' name='oc"+j+"' value="+h+" >"+erk.getExamkey().getKeycontent()+"<br>");
							}
							out.println("</ul><br>");
							k++;
						}
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
					for(int i=1;i<=mclist.size();i++){
						List list = (List)mclist.get(i-1);
						for(int j=1;j<=list.size();j++){
							ExamRealQuestions mrs = (ExamRealQuestions)list.get(j-1);
							out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"<br>");
							out.println("<input type=hidden name='subjectid' value='"+mrs.getRealquesid()+"'>");
							out.println("<table></tr><td></td></table>");
							List  keyset = examservice.findRquesKeysByExamrquesid(mrs.getRealquesid());
							out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
							for(int h =0;h<keyset.size();h++){
								ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
								out.println("<li style='margin-left:7;padding-left:0'><input type=checkbox name='mc"+j+"' value='"+h+"'>"+erk.getExamkey().getKeycontent()+"<br>");
							}
							out.println("</ul>");
							k++;
						}
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
					for(int i=1;i<=jdlist.size();i++){
						List list = (List)jdlist.get(i-1);
						for(int j=1;j<=list.size();j++){
							ExamRealQuestions mrs = (ExamRealQuestions)list.get(j-1);
							out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"&nbsp;&nbsp;&nbsp;<input type=checkbox ><br>");
							out.println("<input type=hidden name='subjectid' value='"+mrs.getRealquesid()+"'>");
						//	Set keyset = mrs.getExamRqueKeys();
						//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						//	for(int h =0;h<keyset.toArray().length;h++){
							//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
							//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
							//}
							//out.println("</ul>");
							k++;
						}
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
					for(int i=1;i<=dllist.size();i++){
						List list = (List)dllist.get(i-1);
						for(int j=1;j<=list.size();j++){
							ExamRealQuestions mrs = (ExamRealQuestions)list.get(j-1);
							out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"<br>");
							out.println("<input type=hidden name='subjectid' value='"+mrs.getRealquesid()+"'>");
							out.println("<table></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
							out.println("<td><textarea style='overflow:auto;width:500;height:100'></textarea></td>");
							out.println("</tr></table>");
						//	Set keyset = mrs.getExamRqueKeys();
						//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						//	for(int h =0;h<keyset.toArray().length;h++){
							//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
							//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
							//}
							//out.println("</ul>");
							k++;
						}
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