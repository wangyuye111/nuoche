<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

%>

<!DOCTYPE HTML>
<html>
<head>

<title>弹出框</title>
<link href="css/alert-tele.css" rel="stylesheet" type="text/css" />
<script src="jquery-3.1.1.min.js"></script>
<script type="text/javascript"> 
function showAlert() {
	document.body.style.overflow = "hidden"; 
	$("#alert_div").fadeIn("slow");
	$("#mask").fadeIn("slow");
} 

function hideAlert(){ 
	$("#alert_div").fadeOut("slow");
	$("#mask").fadeOut("slow");
	document.body.style.overflow = ""; 
} 
</script>
</head>
<body>
    <div onclick="showAlert()">000</div>
	<div id="alert_div">
		<div class="height_alert">
			<div class="float_right" onclick="hideAlert()">
				<i class="icon-remove font_3 "></i>
			</div>
			<!-- <div class="xian"></div> -->
			<div style="font-size: 0.9rem;">
			  会员卡余额&nbsp;:&nbsp;&nbsp;${relist[0].remaining}&nbsp;&nbsp;<a style="background-color:red; color:white; font-size:10px;">查看会员卡</a>
			</div>
			<div style="font-size: 0.9rem;">
			本次支付&nbsp;:&nbsp;&nbsp;${relist[0].yingfu_money}
			</div>
			<div class="button_center">
				<input type="text" class="button_style" style="width: 12rem;"
					id="telePhoneNumber" value="" placeholder="请填写密码" value="${relist[0].card_password}" />
			</div>
			<!-- <div class="xian" style="margin-top: 1rem;"></div> -->
			<div class="div_buttom">
				<input type="button" class="button_buycar" 
					value="确定支付" href="<%=path%>rp?p0= A-user-search&p1=zhifuwancheng&p2=${relist[0].remaining}&p3=${relist[0].yingfu_money}&p4=${relist[0].card_password}"/>
			</div>
			<div class="div_buttom1">
				<input type="button" class="button_buycar1" 
					value="取消支付" onclick="window.close()"/>
			</div>
		</div>
	</div>
	<div id="mask" onclick="hideAlert()"></div>	
</body>
</html>