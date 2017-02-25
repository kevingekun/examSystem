<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRealQuestions" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRqueKeys" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamPaper" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamPaperQuestions" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil"%>
<%@ page import="com.wondersgroup.falcon.beans.common.CommFunc"%> 
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />
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
</HEAD>
<% 
	String examid = request.getParameter("examid");	
	ExamPaper exampaper = examservice.findExamPaperById(examid); 
	String starttime = CommFunc.convertDT(new Date());
	Set set = exampaper.getPaperquestion();
	List oclist = new ArrayList();
	List mclist = new ArrayList();
	List jdlist = new ArrayList();
	List dllist = new ArrayList();
	List rclist = new ArrayList();	
	
	String ocmark = "0";
	String mcmark = "0";
	String jdmark = "0";
	String dlmark = "0";
	String rcmark = "0";
		
	if(set!=null&&set.toArray().length!=0){
		for(int i=0;i<set.toArray().length;i++){
			ExamPaperQuestions eps = (ExamPaperQuestions)set.toArray()[i];
			if(eps.getRealquestion()!=null){
				if(eps.getRealquestion().getExamquestype().getTypeid().equals("1")){
					ocmark = eps.getQuestionmarks();
					oclist.add(eps.getRealquestion());
				}else if(eps.getRealquestion().getExamquestype().getTypeid().equals("2")){
					mcmark = eps.getQuestionmarks();				
					mclist.add(eps.getRealquestion());
				}else if(eps.getRealquestion().getExamquestype().getTypeid().equals("3")){
					jdmark = eps.getQuestionmarks();				
					jdlist.add(eps.getRealquestion());
				}else if(eps.getRealquestion().getExamquestype().getTypeid().equals("4")){
					dlmark = eps.getQuestionmarks();				
					dllist.add(eps.getRealquestion());
				}else if(eps.getRealquestion().getExamquestype().getTypeid().equals("5")){
					rcmark = eps.getQuestionmarks();				
					rclist.add(eps.getRealquestion());
				}
				
			}
		}				
	}	
	
	String username = ((UserDetailsImpl)AcegiUtil.getUserDetails()).getUsername();	
	
	Date st = new Date();
	if(session.getAttribute("st")==null){
		session.setAttribute("st",st);
	}
	
%>

<script>
	//var starttime = new Date();
	
	var endtime = <%=( (Date)session.getAttribute("st") ).getTime()%> + 60*60*1000;
	//alert("starttime and endtime"+starttime.getTime()+" and "+endtime);
	function getCurrentTime(){
		var currenttime = new Date();	
		//alert(currenttime);
		var timems = endtime-currenttime.getTime();
		//alert(timems);
		if(timems<=0){
			//alert("时间到！请注意,系统将自动保存您的考卷！");
			autoSubmit();
			//return ;
			window.clearTimeout();
		}
		//else{
		//	window.onbeforeunload = savebeforeunload;
		//}
		var timeh = (Math.floor(timems/(1000*60*60))%24)<=0?0:(Math.floor(timems/(1000*60*60))%24);
		if(timeh/10<1){
			timeh="0"+timeh
		}
		var timem = (Math.floor(timems/(1000*60))%60)<=0?0:(Math.floor(timems/(1000*60))%60);
		if(timem/10<1){
			timem="0"+timem
		}
		var times = (Math.floor(timems/1000)%60)<=0?0:(Math.floor(timems/1000)%60);
		if(times/10<1){
			times="0"+times
		}
		document.getElementById("timeh").innerHTML=timeh+":";
		document.getElementById("timem").innerHTML=timem+":";		
		document.getElementById("times").innerHTML=times;		
		//alert(timeh+":"+timem+":"+times);
		setTimeout("getCurrentTime()",1000);
	}

	
	function changeTopage(){
//		parent.frames.topFrame.location="../top_exam.jsp";
	//	document.oncontextmenu= new Function("event.returnValue=false");
		
		getCurrentTime();

	}

	
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}	
	
</script>
<BODY  onload="changeTopage()" >
<TABLE border=0 align=center width=100% height=100% cellpadding=1 cellspacing=1 bgcolor="#D2E8FF">
	<tr bgcolor="#ffffff"><td align=center></td></tr>
	<tr bgcolor="#ffffff"　><td align=center><font style="font-size:16px"><b><%=exampaper.getExamname() %></b></font></td></tr>
	<tr bgcolor="#ffffff">
		<td align=left>
			试卷分数: <%=exampaper.getExammark() %> 分&nbsp;&nbsp;&nbsp;
			考试时间: <%=exampaper.getExamtime()%> 分钟&nbsp;&nbsp;&nbsp;
			参考人员: <%=username %>&nbsp;&nbsp;&nbsp;
			剩余时间:
			<font  id="remaintime"　>
				<font id="timeh" color="#0000ff"></font>
				<font id="timem" color="#0000ff"></font>
				<font id="times" color="#0000ff"></font>
			</font>
		</td>
	</tr>	

	<TR bgcolor="#ffffff">
		<TD>

			<%
				
				if(rclist!=null&&rclist.size()>0){
					System.out.println("111111111111111111111111111111");
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;四. 录音题 每题"+rcmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td>");
			%>
				<object align="middle"
					classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer" name="MediaPlayer"
					width="600" height="69">
					<param name="ShowStatusBar" value="-1">
					<param name="AutoStart" value="0">
					<param name="Filename" value="">
					<embed type="application/x-oleobject"
						codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
						flename="mp" src="\\172.16.31.16\voice\65470" width=200 height=50></embed>
				</object><br>
			<%					
					int k=1;
					System.out.println("rclist.size()===========>>>"+rclist.size());
					for(int i=1;i<=rclist.size();i++){
							ExamRealQuestions mrs = (ExamRealQuestions)rclist.get(i-1);
							out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"");
					
						//	Set keyset = mrs.getExamRqueKeys();
						//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						//	for(int h =0;h<keyset.toArray().length;h++){
							//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
							//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
							//}
							//out.println("</ul>");
							
								List urllist = callservice.getRecordUrl(mrs.getExamquestions().getQuestionname());	
								for (Iterator lt = urllist.iterator(); lt.hasNext();) {
									String[] array = (String[])lt.next();
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
									out.println("<a href='#' onclick = 'return mediaplayer(\"" + array[0] + "\")'>播放</a><br>");
//									out.println("</td>");
	//								out.println("<tr>");
								}
							out.println("<input type=hidden name='rcrealsubid' value='"+mrs.getRealquesid()+"'>");
							out.println("<table></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
							out.println("<td><textarea style='overflow:auto;width:500;height:100' name='recordkeys'></textarea></td>");
							out.println("</tr></table>");	
							k++;
						}
					}	
					out.println("</TD>"); 
					out.println("</TR>"); 
					out.println("</TABLE>"); 
			%>	
	
		</TD>
	</TR>		
	
	<TR bgcolor="#ffffff">
		<TD>
			<%
				if(oclist!=null&&oclist.size()!=0){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;单选题 每题"+ocmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					String ocrealkeys = "";
					for(int j=0;j<oclist.size();j++){
						ExamRealQuestions mrs = (ExamRealQuestions)oclist.get(j);
						out.println("<input type=hidden name='ocrealsubid' value='"+mrs.getRealquesid()+"'>");
						ocrealkeys+= mrs.getCorrectkey()+",";
						out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"");
						List  keyset = examservice.findRquesKeysByExamrquesid(mrs.getRealquesid());
						out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						for(int h =0;h<keyset.size();h++){
							ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
							out.println("<li style='margin-left:7;padding-left:0'><input type='radio' id='oc"+j+"' name='oc"+j+"' value="+h+" >"+erk.getExamkey().getKeycontent()+"<br>");
						}
						out.println("</ul><br>");
						k++;
					}	
					System.out.println("ocrealkeys===>>>"+ocrealkeys);
					out.println("<input type=hidden name='ocrealkeys' value='"+ocrealkeys+"'>");
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
				if(mclist!=null&&mclist.size()!=0){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;多选题 每题"+mcmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					String mcrealkeys = "";
					for(int j=0;j<mclist.size();j++){
						ExamRealQuestions mrs = (ExamRealQuestions)mclist.get(j);
						out.println("<input type=hidden name='mcrealsubid' value='"+mrs.getRealquesid()+"'>");
						mcrealkeys+=mrs.getCorrectkey()+",";
						out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"<br>");
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
					System.out.println("mcrealkeys==>>"+mcrealkeys);
					out.println("<input type=hidden name='mcrealkeys' value='"+mcrealkeys+"'>");
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

				if(jdlist!=null&&jdlist.size()!=0){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;判断题 每题"+jdmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					String jdrealkeys = "";
					for(int j=0;j<jdlist.size();j++){
						ExamRealQuestions mrs = (ExamRealQuestions)jdlist.get(j);
						out.println("<input type=hidden name='jdrealsubid' value='"+mrs.getRealquesid()+"'>");
						jdrealkeys+=mrs.getCorrectkey()+",";
						
						out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"<br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确<input type=radio name='jd"+j+"' value=0 ><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;错误<input type=radio name='jd"+j+"' value=1 ><br>");
						
					//	Set keyset = mrs.getExamrquekeys();
					//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
					//	for(int h =0;h<keyset.toArray().length;h++){
						//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
						//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
						//}
						//out.println("</ul>");
						k++;
					}
					System.out.println("jdrealkeys==>>>"+jdrealkeys);
					out.println("<input type=hidden name='jdrealkeys' value='"+jdrealkeys+"'>");
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
				if(dllist!=null&&dllist.size()!=0){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;问答题 每题"+dlmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					for(int j=0;j<dllist.size();j++){
						ExamRealQuestions mrs = (ExamRealQuestions)dllist.get(j);
						out.println("<input type=hidden name='dlrealsubid' value='"+mrs.getRealquesid()+"'>");					
						out.println("&nbsp;"+k+". "+mrs.getExamquestions().getQuestionname()+"<br>");
						out.println("<table></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
						out.println("<td><textarea style='overflow:auto;width:500;height:100' name='dialogkeys'></textarea></td>");
						out.println("</tr></table>");
					//	Set keyset = mrs.getExamrquekeys();
					//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
					//	for(int h =0;h<keyset.toArray().length;h++){
						//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
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
</BODY>
</HTML>