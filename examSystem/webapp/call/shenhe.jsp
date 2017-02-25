<%@ page contentType = "text/html;charset=UTF-8"%>
<%@ page import="java.util.List,java.util.Date,java.text.SimpleDateFormat,java.util.Set,java.util.HashSet" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="java.util.List,java.util.Date,java.util.Iterator"%>
<%@page import="java.util.List,java.text.SimpleDateFormat" %>
	<%@ page import="com.wondersgroup.falcon.model.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
	<%@ page import="com.wondersgroup.falcon.beans.auth.AuthFacade" %>
	<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
<%@ page import="com.wondersgroup.falcon.model.auth.UserType" %>
<%@ page import="com.wondersgroup.falcon.model.citizeninfo.HisAttr" %>
<%@ page import="com.wondersgroup.falcon.beans.archives.HisTree"  %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"  %>
<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
<%@ page import="com.wondersgroup.falcon.model.select.*" %>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.beans.common.*" %>
<%@ page import="com.wondersgroup.falcon.beans.auth.AuthBeans"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*" %>
<%@ page import="com.wondersgroup.falcon.beans.message.MessageBean" %>
<%@ page import="com.wondersgroup.falcon.model.message.Message" %>
<%@ page import="com.wondersgroup.falcon.model.message.MessageUsers" %>
  <title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<script type="text/javascript">
 function save(){
 		document.applyform.submit();
 		}
</script>
 <body>
<%
        request.setCharacterEncoding("UTF-8");
         String zhijianid=request.getParameter("zhijianid");
         if(zhijianid==null)zhijianid="";
        String gonghao=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername().toString();
	    
	    Users users = new Users();
	    Users sender = new Users();	
	    AuthBeans zd = new AuthBeans();	
        sender=zd.findByUsername(gonghao);
       
      if(zhijianid.equals("029")){
       users = zd.findByUsername("029");
       }else if(zhijianid.equals("107")){
       users = zd.findByUsername("107");
      }else if(zhijianid.equals("108")){
      users = zd.findByUsername("108");
      }else if(zhijianid.equals("109")){
      users = zd.findByUsername("109");
      }else if(zhijianid.equals("110")){
      users = zd.findByUsername("110");
      }else if(zhijianid.equals("030")){
      users = zd.findByUsername("030");
       }else{
      users = zd.findByUsername("029");
       }
               
	    ChouyangDAO chouyangService = new ChouyangDAO();
	   
		String shenhereason=request.getParameter("shenhereason");
		String modifyapplyflag=request.getParameter("modifyapplyflag");
		String callid=request.getParameter("callid");
		User uuser = ((UserDetailsImpl)util.getUserDetails()).getUser();
 	    String shenhename=uuser.getUsername();
		String A=request.getParameter("A");
		 if (A==null||A.equals(""))
		  A = "";
		  if(A.equals("1")){
			chouyangService.shenhe(callid,shenhereason,modifyapplyflag,shenhename);
			
			//Set messageusers = new HashSet();
			//		Message msg = new Message();
			//		msg.setTitle("系统提示：修改分数申请审核结果"); 
			//		msg.setContentstring("系统提示：修改分数申请审核结果"); 
			//		msg.setSender(sender);
			//		msg.setEffectdate(new Date());
			//		msg.setNoteffectdate(new Date());
			//		msg.setSendDt(new Date());
			//		msg.setType(10);
			
			//      MessageUsers mu = new MessageUsers();
			//		mu.setDiscription("");
			//		mu.setStatus("0");
					
			//		mu.setUser(users);
			//		mu.setMessage(msg);
			//		messageusers.add(mu);
				
			//		MessageBean mb = new MessageBean();
			//		msg.setMessageusers(messageusers);
			//		mb.sendMessage(msg);
					
					
			      List modifyapplyflagQuerylist;
      	          String modiflag="";;
                  modifyapplyflagQuerylist = chouyangService.modifyapplyflagQuery(callid);
	              Select1 rec2 = (Select1)modifyapplyflagQuerylist.get(0);
	              modiflag=rec2.getModifyapplyflag();
	              if (modiflag==null||modiflag.equals(""))
		              modiflag = "";
	       if(modiflag.equals("2")){
	        chouyangService.addzhijianpingfenmingxitemp(callid);
	        }      
			out.println("<script>");
			//out.println("alert('提交成功');");
			out.println("window.location = 'modifyappQuery.jsp'");
			out.println("</script>");	
			}
 %>
	<form  name="applyform">
	<input type="hidden" name="A" value="1">
	<input type="hidden" name="callid" value="<%=callid %>">
	<input type="hidden" name="zhijianid" value="<%=zhijianid %>">
		<br><br>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
		  <td height="3" colspan="3"></td>
		</tr>
		<tr> 
			<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
			<td background="../images/min_02.gif">
				<div align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
		            	<tr> 
			            	<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
			                <td align="center"><strong> &nbsp;<font size=3>审核修改分数申请</font></strong>
		              			<div align="center"></div></td>
		                </tr>
		             </table>
	             </div>
	        </td>
	    	<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
		</tr>
	    <tr> 
			<td height="5" colspan="3"></td>
	    </tr>
	   
	</table>   
	<table><tr height=15><td></td></tr></table>
	<table width="60%" height=200 border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#84B7C6">
		
		
	<tr bgcolor="#ffffff">
		  	<td valign="middle"><div align="center" >审核：</div></td>
		  	<td >
		  	<INPUT TYPE="radio" NAME="modifyapplyflag" value="2" >
								通过
			<INPUT TYPE="radio" NAME="modifyapplyflag" value="3" >
								不通过
		  	</td>
		</tr>
	  <tr bgcolor="#ffffff">
		  	<td valign="middle"><div align="center" >审核理由：</div></td>
		  	<td colspan="3"><TEXTAREA id=shenhereason name=shenhereason rows=11 cols=55 style="overflow:auto" ></TEXTAREA></td>
		</tr>
			
		<tr bgcolor=#F1F8FE>
	    	<td colspan="7">
	    		<div align="center">
	  				<input type="button"  onclick="save()" value="提交"  class=Bigbutton>
	      		</div>
	      	</td>
		  </tr>
	</table>
		</form>
		
		
</body>

</html>
