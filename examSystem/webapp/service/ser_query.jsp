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

	
<script language="JavaScript" type="text/JavaScript" src="../js/dateMy97/WdatePicker.js"></script>
<link href="../inc/indexing.css" rel="stylesheet">
<%@page import ="com.wondersgroup.falcon.lucene.HTMLSeacher" %>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*"%>
<%@ page import="com.wondersgroup.falcon.model.dic.*"%>
<%@ page import="com.wondersgroup.falcon.model.auth.*"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.archives.*"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%> 
	<link rel="stylesheet" type="text/css" href="../css/main.css"/>
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../inc/all.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="images/menu.js">
		function jiancha(){
		if(document.searchForm.searchWord.value==null||document.searchForm.searchWord.value==""){
			return false
		}
		return true
	}
	function change(shu){
		var tagfont=document.all.tags("div");
		if (tagfont!=null)
		{
			for(j=0; j<tagfont.length; j++)
			{   
				if(shu==1){
					tagfont[j].style.fontSize="16pt";
				}
				if(shu==2){
					tagfont[j].style.fontSize="12pt";
				}
				if(shu==3){
					tagfont[j].style.fontSize="10pt";
				}
			}
		}
	}	
		</script>

	<%
   		request.setCharacterEncoding("GBK");
	String nodeid= request.getParameter("nodeid");	
	if(nodeid==null)nodeid="";
	String queryStr= request.getParameter("searchWord");
	System.out.println("queryStr:"+queryStr);
	User user = LoginValidateUtils.getUser(request);
	int dang=1;
	if(request.getParameter("dang")!=null)
	{
		dang=Integer.parseInt(request.getParameter("dang"));
	}
	
	PropertiesUtil pu =new PropertiesUtil();
	
	//String realpath = this.getServletContext().getRealPath("\\");
	//String realpath ="D:\\eclipse\\workspace\\falcon\\webapp";
	com.wondersgroup.falcon.Util.PropertiesUtil u = new com.wondersgroup.falcon.Util.PropertiesUtil();
	String realpath = u.getProperties("path.index");
	HTMLSeacher txtfilesearcher=new HTMLSeacher();
   	txtfilesearcher.setQueryStr(queryStr);
	txtfilesearcher.setYe(dang);
   	txtfilesearcher.make("service",realpath,user.getUserType().toString());
   	int zongye=(int)Math.ceil(txtfilesearcher.getJilushu()/10.0);
   	
  // 	System.out.println("zongye=-==>"+zongye);
	%>
	
<SCRIPT language=JavaScript>
	
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
	<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">全文搜索</td>
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
                      <td  class="header8">查询条件 <a style="font-size:12px" href=# onclick="history.back(1)">后退</a>  <a style="font-size:12px" href="ser_list.jsp" >回到搜索页</a> </td>
                    </tr>
                </table></td>
              </tr>
            </table>

	<FORM id=searchForm name="searchForm" action=ser_query.jsp onsubmit="return jiancha()">
	        <input type = hidden name = nodeid value="<%=nodeid%>"></input>
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="56" align="right">全文检索：</td>
          <td width="20%"><input name="searchWord" type="text" value=<%=queryStr %> class="input3" ></input></td>
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right"></td>
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
<br>

		
		
	约有
						<b><%=txtfilesearcher.getJilushu()%></b>	
						项符合&nbsp;<b><%=queryStr%></b>
						的查询结果,以下是第<b><%=(dang-1)*10+1%>-<%=dang*10<=txtfilesearcher.getJilushu()?dang*10:txtfilesearcher.getJilushu()%></b>项&nbsp;
					    (搜索用时&nbsp;<%=txtfilesearcher.getSearchtime()/1000%>&nbsp;秒)<hr>
					    
						<%
							if(txtfilesearcher.getList().size()==0){
								out.print("找不到和您的查询 ");
						%>
								<font color=red><%=queryStr%></font> 
						<% 			
								out.print("相符的网页。");%><br><br><%
								out.print(" 建议：");%><br><br><%
								%>&nbsp;<%out.print("请检查输入字词有无错误。");%><br><%
								%>&nbsp;<%out.print("请换用另外的查询字词。");%><br><%
								%>&nbsp;<%out.print("请改用较常见的字词。");%><br><%
								%>&nbsp;<%out.print("请换用简洁的字词。");%><br><%
							}else{
						 		for(int i=0;i<txtfilesearcher.getList().size();i++){
						 		ServiceNode fathernode = null;
						 		String idd = txtfilesearcher.getList().get(i).toString().substring(txtfilesearcher.getList().get(i).toString().lastIndexOf("/")+1,txtfilesearcher.getList().get(i).toString().lastIndexOf(".")); 
						 		System.out.print("dii============>"+idd);
						 		AbstractTree t = FactoryBean.creator("service");
											fathernode = (ServiceNode)t.getNodeById(Node.getNodeType("service"),new Long(idd));
											
							%> 
								<table  width=80% border=0>
									<tr>
										
										<td>
											<a href="ser_file.jsp?fileid=<%=txtfilesearcher.getList().get(i).toString().substring(txtfilesearcher.getList().get(i).toString().lastIndexOf("/")+1,txtfilesearcher.getList().get(i).toString().length()) %>&filename=<%=txtfilesearcher.getList3().get(i).toString() %>&selectitem=<%=txtfilesearcher.getList().get(i).toString().substring(txtfilesearcher.getList().get(i).toString().lastIndexOf("/")+1,txtfilesearcher.getList().get(i).toString().lastIndexOf(".")) %>" title="" ><font color="#FF712D"><%=fathernode.getName().toString() %></font></a>
										
										</td>
									</tr>
									<tr>
									
									<td style="line-height:18px" align=left>
								
							<%
									String print=(String)txtfilesearcher.getList2().get(i);
									if(print==null){
							 		 		out.println("没有最佳匹配的记录。");
							 		}else{
							 		 		out.println("<font style='font-size:10.5pt;font-family:arial;'>"+print+"...</font>");
							 		}
							 %>
							 		</td></tr></table><br><br>
							 <%	 
						 		}
						 	%>	
						 	<br>
								<center>
									<%
										if(dang>1){
									%>
											<A HREF="ser_query.jsp?dang=<%=dang-1%>&searchWord=<%=queryStr%>" class="fa">
									<%
											out.print("上一页");	
									%></A>
									<%
										}
										if((dang-11)>=0){
											if(dang+9<=zongye+1){
												for(int j=dang-10;j<dang+9;j++){
										%>
												<A <%if(j==dang)out.print("style='color:#CC0000'");%> HREF="ser_query.jsp?dang=<%=j%>&searchWord=<%=queryStr%>">
										<%
												out.print(j);	
										%></A>
										<%				
												}		
											}else{
												for(int j=dang-10;j<=zongye;j++){
										%>
												<A <%if(j==dang)out.print("style='color:#CC0000'");%> HREF="ser_query.jsp?dang=<%=j%>&searchWord=<%=queryStr%>">
										<%
												out.print(j);	
										%></A>
										<%				
												}		
											}
										}else{
										    if(dang+9<=zongye+1){
												for(int j=1;j<dang+9;j++){
										 %>
												<A <%if(j==dang)out.print("style='color:#CC0000'");%> HREF="ser_query.jsp?dang=<%=j%>&searchWord=<%=queryStr%>">
										 <%
												out.print(j);	
										 %></A>
										 <%				
												}					    
										    }else{
													for(int j=1;j<=zongye;j++){
										 %>
												<A <%if(j==dang)out.print("style='color:#CC0000'");%> HREF="ser_query.jsp?dang=<%=j%>&searchWord=<%=queryStr%>">
										 <%
												out.print(j);	
										 %></A>
										 <%				
												}				    
										    }
										}
									 %>
									 <%
										if(dang<zongye){
									 %>
											<A HREF="ser_query.jsp?dang=<%=dang+1%>&searchWord=<%=queryStr%>">
									 <%
											out.print("下一页");	
										}
									 %></A><br><br><br>
								</center>
						 	<%
					 		}
					%>
		
		
		
		

	</body>
</html>
