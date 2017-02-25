<%@page language="java" contentType="text/html;charset=gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>无标题文档</title>
		
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<style type="text/css">

	html{ height:100%;}
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #ebf3f6;
		height:100%;	
	}

</style>
<script language="javascript">

function autoHeight()
{
	var obj = document.getElementById("content1");
	var h = document.body.clientHeight;
	obj.style.height=(h-34)+"px";
	
	
}

</script>	

	
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.archives.*"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*"%>
<%@ page import="com.wondersgroup.falcon.model.auth.*"%>
<%@ page import="com.wondersgroup.falcon.model.dic.*"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%> 
	<link rel="stylesheet" type="text/css" href="../css/main.css"/>
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
		<link href="../inc/all.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="images/menu.js"></script>

	<%
	
	
   	String cname = RequestUtils.getString(request, "cname", "service");
	String op = RequestUtils.getString(request, "op", "query");
	String nodeid = RequestUtils.getString(request, "nodeid", "");
	System.out.println("YYYYYYYYYYY"+nodeid);
	if(nodeid.equals("")) nodeid="102";
	List pagelist=null;
	//分页
	int currpage = RequestUtils.getInt(request, "currpage", 1);//默认1 currpage
	int pagesize = RequestUtils.getInt(request, "pagesize", 15);//默认行行数 pagesize
	int pagenum = RequestUtils.getInt(request, "pagenum", 5);

	//参数
	//排序
	String orderby = RequestUtils.getString(request, "orderby",
			"ordering");
	String status = RequestUtils.getString(request, "status", "");
	String name = RequestUtils.getString(request, "name", "");
	String fileno = RequestUtils.getString(request, "fileno", "");
	String effective = RequestUtils.getString(request, "effective", "");
	String visible = RequestUtils.getString(request, "visible", "1");
	String self = request.getParameter("self");
	String chuti = RequestUtils.getString(request, "chuti", "");
	if(self==null)self="1";
	
	//新增加的
	if(chuti.equals("1")){
	   self="1";
	}
	
	//System.out.println("chuti=============》"+chuti);
	//--------------------------------------------------	
	
	if (op.equals("query")) {
		AbstractTree t = FactoryBean.creator(cname);
		ServiceNode node = new ServiceNode();
		DicReleasestate dic = new DicReleasestate();
		Node fathernode = null;
		if (self.equals("1"))
			fathernode = t.getNodeById(Node.getNodeType(cname), new Long(nodeid));
		//System.out.println("fileno=============》"+fathernode);
		ServiceAttr attr = new ServiceAttr();
		dic.setId(new Long(6).longValue());
		//attr.setDicReleasestate(dic);
		node.setName(name.equals("") ? null : name);
		node.setVisible(visible.equals("1") ? true : false);
	
		//区分区县用户类型
		
		node.setParent(fathernode);
		node.setAttribute(attr);

		//查询数据
	    pagelist = t.findPaginationData((Node) node,chuti, currpage,
				pagesize, orderby);
		int total = t.findPaginationTotalCount((Node) node,chuti, currpage,
				pagesize, orderby);

		request.setAttribute("pagelist", pagelist);

		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);
		request.setAttribute("navigateform", navigateform);
		}
	%>
	
<SCRIPT language=JavaScript>
	function opQuery(){
	var fileno =document.getElementById("fileno").value;
	var name =document.getElementById("name").value;
	var self =document.getElementById("self").value;
	var chuti =document.getElementById("chuti").value;
	window.queryform.fileno.value = fileno;
	window.queryform.name.value=name;
	window.queryform.self.value=self;
	window.queryform.chuti.value=chuti;
	window.queryform.submit();
}



//--------按钮操作 结束----------------------------------------//
//--------菜单操作 开始----------------------------------------//


//-------菜单操作 结束 --------------------------------------------------//


function opOK(){
	
	document.lookup.submit();
}



	function select(){
		var selectList=document.getElementById("searchSelect");
		var holeSearch=document.getElementById("holeSearch");
		var keyWordSearch=document.getElementById("keyWordSearch");
		
		var selectValue=selectList.options[selectList.selectedIndex].value;
		if(selectValue==1){
			holeSearch.style.display="block";
			keyWordSearch.style.display="none";
		}
		if(selectValue==2){
			holeSearch.style.display="none";
			keyWordSearch.style.display="block";
		}
	}
function doChangefield(fieldvalue, fieldname){
	document.queryform.displayfield.value = fieldvalue;
	document.queryform.displayfieldname.value = fieldname;
	document.queryform.op.value = "new";
	document.queryform.submit();
}

	</script>
	
	<style type="text/css">
<!--
.w_blue {
	color: #6666CC
}

.w_blue_9 {
	color: #333399;
}
-->
</style>
<style type="text/css">
#dropmenudiv {
	position: absolute;
	border: 1px solid #999999;
	font: normal 12px Verdana;
	line-height: 18px;
	z-index: 100;
}
</style>
<style type="text/css">
<!--


.position01 { cursor:hand; }
#alt01 { position:absolute; border:1px solid #F4AC8E; background-color:#FDEFEE; padding:10px; text-align:left; text-indent:20px; }
.alt { width:200px; border:1px solid #F4AC8E; background-color:#FDEFEE; padding:4px; text-align:left; position:absolute; word-break:break-all; }
-->
</style>
<SCRIPT language=JavaScript>
var t_id = setInterval(animate,20);
function animate()
{
  //
} 
function remove_loading() {
this.clearInterval(t_id);
var targelem = document.getElementById('loader_container');
targelem.style.display='none';
targelem.style.visibility='hidden';
}

	function jiancha(){
		if(document.searchForm.searchWord.value==null||document.searchForm.searchWord.value==""){
			return false
		}
		return true
	}
</SCRIPT>
<STYLE type=text/css>
#loader_container1 {
	LEFT: -2px;
	WIDTH: 100%;
	POSITION: absolute;
	TOP: 1px;
	TEXT-ALIGN: right
}

#loader1 {
	BORDER-RIGHT: #5a667b 1px solid;
	PADDING-RIGHT: 0px;
	BORDER-TOP: #5a667b 1px solid;
	DISPLAY: block;
	PADDING-LEFT: 0px;
	FONT-SIZE: 11px;
	Z-INDEX: 2;
	PADDING-BOTTOM: 1px;
	MARGIN: 0px auto;
	BORDER-LEFT: #5a667b 1px solid;
	WIDTH: 130px;
	COLOR: #000000;
	PADDING-TOP: 1px;
	BORDER-BOTTOM: #5a667b 1px solid;
	FONT-FAMILY: Tahoma, Helvetica, sans;
	BACKGROUND-COLOR: #FFFFCA;
	TEXT-ALIGN: left
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #ebf3f6;
	height:100%;
	
}
</STYLE>
<SCRIPT language=JavaScript>
	function onkey(){
		if (window.event.keyCode==112){
		var url = '<%=request.getContextPath()%>/onlinehelp/show.jsp';
		window.open(url,'addAccording','height=450, width=800, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no,status=yes');
		}
	}
	function aaaa(){
		var obj=document.getElementsByName("zhengchefagui");
		var count=0;
		var st_wh="";
		var st_cc="";
		var wh_id="";
		var type=""
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				count++;
				var names=obj[i].value.split("||");
				st_cc=st_cc+names[1]+"\r\n";
				wh_id=wh_id+names[2]+",";
				type=type+"S,"
			}
		}
		if(count==0){
			alert("请选择");
		}else{
			if(type==""){
				type='S'
			}
			window.parent.opener.fuzhi(st_wh,st_cc,wh_id,type);
			window.parent.close();
		}
	}
</SCRIPT>
	<body onkeydown="onkey();"  style="scrollbar-base-color:#ebf3f6">



<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:2px;">
  <!-- 
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">政策法规查询</td>
          <td  class="header3"></td>
        </tr>
      </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10"></td>
        </tr>
      </table>
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
-->

	<form name="form2" action="" method="post">
	        <input type = hidden name = op value="query"></input>
            <input type=hidden name=self value=0></input>
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
          <td width="15%" height="26" align="right">标题：</td>
          <td width="20%"><input name="name" type="text" class="input3" /></td>
          <td width="15%" align="left">
           是否已出题：<input type="checkbox" name="chuti" id="chuti" value="1" ></td>
         <td width="20%" > <INPUT class = "submit_2" id=doSearch type=submit value=搜索></INPUT></td>
        </tr>
        <!--  
        <tr class="row_height">
          <td width="20%" align="right">状态:</td>
          <td width="30%" colspan="2"><select name="effective">
					<option value="">请选择</option>
					<option value="0">失效</option>
					<option value="1">正常</option>
					<option value="2">部分失效</option>
					<option value="3">废止</option>
					
				</select>
			</td>
         
    	</tr>
    	-->
	</table>
</form>


	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">列表</td>
                      <td align="right"><input  type ="button" value="确定并关闭" onclick="aaaa();"/></td>
                    </tr>
                </table></td>
              </tr>
 </table>


		
		
		<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="tb" >
			<tr class="title_font">
				
				
				<td align="center" width=55% bgcolor="#C7E2F8"><span class="out">
					标题
				</span></td>			
				
				
			</tr>

			<%
			int n = 0;
			ServiceNode pn1 = new ServiceNode();
			%>
			<c:forEach var="aBean" items="${pagelist}" varStatus="status">
											<%
												n++;
												pn1 = (ServiceNode)pagelist.get(n-1);
												if(pn1.isVisible()==true){
											%>
											<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1' bgcolor="<%=n != n / 2 * 2 ? "#ebf3f6" : "#ebf3f6"%>"
												onMouseOut="this.style.background='<%=n != n / 2 * 2 ? "#ebf3f6" : "#ebf3f6"%>'"
												onMouseOver="this.style.background='#E0E9F1'">
												
												

												<td  class='num_font' height="30" valign="middle" nowrap="nowrap" width="70%">
												&nbsp;&nbsp;&nbsp;&nbsp;
											<!-- 	<a href="<%=request.getContextPath()%>/toViewBszn.action?cname=service&docid=<%=pn1.getId() %>" title="" >   -->
												<a href="\\168.30.1.54\c$\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv01\installedApps\yibao-cd3ed4aedNode01Cell\falnew_war.ear\falnew.war\libstore\service\<%=pn1.getAttribute().getHtmlFile() %>" title="\\168.30.1.54\c$\Program Files\IBM\WebSphere\AppServer\profiles\AppSrv01\installedApps\yibao-cd3ed4aedNode01Cell\falnew_war.ear\falnew.war\libstore\service\<%=pn1.getAttribute().getHtmlFile() %>" target="_blank" >
													<span class="">
															<font
															color='<c:choose><c:when test="${aBean.visible == false}">red</c:when><c:otherwise>black</c:otherwise></c:choose>'>
																<c:set value="${aBean.name}" target="${aBean}"
																	property="shortName" /> <c:out
																	value="${aBean.name}" /> </font> </span> </a>
												</td>
												<td class='num_font' align="center" valign="middle"  >
													<input type="checkbox" name="zhengchefagui" value='||<c:out value="${aBean.name}" />||<c:out value="${aBean.id}" />'/>
												</td>
											
											</tr>
											<%} %>
										</c:forEach>
											<c:if test="${empty pagelist}">
										
											<tr bgcolor="#FFFFFF"%>

												<td colspan="7" height="20" align="left">
												<%if(!nodeid.equals("100")) {%>
													<!--	<font color="#FF0000">&nbsp;&nbsp;&nbsp;没有符合条件的查询结果！</font> -->
													<%} %>
												</td>
											</tr>
										</c:if>
			
		</table>
		</div>
		</form>
		<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
        <tr>
          <td align="center" valign="middle" class="num_font"><div class="page1_box">
          		<form name="queryform" action="<%=request.getContextPath()%>/service/ser_list.jsp" method="post">
						<input type="hidden" name="op" value="query"/>
						<input type="hidden" name="nodeid" value="<%=nodeid%>"></input>
						<input type="hidden" name="name" value="<%=name%>"></input>
						<input type="hidden" name="status" value="<%=status%>"></input>
						<input type="hidden" name="visible" value="1"></input>
						<input type="hidden" name="cname" value="<%=cname%>"></input>
						<input type="hidden" name="orderby" value="<%=orderby%>"></input>
						<input type="hidden" name="fileno" value="<%=fileno%>"></input>
					<input type="hidden" name="self" value="<%=self%>"></input>
					<input type="hidden" name="chuti" value="<%=chuti%>"></input>
					<input type="hidden" name="orderby" value="<%=orderby%>"></input>
					
					
						<c:if test="${pagelist!= null}">
							<elile:navigateBar navigateform="navigateform"
								actionName="/falnewexam/service/ser_list.jsp" formName="queryform" />
						</c:if>
					</form>
          </div></td>
        </tr>
      </table>
		
		
		
		
<SCRIPT LANGUAGE="JavaScript">
function disp_s(id) {
//alert("alt"+id);
document.getElementById("alt"+id).style.display ="block";
}
function disp_n(id) {
document.getElementById("alt"+id).style.display ="none";
}
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function disp_s2() {
document.getElementById("alt02").style.display ="block";
}
function disp_n2() {
document.getElementById("alt02").style.display ="none";
}

	</body>
</html>
