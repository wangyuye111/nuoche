<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath()+"/uiface";	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no">
<title>订座信息</title>

<script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script> 

</head>
<body>

	<form action="<%=path%>/seat?p0=A-user-add&p1=ordertable"
		method="POST">
		<input type="hidden" name="user_id" value="${reList[0]['info-userid']}">
		<input type="hidden" name="order_id" value="${reList[0]['info-orderid']}">
		<input type="hidden" name="user_tel" value="${reList[0]['info-tel']}">
		<input type="hidden" name="seat_id" value="${reList[0]['info-seatid']}">
		<input type="hidden" name="seat_note" value="${reList[0]['info-seatnote']}">
		<input type="hidden" name="reserve_time" value="${reList[0]['info-reservetime']}">
		<input type="hidden" name="reserve_section" value="${reList[0]['info-reservesection']}">
		<input type="hidden" name="repast_sum" value="${reList[0]['info-repastsum']}">
		<input type="hidden" name="has_park" value="${reList[0]['info-park']}">
		<input type="hidden" name="add_seat" value="${reList[0]['info-addseat']}">
		<input type="hidden" name="has_baby" value="${reList[0]['info-hasbaby']}">
		<input type="hidden" name="baby_seat" value="${reList[0]['info-babyseat']}">
		<input type="hidden" name="has_waiter" value="${reList[0]['info-waiter']}">
		<input type="hidden" name="order_dish" value="${reList[0]['info-orderdish']}">
		<input type="hidden" name="arrive_time" value="${reList[0]['info-arrivetime']}">
		
	<div><span>预订单编号:</span><span>${reList[0]['info-orderid']}</span></div>
	<div><span>会员等级:</span><span>${reList[0]['member']}</span></div>
	<div><span>预订人姓名:</span><span>${reList[0]['username']}</span></div>
	<div><span>预定人电话:</span><span>${reList[0]['info-tel']}</span></div>
	<div><span>预订座位:</span><span>${reList[0]['info-seatnote']}</span></div>
	<div><span>就餐人数:</span><span>${reList[0]['info-repastsum']}</span></div>
	<div><span>是否有婴儿:</span><span>${reList[0]['info-hasbaby']}</span></div>
	<div><span>预订微信号:</span><span>${reList[0]['weixinnumber']}</span></div>
	<div><span>预计到达时间:</span><span>${reList[0]['info-repastsum']}</span></div>
	<div><span>是否预订停车位:</span><span>${reList[0]['info-park']}</span></div>
	<div><span>是否预先点菜:</span><span>${reList[0]['info-orderdish']}</span></div>
	<div><span>保留时间:</span><span>${reList[0]['info-arrivetime']}</span></div>
	<div><input type="submit" value="提交" style="width:50%;"></div>
	</form>
</body>
</html>