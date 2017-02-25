<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>考试公告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  <%
  	String paperid=request.getParameter("paperid");
   %>
  <script>
  window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;

	if(a+b==c){
		showmore();
	}
}
function showmore(){
	document.getElementById("buttondis").style.display ="block";

}
function submitform(){
  var returnObj = new Object();
					returnObj.code = "true";
					returnObj.text = "shi";
					window.returnValue = returnObj;
 window.close();
}
  </script>
  <body>
  <form action="examAllAction.action"  name="queryform" id="queryform"  method="post">
  <input type="hidden" name="paperId" value="<%=paperid %>">
<p style="line-height: 150%;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各省、自治区、直辖市民政厅（局），新疆生产建设兵团民政局，</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">各民政行业特有工种职业技能鉴定站</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">：</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">根据年度工作安排，为做好2013年民政行业统一职业技能鉴定考试工作，现就有关事项通知如下：</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">一、鉴定职业和等级</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（一）鉴定职业。继续开展灾害信息员、孤残儿童护理员、养老护理员、假肢师、矫形器师、殡仪服务员、遗体接运工、遗体整容师、遗体防腐师、遗体火化师、墓地管理员等11个职业的全国统一鉴定。其中，假肢师、矫形器师将根据报名情况，采取相对集中、分大区统一考试的形式进行。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（二）鉴定等级。鉴定等级为五级（初级）、四级（中级）、三级（高级）、二级（技师）、一级（高级技师）。</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">各职业国家职业资格二级（技师）以上级别的鉴定由部统一组织。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各地可结合实际，研究确定本地区鉴定职业及等级。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">二、鉴定的内容</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各职业</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">五级</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">（初级）</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">、四级（</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">中级）</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">、三级</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">（高级）鉴定的内容为两部分：理论知识考试与技能操作考核。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">二级（技师）、一级（高级技师）鉴定的内容为三部分：理论知识考试、技能操作考核与综合评审。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">三、鉴定申报条件</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各职业鉴定的申报条件，均按相应的</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">《国家职业标准》执行。为加快民政职业技能鉴定与工人技术等级考核的衔接，参加地方各级民政部门与人力资源和社会保障部门联合开展的职业技能鉴定或工人技术等级考核取得的相应职业资格证书继续有效。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">四、鉴定申报程序</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（一）符合申报条件者，登录民政部“民政职业技能鉴定网”网络报名系统，网上填写打印《民政行业职业技能鉴定申报表》（一式两份），并附本人身份证、学历证书、现有《职业资格证书》（工人技术等级证书）等材料原件、复印件及本人一寸照片、两寸照片各一张（背面注明本人单位、姓名，分别为办理准考证、国家职业资格等级证书使用）。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（二）所在单位人事部门按申报条件审核上述材料原件后，汇总申报人员情况，统一向民政行业职业技能鉴定站提出申请，并提交相关报名材料复印件（需加盖单位人事部门印章）。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（三）省（自治区、直辖市）民政厅（局）人事部门指导各民政行业职业技能鉴定站，根据《民政行业职业技能鉴定实施办法（试行）》和各职业《国家职业标准》规定的条件，对申报人进行资格初审，并同时进行“职业技能鉴定考务管理系统”数据采集、录入。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（四）2012年度职业技能鉴定理论、实操考试单项成绩不合格的，可申请补考，填写《民政职业技能鉴定补考报名表》（一式两份），参加单项考试。补考仍未通过的，合格单项成绩作废；再次申报鉴定，须按正常程序办理。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（五）申报国家职业资格二级（技师）、一级（高级技师）的，由部职业技能鉴定指导中心进行资格审核。省（自治区、直辖市）民政厅（局）人事部门汇总本省情况后，报部职业技能鉴定指导中心。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">五、统一鉴定时间</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各职业</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">五级</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">（初级）</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">、四级（</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">中级）</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">、三级</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">（高级）鉴定将举办两次：</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">第一次统一理论考试时间为2013年9月28日上午9:00－</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">11:00</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">（2013年9月15日截止报名）</span><span style="line-height: 150%;font-family: 宋体;font-size: 15px">，各地须于9月29日之前组织实施技能操作考核；第二次统一理论考试时间为11月16日上午9:00-11:00（2013年10月30日截止报名），各地须于2013年11月18日之前组织实施技能操作考核。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各职业二级（技师）、一级（高级技师）鉴定时间为2013年11月12日至18日，报名截止时间为2013年10月20日。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">六、有关要求</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（一）加强考务管理。各地要统筹安排，加强协调，严格按照国家职业技能鉴定考务管理有关规定和民政行业特有职业技能鉴定工作管理制度，统一使用国家职业技能鉴定考务管理系统，认真做好公告发布、报名组织、资格初审、考场编排、准考证制作、考试组织、成绩登录、数据汇总等各项考务工作，为2013年鉴定工作顺利完成奠定基础。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（二）强化技能培训。要协调培训基地和鉴定站相互配合开展工作，根据国家职业标准，采取多种形式组织开展职业技能培训。坚持“先启动培训，后开展鉴定”的原则，凡未开展培训的职业，原则上不启动该职业的技能鉴定工作。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（三）按时申报材料。各地务必在统一考试15日前，将本地鉴定筹备情况及《民政行业职业技能鉴定工作安排和试卷申请表》、《民政行业职业技能鉴定考试报名登记表》、《民政行业职业技能鉴定考点设置情况汇总表》、《民政行业职业技能鉴定补考报名表》和《职工专业岗位工作年限证明》报部职业技能鉴定指导中心，并同时报送“职业技能鉴定网考务管理系统”报名数据。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">（四）理论考试实行集中统一阅卷。各地务必于理论知识考试结束后2日内将考生答题卡通过机要邮局寄往部职鉴中心，由阅卷机统一阅卷。</span>
</p>
<p style="line-height: 150%;text-indent: 31px;margin: 5px 0 0">
    <span style="line-height: 150%;font-family: 宋体;font-size: 15px">各地在实施中如有问题，请及时与部人事司、部职业技能鉴定指导中心联系。</span>
</p>
<p>
    <br/>
</p>
<div align="center" id="buttondis" style="display:none;margin-top: -50px">
	<table>
		<tr>
			<td>
			<input type="button" value="阅读完成，参加考试"  onclick="submitform()"></td>
		</tr>
	</table>
</div>
</form>
  </body>
</html>
