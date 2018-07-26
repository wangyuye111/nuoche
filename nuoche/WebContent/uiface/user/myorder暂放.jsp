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
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<title>我的订单</title>
<script src="<%=path %>/user/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"> 
function deletea(){ 
	var aaa = document.getElementsByName("aaa");
	var bbb = document.getElementsByName("bbb");
	var ccc = document.getElementsByName("ccc");
	var ddd = document.getElementsByName("ddd");
	for(var i=0;i<aaa.length;i++){
		if(aaa[i].style.display=='none'){
			aaa[i].style.display='';
	}
	}
	for(var i=0;i<bbb.length;i++){
		bbb[i].style.display='none';
	}
	for(var i=0;i<ccc.length;i++){
		ccc[i].style.display='none';
	}
	for(var i=0;i<ccc.length;i++){
		ddd[i].style.display='none';
	}
}
function deleteb(){ 
	var aaa = document.getElementsByName("aaa");
	var bbb = document.getElementsByName("bbb");
	var ccc = document.getElementsByName("ccc");
	var ddd = document.getElementsByName("ddd");
	for(var i=0;i<aaa.length;i++){
		if(aaa[i].style.display=='none'){
			aaa[i].style.display='';
	}
	}
	for(var i=0;i<bbb.length;i++){
		bbb[i].style.display='none';
	}
	for(var i=0;i<ccc.length;i++){
		ccc[i].style.display='none';
	}
	for(var i=0;i<ddd.length;i++){
		ddd[i].style.display='none';
	}
}
function deletec(){ 
	var aaa = document.getElementsByName("aaa");
	var bbb = document.getElementsByName("bbb");
	var ccc = document.getElementsByName("ccc");
	var ddd = document.getElementsByName("ddd");
	for(var i=0;i<aaa.length;i++){
		if(aaa[i].style.display=='none'){
			aaa[i].style.display='';
	}
	}
	for(var i=0;i<bbb.length;i++){
		bbb[i].style.display='none';
	}
	for(var i=0;i<ccc.length;i++){
		ccc[i].style.display='none';
	}
	for(var i=0;i<ddd.length;i++){
		ddd[i].style.display='none';
	}
}
</script> 
<style>
.line_one{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 60%;
}
</style>
</head>

<body>
<div class="head_height">
我的订单
</div>
<img src="<%=path %>/user/img/back.png" alt="alt" title="title" class="back_img" 
		onClick="javascript :history.back(-1);"/>
<div class="line_height50">
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=MyOrder&id=&state=待付款">
	<div class="float_left33" >待付款</div>
</a>
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=MyOrder&id=&state=未评价">
	<div class="float_left33" onclick="deleteb()">未评价</div>
</a>
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=MyOrder&id=&state=已退款">
	<div class="float_left33" onclick="deletec()">已退款</div>
</a>	
	<div style="clear:both;"></div>
</div>
<c:forEach var="map" items="${reList}">
<%-- <c:choose>
<c:when test="${map['money_state']=='已付款' }"> --%>
<div class="div_order_bac" name="ddd">
	<div><img src="https://img.alicdn.com/bao/uploaded/i3/2671194415/TB2t6M.aGm5V1Bjy1zeXXcTCFXa_!!2671194415.jpg_q50.jpg" alt="alt" title="title" class="order_img"/></div>
	<div class="order_content">
		<div>${map['order_id']}</div>
		<div class="font_size12">下单时间：<br>${map['time']}</div>
		<div>总价：<c:if test="${map['les_membres']==''}">${map['sum']}</c:if><c:if test="${map['les_membres']!=''}">${map['les_membres']}</c:if>
		</div>
	</div>
	<div class="order_content_right">
	<c:if test="${map['money_state']=='未评价'}">
		<div class="order_border_no_yellow">已付款</div>
		<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=order_content&order_id=${map['order_id']}">
		<div class="order_border_yellow">去评价</div>
		</a>

		<div class="order_border_yellow">再次预定</div>
	</c:if>
	<c:if test="${map['money_state']=='待付款'}">
		<div class="order_border_yellow">去付款</div>
		<div class="order_border_yellow">再次预定</div>
	</c:if>	
	<c:if test="${map['money_state']=='已退款'}">
		<div class="order_border_no_yellow">已退款</div>
		<div class="order_border_yellow">再次预定</div>
	</c:if>		
	</div>
</div>
<%-- </c:when>
<c:when test="${map['money_state'] == '待付款'}">
<div class="div_order_bac" name="aaa">
	<div><img src="https://img.alicdn.com/bao/uploaded/i3/2671194415/TB2t6M.aGm5V1Bjy1zeXXcTCFXa_!!2671194415.jpg_q50.jpg" alt="alt" title="title" class="order_img"/></div>
	<div class="order_content">
		<div>${map['order_id']}</div>
		<div class="font_size12">下单时间：${map['time']}</div>
		<div>总价：${map['VIP_price']}</div>
	</div>
	<div class="order_content_right">
		<div class="order_border_yellow">去付款</div>
		<div class="order_border_yellow">再次预定</div>
	</div>
</div>
</c:when>
<c:when test="${map['money_state'] == '已付款'} ">
<div class="div_order_bac" name="bbb" >
	<div><img src="https://img.alicdn.com/bao/uploaded/i3/2671194415/TB2t6M.aGm5V1Bjy1zeXXcTCFXa_!!2671194415.jpg_q50.jpg" alt="alt" title="title" class="order_img"/></div>
	<div class="order_content">
		<div>${map['order_id']}</div>
		<div class="font_size12">下单时间：${map[time]}</div>
		<div>总价：${map['out_of_pocket']}</div>
	</div>
	<div class="order_content_right">
		<div class="order_border_yellow">已付款</div>
		<div class="order_border_yellow" href="<%=path%>/ServletInOut?
		mode=A-user-add&p1=order_content&p2=${map[order_id]}">去评价</div>
		<div class="order_border_yellow">再次预定</div>
	</div>
</div>
</c:when>
<c:when test="${map['money_state'] == '已退款'}">
<div class="div_order_bac" name="ccc">
	<div><img src="https://img.alicdn.com/bao/uploaded/i3/2671194415/TB2t6M.aGm5V1Bjy1zeXXcTCFXa_!!2671194415.jpg_q50.jpg" alt="alt" title="title" class="order_img"/></div>
	<div class="order_content">
		<div>${map['order_id']}</div>
		<div class="font_size12">下单时间：${map['time']}</div>
		<div>总价：${map['out_of_pocket']}</div>
	</div>
	<div class="order_content_right">
		<div class="order_border_yellow">已退款</div>
		<div class="order_border_yellow">再次预定</div>
	</div>
</div>
</c:when>
</c:choose> --%>
</c:forEach>
</body>
</html>