<%@ page contentType="text/html;charset=gb2312"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>无标题文档</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>
<%@ page import="java.util.*"%>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil"%>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
<%@ page import="com.wondersgroup.falcon.model.shortmessage.*" %>
<%@ page import="java.util.List,java.util.Date,java.text.SimpleDateFormat,java.util.Set,java.util.HashSet" %>

<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.beans.common.*" %>
<%@ page import="com.wondersgroup.falcon.beans.auth.AuthBeans"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
	<body>
		<%
		String gonghao=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername().toString();
	    
	    Users users = new Users();
	    Users sender = new Users();	
	    AuthBeans zd = new AuthBeans();	
        sender=zd.findByUsername(gonghao);
        
       
                Users zhijian1 = zd.findByUsername("029");
				Users zhijian2 = zd.findByUsername("030");;
				Users zhijian3 = zd.findByUsername("107");
				Users zhijian4 = zd.findByUsername("108");
				Users zhijian5 = zd.findByUsername("109");
				Users zhijian6 = zd.findByUsername("110");
				
				Set temp=new HashSet();
				temp.add(zhijian1);
				temp.add(zhijian2);
				temp.add(zhijian3);
				temp.add(zhijian4);
				temp.add(zhijian5);
				temp.add(zhijian6);
	    String callid = request.getParameter("callid");
		 ZhijianDAO pit = new ZhijianDAO();   
			
		 pit.replace2(callid);
	//	if(temp!=null&&temp.size()!=0){
				
					
		//			Set messageusers = new HashSet();
		//			Message msg = new Message();
		//			msg.setTitle("系统提示：质检提交分数"); 
		//			msg.setContentstring("系统提示：质检提交分数"); 
		//			msg.setSender(sender);
		//			msg.setEffectdate(new Date());
		//			msg.setNoteffectdate(new Date());
		//			msg.setSendDt(new Date());
		//			msg.setType(7);
		//		for(int j=0;j<temp.toArray().length;j++){
		//		users = (Users)temp.toArray()[j];
		//			MessageUsers mu = new MessageUsers();
		//			mu.setDiscription("");
		//			mu.setStatus("0");
		//			mu.setUser(users);
		//			mu.setMessage(msg);
		//			messageusers.add(mu);
				
		//			MessageBean mb = new MessageBean();
		//			msg.setMessageusers(messageusers);
		//			mb.sendMessage(msg);
		//				}
		//			}
			out.println("<script>");
			out.println("alert('提交成功');");
			out.println("window.location = 'main_right.jsp?'");
			out.println("</script>");	
		%>
	</body>
</html>
