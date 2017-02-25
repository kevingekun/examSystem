<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="com.wondersgroup.kaoshi.dao.*"%>
<%@ page import="com.wondersgroup.falcon.Util.PageUtil"%>
<%@ page import="com.wondersgroup.falcon.Util.ParamUtil"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>

<%		
		int pagesize = com.wondersgroup.falcon.Util.ParamUtil.getIntParameter(request, "pagesize", 10);
  		int currpage = com.wondersgroup.falcon.Util.ParamUtil.getIntParameter(request, "currpage", 1);
  		int pagenum = com.wondersgroup.falcon.Util.ParamUtil.getIntParameter(request, "pagenum", 5);
	  	
		
		String dtstart =request.getParameter("dtstart");
		//System.out.println(" -------页面上："+dtstart.length() );
		
	   	if(dtstart==null){
	   		dtstart="";
	   	}
   	 	String dtend =request.getParameter("dtend");
   	 	if(dtend==null){
   	 		dtend="";	
   	 	}
		//System.out.println(" -------页面下："+dtend.length() );
		
		Date sjbegin = ParamUtil.getDateParameter(request,"dtstart","",null);
		Date sjend = ParamUtil.getDateParameter(request,"dtend","",null);
		
		System.out.println(dtstart+"试卷");
		
   	 	
   	 	EExerciseDAOImpl tDAO = new EExerciseDAOImpl();
		Object[][] o =null;

		int total = 0;
			o = tDAO.getLianxiALL(dtstart,dtend,(currpage-1)*pagesize,pagesize);
			total = tDAO.getLianxiALLNum(dtstart,dtend);

		
		com.wondersgroup.falcon.Util.NavigateForm navigateform = new com.wondersgroup.falcon.Util.NavigateForm();
	  	navigateform.setCurrpage(currpage);
	  	navigateform.setPagesize(pagesize);
	  	navigateform.setTotal(total);
	  	navigateform.setPagenum(pagenum);
	  	request.setAttribute("navigateform", navigateform);  

	 %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
</head>
<body >
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">练习统计</td>
          <td  class="header3"></td>
          <td></td>
        </tr>
      </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
        <table  width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="10"></td>
                </tr>
    </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询条件</td>
                    </tr>
                </table></td>
              </tr>
            </table>
  
 	<form name="queryform" method="post" action="lianxi_summary.jsp">
 	<input id="pagenumber" name="pagenumber" type="hidden"  value="1">
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    	<tr>
             <td height="10"></td>
        </tr>		
		
		
		<tr>
			<td align="center"><strong>统计时间段:</strong>
			<input type="text" style="width:20%" class="input3 date" id="dtstart" name="dtstart" onclick="WdatePicker()"/>
			到
			<input type="text" style="width:20%" class="input3 date" id="dtend" name="dtend" onclick="WdatePicker()"/>
			</td>
		</tr>
		
		<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="30" align="left"><input name="today" type="submit" class="submit_2"  value="查询"  />  
                  </tr>	
	   </table>	   
	   <p></p>
	</table>
	</form>
</td>
</tr>
</table>

<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询结果</td>
                    </tr>
                </table></td>
              </tr>
 </table>
 
 <table width=50% align=center>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		
	<table id="table2" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb" >
      
			<tr class="title_font">
				<td colspan="8" align="center">
					练习统计
				</td>
			</tr>
			<table id="table1" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb" >
	        <tr class="title_font"> 
	            <td align="center">序号</td>
	         	<td align="center">姓名</td>
	         	<td align="center" >练习时长（分钟）</td>
	         	<td align="center" >练习次数</td>
	          	<td align="center">练习正确率</td>
	          	<td align="center">详细信息</td>
	        </tr>
                       
      
      	<%    			
       			
      			String username ="";
      			String realname ="";
				String interval = "";
				String rights ="";
				String sums ="";
				
       			for(int i=0;i<o.length;i++){
       				username = String.valueOf(String.valueOf(o[i][0]));
       				realname = String.valueOf(String.valueOf(o[i][1]));
       				interval = String.valueOf(String.valueOf(o[i][2]));
       				rights =   String.valueOf(String.valueOf(o[i][3]));
       				sums =   String.valueOf(String.valueOf(o[i][4]));
       				//System.out.println("------"+username);
       				
			%>
    

     
				<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'>
				      <td align="center"><%=i+1%></td>
			          <td align="center"><%=realname%></td>
			          <td align="center"><%=interval%></td>
			          <td align="center"><%=sums%></td>
			          <td align="center"><%=rights%>%</td>
					  <td align="center"><button class="submit_2" onclick="view('<%=username%>')" >详细信息</button></td>
	       		</tr>	
			<%} %>
			</table>
		</table>
		<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
          <tr>
            <td align="center" valign="middle" class="num_font"><div class="page1_box">
                <form name="queryform2" method="post" action="lianxi_summary.jsp">
          			<input type="hidden" name="sjbegin" value=<%=dtstart%> />
          			<input type="hidden" name="sjend" value=<%=dtend %> /> 
          			                         		            	
                   	<elile:navigateBar navigateform="navigateform" actionName="lianxi_summary.jsp" formName="queryform2"/>              
				</form> 
            </div></td>
          </tr>
        </table>
        
           <form name="viewform" method="post" action="lianxiquestionQuery.action">
          			<input type="hidden" name="dtstart" value=<%=sjbegin%> />
          			<input type="hidden" name="dtend" value=<%=sjend %> /> 	
          			<input type="hidden" name="username"  />                            		            	             
		    </form> 

  </body>
</html>

<script language="JavaScript" type="text/JavaScript" >

//将法规修改状态设为已完成
function view(username){
var a=username
alert(a);
	// window.location.href="lianxiquestionQuery.action?username="+username+"&sjbegin="+dt1+"&sjend="+dt2;
	//window.location.href="lianxiquestionQuery.action?username="+username;
	document.viewform.username.value=a;
	document.viewform.submit();

}


</script>
