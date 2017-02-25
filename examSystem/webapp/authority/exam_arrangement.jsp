<%@page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
 ProfessionBean professionBean = new ProfessionBean();
 List<EPapers> p_list = professionBean.getExamingPapers();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>考务安排查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="newcss/style.css"/>
	<link rel="stylesheet" type="text/css" href="inc/all.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
function exportSinfo(){
	var id = $("#examId").val();
	if(id!=""){
		document.exportInfo.action="sinfoExport.action";
		document.exportInfo.submit();
	}else{
		alert("请查询出考场信息！")
	}
}
/*function exportRinfo(){
	document.exportInfo.action="rinfoExport.action";
	document.exportInfo.submit();
}*/
function checkPaper(){
	var sjmc = $("#paper").find("option:selected").text();
	$("#examId").val(sjmc);
	document.addstaffForm.submit();
}
</script>
  </head>
  
  <body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
	  <tr>
	    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="left" valign="middle" class="header1"></td>
	        <td class="header2">考务信息查询</td>
	        <td class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
	      </tr>
	    </table></td>
	    <td width="53%"  align="left"></td>
	  </tr>
	  <tr>
	    <td colspan="2" valign="top" ><div id="content1" class="borader">
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
			      <s:form method="post" action="examArrangement.action" name="addstaffForm" >
		              <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
		                <tr>
		                 <td width="13%" height="28" align="center">试卷名称：</td>
		                  <td width="20%" align="center">
                  			<select id="paper" name="paper" onchange="checkPaper()">
								<option value="">请选择</option>
								<%
									for(int i=0;i<p_list.size();i++){
										EPapers paper = p_list.get(i);
										out.print("<option value="+paper.getSjId()+">"+paper.getSjMc()+"</option>");
									}
								%>
							</select>
		                  </td>
		                  <td width="13%" height="28" align="center">试卷名称：</td>
		                  <td width="20%" align="center"><s:textfield name="examId" id="examId" size="25"/></td>
		                  <td width="3%"><input type="hidden" name="currpage" value="1"/></td>
		                  <td align="left"><input name = "button_editfile" type="submit" class="submit_2" value="查询" /></td>
		                  <td align="left"><a href="javascript:void(0)" class="infocount" onclick="exportSinfo()" style="color: green">导出考场信息</a></td>
		                </tr>
		              </table>
		           </s:form>
			    
			      <s:form action="" name="exportInfo"></s:form>
			      
                </td>  
               </tr>
             </table>
	         <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
	          <tr>
	            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td align="left" class="header7"></td>
	                        <td class="header8">
	                        	<font style="float: left;">考生信息&nbsp;&nbsp;</font>
	                        	<!-- <a href="javascript:void(0)" class="infocount" onclick="exportSinfo()" style="color: green">导出考生信息</a> -->
	                        </td>
	                      </tr>
	                  </table></td>
	                </tr>
	              </table>     
	           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
	             <tr class="title_font">
	                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号 </span></td>
	                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">考卷</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">考场</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">考场地址</span></td>
	             </tr>  
	               <s:iterator value="staff" id="user"  status="state">
		              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
		                <td align='center' class='num_font'><s:property value="#user.zkh"/></td>
		                <td align='center' class='num_font'><s:property value="#user.examineename"/></td>
			            <td align='center' class='num_font'><s:property value="#user.IDnumber"/></td>
			     	    <td align='center' class='num_font'><s:property value="#user.examid"/></td>
			     	    <td align='center' class='num_font'><s:property value="#user.examroom"/></td>
			     	    <td align='center' class='num_font'><s:property value="#user.examroomadress"/></td>
		              </tr>
	           		</s:iterator>
				</table>
				<c:if test="${staff!=null }">
					<s:form action="examArrangement.action" name="chaForm">
						<s:hidden name="examId"></s:hidden>
						<elile:navigateBar navigateform="navigateform" actionName="examArrangement.action" formName="chaForm"/>
					</s:form>
						</c:if>
			  </td>
			</tr>
		 </table>
		 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
	          <tr>
	            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td align="left" class="header7"></td>
	                        <td class="header8">
	                        	<font style="float: left;">考场信息&nbsp;&nbsp;</font>
	                        	<!-- <a href="javascript:void(0)" class="infocount" onclick="exportRinfo()" style="color: green">导出考场信息</a> -->
	                        </td>
	                        
	                      </tr>
	                  </table></td>
	                </tr>
	              </table>     
	           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
	             <tr class="title_font">
	                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">考点名称 </span></td>
	                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考场名称</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">教师机用户名</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">教师机密码</span></td>
	               <%--  <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">专家姓名</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">本次担任职务</span></td> --%>
	             </tr>  
	               <s:iterator value="eArrangement" id="info"  status="state">
		              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
		                <td align='center' class='num_font'><s:property value="#info.kdid"/></td>
		                <td align='center' class='num_font'><s:property value="#info.kcid"/></td>
			            <td align='center' class='num_font'><s:property value="#info.username"/></td>
			     	    <td align='center' class='num_font'><s:property value="#info.password"/></td>
			     	    <%-- <td align='center' class='num_font'><s:property value="#info.expertName"/></td>
			     	    <td align='center' class='num_font'><s:property value="#info.jobTitle"/></td> --%>
		              </tr>
	           		</s:iterator>
				</table>
			  </td>
			</tr>
		 </table>
	  	</div></td>
	  	</tr>
	  	</table>
</body>
</html>
