<%@ page contentType="application/msword; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.wondersgroup.falcon.paper.model.*" %>
<%@ page import="com.wondersgroup.falcon.paper.model.EPapers;" %>



<!-- 以上这行设定本网页为excel格式的网页 -->

<%

   response.setHeader("Content-disposition","attachment; filename=exam.doc");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
%>
<html>
<head>
<title>详细信息</title>
<style>
BODY {
	MARGIN: 0px
}
BODY,td {
    font-size: 12px;
}
/**
 * 分页箭头
 */
.mgray1 {
	font-family: 宋体;
	font-size: 12px;
	line-height: 22px;
	color: #505050;
	text-decoration: none;
}
.minp {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #555555;
	border-right-color: #F4F4F4;
	border-bottom-color: #F4F4F4;
	border-left-color: #555555;
	font-size: 12px;
}
.mgray2 {
	font-family: 宋体;
	font-size: 12px;
	line-height: 20px;
	color: #505050;
	text-decoration: none;
}
.sign{FONT-FAMILY: webdings; FONT-SIZE: 14px;color:#505050}
.sign2{FONT-FAMILY: webdings; FONT-SIZE: 14px;color:#999999}
/**
 * ----End 分页箭头---
 */

/**
 *无框对象
 */
.bno {
	border: 0px solid #FFFFFF;
}
 
.print { border:#A4D1FF; border-style: solid; border-top-width: 1px;
  border-right-width: 0px; border-bottom-width: 0px; border-left-width: 1px; font-size:
  12px; cell-spacing:0;
  }
.print td { border:#A4D1FF; border-style: solid; border-top-width: 0px; border-right-width:
  1px; border-bottom-width: 1px; border-left-width: 0px; font-size: 12px; line-height:
  120%; text-align: center
  }
 
 
.printOnly {display:none} 

@media print {
  .dispOnly {display:none} 
  .printOnly {display:block}
} 
@media screen { 
  .dispOnly {display:block} 
  .printOnly {display:none} 
} 

A:link {
	COLOR:#333333; TEXT-DECORATION: none
}
A:visited {
	 COLOR:#333333;TEXT-DECORATION: none
}
A:active {
	color:purple
}
A:hover {
	COLOR: #FF6600
}
A.b:link {
	COLOR:#0000FF; TEXT-DECORATION: underline
}
A.b:visited {
	 COLOR:#711C57;TEXT-DECORATION: underline
}
A.b:active {
	color:#ff0000
}
A.b:hover {
	COLOR: #FF0000
}
A.d:link {
	COLOR:#000000; TEXT-DECORATION: none
}
A.d:visited {
	 COLOR:#6633CC;TEXT-DECORATION: none
}
A.d:active {
	color:#6633CC
}
A.d:hover {
	COLOR: #FF6600
}
A.c:link {
	COLOR:#333333;
	TEXT-DECORATION: none;
	font-weight: bold;

}

A.c:active {
	color:#FF6600
}
A.c:hover {
	COLOR: #FF6600
}
input {

	font-family: "宋体";
	font-size: 9pt;
	text-decoration: none;
}


.bgre {
	background-repeat: no-repeat;
}
body,td,table{
	font-size: 12px;
	line-height: 140%;
	color:#333333;
SCROLLBAR-FACE-COLOR: #E4F5FC; 
SCROLLBAR-HIGHLIGHT-COLOR: 	#ffffff; 
SCROLLBAR-SHADOW-COLOR: #4E9BC9; 
SCROLLBAR-3DLIGHT-COLOR: #A6D2F7; 
SCROLLBAR-ARROW-COLOR: #A6D2F7; 
SCROLLBAR-TRACK-COLOR: 	#E0F0FD; 
SCROLLBAR-DARKSHADOW-COLOR: #E0F0FD; 
SCROLLBAR-BASE-COLOR: 	#F2F2F2}

}
.TableHeader{
font-weight:bold;
}

.font14 {
	font-family: "宋体";
	font-size: 14px;
	color: #141414;
	text-decoration: none;
}
.BigButton {
	background-image: url(../images/bt000.gif);
	background-repeat: no-repeat;
	height: 21px;
	width: 79px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
	padding: 3px;
	text-align: center;


}
.SmallButton {
	background-image: url(../images/bt00.gif);
	height: 21px;
	width: 49px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	padding-top: 4px;
	cursor:hand;
	text-indent: 10px;

}
.ssButton {
	background-image: url(../images/bt0.gif);
	background-repeat:no-repeat;
	height: 21px;
	width: 25px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
}
.BigButtons {
	background-image: url(../images/bt000s.gif);
	background-repeat: no-repeat;
	height: 21px;
	width: 79px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
	padding: 3px;
	text-align: center;


}

.SmallButtons {
	background-image: url(../images/bt00s.gif);
	height: 21px;
	width: 49px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	padding-top: 4px;
	cursor:hand;
	text-indent: 10px;

}
.BigBigButtons {
	background-image: url(../images/bt0000.gif);
	height: 21px;
	width: 102px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	padding-top: 4px;
	cursor:hand;
	text-indent: 0px;

}
.ssButtons {
	background-image: url(../images/bt0s.gif);
	background-repeat:no-repeat;
	height: 21px;
	width: 25px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
}
.1 {
	font-family: "Verdana", "Arial", "Helvetica", "sans-serif";
	font-size: 14px;
	font-weight: 500;
	color: #FF3300;
	text-decoration: none;

}

.w200 {
	width: 150px;
	padding-top: 3px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #4AB0E8;
	border-right-color: #7EC6EE;
	border-bottom-color: #4AB0E8;
	border-left-color: #7EC6EE;
}
.k {
	font-family: "宋体";
	font-size: 12px;
	width: 120px;
}
.bg_bg {
background-color:;
background-image:url(../images/0-2.jpg);
background-position:left top;
background-repeat:no-repeat;
background-attachment:fixed;
} 

/*----------PRINT STYLE FOR GWGL---------*/
@media print {
.print_no {
display:none;
}
}
.print_main {
margin:10px;
text-align:center;
width:100%;
}
.print_normal {
font-family:"宋体";
font-size:10pt;
font-weight:normal;
line-height:150%;
}
.print_01,.print_01_swd,.print_02 {
width:630px;
text-align:center;
font-family:"宋体";
color:#000000;
line-height:150%;
}
.print_01 {
font-size:24pt;
font-weight:bold;
letter-spacing:20pt;
line-height:150%;
}
.print_01_swd {
font-size:14pt;
letter-spacing:20pt;
}
.print_02 {
font-size:16pt;
font-weight:bold;
letter-spacing:5pt;
}
.print_02_swd {
width:630px;
text-align:center;
/*margin-top:10px;*/
font-family:"黑体";
color:#000000;
font-size:16pt;
font-weight:bold;
letter-spacing:5pt;
line-height:150%;
}
.print_03 {
font-family:"宋体";
color:#000000;
font-size:12pt;
font-weight:bold;
letter-spacing:2pt;
line-height:150%;
}
.print_03_swd {
font-family:"宋体";
color:#000000;
font-size:10pt;
font-style:normal;
letter-spacing:2pt;
line-height:150%;
}
.print_04 {
font-family:"宋体";
color:#000000;
font-size:14pt;
font-weight:bold;
letter-spacing:1px;
border-width:1px;
border-color:#000000;
border-style:solid;
}
.all_border,.top_none,.bottom_none,.left_border,.right_border,.top_bottom_none,.bottom_border {
border-width:1px;
border-style:solid;
border-color:#000000;
}
.top_none {
border-top-style:none;
}
.bottom_none {
border-bottom-style:none;
}
.bottom_border {
border-right-style:none;
border-top-style:none;
border-left-style:none;
}
.left_border {
border-right-style:none;
border-top-style:none;
border-bottom-style:none;
}
.right_border {
border-left-style:none;
border-top-style:none;
border-bottom-style:none;
}
.top_bottom_none {
border-top-style:none;
border-bottom-style:none;
}
.print_fwg,.red_left_right_none,.print_03_fwg,.print_04_fwg,.red_border,.red_right_border,.red_left_border,.red_top_none,.red_bottom_none,.red_top_bottom_none,.red_bottom_border {
color:#FF0000;
border-color:#FF0000;
line-height:150%;
}
.print_fwg {
font-family:"宋体";
font-size:18pt;
font-weight:bold;
letter-spacing:10pt;
}
.print_03_fwg {
font-family:"宋体";
font-size:10pt;
font-weight:normal;
line-height:150%;
}
.print_04_fwg {
font-family:"宋体";
color:#FF0000;
font-size:14pt;
font-weight:bold;
letter-spacing:1px;
border-width:1px;
border-color:#FF0000;
border-style:solid;
}
.red_border,.red_left_right_none,.red_right_border,.red_left_border,.red_top_none,.red_bottom_none,.red_top_bottom_none,.red_bottom_border {
border-style:solid;
border-width:1px;
}
.red_top_none {
border-top-style:none;
}
.red_bottom_none {
border-bottom-style:none;
}
.red_top_bottom_none {
border-top-style:none;
border-bottom-style:none;
}
.red_bottom_border {
border-right-style:none;
border-top-style:none;
border-left-style:none;
}
.red_left_border {
border-right-style:none;
border-top-style:none;
border-bottom-style:none;
}
.red_right_border {
border-left-style:none;
border-top-style:none;
border-bottom-style:none;
}
.red_left_right_none {
border-left-style:none;
border-right-style:none;
border-bottom-style:none;
}.bg {
	background-image: url(../images/m_bg.gif);
	background-repeat: repeat-x;
}
.BigButton {
	background-image: url(../images/bt000.gif);
	background-repeat: no-repeat;
	height: 21px;
	width: 79px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
}
.SmallButton {
	background-image: url(../images/bt00.gif);
	height: 21px;
	width: 49px;
	border: none;
	font-family: "宋体";
	font-size: 12px;
	padding-top: 3px;
	cursor:hand;
	text-align: center;

}
.minButton {
	background-image: url(../images/bt0.gif);
	background-repeat:no-repeat;
	border: 0px solid #9FBED8;
	height: 21px;
	width: 25px;
	font-family: "宋体";
	font-size: 12px;
	cursor:hand;
	text-align: center;
	padding-top: 3px;


}
.bt3 {
	font-family: "黑体";
	font-size: 18px;
}
.t_b
{
	font-size: 12px;
	color: #322C2C;
	height: 20px;
	border-top-width: 1px;
	border-top-style: solid;
	border-bottom-style: solid;
	border-top-color: #FEFEFE;
	border-bottom-color: #C3C8CB;
	background-color: #F5F7F7;
	border-bottom-width: 1px;
	padding-left: 8px;
	padding-right: 8px;
	cursor: hand;
}

.t_b_on
{
	font-size: 12px;
	color: black;
	font-weight: bold;
	background-color: #ffe0c0;
	height: 20px;
	border-top-width: 1px;
	border-top-style: solid;
	border-bottom-style: solid;
	border-top-color: #FEFEFE;
	border-bottom-color: #CCCCCC;
	border-bottom-width: 1px;
	padding-left: 8px;
	cursor: hand;
	padding-right: 8px;
}
.t_b_l
{
	font-size: 12px;
	color: #225588;
	background-color: white;
	padding-left: 20px;
	height: 18px;
}

.input
{
	border-bottom: 1px solid #CCCCCC;
	border-left: 1px solid #000000;
	border-right: 1px solid #CCCCCC;
	border-top: 1px solid #000000;
	color: #FF6633;
	font-size: 9pt;
	font-family: "Tahoma" , "Verdana" , "Arial" , "Helvetica" , "sans-serif";
}

.t_b_l
{
	font-size: 12px;
	color: #225588;
	background-color: white;
	padding-left: 30px;
	height: 20px;
}

.con_bg { width:98%; background:#FFF; border:1px solid #D2E8FF; margin-left:1%; *width:99%;}
h1 { font-size:24px; font-family:"黑体"; margin:10px auto; text-align:center; margin-top:20px;}
.title { background:url(../newimages/title_bg.gif) left bottom repeat-x; height:55px;}
.title ul { margin-left:28%;}
.title ul li { float:left; margin-right:40px; list-style:none; font-size:14px; color:#666;}
.first_t { margin-top:10px; margin-left:20px;}
.first_t li { font-size:18px; color:#004A80; font-weight:bold; line-height:24px; list-style:none; font-family:"黑体"; }
.second_t { margin-left:40px;}
.second_t li { font-size:12px; font-weight:bold; color:#000; list-style:none; margin-bottom:30px; width:98%; font-family:Arial, Helvetica, sans-serif;}
.third_t { margin-left:16px; }
.third_t li { font-size:12px; font-weight:normal; line-height:25px; margin-bottom:0px; color:#555; width:100%; line-height:25px; *height:25px;}
.text_i {  border:1px solid #90b9c6; width:96%;  font-size:12px;  margin:0px; text-align:left; padding:5px;}
.inners { border:0px none; border-bottom:1px solid #333; text-align:center; font-size:12px; line-height:18px; height:18px;}
.fs { font-size:18px; font-weight:bold; color:#F00;}
.title_sy ul { margin-left:32%;}
.third_sy { margin-left:22px;}
.red { color:#F00; font-weight:normal;}
.third_t .answer { background:url(../newimages/answer_bg.jpg) left top repeat-x; height:23px; border:1px solid #D5DBEF; padding-bottom:0px; line-height:22px; text-align:right; font-size:14px;}
.answer dl dd { text-align:right; float:right; color:#F00; margin-right:30px;}
.answer dl dt { float:right; line-height:22px; margin-right:30px;}
.blue_bg { background-color:#f1f1f1; padding-left:5px;}
.third_sy .red { color:#F00; padding-left:5px;}
.title_yl { background:none;}
.blues { color:#03C; font-weight:normal;}
.list_option { margin-left:3px;}
.list_option li { list-style:none; line-height:23px;}
.table_layout { border-top:1px solid #D5E0E3; border-right:1px solid #D5E0E3;}
.table_layout td { height:24px; padding:0px 3px; border-bottom:1px solid #D5E0E3; border-left:1px solid #D5E0E3; line-height:24px;}
.blue_f { color:#30F;}
.table_layout th { background-color:#fff;  border-bottom:1px solid #D5E0E3; border-left:1px solid #D5E0E3; line-height:24px;}
A
{
	text-decoration:none;
	color:#004488
} 

A:hover
{
	COLOR: #D5881E;
	TEXT-DECORATION: underline
}

.data_star
{
	color:#FF6600; position:relative; right:3px;
}

.data_error
{
	font-weight:bold; color:red; position:relative; left:8px;
}

BODY
{
    FONT-SIZE: 11px;
    FONT-FAMILY: "Verdana", "Arial", "Helvetica", "sans-serif";
    COLOR: #000000;
    BACKGROUND-COLOR: #FFFFFF;
    marginwidth: 0px;
    marginheight: 0px;
	overflow-x:hidden;
	SCROLLBAR-FACE-COLOR: #B6DDFF;
    SCROLLBAR-ARROW-COLOR: #ffffff;
    SCROLLBAR-TRACK-COLOR: #ffffff;
    SCROLLBAR-SHADOW-COLOR: #ffffff;
    SCROLLBAR-3DLIGHT-COLOR: #B0DEFF;
    SCROLLBAR-HIGHLIGHT-COLOR: #ffffff;
    SCROLLBAR-DARKSHADOW-COLOR: #B0DEFF;
}
td
{
	font-size:11px;
} 

textarea,input,select{
	font-family: "宋体"; font-size: 12px;
    background-color:#FFF4EB;
	border-bottom: 1px solid #DDBAA1;
	border-left: 1px solid #DDBAA1;
	border-right: 1px solid #DDBAA1;
	border-top: 1px solid #DDBAA1;
}

INPUT.buttonface
{
	FONT-FAMILY: "Verdana", "Arial", "Helvetica", "sans-serif";
	BACKGROUND: #ff9900; 
	border:1 solid black; COLOR: #ffffff;
	FONT-SIZE: 11px; 
	FONT-STYLE: normal; 
	FONT-VARIANT: normal; 
	FONT-WEIGHT: normal; 
	HEIGHT: 18px;
	LINE-HEIGHT: normal
} 

input.smallInput
{
	BORDER-RIGHT: 0.01cm solid;
    BORDER-TOP: 0.01cm solid;
    FONT-SIZE: 12px;
    BORDER-LEFT: 0.01cm solid;
    BORDER-BOTTOM: 0.01cm solid

}
.txtBox1 
{
	BORDER-RIGHT: #464646 1px solid; BORDER-TOP: #464646 1px solid; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(../../../../星空急速/星空急速/css/images/txtbox_shadow.gif); BORDER-LEFT: #464646 1px solid; COLOR: #282828; BORDER-BOTTOM: #464646 1px solid; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #f9f9f9; TEXT-DECORATION: none
}

.h1 {
	COLOR: #F57D08;
	font: 14px "宋体";
	font-weight: bold;
	text-align:center;
	margin: 9px;
}

/*
body
	{
		font:9pt 宋体;
		color:black; 
		scrollbar-face-color: #608EBD; 
		scrollbar-highlight-color: #608EBD;
		scrollbar-shadow-color: #ffffff; 
		scrollbar-3dlight-color:#FFF8F1;
		scrollbar-arrow-color: #ffffff;
		scrollbar-track-color: #FFF8F1;
		scrollbar-darkshadow-color: FFF8F1;

		FFF8F1

	}
*/
table
	{
		border-collapse:collapse;
		
	}

.frame_bg
	{
		height:6px;
		border:0px;
	}
	/*
.menu12
	{
		text-align:center;
		height:24pt;
		color:white;
		background:#454E5D;
		filter:Alpha(opacity:70);
		font:bold 10.5pt 宋体;
		cursor:hand;
	}
.menu
	{
		text-align:left;
		height:24pt;
		color:#99BEE4;
		font:10.5pt 宋体;
		cursor:hand;
		padding-left:6px;
		border-bottom:1px dashed #B1C0CF;
	}
.menu_up
	{
		text-align:left;
		height:24pt;
		background:#33587E;
		color:#FEDC94;
		font:10.5pt 宋体;
		cursor:hand;
		padding-left:6px;
		border-top:1px solid #CCE0EB;
	}
	
.menu {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 133px; PADDING-TOP: 0px
}
A.menu_title {
	WIDTH: 164px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 1px;
	MARGIN: 2px;
	COLOR: #EAFFEE;
	font: bold 9pt "宋体";
	TEXT-DECORATION: none;
	LINE-HEIGHT: 20px;
	PADDING-TOP: 2px;
	PADDING-left: 15px;
	BACKGROUND-COLOR: #628FE9;
	background-image: url(../images/1m.jpg);
}
A.menu_title:hover {
	WIDTH: 164px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 1px;
	MARGIN: 2px;
	COLOR: #D36F21;
	TEXT-DECORATION: none;
	LINE-HEIGHT: 20px;
	PADDING-TOP: 2px;
	PADDING-left: 16px;
	font-family: "宋体";
	font-size: 9pt;
	background-image: url(../images/2m.jpg);
}
.menu_li {
	WIDTH: 164px;FONT-WEIGHT: normal; MARGIN: 0px; BORDER-BOTTOM: #ececec 1px solid; LIST-STYLE-TYPE: none
}
A.menu_li {
	font: 9pt "宋体"; padding:3; DISPLAY: block; PADDING-LEFT: 5px; BACKGROUND: #fff; MARGIN: 0px; COLOR: #008; TEXT-DECORATION: none
}
A.menu_li:hover {
	font: 9pt "宋体"; padding:3; PADDING-LEFT: 8px; BACKGROUND: #FCEBCA; COLOR: #990000
}

.submenu_subtd
	{
		text-align:left;
		height:24pt;
		background:#33587E;
		font:9pt 宋体;
		cursor:hand;
		padding-left:5px;
		color:white;
	}
.submenu_td
	{
		border-bottom:1px solid #CCE0EB;
	}
	*/


	/*按钮区*/
.menu12
	{
		text-align:center;
		height:24pt;
		color:white;
		background:#454E5D;
		filter:Alpha(opacity:70);
		font:bold 10.5pt 宋体;
		cursor:hand;
	}
/*按钮区一级菜单前图片*/
.menu_img
	{
		text-align:left;
		height:20px;
		color:#00457F;
		font:9.5pt 宋体;
		cursor:hand;
		padding-left:12px;
		width:11px;
	}
/*按钮区一级菜单文字*/
.menu
	{
		text-align:left;
		height:20px;
		color:#00457F;
		font:bold 9.5pt 宋体;
		cursor:hand;
		padding-left:3px;
	}
/*按钮区一级菜单滑鼠经过*/
.menu_up
	{
		text-align:left;
		height:20px;
		color:#BF6516;
		font:bold 9.5pt 宋体;
		cursor:hand;
		padding-left:3px;
	}
/*按钮区二级菜单td*/
.submenu_subtd
	{
		text-align:left;
		height:24pt;
		background:#33587E;
		font:9pt 宋体;
		cursor:hand;
		padding-left:5px;
		color:white;
	}
/*按钮区二级菜单文字*/
.submenu
	{
		text-align:left;
		height:18px;
		color:#00457F;
		font:9pt 宋体;
		cursor:hand;
		padding-left:14px;
	}
/*按钮区二级菜单滑鼠经过*/
.submenu_up
	{
		text-align:left;
		height:18px;
		color:#BF6516;
		font:9pt 宋体;
		cursor:hand;
		padding-left:14px;
	}


.data_succtext
	{
		font:9pt 宋体;
		color:#446F2C;
		height:30px;
		background-repeat:no-repeat;
		text-align:center;
		padding-right:10pt;
		padding-top:2px;
	}
.data_messtext
	{
		font:9pt 宋体;
		color:#2B4782;
		height:30px;
		background-repeat:no-repeat;
		text-align:center;
		padding-right:10pt;
		padding-top:2px;
	}
.data_errortext
	{
		font:9pt 宋体;
		color:#BF7A24;
		height:30px;
		background-repeat:no-repeat;
		text-align:center;
		padding-right:10pt;
		padding-top:2px;
	}
.data_messback
	{
		font:9pt 宋体;
		color:#585D68;
		border:1px solid #A5A8B2;
		background:#D5D7DB;
		text-align:center;
	}
.data_buttonbar
	{
		background:#FDF0D4;
		border-bottom:1px solid #B0B0B0;
		border-right:1px solid #B0B0B0;
		font:9pt 宋体;
		color:#314173;
		height:24px;
		background-color:#FDF0D4;
		text-align:left;
		padding-left:5pt;
		padding-top:0px;
	}
.data_buttonbar_img
	{
		cursor:hand;
	}
.data_buttonbar_text
	{
		font:9pt 宋体;
		color:#314173;
		padding-left:3px;
		padding-right:12px;
		padding-top:4px;
	}
.data_buttonbar_textOver
	{
		font:9pt 宋体;
		color:#D97200;
		padding-left:3px;
		padding-right:12px;
		padding-top:4px;
	}
.tab{ 
		margin-left:5px;
		margin-bottom:8px;
		margin-right:12px;
		width:98%
	}
.tab_q{ 
		margin-left:5px;
		margin-bottom:8px;
		margin-right:12px;
		width:70%
	}
.data_tab_tit
	{
		text-align: center;
		font: bold 9.5pt "宋体";
		color:#5F64C0;
		PADDING-TOP: 2px;
		line-height: 18pt;
	}
.data_tab_th
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align:center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#F5FDF2;
	}
.data_tab_space
	{
		font-family: "宋体";
		font-size:10.5pt;
		line-height: 6pt;
		text-align: center;
		font-weight: bold;
		color:#5F64C0;
		PADDING-TOP: 2px;
	}
.data_tab_title
	{
		font-family: "宋体";
		font-size:10.5pt;
		line-height: 12pt;
		text-align: center;
		font-weight: bold;
		color:#5F64C0;
		PADDING-TOP: 2px;
	}
.data_tab_tdr
	{
	font:9pt 宋体;
	line-height: 16pt;
	text-align: right;
	color:#333333;
	PADDING-TOP: 2px;
	padding-right:4px;
	background-color: #D1EBFE;
	white-space:nowrap;
	}
.data_tab_tdc
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#B6D4FB;
		padding-right:4px;
	}

.data_tab_tdheader
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#A694AB;
		padding-right:4px;
	}
.data_tab_tdl
	{
	font:9pt 宋体;
	line-height: 16pt;
	text-align: left;
	color:333333;
	PADDING-TOP: 2px;
	padding-left:4px;
	background-color: #FFFFFF;
	}
.data_list_tit
	{
		font-family: "宋体";
		font-size:10.5pt;
		line-height: 18pt;
		text-align: center;
		font-weight: bold;
		color:#AB5B07;
		PADDING-TOP: 2px;
	}
.data_area_full
{
	width:100%;
}
.data_list_th
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#F8DBBE;
	background-image: url(../images/hbg.jpg);
		border: 1px solid #8CB3E3;
		white-space:nowrap;
	}

.data_list_th_click
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#F8DBBE;
		cursor:hand;
	}

.data_list_td_inactive
{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#ff0000;
		PADDING-TOP: 2px;
		background:#B0B0B0;
}

.data_list_td
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#ffffff;
		border: 1px solid #8CB3E3;
		white-space:nowrap;
	}

.data_list_td_nopass
	{
		font:9pt 宋体;
		line-height: 16pt;
		text-align: center;
		color:#333333;
		PADDING-TOP: 2px;
		background:#FFAAAA;
	}

.data_select
	{
		width:100%;
	}
.data_button
	{

		font: 9pt "宋体"; BORDER-RIGHT: #73B5F2 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #FFFFFF 1px solid; PADDING-LEFT: 2px; FONT-WEIGHT: bold; BACKGROUND: #BEEBFE; PADDING-BOTTOM: 0px; BORDER-LEFT: #FFFFFF 1px solid; COLOR: #0E3299; PADDING-TOP: 2px; BORDER-BOTTOM: #73B5F2 1px solid; TEXT-DECORATION: none
		
	}
	
	.style_menu
	{
		display:block;
		background-color:#F2F2F2;
		cursor:hand;
		text-align:left;
		width:100%;
		padding: 6px;
	}
	.style_menu_on
	{
		display:block;
		background-color:#B5BED6;
		cursor:hand;
		text-align:left;
		width:100%;
		padding: 6px;
	}
.area_span
{
	cursor:hand;
	width:100%;
	font-size:9pt;
	font-weight: bold;
	background-color: #006699; 
	color: #FFA34F; 
	padding-top:2px;
	margin-top:2px;
	padding-bottom:2px;
	border-left:1px solid #6770C3;
	border-top:1px solid #6770C3;
	border-right:1px solid #273093;
	border-bottom:2px solid #273093;
	height: 16pt;
}
.area_span_sel
{
	cursor:hand;
	width:100%;
	font-size:9pt;
	font-weight: bold;
	background-color: #006699; 
	color: white; 
	height:16pt;
	margin-top:2px;
	padding-top:2px;
	padding-bottom:2px;
	border-right:1px solid #6770C3;
	border-bottom:1px solid #6770C3;
	border-left:1px solid #273093;
	border-top:2px solid #273093;
	height: 16pt;
}
	
.span_button
{
	background-color:#93c2f7;
	color:#033568;
	font-size:9pt;
	cursor:hand;
	border:1px solid #358dee;
	padding-left:5px;
	padding-right:5px;
	padding-top:3px;
}

.span_button_on
{
	background-color:#e7b965;
	color:#964701;
	font-size:9pt;
	cursor:hand;
	border:1px solid #d69521;
	padding-left:5px;
	padding-right:5px;
	padding-top:3px;
}

.span_tab
{
	background-color:#93c2f7;
	color:#033568;
	font-size:9pt;
	cursor:hand;
	border:1px solid #358dee;
	padding-left:5px;
	padding-right:5px;
	padding-top:3px;
}

.span_tab_on
{
	background-color:#FDF0D4;
	color:#004488;
	font-size:9pt;
	cursor:hand;
	border:1px solid #d69521;
	padding-left:5px;
	padding-right:5px;
	padding-top:3px;
}

.data_list_desc
	{
		font-family: "宋体";
		font-size:10.5pt;
		line-height: 18pt;
		text-align: right;
		font-weight: bold;
		color:#AB5B07;
		PADDING-TOP: 2px;
	}
.data_list_desc_left
	{
		font-family: "宋体";
		font-size:10.5pt;
		line-height: 18pt;
		text-align: left;
		font-weight: bold;
		color:#AB5B07;
		PADDING-TOP: 2px;
	}
	
.contentHead
    {
		font:9pt 宋体;
		font-size:14.5pt;
		color:#2B4782;
		height:30px;
		background-repeat:no-repeat;
		text-align:center;
		padding-right:10pt;
		padding-top:2px;
    }
.formTable {
    empty-cells: show; min-width: 60%; max-width: 90%
}
.formTable th{
	PADDING-RIGHT: 22px; BORDER-TOP: #aba2ce 1px solid; PADDING-LEFT: 22px; FONT-WEIGHT: bold; PADDING-BOTTOM: 4px; COLOR: #000; PADDING-TOP: 4px; BACKGROUND-COLOR: #e2ebf7; TEXT-ALIGN: right
}
.formTable td{
PADDING-RIGHT: 22px; BORDER-TOP: #aba2ce 1px solid; PADDING-LEFT: 22px; PADDING-BOTTOM: 4px; PADDING-TOP: 4px; BACKGROUND-COLOR: #e2ebf7;TEXT-ALIGN: left
}
.listTable {
    empty-cells: show; min-width: 60%; max-width: 90%
}
.listTable th{
	PADDING-RIGHT: 22px; BORDER-TOP: #aba2ce 1px solid; PADDING-LEFT: 22px; FONT-WEIGHT: bold; PADDING-BOTTOM: 4px; COLOR: #000; PADDING-TOP: 4px; BACKGROUND-COLOR: #e2ebf7; TEXT-ALIGN: center
}
.listTable td{
PADDING-RIGHT: 22px; BORDER-TOP: #aba2ce 1px solid; PADDING-LEFT: 22px; PADDING-BOTTOM: 4px; PADDING-TOP: 4px; BACKGROUND-COLOR: #e2ebf7;TEXT-ALIGN: center
}
    

.data_text_star{
	color:red;
}


.scoll{
		overflow:auto;
		height:300px;
		width:832px;
	}
.down{
	position:absolute;
	left:0px;
	top:350px;
	
	height:370px;
	z-index:100;
	filter:alpha(opacity=0);
	background-color:#CCCCCC;
}
.up{
	position:absolute;
	left:0px;
	top:0px;
	width:820px;
	height:285px;
	z-index:100;
	filter:alpha(opacity=0);
	background-color:#CCCCCC;
}
.read_only
{
	border:0;
	background-color:#ffffff;
}
.statue
{
	position:absolute;
	left:280px;
	top:0px;
	width:400px;
	height:150px;
	
	}
.msgscoll
{
		overflow:auto;
		position:absolute;
		width:100%;
		height:100%;
		background-color:#FFF4EB;
}
.locktop
{
	background-color:#00ccff;
	position: relative; 
	top: expression(this.offsetParent.scrollTop);
}

</style>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<TITLE>New Document</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
	

	</HEAD>
<script type="text/javascript">
var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) { /*ignore*/ }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
}
function retrieveBook(spnid,typeid) {
    var paperid='<s:property value="paperid"/>'
	if(bXmlHttpSupport) {
       var sUrl = '<%=request.getContextPath() %>/findtypefenshu.action?typeId='+typeid+'&paperId='+paperid;
       var oRequest = new XMLHttpRequest();
       oRequest.onreadystatechange = function() {
       		if(oRequest.readyState == 4) {
       			var paperfenshu = eval('(' + oRequest.responseText + ')');
       			var spa=document.getElementById(spnid)
       			spa.innerHTML=paperfenshu;
       		}
       }
       oRequest.open('POST', sUrl);
       oRequest.send(null);
    }
}
function goback(){
	window.location.href="<%=request.getContextPath() %>/papersServlet?actionType=query&sjZt=-1";
}
</script>
<script>
	var time=parseInt(60)
	var now1 = new Date();
	var m1 = now1.getMinutes()+(time-1);
	var s1 = now1.getSeconds()+(60);
	
	function getRemainTime(){
		var now = new Date();
		var m = now.getMinutes();
		var s = now.getSeconds();
		document.getElementById("remaintime").innerHTML=(m1-m)+":"+(s1-s);
		setTimeout("getRemainTime()",1000);
	}
	function count(){
	}
	
	function savePaper(){
		document.autoform.submit();
	}
	
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}
	function deleteSt(paperQuenId,paperid){
		window.location.href="delePaperQuestionAction.action?paperid="+paperid+"&paperQuenId="+paperQuenId;
		
	}
	function addSt(paperId,questionTypeId){
		window.location.href="selectQuestionsAction?paperId="+paperId+"&typeId="+questionTypeId;
	}
	function validatetoadd(){
		var fenshuhas='<s:property value="fenshu"/>';
		var fenshu='<s:property value="pager.sjZf"/>';
		if(fenshuhas>=fenshu){
			return false;
		}
	}
	
	//word导出	
	function jumpViewPaper(sjid){
	window.location.href="Paper_word.action?paperid="+sjid;
}
</script>

<%
String []arry=new String[]{"A","B","C","D","E","F"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);
%>

<BODY>
	<h1 style="font-size:24px; font-family:黑体; color:#06374A; text-align:center;"><s:property value="eqnswerpaper.epapers.sjMc"/></h1>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
    <td>	
    		试卷总分: <s:property value="eqnswerpaper.epapers.sjZf"/>分&nbsp;&nbsp;&nbsp;
			试卷得分:<s:property value="eqnswerpaper.djZf"/>分&nbsp;&nbsp;&nbsp;
			考试时间: <s:date name="eqnswerpaper.djKssj"/> --<s:date name="eqnswerpaper.djJssj"/>
			参考人员: <s:property value="eqnswerpaper.djRymc"/>
    </td>
    </tr>
</table>
<%int zl=0; %>	
<s:iterator value="equestiontypes" id="type">
	<s:property value="type.priority"/>
	<!--定义一个试题类型 -->
	<s:set name="priority" value="#type.priority"/>
	<!--将集合过滤，过滤成符合这个类型的 -->
	<s:set name="eanswerquestionse" value="eanswerquestionses.{?#this.epaperquestions.equestions.equestiontype.id==#priority}"/>
	<!--判断过滤后的集合不为空 -->
	<s:if test="#eanswerquestionse.size!=0">
				<%zl++; %>
				
	<table width="90%" border="0" align="center" cellpadding="0" cellspacing="10" style="font-size:12px;font-family:宋体; border:solid 1px #D2E8FF">								
		<tr>
		 <td style="color:#004A80;font-family:'黑体'; font-size:18px">			
				【<%=arry1[zl-1] %>】.&nbsp;<s:property value="#type.name"/>&nbsp;（共<s:property value="#eanswerquestionse.size"/>题,共<SPAN id='<s:property value="#type.id"/>fenshuSpan'><script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>');</script></SPAN>分）
		</td>
		</tr>
		<!-- 遍历题目 -->
		<s:iterator value="#eanswerquestionse" status="index" id="eapq">
				<!-- 显示题干 -->
		<tr>
		 <td style="font-weight:bold; padding-left:45px;">
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stTg)" escape="false"/>&nbsp;&lt;<s:property value="#eapq.epaperquestions.sjStfs"/>分&gt;
		                 <s:if test="#eapq.epaperquestions.equestions.stWh!=null"><span class="blues">&lt;<s:property value="#eapq.epaperquestions.equestions.stWh"/>&gt;</span></s:if>
						<s:else>&nbsp;</s:else>
						<s:if test="#eapq.epaperquestions.equestions.stCc!=null"><span class="blues">&lt;<s:property value="#eapq.epaperquestions.equestions.stCc"/>&gt;</span></s:if>
						<s:else></s:else>
		</td>
		 </tr>			
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="#eapq.epaperquestions.equestions.stXx" id="querson">
					<s:iterator status="wenti">
								<s:if test="%{#priority==1 || #priority==2}">
								<tr><td style="color:#555; padding-left:60px;"><s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property /></td></tr>
								</s:if>

					</s:iterator>
					</s:generator>
					<!-- 答案 -->
		<tr>
	    <td align="right">
	    <div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF; height:23px; line-height:23px; font-size:14px; padding:0 5px; color:#555; margin-left:60px;">
					  <span class="score">得分：<s:property value="#eapq.stDf"/></span>
					    <b>标准答案：<s:generator separator="||" val="#eapq.epaperquestions.equestions.stDa">
										<s:iterator>
											<s:property/>
										</s:iterator>
									</s:generator></b>
								　　回答：<s:generator separator="||" val="#eapq.stDa">
										<s:iterator>
											<s:property/>
										</s:iterator>
									</s:generator>
		</div></td>
		</tr>							
				</s:if>
				<!-- 如果是判断题 -->
				<!-- 答案 -->
				<s:elseif test="#priority==3" >
	<tr>
	     <td style="padding-left:60px; line-height:22px;">
		   <div style="background:#f1f1f1;padding:5px">
				  <span class="score">得分：<s:property value="#eapq.stDf"/></span>
					 <b>标准答案：
									<s:if test='#eapq.epaperquestions.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else></b>
						回答：<s:if test='#eapq.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else>
			</div></td>
		</tr>										
				</s:elseif>
				
				<s:elseif test="#priority==4" >
		<tr>
	     <td style="padding-left:60px; line-height:22px;">
		   <div style="background:#f1f1f1;padding:5px">
				   <span class="score">得分：<s:property value="#eapq.stDf"/></span>
						<b>标准答案：
									<s:if test='#epq.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else>
								<br>答题说明：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDasm)" escape="false"/>
						</b>
                        回答：<s:if test='#eapq.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else><br>
                       答题说明：<s:property value="#eapq.stDasm"/>	
            </div></td>
		</tr>	           
				</s:elseif>
				
				<s:elseif test="#priority==5">
							<tr>
						    <td>
									<object align="middle"
										classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id='MediaPlayer<s:property value="#epq.equestions.stId"/>'
										width="400" height="69">
										<param name="ShowStatusBar" value="-1">
										<param name="AutoStart" value="0">
										<param name="Filename" value='<s:property value="##epq.equestions.stFjlj"/>'>
										<embed type="application/x-oleobject"
											codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
											flename="mp" src="" width=200 height=50></embed>
									</object>
							</td></tr>
		           <tr>
		           <td style="padding-left:60px; line-height:22px;">
		       <div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF;line-height:23px; font-size:12px; padding:0 5px; color:#555;">
				   <span class="score">得分：<s:property value="#eapq.stDf"/></span>
								<b>标准答案：
									<s:property escape="false" value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDa)"/>
						                </b> <br>
                        回答：<s:property value="#eapq.stDa"/>
                </div></td>
		    </tr>        
				           

				</s:elseif>
				
				<s:else>
		<tr>
		    <td style="padding-left:60px; line-height:22px;">
		<div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF;line-height:23px; font-size:12px; padding:0 5px; color:#555;">	   
				   <span class="score">得分：<s:property value="#eapq.stDf"/></span>
								<b>标准答案：
									<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDa)" escape="false" />
						                </b> <br>
                        回答：<s:property value="#eapq.stDa"/>	
				
			 </div></td>
		    </tr>     	
		</s:else>				
		</s:iterator>
	</table>
	</s:if>
</s:iterator>


</BODY>
	
</HTML>
		