<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>专家信息维护</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
function doQuery() {
  document.aForm.submit();
}
function checkExpert(v,bd){
	$("#expertid").val(v);
	$("#birthday").val(bd);
}
function deleteE(){
	var id = $("#expertid").val();
	if(id==""){
		alert("请选择专家！");
	}else{
		$.ajax({
			type: 'post',
			url: 'deleteExpert.action?expertId='+id,
			success:function(result){
				if(result=="d_success"){
					alert("删除成功！");
					 window.location.reload(); 
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}
function changeE(){
	var id = $("#expertid").val();
	var birthday = $("#birthday").val();
	if(id==""){
		alert("请选择专家！");
	}else{
		window.open ("authority/update_expert.jsp?expertid="+id+"&birthday="+birthday, "newwindow", "height=300, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
		//var url = "authotity/update_expert.jsp";
		//var newWin = window.showModalDialog(url,'',"dialogHeight=400px;dialogWidth=800px;");
		//var url = "${pageContext.request.contextPath}/pages/h2/f14001104/arrangeexpertInfo.jsp";
		//var newWin = window.showModalDialog(url,obj,"dialogHeight=400px;dialogWidth=800px;help:no;status:no;center:yes;Minimize=yes;Maximize=yes;");
	}
}
</script>
</head>
<body class="nrbj">
 
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
	  <tr>
	    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="left" valign="middle" class="header1"></td>
	        <td class="header2">专家信息维护</td>
	        <td class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
	      </tr>
	    </table></td>
	    <td width="53%" align="left"></td>
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
	               <s:form action="maintainexpert.action" name="aForm" method="POST">
	                 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	                   <tr>
	                     <td height="8" colspan="7"><input type="hidden" name="currpage" value="1"/></td>
	                   </tr>
	                   <tr>
	                     <td width="10%" align="right">姓名：</td>
	                     <td width="15%" align="left"><s:textfield name="expert.name" /></td>
	                     <td width="10%" align="right">所属单位：</td>
	                     <td width="15%" align="left"><s:textfield name="expert.org" /></td>
	                     <td width="10%" align="right">所属委员会：</td>
	                     <td width="15%" align="left"><s:textfield name="fexpert.committee" /></td>
	                     <td nowrap="nowrap"  width="10%" align="right">专家类别:</td>	
						 <td width="15%" align="left">
							<select id="expertstyle" name="style">
								<option value="">请选择</option>
								<option value="1">考评专家</option>
							 	<option value="2">命题专家</option>
							 	<option value="3">监考老师</option>
							 	<option value="4">督导专家</option>
							</select>
						 </td>	
	                     <td>&nbsp;</td>
	                   </tr>
	                 </table>
	               </s:form>
	               <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
			          <tr>
			            <td align="center">
							<input name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查 询" />
			           </td>
			         </tr>
			         <tr height="8px"></tr>
			       </table>
	            </td>
	         </tr>
	       </table>
	       
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
	         <tr>
	          <td class="borader3">
	           <table width="100%" border="0" cellspacing="0" cellpadding="0">
	             <tr>
	               <td>
	               	<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	                   <tr>
	                     <td  align="left" valign="middle" class="header7"></td>
	                     <td  class="header8">
	                     	<font style="float: left;">查询结果</font>
	                     	<input id="expertid" value="" type="hidden"/>
	                     	<input id="birthday" value="" type="hidden"/>
	                     	<a href="#" onClick="deleteE()" class="infocount"><font color=red>删除</font></a>&nbsp;&nbsp;
	                     	<a href="#" onClick="changeE()" class="infocount"><font color=red>修改</font></a>&nbsp;&nbsp;
	                     </td>
	                   </tr>
	               	</table>
	               </td>
	             </tr>
	           </table>
	           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
	             <tr class="title_font">
	               	<td width="3%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
	                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">专家姓名</span></td>
	                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">证件类型</span></td>
	                <td width="13%" align="center" bgcolor="#C7E2F8"><span class="out">证件号码</span></td>
	                <td width="8%" align="center" bgcolor="#C7E2F8"><span class="out">出生日期</span></td>
	                <td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">所属单位</span></td>
	                <td width="16%" align="center" bgcolor="#C7E2F8"><span class="out">擅长专业</span></td>
	                <td width="8%" align="center" bgcolor="#C7E2F8"><span class="out">联系电话</span></td>
	                <%-- <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">专家类别</span></td> --%>
	                <td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">职称</span></td>
	             </tr>
	        	<c:forEach var="aBean" items="${experts}" varStatus="status">
	             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
	                <td align='center' class='num_font'>
	                	<input type="radio" id="radio" name="checkexpert" onclick="checkExpert(<c:out value='${aBean.hzz001}'/>,<c:out value="${aBean.date}"/>)"/><c:out value="${status.index+1}"/>
	                </td>
	                <td align='center' class='num_font'><a><c:out value="${aBean.name}"/></a></td>
	                <td align='center' class='num_font'><a><c:out value="${aBean.idstyle}"/></a></td>
	                <td align='center' class='num_font'><a><c:out value="${aBean.idnumber}"/></a></td>
	                <td align='center' class='num_font'><a><c:out value="${aBean.date}"/></a></td>
		     	    <%-- <td align='center' class='num_font'><fmt:formatDate value="${aBean.date}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td> --%>
		            <td align='center' class='num_font'><a><c:out value="${aBean.org}"/></a></td>
		            <td align='center' class='num_font'><a><c:out value="${aBean.major}"/></a></td>
		            <td align='center' class='num_font'><a><c:out value="${aBean.phone}"/></a></td>
		            <%-- <td align='center' class='num_font'><a><c:out value="${aBean.expertstyle}"/></a></td> --%>
		            <td align='center' class='num_font'><a><c:out value="${aBean.jtitle}"/></a></td>
	             </tr>
	        	</c:forEach>        
	    	</table>
	    	<c:if test="${experts!=null}">
		    	<form action="maintainexpert.action" name="chaForm" style="margin-top: 6px;">
		    		<s:hidden name="expert.name"></s:hidden>
		    		<s:hidden name="expert.org"></s:hidden>
		    		<s:hidden name="fexpert.committee"></s:hidden>
		    		<s:hidden name="style"></s:hidden>
					<elile:navigateBar navigateform="navigateform" actionName="maintainexpert.action" formName="chaForm"/>
				</form>
			</c:if>
	       </td>
	      </tr>
	    </table>
	   </div>
	  </td>
	 </tr>
	</table>
 
</body>
</html>
              
       

