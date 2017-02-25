<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>试卷复用</title>
<link href="<%=request.getContextPath() %>/css/all.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="javascript">	
function account(){

									
}
		function checkSubmit(){			
			if(document.all.sjMc.value==""){
				alert("请输入试卷名称！");
				return false;
			}
			if(document.all.sjZf.value==""){
				alert("请输入试卷总分！");
				return false;
			}
			if(document.all.sjDjsx.value==""){
				alert("请选择输入答卷时限！");
				return false;
			}
			if(document.all.sjBhgfs.value==""){
				alert("请输不及格分数线！");
				return false;
			}
			if(document.all.sjKksj.value==""){
				alert("请选择输入开始时间！");
				return false;
			}
			if(document.all.sjYxqjzsj.value==""){
				alert("请选择输入有效期截止日期！");
				return false;
			}
			return true;
		}	
</script>	
</head>
<body scroll="0" id="query" name="query">

<s:form name="myform" action="addPaperAction" method="post" onsubmit="return checkSubmit();">
<s:hidden name="paperid"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="3" colspan="3"></td>
  </tr>
  <tr> 
    <td width="8"><img src="<%=request.getContextPath() %>/images/min_01.gif" width="8" height="32"></td>
    <td background="<%=request.getContextPath() %>/images/min_02.gif"><div align="center"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="15">&nbsp;</td>
            <td><div align="center"><strong>试卷复制</strong></div></td>
          </tr>
        </table>
      </div></td>
    <td width="8"><img src="<%=request.getContextPath() %>/images/min_03.gif" width="8" height="32"></td>
  </tr>
<tr>
    <td height="5" colspan="3"></td>
</tr>
			  
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="5"><img src="<%=request.getContextPath() %>/images/k_01.gif" width="5" height="4"></td>
    <td background="<%=request.getContextPath() %>/images/k_02.gif"></td>
    <td width="6"><img src="<%=request.getContextPath() %>/images/k_03.gif" width="6" height="4"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath() %>/images/k_04.gif"></td>
      <td height="50" bgcolor="#FFFFFF">
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td>试卷名称 : </td>
            <td colspan="3"><s:textfield name="pager.sjMc" id="sjMc"/>
            <font color="#FF0000" >*</font></td>
            <td>试卷总分 : </td>
            <td colspan="3"><s:textfield name="pager.sjZf" id="sjZf" readonly="true"/>
            <font color="#FF0000" >*</font></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td width="10%">答卷时限: </td>
            <td width="35%" colspan="3"><s:textfield name="pager.sjDjsx" id="sjDjsx"/>
            <font color="#FF0000" >*</font>            分钟</td>
            <td width="10%" >不合格分数线: </td>
            <td width="35%" colspan="3" ><s:textfield name="pager.sjBhgfs" id="sjBhgfs"/>
            <font color="#FF0000" >*</font></td>
          </tr>
          <tr bgcolor="#FFFFFF">
			<td>试卷模式：</td>
			<td><input type=radio name="model" value="1" checked />开卷<input type=radio name="model" value="0" />闭卷</td>
            <td>开考时间: </td>
            <td colspan="2"> 
              <input type="text" style="width:150px" class="Wdate" name="sjKksj" id="sjKksj"  value="<fmt:formatDate value='${pager.sjKksj}'  type='date' timeStyle='default' pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>	
              
         	  <font color="#FF0000" >*</font> 
            </td>
            <td >有效截止日期:</td>
            <td colspan="2" >
            	<input type="text" style="width:150px" class="Wdate" name="sjYxqjzsj" id="sjYxqjzsj"  value="<fmt:formatDate value='${pager.sjYxqjzsj}'  type='date' timeStyle='default' pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>	
            </td>
          </tr>
          

          <tr bgcolor="#FFFFFF">
            <td height="30" colspan="8" align="center"> <s:submit cssClass="BigButton" value="复制试卷"/>        </td>
          </tr>
		  
		  
          <tr bgcolor="#FFFFFF">
            <td height="30" colspan="8" align="center">&nbsp;</td>
          </tr>
        </table>
      </td>
    <td background="<%=request.getContextPath() %>/images/k_05.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath() %>/images/k_06.gif" width="5" height="4"></td>
    <td background="<%=request.getContextPath() %>/images/k_07.gif"></td>
    <td><img src="<%=request.getContextPath() %>/images/k_08.gif" width="6" height="4"></td>
  </tr>
</table>


</s:form>


</body>
</html>
