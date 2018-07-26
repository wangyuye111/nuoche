<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath()+"/uiface";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	
<link href="css/alert-tele1.css" rel="stylesheet" type="text/css" />
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
<style>
  
</style>

<body>
    <div onclick="showAlert()">确认支付</div>
	<div id="alert_div">
		<div class="height_alert">
			<div class="float_right" onclick="hideAlert()">
				<i class="icon-remove font_3 "></i>
			</div>
			
			<div style="font-size: 1.0rem;">
			 本次消费&nbsp;:&nbsp;&nbsp;${relist[0].yingfu_money}
			</div><br>
			<div style="font-size: 1.0rem;">
			实付金额&nbsp;:&nbsp;&nbsp;${relist[0].versemen}
			</div><br>
			<div style="font-size: 1.0rem;">
			会员卡余额&nbsp;:&nbsp;&nbsp;${relist[0].remaining}
			</div><br>
			<div style="font-size: 1.5rem;">
			     谢谢您的光临
			</div><br>
			
			
			
		</div>
		</div>
	
	<div id="mask" onclick="hideAlert()"></div>	
</body>
</html>