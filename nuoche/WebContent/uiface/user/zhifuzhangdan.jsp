<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />	
<link href="<%=path %>/user/css/alert-tele.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/user/js/jquery-3.1.1.min.js"></script>
<title>支付账单</title>
<script type="text/javascript">
function huiyuankazhifu(){
	$.ajax({
		type: 'POST',
		url: '<%=path%>/ServletInOut?p0=A-order-search&p1=huiyuankazhifu&id=',
		data: "",
		dataType: 'json',
		success: function(data){
			$("#bencizhifu").text($("#new_price").text());
			$("#yue").text(data.result);
			showAlert();
		},
		error:function(data) {
			console.log(data.msg);
		},
	});		
}
function showAlert() {
	document.body.style.overflow = "hidden"; 
	$("#alert_div").fadeIn("slow");
	$("#mask").fadeIn("slow");
} 
function showAlert2() {
	document.body.style.overflow = "hidden"; 
	$("#alert_div2").fadeIn("slow");
	$("#mask").fadeIn("slow");
} 
function hideAlert(){ 
	$("#alert_div").fadeOut("slow");
	$("#alert_div2").fadeOut("slow");
	$("#alert_div_jifen").fadeOut("slow");
	$("#alert_div_hongbao").fadeOut("slow");
	$("#mask").fadeOut("slow");
	document.body.style.overflow = ""; 
} 

function querenzhifu(){
	var pwd=$("#member_pwd").val();
	var price=$("#new_price").text();
	$("#shifujine").text(price);
	/* var price=${reList[0].les_membres};
	if(price=='0.0'){
		price=price_yuan;
	} */
	var hongbao=$("#user_hongbao").text();
	var jifen=$("#user_jifen").text();
	var order_id=${reList[0].order_id};
	$.ajax({
		type: 'POST',
		url: '<%=path%>/ServletInOut?p0=A-order-search&p1=zhifuwancheng&id=&pwd='+pwd+'&price='+price+'&order_id='+order_id+'&hongbao='+hongbao+'&jifen='+jifen,
		data: "",
		dataType: 'json',
		success: function(data){
			if(data.result=="付款成功"){
			$("#hykye").text(data.result2);
			hideAlert();
			showAlert2();
			}
			if(data.result=="密码错误"){
				alert("密码错误");
			}
			if(data.result=="余额不足"){
				alert("余额不足");
			}
		},
		error:function(data) {
			console.log(data.msg);
		},
	});		
}
function showAlert_jifen() {
	document.body.style.overflow = "hidden"; 
	$("#alert_div_jifen").fadeIn("slow");
	$("#mask").fadeIn("slow");
} 
function alert_div_jifen(){
	$.ajax({
		type: 'POST',
		url: '<%=path%>/ServletInOut?p0=A-order-search&p1=jifen&id=',
		data: "",
		dataType: 'json',
		success: function(data){
			$("#jifen").text(data.result);
			hideAlert();
			showAlert_jifen();
		},
		error:function(data) {
			console.log(data.msg);
		},
	});		
}
function showAlert_hongbao() {
	document.body.style.overflow = "hidden"; 
	$("#alert_div_hongbao").fadeIn("slow");
	$("#mask").fadeIn("slow");
} 
function alert_div_hongbao(){
	$.ajax({
		type: 'POST',
		url: '<%=path%>/ServletInOut?p0=A-order-search&p1=hongbao&id=',
		data: "",
		dataType: 'json',
		success: function(data){
			$("#hongbao").text(data.result);
			hideAlert();
			showAlert_hongbao();
		},
		error:function(data) {
			console.log(data.msg);
		},
	});		
}
function queren_jifen(){
	var aa=$("#jifen").text();
	var bb=$("#jifen_num").val();
	var price_old=$("#price_old").text();
	var hongbao=$("#user_hongbao").text();
	if(parseFloat(aa)>=parseFloat(bb)){
		$("#user_jifen").text($("#jifen_num").val());
		var new_price=$("#new_price").text();
		if((parseFloat(price_old)-parseFloat(hongbao)-parseFloat(bb))>0){
			$("#new_price").text(parseFloat(price_old)-parseFloat(hongbao)-parseFloat(bb));
		}else{
			$("#new_price").text("0.0");
		}
	}else{
		alert("输入的数值大于可用积分数量");
	}

	hideAlert();
}
function queren_hongbao(){
	var aa=$("#hongbao").text();
	var bb=$("#hb_num").val();
	var price_old=$("#price_old").text();
	var jifen=$("#user_jifen").text();
	
	if(parseInt(aa)>=parseInt(bb)){
		$("#user_hongbao").text($("#hb_num").val());
		var new_price=$("#new_price").text();
		if((parseFloat(price_old)-parseFloat(jifen)-parseFloat(bb))>0){
		$("#new_price").text(parseFloat(price_old)-parseFloat(jifen)-parseFloat(bb));
		}else{
			$("#new_price").text("0.0");
		}
	}else{
		alert("输入的数值大于可用红包金额");
	}
	hideAlert();
}
</script>
</head>
<style>
 html,body{
    background: white;
}   
   .dingdan{
    margin-left:10px;
    line-height:30px;
   }
   .yingfu_money{
    margin-left:10px;
   line-height:30px;
   }
   .jifen_money{
    margin-left:10px;
   line-height:30px;
   }
   .hongbao_money{
    margin-left:10px;
   line-height:30px;
   }
   .shifu_money{
    margin-left:10px;
   line-height:30px;
   }
   
   .weixin{
	background-color: #A3FF00;
    height: 40px;
    /* width: 100px; */
    margin-top: 10px;
    text-align: center;
    color: white;
    width: 35%;
    float: left;
    margin-left: 10%;
    line-height: 40px;
    border-radius: 10px;
   }
   .huiyuanka{
    background-color: #00F8FF;
    height: 40px;
    /* width: 120px; */
    margin-top: 10px;
    text-align: center;
    color: white;
    width: 35%;
    float: right;
    margin-right: 10%;
    line-height: 40px;
    border-radius: 10px;
   }
  
</style>
<body >
<div class="head_height">
支付账单
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
	<div class="dingdan">
	    订单编号：${reList[0].order_id}
	</div>
	<c:if test="${reList[0].les_membres =='0.0'}">
	<div class="yingfu_money">
	   应付金额：<span id="price_old">${reList[0].sum}</span>元
	</div>
	</c:if>
	<c:if test="${reList[0].les_membres !='0.0'}">
	<div class="yingfu_money">
	   应付金额：<span id="price_old">${reList[0].les_membres}</span>元
	</div>
	</c:if>	
	<div class="jifen_money">
	  积分换币：<a style="color:red;width: 60px;display: inline-block;" ><span id="user_jifen">0</span>元</a><span onclick="alert_div_jifen()" style="background-color:red; color:white; font-size:10px;padding: 3px;">兑换积分</span>
	  
	</div>
	<div class="hongbao_money">
	    我的红包：<a style="color:red;width: 60px;display: inline-block;" ><span id="user_hongbao">0</span>元</a><span onclick="alert_div_hongbao()" style="background-color:red; color:white; font-size:10px;padding: 3px;">使用红包</span>
	</div>
	<div style="height:1px; align:center; background-color:red;margin: 0 10px;"></div>
	<c:if test="${reList[0].les_membres !='0.0'}">
	<div class="shifu_money">
	 实付金额：<span id="new_price">${reList[0].les_membres}</span>元
	</div>
	</c:if>
	<c:if test="${reList[0].les_membres =='0.0'}">
	<div class="shifu_money">
	 实付金额：<span id="new_price">${reList[0].sum}</span>元
	</div>
	</c:if>
	<div>
		<a href="#">
		<div class="weixin">
		     <img src="<%=path%>/user/img/weixin.png" style="width:25px; height:25px;position: relative;top: 6px;"/>微信支付
		</div>
		</a>
		<%-- <a href="<%=path%>/ServletInOut?p0=A-order-search&p1=huiyuankazhifu&p2=${reList[0].versement}"> --%>
		<div class="huiyuanka" onclick="huiyuankazhifu()">
		     <img src="<%=path%>/user/img/huiyuanka.png" style="width:25px; height:25px;position: relative;top: 6px;"/>会员卡支付
		</div>
		<!-- </a> -->
	</div>
	
	
	<div id="alert_div">
		<div class="height_alert">
			
			<div style="font-size: 1rem;margin-left: 10px;">会员卡余额：<span id="yue"></span>元</div>
		
			<div style="font-size: 1rem;margin-top:5px;margin-left: 10px;">本次支付：<span id="bencizhifu"></span>元</div>

			<div class="button_center">
				<input type="text" class="button_style" style="width: 12rem;"
					id="member_pwd" value="" placeholder="请填写密码" />
			</div>
			<div class="xian" style="margin-top: 1rem;"></div>
			<div class="div_buttom" style="padding:10px 30px;" onclick="querenzhifu()">
				<input type="button" class="button_buycar" style=" width: 13rem;background:#FF0000;" id="phoneNumberPerfect"
					value="确认支付" />
			</div>
			<div class="div_buttom" style="padding:0px 30px;" onclick="hideAlert()">
				<input type="button" class="button_buycar" style=" width: 13rem;background:#00FF00;" id="phoneNumberPerfect"
					value="取消支付" />
			</div>
		</div>
	</div>
	<div id="alert_div2">
		<div class="height_alert" style="line-height: 30px;text-align: center;">
			<div style="font-size: 1rem;margin-top: 2rem;">本次消费：<c:if test="${reList[0].les_membres =='0.0'}">${reList[0].sum}</c:if><c:if test="${reList[0].les_membres !='0.0'}">${reList[0].les_membres}</c:if>元</div>
			<div style="font-size: 1rem;">实付金额：<span id="shifujine"></span>元</div>
			<div style="font-size: 1rem;margin-top:5px">会员卡余额：<span id="hykye"></span>元</div>
			
		</div>
	</div>	
	<div id="alert_div_jifen">
		<div class="height_alert" style="line-height: 30px;text-align: center;">
			<div style="font-size: 1rem;margin-top: 1rem;">可用积分：<span id="jifen"></span></div>
			<div style="font-size: 1rem;">积分换币：<input type="text" class="button_style" style="width: 12rem;"
					id="jifen_num" value="0" placeholder="请填写要兑换的积分" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></div>
			<div class="div_buttom" style="padding:10px 30px;" onclick="queren_jifen()">
				<input type="button" class="button_buycar" style=" width: 13rem;background:#FF0000;" id="phoneNumberPerfect"
					value="确认兑换" />
			</div>
			
		</div>
	</div>	
	<div id="alert_div_hongbao">
		<div class="height_alert" style="line-height: 30px;text-align: center;">
			<div style="font-size: 1rem;margin-top: 1rem;">可用红包：<span id="hongbao"></span></div>
			<div style="font-size: 1rem;">选择使用红包：<input type="text" class="button_style" style="width: 12rem;"
					id="hb_num" value="0" placeholder="请填写要使用的红包" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></div>
			<div class="div_buttom" style="padding:10px 30px;" onclick="queren_hongbao()">
				<input type="button" class="button_buycar" style=" width: 13rem;background:#FF0000;" id="phoneNumberPerfect"
					value="确认使用" />
			</div>
		</div>
	</div>		
	<div id="mask" onclick="hideAlert()"></div>		
</body>
</html>