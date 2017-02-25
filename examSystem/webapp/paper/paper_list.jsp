<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>

<html>
<head>
<title>详细信息</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/date2.js"></script>
<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js" ></script>
<script type="text/JavaScript" >
function modify(stid){
	
	document.aForm.action="epaperquestion.action?sjid="+stid;
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


function doQuery() {

  document.aForm.submit();
}

function changePaper(sjid){
	window.open ("<%=request.getContextPath() %>/paper/changepaper.jsp?sjid="+sjid, "newwindow", "height=400, width=1000, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
	//doQuery();
}
function jumpViewPaper(sjid){
	window.location.href="previewPaperAction.action?paperid="+sjid;
}
//删除试卷
function deleteViewPaper(sjid){
     var tt=confirm("确定要删除整份试卷吗？");  //确认是否删除
     if(tt){
	window.location.href="deletePaperAction.action?paperid="+sjid;
	}
}
//复制试卷
function copyPaper(sjid){
	window.location.href="copyPaperAction.action?paperid="+sjid;
}
//更改试卷状态
function changeState(sjid,paperState){
	var preState=$("#sjZt").val();
	var sjMc=$("#sjMc").val();
	window.location.href="changeState.action?paperid="+sjid+"&paperState="+paperState+"&preState="+preState+"&sjMc="+sjMc;
}
//拨测试卷改变试卷状态
function changeStateByKaoshi(sjid,ryid){
 var url="examTelephonePaper.action?paperId="+sjid+"&ryid="+ryid;
 window.location.href=url;
}
function viewpam(paperid){
	window.location.href="cjpmAction.action?paperid="+paperid;
}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">考试管理</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" >
    <div id="content1" class="borader">
    
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="borader3">
                  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                            <tr>
                              <td  align="left" valign="middle" class="header7"></td>
                              <td  class="header8">查询条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
                    <form action="<%=request.getContextPath() %>/papersServlet" name="aForm" method="POST">
					<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
						 <input type=hidden name="actionType" value="query">
						 <input type=hidden name="myaction" value="">
						 <input type="hidden" name="currpage" value="1"/>
                         <tr>
                            <td width="13%" height="28" align="right">试卷名称：</td>
                            <td width="18%" align="left"><input type="text" id="sjMc" name="sjMc" value="<c:out value='${sjMc}'/>" style="width: 200px"></td>
                            <td width="13%" height="28" align="right">开考时间：</td>
                            <td width="18%" align="left"><input type="text" class="Wdate" id="sjKksjbegin" name="sjKksjbegin" value="<fmt:formatDate value='${sjKksjbegin}' type='date' timeStyle='default'/>" onclick="WdatePicker()"/>
							至
							<input type="text" class="Wdate" id="sjKksjend" name="sjKksjend" onclick="WdatePicker()" value="<fmt:formatDate value='${sjKksjend}' type='date' timeStyle='default'/>"/>
							</td>
                          </tr>
                          <tr>
                          <td width="13%" align="right">试卷状态：</td>
                            <td width="18%"> 
                            	<select id="sjZt" name="sjZt" style="width:200px;">
						          <option value="-1" selected="selected">全部</option>
						          <option value="0" <c:if test="${sjZt=='0'}">selected="selected"</c:if>>未考试</option>
						          <option value="1" <c:if test="${sjZt=='1'}">selected="selected"</c:if>>考试中</option>
						          <option value="2" <c:if test="${sjZt=='2'}">selected="selected"</c:if>>已结束</option>
					        	</select>
					        </td>
                            
							<td width="13%" align="right">组卷时间：</td>
                            <td width="18%"> <input type="text" class="Wdate" id="sjZjsjbegin" name="sjZjsjbegin" value="<fmt:formatDate value='${sjZjsjbegin}' type='date' timeStyle='default'/>" onclick="WdatePicker()"/>
							至
							<input type="text" class="Wdate" id="sjZjsjend" name="sjZjsjend" value="<fmt:formatDate value='${sjZjsjend}' type='date' timeStyle='default'/>" onclick="WdatePicker()"/>
							</td> 
                          </tr>
                          <tr>
                  			<td height="8" colspan="4"></td>
                		  </tr>
                          <tr>
                  			<td align="center" colspan="4">
                  				<input name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查询" />
                    		</td>
                		  </tr>
                		  <tr>
                  			<td height="8" colspan="4"></td>
                		  </tr>
        
        			
     </table>
     </form>
     </td>
     </tr>                                     
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">试卷列表显示</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
      	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
            <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td align="center" bgcolor="#C7E2F8" width="30%" ><span class="out">试卷名称</span></td>
				<td align="center" bgcolor="#C7E2F8"><span class="out">考试类型</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">开考时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">有效截止日期</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">组卷时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr>
             
           <c:forEach var="aBean" items="${list}" varStatus="status">
             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><c:out value="${status.index+1}"/>
                </td>
                <td align='center' class='num_font'>
	                 <a href="#" <c:if test="${colorCheck=='0' }">onClick='modify(<c:out value="${aBean.sjId}"/>);'</c:if> title="<c:out value="${aBean.sjMc}"/>" >            
		             <c:set value="${aBean.sjMc}" target="${aBean}" property="shortSjMc"/>
		            　 		<c:out value="${aBean.sjMc}"/></a>              
	     	    </td>
				<td align='center' class='num_font'>
				  <c:choose>
	                <c:when test="${aBean.sjKslx == '1'}">鉴定类考试</c:when>
	                <c:when test="${aBean.sjKslx == '5'}">其他类考试</c:when>
	                <c:otherwise></c:otherwise>
	              </c:choose> 
				</td>
	     	    <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjKksj}" type="date" timeStyle="default" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjYxqjzsj}" type="date" timeStyle="default" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td align='center' class='num_font'><fmt:formatDate value="${aBean.sjZjsj}" type="date" timeStyle="default" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td align='center' class='num_font'>
	                <c:choose>
	                <c:when test="${aBean.sjZt == '0'}">
	                  <font color="green">未审核</font>
	                </c:when>
	                <c:when test="${aBean.sjZt == '1'}">
	                  <font color="red">考试中</font>
	                </c:when>
	                <c:when test="${aBean.sjZt == '2'}">
	                  <font color="orange">已结束</font>
	                </c:when>
	                <c:when test="${aBean.sjZt == '6'}">
	                  <font color="blue">审核通过</font>
	                </c:when>
	              </c:choose>
               </td>
	            <td align="center">
	            	<c:if test="${colorCheck=='0' }">
		            	<button class="SmallButton" onclick='jumpViewPaper(<c:out value="${ aBean.sjId}"/>);'>预览</button>
		            </c:if>
	            	<c:if test="${aBean.sjZt=='0'||aBean.sjZt=='6'}">
	            		<button class="SmallButton" onclick='deleteViewPaper(<c:out value="${ aBean.sjId}"/>);'>删除</button>
	            	</c:if>
	            	<button class="SmallButton" onclick='changePaper(<c:out value="${ aBean.sjId}"/>);'>修改</button>
	            	<c:if test="${aBean.sjZt=='6'}">
					<c:if test="${aBean.paperType==4}">
						<button class="SmallButton" onclick='changeStateByKaoshi(<c:out value="${ aBean.sjId}"/>,<c:out value="${ aBean.toUserId}"/>);'>考试中</button>
					</c:if>
					<%-- <c:if test="${aBean.paperType!=4}">
	            		<button class="SmallButton" onclick='changeState(<c:out value="${ aBean.sjId}"/>,1);'>考试中</button>
					</c:if> --%>
	            	</c:if>
	            	
	            	<c:if test="${aBean.sjZt!='0'&&aBean.sjZt!='6'}">
	            		<button class="SmallButton" disabled="disabled" onclick='deleteViewPaper(<c:out value="${ aBean.sjId}"/>);'>删除</button>
	            	</c:if>
	            	<c:if test="${aBean.sjZt=='1'}">
	            		<button class="SmallButton" onclick='changeState(<c:out value="${ aBean.sjId}"/>,2);'>考试完</button>
	            	</c:if>
	            	<%-- <button class="SmallButton" onclick='copyPaper(<c:out value="${ aBean.sjId}"/>);'>复制</button> --%>
	            	
	            </td>
             </tr>
           </c:forEach>
           
        </table>    
        <c:if test="${list!= null }">
	    <form action="papersServlet" name="sForm" style="margin-top: 6px;">
		     <input type=hidden name="actionType" value="query">
			 <input type=hidden name="myaction" value="">
			 <input type="hidden" name="sjMc" value="<c:out value='${sjMc}'/>"/>
			 <input type="hidden" name="sjZt" value="<c:out value='${sjZt}'/>"/>
			 <input type="hidden" name="sjKksjbegin" value="<fmt:formatDate value='${sjKksjbegin}' type='date' timeStyle='default'/>" />
			 <input type="hidden" name="sjKksjend" value="<fmt:formatDate value='${sjKksjend}' type='date' timeStyle='default'/>"/>
			 <input type="hidden" name="sjZjsjbegin" value="<fmt:formatDate value='${sjZjsjbegin}' type='date' timeStyle='default'/>" />
			 <input type="hidden" name="sjZjsjend" value="<fmt:formatDate value='${sjZjsjend}' type='date' timeStyle='default'/>" />
			 <elile:navigateBar navigateform="navigateform" actionName="papersServlet" formName="sForm"/>
		</form>
  	  </c:if>              
	    </td>
        </tr>
      </table>
	  
	</table>
	</div>
	</td>
	</tr>
</table>
</body>
</html>

