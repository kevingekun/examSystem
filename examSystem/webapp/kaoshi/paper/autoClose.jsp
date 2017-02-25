<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*"%>
<%@page import="com.wondersgroup.falcon.acegi.AcegiUtil" %>
<%@page import="com.wondersgroup.popedom.bo.EUser" %>
<%@ page import="com.wondersgroup.falcon.acegi.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<html>

<head>
<%
request.getSession().setAttribute("ceping","1");
EPapers p = (EPapers) request.getAttribute("epapers");
double sjdf=(Double)request.getAttribute("sjdf");
//double jgfs = (Double)request.getAttribute("jgfs");
AcegiUtil acegiUtil=new AcegiUtil();
EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
String ljcf=p.getSjLjcf();
if("1".equals(p.getSjLjcf())){
String alertStr="姓名："+user.getRealname()+"身份证号码："+user.getUsername()+"准考证号码："+user.getPassword()+"考试名称："+p.getSjMc()+"总得分："+ljcf;
}
%>
<%-- <script language="javascript">
	if("1"==<%=ljcf %>){
	}else{
    alert("谢谢你耐心的回答")
	}
	window.close();
</script> --%>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	parent.menu.ushow();
});
</script>
</head>
<body class="nrbj">
<%
	if("1".equals(ljcf)){
	
 %>
 	<div style="width: 100%;font-size: 34px;text-align: center;margin-top: -20px;">
		<div style="height: 20px;"></div>
		<div class="examOnline">
			<div class="examinfo">
				<font size="36px">得分：<%=sjdf %></font>
			</div>
			<%if(sjdf>=p.getSjBhgfs()){ %>
			<div class="examinfo">
				<font size="34px" style="color: green;">恭喜您，本次考试及格！</font>
			</div>
			<%}else{ %>
			<div class="examinfo">
				<font size="34px" style="color: red;">本次考试不及格，请继续努力！</font>
			</div>
			<%} %>
			<div class="examinfo">
				<font size="34px">考试结束，请带好您的个人物品，有序离开考场！</font>
			</div>
			
		</div>
	</div>
<%-- <table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">考试信息</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
         <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left"  class="header7"></td>
                        <td  class="header8">考试结果</td>
                      </tr>
                  </table></td>
                </tr>
              </table>     
           <table  width="100%" border="1" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">考试名称</span></td>
                  <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">总得分</span></td>
             </tr>   
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
	               <td align='center' class='num_font'><%=user.getPassword() %> </td>
	               <td align='center' class='num_font'><%=user.getRealname() %> </td>
		           <td align='center' class='num_font'><%=user.getUsername() %> </td>
		     	   <td align='center' class='num_font'><%=p.getSjMc() %> </td>
		     	   <td align='center' class='num_font'><%=sjdf %> </td>
             </tr>
			</table>
					</td>
				</tr>
				
			</table>
			  
  	</div></td>
  	</tr>
  	</table> --%>
  	<%}else{%>
  	<div style="width: 100%;font-size: 34px;text-align: center;margin-top: -20px;">
		<div style="height: 20px;"></div>
		<div class="examOnline">
			<div class="examinfo">
				<font>考试结束，请带好您的个人物品，有序离开考场！</font>
			</div>
			
		</div>
	</div>
  	<% }%>
</body>
</html>
