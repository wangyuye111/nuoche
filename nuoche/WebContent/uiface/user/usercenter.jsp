<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath()+"/uiface";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>个人中心</title>
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	$("#order_table").click(function() {
		window.location.href = '<%=path%>/seat?p0=A-user-search&p1=tablelist&p2=tojsp';
	});
});
</script>
</head>

<body>
<div class="center_top ">
	<div class="font_size20 color_huang">个人中心</div>
	<div class="margin_top10"><img src="https://img.alicdn.com/bao/uploaded/i3/2671194415/TB2t6M.aGm5V1Bjy1zeXXcTCFXa_!!2671194415.jpg_q50.jpg" alt="alt" title="title" class="center_img"/></div>
	<div class="center_nickname margin_top10">00000</div>
</div>
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=MyOrder&id=">
<div class="center_line_white">
	我的订单
	<img src="<%=path %>/user/img/right_jiantou.png" alt="alt" title="title" class="rgiht_jiantou"/>
</div>
</a>
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=intergral&id=">
<div class="center_line_white margin_top3">
	我的积分
	<img src="<%=path %>/user/img/right_jiantou.png" alt="alt" title="title" class="rgiht_jiantou"/>
</div>
</a>
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=red_balance&id=">
<div class="center_line_white margin_top3">
	我的红包
	<img src="<%=path %>/user/img/right_jiantou.png" alt="alt" title="title" class="rgiht_jiantou"/>
</div>
</a>
<a href="<%=path %>/ServletInOut?mode=A-user-search&p1=card_number_search&id=">
<div class="center_line_white margin_top3">
	我的会员卡
	<img src="<%=path %>/user/img/right_jiantou.png" alt="alt" title="title" class="rgiht_jiantou"/>
</div>
</a>
<div class="center_line_white margin_top10">
	绑定手机号
	<img src="<%=path %>/user/img/right_jiantou.png" alt="alt" title="title" class="rgiht_jiantou"/>
</div>
<div class="center_line_white margin_top3">
	修改昵称
	<img src="<%=path %>/user/img/right_jiantou.png" alt="alt" title="title" class="rgiht_jiantou"/>
</div>
<div style="height:100px;"></div>

<div class="div_bac">
	<div id="order_table">
		<div>
			<img src="<%=path %>/user/img/diancan.png" alt="alt" title="title" class="dibu_img"/>
		</div>
		<div>座位预定</div>
	</div>
	<div>
		<div>
			<img src="<%=path %>/user/img/xiaoxi.png" alt="alt" title="title" class="dibu_img"/>
		</div>
		<div>我的消息</div>
	</div>
	<div>
		<div>
			<img src="<%=path %>/user/img/youhui.png" alt="alt" title="title" class="dibu_img"/>
		</div>
		<div>优惠活动</div>
	</div>
	<div>
		<div>
			<img src="<%=path %>/user/img/center_red.png" alt="alt" title="title" class="dibu_img"/>
		</div>
		<div class="color_red_dibu">个人中心</div>
	</div>	
</div>
</body>
</html>