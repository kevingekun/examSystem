<%@page language="java" contentType="text/html;charset=gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <%@ page import="org.apache.commons.lang.StringUtils" %>
	<head>
		<title>无标题文档</title>
		
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<style type="text/css">

	html{ height:100%;}
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #ebf3f6;
		height:100%;	
	}

</style>
<script language="javascript">

function autoHeight() 
{
	var obj = document.getElementById("content1");
	var h = document.body.clientHeight;
	obj.style.height=(h-34)+"px";
	
	 
}

</script>	

	<%@taglib uri="elile.tld" prefix="elile"%>	
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<script language="JavaScript" type="text/JavaScript" src="../js/dateMy97/WdatePicker.js"></script>
	<jsp:useBean id="Wonders"
		class="com.wondersgroup.falcon.beans.archives.HisTree" />
	<jsp:useBean id="Comm"
		class="com.wondersgroup.falcon.beans.common.CommFunc" />
		<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
		<jsp:useBean id = "authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
	<%@ page import="java.util.*,java.text.SimpleDateFormat,java.sql.*" %>
	<%@ page import="com.wondersgroup.falcon.model.archives.Users" %>
	<%@ page import="com.wondersgroup.falcon.model.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.beans.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.Date"%>
	<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
	<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil" %>
	<%@ page import="com.wondersgroup.falcon.beans.archives.HisTree"  %>
	<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
	<%@ page import="com.wondersgroup.falcon.model.select.*" %>
	<%@ page import="com.wondersgroup.falcon.model.call.Zhijianpingfen" %>
	<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
<%@ page import="com.wondersgroup.falcon.dao.auth.*"%>
<%@ page import="com.wondersgroup.falcon.dao.auth.*"%>
<%@ page import="com.wondersgroup.falcon.persistence.DBConnection"%>
		<%
		User user = new User();
		UserDAO udao = new UserDAO();
		int currpage = RequestUtils.getInt(request, "currpage", 1);//默认1 currpage
	int pagesize = RequestUtils.getInt(request, "pagesize", 20);//默认行行数 pagesize
	int pagenum = RequestUtils.getInt(request, "pagenum", 5);
	 User uuser = ((UserDetailsImpl)util.getUserDetails()).getUser();
 	String name=uuser.getUsername();
 	Object[][] o =null;
 	Object[][] o1 =null;
 	String job = "";
 	String jobname = "";
 	String jobname1 = "";
 	//name = "";
 	
   		String username="";
   		Users users = authbean.findByUsername(util.getUserDetails().getUsername());
   		
   		String temp= users.getGroup_id();
		String gonghao =users.getUsername();
		String mutex="";
		mutex=request.getParameter("mutex");
		if(mutex==null)mutex="";
		int prepage=0;
		int nextpage=15;
		Integer count=new Integer(0);
		
		username=request.getParameter("username");
		if(username==null)username="";
		String realname=request.getParameter("realname");
		if(realname==null)realname="";
		if(!realname.equals("")){
		user = udao.getUserByRealname(realname);
		if(user!=null)
		username = user.getUsername();
		else username="777";
		}

		String callid=request.getParameter("callid");
		if(callid==null)callid="";
		String starttime=request.getParameter("starttime");
		if(starttime==null)starttime="";
		String endtime=request.getParameter("endtime");
		if(endtime==null)endtime="";
		String sort = request.getParameter("sort");
		String type = request.getParameter("fuwu");
			if (sort==null||sort.equals(""))
				sort = "";
				
				if (type==null||type.equals(""))
				type = "";
		
		    
		
		String start=request.getParameter("start");
		if(start==null)start="";
		String end=request.getParameter("end");
		if(end==null)end="";
		String dn=request.getParameter("dn");
		if(dn==null)dn="";

		
		ZhijianDAO pit = new ZhijianDAO();
		o=pit.getzuzhang(name);
		if(o!=null&&o.length>0){
		jobname = o[0][0].toString();
		job = o[0][1].toString();
		}
		
		int pa = 1;
		
		int ye = 1;
		List list=null;
		List list111=null;
		History pn = new History();
		Zhijianpingfen pf = new Zhijianpingfen();
		
		
		
	
		list111 = pit.findAll(gonghao,name);
			if(list111!=null&&list111.size()!=0){
			System.out.println("list.get(0)"+list111.get(0));
						Integer obj= Integer.valueOf(list111.get(0).toString());
						count=obj;
			}
		ye=count.intValue()/15+1;
		System.out.println("count===>"+count);
		if (list != null && list.size() != 0) {
			ye = list.size() / 15 + 1;
		}
		
	%>
	<script type="text/javascript">
	function go(){
			document.zhonglei.pa.value=document.getElementById("page").value;
			document.zhonglei.submit();
		}
	
   		function fPopUpCalendarDlg(textname,startYear,endYear,q)
		{
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
			
			retval = window.showModalDialog("8.htm", arguments , features );
				
			if( retval != null ){
				textname.value = retval;
			}
		}
			
		function checksubmit_2(){
			document.lookup2.submit();
		}	
		
		function queryHistory(){
			document.queryform.submit();
		}
		
		
		

	</script>

	<body>
	<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">技巧欠佳录音列表</td>
          <td  class="header3"></td>
        </tr> 
      </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10"></td>
        </tr>
      </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td class="borader3">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询条件</td>
                    </tr>
                </table></td>
              </tr>
            </table>

		<form name="queryform" method="post" action="baddianxingQuery.jsp">
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">工号：</td>
          <td width="20%"><input type=text name="username" class="input3" size=15></td>
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">姓名：</td>
          <td width="20%">
           <input type=text name="realname" class="input3" size=15></input>
          </td>
    	</tr>
		<tr>		
            
            	 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">来电号码：</td>
          <td width="20%"> <input type=text name="callid" class="input3"></input>
			
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">来电时间：</td>
          <td width="30%"><input type="text" style="width:45%" class="Wdate Wdate_30" id="starttime" name="starttime" onclick="WdatePicker()"></input> 至<input style="width:45%" type="text" class="Wdate Wdate_30" id="endtime" name="endtime" onclick="WdatePicker()"></input>
			
			</td>
          <td width="15%">&nbsp;</td>
        </tr>
      
         <tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">时长：</td>
          <td width="20%"><input type=text class="input3" name="start" size=6 maxlength="6"></input> --
			<input type=text class="input3" name="end" size=6 maxlength="6"></input></td>
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right"></td>
          <td width="20%">
          
          </td>
    	</tr> 
		  
		
          
          
          
        <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="查 询" onclick="queryHistory()"></input> 
                  </tr>	
	   	</table>                                           
<input type="hidden" name="mutex" value="1"></input>
	
</table>

	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询列表</td>
                    </tr>
                </table></td>
              </tr>
 </table>
	
	
		
		<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb" >
			<tr class="title_font">
				<td align="center">
					序号
				</td>			
				<td align="center">
					工号
				</td>
				<td align="center">
					姓名
				</td>
				<td align="center">
					组别
				</td>
				
				<td align="center">
					通话时间
				</td>
				<td align="center">
					分数
				</td>
				
				<td align="center">
					听录音
				</td>
				<%	if(name.equals("015")||name.equals("033")||name.equals("009")||name.equals("008")||name.equals("admin")){%>
				<td align="center">
					来电时间
				</td>
				<td align="center">
					来电号码
				</td>
				<td align="center">
					打分时间
				</td>
				<td align="center">
					归档人
				</td>
				<td align="center">
					操作
				</td>
				
				<%}%>
			</tr>
<%
		
		
	if(name.equals("061")&&mutex.equals("")){
			username = "'061','037','103','058','090','083'";
			}
			if(name.equals("066")&&mutex.equals("")){
			username = "'066','059','092','100','073','052'";
			}
			if(name.equals("065")&&mutex.equals("")){
			username = "'065','089','067','105','091','026','081'";
			}
			if(name.equals("055")&&mutex.equals("")){
			username = "'055','102','078','056','080','079'";
			}
			if(name.equals("039")&&mutex.equals("")){
			username = "'039','088','053','095','087','035'";
			}
			if(name.equals("050")&&mutex.equals("")){
			username = "'050','068','082','101','057','076'";
			}
			if(name.equals("030")&&mutex.equals("")){
			username = "'050','068','082','101','057','076','039','088','053','095','087','035','055','102','078','056','080','079'";
			}
			if(name.equals("029")&&mutex.equals("")){
			username = "'061','037','103','058','090','083','066','059','092','100','073','052','065','089','067','105','091','026','081'";
			}
			else if(!username.equals("")&&!mutex.equals(""))
			username = "'"+username+"'";
			if(uuser.getGroup().getId().toString().equals("1")){
			username = name;
			}
		
		Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formatime = sdf.format(date);
			if(temp.equals("2")||temp.equals("1")){
			//username=gonghao;
			//endtime=formatime;
			//starttime=formatime;
			}
			String endtime2 = null;
			String[][] time2 = null;
			List list2=null;
			List list3=null;
			
			list2=pit.baddianxingfindHistory1(sort,type,username,callid,starttime,endtime,start,end,currpage,pagesize);
			list3=pit.baddianxingfindHistory1(sort,type,username,callid,starttime,endtime,start,end,1,60000);
			
			NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		
		navigateform.setPagenum(pagenum);
		request.setAttribute("navigateform", navigateform);
			if(list2!=null&&list2.size()!=0){
				int j = list2.size();
				for (int i = 0; i < j; i++) {
					Select1 hospital = (Select1) list2.get(i);
					
					navigateform.setTotal(list3.size());		
					String name1 ="";
					String realname1 = "";	
					DBConnection db = new DBConnection();
					ResultSet rs1 = null;
					String sql = "select t.realname,t.name from usergroup_view t where t.gonghao = '"+hospital.getAgentid()+"'";
					rs1 = db.execQuery(sql.toString());	
			 while (rs1.next()) {
			 		name1 = rs1.getString("name");
			 		realname1 = rs1.getString("realname");
			 }
			 rs1.close();
			 db.close();	
					String mark = "";
					String id = hospital.getId();
					System.out.println("id===>"+id);
					if( id != null ){
					pf = pit.getMarkByID(id);
					}
					if( pf!=null&&pf.getMark() != null ){
					 mark = pf.getMark();
					}
				

						
					out.println("<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'>");
					out.println("<td align=center>");
					out.println(i+1 );
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getAgentid());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(realname1);
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(name1);
					out.println("</td>");
					
					out.println("<td align='center'>");
					out.println(hospital.getRecordlength()==null?"":hospital.getRecordlength());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(mark);
					out.println("</td>");
					
					out.println("<td align=center>");
					out.println("<a  href=luyin1.jsp?callid="
							+ hospital.getId() + " target=\"_self\">"+
							"听录音 </a>");
					out.println("</td>");
					
					if(name.equals("015")||name.equals("033")||name.equals("009")||name.equals("008")||name.equals("admin")){
					out.println("<td align='center'>");
					out.println(hospital.getStartrecordtime().toString().substring(0,19));
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getCallerid()==null?"":hospital.getCallerid());
					out.println("</td>");
					out.println("<td align='center'>");
					if(pf != null){
						out.println(Comm.convertDT(pf.getRiqi()));
					}
					out.println("</td>");
					out.println("<td align='center'>");
					String realname2="";
					if(hospital.getDafenren2()!=null){
					User user1=udao.getUserByUsername(hospital.getDafenren2());
					realname2=user1.getRealname();}
					out.println(StringUtils.trimToEmpty(realname2));
					out.println("</td>");
					
					out.println("<td align='center'>");
					out.println("<a  href=baddelete.jsp?callid="
							+ hospital.getId() + " target=\"_self\">"+
							"删除 </a>");
					out.println("</td>");
					}
					//out.println("<td align='center'>");
					//out.println(time2==null||time2.length==0?"":time2[0][1]);
					//out.println("</td>");
					
				}
			
			}				
			%>
				
		</table>
	
	
			</form>
		<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
		<form name="queryform1" action="baddianxingQuery.jsp" method="post">
		<input type="hidden" name="currpage"  />
			<input type="hidden" name="username" value=<%=username %>></input>
			<input type="hidden" name="callid" value=<%=callid %>></input>
			<input type="hidden" name="starttime" value=<%=starttime %>></input>
			<input type="hidden" name="endtime" value=<%=endtime %>></input>
					</form>
	 <td align="center" valign="middle" class="num_font"><div class="page1_box">
			<elile:navigateBar navigateform="navigateform"
								actionName="baddianxingQuery.jsp" formName="queryform1" />	
								  </div></td>
        </tr>
		
			<form action="main_right.jsp" name="zhonglei">
			<input type="hidden" name="mutex" value="1"></input>
			<input type="hidden" name="pa"></input>
			<input type="hidden" name="prepage" value=<%=prepage %>></input>
			<input type="hidden" name="nextpage" value=<%=nextpage %>></input>
			<input type="hidden" name="username" value=<%=username %>></input>
			<input type="hidden" name="callid" value=<%=callid %>></input>
			<input type="hidden" name="starttime" value=<%=starttime %>></input>
			<input type="hidden" name="endtime" value=<%=endtime %>></input>
		</form>

	</body>
</html>
