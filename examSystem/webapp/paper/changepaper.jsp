<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String sjid = request.getParameter("sjid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>试卷修改</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script language="javascript">
function autoload(){
	var sjid = "<%=sjid%>";
		$("#sjid").val(sjid);
		$.ajax({
			type : 'post',
			async : false,
			url : 'changePaper.action?sjid=' +<%=sjid%>,
			success : function(result) {
				var data = eval(result);
				$("#sjmc").val(data[0][0]);
				$("#sjzf").val(data[0][1]);
				$("#bhgfs").val(data[0][2]);
				$("#djsx").val(data[0][3]);
				$("#kksj").val(data[0][4]);
				$("#yxqjzsj").val(data[0][5]);
				$("#zt").val(data[0][6]);
				$("#sj_kslx").val(data[0][7]);
				$("#jjsj").val(data[0][8]);
				$("#ljcf").val(data[0][9]);
				if (data[0][6] == "0") {
					$("#zt").val("未考试");
				} else if (data[0][6] == "1") {
					$("#zt").val("考试中");
				} else if (data[0][6] == "2") {
					$("#zt").val("已结束");
				} else if (data[0][6] == "6") {
					$("#zt").val("审核通过");
				}
				if (data[0][7] == "1") {
					$("#sj_kslx").val("鉴定类考试");
				} else if (data[0][7] == "2") {
					$("#sj_kslx").val("其他类型考试");
				}
				if (data[0][8] == "30") {
					$("#jjsj").val("30");
				} else if (data[0][8] == "45") {
					$("#jjsj").val("45");
				} else if (data[0][8] == "60") {
					$("#jjsj").val("60");
				}else if (data[0][8] == "10") {
					$("#jjsj").val("10");
				}

				if (data[0][9] == "1") {
					$("#jjsj").val("是");
				} else if (data[0][8] == "0") {
					$("#jjsj").val("否");
				}

			},
			error : function() {
				alert("试卷信息加载失败！");
			}
		});
	}
	function checkSubmit() {
		var sjmc = $("#sjmc").val();
		var sjzf = $("#sjzf").val();
		var bhgfs = $("#bhgfs").val();
		var djsx = $("#djsx").val();
		var kksj = $("#kksj").val();
		var yxqjzsj = $("#yxqjzsj").val();
		var zt = $("#zt").val();
		var sj_kslx = $("#sj_kslx").val();
		var jjsj = $("#jjsj").val();
		var ljcf = $("#ljcf").val();
		if (sjmc == "") {
			alert("请填写试卷名称");
			return;
		}
		if (sjzf == "") {
			alert("请填写试卷总分");
			return;
		}
		if (bhgfs == "") {
			alert("请填写试卷不合格分数");
			return;
		}
		if (djsx == "") {
			alert("请填写试卷答卷时限");
			return;
		}
		if (kksj == "") {
			alert("请选择试卷的开考时间");
			return;
		}
		if (zt == "") {
			alert("请选择试卷状态");
			return;
		}
		if (sj_kslx == "") {
			alert("请选择试卷开考类型");
			return;
		}
		if (jjsj == "") {
			alert("请选择试卷交卷时间");
			return;
		}
		if (ljcf == "") {
			alert("请选择试卷是否立即出分");
			return;
		}
		if (yxqjzsj == "") {
			alert("请选择试卷有效日期");
			return;
		}
		$.ajax({
			type : 'post',
			url : 'updatePaper.action',
			data : $('#paperform').serialize(),
			dataType : 'text',
			success : function(result) {
				var re = result;
				if (re == "success") {
					alert("修改成功！");
					window.opener.location.reload();
					window.close();
					
				} else if (re == "fail") {
					alert("考生已经开始答题，不可修改考试信息");
				}
			},
			error : function() {
				alert("程序出错！");
			}

		});

	}
	function clearTime() {
		var sjid = $("#sjid").val();
		var zt = $("#zt").val();

		if (zt != "1") {
			alert("考试状态非考试中，不可清除考试时间");
			return;
		} else if (zt == "1") {
			$.ajax({
				type : 'post',
				async : false,
				url : 'clearTime.action?sjid=' + sjid,
				success : function(result) {
					var re = result;
					if (re == "success") {
						alert("清除成功");
					} else if (re == "fail") {
						alert("已有考生开始答题 ，不可清除考试时间");
					}
				},
				error : function() {
					alert("清除失败！");
				}

			});
		}
	}
	window.onload = autoload;
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left">
				<table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">
							<%
								out.print("试卷信息修改");
							%>
						</td>
						<td class="header3"></td>
					</tr>
				</table>
			</td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<form id="paperform" name="paperform">
						<!-- 误删 -->
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr class="row_height">
								<td height="26" colspan="8" align="center">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablistys">
										<tr class="row_height">
											<td width="10%" align="right">试卷名称：</td>
											<td width="20%">
												<input type="text" name="sjmc" value="" id="sjmc" class="input1" readonly="true" style="float: left;width: 150px"> 
												<input type=hidden name="sjid" id="sjid" value=""> 
												<font color="#FF0000" style="float: left;">*</font>
											</td>
											<td width="10%" align="right">试卷总分：</td>
											<td width="20%">
												<input type="text" name="sjzf" value="" id="sjzf" class="input1" style="float: left;width: 150px" />
												<font color="#FF0000" style="float: left;">*</font>
											</td>
											<td width="10%" align="right">答卷时限：</td>
											<td width="20%">
												<input type="text" name="djsx" value="" id="djsx" class="input1" style="float: left; width: 70px" />
												<font color="#FF0000" style="float: left;">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">试卷状态：</td>
											<td><select name="zt" id="zt" style="width:90px">
													<option value="">请选择</option>
													<option value="0">未考试</option>
													<option value="1">考试中</option>
													<option value="2">已结束</option>
													<option value="6">审核通过</option>

											</select></td>

											<td align="right">及格分数：</td>
											<td><input type="text" name="bhgfs" value="" id="bhgfs" class="input1" style="float: left;width: 150px" /> <font color="#FF0000" style="float: left;">*</font></td>
											<td align="right">开考后：</td>
											<td><select name="jjsj" id="jjsj" style="width:70px">
													<option value="10">10</option>
													<option value="30">30</option>
													<option value="45">45</option>
													<option value="60">60</option>
											</select> <font>分钟可交卷</font></td>

										</tr>
										<tr class="row_height">
											<td align="right">开考时间：</td>
											<td>
												<input type="text" id="kksj" name="kksj" onclick="WdatePicker()" class="Wdate" style="width:150px" />
												<font color="#FF0000">*</font>
											</td>
											<td align="right">有效期至：</td>
											<td>
												<input type="text" id="yxqjzsj" name="yxqjzsj" onclick="WdatePicker()" class="Wdate" style="width:150px" />
												<font color="#FF0000">*</font>
											</td>
											<td align="right">即显分数：</td>
											<td><select name="ljcf" id="ljcf" style="width:70px">
													<option value="1">是</option>
													<option value="0">否</option>
											</select></td>
										</tr>
										<tr class="row_height">
											<td align="right">考试类型：</td>
											<td>
												<select id="sj_kslx" name="sj_kslx" style="width: 100px">
													<option value="">请选择</option>
													<option value="1">鉴定类考试</option>
													<option value="2">其他类考试</option>
												</select>
											</td>
											<td>&nbsp;</td>
											<td align="right">
												<a href="javascript:void(0)" class="infocount" onclick="clearTime()" style="color: green">清除考试时间</a>
											</td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr height="6px">
								<td colspan="6"></td>
							</tr>
							<tr class="row_height">
								<td rowspan="9">
									<div>
										<div style="margin-left: 400px;float: left; ">
											<input name="button" type="button" class="submit_2" onClick="javascript:checkSubmit();" value="提交" />
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</form>
				</div></td>
		</tr>
	</table>
</body>
</html>