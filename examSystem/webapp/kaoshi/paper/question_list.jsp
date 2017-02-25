<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.falcon.question.dao.*" %>
<%@page import="com.wondersgroup.falcon.question.model.*" %>
<%@page import="java.util.*,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*" %>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();//工种
List<Tjobsubject> professions = professionBean.getDistinctProfessions();
%>
<html>
<head>
<s:head theme="ajax" /> 
<base href="<%=basePath%>">
<title>试题信息列表</title>
<link href="css/all.css" rel="stylesheet" type="text/css">
<script type="text/JavaScript" src="js/date.js"></script>
<script type="text/JavaScript" src="js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<style>
<!--
.hh {
width:200px;
-->
</style>  
<script type="text/javascript">
<%-- var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) { /*ignore*/ }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
}

function retrieveBook(spnid,questionId) {
	if(bXmlHttpSupport) {
       var sUrl = '<%=request.getContextPath() %>/findNum.action?questionId='+questionId;
       var oRequest = new XMLHttpRequest();
       oRequest.onreadystatechange = function() {
       		if(oRequest.readyState == 4) {
       			var paperfenshu = eval('(' + oRequest.responseText + ')');
       			var spa=document.getElementById(spnid)
       			spa.innerHTML=paperfenshu.times;
       		}
       }
       oRequest.open('POST', sUrl);
       oRequest.send(null);
    }
} --%>
function selectall()
{
    var length = document.aForm.all["questionIds"].length;
    document.aForm.all1.checked = document.aForm.all1.checked|0;
    if (length == 0 ){
          return;
    }
    if (length ==1 )
    {
       document.aForm.questionIds.checked=document.aForm.all1.checked ;
    }
    if (length>1)
    {
      for (var i = 0; i < length; i++)
       {
            document.aForm.questionIds[i].checked=document.aForm.all1.checked;  
	        
       }
    }
}




	function jumpback(){
		window.location.href='<%=request.getContextPath() %>/paper/paper_list.jsp';
	}
	function jumpViewPaper(sjid){
		//window.location.href="previewPaperAction.action?paperid="+sjid;
		window.location.href="previewPaperActionUnpass.action?paperid="+sjid;
		
	}
	///修改前的判断方法
	function vilidateform11111(form){
		var paperzf='<c:out value="${paper.sjZf}"/>';
		var yxfs='<c:out value="${fenshu}"/>';
		var fencha=Number(paperzf)-Number(yxfs);
		
		var questionIds = window.document.getElementsByName("questionIds");
		
		var selete=0;
		var fenshu=false;
		var fenshusum=0;



		for(var i=0;i<questionIds.length;i++){
			if(questionIds[i].checked){
				var timu=questionIds[i].value;
				if(document.getElementById(timu+"fenshu").value.length==0){
					fenshu=true;
				}else{
					var timufenshu=Number(document.getElementById(timu+"fenshu").value);
					fenshusum+=timufenshu;
				}
				selete++;
				
			}
		}
		if(selete==0){
			alert("请选择题目！");
			return false;
		}
		
		if(fenshu){
			alert("选中的试题必须输入分数！");
			return false;
		}
		//alert("选择的分数："+fenshusum+";分差:"+fencha);
		if(fenshusum>fencha){
			if(!window.confirm("您选择后试题的总分将超过试卷总分")){
				return false;
			}else{
				return false;
			}
		}
		
		return true;
	}
	//修改后的
	function vilidateform(form){
		var paperzf='<c:out value="${paper.sjZf}"/>';
		var yxfs='<c:out value="${fenshu}"/>';
		var fencha=Number(paperzf)-Number(yxfs);
		
		var questionIds = window.document.getElementsByName("questionIds");
		
		if(document.getElementById("fenzhi").value.length==0){
				alert("请选择题目分值");
				return false;
		}
		else {
		var fenzhi = Number(document.aForm.fenzhi.value);
		}
		var selete=0;
		var fenshu=false;
		var fenshusum=0;

		for(var i=0;i<questionIds.length;i++){
			if(questionIds[i].checked){
					fenshusum+=fenzhi;
				selete++;	
			}
		}
		if(selete==0){
			alert("请选择题目！");
			return false;
		}
		//alert("选择的分数："+fenshusum+";分差:"+fencha);
		if(fenshusum>fencha){
			if(!window.confirm("您选择后试题的总分将超过试卷总分")){
				return false;
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	
	function aFormsubmit(){ 
		//var form=document.forms("aForm");
		var form=document.aForm;
		var re=vilidateform(form);
		if(re){
			form.submit();
		}
		//document.getElementById("aFormsubmit").click();
	}
	
function questionshow(questionid){
		window.open("<%=request.getContextPath() %>/questionDetailshow.action?questionId="+questionid,"试题详细信息",'height=400, width=800, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
</script>
<script type="text/javascript">
function gzChange(v){
	var value = v.value;
	var dj = document.getElementById("gzdj");
	dj.length = 1;
	$.ajax({
		type:'post',
		async : false,
		url:'findDjByGzid.action?gzid='+value,
		success:function(result){
			var data = eval(result);
			$.each(data, function(i, n) {
				document.getElementById("gzdj").options
				.add(new Option(data[i][1],
						data[i][1]));
			});
		},
		error:function(){
			alert("error");
		}
	});
}
</script>
</head>
<body>
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
            <td><div align="left">选择试题</div><div align="center"></div></td>
          </tr>
        </table>		
    </div></td>
    <td width="8"><img src="<%=request.getContextPath() %>/images/min_03.gif" width="8" height="32"></td>
  </tr>
<tr>
    <td height="5" colspan="3">    	
    </td>
</tr>
</table>
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td>试卷名称: <c:out value="${paper.sjMc}"/></td>
            <td>试卷总分: <c:out value="${paper.sjZf}"/>&nbsp;&nbsp;&nbsp;(已选试题的总分：<c:out value="${fenshu}"/>)</td>
            <td>答卷时限: <c:out value="${paper.sjDjsx}"/> 分钟</td>
            <td >合格分数线: <c:out value="${paper.sjBhgfs}"/></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>开考时间:
            	<fmt:formatDate value="${paper.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td >有效截止日期:
            	<fmt:formatDate value="${paper.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
</table>
<br>
<s:form name="selectQuestions" action="selectQuestionsAction" method="post" >
<s:hidden name="paperid"/>
<s:hidden name="typeId" />
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td width="20%">工种: 
	            <select id="gzid" name="gzid" style="width: 200px" onchange="gzChange(this)">
					<option value="">请选择</option>
						<%
							for (int i = 0; i < professions.size(); i++) {
									Tjobsubject tj = professions.get(i);
									out.println("<option value=" + tj.getId_job() + "");
												/* if(tj.getId_job().equals(request.getParameter("id_job"))){
													out.println(" selected='selected' ");
												} */
									out.println(">" + tj.getJobname() + "");
									out.println("</option>");
							}
						%>
				</select>
            </td>
           	<td width="20%">等级：
           		<select id="gzdj" name="gzdj" style="width:80px">
					<option value="">请选择</option>
				</select>
           	</td>
            <td width="20%">难易度：<s:select name="difficulty" list="eimportances"  listKey="id" listValue="name" headerKey="-1" headerValue="--全部--"></s:select> </td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td>&nbsp;</td>
				<td width="10%"><s:submit cssClass="BigButton" value="查询"></s:submit> </td>
				<td>&nbsp;</td>
          	</tr>
        </table>
</s:form>
<s:form action="addQuestionsAction" name="aForm" method="post" onsubmit="return vilidateform(this);">
<s:hidden name="paperid"/>
<s:hidden name="typeId" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="5" colspan="4"></td>
    </tr>
    <tr>
      <td width="3"><img src="<%=request.getContextPath() %>/images/k1_01.gif" width="3" height="30"></td>
      <td width="21" background="<%=request.getContextPath() %>/images/k1_03.gif"><div align="center"><img src="<%=request.getContextPath() %>/images/k1_02.gif" width="21" height="30"></div></td>
      <td width="98%" background="<%=request.getContextPath() %>/images/k1_03.gif">试题查询列表 （试题分数：<input type="text" maxlength="4" style="width:20px" onkeyup="if(isNaN(value))execCommand('undo')"  id="fenzhi" name="fenzhi" />） </td>
      <td width="4" valign="top"><img src="<%=request.getContextPath() %>/images/k1_04.gif" width="4" height="30"></td>
    </tr>
    <tr>
      <td background="<%=request.getContextPath() %>/images/k1_05.gif"></td>
      <td colspan="2">
	  	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C4E2FB">
          <tr>
            <td align="center" width="3%"><input name="all1" type="checkbox" onClick="selectall()"></td>
            <!--  <td align="center" width="5%">试题分数</td>-->
            <td align="center" width="14%">试题题目</td>
            <td align="center" width="10%">出处</td>
            <td align="center" width="10%">工种</td>
            <td align="center" width="10%">等级</td>
            <td align="center" width="10%">试题类型</td>
            <td align="center" width="5%">难易度</td>
            <td align="center" width="13%">录入时间</td>
           <!--  <td align="center" width="13%">修改时间</td>
            <td align="center" width="7%">出题次数</td>	 -->
		
          </tr>    
          
          <s:iterator value="questiongs" id="question" status="index">
          <tr bgcolor="#FFFFFF">
          	<td align="center">
          		<s:if test="#question.has!=0">
          			<input type="checkbox" disabled="true" id="questionIds" name="questionIds" value='<s:property value="%{#question.id}"/>'/>
          		</s:if>
          		<s:else>
          			<input type="checkbox" name="questionIds" id="questionIds" value='<s:property value="%{#question.id}"/>'/>
          		</s:else>
          	</td>
          	<!--  
          	<td align="center">
          		<s:if test="#question.has!=0">
          			<input type="text" maxlength="4" style="width:20px" onkeyup="if(isNaN(value))execCommand('undo')" disabled="true" name='<s:property value="%{#question.stId}"/>fenshu' />
          		</s:if>
          		<s:else>
          			<input type="text" maxlength="4" style="width:20px" onkeyup="if(isNaN(value))execCommand('undo')"  name='<s:property value="%{#question.stId}"/>fenshu' />
          		</s:else>
          	</td>
          	-->
            <td align="left"><a href="#" class="hh" onclick='questionshow(<s:property value="#question.id"/>);'s><s:property value="#question.tg"/></a></td>
            <td align="center"><s:property value="#question.reference"/></td>
            <td align="center"><s:property value="#question.profession"/></td>
            <td align="center"><s:property value="#question.grade"/></td>
            <td align="center"><s:property value="#question.questiontype"/></td>
            <td align="center"><s:property value="#question.difficulty"/></td>
     	    <td align="center"><s:date name="#question.lrsj" format="yyyy-MM-dd HH:mm"/></td>
            <%-- <td align="center"><s:date name="#question.stXgsj" format="yyyy-MM-dd HH:mm"/></td>
            <td align="center">f
            	<SPAN id='<s:property value="#question.stId"/>fenshuSpan'>
	            	<script type="text/javascript">retrieveBook('<s:property value="%{#question.stId}"/>fenshuSpan','<s:property value="#question.stId"/>');
	            	</script>
            	</SPAN>
            </td> --%>
          </tr>
          </s:iterator>
		 </table>
		  </td>
    </tr>
    </table>
</s:form>
    <s:form name="queryform1" method="post" action="selectQuestionsAction">
    	 <s:hidden  name="stMc" />
    	 <s:hidden name="busType"></s:hidden>
    	 <s:hidden name="paperid"/>
		 <s:hidden name="typeId" />
		 <s:hidden name="sjbegin" />
		 <s:hidden name="gzid" />
		 <s:hidden name="gzdj" />
		 <s:hidden name="difficulty" />
         <elile:navigateBar navigateform="navigateform" actionName="selectQuestionsAction.action" formName="queryform1"/>
    </s:form>
    <div align="center">
    	<s:submit cssClass="BigButton" value="提交" onclick="aFormsubmit();"></s:submit>
    	<button class="BigButton" onclick='jumpViewPaper(<s:property value="paperid"/>);'>返回</button>
    </div>
</body>
</html>

