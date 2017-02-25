<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ page import="com.wondersgroup.falcon.Util.*" %>
<%
    out.print(" <script language='javascript'>" );
    out.print(" alert('±£´æ³É¹¦'); ");
    String sjType = (String)request.getAttribute("sjType");
    if("cpt".equals(sjType)){
	    out.print(" window.parent['LeftTree2'].location.reload(); ");
		out.print(" window.location.href='question/questions_set_add_right.jsp';  " );
    }else if("written".equals(sjType)){
    	out.print(" window.parent['LeftTree3'].location.reload(); ");
		out.print(" window.location.href='question/questions_set_add_written_right.jsp';  " );
    }
	out.print("</script>");
//response.sendRedirect("question/question_add.jsp");
 %>


