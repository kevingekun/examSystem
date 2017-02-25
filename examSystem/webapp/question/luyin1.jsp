<%@page contentType = "text/html;charset=utf-8" %>
<%@page import="java.util.List,java.util.Iterator,java.util.ArrayList,java.util.Set" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<%@ page import="com.wondersgroup.falcon.model.call.Zhijiankaohe" %>
<%@ page import="com.wondersgroup.falcon.model.citizeninfo.History" %>
<%@ page import="com.wondersgroup.falcon.dao.call.*" %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.model.select.*" %>
<%@ page import="com.wondersgroup.falcon.model.call.*" %>
<%@ page import="com.wondersgroup.falcon.model.zhijian.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil" %> 
<%@ page import="com.wondersgroup.falcon.persistence.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.wondersgroup.falcon.model.call.Zhijianpingfen" %>
<%@ page import="com.wondersgroup.falcon.dao.auth.*,java.sql.*"%>
<%@ page import="com.wondersgroup.popedom.bo.*"%>
<%@ page import="com.wondersgroup.popedom.dao.*"%>


<html>
<head>
<title>无标题文档</title>
<link href="../inc2/all.css" rel="stylesheet" type="text/css">
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
             request.setCharacterEncoding("utf-8");
             //String dafenmemo1 = request.getParameter("dafenmemo1");
           
              ZhijianDAO pit = new ZhijianDAO();   
              
              String callid = request.getParameter("callid");
               String dafenmemo1="";
             Zhijianpingfen pf = new Zhijianpingfen();
             if( callid != null ){
					pf = pit.getMarkByID(callid);
					}
             if( pf!=null&&pf.getDafenmemo() != null ){
					 dafenmemo1 = pf.getDafenmemo();
					}
              
           //  pit.replace(callid);    

		Authority authority =new Authority();
		String auth=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername();
   			Set temp= ((UserDetailsImpl)util.getUserDetails()).getUser().getAuthorities();
   			String quanxian="";
   			String privilige="";
	
	
	
	List urllist = callservice.getHisRecordUrl(callid);	
	HistoryDAO his = new HistoryDAO();
	Select1 h = his.getBySelect(callid);
	
	
	
	String id3 = request.getParameter("callid");
	String startrecordtime = request.getParameter("time");
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date boundary = dateFormat.parse("2010-10-09");
	Date startDate = null;
	
	ZhijianDAO pi = new ZhijianDAO();
	String type = "";
	String gonghao = "";
	String phoneid = "";
	String callid1 = "";
	String comments = "";
	String startime = "";
	String endtime = "";
	String sort = "";
%>
	<%
		List all = new ArrayList();
		Zhijiankaohe pn = new Zhijiankaohe();
		Zhijiankaohe pn1 = new Zhijiankaohe();
		Zhijiankaohe pn2 = new Zhijiankaohe();
		
		HisZhijiankaohe hispn = new HisZhijiankaohe();
		HisZhijiankaohe hispn1 = new HisZhijiankaohe();
		HisZhijiankaohe hispn2 = new HisZhijiankaohe();
		
		ZhijiankaoheDAO dao = new ZhijiankaoheDAO();
		
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
					EUser user1 = new EUser();
					
					EuserDao eudao=new EuserDao();
					UserDAO udao = new UserDAO();
							user1 = eudao.getUserByUsername(auth1);	
							
							
		%>


<body>

                    
<br>
                      
<TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0><!--DWLayoutTable-->
	<TBODY>
	 <tr>
       
        <td align=center colspan="4"><strong><a href="javascript:history.back(0)" class="bg"><img src="newimages/bt_fh.gif" width="48" height="21" border="0" /></a></strong></td>
        
      </tr>
		<TR>
			<TD vAlign=top width=16 rowSpan=2><IMG height=14 src="../newimages/right_01.gif"></TD>
			<TD vAlign=top colSpan=2 height=10>
			    <TABLE cellSpacing=0 cellPadding=0 width="100%" background=../newimages/right_02.gif border=0><!--DWLayoutTable-->
			  		<TBODY>
			      		<TR>
			        		<TD width=400 height=10></TD>
			        	</TR>
			        </TBODY>
			    </TABLE>
		   	</TD>
		  	<TD vAlign=top width=24 rowSpan=2><IMG height=14 src="../newimages/right_03.gif" width=24></TD></TR>
		<TR>
		  	<TD width=400 height=4></TD>
		  	<TD width=400></TD></TR>
		<TR>
		  	<TD vAlign=top background=../newimages/right_06.gif>
		    	<TABLE cellSpacing=0 cellPadding=0 width="100%" 
		    		background=../newimages/right_06.gif border=0><!--DWLayoutTable-->
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
					EUser user = new EUser();	
					String agentid = "";
					
					String userid = new ManuCallDAO().getUserIdByCallId(callid,startrecordtime);
					
					if(userid != null){
						user = eudao.getUserByID(userid);
					}
					
						for (Iterator lt = urllist.iterator(); lt.hasNext();) {
							String[] array = (String[])lt.next();
							agentid=array[3];
							
							System.out.println("工号："+array[3]);
							
							if(userid == null){
								user = eudao.getUserByAgentid(array[3]);
								
							}
							
							
							
							out.println("<tr>");
							//out.println("<td width='10%' align='center'>名称</td>");
							//out.print("<td width='10%'>");
							//out.print(array[1]);
							//out.println("</td>");							
							out.println("<td width='20%' align='center'>来电时间</td>");
							out.print("<td width='20%'>");
							out.print(array[2]);
							out.println("</td>");
							out.println("<td width='10%' align='center'>来电号码</td>");
							out.print("<td width='10%'>");
							out.print(array[4]);
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
	<TD background=../newimages/right_05.gif vAlign=top>
		<TABLE cellSpacing=0 cellPadding=0 background=../newimages/right_05.gif
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
		<IMG height=17 src="../newimages/right_08.gif" width=16>
	</TD>
	<TD vAlign=top colSpan=2>
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background=../newimages/right_09.gif border=0>
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
		<IMG height=17 src="../newimages/right_10.gif" width=24>
	</TD>
</TR>
</TBODY>
</TABLE>
<%System.out.print("time22222222222222================>"+startrecordtime);%>
<form action="zhijiandafen.jsp" name="dafen">
<input type=hidden name=gonghao value=<%=h.getAgentid() %>>
<input type="hidden" name="startrecordtime" value="<%=startrecordtime %>">
<input type=hidden name=auth value=<%=auth %>>
<input type=hidden name=callid value=<%=callid %>>
<table width="99%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#ffffff">
		
			<tr>
			<%if(auth.equals("015")) {%>
			<td align="left">
					打分人： 
				<%=user1.getRealname() %>
				</td>
				<%} %>
				<td align="left">
					评分说明： 

				
					<TEXTAREA id=dafenmemo name=dafenmemo rows=8 cols=55 style="overflow:auto" ><%=dafenmemo1 == null ? "" : dafenmemo1%></TEXTAREA>
				</td>
			</tr>
	
		</table>
<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">

  <tr>
    <td width="3"><img src="newimages/k1_01.gif" width="3" height="30" /></td>
    <td width="21" background="newimages/k1_03.gif"><img src="newimages/k1_02.gif" width="21" height="30" /></td>
    <td width="50%" background="newimages/k1_03.gif"><strong>打分</strong></td>
    <td width="50%" background="newimages/k1_03.gif">&nbsp;</td>
    <td width="4" background="newimages/k1_03.gif"><img src="newimages/k1_04.gif" width="4" height="30" /></td>
  </tr>
  <tr>
    <td background="newimages/k1_05.gif"></td>
    <td colspan="3" valign="top"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" bgcolor="#CCCCCC">
      <tr>
        <td width="65%" bgcolor="#92D6E4"><div align="left"><strong>评估内容</strong></div></td>
        <td width="35%" bgcolor="#92D6E4"><div align="left">打 分</div></td>
        </tr>
      <tr>
        <td height="2" colspan="3" bgcolor="#28B9FF"></td>
      </tr>
      
      <%
							if(startime != null && !"".equals(startime)){
								startDate = dateFormat.parse(startime);
							}else{
								startDate = dateFormat.parse(dateFormat.format(new Date()));
							}
						%>
      
        <%
      	
		
        DBConnection db = new DBConnection();
		ResultSet rs = null;
		String strSql="";
		String fenshu="";
		double fenshudb=0.0;
		String fenshuinit="";
		
		List tree = null;
		List subtree = null;
		List subtree1 = null;
		Long id=new Long(0);
		Long id1=new Long(0);
		Long id2=new Long(0);
		
		if(startDate.before(boundary)){
			tree = dao.getFirstFromHis();    //在2010-09-01之前，采用老的考核标准
			
			for(int i=0;i<tree.size();i++){
	      		hispn=(HisZhijiankaohe)tree.get(i);
	      		out.print("<tr><td width='65%' bgcolor='#ffffff'><img src='newimages/TreeBranch1.gif' width='18' height='18'>");
	      		out.print(hispn.getName()+"("+hispn.getValue()+"%)</td>");
	      		id=hispn.getId();
	      		if(dao.getChildrenFromHis(id)==null||dao.getChildrenFromHis(id).size()==0){
	      			out.print("<td bgcolor='#ffffff'><input type='radio' name="+hispn.getId()+" value=4>好<input type='radio' name="+hispn.getId()+" value=3>较好<input type='radio' name="+hispn.getId()+" value=2>一般<input type='radio' name="+hispn.getId()+" value=1>较差<input type='radio' name="+hispn.getId()+" value=0>差</td></tr>");
	      			out.print("<tr><td height='3' colspan='3' background='newimages/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
	      			
	      			strSql="select t.mark from zhijianpingfenmingxi t where t.pingfenid='"+callid+"' and t.kaoheid='"+hispn.getId()+"' and t.riqi=(select max(w.riqi) from zhijianpingfenmingxi w where w.pingfenid='"+callid+"' and w.kaoheid='"+pn.getId()+"' )";
	      				//out.println(strSql);
	      				rs = db.execQuery(strSql);
	      			    if (rs.next()) {
	      			    	fenshu=rs.getString(1);
	      			    	System.out.println("fenshu===============>"+fenshu);
	      			    }
	      			    rs.close();
	      			    if(fenshu==null || fenshu.equals("")){}
	      			    else{
	      			    fenshudb=Double.parseDouble(fenshu)/Integer.parseInt(hispn1.getValue())*4;
	      			    if(hispn1.getParentid().equals("50")){
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			    else{
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			     double fenshusec = Math.round(Double.parseDouble(fenshuinit));
	      				out.println("<script language=\"javascript\">");
	          			out.println(" var length = dafen.radio"+hispn1.getId()+".length ");
	          			out.println(" for(var i=0;i<length;i++)");
	          			out.println("{ if(dafen.radio"+hispn.getId()+"[i].value=="+fenshusec+") ");
	          			out.println("   {dafen.radio"+hispn.getId()+"[i].checked=true; }");
	          			out.println("} ");
	          			
	          			out.println("</script>");
	      			    }
	      		}
	      		else if(dao.getChildrenFromHis(id)!=null&&dao.getChildrenFromHis(id).size()!=0){
	      		    out.print("<td bgcolor='#ffffff'></td></tr>");
	      			out.print("<tr><td height='3' colspan='3'  bgcolor='#FFFFFF'></td></tr>");
	      			subtree=dao.getChildrenFromHis(id);
					for(int j=0;j<subtree.size();j++){
						hispn1=(HisZhijiankaohe)subtree.get(j);
						out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='newimages/TreeBranch1.gif' width='18' height='18'>");
						out.print(hispn1.getName()+"("+hispn1.getValue()+"%)</td>");
	      				id1=hispn1.getId();
	      				if(dao.getChildrenFromHis(id1)==null||dao.getChildrenFromHis(id1).size()==0){
	      				out.print("<td bgcolor='#ffffff'><input type='radio' name=radio"+hispn1.getId()+" value=4>好<input type='radio' name=radio"+hispn1.getId()+" value=3>较好<input type='radio' name=radio"+hispn1.getId()+" value=2>一般<input type='radio' name=radio"+hispn1.getId()+" value=1>较差<input type='radio' name=radio"+hispn1.getId()+" value=0>差</td></tr>");
	      				out.print("<tr><td height='3' colspan='3' background='newimages/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
	      				
	      				//找分数




	      				strSql="select t.mark from zhijianpingfenmingxi t where t.pingfenid='"+callid+"' and t.kaoheid='"+hispn1.getId()+"' and t.riqi=(select max(w.riqi) from zhijianpingfenmingxi w where w.pingfenid='"+callid+"' and w.kaoheid='"+hispn1.getId()+"' )";
	      				//out.println(strSql);
	      				rs = db.execQuery(strSql);
	      			    if (rs.next()) {
	      			    	fenshu=rs.getString(1);
	      			    	System.out.println("fenshu===============>"+fenshu);
	      			    }
	      			    rs.close();
	      			    if(fenshu==null || fenshu.equals("")){}
	      			    else{
	      			    fenshudb=Double.parseDouble(fenshu)/Integer.parseInt(hispn1.getValue())*4;
	      			    if(hispn1.getParentid().equals("50")){
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			    else{
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			     double fenshusec = Math.round(Double.parseDouble(fenshuinit));
	      				out.println("<script language=\"javascript\">");
	          			out.println(" var length = dafen.radio"+hispn1.getId()+".length ");
	          			out.println(" for(var i=0;i<length;i++)");
	          			out.println("{ if(dafen.radio"+hispn1.getId()+"[i].value=="+fenshusec+") ");
	          			out.println("   {dafen.radio"+hispn1.getId()+"[i].checked=true; }");
	          			out.println("} ");
	          			
	          			out.println("</script>");
	      			    }
	      				}
	      				else if(dao.getChildrenFromHis(id1)!=null&&dao.getChildrenFromHis(id1).size()!=0){
	      					 out.print("<td bgcolor='#ffffff'></td></tr>");
	      					out.print("<tr><td height='3' colspan='3'  bgcolor='#FFFFFF'></td></tr>");
	      					subtree1=dao.getChildrenFromHis(id1);
							for(int k=0;k<subtree1.size();k++){
								hispn2=(HisZhijiankaohe)subtree1.get(k);
								out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='newimages/TreeBranch1.gif' width='18' height='18'>");
								out.print(hispn2.getName()+"("+hispn2.getValue()+"%)</td>");
								out.print("<td bgcolor='#ffffff'><input type='radio' name=radio"+hispn2.getId()+" value=4>好<input type='radio' name=radio"+hispn2.getId()+" value=3>较好<input type='radio' name=radio"+hispn2.getId()+" value=2>一般<input type='radio' name=radio"+hispn2.getId()+" value=1>较差<input type='radio' name=radio"+hispn2.getId()+" value=0>差</td></tr>");
								out.print("<tr><td height='3' colspan='3' background='newimages/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
							    
								
								//找分数




			      				strSql="select t.mark from zhijianpingfenmingxi t where t.pingfenid='"+callid+"' and t.kaoheid='"+hispn2.getId()+"' and t.riqi=(select max(w.riqi) from zhijianpingfenmingxi w where w.pingfenid='"+callid+"' and w.kaoheid='"+hispn1.getId()+"' )";
			      				//out.println(strSql);
			      				rs = db.execQuery(strSql);
			      			    if (rs.next()) {
			      			    	fenshu=rs.getString(1);
			      			    }
			      			    rs.close();
			      			    db.close();
			      			    if(fenshu==null || fenshu.equals("")){}
			      			    else{
			      			    fenshudb=Double.parseDouble(fenshu)/Integer.parseInt(hispn2.getValue())*4;
			      			    if(hispn2.getParentid().equals("50")){
			      			    	fenshuinit = String.valueOf(fenshudb);
			      			    }
			      			    else{
			      			    	fenshuinit = String.valueOf(fenshudb);
			      			    }
			      			    double fenshusec = Math.round(Double.parseDouble(fenshuinit));
			      				out.println("<script language=\"javascript\">");
			          			out.println(" var length = dafen.radio"+hispn2.getId()+".length ");
			          			out.println(" for(var i=0;i<length;i++)");
			          			out.println("{ if(dafen.radio"+hispn2.getId()+"[i].value=="+fenshusec+") ");
			          			out.println("   {dafen.radio"+hispn2.getId()+"[i].checked=true; }");
			          			out.println("} ");
			          			
			          			out.println("</script>");
			      			    }
								
							
							}
	      				}
					}
	      		}
	      	}
			
		}else{
			tree=dao.getFirst(); //在2010-09-01之后，采用新的考核标准
			
			
			for(int i=0;i<tree.size();i++){
	      		pn=(Zhijiankaohe)tree.get(i);
	      		out.print("<tr><td width='65%' bgcolor='#ffffff'><img src='newimages/TreeBranch1.gif' width='18' height='18'>");
	      		out.print(pn.getName()+"("+pn.getValue()+"%)</td>");
	      		id=pn.getId();
	      		if(dao.getChildren(id)==null||dao.getChildren(id).size()==0){
	      			out.print("<td bgcolor='#ffffff'><input type='radio' name="+pn.getId()+" value=4>好<input type='radio' name="+pn.getId()+" value=3>较好<input type='radio' name="+pn.getId()+" value=2>一般<input type='radio' name="+pn.getId()+" value=1>较差<input type='radio' name="+pn.getId()+" value=0>差</td></tr>");
	      			out.print("<tr><td height='3' colspan='3' background='newimages/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
	      			
	      			strSql="select t.mark from zhijianpingfenmingxi t where t.pingfenid='"+callid+"' and t.kaoheid='"+pn.getId()+"' and t.riqi=(select max(w.riqi) from zhijianpingfenmingxi w where w.pingfenid='"+callid+"' and w.kaoheid='"+pn.getId()+"' )";
	      				//out.println(strSql);
	      				rs = db.execQuery(strSql);
	      			    if (rs.next()) {
	      			    	fenshu=rs.getString(1);
	      			    	System.out.println("fenshu===============>"+fenshu);
	      			    }
	      			    rs.close();
	      			    if(fenshu==null || fenshu.equals("")){}
	      			    else{
	      			    fenshudb=Double.parseDouble(fenshu)/Integer.parseInt(pn1.getValue())*4;
	      			    if(pn1.getParentid().equals("50")){
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			    else{
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			     double fenshusec = Math.round(Double.parseDouble(fenshuinit));
	      				out.println("<script language=\"javascript\">");
	          			out.println(" var length = dafen.radio"+pn1.getId()+".length ");
	          			out.println(" for(var i=0;i<length;i++)");
	          			out.println("{ if(dafen.radio"+pn.getId()+"[i].value=="+fenshusec+") ");
	          			out.println("   {dafen.radio"+pn.getId()+"[i].checked=true; }");
	          			out.println("} ");
	          			
	          			out.println("</script>");
	      			    }
	      		}
	      		else if(dao.getChildren(id)!=null&&dao.getChildren(id).size()!=0){
	      		    out.print("<td bgcolor='#ffffff'></td></tr>");
	      			out.print("<tr><td height='3' colspan='3'  bgcolor='#FFFFFF'></td></tr>");
	      			subtree=dao.getChildren(id);
					for(int j=0;j<subtree.size();j++){
						pn1=(Zhijiankaohe)subtree.get(j);
						out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='newimages/TreeBranch1.gif' width='18' height='18'>");
						out.print(pn1.getName()+"("+pn1.getValue()+"%)</td>");
	      				id1=pn1.getId();
	      				if(dao.getChildren(id1)==null||dao.getChildren(id1).size()==0){
	      				out.print("<td bgcolor='#ffffff'><input type='radio' name=radio"+pn1.getId()+" value=4>好<input type='radio' name=radio"+pn1.getId()+" value=3>较好<input type='radio' name=radio"+pn1.getId()+" value=2>一般<input type='radio' name=radio"+pn1.getId()+" value=1>较差<input type='radio' name=radio"+pn1.getId()+" value=0>差</td></tr>");
	      				out.print("<tr><td height='3' colspan='3' background='newimages/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
	      				
	      				//找分数




	      				strSql="select t.mark from zhijianpingfenmingxi t where t.pingfenid='"+callid+"' and t.kaoheid='"+pn1.getId()+"' and t.riqi=(select max(w.riqi) from zhijianpingfenmingxi w where w.pingfenid='"+callid+"' and w.kaoheid='"+pn1.getId()+"' )";
	      				//out.println(strSql);
	      				rs = db.execQuery(strSql);
	      			    if (rs.next()) {
	      			    	fenshu=rs.getString(1);
	      			    	System.out.println("fenshu===============>"+fenshu);
	      			    }
	      			    rs.close();
	      			    if(fenshu==null || fenshu.equals("")){}
	      			    else{
	      			    fenshudb=Double.parseDouble(fenshu)/Integer.parseInt(pn1.getValue())*4;
	      			    if(pn1.getParentid().equals("50")){
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			    else{
	      			    	fenshuinit = String.valueOf(fenshudb);
	      			    }
	      			     double fenshusec = Math.round(Double.parseDouble(fenshuinit));
	      				out.println("<script language=\"javascript\">");
	          			out.println(" var length = dafen.radio"+pn1.getId()+".length ");
	          			out.println(" for(var i=0;i<length;i++)");
	          			out.println("{ if(dafen.radio"+pn1.getId()+"[i].value=="+fenshusec+") ");
	          			out.println("   {dafen.radio"+pn1.getId()+"[i].checked=true; }");
	          			out.println("} ");
	          			
	          			out.println("</script>");
	      			    }
	      				}
	      				else if(dao.getChildren(id1)!=null&&dao.getChildren(id1).size()!=0){
	      					 out.print("<td bgcolor='#ffffff'></td></tr>");
	      					out.print("<tr><td height='3' colspan='3'  bgcolor='#FFFFFF'></td></tr>");
	      					subtree1=dao.getChildren(id1);
							for(int k=0;k<subtree1.size();k++){
								pn2=(Zhijiankaohe)subtree1.get(k);
								out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='newimages/TreeBranch1.gif' width='18' height='18'>");
								out.print(pn2.getName()+"("+pn2.getValue()+"%)</td>");
								out.print("<td bgcolor='#ffffff'><input type='radio' name=radio"+pn2.getId()+" value=4>好<input type='radio' name=radio"+pn2.getId()+" value=3>较好<input type='radio' name=radio"+pn2.getId()+" value=2>一般<input type='radio' name=radio"+pn2.getId()+" value=1>较差<input type='radio' name=radio"+pn2.getId()+" value=0>差</td></tr>");
								out.print("<tr><td height='3' colspan='3' background='newimages/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
							    
								
								//找分数




			      				strSql="select t.mark from zhijianpingfenmingxi t where t.pingfenid='"+callid+"' and t.kaoheid='"+pn2.getId()+"' and t.riqi=(select max(w.riqi) from zhijianpingfenmingxi w where w.pingfenid='"+callid+"' and w.kaoheid='"+pn1.getId()+"' )";
			      				//out.println(strSql);
			      				rs = db.execQuery(strSql);
			      			    if (rs.next()) {
			      			    	fenshu=rs.getString(1);
			      			    }
			      			    rs.close();
			      			    db.close();
			      			    if(fenshu==null || fenshu.equals("")){}
			      			    else{
			      			    fenshudb=Double.parseDouble(fenshu)/Integer.parseInt(pn2.getValue())*4;
			      			    if(pn2.getParentid().equals("50")){
			      			    	fenshuinit = String.valueOf(fenshudb);
			      			    }
			      			    else{
			      			    	fenshuinit = String.valueOf(fenshudb);
			      			    }
			      			    double fenshusec = Math.round(Double.parseDouble(fenshuinit));
			      				out.println("<script language=\"javascript\">");
			          			out.println(" var length = dafen.radio"+pn2.getId()+".length ");
			          			out.println(" for(var i=0;i<length;i++)");
			          			out.println("{ if(dafen.radio"+pn2.getId()+"[i].value=="+fenshusec+") ");
			          			out.println("   {dafen.radio"+pn2.getId()+"[i].checked=true; }");
			          			out.println("} ");
			          			
			          			out.println("</script>");
			      			    }
								
							
							}
	      				}
					}
	      		}
	      	}
		}
       %>
 
        <tr>
        <td width="65%" bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
     
        
    </table>
    
    
  
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="40" height="1" bgcolor="#DFDFDF"></td>
          </tr>
          <tr>
            <td height="1" bgcolor="#D9D9D9"></td>
          </tr>
    </table>
    <td background="newimages/k1_06.gif"></td>
 
  <tr>
    <td background="newimages/k1_05.gif"><img src="newimages/k1_07.gif" width="3" height="5" /></td>
    <td colspan="3" background="newimages/k1_08.gif"></td>
    <td background="newimages/k1_08.gif"><img src="newimages/k1_09.gif" width="4" height="5" /></td>
  </tr>


</body>

