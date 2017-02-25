<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>查询排序的试卷</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/JavaScript" >

function modify(stid){	
	document.aForm.action="ecjpmquestion.action?sjid="+stid;
	//被修改的地方	document.aForm.myaction.value="modifyload";
	
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
function viewpam(paperid){
	window.location.href="cjpmAction.action?paperid="+paperid;
}
//查看错误率
function viewRight(paperid){
	window.location.href="wrongPercent.action?paperId="+paperid;
}

//全体成绩报表
function allscoreCount(){
  var url="kaoshi/answer/allscore.jsp";
 window.location.href=url;
}

//未参加考试列表
function unpartexam(){
 var row =0;
    var paperid="";
	var values = document.getElementsByName("deleteid");
	for (var i = 0; i < values.length; i++){
       	if(values[i].checked == true){
       		paperid+=values[i].value+",";
       	   row++;
       	}
    }   
    if(row>0)
    {
    	paperid=paperid.substring(0,paperid.length-1);
		var url="nopartinexam.action?paperid="+paperid;
	   	window.location.href=url;
		return false;
    }else{
    	alert("您还没有选择");
    	return false;
    }
}

//组间成绩排名统计报表
function groupbwCount(){
 var url="kaoshi/answer/groupscore.jsp";
 window.location.href=url;
}
//checkbox复选
function zhpm()
{
    var row =0;
    var paperid="";
	var values = document.getElementsByName("deleteid");
	for (var i = 0; i < values.length; i++){
       	if(values[i].checked == true){
       		paperid+=values[i].value+",";
       	   row++;
       	}
    }   
    if(row>0)
    {
    	paperid=paperid.substring(0,paperid.length-1);
		var url="selectZhuHeAction.action?paperid="+paperid;
	   	window.location.href=url;
		return false;
    }else{
    	alert("您还没有选择");
    	return false;
    }
}
//全选
function selectall()
{
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("deleteid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}

</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">查询需要排名的试卷</td>
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
                        <s:form action="answerpaperpmquery.action"  name="aForm" method="POST">
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                          <tr>
                            <td width="13%" height="28" align="right">试卷名称：</td>
                            <td width="18%" align="left"><s:textfield name="epaper.sjMc" /></td>
                            <td width="13%" align="right">开考时间：</td>
                            <td width="18%">
                            	<input type="text" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd"/>' 
                            		style="width:150px" class="Wdate" name="sjKksjbegin" id="sjKksjbegin" 
                            		onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
                            </td>
                            <td width="13%" align="right">组卷时间：</td>
                            <td width="18%">
                            	<input type="text" value='<s:date name="sjZjsjbegin" format="yyyy-MM-dd"/>'
                            		style="width:150px" class="Wdate" name="sjZjsjbegin" id="sjZjsjbegin" 
                            		onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
                            </td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                          <tr>
                  			<td align="center" colspan="7">
								<input  name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查 询" />
                    		</td>
                		  </tr>
                		  <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                        </s:form>
                      </table></td>
                </tr>
                <tr>
                
                </tr>
              </table>
			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle" class="header7"></td>
                        <td class="header8">查询结果<!-- &nbsp;&nbsp;<a href="#" onClick="zhpm();" ><font color=red>组合排名</font></a>&nbsp;&nbsp;&nbsp; --></td>
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()">&nbsp;<span class="out">序号 </span></td>
                <td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">开考日期</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">有效截止日期</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">组卷时间</span></td>
                <td width="8%" align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
                <td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr>
        <c:forEach var="aBean" items="${epapers}" varStatus="status">
             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><input type="checkbox"  id="checkbox"  name="deleteid" value="<c:out value='${aBean.sjId}'/>"/>&nbsp;<c:out value="${status.index+1}"/>
                </td>
                <td align='center' class='num_font'>
	            <a href="#" onClick='modify(<c:out value="${aBean.sjId}"/>);'>            
                <c:set value="${aBean.sjMc}" target="${aBean}" property="shortSjMc"/>
                　<c:out value="${aBean.sjMc}"/></a>            
	     	    </td>
	     	    <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjKksj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
	            <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjYxqjzsj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
	            <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjZjsj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
	            <td align='center' class='num_font'>
                <c:choose>
                <c:when test="${aBean.sjZt == '0'}">
                  <font color="green">未考试</font>
                </c:when>
                <c:when test="${aBean.sjZt == '1'}">
                  <font color="red">考试中</font>
                </c:when>
                <c:when test="${aBean.sjZt == '2'}">
                  <font color="orange">已结束</font>
                </c:when>
               </c:choose>
               </td>
	            <td align="center">
	            	<a href="#" onclick='viewpam(<c:out value="${ aBean.sjId}"/>);'>&lt;查看试卷排名&gt;</a>
	            	&nbsp;&nbsp;
	            	<a href="#" onclick='viewRight(<c:out value="${aBean.sjId}"/>);'>&lt;查看错误率&gt;</a>
	            </td>
             </tr>
        </c:forEach>        
    </table> 
	<c:if test="${epapers!= null }">
              <!-- fenye -->
  	<form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post" style="margin-top: 6px;">
  		<s:hidden name="epaper.sjMc"></s:hidden>
  		<s:hidden name="epaper.sjZt"></s:hidden>
  		<input type="hidden" name="sjKksjbegin" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
  		
  		<input type="hidden" name="sjZjsjbegin" value='<s:date name="sjZjsjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
  		<s:hidden name="sjZjsjend"></s:hidden>
		<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm"/>
	</form>
  </c:if>
            </td>
        </tr>
              </table>
	
	</div>
	</td></tr></table>

</body>
</html>
              
       

