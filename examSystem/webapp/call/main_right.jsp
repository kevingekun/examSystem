<%@page language="java" contentType="text/html;charset=gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
	<%@ page import="com.wondersgroup.falcon.model.archives.Users" %>
	<%@ page import="com.wondersgroup.falcon.model.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.Date"%>
	<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
	<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil" %>
	<%@ page import="com.wondersgroup.falcon.beans.archives.HisTree"  %>
	<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
	<%@ page import="com.wondersgroup.falcon.model.select.*" %>
	<%@ page import="com.wondersgroup.falcon.model.call.Zhijianpingfen" %>


	<%
		int currpage = RequestUtils.getInt(request, "currpage", 1);//默认1 currpage
	int pagesize = RequestUtils.getInt(request, "pagesize", 20);//默认行行数 pagesize
	int pagenum = RequestUtils.getInt(request, "pagenum", 5);
			try{
	 User uuser = ((UserDetailsImpl)util.getUserDetails()).getUser();
 	String name=uuser.getUsername();
 	
   		//典型录音判断
   		String good=request.getParameter("good");
		if(good==null)good="";
		String yewuerror=request.getParameter("yewuerror");
		if(yewuerror==null)yewuerror="";
		String jiqiao=request.getParameter("jiqiao");
		if(jiqiao==null)jiqiao="";
		String bad=request.getParameter("bad");
		if(bad==null)bad="";
   		
   		
   		String username="";
   		//Users users = authbean.findByUsername(util.getUserDetails().getUsername());
   		
   		String temp= uuser.getGroup().getId().toString();
		String gonghao =uuser.getUsername();
		String mutex="";
		String time="";
		mutex=request.getParameter("mutex");
		if(mutex==null)mutex="";
		int prepage=0;
		int nextpage=15;
		Integer count=new Integer(0);
		List list2=null;
		username=request.getParameter("username");
		if(username==null)username="";

		String callid=request.getParameter("callid");
		if(callid==null)callid="";
		String starttime=request.getParameter("starttime");
		if(starttime==null)starttime="";
		String endtime=request.getParameter("endtime");
		if(endtime==null)endtime="";
		String start=request.getParameter("start");
		if(start==null)start="";
		String end=request.getParameter("end");
		if(end==null)end="";
		String dn=request.getParameter("dn");
		if(dn==null)dn="";

		
		ZhijianDAO pit = new ZhijianDAO();
		
		
		int pa = 1;
		if (request.getParameter("pa") != null) {
			pa = Integer.parseInt(request.getParameter("pa"));
			prepage=Integer.parseInt(request.getParameter("prepage")==null?"0":request.getParameter("prepage"));
			nextpage=Integer.parseInt(request.getParameter("nextpage")==null?"15":request.getParameter("nextpage"));
			prepage=(pa-1)*15+1;
			nextpage=pa*15;
		}
		int ye = 1;
		List list=null;
		List list111=null;
		History pn = new History();
		Zhijianpingfen pf = new Zhijianpingfen();
		
		
		
	
		
		
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formatime = sdf.format(date);
		
			String endtime2 = null;
			String[][] time2 = null;
			
			List list3=null;
			NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		
		navigateform.setPagenum(pagenum);
		request.setAttribute("navigateform", navigateform);
			
			list2=pit.findHistory(username,callid,starttime,endtime,start,end,name,currpage,pagesize);
			list3=pit.findHistory(username,callid,starttime,endtime,start,end,name,1,60000);
			
			
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
		
		function queryTJHistory(){
			document.tjform.submit();
		}
		function queryOtherHistory(){
			document.otherform.submit();
		}
		function queryReport(){
			document.reportform.submit();
		}
		function tijiao(){
		//alert(document.getElementById("checkboxid"))
	   if(document.getElementById("checkboxid")==null){
	   return;
	   }
	   
	    for(var i=0;i<document.queryform.checkboxid.length;i++){
	      if(document.queryform.checkboxid[i].checked==true)
	        document.tijiaoform.checkboxids.value=document.tijiaoform.checkboxids.value+document.queryform.checkboxid[i].value+";";
	     
	    }
		document.tijiaoform.submit();
		
	} 
		function checkall(i,ischeck){
		var memberboxes = document.getElementsByName("checkboxid");
		
		for(var a=0;a<memberboxes.length;a++){
			memberboxes[a].checked=ischeck;
		}
	}
	</script>

	<body>
	<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">质检打分</td>
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

			<form name="queryform" method="post" action="main_right.jsp">

    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">工号：</td>
          <td width="20%"><input type=text name="username" class="input3" size=15></td>
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">时长：</td>
          <td width="20%">
          <input type=text class="input3" name="start" size=6 maxlength="6"></input> --
			<input type=text class="input3" name="end" size=6 maxlength="6"></input>
          </td>
    	</tr>
		<tr>		
            
            	 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">来电号码：</td>
          <td width="20%"> <input type=text name="callid" class="input3"></input>
			
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">时间：</td>
          <td width="30%"><input type="text" style="width:45%" class="Wdate Wdate_30" id="starttime" name="starttime" onclick="WdatePicker()"></input> 至<input style="width:45%" type="text" class="Wdate Wdate_30" id="endtime" name="endtime" onclick="WdatePicker()"></input>
			
			</td>
          <td width="15%">&nbsp;</td>
        </tr>
      
          
          
          
        <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="查 询" onclick="queryHistory()"></input> 
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="批量提交" onclick="tijiao()"></input> 
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="查询提交记录" onclick="queryTJHistory()"></input>
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="互查提交记录" onclick="queryOtherHistory()"></input>
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
				<td align="center" width=4%>
					<%if (list2==null){ %>
					全选<input type=checkbox name="team0" id="team0"></input>
				    <%}
				    else{ %>
				    全选<input type=checkbox name="team0" id="team0" onclick="checkall(<%=list2.size() %>,this.checked)"></input>
				    <%} %>
				</td>	
				<td align="center">
					序号
				</td>			
				<td align="center">
					工号
				</td>
				<td align="center">
					来电时间
				</td>
				<td align="center">
					来电号码
				</td>
				<td align="center">
					通话时间
				</td>
				<td align="center">
					分数
				</td>
				<td align="center">
					审核结果
				</td>
				<td align="center">
					审核理由
				</td>
				<td width=8% align="center">
					申请微调
				</td>
				<td width=5% align="center">
					听录音

				</td>
				<td align="center" width=35%>
					典型录音库

				</td>
				<td width=5% align="center">
					提交
				</td>
			</tr>
<%
		
			if(list2!=null&&list2.size()!=0){
				int j = list2.size();
				for (int i = 0; i < j; i++) {
					Select1 hospital = (Select1) list2.get(i);
					navigateform.setTotal(list3.size());	
							
					HibernateUtil.commitTransaction();		
					String mark = "";
					String dafenmemo="";
					String id = hospital.getId();
					
					if( id != null ){
					pf = pit.getMarkByID(id);
					}
					if( pf!=null&&pf.getMark() != null ){
					 mark = pf.getMark();
					}
					if( pf!=null&&pf.getDafenmemo() != null ){
					 dafenmemo = pf.getDafenmemo();
					}
				    String	flag="";
				    flag= hospital.getFlag();
				     if (flag==null||flag.equals(""))
				     flag = "";
                    String shenheresult="";
                    String modifyapplyflag="";
                    modifyapplyflag=hospital.getModifyapplyflag();
                    if (modifyapplyflag==null||modifyapplyflag.equals(""))
				     modifyapplyflag = "";
                    if(modifyapplyflag!=null){
						if(modifyapplyflag.equals("2")){
						shenheresult="通过";
					}else if(modifyapplyflag.equals("3")){
					shenheresult="不通过";
					}}
					out.println("<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'>");%>
					 <td align="center" width=3%>
	                  <input type=checkbox name="checkboxid" value="<%=hospital.getId() %>"></input>
				    </td>
					<%
					out.println("<td align=center>");
					out.println(i+1);
					out.println("<td align='center'>");
					out.println(hospital.getAgentid());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getStartrecordtime().toString().substring(0,19));
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getCallerid());
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getRecordlength()==null?"":hospital.getRecordlength());
					out.println("</td>");
					if(modifyapplyflag.equals("2")||modifyapplyflag.equals("3")){
					out.println("<td align='center' style='color:#ff0000' >");
					out.println(mark);
					out.println("</td>");
					}else{
					out.println("<td align='center'>");
					out.println(mark);
					out.println("</td>");
					}
					out.println("<td align='center'>");
					out.println(shenheresult==null?"":shenheresult);
					out.println("</td>");
					out.println("<td align='center'>");
					out.println(hospital.getShenhereason()==null?"":hospital.getShenhereason());
					out.println("</td>");
					if(flag.equals("1")){
					out.println("<td align=center>");
					out.println("&nbsp;");
					out.println("</td>");
					}else{
					out.println("<td align=center>");
					out.println("<a  href=applyweitiao.jsp?callid="
							+ hospital.getId()+" target=\"_self\">"+
							"申请微调 </a>");
					out.println("</td>");
					}
						if(modifyapplyflag.equals("3")){
					out.println("<td align=center>");
					out.println("&nbsp;");
					out.println("</td>");
					out.println("<td align=center>");
					out.println("&nbsp;");
					out.println("</td>");
				
					}else{
					out.println("<td align=center>");
					out.println("<a  href='luyin.jsp?callid="
							+ hospital.getId() + "&time="+hospital.getStartrecordtime().toString()+"&dafenmemo1="+dafenmemo+"' target=\"_self\">"+
							"听录音 </a>");
					out.println("</td>");
					out.println("<td align=center>");
					if(hospital.getDianxingflag()==null||!hospital.getDianxingflag().equals("1")){
					out.println("<a  href=good.jsp?callid="
							+ hospital.getId()+" target=\"_self\">"+
							"优秀录音 </a>");
					out.println("&nbsp;&nbsp;");
					}
					else{
					out.println("<a  href=good.jsp?callid="
							+ hospital.getId()+"&a=1 target=\"_self\">"+
							"<font color=#C3C3C3>优秀录音</font> </a>");
					out.println("&nbsp;&nbsp;");
					}
					if(hospital.getDianxingflag3()==null||!hospital.getDianxingflag3().equals("3")){
					out.println("<a  href=yewuerror.jsp?callid="
							+ hospital.getId()+" target=\"_self\">"+
							"业务欠佳 </a>");
							out.println("&nbsp;&nbsp;");
							}
					else{
					out.println("<a  href=yewuerror.jsp?callid="
							+ hospital.getId()+"&a=1 target=\"_self\">"+
							"<font color=#C3C3C3>业务欠佳</font> </a>");
					out.println("&nbsp;&nbsp;");
					}
					if(hospital.getDianxingflag4()==null||!hospital.getDianxingflag4().equals("4")){
					out.println("<a  href=jiqiao.jsp?callid="
							+ hospital.getId()+" target=\"_self\">"+
							"服务差错 </a>");
							out.println("&nbsp;&nbsp;");
							}
					else{
					out.println("<a  href=jiqiao.jsp?callid="
							+ hospital.getId()+"&a=1 target=\"_self\">"+
							"<font color=#C3C3C3>服务差错</font> </a>");
					out.println("&nbsp;&nbsp;");
					}
					if(hospital.getDianxingflag2()==null||!hospital.getDianxingflag2().equals("2")){
					out.println("<a  href=bad.jsp?callid="
							+ hospital.getId()+" target=\"_self\">"+
							"技巧欠佳 </a>");
					out.println("</td>");
					}
					else{
					out.println("<a  href=bad.jsp?callid="
							+ hospital.getId()+"&a=1 target=\"_self\">"+
							"<font color=#C3C3C3>技巧欠佳</font> </a>");
					out.println("&nbsp;&nbsp;");
					}
					
					}
					out.println("<td align=center>");
					out.println("<a  href=tijiao1.jsp?callid="
							+ hospital.getId()+" target=\"_self\">"+
							"提交 </a>");
					out.println("</td>");
					
					//out.println("<td align='center'>");
					//out.println(time2==null||time2.length==0?"":time2[0][1]);
					//out.println("</td>");
					
				}
			
			}		
			%>
				
		</table>
	
		
			</form>
		
		<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
		<form name="queryform1" action="main_right.jsp" method="post">
		 <input type="hidden" name="currpage"  />
						<input type="hidden" name="mutex" value="1"></input>
		<input type="hidden" name="username" value="<%=username%>"></input>
						<input type="hidden" name="name" value="<%=name%>"></input>
						<input type="hidden" name="start" value="<%=start%>"></input>
						<input type="hidden" name="end" value="<%=end%>"></input>
						<input type="hidden" name="starttime" value="<%=starttime%>"></input>
						<input type="hidden" name="endtime" value="<%=endtime%>"></input>
					<input type="hidden" name="mutex" value="<%=mutex%>"></input>
					</form>
	 <td align="center" valign="middle" class="num_font"><div class="page1_box">
			<elile:navigateBar navigateform="navigateform"
								actionName="main_right.jsp" formName="queryform1" />	
								  </div></td>
        </tr>
	 </table>
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
		<form action="tjhistroy_query.jsp" name="tjform">
			
		</form>
		<form action="tijiao.jsp" name="tijiaoform">
			<input type=hidden name="checkboxids" value=""></input>
		</form>
		<form action="othertjhistroy.jsp" name="otherform">
			
		</form>
		<form action="../reportzhijian/reportMain.jsp" name="reportform">
			
		</form>
	</body>
</html>
<%}
catch(Exception ex){

} 
finally{

}
%>

	</body>
</html>
