<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<title>详细信息</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<%response.setHeader("Pragma","cache");response.setHeader("Cache-Control","cache");response.setDateHeader("Expires",10);%>

<script language="JavaScript" type="text/JavaScript" >
function modify(stid){
	
	document.aForm.action="epaperquestion.action?sjid="+stid;
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


function doQuery() {

  document.aForm.submit();
}

function jumpViewPaper(sjid){
	window.location.href="previewPaperAction.action?paperid="+sjid;
}
//复制试卷
function copyPaper(sjid){
	window.location.href="copyPaperAction.action?paperid="+sjid;
}
//更改试卷状态
function changeState(sjid,paperState){
	window.location.href="changeState.action?paperid="+sjid+"&paperState="+paperState;
}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">我的试卷</td>
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
        <s:form action="myPaperAction"  name="aForm" method="POST">
               <tr>
                 <td height="8" colspan="7"></td>
               </tr>
                          <tr>
                            <td width="13%" height="28" align="right">试卷名称：</td>
                            <td width="18%" align="left"><s:textfield name="epaper.sjMc"></s:textfield></td>
                            <td width="13%" align="right">试卷状态：</td>
                            <td width="18%"><s:select name="epaper.sjZt" list="sjzt" listKey="key" listValue="value" headerKey="-1" headerValue="--全部--"/></td>
                            <td width="13%" align="right">开考时间：</td>
                            <td width="18%">
			        		<input type="text" value="<fmt:formatDate value='${epaper.sjKksj}' type='date' timeStyle='default'/>"  id="epaper.sjKksj" name="epaper.sjKksj" onclick="WdatePicker()" style="width:80px"/>
			        		至
			        		<input type="text" value="<fmt:formatDate value='${epaper.sjKksjEnd}' type='date' timeStyle='default'/>" class="Wdate" id="epaper.sjKksj" name="epaper.sjKksjEnd" onclick="WdatePicker()" style="width:80px"/>
			        		</td>      	
           </s:form>                 
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center"><input   name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查 询" />
                    </td>
                </tr>
              </table>
 
         </table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">试卷查询列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
                <td align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
				<td align="center" bgcolor="#C7E2F8"><span class="out">试卷类型</span></td>
                <td align="center" bgcolor="#C7E2F8"><span class="out">开考时间</span></td>
                <td align="center" bgcolor="#C7E2F8"><span class="out">有效截止日期</span></td>
                <td align="center" bgcolor="#C7E2F8"><span class="out">审阅状态</span></td>
             </tr>           
          <s:iterator value="mypapers" id="answerpaper" status="status">
          <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#status.index+1"/>
                </td>
                <td align='center' class='num_font'>
	            <s:url id="myPaperQuestionActionaction" action="myPaperQuestionAction.action">
            	<s:param name="myPaperId" value="#answerpaper.djId"/>
            	</s:url>
            	<s:a href="%{#myPaperQuestionActionaction}"><s:property value="#answerpaper.epapers.sjMc"/> </s:a>          
	     	    </td>
				<td align='center' class='num_font'>
				     <c:choose>
	                <c:when test="${answerpaper.epapers.paperType == '1'}"> 培训评估考试</c:when>
	                <c:when test="${answerpaper.epapers.paperType == '2'}">新进人员业务培训考试</c:when>
	                <c:when test="${answerpaper.epapers.paperType == '3'}">星级考试</c:when>
	                <c:when test="${answerpaper.epapers.paperType == '4'}">拨测题考试</c:when>
	              </c:choose> 
				</td>
	     	    <td align='center' class='num_font'><s:date name="#answerpaper.epapers.sjKksj" format="yyyy-MM-dd HH:mm"/></td>
	            <td align='center' class='num_font'><s:date name="#answerpaper.epapers.sjYxqjzsj" format="yyyy-MM-dd HH:mm"/></td>
	            <td align='center' class='num_font'>
                <s:if test="#answerpaper.djSyzt==0">
            		<font color="green">待审阅</font>
            	</s:if>
            	<s:elseif test="#answerpaper.djSyzt==1">
            		<font color="red">待二审</font>
            	</s:elseif>
            	<s:else>
            		<font color="orange">审阅结束</font>
            	</s:else>
                </td>
             </tr>  
          </s:iterator>
		 </table>
		  </td>
    </tr>
 
</table>
  <s:form name="mypaperActionform" method="post" action="myPaperAction">
  			<s:hidden name="epaper.sjMc"/>
    	 <s:hidden  name="epaper.sjZt" />
    	 <s:hidden name="epaper.sjKksj"></s:hidden>
         <elile:navigateBar navigateform="navigateform" actionName="myPaperAction.action" formName="mypaperActionform"/>
</s:form>
</td>
</tr>
</table>
</div>
</td>
</tr>
</table>
</body>
</html>

