<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="elile.tld" prefix="elile"%>
<html>
<head>
<title>详细信息</title> 
<link href="<%=request.getContextPath() %>/css/all.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" >

function modify(stid){
	
	document.aForm.action="QuestionServlet?stid="+stid;
	document.aForm.myaction.value="modifyload";
	document.aForm.submit();
}

function mediaplayer(url,shuzi,name){
	var url="ftp://cl:cl@"+url+"/"+shuzi+"/"+name+".wav";
	alert(url);
	var MediaPlayer=document.getElementById("MediaPlayer");
	MediaPlayer.Filename = url;
	//alert(MediaPlayer.Filename);
}

function select(url,shuzi,name){
	var url="'ftp://cl:cl@"+url+"/"+shuzi+"/"+name+".wav";
	window.opener.getluyintiValue(name,url);
	window.close();
}
</script>
</head>
<body>

<s:form name="queryform" action="luyintilist.action" method="post">

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
            <td><div align="center"><strong>录音题查询</strong></div></td>
          </tr>
        </table>
      </div></td>
    <td width="8"><img src="<%=request.getContextPath() %>/images/min_03.gif" width="8" height="32"></td>
  </tr>
<tr>
    <td height="5" colspan="3"></td>
</tr>
			  
</table>



<object align="middle"
			classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer"
			width="400" height="69">
			<param name="ShowStatusBar" value="-1">
			<param name="AutoStart" value="0">
			<param name="Filename" value="">
			<embed type="application/x-oleobject"
				codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
				flename="mp" src="" width=200 height=50></embed>
</object>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="5" colspan="4"></td>
    </tr>
    <tr>
      <td width="3"><img src="images/k1_01.gif" width="3" height="30"></td>
      <td width="21" background="images/k1_03.gif"><div align="center"><img src="images/k1_02.gif" width="21" height="30"></div></td>
      <td width="98%" background="images/k1_03.gif">试题查询列表</td>
      <td width="4" valign="top"><img src="images/k1_04.gif" width="4" height="30"></td>
    </tr>
    <tr>
      <td background="images/k1_05.gif"></td>
      <td colspan="2">
	  	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#C4E2FB">
          <tr>
            <td align="center">开始时间</td>
            <td align="center">录音长度</td>
            <td align="center">电话号码</td>
            <td align="center">呼叫方向</td>
			<td align="center">操作</td>			
          </tr>    
          <s:iterator value="recordoriginaldates" id="aBean" status="state">
          <tr bgcolor="#FFFFFF">
            <td width="20%"><s:date name="#aBean.startrecordtime" format="yyyy-DD-mm"/>     </td>
     	    <td><s:property value="#aBean.recordlength"/></td>
            <td><s:property value="#aBean.callerid"/></td>
            <td><s:property value="#aBean.directionflag"/></td>
			<td>
				<a href="#" onclick="mediaplayer('<s:property value="#aBean.voiceip"/>','<s:property value="#aBean.voiceid"/><s:property value="#aBean.channel"/>','<s:property value="#aBean.recordreference"/>')">播放</a>
				<a href="#" onclick="select('<s:property value="#aBean.voiceip"/>','<s:property value="#aBean.voiceid"/><s:property value="#aBean.channel"/>','<s:property value="#aBean.recordreference"/>')">选择</a>
				
			</td>
          </tr>
          </s:iterator>
		 </table>
		  </td>
      <td background="images/k1_06.gif"><img src="images/k1_06.gif" width="4" height="2"></td>
    </tr>
    <tr>
      <td><img src="images/k1_07.gif" width="2" height="5"></td>
      <td colspan="2" background="images/k1_08.gif"> </td>
      <td><img src="images/k1_09.gif"></td>
    </tr>


  <!--   分页       -->
  <c:if test="${list!= null}">
	<elile:navigateBar navigateform="navigateform" actionName="luyintilist.action" formName="queryform"/>
  </c:if>
  
    </table>
   </s:form>
  <!-- <button class="BigButton" onclick="del();">删除</button>-->
  <script language="javascript" type="text/JavaScript">
function doQuery() {

  document.queryform.submit();
}
</script>
</body>
</html>

