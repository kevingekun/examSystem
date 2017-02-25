<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRqueKeys" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamAnswers" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamAnswersQuestions" %>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />

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
	String examanswerid = request.getParameter("examanswerid");	
	
	ExamAnswers realandwerpage = examservice.finAnswerPaperById(examanswerid); 
	
	//System.out.println("examanserpager===>>>"+realandwerpage);

	Set set = realandwerpage.getExamansrques();
	//System.out.println("set.size()===>>>"+set.size());
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
			ExamAnswersQuestions eas = (ExamAnswersQuestions)set.toArray()[i];
			if(eas.getExamrealques()!=null){
				if(eas.getExamrealques().getExamquestype().getTypeid().equals("1")){
					ocmark = eas.getMarks();
					oclist.add(eas);
				}else if(eas.getExamrealques().getExamquestype().getTypeid().equals("2")){
					mcmark = eas.getMarks();				
					mclist.add(eas);
				}else if(eas.getExamrealques().getExamquestype().getTypeid().equals("3")){
					jdmark = eas.getMarks();				
					jdlist.add(eas);
				}else if(eas.getExamrealques().getExamquestype().getTypeid().equals("4")){
					dlmark = eas.getMarks();				
					dllist.add(eas);
				}else if(eas.getExamrealques().getExamquestype().getTypeid().equals("5")){
					rcmark = eas.getMarks();				
					rclist.add(eas);
				}
			}
		}				
	}	
%>
<BODY style="font-size: 12px;background-image: url(<%=request.getContextPath()%>/images/m_bg.gif);background-repeat: repeat-x;MARGIN: 5px;" >

<TABLE border=0 align=center width=100% height=100% cellpadding=1 cellspacing=1 bgcolor="#D2E8FF">
	<tr bgcolor="#ffffff"><td align=center></td></tr>
	<tr bgcolor="#ffffff"　><td align=center><font style="font-size:16px"><b><%=realandwerpage.getExampaper().getExamname() %></b></font></td></tr>
	<tr bgcolor="#ffffff">
		<td align=left>
			试卷分数: <%=realandwerpage.getExampaper().getExammark() %> 分&nbsp;&nbsp;&nbsp;
			考试时间: <%=realandwerpage.getExampaper().getExamtime()%> 分钟&nbsp;&nbsp;&nbsp;
			参考人员: <%=realandwerpage.getUsername() %>&nbsp;&nbsp;&nbsp;
			答卷得分: <font color="#ff0000"><%=realandwerpage.getWholemark() %></font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class=b href="<%=request.getContextPath() %>/exam/examonline.jsp" >返回</a>
		</td>
	</tr>	
	<TR bgcolor="#ffffff">
		<TD>
			<%
				if(rclist!=null&&rclist.size()!=0){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;录音题 每题"+rcmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					for(int j=0;j<rclist.size();j++){
						ExamAnswersQuestions eas = (ExamAnswersQuestions)rclist.get(j);
						out.println("<input type=hidden name='rcrealsubid' value='"+eas.getExamrealques().getRealquesid()+"'>");					
						out.println("&nbsp;"+k+". "+eas.getExamrealques().getExamquestions().getQuestionname()+"<br>");
						out.println("<table border=0></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
						out.println("<td><textarea style='overflow:visible;border=0' name='recordkeys'>"+(eas.getAnswerkey()==null?"":eas.getAnswerkey())+"</textarea></td>");
						out.println("</tr></table>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<br>");	
						out.println("<table border=0></tr><td valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
						out.println("<td><textarea style='overflow:visible;color:#0000ff;border=0' name='dialogkeys'>"+eas.getCorrectkey()+"</textarea></td>");
						out.println("</tr></table>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;得分："+(eas.getGainmark())+"<br>");									
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
				if(oclist!=null&&oclist.size()!=0){
					out.println("<TABLE border=0 width=100% height=100% cellpadding=0 cellspacing=0 > ");
					out.println("<TR bgcolor='#D2E8FF'>");
					out.println("<TD height=20><b>&nbsp;单选题 每题"+ocmark+"分</b></TD>");
					out.println("</TR>");
					out.println("<TR bgcolor='#ffffFF'><td><br>");
					int k=1;
					String ocrealkeys = "";
					for(int j=0;j<oclist.size();j++){
						ExamAnswersQuestions eas = (ExamAnswersQuestions)oclist.get(j);
						out.println("&nbsp;"+k+". "+eas.getExamrealques().getExamquestions().getQuestionname()+"");
						List  keyset = examservice.findRquesKeysByExamrquesid(eas.getExamrealques().getRealquesid());
						//Set keyset = eas.getExamrealques().getExamrqueskeys();
						out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						for(int h =0;h<keyset.size();h++){
							ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
							out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
						}
						out.println("</ul><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<font color='#0000ff'>"+eas.getCorrectkey()+"</font><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;考生答案：<font color="+(eas.getAnswerkey().equals(eas.getCorrectkey())?"#0000ff":"#ff0000")+">"+eas.getAnswerkey()+"</font><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;得分："+(eas.getGainmark())+"<br>");
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
						ExamAnswersQuestions eas = (ExamAnswersQuestions)mclist.get(j);
						out.println("&nbsp;"+k+". "+eas.getExamrealques().getExamquestions().getQuestionname()+"<br>");
						out.println("<table></tr><td></td></table>");
						List  keyset = examservice.findRquesKeysByExamrquesid(eas.getExamrealques().getRealquesid());
						//Set keyset = eas.getExamrealques().getExamrqueskeys();
						out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
						for(int h =0;h<keyset.size();h++){
							ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
							out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
						}
						out.println("</ul><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<font color='#0000ff'>"+eas.getCorrectkey()+"</font><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;考生答案：<font color="+(eas.getAnswerkey().equals(eas.getCorrectkey())?"#0000ff":"#ff0000")+">"+eas.getAnswerkey()+"</font><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;得分："+(eas.getGainmark())+"<br>");					
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
						ExamAnswersQuestions eas = (ExamAnswersQuestions)jdlist.get(j);
						
						out.println("&nbsp;"+k+". "+eas.getExamrealques().getExamquestions().getQuestionname()+"<br><br>");
						
					//	Set keyset = mrs.getExamrquekeys();
					//	out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
					//	for(int h =0;h<keyset.toArray().length;h++){
						//	ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[h];
						//	out.println("<li style='margin-left:7;padding-left:0'>"+erk.getExamkey().getKeycontent()+"<br>");
						//}
						//out.println("</ul>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<font color='#0000ff'>"+(eas.getCorrectkey().trim().equals("0")?"对":"错")+"</font><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;考生答案：<font color="+(eas.getAnswerkey().equals(eas.getCorrectkey())?"#0000ff":"#ff0000")+">"+(eas.getAnswerkey().trim().equals("0")?"对":( eas.getAnswerkey().equals(" ")?"":"错" ))+"</font><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;得分："+(eas.getGainmark())+"<br>");
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
						ExamAnswersQuestions eas = (ExamAnswersQuestions)dllist.get(j);
						out.println("<input type=hidden name='dlrealsubid' value='"+eas.getExamrealques().getRealquesid()+"'>");					
						out.println("&nbsp;"+k+". "+eas.getExamrealques().getExamquestions().getQuestionname()+"<br>");
						out.println("<table border=0></tr><td valign=top>&nbsp;&nbsp;&nbsp;答:</td>");
						out.println("<td><textarea style='overflow:visible;border=0' name='dialogkeys'>"+(eas.getAnswerkey()==null?"":eas.getAnswerkey())+"</textarea></td>");
						out.println("</tr></table>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<br>");	
						out.println("<table border=0></tr><td valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
						out.println("<td><textarea style='overflow:visible;color:#0000ff;border=0' name='dialogkeys'>"+eas.getCorrectkey()+"</textarea></td>");
						out.println("</tr></table>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;得分："+(eas.getGainmark())+"<br>");									
						k++;
					}
					out.println("</TD>"); 
					out.println("</TR>"); 
					out.println("</TABLE>"); 
				}
			%>		
		</TD>
	</TR>
	<tr><td align=center><a href="<%=request.getContextPath() %>/exam/examonline.jsp" class="SmallButton">返回</a></td></tr>
</TABLE>

</BODY>
</HTML>
<%System.out.println("HibernateUtil.issessionExist=========>>>>>"+HibernateUtil.isSessionExist());%>