<%@ page contentType = "text/html;charset=gb2312" %>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<%@ page import="java.util.Hashtable,java.util.Enumeration" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@page import="com.wondersgroup.falcon.beans.exam.TrainFileService" %>
<%@page import="com.wondersgroup.falcon.model.exam.TrainFile" %>
<%@page import="com.wondersgroup.falcon.model.exam.TrainFileFujian" %>
<%@page import="java.util.List,java.util.Set,java.util.HashSet" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<script type="text/javascript">
	function mediaplayer(wav_name){
		MediaPlayer.Filename = wav_name;
	}

</script>
<%
	TrainFileService tfs = new TrainFileService();
	String tfid = request.getParameter("tfid");
	
	TrainFile tf = tfs.findOneTrainFile(tfid);
	
	String contentstring = "";
	java.sql.Clob clob = tf.getContent();
	if(clob!=null){
		contentstring = clob.getSubString(1,(int)clob.length());
		tf.setContentstring(contentstring);
	}
%>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">   
		<tr> 
      		<td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
	      	<td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
	      	<td width="98%" background="../images/k1_03.gif">培训文件详细内容 &nbsp;&nbsp;&nbsp;
			</td>
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
								Set set = new HashSet();
								set = tf.getFujian();
								if(set!=null&&set.size()>0){
									if(tf.getType()==0){
										for(int i=0;i<set.size();i++){
											TrainFileFujian tff = (TrainFileFujian)set.toArray()[i];
											out.println("文件："+(i+1)+"&nbsp;&nbsp;<a href=# onclick=\"window.open('"+tff.getAddress()+"')\">"+tff.getName()+"</a>");
										}
									}else{
										for(int i=0;i<set.size();i++){
											TrainFileFujian tff = (TrainFileFujian)set.toArray()[i];
											List urllist = callservice.getRecordUrl(tff.getCode()); 	
											
											out.println("录音文件："+tff.getCode()+"&nbsp;&nbsp;<a href=# onclick = 'return mediaplayer(\"" + ((String[])urllist.get(0))[0] + "\")'>点击播放</a>");
										}										
									}
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
		if(tf.getType()==1){
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
		}
	%>
</body>    	
<form method = "post" action = "news_list.jsp" name="list">
		<input type=hidden name=selectitem value="">
</form>