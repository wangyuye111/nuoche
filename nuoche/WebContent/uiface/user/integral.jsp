<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;">
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />	
<title>我的积分</title>
<style type="text/css">
html,body{
    background: white;
}
</style>
</head>
<body >
<div class="head_height">
我的积分
</div>
 <div style="margin:0 auto;font-size:20px; width:80%">
		<p>积分规则:座位预订和订单评论,均有机会获得积分奖励哦~</p>
		
<div style="margin:0 auto;width:80%;height:40px;">
<input type="submit" href="boss/.jsp" value="去下单" 
		style="width:45%; height:40px; font-size:20px;
		text-align:center;background:red;color:white;
		border-radius: 8px;border: 0px;"/>
<input type="submit" href="boss/.jsp" value="去评论"
		style="width:45%; height:40px; float:right;
		font-size:20px;text-align:center;background:red;
		color:white;border-radius: 8px;border: 0px;"/>
</div>

</div>
<div style="margin:0 auto;font-size:20px ; 
	 width:80%; border:1px 
	 solid black; text-align:center;line-height:20px;
	 margin-top:20px;border-radius: 5px;padding-bottom: 20px;">
	 <p>积分详情</p>
	 <a style="letter-spacing:5px;">全部积分:</a>${reList[0].expenditure_integral+reList[0].remaining_integral}<br/>
	 <p >已消费积分:${reList[0].expenditure_integral}</p>
	 <a style="letter-spacing:5px;">剩余积分:</a>${reList[0].remaining_integral}<br/>
</div>
</body>
</html>