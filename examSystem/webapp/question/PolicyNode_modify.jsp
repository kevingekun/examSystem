<%@ page contentType = "text/html;charset=gb2312" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>�Ѿ����޸Ĺ������߷���</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
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
		alert("��ѡ��Ҫɾ���ļ�¼��");
		return false;
	}
    var tt=confirm("ȷ��Ҫɾ����");  //ȷ���Ƿ�ɾ��
    if(tt){
    	document.aForm.action="QuestionServlet";
		document.aForm.myaction.value="del";
		document.aForm.submit();
    }else{
    	return false;
    }
	
}


function Remove(ID){                        
     var tt=confirm("ȷ���������߷���������ⶼ�Ѿ��޸�����ˣ�"); 
     if(tt){ 	           
     window.location.href="modefy_state.action?policyNodeId="+ID;
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
//�����Ծ�
function copyPaper(sjid){
	window.location.href="copyPaperAction.action?paperid="+sjid;
}
//�����Ծ�״̬
function changeState(sjid,paperState){
	window.location.href="changeState.action?paperid="+sjid+"&paperState="+paperState;
}

function modify(stid){
	
	document.aForm.action="QuestionServlet?stid="+stid;
	document.aForm.myaction.value="modifyload";
	document.aForm.submit();
}
//�鿴�÷����µ���Ŀ
function view(stid){
	
	document.aForm.action="QuestionServlet?stid="+stid;
	document.aForm.myaction.value="modifyload";
	document.aForm.submit();
}
//�������޸�״̬��Ϊ�����
function deleteViewPaper(sjid){
     var tt=confirm("ȷ�ϴ˷����������Ŀ�Ѿ��޸���ɣ�");  //ȷ���Ƿ�ɾ��
     if(tt){
	window.location.href="deletePaperAction.action?paperid="+sjid;
	}
}


</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">�Ѿ����޸Ĺ������߷���</td>
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
                        <td  class="header8">�б�</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">��� </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">���߷������</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">�ļ�״̬</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">ȷ�ϴ��ļ��µ���Ŀ���޸����</span></td>
             </tr>   
          <s:iterator value="policyNode" id="policyNode" status="status">
          <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#status.index+1"/>
                </td>
                <td align='center' class='num_font'>
	            <s:url id="viewst" action="toView_st.action">
		            		<s:param name="policyNodeId" value="#policyNode.id"></s:param>
		            	</s:url>
		            	<s:a href="%{#viewst}">
		            	<s:property value="#policyNode.name"/> 
		            	</s:a>     
	     	    </td>
	     	     <td align='center' class='num_font'>
	     	       <s:if test="#policyNode.attribute.effective==0">ʧЧ���ļ�</s:if>
	     	       <s:elseif test="#policyNode.attribute.effective==3">��ֹ���ļ�</s:elseif>
	     	       <s:elseif test="#policyNode.attribute.effective==1">����ʧЧ���ļ�</s:elseif>
	     	       <s:elseif test="#policyNode.attribute.modifystate==2">�������ļ�</s:elseif>
	     	       <s:elseif test="#policyNode.attribute.modifystate==1" >�޸ĵ��ļ�</s:elseif>
                </td>
	     	    <td align='center' class='num_font'>
	     	    <button class="Submit_2" onclick='Remove(<s:property value="#policyNode.id"/> );'/> ȷ��</button>
	     	    
	     	    <!--  
	     	    <s:url id="modefy" action="modefy_state.action">
		            	<s:param name="policyNodeId" value="#policyNode.id"></s:param>
		            	</s:url>
		            	<s:a href="%{#modefy}">
		            	��ȷ�ϴ��ļ��µ���Ŀ���޸���ɡ�
		            	</s:a> 
		          -->
	     	   </td> 	          
             </tr>  
          </s:iterator>
		 </table>
	 
		 <s:form name="mypaperActionform" method="post" action="toView_UpdatedQuestions.action">  		
	         <elile:navigateBar navigateform="navigateform" actionName="toView_UpdatedQuestions.action" formName="mypaperActionform"/>        
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

