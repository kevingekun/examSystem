<%@ page contentType="text/html;charset=gb2312"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>无标题文档</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>
	<%@ page import="java.util.List" %>
	<%@ page import="com.wondersgroup.falcon.exceptions.InfrastructureException"%>
	<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.HashSet"%>
	<%@ page import="com.wondersgroup.falcon.model.call.Zhijiankaohe" %>
<%@ page import="com.wondersgroup.falcon.dao.call.*" %>
	<body>
		<%
			String id = request.getParameter("id");
			if(id==null) id="";
			String name = request.getParameter("name");
			String value1 = request.getParameter("value1");
			String comments = request.getParameter("comments");
			if(comments==null)comments="";
			
			
			Zhijiankaohe pn = new Zhijiankaohe();
			
			ZhijiankaoheDAO dao = new ZhijiankaoheDAO();
			
			pn = dao.findZhijiankaoheById(new Long(id));
			
		
			
		
				pn.setName(name);
				pn.setValue(value1);
				pn.setComments(comments);
				dao.addZhijiankaohe(pn);

			
			out.println("<script>");
			out.println("alert('操作成功');");
			out.println("window.location = 'main_pingfen.jsp'");
			out.println("</script>");
			
		%>
	</body>
</html>
