<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wondersgroup.falcon.Util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />

<%
  String id = ParamUtil.getStringParameter(request,"id",null);
   %>
<script type="text/javascript">
function vilidateform(form){
	if(form.ggbt.value.length==0){
		alert("请填写标题！");
		return false;
	}
	var gglm=form.gglm;
	var isfa=false;
	for(var i=0;i<gglm.length;i++){
		if(gglm[i].selected){
			if(gglm[i].value==-1){
				isfa=true;
				break;
			}
		}
	}
	if(isfa){
		alert("公告栏目不能为空！");
		return false;
		}
		
	var fbbm=form.fbbm;
	var isfa=false;
	for(var i=0;i<fbbm.length;i++){
		if(fbbm[i].selected){
			if(fbbm[i].value==-1){
				isfa=true;
				break;
			}
		}
	}
	if(isfa){
		alert("发布部门不能为空！");
		return false;
		}
	return true;
}

</script>
</head>
<body class="nrbj">
<s:form name="addgg" method="post" action="ggadd.action" onsubmit="return vilidateform(this);" enctype="multipart/form-data">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">添加公告</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
   <td colspan="2" valign="top" ><div id="content1" class="borader">
				<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
					     <tr>
                            <td width="20%" height="28" align="right">标 &nbsp;&nbsp;&nbsp;题：</td>
                            <td width="79%" align="left"><input type=text id="ggbt" name="ggbt" size=100 ></td>
                          </tr>
                           <tr>
                            <td width="20%" height="28" align="right">公告栏目：</td>
                            <td width="79%" align="left">
                            	<s:select name="lmid" id="gglm" list="tgglms" listKey="lxid" listValue="lxmc" headerKey="-1" headerValue="--全部--"/>
                            </td>
                          </tr>
                          <tr>
                            <td width="20%" height="28" align="right">发布部门：</td>
                            <td width="79%" align="left">
                            	<s:select name="bmid" id="fbbm" list="tbms" listKey="bmid" listValue="bmmc" headerKey="-1" headerValue="--全部--"/>
                            </td>
                          </tr>
                          <tr>
                            <td width="20%" height="28" align="right">公告内容：</td>
                            <td width="79%" align="left">
                            	<textarea name="ggnr" id="GGNR" cols="100" rows="5"></textarea>
                            </td>
                          </tr>
                        
                          <tr>
                            <td width="20%" height="28" align="right">附件：</td>
                            <td width="79%" align="left">
                            	<s:file name="upload" cssStyle="width:500px"></s:file>
                            </td>
			              </tr>
			            
				          <tr>
				            <td height="1" colspan="2"></td>
				          </tr>
						  <tr> 
					      	<table width="49" border="0" align="center" cellpadding="0" cellspacing="0">
					        	<tr> 
					          		<td ><input type="submit" class="submit_2" value="提 交"></td>
					        	</tr>
					      	</table>
						  </tr>
      			 </table>
      		</div></td>
		</tr>
	</table>
</s:form> 
</body>
</html>