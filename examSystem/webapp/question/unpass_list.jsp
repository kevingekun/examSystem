<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@page import="com.wondersgroup.falcon.question.beans.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>审核未通过试题</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<style type="text/css">
		p{
			margin:0 0 0;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">审核未通过试题</h3>
		</div>
		<div class="panel-body">
			<form class="form-inline" name="queryform" action="<%=request.getContextPath() %>/QuestionServlet" method="post">
				<input type=hidden name="actionType" value="query">
				<input type=hidden name="myaction" value="unpasslist">
				<div class="row">
					<div class="col-md-3 col-md-offset-1 form-group">
						<label for="subject">题目</label>
						<input class="form-control input-sm" type="text" id="subject" name="subject" value="<c:out value="${subject }"/>" style="width:200px;"/>
					</div>
					<div class="col-md-3 form-group">
						<label for="questiontype">试题类型</label>
						<select class="form-control input-sm style2" id="questiontype" name="questiontype" style="width:85px;">
		              		<option value="0" >全部</option>
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
		                 </select>
					</div>
					<div class="col-md-5 form-group">
						<label for="difficulty">难易程度</label> 
						<select class="form-control input-sm style3" id="difficulty" name="difficulty">
							<option value="0">全部</option>
							<c:forEach var="aBean" items="${importiontypelist}">
								<c:choose>
									<c:when test="${importance==aBean.id}">
										<option value="<c:out value="${aBean.id}"/>"
											selected="selected"><c:out value="${aBean.name}" />
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${aBean.id}"/>"><c:out
												value="${aBean.name}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row" style="margin-top:8px;">
					<div class="col-md-3 col-md-offset-1 form-group">
						<label for="gzid">工种</label>
						<select class="form-control input-sm style1" id="gzid" name="gzid" onchange="checkDj(this)" style="width:200px;">
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
					</div>
					<div class="col-md-3 form-group">
						<label for="gzdj">工种等级</label>
						<input id="dqdj" name="dqdj" value="<c:out value="${dqdj}"/>" style="display: none;" />
		            	<select class="form-control input-sm style2" id="gzdj" name="gzdj" style="width:85px;">
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
			                </c:if>
	                    </select>
					</div>
					<div class="col-md-5 form-group">
						<label for="recorddatebegin">录入时间</label>
						<input class="form-control input-sm" type="date" id="recorddatebegin" name="recorddatebegin" value="<c:out value="${recorddatebegin}"></c:out>" />至
	            		<input class="form-control input-sm" type="date" id="recorddateend" name="recorddateend" value="<c:out value="${recorddateend}"></c:out>" />
					</div>
				</div>
				<div class="row" style="margin-top:8px;">
					<div class="col-md-12" style="text-align:center">
						<button name="button" type="button" class="btn btn-primary btn-sm" onClick="javascript:doQuery();">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询
						</button>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed text-center" >
         <tr class="info"> 
         		<td width="5%"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()" /></td>
                <td width="5%"><span class="out">序号</span></td>
                <td width="10%"><span class="out">题目</span></td>
                <td width="8%"><span class="out">类型</span></td>
                <td width="10%"><span class="out">工种</span></td>
                <td width="8%"><span class="out">等级</span></td>
                <td width="8%"><span class="out">分值</span></td>
                <td width="10%"><span class="out">难易度</span></td>
                <td width="10%"><span class="out">重要程度</span></td>
                <td width="10%"><span class="out">录入时间</span></td>
          </tr>
        <c:forEach var="aBean" items="${list}" varStatus="status">
          <tr>
          		<td>
                	<input type="checkbox"  id="checkbox" name="checkid" value="<c:out value="${aBean.id}"/>">
                </td>
                <td>
	            	<c:out value="${status.index+1}" />                     
	            </td>
	     	    <td><a role="button" onClick='modify(<c:out value="${aBean.id}"/>);' style="display:block;width:200px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.tg}"/>" >
	           		<c:out value="${aBean.tg}"/></a>
	            </td>
	            <td><c:out value="${aBean.questiontype}"/></td>
	            <td>
	            	<p style="display:block;width:100px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.profession}"/>"><c:out value="${aBean.profession}"/></p>
	            </td>
                <td><c:out value="${aBean.grade}"/></td>
                <td><c:out value="${aBean.stFz}"/></td>
	            <td><c:out value="${aBean.difficulty}"/></td>
                <td><c:out value="${aBean.importence}"/></td>
	            <td>
	            	<fmt:formatDate value="${aBean.lrsj}" type="date" timeStyle="default"/>
	            </td>
           </tr>                      
         </c:forEach>
	  </table>
	  <c:if test="${list!= null}">
	  <form action="<%=request.getContextPath() %>/QuestionServlet?myaction=unpasslist"  name="aForm" method="POST">
			<input type=hidden name="actionType" value="query">
			<input type="hidden" name="subject" value="<c:out value="${subject}"/>" /> 
			<input type="hidden" name="documentnum" value="<c:out value="${documentnum}"/>" /> 
			<input type="hidden" name="examsign" value="<c:out value="${examsign}"/>" />
			<input type="hidden" name="recorddatebegin"
				value='<fmt:formatDate value='${recorddatebegin}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>' />
			<input type="hidden" name="recorddateend"
				value='<fmt:formatDate value='${recorddateend}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>' />
			<input type="hidden" name="modifiydatebegin"
				value='<fmt:formatDate value='${modifiydatebegin}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>' />
			<input type="hidden" name="modifiydateend"
				value='<fmt:formatDate value='${modifiydateend}' type="date" timeStyle="default" pattern="yyyy-MM-dd"/>' />
			<input type="hidden" name="professions" value="<c:out value="${professions}"/>" /> 
			<input type="hidden" name="difficulty" value="<c:out value="${difficulty}"/>" /> 
			<input type="hidden" name="questiontype" value="<c:out value="${questiontype}"/>" /> 
			<input type="hidden" name="Paperid" value="<c:out value="${Paperid}"/>" /> 
			<input type="hidden" name="gzid" value="<c:out value="${gzid}"/>" /> 
			<input type="hidden" name="gzdj" value="<c:out value="${gzdj}"/>" /> 
			<input type="hidden" name="dqdj" value="<c:out value="${dqdj}"/>" /> 
			
			<elile:navigateBar navigateform="navigateform" actionName="QuestionServlet?myaction=unpasslist" formName="aForm"/>
		</form>
	  </c:if>
	</div>
</div>
<script language="JavaScript" type="text/JavaScript" >
function modify(stid){
	document.aForm.action="QuestionServlet?myaction=modifyload&stid="+stid;
	document.aForm.submit();
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
function doQuery() {
  document.queryform.submit();
}
</script>
</body>
</html>

