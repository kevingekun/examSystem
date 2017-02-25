<%@page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
ProfessionBean professionBean = new ProfessionBean();
List<EPapers> p_list = professionBean.getUnUploadPapers();
%>
<html>
<head>
<title>成绩查询</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.1.3.min.js"></script>
<script language="JavaScript" type="text/JavaScript" >
function doQuery() {
  document.aForm.submit();
}
</script>
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">成绩查询</td>
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
                              <td  align="left" valign="middle" class="header7"></td>
                              <td  class="header8">查询条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
                      <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <s:form action="gradeSearch.action"  name="aForm" method="POST">
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                          <tr>
                            <td width="10%" height="28" align="right">试卷名称：</td>
                            <td width="10%" align="left">
                            	<input id="sjmc" name="epaper.sjMc" />
                            </td>
                            <td width="10%" align="right">考试日期：</td>
                            <td width="10%">
                            	<input type="text" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd"/>' 
                            		style="width:100px" class="Wdate" name="sjKksjbegin" id="sjKksjbegin" 
                            		onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
                            </td>
                            <td width="10%" height="28" align="right">身份证号：</td>
                            <td width="10%" align="left"><s:textfield name="eUser.username" /></td>
                            <td width="10%" height="28" align="right">考生姓名：</td>
                            <td width="10%" align="left"><s:textfield name="eUser.realname" /></td>
                            <td width="10%" height="28" align="right">批次号：</td>
                            <td width="10%" align="left"><s:textfield name="pcid" /></td>
                            <td>&nbsp;</td>
                          </tr>
                        </s:form>
                      </table>
                      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
		                <tr>
		                  <td align="center">
								<input name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查 询" />
		                  </td>
		                </tr>
		                <tr height="8"></tr>
		              </table>
                     </td>
                </tr>
              </table>
  
              
			  
			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle" class="header7"></td>
                        <td class="header8">
                        	<font style="float: left;">查询结果</font>
                        </td> 
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8">
                	<!-- <input id="checkAll" name="all1" type="checkbox" onClick="selectall()">&nbsp; --><span class="out">序号 </span>
                </td>
                <td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考试日期</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">姓名</span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
                <td width="6%" align="center" bgcolor="#C7E2F8"><span class="out">分数</span></td>
                <td width="6%" align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
             </tr>
        <c:forEach var="aBean" items="${list}" varStatus="status">
             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><c:out value="${status.index+1}"/></td>
                <td align='center' class='num_font'><c:out value="${aBean.sjMc}"/></td>
	     	    <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjKksj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
	            <td align='center' class='num_font'><c:out value="${aBean.realname }"></c:out></td>
	            <td align='center' class='num_font'><c:out value="${aBean.username }"></c:out></td>
	            <td align='center' class='num_font'><c:out value="${aBean.djZf}"/></td>
	            <td align='center' class='num_font'>
                <c:choose>
                <c:when test="${aBean.cheatflag == '0'}">
                  <font color="green">正常</font>
                </c:when>
                <c:when test="${aBean.cheatflag == '1'}">
                  <font color="red">作弊</font>
                </c:when>
                <c:when test="${aBean.cheatflag == '2'}">
                  <font color="orange">缺考</font>
                </c:when>
               </c:choose>
               </td>
             </tr>
        </c:forEach>
    </table> 
	<c:if test="${list!= null }">
              <!-- fenye -->
		  	<form action="gradeSearch.action" name="answerpaperpmqueryForm" method="post" style="margin-top: 6px;">
		  		<s:hidden name="epaper.sjMc"></s:hidden>
		  		<input type="hidden" name="sjKksjbegin" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
		  		<s:hidden name="eUser.username"></s:hidden>
		  		<s:hidden name="eUser.realname"></s:hidden>
		  		<s:hidden name="pcid"></s:hidden>
				<elile:navigateBar navigateform="navigateform" actionName="gradeSearch.action" formName="answerpaperpmqueryForm"/>
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
              
       

