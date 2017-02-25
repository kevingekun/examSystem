<%@page language="java" contentType="text/html;charset=gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>无标题文档</title>
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

	
<script language="JavaScript" type="text/JavaScript" src="../js/dateMy97/WdatePicker.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.archives.*"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.*"%>
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
	
	User user = LoginValidateUtils.getUser(request);
	Long userid ;
	if(user==null){
		UserBean u = new UserBean();
		user = u.getUserById(new Long(1));
	}
	userid = user.getId();
	
   	String cname = RequestUtils.getString(request, "cname", "policy");
	String op = RequestUtils.getString(request, "op", "query");
	String nodeid = RequestUtils.getString(request, "nodeid", "");
	if(nodeid.equals("")) nodeid="100";
	List pagelist=null;
	//分页
	int currpage = RequestUtils.getInt(request, "currpage", 1);//默认1 currpage
	int pagesize = RequestUtils.getInt(request, "pagesize", 20);//默认行行数 pagesize
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
	if(self==null)self="1";
	//System.out.println("self=============》"+self);
	//--------------------------------------------------	
	
	if (op.equals("query")) {
		AbstractTree t = FactoryBean.creator(cname);
		PolicyNode node = new PolicyNode();
		DicReleasestate dic = new DicReleasestate();
		Node fathernode = null;
		if (self.equals("1"))
			fathernode = t.getNodeById(Node.getNodeType(cname), new Long(nodeid));
		//System.out.println("fileno=============》"+fathernode);
		PolicyAttr attr = new PolicyAttr();
		dic.setId(new Long(6).longValue());
		//attr.setDicReleasestate(dic);
		
		
		attr.setFileno(fileno);
		attr.setEffective(effective.equals("") ? null : new Long(effective));
		node.setName(name.equals("") ? null : name);
		node.setVisible(visible.equals("1") ? true : false);
		//区分区县用户类型
		node.setUsertype(user.getUserType());
		
		node.setParent(fathernode);
		node.setAttribute(attr);

		//查询数据
	    pagelist = t.findPaginationData((Node) node, currpage,
				pagesize, orderby);
		int total = t.findPaginationTotalCount((Node) node, currpage,
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
	window.queryform.fileno.value = fileno;
	window.queryform.name.value=name;
	window.queryform.self.value=self;
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

	<body>
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:2px;">
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


	<form name="form1" action="" method="post">
	        <input type = hidden name = op value="query"></input>
            <input type=hidden name=self value=0></input>
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">标题：</td>
          <td width="20%"><input name="name" type="text" class="input3" /></td>
          <td width="15%">文号:<input id="fileno" name="fileno" class="input3" type="text" value="" size="10"></td>
          <td width="15%" align="right">状态:<select name="effective">
					<option value="">请选择</option>
					<option value="0">失效</option>
					<option value="1">正常</option>
					<option value="2">部分失效</option>
					<option value="3">废止</option>
					
				</select></td>
          <td width="20%"> <INPUT class = "submit_2" id=doSearch type=submit value=搜索></INPUT></td>
          <td width="15%">&nbsp;</td>
    	</tr>
		 
            
          
          
</form>

	<FORM id=searchForm name="searchForm" action=law_query.jsp onSubmit="return jiancha()">
	        <input type = hidden name = nodeid value="<%=nodeid%>"></input>
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">全文检索：</td>
          <td width="25%"><input name="searchWord" type="text" class="input1" /></td>
          <td width="10%">&nbsp;</td>
          <td width="15%" align="right">&nbsp;</td>
          <td width="20%"> <INPUT class = "submit_2" id=doSearch type=submit value=搜索></INPUT></td>
          <td width="15%">&nbsp;</td>
    	</tr>
		
         <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                  </tr>	
	   	</table>   
          
          
</form>
</table>

	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询列表</td>
                    </tr>
                </table></td>
              </tr>
 </table>


		
		
		<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="tb" >
			<tr class="title_font">
				
				<td align="center" width=30% bgcolor="#C7E2F8"><span class="out">
					文号
				</span></td>
				<td align="center" width=55% bgcolor="#C7E2F8"><span class="out">
					标题
				</span></td>			
				<td align="center" width=15% bgcolor="#C7E2F8"><span class="out">
					状态
				</span></td>
				
			</tr>

			<%
			int n = 0;
			PolicyNode pn1 = new PolicyNode();
			%>
			<c:forEach var="aBean" items="${pagelist}" varStatus="status">
											<%
												n++;
												pn1 = (PolicyNode)pagelist.get(n-1);
											%><c:choose>
											<c:when test="${aBean.attribute.effective == '1'}">
											<tr align="left" onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'  bgcolor="<%=n != n / 2 * 2 ? "#ebf3f6" : "#ebf3f6"%>">
												</c:when>
												</c:choose>
												<c:choose>
														<c:when test="${aBean.attribute.effective == '3'}">
											<tr align="left" onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'  bgcolor="<%=n != n / 2 * 2 ? "#ebf3f6" : "#ebf3f6"%>">
												</c:when>
												</c:choose>
												<c:choose>
														<c:when test="${aBean.attribute.effective == '0'}">
											<tr align="left" onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'  bgcolor="<%=n != n / 2 * 2 ? "#ebf3f6" : "#ebf3f6"%>">
												</c:when>
												</c:choose>
												<c:choose>
												<c:when test="${aBean.attribute.effective == '2'}">
											<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'  bgcolor="<%=n != n / 2 * 2 ? "#ebf3f6" : "#ebf3f6"%>">
												</c:when>
												</c:choose>
												<td height="30" align="left" valign="middle">
													&nbsp;
													<c:choose>
														<c:when test="${aBean.attribute.effective == '1'}">
															<font color="black"><c:out value="${aBean.attribute.fileno}" /></font>
														</c:when>
														<c:when test="${aBean.attribute.effective == '2'}">
															<font color="blue"><c:out value="${aBean.attribute.fileno}" /></font>
														</c:when>
														<c:when test="${aBean.attribute.effective == '0'}">
															<font color="red"><c:out value="${aBean.attribute.fileno}" /></font>
														</c:when>
														<c:when test="${aBean.attribute.effective == '3'}">
															<font color="red"><c:out value="${aBean.attribute.fileno}" /></font>
														</c:when>
													</c:choose>
													
												</td>



												<td  class='num_font'  valign="middle" class="position01" <c:if test="${aBean.attribute.remark!=null}">onMouseMove="javescript:disp_s('<%=n %>');" onMouseOut="javescript:disp_n('<%=n %>');"</c:if>	>
												<a href="law_file.jsp?fileid=<%=pn1.getAttribute().getHtmlFile() %>&filename=<%=pn1.getName() %>&selectitem=<%=pn1.getId() %>" title="" >
													<span class="">
															<font
															color='<c:choose><c:when test="${aBean.attribute.effective == '0'}">red</c:when><c:when test="${aBean.attribute.effective == '3'}">red</c:when><c:when test="${aBean.attribute.effective == '2'}">blue</c:when><c:otherwise>black</c:otherwise></c:choose>'>
																<c:set value="${aBean.name}" target="${aBean}"
																	property="shortName" /> <c:out
																	value="${aBean.shortName}" /> </font> </span> </a>		
												<c:if test="${aBean.attribute.remark!=null}">																				
													<div id="alt<%=n %>" style="display:none;" class="alt" 
														onMouseOver="javescript:disp_s('<%=n %>');" onMouseOut="javescript:disp_n('<%=n %>');">
														<c:out value="${aBean.attribute.remark}" />
													</div>
												</c:if>
												</td>
	
												
											<td class='num_font' align="center" valign="middle"  >
													<c:choose>
														<c:when test="${aBean.attribute.effective == '1'}">
															<font color="black">正常</font>
														</c:when>
														<c:when test="${aBean.attribute.effective == '2'}">
															<font color="blue">部分失效</font>
														</c:when>
														<c:when test="${aBean.attribute.effective == '0'}">
															<font color="red">失效</font>
														</c:when>
														<c:when test="${aBean.attribute.effective == '3'}">
															<font color="red">废止</font>
														</c:when>
													</c:choose>
												</td>
											</tr>
										</c:forEach>
											<c:if test="${empty pagelist}">
										
											<tr bgcolor="#FFFFFF"%>

												<td colspan="7" height="20" align="left">
												<%if(!nodeid.equals("100")) {%>
													<font color="#FF0000">&nbsp;&nbsp;&nbsp;没有符合条件的查询结果！</font>
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
          		<form name="queryform" action="policy_list_view.jsp" method="post">
						<input type="hidden" name="op" value="query"/>
						<input type="hidden" name="nodeid" value="<%=nodeid%>"></input>
						<input type="hidden" name="name" value="<%=name%>"></input>
						<input type="hidden" name="status" value="<%=status%>"></input>
						<input type="hidden" name="visible" value="1"></input>
						<input type="hidden" name="cname" value="<%=cname%>"></input>
						<input type="hidden" name="orderby" value="<%=orderby%>"></input>
						<input type="hidden" name="fileno" value="<%=fileno%>"></input>
					<input type="hidden" name="self" value="<%=self%>"></input>
					<input type="hidden" name="orderby" value="<%=orderby%>"></input>
					
					
						<c:if test="${pagelist!= null}">
							<elile:navigateBar navigateform="navigateform"
								actionName="policy_list_view.jsp" formName="queryform" />
						</c:if>
					</form>
          </div></td>
        </tr>
      </table>
		
		
		
		

	</body>
</html>

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
</SCRIPT>