<%@ page contentType="text/html;charset=gb2312"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>�ޱ����ĵ�</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>
	<%@ page import="java.util.List" %>
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
			int sum = 0;
			int count = 0;
			
			
			Zhijiankaohe pn = new Zhijiankaohe();
			Zhijiankaohe pn1 = new Zhijiankaohe();
			Zhijiankaohe pn2 = new Zhijiankaohe();
			ZhijiankaoheDAO dao = new ZhijiankaoheDAO();
			
			List subtree = null;
			pn2 = dao.findZhijiankaoheById(new Long(id));
			sum = Integer.parseInt(pn2.getValue());
			subtree = dao.getChildren(new Long(id));
			if(subtree!=null&&subtree.size()>0){
				for(int i=0;i<subtree.size();i++){
					pn1 = (Zhijiankaohe)subtree.get(i);
					count += Integer.parseInt(pn1.getValue());
				}
				if(count>=sum){
					out.println("<script>");
					out.println("alert('�ٷֱ������������޸������');");
					out.println("window.location = 'main_pingfen.jsp'");
					out.println("</script>");
				}
				else if(count<sum){
					try {
				pn.setName(name);
				pn.setValue(value1);
				pn.setParentid(id);
				pn.setComments(comments);
				pn.setVisible("1");
				dao.addZhijiankaohe(pn);
				HibernateUtil.commitTransaction();

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
			out.println("<script>");
			out.println("alert('�����ɹ�');");
			out.println("window.location = 'main_pingfen.jsp'");
			out.println("</script>");
				}
			}
			else {
			try {
				pn.setName(name);
				pn.setValue(value1);
				pn.setParentid(id);
				pn.setComments(comments);
				pn.setVisible("1");
				dao.addZhijiankaohe(pn);
				HibernateUtil.commitTransaction();

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
			out.println("<script>");
			out.println("alert('�����ɹ�');");
			out.println("window.location = 'main_pingfen.jsp'");
			out.println("</script>");
			}
		%>
	</body>
</html>
