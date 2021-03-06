<%@ page contentType="application/msexcel; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wondersgroup.falcon.paper.model.EPapers;" %>



<!-- 以上这行设定本网页为excel格式的网页 -->

<%

   response.setHeader("Content-disposition","attachment; filename=exam.xls");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
%>
<html>
<head>
<title>详细信息</title>
<style>
BODY {
	font-family: "宋体"; 
	font-size: 12px;
	color: #06374a;
	text-decoration: none;
	margin: 0px;

}
*{ margin:0; padding:0;}
/*头部样式*/
body{ font-size:12px;color:06374a;}
#userinfo {
    font-size:12px;color:#003748;
}
#userinfo a:link{
    font-size:12px;
    color:#003748;
text-decoration: none;
}

#userinfo a:visited{
	font-size:12px;
	color:#003748;
	text-decoration: none;
}

#userinfo a:hover{
	font-size:12px;
	color:#003748;
	text-decoration: underline;
}

.opacity {
FILTER: alpha(opacity=100);
}

/*左边菜单样式*/
.header05 {
	background:url(../images/left_menu_bj.jpg) repeat-x;
	height:19px;
	text-align:left;
	font-size:12px;
	color:#255a7a;
	padding-left:46px;
	padding-right:1px;
	padding-top:9px;
	font-weight: weight;
}
.header06{ background:url(../images/left_menu.jpg) no-repeat; height:28px; width:27px;
}

.left_menu_font{ font-size:12px; color:#002835; padding-left:50px; padding-top:3px;
}
.left_menu_border{
	width:201px;
	color:#f3f7f9;
	height:100%;
	border-right-width: 1px;
	border-left-width: 1px;
	border-right-style: solid;
	border-left-style: solid;
	border-right-color: #FFFFFF;
	border-left-color: #ffffff;
	background-color: #F3F7F9;
}
#left_menu { 
   width:201px; 
   padding:0px; 
   margin:0px 0px 0px 0px;
}
#left_menu .title_title{ 
   width:173px;height:27px;
   line-height:27px;
   padding:0px 0px 0px 34px;
   margin:0px 0px 0px 0px;
   font-size:9pt;
   background-image:url(../images/left_menu/alink_bj.jpg);
   color:#164c62;
}
.left_menu_title{
   background:url(../images/left_menu/alink_bj.jpg) repeat-x; 
   width:201px; height:24px; 
   color:#F3F7F9; 
   font-weight:bold;
   border-left:1px solid #fff;
}
.left_menu_title .span{ 
   padding:0px; margin:3px 6px 0px 30px; float:left;
}
.left_menu_title .span_left{ 
   padding:0px; margin:7px 8px 0px 0px; float:left;
}
#left_menu ul {
   padding:0px; margin:0px; list-style-type:none; padding-top:6px; padding-bottom:3px; background-color:#f3f7f9;
}
#left_menu li { 
   padding:0px;margin:0px;list-style-type:none;
}
#left_menu li a {
	width:159px;
	height:20px;
	margin:0px;
	list-style-type:none;
    background:url(../images/left_menu/title_title_bg.jpg) no-repeat 8px -7px; 
	font-size:9pt;
	text-decoration:none;
	color:#04212d;
	display:block;
	padding-top: 1px;
	padding-right: 0px;
	padding-bottom: 3px;
	padding-left: 48px;
}
#left_menu li a:hover {
    list-style-type:none; font-size:9pt; text-decoration:none; color:#FF0000;
}

/*bar样式*/

.bar { background:url(../images/bar1.jpg) top left no-repeat; width:5px; margin-top:100px;}
.bar1 { background:url(../images/bar2.jpg) top left no-repeat; width:5px; margin-top:100px;}
.bar3 { background:url(../images/bar3.jpg) top center no-repeat; width:59px;}
.bar4 { background:url(../images/bar4.jpg) top center no-repeat; width:59px;}
.bar5 { background:url(../images/bar5.jpg) top left no-repeat; width:6px; margin-top:100px;}
.bar6 { background:url(../images/bar6.jpg) top left no-repeat; width:6px; margin-top:100px;}

/*bottom样式*/
.bottom {
font-size:12px;
color:#003748;
}

/*content_top样式*/
.content_top {
font-size:12px;
color:#c31c00;
padding-top:4px;
}

/*content_position样式*/
.position {
font-size:12px;
color:#06374a;
}

/*表头样式*/
.header1 { background:url(../newimages/content_left_bj.jpg) no-repeat; height:22px; width:4px;}
.header2{ background:url(../newimages/content_bj.jpg) repeat-x; height:10px; text-align:center; font-size:12px; color:#ffffff; padding-left:7px; padding-right:1px; padding-top:4px;}
.header3 {
background:url(../newimages/content_right_bj.gif) no-repeat; height:22px; width:24px;
}

/*表格样式*/
.borader {
border:#90b9c6 solid 1px; background-color:#f8fbfd; margin-right:5px; padding:10px 0 12px 0;

}
.borader_box{width:100%; padding:8px 0 0 0; margin:0 auto;}
/*表格样式*/
.borader1 {
border:#90b9c6 solid 1px; border-top:none;}
/*表格样式*/
.borader2 {
border:#90b9c6 solid 1px; border-top:none; border-bottom:none;}

/*right_menu样式*/
.header4 { background:url(../images/right_menu_01.jpg) no-repeat; height:26px; width:24px;
}
.header5{ background:url(../images/right_menu_02.jpg) repeat-x; height:20px; text-align:left; font-size:12px; color:#002d3b; padding-left:4px; padding-right:2px; padding-top:2px;
}
.header6 { background:url(../images/right_menu_03.jpg) no-repeat; height:26px; width:3px;
}
/*right_menu字体样式*/
.right_menu_td {
	font-size:12px;
	color:#06374a;
	padding-top:7px;
	padding-bottom:3px;
	line-height: 12px;
}
.right_menu_td a:link{
	font-size:12px;
	color:#06374a;
	padding-top:7px;
	padding-bottom:3px;
	line-height: 12px;
	text-decoration: none;

}
.right_menu_td a:visited{
	font-size:12px;
	color:#06374a;
	padding-top:7px;
	padding-bottom:3px;
	line-height: 12px;
    text-decoration: none;

}
.right_menu_td a:hover{
	font-size:12px;
	color:#c31c00;
	padding-top:7px;
	padding-bottom:3px;
	line-height: 12px;
	text-decoration: none;
}

.right_menu_line{
    border-bottom:#bebebe 1px dashed;
}

.category{
color:#002d3b;
padding-top:4px;
}

/*表单样式*/
.input1 { border:1px solid #90b9c6; height:16px; line-height:14px; width:92%; padding-left:3px; padding-right:3px; font-size:12px; background:url(../images/inputbj.jpg) repeat-x;}
.input2 { border:1px solid #90b9c6; height:16px; line-height:14px; width:98%; padding-left:3px; padding-right:3px; font-size:12px;background:url(../images/inputbj.jpg) repeat-x;}
.input3 { border:1px solid #90b9c6; height:16px; line-height:14px; padding-left:3px; padding-right:3px; font-size:12px; background:url(../images/inputbj.jpg) repeat-x;}
.input4 { border:1px solid #90b9c6; height:16px; line-height:14px; width:86%; padding-left:3px; padding-right:3px; font-size:12px;background:url(../images/inputbj.jpg) repeat-x;}

.input5 { border:1px solid #90b9c6; height:20px; line-height:16px; width:50%; padding-left:3px; padding-right:3px; font-size:12px; background:url(../images/inputbj.jpg) repeat-x;}
.date { background: url(../images/date.gif) 1px right no-repeat;}
.textarea1{ border:1px solid #90b9c6; width:86%; padding-left:3px; padding-right:3px; font-size:12px; height:50px;}
.textarea2{ border:1px solid #90b9c6; width:92%; padding-left:3px; padding-right:3px; font-size:12px; height:50px;}
.textarea3{ border:1px solid #90b9c6; width:98%; padding-left:3px; padding-right:3px; font-size:12px; height:50px;}
.textarea4{ border:1px solid #90b9c6; padding-left:3px; padding-right:3px; font-size:12px; height:50px;}
.submit_2 {
	background:url(../images/submit/submit1.jpg) top left no-repeat;
	width:74px;
	height:22px;
	border:0px none;
	background-color:transparent;
	color:#0f4859;
	font-size:14px;
	padding-top:3px;
	cursor:hand;
	font-weight: normal;
}
.submit_3 {
	background:url(../images/submit/submit2.jpg) top left no-repeat;
	width:90px;
	height:22px;
	border:0px none;
	background-color:transparent;
	color:#0f4859;
	font-size:14px;
	padding-top:3px;
	cursor:hand;
	font-weight: bold;
}

.borader3 {
border:#a1c8e4 solid 1px; background-color:#f4f7f9;}

.header7 { background:url(../images/left_menu_01.jpg) no-repeat; height:24px; width:20px;
}
.header8{ background:url(../images/left_menu_02.jpg) repeat-x; height:22px; text-align:left; font-size:12px; color:#002d3b; padding-left:7px; padding-right:2px; padding-top:1px;
}

.title_font{
	font-family: "宋体";
	font-size: 12px;
	color: #003748;
	text-decoration: none;
	margin: 0px;
	font-weight: bold;
	padding-top:2px;
	background:url(../images/titlebj.jpg) repeat-x; height:23px;
}
.table_list1 { border:1px #90b9c6 solid;}
.table_list1 th { height:22px; background-color:#dff1ff border-bottom:none;}
.table_list1 td { height:22px; text-align:center; border-left:none;  border-top:1px solid #90b9c6;border-bottom:none; padding:3px 0;}
.table_list th { height:22px; background-color:#e0edf1 border-bottom:1px solid #e0edf1; font-size:12px;}
.table_list td { height:20px; text-align:center; border-left:1px solid #ffffff;  border-top:1px solid #ffffff;border-bottom:1px solid #ffffff; padding-top:2px; font-size:12px;}
.td_over { background-color:#fbf2cc;}
.input_td_over { background-color: #ffffff; border:1px solid #7f9db9;}
.input_td_out { border:1px solid #000000; background:none;}
.input_td_out_1 { border:1px solid #000000; background:#ebf3f6}


.num_font a{
	font-family: "宋体";
	font-size: 12px;
	color: #06374a;
	text-decoration: none;
	margin: 0px;
	padding-top:2px;
}
.num_font a:link{
	font-family: "宋体";
	font-size: 12px;
	color: #06374a;
	text-decoration: none;
	margin: 0px;
	padding-top:2px;
}
.num_font a:hover{
	font-family: "宋体";
	font-size: 12px;
	color: #ff0000;
	text-decoration: none;
	margin: 0px;
	padding-top:2px;
}

.SmallButton {
	background-image: url(../images/smbmit.jpg);
	height: 22px; line-height:22px; text-align:center;
	width: 48px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
}

.ok { border:0px solid #90b9c6; width:60%; height:166px; margin-top:10%; margin-left:20%;}
.ok span { font-size:18px; font-weight:bold; color:#177B2F;}
.table_lineheight td { height:26px; font-size:12px;}

.submit_2_ro { background:url(../images/submit/submit1_2.jpg) top left no-repeat; width:74px; height:22px; border:0px none; background-color:transparent; color:#808080; font-size:14px; font-weight:bold;  cursor:hand;}

#page_height { margin:0;width:100%; text-align:center; padding:0 10px;}
.select1 { width:100%;}
.select2 { width:96%;}
.select3 {  }
.margin10 { margin-top:10px;}
.t_font14 td { font-size:14px; font-weight:bold; margin:0;}
.fyclass{ background:url(../images/fybj.jpg) repeat-x;height:26px;border-left:solid #90b9c6 1px;;border-right:solid #90b9c6 1px;;border-bottom:solid #90b9c6 1px;}

.sign{FONT-FAMILY: webdings; FONT-SIZE: 14px;color:#505050}
.sign2{FONT-FAMILY: webdings; FONT-SIZE: 14px;color:#999999}
.page1_box{ width:100%; float:right; font-size:12px; text-align:right;}
.page1{float:left; width:93%; padding:0 0 10px 0; margin:0;}
.page1_img{float:left; padding:0; margin:0;}

/*content_top样*/
.system_font{
	font-size:14px;
	font-weight: bold;
	color:#06374a;
	line-height: 22px;
	text-align:left;
	padding:22px 0 0 0;
}
.system_font1{
	font-size:14px;
	font-weight: bold;
	color:#06374a;
	line-height: 40px;
	text-align:left;
	padding:15px 0 0 0;
}
.tips{float:left;margin:0; padding:20px 0 0 185px;}
.tips1{float:left;margin:0; width:40px; padding:20px 0 0 185px;}

.title_background{
	font-family: "宋体";
	font-size: 12px;
	background:url(../images/statistics_background.jpg) repeat-x; height:23px;
	color: #003748;
	text-decoration: none;
	margin: 0px;
	padding-top:2px;
}
.title_background2{
	font-family: "宋体";
	font-size: 12px;
	background:url(../images/statistics_background2.jpg) repeat-x; height:20px;
	color: #003748;
	padding-top:2px;
	text-decoration: none;
	margin: 0px;
	padding-top:6px;
}
.title_background3{
	font-family: "宋体";
	font-size: 12px;
	height:184px;
	text-decoration: none;
	margin: 0px;
	padding-top:2px;
	background-image: url(../images/statistics_background1.jpg);
	background-repeat: repeat-x;
	background-position: bottom;
	background-color:#eaf5fa;
}

/*登录页层样式*/
#Layer1{
	position:absolute;
	width:460px;
	height:125px;
	z-index:5;
	left: 420px;
	top: 385px;
}
/*登录页层样式*/
.login_font{
    font-size:12px; 
	color:#05730e;
}

.login_font a{
    font-size:12px; 
    color:#05730e;
	text-decoration: none;

}

.login_font a:hover{
	font-size:12px;
	color:#05730e;
	text-decoration: underline;
}


/*登录页输入框样式*/
.input_left{
	background:url(../images/input_left.jpg) no-repeat; height:23px; width:10px;
}
.input_bg{
    background-image: url(../images/input_bg.jpg);
	background-repeat:repeat-x;
	height:23px;
}
.input_right{
    background-image: url(../images/input_right.jpg);
	background-repeat:repeat-x;
	height:23px;
	width:10px;
}

/*输入框透明样式*/
.input_box{
	border:0px solid #90b9c6;
	height:12px;
	line-height:12px;
	width:98%;
	padding-top:4px;
	padding-left:0px;
	background-color: transparent;
	font-size:12px;
}
.nav{ padding-left:}
.nav a:link{ font-size:12px;}
.nav a:hover { font-size:12px; color:#30607a;}
/*.nav a:hover span{ font-size:12px; color:#ff0000; background:url(../images/submit/rl_navbj.gif) right 3px no-repeat; height:22px; padding-right:4px; cursor:pointer; padding-left:3px;}*/
.nav a:active { font-size:12px; height:22px; color:#30607a; }
/*.nav a:active span{ font-size:12px; color:#000; background:url(../images/submit/ra_navbj.gif) right 3px no-repeat; height:22px; padding-right:4px; cursor:pointer; padding-left:3px;}*/
.nav a:visited{ font-size:12px;}
.row_height{padding:6px 0 0 0;}

.bottom_c1 {
	border-bottom:1px dashed #90b9c6;
}

.main_right_list{float:left; WIDTH:100%;  margin:0; padding:0;}
.main_right_list ul{float:left; display:block; margin:10px 0 0 0; padding:0; width:100%;   border-bottom:1px solid #9accda; 
padding-left:12px;}
.main_right_list ul li{ background:url(../images/title_27.gif) repeat-x;float:left; display:block; list-style:none; margin:0; padding:0;text-align:center; line-height:22px; margin-right:5px; }
.main_right_list ul li span{ float:left; display:block;}
.span_list1{float:left; display:block;background:url(../images/title_26.jpg) repeat-x; height:22px;  padding:0 15px; margin:0;border-top-width: 1px;
 border-right-width: 1px;
 border-left-width: 1px;
 border-top-style: solid;
 border-right-style: solid;
 border-left-style: solid;
 border-top-color: #9accda;
 border-right-color: #9accda;
 border-left-color: #9accda;
  font-size:14px;
  color:#474747; font-weight:bold;
}
.span_list1 a{ color:#06374a; text-decoration:none;}
.span_list1 a:link{ color:#06374a; text-decoration:none;}
.span_list1 a:hover{ color:#06374a; text-decoration:none;}
.span_list2{float:left; display:block; height:22px; padding:0 15px; margin:0; color: #474747;}
.span_list2 a{ color:#06374a; text-decoration:none;}
.span_list2 a:link{ color:#06374a; text-decoration:none;}
.span_list2 a:hover{ color:#06374a; text-decoration:none;}
.on { background:url(../images/submit/nav_bg.gif) 1px 6px no-repeat; height:22px; color:#ffffff;}
.on a:link, .on a:visited { color:#fff;}
.on a:hover, .on a:active { color:#fff;}
/*表格样式*/
.lxtable td{ line-height:24px;}
.bbline{ border-bottom:dotted #b3b2b2 1px;}
.tablistys td{ height:25px;}
/*导航栏样式，底部样式，新样式*/
.nav_active{ background:url(../images/submit/activebj.jpg) 1px 6px no-repeat; width:97px;}
.nav_sactive{ background:url(../images/submit/sactivebj.jpg) 12px 6px no-repeat; width:76px;}
.rrbj{ background:url(../images/rrbj.jpg) no-repeat; width:35px; height:29px;}
.rbj{background:url(../images/rbj.jpg) repeat-x; height:29px;}
.exit_btn{ background:url(../images/submit/exit_btn.jpg) no-repeat; width:69px; height:21px; border:none;}
.nrbj{ background:url(../images/nrtopbj.jpg) left top repeat-x; height:24px;}
.tjbtn { background: url(../images/submit/smbmit.jpg) no-repeat; height:22px; line-height:22px; text-align:center; width: 48px;	border:none; font-family:"宋体"; font-size:12px; cursor:hand;}

.btnleft{ background:url(../images/btnleft.jpg) left center no-repeat; width:4px; height:22px; padding-left:2px;}
.btnright{ background:url(../images/btn_right.jpg) right center no-repeat; border:none;  height:22px; font-size:14px; color:#0f4859; line-height:22px;}
.mlistys{ list-style:none;}
.mlistys li a{ font-size:12px; text-decoration:none; color:#06374a;}
</style>

<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
<script language="JavaScript" type="text/JavaScript" >
function modify(stid){
	
	document.aForm.action="epaperquestion.action?sjid="+stid;
	document.aForm.myaction.value="modifyload";
	document.aForm.submit();
}
function del(){
	var row=0;
	var t=window.document.getElementsByName('deleteid');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要删除的记录！");
		return false;
	}
    var tt=confirm("确定要删除吗？");  //确认是否删除
    if(tt){
    	document.aForm.action="QuestionServlet";
		document.aForm.myaction.value="del";
		document.aForm.submit();
    }else{
    	return false;
    }
	
}
//导出excel


function viewpam(paperid){
	window.location.href="cjpmAction.action?paperid="+paperid;
}
function excel(){
	window.location.href="cjpmexcel.jsp";
}
cjpmexcel.jsp
function doQuery() {

  document.aForm.submit();
}

function jumpViewPaper(sjid){
	window.location.href="previewPaperAction.action?paperid="+sjid;
}
//复制试卷
function copyPaper(sjid){
	window.location.href="copyPaperAction.action?paperid="+sjid;
}
//更改试卷状态
function changeState(sjid,paperState){
	window.location.href="changeState.action?paperid="+sjid+"&paperState="+paperState;
}
function goback(){
	window.location.href="answerpaperpmquery.action";
}

</script>

</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
    <tr>
      <s:iterator value="eanswerpapers" id="ap" status="status"></s:iterator>  
         <td    align='center'><font color="red"><s:property value="#ap.epapers.sjMc"/>:</font>考试成绩排名表</td>
    </tr>
     <tr>
       <td>
         <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">排名 </span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">答卷人员</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">开考时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">得分</span></td>
             </tr>
            <s:iterator value="eanswerpapers" id="answerpaper" status="status">
           <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
             <td align='center' class='num_font'><s:property value="#status.index+1"/></td>
             <td align='center' class='num_font'><s:property value="#answerpaper.epapers.sjMc"/></td>
             <td align='center' class='num_font'><s:property value="#answerpaper.djRymc"/></td>
     	     <td align='center' class='num_font'><s:date name="#answerpaper.djKssj" format="yyyy-MM-dd HH:mm:ss"/></td>
             <td align='center' class='num_font'><s:date name="#answerpaper.djJssj" format="yyyy-MM-dd HH:mm:ss"/></td>
             <td align='center' class='num_font'><s:property value="#answerpaper.djZf"/></td>
          </tr>
          </s:iterator>      
    </table> 
	<c:if test="${epapers!= null }">
              <!-- fenye -->
  	<s:form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post">
  		<s:hidden name="epaper.sjMc"></s:hidden>
  		<s:hidden name="epaper.sjZt"></s:hidden>
  		<input type="hidden" name="sjKksjbegin" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
  		<input type="hidden" name="sjZjsjbegin" value='<s:date name="sjZjsjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
  		<s:hidden name="sjZjsjend"></s:hidden>
		<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm"/>
	</s:form>
  </c:if>
            </td>
        </tr>
              </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
        <td>本次参考人数:<s:property value="all"/>&nbsp;&nbsp;&nbsp;&nbsp;不及格人数:<s:property value="unpass"/>
        &nbsp;&nbsp;&nbsp;&nbsp;平均分:<s:property value="average"/>&nbsp;&nbsp;&nbsp;&nbsp;合格率:<s:property value="percent"/></td>   
          </tr>
          </table>

</body>
</html>         
  		 