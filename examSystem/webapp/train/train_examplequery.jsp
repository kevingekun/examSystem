<%@ page contentType = "text/html;charset=gb2312" %>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<%@page import="java.util.List,java.util.Iterator" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRqueKeys" %>
<%@page import="com.wondersgroup.falcon.beans.exam.TrainExampleService" %>
<%@page import="com.wondersgroup.falcon.model.exam.TrainExample" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRealQuestions" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple"/>
<script type="text/javascript">
	function mediaplayer(wav_name){
		MediaPlayer.Filename = wav_name;
	}

</script>
<%
	TrainExampleService tfs = new TrainExampleService();
	String tfid = request.getParameter("tfid");
	
	TrainExample tf = tfs.findOneTrainExample(tfid);
	
	String contentstring = "";
	java.sql.Clob clob = tf.getContent();
	if(clob!=null){
		contentstring = clob.getSubString(1,(int)clob.length());
		tf.setContentstring(contentstring);
	}
	
	ExamRealQuestions erq = tf.getRealquestion();
%>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">   
		<tr> 
      		<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
	      	<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
	      	<td width="98%" background="../images/k1_03.gif">培训文件详细内容 &nbsp;&nbsp;&nbsp;</td>
	      	<td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
      	</tr>       
      	<tr> 
          	<td background="../images/k1_05.gif"></td>
          	<td  colspan="2" valign="top"> 
             	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
					<tr bgcolor="#FFFFFF" > 
                   		<td height="2" colspan="1" bgcolor="#F1F8FE" align=center style="font-size:16px"><b><%=tf.getTitle()%></b></td>
                	</tr>
                 	<tr >
                		<td bgcolor=#ffffff align=center style="font-size:14px">时间：<%=(tf.getSendtime()==null?"":tf.getSendtime().toString().substring(0,10))%></td>
					</tr>
                 	<tr >
                		<td bgcolor=#ffffff style="font-size:14px">
               			<% 
             				if(erq.getExamquestype().getIdentiname().equals("single")){
								out.println(""+erq.getExamquestions().getQuestionname()+"");
								List  keyset = examservice.findRquesKeysByExamrquesid(erq.getRealquesid());
								out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
								for(int h =0;h<keyset.size();h++){
									ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
									out.println("<li style='margin-left:7;padding-left:0'><input type='radio' value="+h+" >"+erk.getExamkey().getKeycontent()+"<br>");
								}
								out.println("</ul><br>");
								out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<font color='#0000ff'>"+erq.getCorrectkey()+"</font><br>");
							}else if(erq.getExamquestype().getIdentiname().equals("multiple")){
								out.println(""+erq.getExamquestions().getQuestionname()+"<br>");
								out.println("<table></tr><td></td></table>");
								List  keyset = examservice.findRquesKeysByExamrquesid(erq.getRealquesid());
								out.println("<ul style='list-style-type:upper-alpha;margin-top:0;margin-bottom:0;'>");
								for(int h =0;h<keyset.size();h++){
									ExamRqueKeys erk = (ExamRqueKeys)keyset.get(h);
									out.println("<li style='margin-left:7;padding-left:0'><input type=checkbox >"+erk.getExamkey().getKeycontent()+"<br>");
								}
								out.println("</ul>");
								out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<font color='#0000ff'>"+erq.getCorrectkey()+"</font><br>");
							}else if(erq.getExamquestype().getIdentiname().equals("judge")){
								out.println(""+erq.getExamquestions().getQuestionname()+"<br><br>");
								out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<font color='#0000ff'>"+(erq.getCorrectkey().trim().equals("0")?"对":"错")+"</font><br>");
							}else if(erq.getExamquestype().getIdentiname().equals("question")){
								out.println(""+erq.getExamquestions().getQuestionname()+"<br>");
								out.println("&nbsp;&nbsp;&nbsp;&nbsp;正确答案：<br>");	
								out.println("<table border=0></tr><td valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
								out.println("<td><textarea style='overflow:visible;color:#0000ff;border=0' name='dialogkeys'>"+erq.getCorrectkey()+"</textarea></td>");
								out.println("</tr></table>");
							}else if(erq.getExamquestype().getIdentiname().equals("record")){
								out.println("&nbsp;&nbsp;题 目 名："+erq.getExamquestions().getQuestionname()+"&nbsp;&nbsp;");
								List urllist = callservice.getRecordUrl(erq.getExamquestions().getQuestionname());	
								for (Iterator lt = urllist.iterator(); lt.hasNext();) {
									String[] array = (String[])lt.next();
									out.println("<a href='#' onclick = 'return mediaplayer(\"" + array[0] + "\")'>播放</a><br>");
								}
								out.println("&nbsp;&nbsp;正确答案：<br>");	
								out.println("<table border=0></tr><td valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
								out.println("<td><textarea style='overflow:visible;color:#0000ff;border=0' name='dialogkeys'>"+erq.getCorrectkey()+"</textarea></td>");
								out.println("</tr></table>");
							}
               			%>
						</td>
					</tr>					               	
                	<tr >
                		<td bgcolor=#ffffff style="font-size:14px">
   							<br>
                			<textarea style="width:100%;border:0;overflow:visible;font-size:14px;line-height:20px;"><%=tf.getContentstring()%></textarea>
                			<p align=center>             			
                				<a href="#" onclick="history.back(1)">[返回]</a>
                			</p>
                		</td>
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
	
	<% 
		//if(tf.getType()==1){
	%>
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
		
	<%	
	//	}
	%>
</body>    	
<form method = "post" action = "news_list.jsp" name="list">
		<input type=hidden name=selectitem value="">
</form>