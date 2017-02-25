<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>

<html>
<head>
<title>准考证打印</title> 
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script language="JavaScript" type="text/JavaScript" >

//全选
function selectall()
{
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("checkid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}
function printCard(v){
	window.open ("<%=request.getContextPath() %>/authority/printVies.jsp?id="+v, "newwindow", "height=500, width=1000, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">准考证打印</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
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
                              <td align="left" valign="middle" class="header7"></td>
                              <td class="header8">查询条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
		<form name="addform" action="<%=request.getContextPath() %>/servlet/PrintCardServlet" method="post">
		<input type=hidden id="actionType" name="actionType" value="">
		<input type=hidden id="myaction" name="myaction" value="">
		 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
           <tr>
	            <td width="13%" height="28" align="right">准考证标题：</td>
	            <td width="18%" align="left"><input type="text" id="zkzbt" name="zkzbt" style="width:150px"></td>
                <td width="13%" align="right">批次名称：</td>
	            <td width="18%"><input type="text" id="pcmc" name="pcmc" style="width:150px"></td>
	            <td width="13%" align="right">考点名称：</td>
	            <td width="18%">
	            	<input type="text" id="kdmc" name="kdmc" style="width:150px">
                </td>
            </tr>
  			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;" >
               <tr>
                 <td align="center">
                 	<input name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查询" /> 
                 </td>
               </tr>
               <tr height="8"></tr>
             </table>
		   </table>
		 </form>
 
		<form action="<%=request.getContextPath() %>/servlet/PrintCardServlet?myaction=checkPrintList"  name="aForm" method="POST">
		 <input type=hidden name="actionType" value="query">
		 <input type="hidden" name="subject"  value="<c:out value="${subject}"/>"/>
		 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">
                        	<font style="float: left;">查询结果</font>
                        </td>
                      </tr>
                  </table></td>
                </tr>
            </table>
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
         <tr class="title_font"> 
         		<td width="5%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()" /></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td width="15%" align="center" bgcolor="#C7E2F8"><span class="out">批次名称</span></td>
                <td width="15%" align="center" bgcolor="#C7E2F8"><span class="out">考试名称</span></td>
                <td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">考点名称</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">人数</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
          </tr>
        <c:forEach var="aBean" items="${printList}" varStatus="status">
          <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
          		<td align='center' class='num_font'>
                	<input type="checkbox"  id="checkbox" name="checkid" value="<c:out value="${aBean[0]}"/>">
                </td>
                <td align='center' class='num_font'>
	            	<c:out value="${status.index+1}" />                     
	            </td>
	     	    <td class='num_font alignleft'><a href="#" style="display:block;width:200px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean[1]}"/>" >
	           		<c:out value="${aBean[1]}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:200;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean[2]}"/>">
	            	<c:out value="${aBean[2]}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean[3]}"/>">
	            	<c:out value="${aBean[3]}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean[4]}"/>">
	            	<c:out value="${aBean[4]}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<input type="button" class="submit_2" onClick="printCard(<c:out value='${aBean[0]}'/>)"  value="打印"/>
	            </td>
           </tr>                   
         </c:forEach>
	  </table>
	  <!--   分页       -->
	  <c:if test="${printList!= null}">
		<elile:navigateBar navigateform="navigateform" actionName="PrintCardServlet?myaction=checkPrintList" formName="aForm"/>
	  </c:if>
  	    </td>
      </tr>
    </table>
   </form>
<script language="javascript" type="text/JavaScript">
function doQuery() {
	$("#actionType").val("query");
	$("#myaction").val("checkPrintList");
  	document.addform.submit();
}
</script>
</body>
</html>

