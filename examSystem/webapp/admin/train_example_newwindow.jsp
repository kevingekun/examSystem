<%@page contentType="text/html;charset=gbk"%>
<%@page import="java.util.List" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamRealQuestions" %>
<jsp:useBean id="questionservice" class="com.wondersgroup.falcon.beans.exam.ExamQuestionServiceImple"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>试题查询页面</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script language="javascript">
		function checkAll(chec){
			var singlearray = document.getElementsByName("singlecheck");
			for(var i=0;i<singlearray.length;i++){
				singlearray[i].checked = chec;
			}
		}	
		
		function deleteQuestion(){
			if(window.confirm("您确定要删除所选项吗？")){
				document.deleteForm.submit();
			}else{
				return ;
			}
		}		
		
		function init(){
			document.getElementById("error").innerText="";
		}
		
		function backToParent(id,name){
			opener.document.trainexampleform.realquesid.value=id;
			opener.document.trainexampleform.example.value= name;
			window.close();
		}
		
	</script>
	<% 
		String realquesname = request.getParameter("query_realquesname");
		if(realquesname==null)realquesname="";
		String businesstype = request.getParameter("query_businesstype");
		if(businesstype==null)businesstype="0";
		String questiontype = request.getParameter("query_questiontype");
		if(questiontype==null)questiontype="0";
		String importance = request.getParameter("query_importance");
		if(importance==null)importance="0";
		List list = questionservice.findByCrits(realquesname, businesstype, questiontype, importance); 
	
		String errorinfo = (String)request.getAttribute("error");
		if(errorinfo==null) errorinfo="";
	%>
	<BODY scroll="no">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
			  <td height="3" colspan="3"></td>
			</tr>
			<tr> 
				<td width="8"><img src="<%=request.getContextPath() %>/images/min_01.gif" width="8" height="32"></td>
				<td background="<%=request.getContextPath() %>/images/min_02.gif">
					<div align="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
			            	<tr> 
				            	<td width="15"><strong><img src="<%=request.getContextPath() %>/images/ico/search.gif" width="16" height="16"></strong></td>
				                <td><strong> &nbsp;试题列表</strong>
			              			<div align="left"></div></td>
			                </tr>
			             </table>
		             </div>
		        </td>
		    	<td width="8"><img src="<%=request.getContextPath() %>/images/min_03.gif" width="8" height="32"></td>
			</tr>
		    <tr> 
				<td height="5" colspan="3"></td>
		    </tr>
		</table> 
				
		<form name="queryForm" action="<%=request.getContextPath() %>/admin/exam_question_query.jsp" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="5"><img src="<%=request.getContextPath() %>/images/k_01.gif" width="5" height="4"></td>
				<td background="<%=request.getContextPath() %>/images/k_02.gif"></td>
				<td width="6"><img src="<%=request.getContextPath() %>/images/k_03.gif" width="6" height="4"></td>
			</tr>
			<tr>
				<td background="<%=request.getContextPath() %>/images/k_04.gif"></td>
				<td height="50" bgcolor="#F2F8FD"> 
					<table width="100%" border="0" align="center" cellpadding="1" cellspacing="2">
						<tr>
							<td width="62" rowspan="2"><div align="center"><img src="<%=request.getContextPath() %>/images/n_02.gif" width="43" height="53"></div></td>
							<td>
								<table  width=100% border=0 cellspadding=0 cellspacing =0 bgcolor="#F2F8FD">
								<tr>
									<td  width=8% align=right >题&nbsp;目&nbsp;名：</td>
									<td colspan=5>
										<input type=text name=query_realquesname size=82>
									</td>
								</tr>
								<tr  >
									<td align=right width=8%>业务类型：</td> 
									<td align=left width=8%>
										<select name="query_businesstype">
											<option value=0>请选择</option>
											<option value=1>城保类</option>
											<option value=2>镇保类</option>
										</select>
									</td>
									<td align=right width=8%>试题题型：</td>
									<td align=left width=8%>
										<select name="query_questiontype">
											<option value=0>请选择</option>
											<option value=1>单选题</option>
											<option value=2>多选题</option>
											<option value=3>判断题</option>
											<option value=4>问答题</option>
										</select>
									</td>
									<td align=right width=8%>重要性：</td>
									<td align=left width=8%>
										<select name="query_importance">
											<option value=0>请选择</option>
											<option value=1>普通</option>
											<option value=2>较高</option>
											<option value=3>很高</option>
										</select>								
									</td>
									</tr>
									<tr>
										<td colspan=6 align=center>
											<input type=button class="BigButton" onclick="submit()" value="查询试题">
										</td>
									</tr>
								</table>
							</td>	           				
						</tr>
					</table>		
				</td>
			  <td background="<%=request.getContextPath() %>/images/k_05.gif"></td>
			</tr>
			<tr>
				<td><img src="<%=request.getContextPath() %>/images/k_06.gif" width="5" height="4"></td>
				<td background="<%=request.getContextPath() %>/images/k_07.gif"></td>
				<td><img src="<%=request.getContextPath() %>/images/k_08.gif" width="6" height="4"></td>
			</tr>
		</table>
		</form>

		<form name="deleteForm" method="post" action="<%=request.getContextPath() %>/questionServlet">
		<input type=hidden name="actionType" value="delete">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
				<td height="5" colspan="4"></td>
			</tr>
			<tr> 
				<td width="3"><img src="<%=request.getContextPath() %>/images/k1_01.gif" width="3" height="30"></td>
				<td width="21" background="<%=request.getContextPath() %>/images/k1_03.gif"><div align="center"><img src="<%=request.getContextPath() %>/images/k1_02.gif" width="21" height="30"></div></td>
				<td width="98%" background="<%=request.getContextPath() %>/images/k1_03.gif"><strong>试题列表</strong>
					&nbsp;&nbsp;
					<a href=# onclick="deleteQuestion()">删除</a>
					&nbsp;&nbsp;<span id="error"><%=errorinfo%></span> 
				</td>
				<td width="4" valign="top"><img src="<%=request.getContextPath() %>/images/k1_04.gif" width="4" height="30"></td>
			</tr>
			<tr> 
				<td background="<%=request.getContextPath() %>/images/k1_05.gif"> </td>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C6E2FB">
						<tr bgcolor="#E7F3FE" height="23">
							<td align="center" width=35>
								<input type=checkbox name="all" onclick="checkAll(this.checked)">
							</td>
							<td align="center" width=35>序号</td>
							<td align="center" width=500>试题名</td>
							<td align="center" width=60>试题类型</td>
							<td align="center" width=40>重要性</td>
							<td align="center" >操作</td>
						</tr>
					</table>

					<div style="height:350;width:793;overflow:auto">
						<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#C6E2FB">
						<%
							//List list = questionservice.findByCrits("","0","0","0");
								if(list!=null&&list.size()>0){
								for(int i=0;i<list.size();i++){
									ExamRealQuestions ers = (ExamRealQuestions)list.get(i);
									out.println("<tr bgcolor='#ffffff'>");
									out.println("<td width=35 align=center>");
		
									out.println("<input type=checkbox name='singlecheck' value='"+ers.getRealquesid()+"' >");
									
									out.println("</td>");
									out.println("<td width=35 align=center align=center>"+(i+1)+"</td>");
									out.println("<td align=center  width=500><div id='"+ers.getRealquesid()+"'>"+(ers.getExamquestions()==null?"":ers.getExamquestions().getQuestionname().trim())+"</div></td>");
									out.println("<td align=center width=60 ><div id='type"+ers.getRealquesid()+"'>"+(ers.getExamquestype()==null?"":ers.getExamquestype().getTypename().trim())+"</div></td>");
									out.println("<td width=40 align=center >"+(ers.getImportance()==null?"":ers.getImportance().getImpname().trim())+"</td>");
									out.println("<td align=center><a href=# onclick=\"backToParent('"+ers.getRealquesid()+"','"+ers.getExamquestions().getQuestionname()+"')\" >添加</a></td>");	
									out.println("</tr>");	
								}
							}	
						%>
			
					</table>
					</div>
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
	</BODY>
	<script>
		setTimeout("init()",5000);
	</script>
</HTML>
