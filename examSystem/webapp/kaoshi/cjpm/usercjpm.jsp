<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.utils.GroupUtil" %>

<html>
<head>
<title>详细信息</title>
<link href="<%=request.getContextPath() %>/newcss/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
<script language="JavaScript" type="text/JavaScript" >
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
//导出excel



function excel(paperid){
	window.location.href="cjpmexcelAction.action?paperid="+paperid;
}
//查看参加过的答卷
function selectInPapers(id){
	var url="usercjpmAction.action?djryid="+id;
	window.open(url,"列表查询","height=800,width=600,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes");
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
<!--  
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
                        <s:form action="cjpmAction.action" name="aForm" method="POST">
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                          <tr>
							<input type="hidden" name="paperid" value='<s:property value="paperid"/>'/>
							<input type="hidden" name="countaveage" value='<s:property value="countaveage"/>'/>
                            <td width="13%" height="28" align="right" style="font-size:12px">小组名称：</td>
                            <td width="18%" align="left">
								<select name="groupId">
									<option value="">请选择</option>
								<s:iterator value="groupList" id="group" status="status">
									<option value="<s:property value='#group.id'/>"><s:property value="#group.name"/></option>
								</s:iterator>
								</select>
								</td>
                            <td width="13%" align="right" style="font-size:12px">起始分数：</td>
                            <td width="18%"><input type="text" id="startScore" name="startScore" style="width:150px" /></td>
                            <td width="13%" align="right" style="font-size:12px">结束分数：</td>
                            <td width="18%" colspan="2"><input type="text" id="endScore" name="endScore" style="width:150px"/></td>
                          </tr>
							<tr>
                            <td width="13%" height="28" align="right" style="font-size:12px">性别：</td>
                            <td width="18%" align="left">
								<select name="sex" style="width:65%">
									<option value="">请选择</option>
									<option value="sex" id="1" >男</option>
									<option value="sex" id="0" >女</option>
								</select>
								</td>
							<td width="13%" align="right" style="font-size:12px">用户星级：</td>
                            <td width="18%"><input type="text" id="userstar" name="userstar" style="width:150px" /></td>
							<td width="13%" align="right" style="font-size:12px">进入中心时间：</td>
                            <td width="18%" colspan="2">
								<input type="text" value='<s:date name="startctime" format="yyyy-MM-dd HH:mm:ss"/>' style="width:75px" class="Wdate" name="startctime"  onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>~
								<input type="text" value='<s:date name="endctime" format="yyyy-MM-dd HH:mm:ss"/>' style="width:75px" class="Wdate" name="endctime" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td>
                            </tr>
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                        </s:form>
                      </table></td>
                </tr>
              </table>
		
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center"><input name="button" type="button" class="submit_2" onclick="viewpam();"  value="查 询" />
                    </td>
                </tr>
              </table>
              -->
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
 <!--   <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">试卷排名</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>-->
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader"> 			  
			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">排名列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
              <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">排名 </span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">答卷人员</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">性别</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">用户星级</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">进入中心时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">开考时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">得分</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td>
             </tr>
         <s:iterator value="eanswerpapers" id="answerpaper" status="status">
           <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
             <td align='center' class='num_font'><s:property value="#status.index+1"/></td>
             <td align='center' class='num_font' title="<s:property value='#answerpaper.epapers.groupId'/>"><s:property value="#answerpaper.epapers.sjMc"/></td>
             <td align='center' class='num_font'><s:property value="#answerpaper.djRymc"/></td>
			 <td align='center' class='num_font'><c:if test="${answerpaper.userSex=='0'}">女</c:if><c:if test="${answerpaper.userSex=='1'}">男</c:if> </td>
			 <td align='center' class='num_font'><s:property value="#answerpaper.userStar"/></td>
			 <td align='center' class='num_font'><s:date name="#answerpaper.userDate" format="yyyy-MM-dd"/></td>
     	     <td align='center' class='num_font'><s:date name="#answerpaper.djKssj" format="yyyy-MM-dd HH:mm:ss"/></td>
             <td align='center' class='num_font'><s:date name="#answerpaper.djJssj" format="yyyy-MM-dd HH:mm:ss"/></td>
             <td align='center' class='num_font'><s:property value="#answerpaper.djZf"/></td>
 			 <td align='center' class='num_font'><c:choose>  
				<c:when test="${answerpaper.djZf>=90}">A级</c:when>
				<c:when test="${answerpaper.djZf>=80&&answerpaper.djZf<90}">B级</c:when>
				<c:when test="${answerpaper.djZf>=70&&answerpaper.djZf<80}">C级</c:when>
				<c:otherwise>D级</c:otherwise>
			 </c:choose></td>
          </tr>
          </s:iterator>  
    </table> 
	<c:if test="${epapers!= null }">
              <!-- fenye -->
  	<s:form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post">
  	    <s:hidden name="epaper.sjId" id="epaperid"></s:hidden>
  		<s:hidden name="epaper.sjMc"></s:hidden>
  		<s:hidden name="epaper.sjZt"></s:hidden>
  		<input type="hidden" name="sjKksjbegin" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
  		<input type="hidden" name="sjZjsjbegin" value='<s:date name="sjZjsjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
  		<s:hidden name="sjZjsjend"></s:hidden>
		<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm"/>
	</s:form>
  </c:if>  
            </td>
        </tr>
              </table>
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center" ><input name="button" type="button" class="submit_2" onclick="javascript:self.close();" value="关闭" />
                  </td>&nbsp;&nbsp;&nbsp;&nbsp;
                  <td align="center"><input name="button" type="button" class="submit_2" onclick="excel('<s:property value="#answerpaper.epapers.sjId"/>');" value="导出excel" />
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