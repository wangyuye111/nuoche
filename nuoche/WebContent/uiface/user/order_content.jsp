<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>我的评价</title>
<script src="jquery-3.1.1.min.js"></script>
<script src="<%=path %>/user/js/jquery-3.1.1.min.js"></script>
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/user/css/alert-tele.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(function(){
	// 就餐
  $("#cha").click(function(){
  	$("#jiucan").val("很差");
  	$("#select_red").attr('src', '<%=path %>/user/img/select_red_yes.png');  
  	$("#select_yellow").attr('src', '<%=path %>/user/img/select_yellow.png'); 
  	$("#select_green").attr('src', '<%=path %>/user/img/select_green.png'); 
  });
  $("#yiban").click(function(){
  	$("#jiucan").val("一般");
  	$("#select_red").attr('src', '<%=path %>/user/img/select_red.png');  
  	$("#select_yellow").attr('src', '<%=path %>/user/img/select_yellow_yes.png'); 
  	$("#select_green").attr('src', '<%=path %>/user/img/select_green.png'); 
  });  
  $("#good").click(function(){
  	$("#jiucan").val("很好");
  	$("#select_red").attr('src', '<%=path %>/user/img/select_red.png');  
  	$("#select_yellow").attr('src', '<%=path %>/user/img/select_yellow.png'); 
  	$("#select_green").attr('src', '<%=path %>/user/img/select_green_yes.png'); 
  }); 
  // 服务水平
  $("#cha_fuwu").click(function(){
  	$("#fuwu").val("很差");
  	$("#select_red_fuwu").attr('src', '<%=path %>/user/img/select_red_yes.png');  
  	$("#select_yellow_fuwu").attr('src', '<%=path %>/user/img/select_yellow.png'); 
  	$("#select_green_fuwu").attr('src', '<%=path %>/user/img/select_green.png'); 
  });
  $("#yiban_fuwu").click(function(){
  	$("#fuwu").val("一般");
  	$("#select_red_fuwu").attr('src', '<%=path %>/user/img/select_red.png');  
  	$("#select_yellow_fuwu").attr('src', '<%=path %>/user/img/select_yellow_yes.png'); 
  	$("#select_green_fuwu").attr('src', '<%=path %>/user/img/select_green.png'); 
  });  
  $("#good_fuwu").click(function(){
  	$("#fuwu").val("很好");
  	$("#select_red_fuwu").attr('src', '<%=path %>/user/img/select_red.png');  
  	$("#select_yellow_fuwu").attr('src', '<%=path %>/user/img/select_yellow.png'); 
  	$("#select_green_fuwu").attr('src', '<%=path %>/user/img/select_green_yes.png'); 
  }); 
  // 价格水平
  $("#cha_jiage").click(function(){
  	$("#jiage").val("很差");
  	$("#select_red_jiage").attr('src', '<%=path %>/user/img/select_red_yes.png');  
  	$("#select_yellow_jiage").attr('src', '<%=path %>/user/img/select_yellow.png'); 
  	$("#select_green_jiage").attr('src', '<%=path %>/user/img/select_green.png'); 
  });
  $("#yiban_jiage").click(function(){
  	$("#jiage").val("一般");
  	$("#select_red_jiage").attr('src', '<%=path %>/user/img/select_red.png');  
  	$("#select_yellow_jiage").attr('src', '<%=path %>/user/img/select_yellow_yes.png'); 
  	$("#select_green_jiage").attr('src', '<%=path %>/user/img/select_green.png'); 
  });  
  $("#good_jiage").click(function(){
  	$("#jiage").val("很好");
  	$("#select_red_jiage").attr('src', '<%=path %>/user/img/select_red.png');  
  	$("#select_yellow_jiage").attr('src', '<%=path %>/user/img/select_yellow.png'); 
  	$("#select_green_jiage").attr('src', '<%=path %>/user/img/select_green_yes.png'); 
  });     
});
function tijiao() {
		//document.ThisForm.submit();
		//alert("谢谢您反馈的意见，本次获赠的积分是10");
		/**/
		//showAlert2();
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=path %>/ServletInOut?mode=A-user-add&p1=order_content_add',
        data:$('#ThisForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	showAlert2();
        }
    });
	}
function showAlert2() {
	document.body.style.overflow = "hidden"; 
	$("#alert_div2").fadeIn("slow");
	$("#mask").fadeIn("slow");
}
function queding(){
	window.location.href="<%=path %>/ServletInOut?mode=A-user-search&p1=MyOrder&id=&state=未评价";
}
</script>
<style>
select{
-webkit-appearance: none;	
    border: 0;
    background: #FD001C;
    color: white;
    padding: 2px;
    line-height: 15px;
        height: 20px;
}
</style>
</head>

<body>
<div class="head_height">
我的评价
</div>
<div class="div_head_right">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img" onclick="window.close()"/><br>取消
</div>
<div class="div_one">
<form method="post" action="<%=path %>/ServletInOut?mode=A-user-add&p1=order_content_add" id="ThisForm" name="ThisForm">
	<div class="feicaipin">非菜品评价</div>
	<div class="dispaly_line">
		<div>就餐环境:</div>
		<input type="hidden" id="id" name="user_id" value="${reList[0].user_id} ">
		<input type="hidden" id="order_id" name="order_id" value="${reList[0].order_id} ">
		<div class="select_font_size" id="cha">很差<br><img src="<%=path %>/user/img/select_red.png" alt="alt" title="title" id="select_red"/></div>&nbsp;
		<div class="select_font_size" id="yiban">一般<br><img src="<%=path %>/user/img/select_yellow.png" alt="alt" title="title" id="select_yellow"/></div>&nbsp;
		<div class="select_font_size" id="good">很好<br><img src="<%=path %>/user/img/select_green_yes.png" alt="alt" title="title" id="select_green"/></div>
	</div>
	<div class="mysuggest">我的建议：<input type="text" name="dining_suggest" class="input_radius" value="很好"/></div>
	<input type="hidden" id="jiucan" name="dining_environment" value="很好">

	<div class="dispaly_line">
		<div>服务水平:</div>
		<div class="select_font_size" id="cha_fuwu">很差<br><img src="<%=path %>/user/img/select_red.png" alt="alt" title="title" id="select_red_fuwu"/></div>&nbsp;
		<div class="select_font_size" id="yiban_fuwu">一般<br><img src="<%=path %>/user/img/select_yellow.png" alt="alt" title="title" id="select_yellow_fuwu"/></div>&nbsp;
		<div class="select_font_size" id="good_fuwu">很好<br><img src="<%=path %>/user/img/select_green_yes.png" alt="alt" title="title" id="select_green_fuwu"/></div>
	</div>
	<div class="mysuggest">我的建议：<input type="text" name="service_suggest" class="input_radius" value="很好"/></div>
 <input type="hidden" id="fuwu" name="service_level" value="很好">
	<div class="dispaly_line">
		<div>价格水平:</div>
		<div class="select_font_size" id="cha_jiage">很差<br><img src="<%=path %>/user/img/select_red.png" alt="alt" title="title" id="select_red_jiage"/></div>&nbsp;
		<div class="select_font_size" id="yiban_jiage">一般<br><img src="<%=path %>/user/img/select_yellow.png" alt="alt" title="title" id="select_yellow_jiage"/></div>&nbsp;
		<div class="select_font_size" id="good_jiage">很好<br><img src="<%=path %>/user/img/select_green_yes.png" alt="alt" title="title" id="select_green_jiage"/></div>
	</div>
	<div class="mysuggest" >我的建议：<input type="text" name="price_level" class="input_radius" value="很好"/></div>
 <input type="hidden" id="jiage" name="price_suggest" value="很好">
	<div class="cppj">菜品评价</div>
	<c:forEach var="map" items="${reList}">
	<div class="dispaly_line">
		<div>${map['content']} :</div>
		<input type="hidden" id="content" name="${map['content']}" value="${map['content']}">
	<div style="width: 78%;">
		<div class="select_font_size float_left">咸淡度<br><select name="a${map['id']}">
		<option>适中</option>
		<option>咸</option>
		<option>淡</option></select></div>
		<div class="select_font_size float_left">油腻度<br><select name="b${map['id']}">
		<option>适中</option>
		<option>油</option>
		<option>淡</option></select></div>
		<div class="select_font_size float_left">外观感受度<br><select name="c${map['id']}">
		<option>适中</option>
		<option>好</option>
		<option>差</option></select></div>
		<div class="select_font_size float_left">辛辣度<br><select name="d${map['id']}">
		<option>适中</option>
		<option>辣</option>
		<option>淡</option></select></div>
	</div>
	</div>
	<div class="mysuggest" style="margin-top: 10px;">我的建议：<input type="text" class="input_radius" name="mysuggest${map['id']}"/></div>
	</c:forEach>
	
	<div class="xian_gray margin_top10"></div>
	
	<div class="tijaio_div" onclick="tijiao()"> <span>提交</span></div>
</form>
</div>
	<div id="alert_div2">
		<div class="height_alert" style="line-height: 30px;text-align: center;">
			<div style="font-size: 1rem;margin-top: 2rem;">谢谢您反馈的意见，本次获赠的积分是10分！</div>
			<div class="div_buttom" style="padding:10px 30px;" onclick="queren_jifen()">
				<input type="button" class="button_buycar" style=" width: 6rem;background:#FF0000;" id="phoneNumberPerfect"
					value="确定" onclick="queding()"/>
			</div>
		</div>
	</div>	
<div id="mask" ></div>		
</body>
</html>