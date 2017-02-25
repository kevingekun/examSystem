<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tkcategory"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	ProfessionBean p = new ProfessionBean();
	List<Tkcategory> list = p.getAllLeafcategories();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>鉴定要素top</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.11.3.min.js"></script>
</head>
<script>
	var gzid,gzdj;
	var ret = false;//工种名称是否正确
	var state = 0;//鉴定要素是否已经存在，0不存在，1存在
	function checkGz(){
		var jobname = document.getElementById("gzmc").value;
		if(jobname==''){
			alert("请输入工种名称");
			ret = false;
			return ret;
		}
		jobname = encodeURI(encodeURI(jobname));
		$.ajax({
			type:'post',
			async:false,
			url:'findProfessionByName.action?jobname='+jobname,
			success:function(result){
				if(result=='0'){
					alert("工种不存在");
					ret =  false;
				}else{
					//document.getElementById("id_job").value=result;
					gzid = result;
					ret =  true;
				}
			},
			error:function(){
				alert("error");
				ret =  false;
			}
		});
		return ret;
	}
	function checkDj(){
		if(ret==true){
			var jobname = document.getElementById("gzmc").value;
			var dj = document.getElementById("dj").value;
			var cpyFS = $("#companyForShort").val();
			var tkCat = $("#tkCategory").val();
			var cpyBC = $("#companyBriefCode").val();
			jobname = encodeURI(encodeURI(jobname));
			dj = encodeURI(encodeURI(dj));
			cpyFS = encodeURI(encodeURI(cpyFS));
			if(tkCat=='1'||tkCat=='2'||tkCat=='3'||tkCat=='5'){
			}else{
				if(cpyFS==''){
					alert("请填写题库简称！");
					$("#dj").val("0");
					return ;
				}
				if(cpyBC==''){
					alert("请填写题库简码！");
					$("#dj").val("0");
					return ;
				}
			}
			if(dj=='0'){
				var url = '<%=request.getContextPath()%>/question/testLibrary_bottom.jsp?gzid='+gzid+'&gzdj='+dj+'&gzmc='+jobname+'&state='+state+'&companyForShort='+cpyFS+'&tkCategory='+tkCat+'&companyBriefCode='+cpyBC;
				parent.Bottomjdys.location=url;
				alert("请选择等级");
			}else{
				$.ajax({
					type:'post',
					async:false,
					url:'findTkByjobnameAndDj.action?jobname='+jobname+'&dj='+dj+'&companyForShort='+cpyFS,
					success:function(result){
						if(result=='1'){
							state = 1;//鉴定要素存在
						}else{
							state = 0;//鉴定要素不存在
						}
						var url = '<%=request.getContextPath()%>/question/testLibrary_bottom.jsp?gzid='+gzid+'&gzdj='+dj+'&gzmc='+jobname+'&state='+state+'&companyForShort='+cpyFS+'&tkCategory='+tkCat+'&companyBriefCode='+cpyBC;
						//var url = encodeURI(encodeURI(u));//jobname和dj已经转码
						parent.Bottomjdys.location=url;
					},
					error:function(){
						alert("程序出错！");
						state = 0;
					}
				});
			}
			
		}else{
			alert("您输入的工种名称有误！");
		}
	}
	function tkAdd(){
		var jobname = document.getElementById("gzmc").value;
		var dj = document.getElementById("dj").value;
		var cpyFS = $("#companyForShort").val();
		var tkCat = $("#tkCategory").val();
		var cpyBC = $("#companyBriefCode").val();
		if(jobname==''||dj=='0'){
			alert("工种名称或等级有误");
			return false;
		}
		if(tkCat=='1'||tkCat=='2'||tkCat=='3'||tkCat=='5'){
		}else{
			if(cpyFS==''){
				alert("请填写题库简称！");
				return false;
			}
			if(cpyBC==''){
				alert("请填写题库简码！");
				return false;
			}
		}
		jobname = encodeURI(encodeURI(jobname));
		dj = encodeURI(encodeURI(dj));
		cpyFS = encodeURI(encodeURI(cpyFS));
		$.ajax({
			type:'post',
			async:false,
			url:'tkAdd.action?gzmc='+jobname+'&dj='+dj+'&id_job='+gzid+'&tkCategory='+tkCat+'&companyForShort='+cpyFS+'&companyBriefCode='+cpyBC,
			success:function(result){
				if(result=='success'){
					alert("添加成功！");
				}else if(result=='repeat'){
					alert("已经存在！");
				}else{
					alert("添加失败！");
				}
			},
			error:function(){
				alert("程序出错！");
			}
		});
	}
	function ctgyChange(v){
		var cgyValue=v.value;
		if(cgyValue=='1'||cgyValue=='2'||cgyValue=='3'||cgyValue=='5'){
			$("#companyForShort").val("");
			$("#companyBriefCode").val("");
			$("#companyForShort").attr("disabled",true);
			$("#companyBriefCode").attr("disabled",true);
		}else{
			$("#companyForShort").val("");
			$("#companyBriefCode").val("");
			$("#companyForShort").attr("disabled",false);
			$("#companyBriefCode").attr("disabled",false);
		}
	}
	$(function(){
		$("#tkCategory").val("5");
	});
</script>
<body bgcolor="#ebf3f6">
	<div>
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="8" colspan="4"></td>
			</tr>
			<tr>
				<input id="id_job" type="hidden" />
				<td align="right">题库类别：</td>
				<td>
					<select id="tkCategory" name="tkCategory" onchange="ctgyChange(this)">
						<%
							for(int i=0;i<list.size();i++){
								Tkcategory t = list.get(i);
								out.print("<option value="+t.getId()+">"+t.getName()+"</option>");
							}
						%>
					</select>
				</td>
				<td align="right"><a id="qyjc">题库简称：</a></td>
				<td>
					<input type="text" id="companyForShort" name="companyForShort" maxlength="10" disabled="disabled">
				</td>
				<td align="right"><a id="qyjm">题库简码：</a></td>
				<td>
					<input type="text" id="companyBriefCode" name="companyBriefCode" maxlength="10" disabled="disabled">
				</td>
				<td height="28" align="right">工种名称：</td>
				<td align="left"><input id="gzmc" name="gz" onblur="checkGz()"></input></td>
				<td align="right">等级：</td>
				<td>
					<select id="dj" name="grade" style="width: 80px;" onchange="checkDj()">
						<option value="0">请选择</option>
						<option value="一级">一级</option>
						<option value="二级">二级</option>
						<option value="三级">三级</option>
						<option value="四级">四级</option>
						<option value="五级">五级</option>
						<option value="专项">专项</option>
					</select>
				</td>
				<td align="center">
					<input name="add" type=button class="submit_2" onClick="tkAdd()" value="确  定" />
				</td>
			</tr>

		</table>
	</div>
</body>
</html>
