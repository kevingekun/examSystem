<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.utils.GroupUtil" %>

<html>
<head>
<title>详细信息</title>
<link href="<%=request.getContextPath() %>/newcss/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
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
function exportExcel(){
	document.exportE.action="cjpmexcelAction.action";
	document.exportE.submit();
}
//导出excel
function excel(paperid){
	window.location.href="cjpmexcelAction.action?paperid="+paperid;
}
//查看参加过的答卷
function selectInPapers(id){
	var url="usercjpmAction.action?djryid="+id;
	window.open(url,"列表查询","height=500,width=1000,top=100,left=200,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=yes");
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
function goback(){
	window.location.href="answerpaperpmquery.action";
}
function viewpam(){
	//var url="cjpmAction.action?paperid=<s:property value='paperid'/>";
	document.aForm.submit();
	//window.location.href=url;
}
//根据小组改变平均值
function changeavge(id){
var temp=id.value;
	if(temp==""){
		document.getElementById("averspan").style.display="none";
	}else{
		document.getElementById("averspan").style.display="";	
	}

}

</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left">
    <table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">试卷排名</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader"> 			  
			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>
                  	<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">排名列表</td>
                      </tr>
                  	</table>
                  </td>
                </tr>
              </table>
              <s:form action="" name="exportE"></s:form>
              <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">排名 </span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">准考证号</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
				<%-- <td  align="center" bgcolor="#C7E2F8"><span class="out">进入中心时间</span></td> --%>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">开考时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">得分</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
             </tr>
            
         <s:iterator value="gradeList" id="answerpaper" status="status">
           <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
             <td align='center' class='num_font'><s:property value="#status.index+1"/></td>
             <td align='center' class='num_font' title="<s:property value='#answerpaper[0]'/>"><s:property value="#answerpaper[0]"/></td>
             <td align='center' class='num_font' title="查看参考过的答卷"><a href='javascript:void(0);' onclick='selectInPapers(<s:property value="#answerpaper[0]"/>)'><s:property value="#answerpaper[1]"/></a></td>
			 <td align='center' class='num_font'><s:property value="#answerpaper[2]"/></td>
			 <td align='center' class='num_font'><s:property value="#answerpaper[3]"/></td>
			 <td align='center' class='num_font'><s:property value="#answerpaper[4]"/></td>
     	     <td align='center' class='num_font'><s:property value="#answerpaper[5]"/></td>
             <%-- <td align='center' class='num_font'><s:date name="#answerpaper.djJssj" format="yyyy-MM-dd HH:mm:ss"/></td> --%>
             <td align='center' class='num_font'><s:property value="#answerpaper[6]"/></td>
 			 <td align='center' class='num_font'>
	 			 <c:choose>  
					<c:when test="${answerpaper[7]=='0'}"><a style="color: green;">正常</a></c:when>
					<c:when test="${answerpaper[7]=='1'}"><a style="color: red;">作弊</a></c:when>
					<c:when test="${answerpaper[7]=='2'}"><a style="color: orange;">缺考</a></c:when>
				 </c:choose>
			</td>
          </tr>
          </s:iterator>
    </table> 
	<c:if test="${gradeList!= null }">
	  	<s:form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post">
			<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm"/>
		</s:form>
  	</c:if>
            </td>
        </tr>
              </table>
       <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
        <td width="20%" align="left">本次参考人数:<s:property value="all"/>&nbsp;&nbsp;&nbsp;&nbsp;不及格人数:<s:property value="unpass"/>
       	<!--<span id="averspan" style="display:none">--> &nbsp;&nbsp;&nbsp;&nbsp;平均分：<s:property value="average"/><!--</span>--> &nbsp;&nbsp;&nbsp;&nbsp;<%-- 总平均分：<s:property value="countaveage"/>&nbsp;&nbsp;&nbsp;&nbsp; --%>合格率：<s:property value="percent"/>%</td>   
          </tr>
          </table>
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center" ><input name="button" type="button" class="submit_2" onclick="goback();" value="返回" />
                  </td>&nbsp;&nbsp;&nbsp;&nbsp;
                  <td align="center">
                  	<input name="button" type="button" class="submit_2" onclick="excel(<s:property value="#answerpaper[9]"/>);" value="导出excel" /><!-- '<s:property value="#answerpaper.epapers.sjId"/>' -->
                  	<!-- <input name="button" type="button" class="submit_2" onclick="exportExcel()" value="导出excel" /> -->
                  </td>                              
                </tr>
                <tr>                
                </tr>
              </table>
	</td></tr></table>
</body>
</html>         
  		  <!--   分页       
  <c:if test="${eanswerpapers!= null }">
  	<s:form action="tosypaperAction" name="tosypaperActionform">
		<elile:navigateBar navigateform="navigateform" actionName="tosypaperAction.action" formName="tosypaperActionform"/>
	</s:form>
  </c:if>
  	</tr>
    </table>    
	<div align="center"><button class="SmallButton" onclick="goback();">返回</button></div>
</body>
</html>
-->