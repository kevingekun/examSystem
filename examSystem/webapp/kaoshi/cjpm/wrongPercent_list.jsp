<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<html>
<head>
<title>试题信息列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>

<style>
<!--
.hh {
width:200px;
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap}
-->
</style>  
<script type="text/javascript">
	function jumpback(){
		// window.location.href='<%=request.getContextPath() %>/kaoshi/answer/px_answer_paper.jsp';
		window.location.href="answerpaperpmquery.action";
	   //被修改的地方	document.aForm.myaction.value="modifyload"; 
	}
	
	//导出excel
	function excel(paperid){
		window.location.href="wrongPercentExcelAction.action?paperid="+paperid;
	}
	
	function questionshow(questionid){
		window.open("<%=request.getContextPath() %>/questionDetailshow.action?questionId="+questionid,"试题详细信息",'height=400, width=800, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
	
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">所属试卷：<s:property value="epapers.sjMc"/></td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">结果</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
        <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试题题目</span></td>
               <%--  <td  align="center" bgcolor="#C7E2F8"><span class="out">工种</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td> --%>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试题类型</span></td>
                <%-- <td  align="center" bgcolor="#C7E2F8"><span class="out">难度系数</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">重要程度</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">出处</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">专家</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">录入时间</span></td> --%>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试题错误率</span></td>
             </tr>
          <s:iterator value="epaperquention" id="epaper" status="index">
            <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><a href="#" class="hh" style="display:block;width:900px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" 
                 title="<s:property value="#epaper.equestions.stTg"/>"><s:property value="#epaper.equestions.stTg"/></a>
                </td>
               <%--  <td>
                <s:property value="#epaper.equestions.ebusinesstype.name"/>          
	     	    </td>
	     	    <td align="center">
	            	<s:property value="#epaper.equestions.equestiontype.name"/>
	            </td> --%>
	     	    <td align='center' class='num_font'><s:property value="#epaper.equestions.equestiontype.name"/></td>
	            <%--<td align='center' class='num_font'><s:property value="#epaper.equestions.eimportance.id"/>
	             <s:if test="#epaper.equestions.eimportance.id==1" >容易</s:if>
	            <s:elseif test="#epaper.equestions.eimportance.id==2" >普通</s:elseif>
	            <s:else>难</s:else> --%>
	            <!-- <s:property value="#epaper.equestions.eimportance.name"/>
	            </td>
	            <td align='center' class='num_font'><s:property value="#epaper.equestions.bxType"/></td>
	            <td align='center' class='num_font'><s:property value="#epaper.equestions.stCc"/></td>
	            <td align='center' class='num_font'>
                <s:property value="#epaper.equestions.stSyryId"/>
               </td>
	            <td align="center">
	            	<s:date name="#epaper.equestions.stLrsj" format="yyyy-MM-dd"/>
	            </td>--> 
	            <td align='center' class='num_font'><s:property value="#epaper.wrong_percent"/>%</td>
            </tr>       
         </s:iterator>
	</table>
	 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                    <td align="center"><button class="submit_2" onclick="excel(<s:property value="paperId"/>);" >导出excel</button></td>
                </tr>
              </table>
    
    	   </td>
    </tr>
</table>
  

</table> 
</body>
<script type="text/javascript">

</script>
</html>

