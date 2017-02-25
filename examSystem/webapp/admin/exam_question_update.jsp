<%@page contentType="text/html;charset=GBK"%>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRealQuestions" %>
<jsp:useBean id="questionservice" class="com.wondersgroup.falcon.beans.exam.ExamQuestionServiceImple"/>
<% 
	String errorinfo = (String)request.getAttribute("error");
	if(errorinfo==null) errorinfo="";
	
	String ersid = request.getParameter("ersid");
	
	ExamRealQuestions ers = questionservice.findRealQuesById(ersid);
%>
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<script>
	function updateQuestion(){
		if(document.updateForm.update_questionname.value==""){
			alert("请输入试题名！");
			return ;
		}
		if(document.updateForm.update_businesstype.value=="0"){
			alert("请选择一个业务类型！");
			return ;
		}
		if(document.updateForm.update_importance.value=="0"){
			alert("请选择重要性！");
			return ;
		}				
		document.updateForm.submit();
	}
</script>
<form name="updateForm" method="post" action="<%=request.getContextPath() %>/questionServlet">
<input type=hidden name="actionType" value="update">
<input type=hidden name="ersid" value="<%=ersid %>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr> 
		<td height="5" colspan="4"></td>
	</tr>
	<tr> 
		<td width="3"><img src="<%=request.getContextPath() %>/images/k1_01.gif" width="3" height="30"></td>
		<td width="21" background="<%=request.getContextPath() %>/images/k1_03.gif"><div align="center"><img src="<%=request.getContextPath() %>/images/k1_02.gif" width="21" height="30"></div></td>
		<td width="98%" background="<%=request.getContextPath() %>/images/k1_03.gif"><strong>试题更改</strong>
			&nbsp;&nbsp;<span id="error"><%=errorinfo%></span> 
		</td>
		<td width="4" valign="top"><img src="<%=request.getContextPath() %>/images/k1_04.gif" width="4" height="30"></td>
	</tr> 
	<tr> 
		<td background="<%=request.getContextPath() %>/images/k1_05.gif"> </td>
		<td colspan="2" align=center valign=center>
			<br>
			<table border =0 cellpadding=0 cellspaceing=0 >
				<tr height=30>
					<td>试&nbsp;题&nbsp;名：</td>
					<td><input type=text size=50 name="update_questionname" value="<%=ers.getExamquestions().getQuestionname() %>"></td>
				</tr>
				<tr height=30>
					<td>业务类型：</td>
					<td>
						<select name="update_businesstype" >
							<option value=0>请选择</option>
							<option value=1 <%if(ers.getBusinesstype().equals("1") ) out.print("selected"); %>>城保类</option>
							<option value=2 <%if(ers.getBusinesstype().equals("2") ) out.print("selected"); %>>镇保类</option>
						</select>
					</td>
				</tr>				 
				<tr height=30>
					<td>重&nbsp;要&nbsp;性：</td>
					<td>
						<select name="update_importance" >
							<option value=0 >请选择</option>
							<option value=1 <%if(ers.getImportance().getImpid().equals("1") )out.print("selected"); %>>普通</option>
							<option value=2 <%if(ers.getImportance().getImpid().equals("2") )out.print("selected"); %>>较高</option>
							<option value=3 <%if(ers.getImportance().getImpid().equals("3") )out.print("selected"); %>>很高</option>
						</select>	 					
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<input type=button class="SmallButton" onclick="history.back(1)" value="返回">
						&nbsp;&nbsp;
						<input type=button class="SmallButton" onclick="updateQuestion()" value="提交">
					</td>
				</tr>				
			</table>
			<br>
		</td>
		<td background="<%=request.getContextPath() %>/images/k1_06.gif"><img src="<%=request.getContextPath() %>/images/k1_06.gif" width="4" height="2"></td>
	</tr>
   <tr> 
		<td><img src="<%=request.getContextPath() %>/images/k1_07.gif" width="3" height="5"></td>
		<td colspan="2" background="<%=request.getContextPath() %>/images/k1_08.gif"> </td>
		<td><img src="<%=request.getContextPath() %>/images/k1_09.gif"></td>
   </tr>
</table>
</form>