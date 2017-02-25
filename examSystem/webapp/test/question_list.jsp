<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>详细信息</title>
<link href="css/all.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="js/date.js"></script>
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
<body>


  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="5" colspan="4"></td>
    </tr>
    <tr>
      <td width="3"><img src="images/k1_01.gif" width="3" height="30"></td>
      <td width="21" background="images/k1_03.gif"><div align="center"><img src="images/k1_02.gif" width="21" height="30"></div></td>
      <td width="98%" background="images/k1_03.gif">列表</td>
      <td width="4" valign="top"><img src="images/k1_04.gif" width="4" height="30"></td>
    </tr>
    <tr>
      <td background="images/k1_05.gif"></td>
      <td colspan="2">
	  	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C4E2FB">
          <tr>
            <td align="center">序号</td>
            <td align="center">id</td>		
          </tr>    
          <s:iterator value="result" id="kind">
                 
          <tr bgcolor="#FFFFFF">
          	<td align="center"></td>
            <td><s:property value="stId"></s:property></td>
            <td width="20%">
          </tr>
          </s:iterator>
		 </table>
		  </td>
      <td background="images/k1_06.gif"><img src="images/k1_06.gif" width="4" height="2"></td>
    </tr>
    <tr>
      <td><img src="images/k1_07.gif" width="2" height="5"></td>
      <td colspan="2" background="images/k1_08.gif"> </td>
      <td><img src="images/k1_09.gif"></td>
    </tr>


<s:property value="pager" escape="false"/>  
  
    </table>
</body>
</html>

