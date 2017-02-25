<%@page import="com.wondersgroup.kaoshi.bo.Tkcategory"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	ProfessionBean professionBean = new ProfessionBean();
	List<Tkcategory> list = professionBean.getYjTkcategories();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>题库类别维护</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
	function checkCategory(v){
		var v = v.value;
		if(v==2){
			$("#yjtk").removeAttr("disabled")
		}else{
			$("#yjtk").val("0");
			$("#yjtk").attr("disabled",true);
		}
	}
	
	function sbt(){
		var tkzl = $("#tkzl").val();
		var yjtk = $("#yjtk").val();
		var tkmc = $("#tkmc").val();
		var remark = $("#remark").val();
		
		if(tkzl==2&&yjtk==0){
			alert("请选择一级题库！");
			return;
		}
		if(tkmc==""){
			alert("请填写题库名称！");
			return;
		}
		var url = 'tkCategoryAdd.action?yjtk='+yjtk+'&tkmc='+tkmc+'&remark='+remark;
		url = encodeURI(encodeURI(url));
		
		$.ajax({
			type: 'post',
			url: url,
			success:function(result){
				alert(result);
				window.location.reload();
			},
			error:function(){
				alert("新增失败！");
			}
		});
	}
</script>
</head>

<body class="nrbj">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
		<tr>
			<td colspan="2" valign="top">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<form>
					<tr>
						<td height="6" colspan="6"></td>
					</tr>
					<tr>
						<td width="10%" height="28" align="right">题库种类：</td>
						<td width="15%" align="left">
							<select id="tkzl" name="tkzl" style="width: 150px;" onchange="checkCategory(this)">
								<option value="1" selected="selected">一级题库</option>
								<option value="2">二级题库</option>
							</select>
						</td>
						<td width="10%" align="right">一级题库：</td>
						<td width="15%">
							<select id="yjtk" name="yjtk" style="width: 150px;" onchange="" disabled="disabled" >
								<option value="0">请选择</option>
								<%
									for(int i=0;i<list.size();i++){
										Tkcategory t = list.get(i);
										out.print("<option value="+t.getId()+">"+t.getName()+"</option>");
									}
								%>
							</select>
						</td>
						<td width="10%" align="right">题库名称：</td>
						<td width="15%">
							<input id="tkmc" name="tkmc" style="width: 150px;"/>
						</td>
						<td width="10%" align="right">备注：</td>
						<td width="18%">
							<input id="remark" name="remark" style="width: 100px;"/>
						</td>
					</tr>
					<tr>
						<td height="20" colspan="6"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td align="center"><button class="submit_2" type="button" onclick="sbt()" >确定</button> </td>
						<td align="center"><button class="submit_2" type="reset">重置</button></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					</form>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
