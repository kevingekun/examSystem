<%@page contentType = "text/html;charset=gbk" %>
<%@page import="java.util.List,java.util.Iterator,java.util.ArrayList,java.util.Set" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<%@ page import="com.wondersgroup.falcon.model.call.*" %>
<%@ page import="com.wondersgroup.falcon.model.citizeninfo.History" %>
<%@ page import="com.wondersgroup.falcon.dao.call.*" %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*" %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.model.select.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
<%@ page import="com.wondersgroup.falcon.model.call.*" %>
<%@ page import="com.wondersgroup.falcon.model.report.*" %>
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
<%@ page import="com.wondersgroup.falcon.persistence.DBConnection" %>

<%@ page import="java.sql.*" %>

<link href="../inc/all.css" rel="stylesheet" type="text/css">



<html>
<head>
<title>无标题文档</title>
</head>
<script>
	function mediaplayer(wav_name){
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}
	function save(){
 		document.save_his.submit();
 		}
 			function xiazai(aa){
		window.location=aa;
	}
</script>
<%

              ZhijianDAO pit = new ZhijianDAO();   
              
              String callid = request.getParameter("callid");
             
              
            // pit.replace(callid);
      			
           
              

		String auth=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername();
	
	
	
	List urllist = callservice.getHisRecordUrl(callid);	
	
	
	
	
	request.setCharacterEncoding("GBK");
	String id3 = request.getParameter("callid");
	String startrecordtime = request.getParameter("time");
	
	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
						String url11="jdbc:oracle:thin:@172.16.31.22:1521:orcl";	
						String user11="falcon";
    					String password11="falcon";  
    					String auth1 = "";
    					String dafenmemo = "";
						Connection conn11 = DriverManager.getConnection(url11,user11,password11);						
						Statement stmt11 =	conn11.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt11.setFetchSize(1000);
						ResultSet rs11 = null; 
						try{
								
						String pSql="select t.auth,t.dafenmemo  from zhijianpingfen t where t.pingfenid='"+callid+"' ";
						
						
						//out.println(pSql);

						rs11=stmt11.executeQuery(pSql);
						//conn.commit();
						
																		
						while(rs11.next()){
						auth1 = rs11.getString("auth");
						dafenmemo = rs11.getString("dafenmemo");
						
						}
									
						}catch (Exception e){
						e.printStackTrace();
						}  
				
				finally{
						if(rs11!=null){
						rs11.close();	}	
						stmt11.close();
						conn11.close();  				
					}

%>


<body>
<TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0><!--DWLayoutTable-->
	<TBODY>
		<TR>
			<TD vAlign=top width=16 rowSpan=2><IMG height=14 src="../images/right_01.gif"></TD>
			<TD vAlign=top colSpan=2 height=10>
			    <TABLE cellSpacing=0 cellPadding=0 width="100%" background=../images/right_02.gif border=0><!--DWLayoutTable-->
			  		<TBODY>
			      		<TR>
			        		<TD width=400 height=10></TD>
			        	</TR>
			        </TBODY>
			    </TABLE>
		   	</TD>
		  	<TD vAlign=top width=24 rowSpan=2><IMG height=14 src="../images/right_03.gif" width=24></TD></TR>
		<TR>
		  	<TD width=400 height=4></TD>
		  	<TD width=400></TD></TR>
		<TR>
		  	<TD vAlign=top background=../images/right_06.gif>
		    	<TABLE cellSpacing=0 cellPadding=0 width="100%" 
		    		background=../images/right_06.gif border=0><!--DWLayoutTable-->
					<TBODY>
		            	<TR>
		                	<TD >&nbsp;</TD>
		                </TR>
		            </TBODY>
		         </TABLE>
		    </TD>
			<TD colspan="2" vAlign=top>
				<table border=0>
					<%
					UserDAO	userDAO = new UserDAO();
					User user = new User();	
					String agentid = "";
						for (Iterator lt = urllist.iterator(); lt.hasNext();) {
							String[] array = (String[])lt.next();
							agentid=array[3];
							user = userDAO.getUserByAgentid(array[3]);
							out.println("<tr>");
							//out.println("<td width='10%' align='center'>名称</td>");
							//out.print("<td width='10%'>");
							//out.print(array[1]);
							//out.println("</td>");							
							out.println("<td width='20%' align='center'>开始时间：</td>");
							out.print("<td width='20%'>");
							out.print(array[2]);
							out.println("</td>");
							out.println("<td width='10%' align='center'>电话号码：</td>");
							out.print("<td width='10%'>");
							//if(user!=null)
							out.print(array[4]);
							//elseout.print("");
							out.println("</td>");
							out.print("<td >");
							out.print("<input type=button class=submit_2 value='播 放' onclick='return mediaplayer(\"" + array[0] + "\")'>");
							//System.out.println("array[0]==============>>>>>>>>>>>>>>>"+array[0]);
							out.println("</td>");
							out.print("<td width='10%'><input type=button class=submit_2 value='下 载' onclick='xiazai(\"" + array[0] + "\")'>");
							out.println("</td>");
							out.println("<tr>");
						}
			   		%>
				
			</table>
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
	</TD>
	<TD background=../images/right_05.gif vAlign=top>
		<TABLE cellSpacing=0 cellPadding=0 background=../images/right_05.gif
			border=0>
			<!--DWLayoutTable-->
			<TBODY>
				<TR>
					<TD align=left width=24>
						&nbsp;
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</TD>
	</TR>
	<TR>
	<TD vAlign=top height=17>
		<IMG height=17 src="../images/right_08.gif" width=16>
	</TD>
	<TD vAlign=top colSpan=2>
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background=../images/right_09.gif border=0>
			<!--DWLayoutTable-->
			<TBODY>
				<TR>
					<TD width=400 height=17>
						&nbsp;
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</TD>
	<TD vAlign=top>
		<IMG height=17 src="../images/right_10.gif" width=24>
	</TD>
</TR>
</TBODY>
</TABLE>
                      
<br>
                      

<form action="zhijiandafen.jsp" name="dafen">

<input type="hidden" name="startrecordtime" value="<%=startrecordtime %>">
<input type=hidden name=auth value=<%=auth %>>
<input type=hidden name=callid value=<%=callid %>>



  
<table width="50%" border="0" align="center" cellpadding="4" cellspacing="0" bgcolor="#CCCCCC">
      <tr>
        <td width="65%" bgcolor="#92D6E4"><div align="left"><strong>评估内容</strong></div></td>
       
        </tr>
      <tr>
        <td height="2" colspan="3" bgcolor="#28B9FF"></td>
      </tr>
     
        <%
        
        String riqi = "";
        String dafenren = "";
        ChouyangDAO chouyangService = new ChouyangDAO();
      	List mingxilist;
        mingxilist = chouyangService.getMingxi(callid);
        
       
        
        if (!(mingxilist==null||mingxilist.equals(""))){%>
		<%
		for(int i=0;i<mingxilist.size();i++){
		
			ZhijianpingfenmxVO rec = (ZhijianpingfenmxVO)mingxilist.get(i);
			riqi = rec.getRiqiqi().toString();
			String kaoheid="";
			kaoheid=rec.getKaoheid();
			if(kaoheid==null || kaoheid.equals("")){
					 kaoheid="";
				 }
       %>
       	<table width="50%" border="0" align="center" cellpadding="3"
		cellspacing="1" bgcolor="#9CDBF6">
		<%	if(i==29){%>
	<tr><td height='6' colspan='4'  bgcolor='#0000FF'>  </td></tr>
	
	 <%}	%>
       <tr bgcolor="#E4F5FC" height="23">
				<td align="center" width=5%>
				<%if(kaoheid.equals("10")||kaoheid.equals("14")||kaoheid.equals("17")||kaoheid.equals("21")||kaoheid.equals("24")){%>
				<%=rec.getName() %>(<%=rec.getValue() %>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	        <%}else{%>
				<%=rec.getName() %>(<%=rec.getValue() %>)
				<%	}%>
					
				</td>			
				<td align="center" width=4%>
				<%if(kaoheid.equals("10")||kaoheid.equals("14")||kaoheid.equals("17")||kaoheid.equals("21")||kaoheid.equals("24")){%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	        <%}else{%>
				<%=Double.parseDouble(rec.getRealmark()) %>
				<%	}%>
				</td>
				
			
	</tr>
	
			<%		
				}
			%>
         <%		
				}
			%>
       <tr>
       
        <td bgcolor="#EAF8FD" align=center colspan="4"><strong><a href="javascript:history.back(0)" class="bg"><img src="images/bt_fh.gif" width="48" height="21" border="0" /></a></strong></td>
        
      </tr>
      </table>  
    </table>
   
   </form>
    
  
   <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"> 

	
	<tr>
       <td bgcolor="#ebf3f6" align=center><strong>打分日期:</strong>
       <td bgcolor="#ebf3f6" align=center><strong><%=riqi %></strong>
      </tr>
      
      <tr>
       <td bgcolor="#ebf3f6" align=center><strong>打分说明:</strong>
       <td bgcolor="#ebf3f6" align=center><strong><%=dafenmemo==null?"":dafenmemo %></strong>
      </tr>
       <tr>
      <tr>
<tr>


</body>
      
        
    

