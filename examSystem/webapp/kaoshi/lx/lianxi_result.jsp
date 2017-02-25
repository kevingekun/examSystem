<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<s:head theme="ajax" /> 
<title>练习信息</title>
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
	function validateForm(form){
		var daan=form.daan;
		var boo=false;
		for(var i=0;i<daan.length;i++){
			if(daan[i].checked){
				boo= true;
				break;
			}
		}
		if(!boo){
			if(window.confirm("您没有填写答案！确定提交!")){
				return true;
			}else{
				return false;
			}
		}else{
			if(window.confirm("确定提交!")){
				return true;
			}else{
				return false;
			}
		}
	}
	
	function vidwtruedaan(){
		document.getElementById("bzda").style.display="block";
	}
</script>
</head>
<body class="nrbj">
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">我的练习</td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
  <td colspan="2" valign="top" ><div id="content1" class="borader">
       <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="lxtable">
          <tr>
            <td colspan="6" class="bbline">&nbsp;</td>
          </tr>
		<tr>
			<td>
				<div style="margin-top:5px;"><table width="100%">
		          <tr>
            <td width="12%">&nbsp;</td>
            <td align="right" width="12%">练习时间: </td>
            <td width="26%"><s:date name="eexercise.lxKssj" format="yyyy-MM-dd HH:mm"/>--<s:date name="eexercise.lxJssj" format="yyyy-MM-dd HH:mm"/></td>
           	<td align="right" width="14%">答题数量: </td>
            <td width="26%"><s:property value="eexercise.lxDtsl"/> </td>
            <td width="10%">&nbsp;</td>
          </tr>
          
          <tr>
          <td width="8%">&nbsp;</td>
            <td align="right">客观题数量: </td>
            <td><s:property value="eexercise.lxKgtsl"/></td>
           	<td align="right">客观题正确率: </td>
            <td><s:property value="eexercise.lxKgtzql"/>%</td>
            <td width="23%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td width="23%">&nbsp;</td>
          </tr>
				</table></div>
			</td>
		</tr>
        </table>
		</div>
      </td>
  </tr>
</table>
</body>
</html>

