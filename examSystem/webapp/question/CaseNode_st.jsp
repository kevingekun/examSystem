<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<title>需要更新的题目</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" >

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

function modify(stid){
	
	document.aForm.action="QuestionServlet?stid="+stid;
	document.aForm.myaction.value="modifyload_st";
	document.aForm.submit();
}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">需要更新的试题</td>
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
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
       <form name="aForm" action="<%=request.getContextPath() %>/QuestionServlet" method="post">
       <input type=hidden name="actionType" value="query">
        <input type=hidden name="myaction" value="modifyload_st">
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">题目</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">业务类型</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">试题出处</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">文号</span></td>
             </tr>           
          <s:iterator value="caseNode_st" id="question" status="status">
          <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#status.index+1"/>
                </td>
                <td align='center' class='num_font'><a href="#" onClick='modify(<s:property value="#question.stId"/>);' >
	            <s:property value="#question.stTg"/> </a>        
	     	    </td>
	     	    <td align='center' class='num_font'><s:property value="#question.ebusinesstype.name"/></td>
	     	    <td align='center' class='num_font'><s:property value="#question.stCc"/></td>
	            <td align='center' class='num_font'><s:property value="#question.stWh"/></td>
	            
             </tr>  
          </s:iterator>
		 </table>
		 </form>

    <s:form name="mypaperActionform" method="post" action="toView_Cst.action">
  		<input type="hidden" name="caseNodeId" value="<s:property value='caseNodeId'/>"/>
         <elile:navigateBar navigateform="navigateform" actionName="toView_Cst.action" formName="mypaperActionform"/>
	</s:form>

</td>
</tr>
</table>
</div>
</td>
</tr>
</table>
</body>
</html>

