<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.falcon.question.dao.EBusinesstypeDAO,java.util.List,com.wondersgroup.falcon.model.citizeninfo.HisNode,com.wondersgroup.falcon.model.citizeninfo.HisAttr,com.wondersgroup.falcon.question.model.EQuestiontype" %>
<%@page import="com.wondersgroup.falcon.Util.*,com.wondersgroup.falcon.question.beans.*,com.wondersgroup.falcon.model.citizeninfo.PaperType"%>
<%@page import="com.wondersgroup.falcon.question.model.EImportance"%>
<%@page import="java.util.*"%>
<html>
<head>
<%EBusinesstypeDAO ht=new EBusinesstypeDAO();
List<PaperType> paperTypeList=ht.findPaperType();//试卷类型
%>
<s:head theme="ajax" /> 
<title>人工组卷</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>

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
		var sttg=window.document.getElementById("rgzjAction_addpaper_paper_sjMc");
		var sjDjsx=window.document.getElementById("rgzjAction_addpaper_paper_sjDjsx");
		if(sttg.value.length==0){
			alert("试卷名称不能为空！");
			return false;
		}
		if(sjDjsx.value.length==0){
			alert("答卷时限不能为空！");
			return false;
		}
		if(form.sjzf.value.length==0){
			alert("请输入总分");
			form.sjzf.focus();
			return false;
		}
		if(form.sjKksj.value.length==0){
			alert("开始考试日期不能为空！");
			return false;
		}
		if(form.sjYxqjzsj.value.length==0){
			alert("有效截止日期不能为空！");
			return false;
		}
		if(form.paperType.value.length==0){
			alert("考试类型不能为空！");
			return false;
		}
		if(form.sjBhgfs.value.length==0){
			alert("合格分数不能为空！");
			return false;
		}
		if(form.paperType.value==4){
			if(form.toUserId.value.length==0){
				alert("播测题考试人不能为空！");
				return false;
			}
		}
		return true;
	}
	//判断日期格式
	function   IsDate(sm,mystring)   {  
      var   reg   =   /^(\d{4})-(\d{2})-(\d{2})$/;  
      var   str   =   mystring;  
      var   arr   =   reg.exec(str);  
      if   (str=="")   return   true;  
      if   (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){  
        alert("日期格式不正确，应为为yyyy-mm-dd格式!");  
        return   false;  
      }
        return   true;  
    }   
	
	
	function dataenable(){
	 	//dojo.widget.byId("rgzjAction_addpaper_paper_sjKksj").enabled=false;
		//dojo.widget.byId("rgzjAction_addpaper_paper_sjYxqjzsj").enabled=false;
		//dojo.widget.byId("rgzjAction_addpaper_paper_sjKksj_time").enabled=false;
		//dojo.widget.byId("rgzjAction_addpaper_paper_sjYxqjzsj_time").enabled=false;
		//document.getElementById("rgzjAction_addpaper_paper_sjKksj").childNodes(1).readonly="true";
	}
function onselectchange(id){
	var idvalue=id.value;
	 if(idvalue==""){
	 	document.getElementById('to_userid').style.display="none";
	 	document.getElementById('to_useridspan').style.display="none"
	 	alert("试卷类型不能为空");
	 	return false;
	 }else if(idvalue==4){
	  document.getElementById('to_useridshow').style.display=""
	  document.getElementById('to_useridspan').style.display=""
	 }else{
	 	document.getElementById('to_userid').value="";
	 	document.getElementById('to_useridshow').value="";
	 	document.getElementById('to_useridshow').style.display="none";
	 	document.getElementById('to_useridspan').style.display="none"
	 }
 }
 function getMembers(){
   var temp=new Date().getTime();
   var members = showModalDialog('message_receiver.jsp?temp='+temp,window,'dialogWidth:600px;dialogHeight:500px;');
   var value1 = "";
   if(members!=null){
		document.getElementById('to_userid').value=members.id;
		document.getElementById('to_useridshow').value=members.value;
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
        <td class="header2">手工组卷</td>
        <td class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
		<s:form action="rgzjAction_addpaper" method="post" onsubmit="return validateForm(this);">
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">试卷名称：</td>
			          <td width="20%"><s:textfield name="sjMc"  maxlength="50" id="rgzjAction_addpaper_paper_sjMc"/></td>
			          <td width="12%" align="right">答卷时限：</td>
			          <td width="20%"><s:textfield name="sjDjsx" maxlength="3" value="60" id="rgzjAction_addpaper_paper_sjDjsx" onkeyup="if(isNaN(value))execCommand('undo')"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">总分：</td>
			          <td width="20%"><s:textfield name="sjzf" maxlength="3" value="100" id="rgzjAction_addpaper_paper_sjzf" onkeyup="if(isNaN(value))execCommand('undo')"/></td>
			          <td width="12%" align="right">立即出分：</td>
			          <td width="20%">
							<input type=radio name="sjLjcf" value="1" checked />是<input type=radio name="sjLjcf" value="0" checked/>否
					</td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">考试类型：</td>
			          <td width="20%">
							<select id="paper_Type" name="paperType" style="width: 130px" onchange="onselectchange(this);">
								<option value=1 selected>鉴定类考试</option>
								<option value=2>其他类考试</option>
							</select>
					  </td>
			          <td width="15%" height="26" align="right">合格分数：</td>
			          <td width="20%"><s:textfield name="sjBhgfs" id="rgzjAction_addpaper_paper_sjBhgfs"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">开考时间：</td>
			          <td width="20%"><input type="text" style="width:130px" class="Wdate" name="sjKksj" id="sjKksj" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td>
			          <td width="12%" align="right">有效截止时间：</td>
			          <td width="20%"><input type="text" style="width:130px" class="Wdate" name="sjYxqjzsj" id="sjYxqjzsj" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>          			
                 <tr class="row_height">
                   <td align="center" valign="bottom" colspan="6">
                     	<input name="add" type=submit class="submit_2" value="提 交"/>			
                   </td>
                 </tr>
    		</table>	
		</s:form>
	</td>
  </tr>
</table>
</body>
</html>

