<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@page import="com.wondersgroup.falcon.Util.*,com.wondersgroup.falcon.question.beans.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="elile.tld" prefix="elile"%>

<%
EBusinesstypeService  eBusinesstypeService =new EBusinesstypeService(); 
EImportanceService eImportanceService =new EImportanceService();
EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();
List businesstypelist=eBusinesstypeService.findEBusinesstypeAll();
List importiontypelist=eImportanceService.findEImportanceAll();
List questiontypelist=eQuestiontypeService.findEQuestiontypeAll();
request.setAttribute("businesstypelist",businesstypelist);
request.setAttribute("importiontypelist",importiontypelist);
request.setAttribute("questiontypelist",questiontypelist);
%>
<html> 
<head>
<title>来电信息查询</title>
<link href="../css/all.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../js/date.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" >
function modify(stid){
	
	document.aForm.action="QuestionServlet?stid="+stid;
	document.aForm.myaction.value="modifyload";
	document.aForm.submit();
}
function del(){
	var row=0;
	var t=window.document.getElementsByName('deleteid');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要删除的记录！");
		return false;
	}
    var tt=confirm("确定要删除吗？");  //确认是否删除
    if(tt){
    	document.aForm.action="QuestionServlet";
		document.aForm.myaction.value="del";
		document.aForm.submit();
    }else{
    	return false;
    }
	
}
</script>
</head>
<body scroll="0" id="query" name="query">

<form name="queryform" action="<%=request.getContextPath() %>/QuestionServlet" method="post">
<input type=hidden name="actionType" value="query">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="3" colspan="3"></td>
  </tr>
  <tr> 
    <td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
    <td background="../images/min_02.gif"><div align="center"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="15">&nbsp;</td>
            <td><div align="center"><strong>试题信息查询</strong></div></td>
          </tr>
        </table>
      </div></td>
    <td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
  </tr>
<tr>
    <td height="5" colspan="3"></td>
</tr>
			  
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="5"><img src="../images/k_01.gif" width="5" height="4"></td>
    <td background="../images/k_02.gif"></td>
    <td width="6"><img src="../images/k_03.gif" width="6" height="4"></td>
  </tr>
  <tr>
    <td background="../images/k_04.gif"></td>
      <td height="50" bgcolor="#FFFFFF">
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td>题目 : &nbsp;&nbsp;<input type="text" name="subject" value=""></td>
            <td>
            	业务类型 : &nbsp;&nbsp;
            	<select name="businesstype">
	                <option value="0" selected="selected">全部</option>
	                <c:forEach var="aBean" items="${businesstypelist}">
	                <option value="<c:out value="${aBean.id}"/>"><c:out value="${aBean.name}"/>
	                </c:forEach>
                </select>
            </td>
            <td>文号:&nbsp;&nbsp;<input type="text" name="documentnum" value=""/></td>
            <td>
              	难易度:&nbsp;&nbsp;
              	<select name="importance">
              		<option value="0" selected="selected">全部</option>
					<c:forEach var="aBean" items="${importiontypelist}">
                	<option value="<c:out value="${aBean.id}"/>"><c:out value="${aBean.name}"/>
                	</c:forEach>
            	</select>
			</td>
			<td>
				考试题:<input type=radio name="examsign" value="1" checked>是<input type=radio  name="examsign" value="0">否
			</td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td >试题类型:
            	<select name="questiontype">
              		<option value="0" selected="selected">全部</option>
             		<c:forEach var="aBean" items="${questiontypelist}">
                	<option value="<c:out value="${aBean.id}"/>"><c:out value="${aBean.name}"/>
                </c:forEach>
            </select>
            </td>
            <td>
            	录入时间:
            	<input type="text" class="Wdate" id="recorddatebegin" name="recorddatebegin" onclick="WdatePicker()"/>
            	至
            	<input type="text" class="Wdate" id="recorddateend" name="recorddateend" onclick="WdatePicker()"/>
            <td colspan="3">
            	修改时间:
            	<input type="text" class="Wdate" id="modifiydatebegin" name="modifiydatebegin" onclick="WdatePicker()"/>
            	<input type="text" class="Wdate" id="modifiydateend" name="modifiydateend" onclick="WdatePicker()"/></td>
			 </tr>
          <tr bgcolor="#FFFFFF">
            <td height="30" colspan="5" align="center"> <input name="button" type="button" class="SmallButton" onClick="javascript:doQuery();" value="查询" />            </td>
          </tr>
        </table>
      </td>
    <td background="../images/k_05.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../images/k_06.gif" width="5" height="4"></td>
    <td background="../images/k_07.gif"></td>
    <td><img src="../images/k_08.gif" width="6" height="4"></td>
  </tr>
</table>


</form>
<form action="<%=request.getContextPath() %>/QuestionServlet"  name="aForm" method="POST">
 <input type=hidden name="actionType" value="query">
 <input type=hidden name="myaction" value="">
 
 <input type="hidden" name="subject"  value="<c:out value="${subject}"/>"/>
 <input type="hidden" name="documentnum"  value="<c:out value="${documentnum}"/>"/>
 <input type="hidden" name="examsign"  value="<c:out value="${examsign}"/>"/>
 
 <input type="hidden" name="recorddatebegin"  value='<fmt:formatDate value='${recorddatebegin}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="recorddateend"  value='<fmt:formatDate value='${recorddateend}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="modifiydatebegin"  value='<fmt:formatDate value='${modifiydatebegin}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="modifiydateend"  value='<fmt:formatDate value='${modifiydateend}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="businesstype"  value="<c:out value="${businesstype}"/>"/>
 <input type="hidden" name="importance"  value="<c:out value="${importance}"/>"/>
 <input type="hidden" name="questiontype"  value="<c:out value="${questiontype}"/>"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="5" colspan="4"></td>
    </tr>
    <tr>
      <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
      <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
      <td width="98%" background="../images/k1_03.gif">试题查询列表</td>
      <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
    </tr>
    <tr>
      <td background="../images/k1_05.gif"></td>
      <td colspan="2">
	  	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C4E2FB">
          <tr>
            <td align="center"><a href="#" onClick="del();" ><font color=red>删除</font></a></td>
            <td align="center">序号</td>
            <td align="center">题目</td>
            <td align="center">业务类型</td>
            <td align="center">试题类型</td>
            <td align="center">难易度</td>
            <td align="center">试题出处</td>
			<td align="center">文号</td>
            <td align="center">录入时间</td>
			<td align="center">修改时间</td>
			<td align="center" width="80">建议次数</td>			
          </tr>    
          <c:forEach var="aBean" items="${list}" varStatus="status">
                 
          <tr bgcolor="#FFFFFF">
          	<td align="center"><input type="checkbox"  name="deleteid" value="<c:out value="${aBean.stId}"/>"></td>
            <td><c:out value="${status.index+1}" /></td>
            <td width="20%">
            <a href="#" onClick='modify(<c:out value="${aBean.stId}"/>);' >
            
            <c:set value="${aBean.stTg}" target="${aBean}" property="shortStTg"/>
            　<c:out value="${aBean.shortStTg}"/></a>            
     	   </td>
     	    <td><c:out value="${aBean.ebusinesstype.name}"/></td>
            <td><c:out value="${aBean.equestiontype.name}"/></td>
            <td><c:out value="${aBean.eimportance.name}"/></td>
            <td><c:out value="${aBean.stCc}"/></td>           	
        	<td><c:out value="${aBean.stWh}"/></td>
            <td><fmt:formatDate value="${aBean.stLrsj}" type="date" timeStyle="default"/></td>
			<td><fmt:formatDate value="${aBean.stXgsj}" type="date" timeStyle="default"/>
			</td>
			<td><c:out value="${aBean.stJyxgcs}"/></td>
          </tr>
          </c:forEach>
		 </table>
		  </td>
      <td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
    </tr>
    <tr>
      <td><img src="../images/k1_07.gif" width="2" height="5"></td>
      <td colspan="2" background="../images/k1_08.gif"> </td>
      <td><img src="../images/k1_09.gif"></td>
    </tr>


  <!-- 分页      -->
  <c:if test="${list!= null}">
	<elile:navigateBar navigateform="navigateform" actionName="QuestionServlet" formName="aForm"/>
  </c:if>
  
    </table>
   </form>

</body>
</html>

<script language="javascript" type="text/JavaScript">
function doQuery() {

  document.queryform.submit();
}
</script>

