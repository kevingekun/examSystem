<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@page import="com.wondersgroup.falcon.question.beans.*"%>

<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
EBusinesstypeService  eBusinesstypeService =new EBusinesstypeService(); 
EImportanceService eImportanceService =new EImportanceService();
EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();
List businesstypelist=eBusinesstypeService.findEBusinesstypeAll();
List importiontypelist=eImportanceService.findEImportanceAll();
List questiontypelist=eQuestiontypeService.findEQuestiontypeAll();
List paperlist=eQuestiontypeService.findEPaperAll();
request.setAttribute("businesstypelist",businesstypelist);
request.setAttribute("importiontypelist",importiontypelist);
request.setAttribute("questiontypelist",questiontypelist);
request.setAttribute("paperlist",paperlist);

ProfessionBean professionBean = new ProfessionBean();//工种
List<Tjobsubject> professions = professionBean.getDistinctProfessions();
request.setAttribute("professions",professions);
%>
<html>
<head>
<title>审核未通过试题</title> 
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script language="JavaScript" type="text/JavaScript" >

function modify(stid){
	
	document.aForm.action="QuestionServlet?myaction=modifyload&stid="+stid;
	//document.aForm.myaction.value="modifyload_again";
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
    	document.aForm.action="QuestionServlet?myaction=del";
		//document.aForm.myaction.value="del";
		document.aForm.submit();
    }else{
    	return false;
    }
	
}
function turnToExam(){
	var row=0;
	var t=window.document.getElementsByName('deleteid');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要转化的试题！");
		return false;
	}
    var tt=confirm("确定要转化成考试题吗？");  //确认转化
    if(tt){
    	document.aForm.action="QuestionServlet?myaction=turn";
		//document.aForm.myaction.value="turn";
		document.aForm.submit();
    }else{
    	return false;
    }
	
}

function turnback(){
	var row=0;
	var t=window.document.getElementsByName('deleteid');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要转化的试题！");
		return false;
	}
    var tt=confirm("确定要转化成非考试题吗？");  //确认转化
    if(tt){
    	document.aForm.action="QuestionServlet?myaction=turnback";
		//document.aForm.myaction.value="turnback";
		document.aForm.submit();
    }else{
    	return false;
    }	
}
function turnToStage(){
	var row=0;
	var t=window.document.getElementsByName('deleteid');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要转化的试题！");
		return false;
	}
    var tt=confirm("确定要转化成阶段性试题吗？");  //确认转化
    if(tt){
    	document.aForm.action="QuestionServlet?myaction=turnjd";
		//document.aForm.myaction.value="turnjd";
		document.aForm.submit();
    }else{
    	return false;
    }
}
function checkDj(v){
	var value = v.value;
	var dj = document.getElementById("gzdj");
	var array = new Array();
	dj.length = 1;
	$.ajax({
		type:'post',
		async : false,
		url:'findDjByGzid.action?gzid='+value,
		success:function(result){
			var data = eval(result);
			$.each(data, function(i, n) {
				document.getElementById("gzdj").options
				.add(new Option(data[i][1],data[i][1]));
				array.push(data[i][1]);
			});
			document.getElementById("dqdj").value=array;
		},
		error:function(){
			alert("error");
		}
	});
}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">审核未通过试题</td>
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
<form name="queryform" action="<%=request.getContextPath() %>/QuestionServlet" method="post">
<input type=hidden name="actionType" value="query">
 <input type=hidden name="myaction" value="unpasslist">
 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
           <tr>
	            <td width="13%" height="28" align="right">题目：</td>
	            <td width="18%" align="left"><input type="text" name="subject" value="<c:out value="${subject }"/>" style="width:182px"></td>
	            <td width="13%" align="right">试题类型：</td>
	            <td width="18%"><select name="questiontype" style="width:132px">
              		<option value="0">全部</option>
             		<c:forEach var="aBean" items="${questiontypelist}">
						<c:choose>
							<c:when test="${questiontype==aBean.id}">
								<option value="<c:out value="${aBean.id}"/>" selected="selected"><c:out value="${aBean.name}"/>
						    </c:when>
						    <c:otherwise>
								<option value="<c:out value="${aBean.id}"/>" ><c:out value="${aBean.name}"/>
						    </c:otherwise>
					    </c:choose>
				    </c:forEach>
                    </select></td>
                <td width="13%" height="28" align="right">难易度：</td>
	            <td width="18%" align="left"><select name="difficulty" style="width:132px">
              		<option value="0">全部</option>
					<c:forEach var="aBean" items="${importiontypelist}">
							<c:choose>
								<c:when test="${importance==aBean.id}">
									<option value="<c:out value="${aBean.id}"/>" selected="selected"><c:out value="${aBean.name}"/>
							    </c:when>
							    <c:otherwise>
									<option value="<c:out value="${aBean.id}"/>" ><c:out value="${aBean.name}"/>
							    </c:otherwise>
						    </c:choose>
				    	</c:forEach>
            	    </select></td>
            </tr>
          <tr>
	            <td width="13%" align="right">工种：</td>
	            <td width="18%"><select name="gzid" style="width:182px" onchange="checkDj(this)">
	                <option value="">全部</option>
	                <c:forEach var="aBean" items="${professions}">
							<c:choose>
								<c:when test="${gzid==aBean.id_job}">
									<option value="<c:out value="${aBean.id_job}"/>" selected="selected"><c:out value="${aBean.jobname}"/>
							    </c:when>
							    <c:otherwise>
									<option value="<c:out value="${aBean.id_job}"/>" ><c:out value="${aBean.jobname}"/>
							    </c:otherwise>
						    </c:choose>
				    	</c:forEach>
                    </select>
                </td>
                <td width="13%" align="right">等级：</td>
	            <td width="18%">
	            	<input id="dqdj" name="dqdj" value="<c:out value="${dqdj}"/>" style="display: none;" />
	            	<select id="gzdj" name="gzdj" style="width:132px">
		                <option value="">全部</option>
		                <c:if test="${gzdj!=''}">
		                	<c:forEach var="aBean" items="${dqdj}">
		                		<c:choose>
		                			<c:when test="${gzdj==aBean }">
		                				<option value="<c:out value="${aBean}"/>" selected="selected"><c:out value="${aBean}"/>
		                			</c:when>
		                			<c:otherwise>
		                				<option value="<c:out value="${aBean}"/>" ><c:out value="${aBean}"/>
		                			</c:otherwise>
		                		</c:choose>
		                	</c:forEach>
		                	<%-- <option value="<c:out value="${gzdj}"/>" selected="selected"><c:out value="${gzdj}"/> --%>
		                </c:if>
                    </select>
                </td>
	            
	            <td width="13%" align="right">录入时间：</td>
	            <td width="18%" ><input type="text"  id="recorddatebegin" name="recorddatebegin" value="<c:out value="${recorddatebegin}"></c:out>" onclick="WdatePicker()" style="width:70px"/>至
            	<input type="text" class="Wdate" id="recorddateend" name="recorddateend" value="<c:out value="${recorddateend}"></c:out>" onclick="WdatePicker()" style="width:78px"/></td>
            </tr>
            <tr>
	            
            </tr> 
  			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;" >
               <tr>
                 <td align="center"><input name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查询" /> 
                 </td>
               </tr>
               <tr>
               	<td height="8"></td>
               </tr>
             </table>
   </table>          	        
 </form>
 
<form action="<%=request.getContextPath() %>/QuestionServlet?myaction=unpasslist"  name="aForm" method="POST">
 <input type=hidden name="actionType" value="query">
<!-- <input type=hidden name="myaction" value="unpasslist">-->
 
 <input type="hidden" name="subject"  value="<c:out value="${subject}"/>"/>
 <input type="hidden" name="documentnum"  value="<c:out value="${documentnum}"/>"/>
 <input type="hidden" name="examsign"  value="<c:out value="${examsign}"/>"/>
 
 <input type="hidden" name="recorddatebegin"  value='<fmt:formatDate value='${recorddatebegin}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="recorddateend"  value='<fmt:formatDate value='${recorddateend}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="modifiydatebegin"  value='<fmt:formatDate value='${modifiydatebegin}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="modifiydateend"  value='<fmt:formatDate value='${modifiydateend}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>'/>
 <input type="hidden" name="professions"  value="<c:out value="${professions}"/>"/>
 <input type="hidden" name="difficulty"  value="<c:out value="${difficulty}"/>"/>
 <input type="hidden" name="questiontype"  value="<c:out value="${questiontype}"/>"/>
 <input type="hidden" name="Paperid"  value="<c:out value="${Paperid}"/>"/>
 <input type="hidden" name="gzid"  value="<c:out value="${gzid}"/>"/>
 <input type="hidden" name="gzdj"  value="<c:out value="${gzdj}"/>"/>

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">查询结果
                        </td>
                      </tr>
                  </table></td>
                </tr>
            </table>
	  <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
         <tr class="title_font">
                <td width="30"  align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td align="center" bgcolor="#C7E2F8"><span class="out">题目</span></td>
                <td align="100" bgcolor="#C7E2F8"><span class="out">试题出处</span></td>
                <td width="60"  align="center" bgcolor="#C7E2F8"><span class="out">试题类型</span></td>
                <td width="100"  align="center" bgcolor="#C7E2F8"><span class="out">工种</span></td>
                <td width="40" align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td>
                <td width="40" align="center" bgcolor="#C7E2F8"><span class="out">分值</span></td>
                <td width="40"  align="center" bgcolor="#C7E2F8"><span class="out">难易度</span></td>
                <td width="60" align="center" bgcolor="#C7E2F8"><span class="out">重要程度</span></td>
                <td width="60"  align="center" bgcolor="#C7E2F8"><span class="out">录入时间</span></td>
                <td width="60"  align="center" bgcolor="#C7E2F8"><span class="out">出题专家</span></td>
          </tr>
        <c:forEach var="aBean" items="${list}" varStatus="status">
          <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'>
	            <c:out value="${status.index+1}" />                     
	            </td>
	     	   	<td class='num_font alignleft'><a href="#" onClick='modify(<c:out value="${aBean.id}"/>);' >
	            	<%-- <c:set value="${aBean[2]}" target="${aBean}" property="shortStTg"/><!-- shortStTg --> --%>
	           		<c:out value="${aBean.tg}"/></a>
	            </td>
	            <td align='center' class='num_font'><c:out value="${aBean.reference}"/></td>
	            <td align='center' class='num_font'><c:out value="${aBean.questiontype}"/></td>
	            <td align='center' class='num_font'><c:out value="${aBean.profession}"/></td>
                <td align='center' class='num_font'><c:out value="${aBean.grade}"/></td>
                <td align='center' class='num_font'><c:out value="${aBean.stFz}"/></td>
	            <td align='center' class='num_font'><c:out value="${aBean.difficulty}"/></td>
                <td align='center' class='num_font'><c:out value="${aBean.importence}"/></td>
	           	<td align='center' class='num_font'>
	            	<fmt:formatDate value="${aBean.lrsj}" type="date" timeStyle="default"/>
	            </td>
	             <td align='center' class='num_font'>
	             	<c:out value="${aBean.expert}"/>
	             </td>
           </tr>                      
         </c:forEach>
	  </table>



  <!--   分页       -->
  <c:if test="${list!= null}">
	<elile:navigateBar navigateform="navigateform" actionName="QuestionServlet?myaction=unpasslist" formName="aForm"/>
  </c:if>
  	    </td>
      </tr>
    </table>
   </form>
  <!-- <button class="BigButton" onclick="del();">删除</button>-->
  <script language="javascript" type="text/JavaScript">
function doQuery() {

  document.queryform.submit();
}


//查看建议详情
function viewpam(ST_ID){
	window.location.href="findAdvice.action?questionId="+ST_ID;
}
function selectall()
{
    var length = document.aForm.all["deleteid"].length;
    document.aForm.all1.checked = document.aForm.all1.checked|0;
    if (length == 0 ){
          return;
    }
    if (length ==1 )
    {
       document.aForm.deleteid.checked=document.aForm.all1.checked ;
    }
    if (length>1)
    {
      for (var i = 0; i < length; i++)
       {
            document.aForm.deleteid[i].checked=document.aForm.all1.checked;  
	        
       }
    }
}

</script>
</body>
</html>

