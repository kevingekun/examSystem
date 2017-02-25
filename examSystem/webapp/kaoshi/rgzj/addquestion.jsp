<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<s:head theme="ajax" /> 
<title>试题信息列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>

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
		window.location.href='<%=request.getContextPath() %>/paper/paper_list.jsp';
	}
	function addquestion(){
		window.document.aForm.action="rgzjAction_addquestion.action";
		var questionids=window.document.getElementsByName("questionids");
		var num=0;
		if(questionids!=null){
			for(var i=0;i<questionids.length;i++){
				if(questionids[i].checked){
					num++;
				}
			}
		}
		if(num!=0){
			window.document.aForm.submit();
		}else{
			alert("请选择题目！");
		}
		
	}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">所属试卷：<s:property value="epaper.sjMc"/></td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
<s:form  action="to_addquestion" name="aForm" method="post">
<s:hidden name="paperid"/>
<<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">试卷名称：</td>
			          <td width="20%"><s:textfield name="stTg" /></td>
			          <td width="12%" align="right">试卷总分：</td>
			          <td width="20%"><s:textfield name="sjDjsx" maxlength="3" value="60" id="rgzjAction_addpaper_paper_sjDjsx" onkeyup="if(isNaN(value))execCommand('undo')"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">总分：</td>
			          <td width="20%"><s:textfield name="sjzf" maxlength="3" value="100" id="rgzjAction_addpaper_paper_sjzf" onkeyup="if(isNaN(value))execCommand('undo')"/></td>
			          <td width="12%" align="right">&nbsp;</td>
			          <td width="20%">&nbsp;</td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">开考时间：</td>
			          <td width="20%"><input type="text" style="width:150px" class="Wdate" name="sjKksj" id="sjKksj" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td>
			          <td width="12%" align="right">有效截止时间：</td>
			          <td width="20%"><input type="text" style="width:150px" class="Wdate" name="sjYxqjzsj" id="sjYxqjzsj" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td>试题名称: </td>
            <td><s:textfield name="stTg" /> </td>
            <td>试题类型 : </td>
            <td><s:select   name="stLx" list="equestiontypes" listKey="id"  listValue="name" headerKey="-1" headerValue="--全部--"/></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td nowrap>试题业务类型:</td>
            <td colspan="4">
            	<s:select name="stYwlx" list="equestionBuTypes" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--"/>
			</td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="30" colspan="4" align="center">
			<s:submit cssClass="SmallButton" value="查询"></s:submit>
			<button class="SmallButton" onclick="jumpback();">返回</button>
			           </td>
          </tr>
        </table>
        
</table>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="5" colspan="4"></td>
    </tr>
    <tr>
      <td width="3"><img src="<%=request.getContextPath() %>/images/k1_01.gif" width="3" height="30"></td>
      <td width="21" background="<%=request.getContextPath() %>/images/k1_03.gif"><div align="center"><img src="<%=request.getContextPath() %>/images/k1_02.gif" width="21" height="30"></div></td>
      <td width="98%" background="<%=request.getContextPath() %>/images/k1_03.gif">试题查询列表</td>
      <td width="4" valign="top"><img src="<%=request.getContextPath() %>/images/k1_04.gif" width="4" height="30"></td>
    </tr>
    <tr>
      <td background="<%=request.getContextPath() %>/images/k1_05.gif"></td>
      <td colspan="2">
	  	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C4E2FB">
          <tr>
            <td align="center" width="10%"><a href="#" onClick="del();" ><font color=red>选择</font></a></td>
            <td align="center" width="10%">试题题目</td>
            <td align="center" width="15%">业务类型</td>
            <td align="center" width="10%">试题类型</td>
            <td align="center" width="5%">重要性</td>
            <td align="center" width="10%">出处</td>
            <td align="center" width="10%">文号</td>
            <td align="center" width="15%">录入时间</td>
            <td align="center" width="15%">修改时间</td>		
		
          </tr>    
          <s:iterator value="equestions" id="equestion" status="index">
                 
          <tr bgcolor="#FFFFFF">
          	<td align="center"><input type="checkbox"  name="questionids" value='<s:property value="#equestion.stId"/>'></td>
            <td align="center"><a href="#" class="hh"><s:property value="#equestion.stTg"/></a></td>
            <td align="center"><s:property value="#equestion.ebusinesstype.name"/></td>
            <td align="center"><s:property value="#equestion.equestiontype.name"/></td>
            <td align="center"><s:property value="#equestion.eimportance.name"/></td>
            <td align="center"><s:property value="#equestion.stCc"/></td>
            <td align="center"><s:property value="#equestion.stWh"/></td>
     	    <td align="center"><s:date name="#equestion.stLrsj" format="yyyy-MM-dd HH:mm"/></td>
            <td align="center"><s:date name="#equestion.stXgsj" format="yyyy-MM-dd HH:mm"/></td>
            
          </tr>
          </s:iterator>
		 </table>
		  </td>
      <td background="<%=request.getContextPath() %>/images/k1_06.gif"><img src="<%=request.getContextPath() %>/images/k1_06.gif" width="4" height="2"></td>
    </tr>
    <tr>
      <td><img src="<%=request.getContextPath() %>/images/k1_07.gif" width="2" height="5"></td>
      <td colspan="2" background="<%=request.getContextPath() %>/images/k1_08.gif"> </td>
      <td><img src="<%=request.getContextPath() %>/images/k1_09.gif"></td>
    </tr>
    </table>
    <s:hidden name='ppp' value='%{ppp}'/>
    <div align="right"><s:property value="pager" escape="false"/> </div>
    <div align="center"><button onclick="addquestion();">提交</button></div>
</s:form>

</body>
</html>

