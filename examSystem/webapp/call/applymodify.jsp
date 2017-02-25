<%@ page contentType = "text/html;charset=utf-8" %>
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
<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.beans.common.*" %>
<%@ page import="com.wondersgroup.falcon.beans.auth.AuthBeans"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*" %>

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
	   request.setCharacterEncoding("utf-8");
	    String gonghao=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername().toString();
	    
	    Users users = new Users();
	    Users sender = new Users();	
	    AuthBeans zd = new AuthBeans();	
        sender=zd.findByUsername(gonghao);
       
       
        Users zhijian1 = zd.findByUsername("029");
				Users zhijian2 = zd.findByUsername("107");;
				Users zhijian3 = zd.findByUsername("108");
				Users zhijian4 = zd.findByUsername("109");
				Users zhijian5 = zd.findByUsername("110");
				Users zhijian6 = zd.findByUsername("030");
				//Set temp = group.getUsers();
				Set temp=new HashSet();
				temp.add(zhijian1);
				temp.add(zhijian2);
				temp.add(zhijian3);
				temp.add(zhijian4);
				temp.add(zhijian5);
				temp.add(zhijian6);
	    ChouyangDAO  chouyangService = new ChouyangDAO();
		String modifyreason=request.getParameter("modifyreason");
		String callid=request.getParameter("callid");
		String A=request.getParameter("A");
		 if (A==null||A.equals(""))
		  A = "";
		  if(A.equals("1")){
			chouyangService.modifyapply(callid,modifyreason);
			//if(temp!=null&&temp.size()!=0){
				
					
			//		Set messageusers = new HashSet();
			//		Message msg = new Message();
			//		msg.setTitle("系统提示：质检修改分数申请"); 
			//		msg.setContentstring("系统提示：质检修改分数申请"); 
			//		msg.setSender(sender);
			//		msg.setEffectdate(new Date());
			//		msg.setNoteffectdate(new Date());
			//		msg.setSendDt(new Date());
			//		msg.setType(8);
			//	for(int j=0;j<temp.toArray().length;j++){
			//	users = (Users)temp.toArray()[j];
			//		MessageUsers mu = new MessageUsers();
			//		mu.setDiscription("");
			//		mu.setStatus("0");
			//		mu.setUser(users);
			//		mu.setMessage(msg);
			//		messageusers.add(mu);
				
			//		MessageBean mb = new MessageBean();
			//		msg.setMessageusers(messageusers);
			//		mb.sendMessage(msg);
			//			}
			//		}
			out.println("<script>");
			//out.println("alert('提交成功');");
			out.println("window.location = 'tjhistroy_query.jsp?'");
			out.println("</script>");	
			}
 %>
	<form  name="applyform">
	<input type="hidden" name="A" value="1">
	<input type="hidden" name="callid" value="<%=callid %>">
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
			                <td align="center"><strong> &nbsp;<font size=3>修改分数申请单</font></strong>
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
		  	<td valign="middle"><div align="center" >修改理由：</div></td>
		  	<td colspan="3"><TEXTAREA id=modifyreason name=modifyreason rows=10 cols=55 style="overflow:auto" ></TEXTAREA></td>
		</tr>
			
		<tr bgcolor=#F1F8FE>
	    	<td colspan="7">
	    		<div align="center">
	  				<input type="button"  onclick="save()" value="确认"  class=Bigbutton>
	      		</div>
	      	</td>
		  </tr>
	</table>
		</form>
		
		
</body>

</html>
