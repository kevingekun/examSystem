<%@ page import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tkcategory"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	ProfessionBean p = new ProfessionBean();
	List<Tkcategory> list = p.getAllLeafcategories();
	List<Tkcategory> list2 = p.getYjTkcategories();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>题库维护</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
	/* .form-group{
		padding:0px;
	} */
</style>
</head>
<body>
<div class="container-fluid">
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">题库维护</a></li>
		<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">题库类别维护</a></li>
		<li role="presentation"><a href="#profile2" aria-controls="profile2" role="tab" data-toggle="tab">工种维护</a></li>
	</ul>
	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active panel" id="home">
			<div class="row form-inline panel-body" style="margin-right:0px">
				<div class="col-md-3 form-group">
					<label for="tkCategory">题库类别</label>
					<select class="form-control input-sm" id="tkCategory" name="tkCategory" onchange="ctgyChange(this)" style="width:160px;">
						<%
							for(int i=0;i<list.size();i++){
								Tkcategory t = list.get(i);
								out.print("<option value="+t.getId()+">"+t.getName()+"</option>");
							}
						%>
					</select>
				</div>
				<div class="col-md-2 form-group">
					<label for="companyForShort">题库简称</label>
					<input class="form-control input-sm" type="text" title="会拼接到工种名称前面，用于区分日常鉴定工种" id="companyForShort" name="companyForShort" maxlength="10" disabled="disabled" style="width:90px;">
				</div>
				<div class="col-md-2 form-group">
					<label for="companyBriefCode">题库简码</label>
					<input class="form-control input-sm" type="text" title="会拼接到工种编号前面，用于区分日常鉴定工种" id="companyBriefCode" name="companyBriefCode" maxlength="10" disabled="disabled" style="width:90px;">
				</div>
				<div class="col-md-2 form-group">
					<label for="gzmc">工种名称</label>
					<input class="form-control input-sm" id="gzmc" name="gz" onblur="checkGz()" style="width:90px;">
				</div>
				<div class="col-md-2 form-group">
					<label for="dj">等级</label>
					<select class="form-control input-sm" id="dj" name="grade"  onchange="checkDj()">
						<option value="0">请选择</option>
						<option value="一级">一级</option>
						<option value="二级">二级</option>
						<option value="三级">三级</option>
						<option value="四级">四级</option>
						<option value="五级">五级</option>
						<option value="专项">专项</option>
					</select>
				</div>
				<div class="col-md-1 form-group" style="padding-left:0px;">
					<button name="add" type="button" class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="bottom" title="此按钮仅用于整套试题录入，不需要添加鉴定要素的情况下新增题库" onClick="tkAdd()">确 定</button>
				</div>
			</div>
			<div class="panel-body">
				<iframe id="Bottomjdys" name="Bottomjdys" src="question/testLibrary_bottom.jsp" width="100%" height="310px"></iframe>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane panel" id="profile">
			<form>
				<div class="row form-inline panel-body" style="margin-right:0px">
					<div class="col-md-3 form-group">
						<label for="tkzl">题库种类</label>
						<select class="form-control input-sm" id="tkzl" name="tkzl" onchange="checkCategory(this)">
							<option value="1" selected="selected">一级题库</option>
							<option value="2">二级题库</option>
						</select>
					</div>
					<div class="col-md-3 form-group">
						<label for="yjtk">一级题库</label>
						<select class="form-control input-sm" id="yjtk" name="yjtk" onchange="" disabled="disabled" >
							<option value="0">请选择</option>
							<%
								for(int i=0;i<list2.size();i++){
									Tkcategory t = list2.get(i);
									out.print("<option value="+t.getId()+">"+t.getName()+"</option>");
								}
							%>
						</select>
					</div>
					<div class="col-md-3 form-group">
						<label for="tkmc">题库名称</label>
						<input class="form-control input-sm" id="tkmc" name="tkmc"/>
					</div>
					<div class="col-md-3 form-group">
						<label for="remark">备注</label>
						<input class="form-control input-sm" id="remark" name="remark"/>
					</div>
				</div>
				<div class="row" style="margin-top:20px;margin-right:0px">
					<div class="col-md-1 col-md-offset-4">
						<button id="sbt1" class="btn btn-primary btn-sm" type="button" onclick="sbt()" >确定</button>
					</div>
					<div class="col-md-1 col-md-offset-1">
						<button class="btn btn-warning btn-sm" type="reset">重置</button>
					</div>
				</div>
			</form>
		</div>
		<div role="tabpanel" class="tab-pane panel" id="profile2">
			<form>
				<div class="row form-inline panel-body" style="margin-right:0px">
					<div class="col-md-3 col-md-offset-3 form-group">
						<label for="gzmc2">工种名称</label>
						<input class="form-control input-sm" id="gzmc2" name="gzmc" />
					</div>
					<div class="col-md-4 form-group">
						<label for="gzid">工种编号(7位数字)</label>
						<input class="form-control input-sm" type="number" id="gzid" name="gzid" />
					</div>
				</div>
				<div class="row" style="margin-top:20px;margin-right:0px">
					<div class="col-md-1 col-md-offset-4">
						<button id="sbt2" class="btn btn-primary btn-sm" type="button" onclick="sbtT()" >确定</button>
					</div>
					<div class="col-md-1 col-md-offset-1">
						<button class="btn btn-warning btn-sm" type="reset">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
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
			Bottomjdys.location=url;
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
					Bottomjdys.location=url;
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
$(function () {
	$('[data-toggle="tooltip"]').tooltip();
});
function checkCategory(v){
	var v = v.value;
	if(v==2){
		$("#yjtk").removeAttr("disabled");
	}else{
		$("#yjtk").val("0");
		$("#yjtk").attr("disabled",true);
	}
}

function sbt(){
	$("#sbt1").attr("disabled","disabled");
	var tkzl = $("#tkzl").val();
	var yjtk = $("#yjtk").val();
	var tkmc = $("#tkmc").val();
	var remark = $("#remark").val();
	
	if(tkzl==2&&yjtk==0){
		alert("请选择一级题库！");
		$("#sbt1").removeAttr("disabled");
		return;
	}
	if(tkmc==""){
		alert("请填写题库名称！");
		$("#sbt1").removeAttr("disabled");
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
			$("#sbt1").removeAttr("disabled");
		}
	});
}
function sbtT(){
	$("#sbt2").attr("disabled","disabled");
	var gzmc = $("#gzmc2").val();
	var gzid = $("#gzid").val();

	gzmc = gzmc.replace(/\s/g, "");
	gzid = gzid.replace(/\s/g, "");
	if (gzmc == "") {
		alert("请填写工种名称！");
		$("#sbt2").removeAttr("disabled");
		return;
	}
	if (gzid == "") {
		alert("请填写工种编号！");
		$("#sbt2").removeAttr("disabled");
		return;
	} else if (gzid.length != 7) {
		alert("工种编号应该为7位！");
		$("#sbt2").removeAttr("disabled");
		return;
	}
	var state = true;

	var url = 'gzCheck.action?gzmc=' + gzmc + '&gzid=' + gzid;
	url = encodeURI(encodeURI(url));
	$.ajax({
		type : 'post',
		async : false,
		url : url,
		success : function(result) {
			if (result == '01') {
				alert("工种编号已存在，请修改！");
				$("#sbt2").removeAttr("disabled");
				state = false;
				return;
			} else if (result == '02') {
				alert("工种名称已存在，请修改！");
				$("#sbt2").removeAttr("disabled");
				state = false;
				return;
			} else if (result == '012') {
				alert("工种编号和工种名称均已存在！");
				$("#sbt2").removeAttr("disabled");
				state = false;
				return;
			}
		},
		error : function() {
			alert("系统出错");
		}
	});
	if (state) {
		var url2 = 'gzAdd.action?gzmc=' + gzmc + '&gzid=' + gzid;
		url2 = encodeURI(encodeURI(url2));
		$.ajax({
			type : 'post',
			url : url2,
			success : function(result) {
				if (result == 'success') {
					alert("新增成功！");
					window.location.reload();
				} else {
					alert("新增失败！");
					$("#sbt2").removeAttr("disabled");
				}
			},
			error : function() {
				alert("系统出错！");
				$("#sbt2").removeAttr("disabled");
			}
		});
	}
}
</script>
</body>
</html>
