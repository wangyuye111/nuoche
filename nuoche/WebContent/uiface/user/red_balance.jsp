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
<title>我的红包</title>
<style type="text/css">
html,body{
    background: white;
}

</style>
</head>
<body style="background: white;">
<div class="head_height">
我的红包
</div>
<div style="margin:0 auto;font-size:20px; width:80%">
		<p>红包规则:转发公众微信号和菜品图片,均有机会获得红包奖励哦~快去转发吧~</p>
		<p style="color:red">我的红包:${reInt}个</p>
<div style="margin:0 auto;font-size:20px ; 
	 width:80%; padding-bottom: 25px;border:2px 
	 solid black; text-align:center;margin-bottom: 25px;border-radius: 10px;">
	 <p>微信公众号</p>
<img src="" style="width: 160px;height: 160px;"/><br>
<input type="submit" value="分享到朋友圈" style="background:red;
 	 color:white; text-align:center; height:40px;border: 0;border-radius: 10px;width: 111px;margin-top: 10px;"/>
</div>
</div>
<div style="height:50px;'"></div>
</body>
</html>