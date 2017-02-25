<%@page contentType = "text/html;charset=utf-8" %>
<%@page import="java.util.List,java.util.Iterator,java.util.ArrayList,java.util.Set" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<%@ page import="com.wondersgroup.falcon.model.call.Zhijiankaohe" %>
<%@ page import="com.wondersgroup.falcon.model.citizeninfo.History" %>
<%@ page import="com.wondersgroup.falcon.dao.call.*" %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*" %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.model.select.*" %>
<%@ page import="com.wondersgroup.falcon.model.call.*" %>
<%@ page import="com.wondersgroup.falcon.model.zhijian.*" %>
<%@ page import="com.wondersgroup.falcon.model.report.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil" %> 
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
<%@ page import="com.wondersgroup.falcon.persistence.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
	<%@ page import="com.wondersgroup.falcon.model.call.Zhijianpingfen" %>



<html>
<head>
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
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
      			
             System.out.println("callid=============>"+callid);
              

		Authority authority =new Authority();
		String auth=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername();
   			Set temp= ((UserDetailsImpl)util.getUserDetails()).getUser().getAuthorities();
   			String quanxian="";
   			String privilige="";
	
	
	
	List urllist = callservice.getHisRecordUrl(callid);	
	System.out.println("urllist==============>>>>>>>>>>>>>>>"+urllist); 
	HistoryDAO his = new HistoryDAO();
	String servicetype = "";
	Select1 h = his.getBySelect(callid);
	
	
	
	String id3 = request.getParameter("callid");
	String startrecordtime = request.getParameter("time");
	
	ZhijianDAO pi = new ZhijianDAO();
	String type = "";
	String gonghao = "";
	String phoneid = "";
	String callid1 = "";
	String comments = "";
	String startime = "";
	String endtime = "";
	String sort = "";
	String liushui="";
	Luyin_his model = null;
	Luyin1 model1 = null;
	model = his.getDataByID(new Long(callid));
	if(model==null)  model1 = his.getTDataByID(new Long(callid)); 
	if(h!=null){
	servicetype = h.getFuwu();
		gonghao = h.getAgentid();
		callid1 = h.getCallerid();
		comments = h.getComments() == null ? "" : h.getComments();
		startime = h.getStartrecordtime()==null?"": h.getStartrecordtime().toString();
		endtime = h.getStoprecordtime()==null?"":h.getStoprecordtime().toString();
		sort = h.getSort() == null ? "" : h.getSort();
	}
	if(model!=null){
		gonghao = model.getAgentid();
		callid1 = model.getCallerid();
		comments = model.getSummary() == null ? "" : model.getSummary();
		startime = model.getStartrecordtime()==null?"": model.getStartrecordtime().toString();
	}
	if(model1!=null){
		gonghao = model1.getAgentid();
		callid1 = model1.getCallerid();
		comments = model1.getSummary() == null ? "" : model1.getSummary();
		startime = model1.getStartrecordtime()==null?"": model1.getStartrecordtime().toString();
	}
%>
	<%
		List all = new ArrayList();
		Zhijiankaohe pn = new Zhijiankaohe();
		Zhijiankaohe pn1 = new Zhijiankaohe();
		Zhijiankaohe pn2 = new Zhijiankaohe();
		
		ZhijiankaoheDAO dao = new ZhijiankaoheDAO();
	List serlist = null;
			List serlist1 = null;
			serlist = his.getListDataBycallid(callid);
			if(serlist==null)
			serlist1 = his.gettDataBycallid(callid);
			HisServiceType Type = new HisServiceType();
			ServiceType Type1 = new ServiceType();
			DicServicetype dic = new DicServicetype();
			String Typeid = "";
		%>


<body>

                      
<br>
                      
<TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0><!--DWLayoutTable-->
	<TBODY>
	 <tr>
       
        <td align=center colspan="4"><strong><a href="javascript:history.back(0)" class="bg"><img src="images/bt_fh.gif" width="48" height="21" border="0" /></a></strong></td>
        
      </tr>
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
							out.println("<td width='20%' align='center'>开始时间</td>");
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
<%System.out.print("time22222222222222================>"+startrecordtime);%>
<form action="zhijiandafen.jsp" name="dafen">
<input type=hidden name=gonghao value=<%=h.getAgentid() %>>
<input type="hidden" name="startrecordtime" value="<%=startrecordtime %>">
<input type=hidden name=auth value=<%=auth %>>
<input type=hidden name=callid value=<%=callid %>>
<table width="99%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#ffffff">
		
			<tr>
				<td align="right" width="40%">
					评分说明： 

				</td>
				<td align="left" >
					<TEXTAREA id=dafenmemo name=dafenmemo rows=8 cols=55 style="overflow:auto" ><%=dafenmemo1 == null ? "" : dafenmemo1%></TEXTAREA>
				</td>
			</tr>
	
		</table>
<fieldset style="height:100;"  >
	<legend>历史记录查看</legend>
		<br><br>
		<table width="99%" border="0" align="left" cellpadding="3"
						cellspacing="1" bgcolor="#ffffff">
			
				<tr>
				<td align="right" width="40%">
					服务类型：


				</td>
				<td align="left">
				<%
				if(serlist!=null&&serlist.size()>0){
				for(int i =0;i<serlist.size();i++){
					Type = (HisServiceType)serlist.get(i);
					Typeid = Type.getServicetype();
					if(Typeid!=null&&Typeid.substring(0,2).equals("01")){
						dic = his.getByPhoneid(Typeid);
						type = dic.getServicetypename();
					}
				}
				}
				 %>
				 	<%
				if(serlist1!=null&&serlist1.size()>0){
				for(int i =0;i<serlist1.size();i++){
					Type1 = (ServiceType)serlist1.get(i);
					Typeid = Type1.getServicetype();
					if(Typeid!=null&&Typeid.substring(0,2).equals("01")){
						dic = his.getByPhoneid(Typeid);
						type += dic.getServicetypename()+"、";
					}
				}
				}
				
				 %>
					<input name="type" type="text" size="18" value="<%=type%>" readonly>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%">
					业务类别：


				</td>
				<td align="left">
				<%
				type = "";
				if(serlist!=null&&serlist.size()>0){
				for(int i =0;i<serlist.size();i++){
					Type = (HisServiceType)serlist.get(i);
					Typeid = Type.getServicetype();
					if(Typeid!=null&&Typeid.substring(0,2).equals("02")){
						
						if(Typeid!=null&&Typeid.length()==8){
							dic = his.getByPhoneid(Typeid.substring(0,4));
							type = dic.getServicetypename()+"-->";
							dic = his.getByPhoneid(Typeid.substring(0,6));
							type = type+dic.getServicetypename()+"-->";
							dic = his.getByPhoneid(Typeid.substring(0,8));
							type = type+dic.getServicetypename();
						}
						if(Typeid!=null&&Typeid.length()==6){
							dic = his.getByPhoneid(Typeid.substring(0,4));
							type = dic.getServicetypename()+"-->";
							dic = his.getByPhoneid(Typeid.substring(0,6));
							type = type+dic.getServicetypename()+"-->";
						}
						if(Typeid!=null&&Typeid.length()==4){
							dic = his.getByPhoneid(Typeid.substring(0,4));
							type = dic.getServicetypename();
							
						}
					}
				}
				}
				 %>
				 <%
				if(serlist1!=null&&serlist1.size()>0){
				for(int i =0;i<serlist1.size();i++){
					Type1 = (ServiceType)serlist1.get(i);
					Typeid = Type1.getServicetype();
					if(Typeid!=null&&Typeid.substring(0,2).equals("02")){
						
						if(Typeid!=null&&Typeid.length()==8){
							dic = his.getByPhoneid(Typeid.substring(0,4));
							type = dic.getServicetypename()+"-->";
							dic = his.getByPhoneid(Typeid.substring(0,6));
							type = type+dic.getServicetypename()+"-->";
							dic = his.getByPhoneid(Typeid.substring(0,8));
							type = type+dic.getServicetypename();
						}
						if(Typeid!=null&&Typeid.length()==6){
							dic = his.getByPhoneid(Typeid.substring(0,4));
							type = dic.getServicetypename()+"-->";
							dic = his.getByPhoneid(Typeid.substring(0,6));
							type = type+dic.getServicetypename()+"-->";
						}
						if(Typeid!=null&&Typeid.length()==4){
							dic = his.getByPhoneid(Typeid.substring(0,4));
							type = dic.getServicetypename();
							
						}
					}
				}
				}
				
				 %>
				<TEXTAREA id=dafenmemo name=dafenmemo readonly rows=5 cols=25 style="overflow:auto" ><%=type == null ? "" : type%></TEXTAREA>
				</td>
			</tr>
			<tr>

				<td align="right" width="40%">
					座席工号：


				</td>
				<td align="left" >
					<input name="gonghao" type="text" contentEditable="false"
						size="18" value="<%=gonghao%>" readonly>
				</td>
			</tr>

			<tr>
				<td align="right" width="40%">
					电话号码：


				</td>
				<td align="left">
					<input name="callid" type="text" contentEditable="false"
						size="18" value="<%=callid1%>" readonly>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%">
					电话结束时间：


				</td>
				<td align="left">
					<input name="endtime" type="text" contentEditable="false"
						size="18" value="<%=startime == null ? "" : startime.substring(0,startime.length()-2) %>" readonly>
				</td>
			</tr>
			<tr>
				<td align="right">
					电话概要：


				</td>
				<td align="left" >
					<TEXTAREA id=comments name=comments rows=5 cols=30 style="overflow:auto" readonly><%=comments == null ? "" : comments%></TEXTAREA>
				</td>
			</tr>
		
		</table>
		</fieldset>
<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">

  <tr>
    <td width="3"><img src="images/k1_01.gif" width="3" height="30" /></td>
    <td width="21" background="images/k1_03.gif"><img src="images/k1_02.gif" width="21" height="30" /></td>
    <td width="50%" background="images/k1_03.gif"><strong>打分</strong></td>
    <td width="50%" background="images/k1_03.gif">&nbsp;</td>
    <td width="4" background="images/k1_03.gif"><img src="images/k1_04.gif" width="4" height="30" /></td>
  </tr>
  <tr>
    <td background="images/k1_05.gif"></td>
    <td colspan="3" valign="top"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" bgcolor="#CCCCCC">
      <tr>
        <td width="65%" bgcolor="#92D6E4"><div align="left"><strong>评估内容</strong></div></td>
        <td width="35%" bgcolor="#92D6E4"><div align="left">打 分</div></td>
        </tr>
      <tr>
        <td height="2" colspan="3" bgcolor="#28B9FF"></td>
      </tr>
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
		tree=dao.getFirst();
      	for(int i=0;i<tree.size();i++){
      		pn=(Zhijiankaohe)tree.get(i);
      		out.print("<tr><td width='65%' bgcolor='#ffffff'><img src='images/TreeBranch1.gif' width='18' height='18'>");
      		out.print(pn.getName()+"("+pn.getValue()+"%)</td>");
      		id=pn.getId();
      		if(dao.getChildren(id)==null||dao.getChildren(id).size()==0){
      			out.print("<td bgcolor='#ffffff'><input type='radio' name="+pn.getId()+" value=4>好<input type='radio' name="+pn.getId()+" value=3>较好<input type='radio' name="+pn.getId()+" value=2>一般<input type='radio' name="+pn.getId()+" value=1>较差<input type='radio' name="+pn.getId()+" value=0>差</td></tr>");
      			out.print("<tr><td height='3' colspan='3' background='images/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
      			
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
					out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/TreeBranch1.gif' width='18' height='18'>");
					out.print(pn1.getName()+"("+pn1.getValue()+"%)</td>");
      				id1=pn1.getId();
      				if(dao.getChildren(id1)==null||dao.getChildren(id1).size()==0){
      				out.print("<td bgcolor='#ffffff'><input type='radio' name=radio"+pn1.getId()+" value=4>好<input type='radio' name=radio"+pn1.getId()+" value=3>较好<input type='radio' name=radio"+pn1.getId()+" value=2>一般<input type='radio' name=radio"+pn1.getId()+" value=1>较差<input type='radio' name=radio"+pn1.getId()+" value=0>差</td></tr>");
      				out.print("<tr><td height='3' colspan='3' background='images/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
      				
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
							out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/TreeBranch1.gif' width='18' height='18'>");
							out.print(pn2.getName()+"("+pn2.getValue()+"%)</td>");
							out.print("<td bgcolor='#ffffff'><input type='radio' name=radio"+pn2.getId()+" value=4>好<input type='radio' name=radio"+pn2.getId()+" value=3>较好<input type='radio' name=radio"+pn2.getId()+" value=2>一般<input type='radio' name=radio"+pn2.getId()+" value=1>较差<input type='radio' name=radio"+pn2.getId()+" value=0>差</td></tr>");
							out.print("<tr><td height='3' colspan='3' background='images/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
						    
							
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
    <td background="images/k1_06.gif"></td>
 
  <tr>
    <td background="images/k1_05.gif"><img src="images/k1_07.gif" width="3" height="5" /></td>
    <td colspan="3" background="images/k1_08.gif"></td>
    <td background="images/k1_08.gif"><img src="images/k1_09.gif" width="4" height="5" /></td>
  </tr>


</body>

