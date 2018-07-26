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
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;"/>
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<title>我的会员卡</title>
</head>
<body>
<div class="head_height">
我的会员卡
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
<c:choose>
  <c:when test="${empty str}">
	<a href="<%=path%>/ServletInOut?p0=A-user-search&p1=user_table&p2=">
	<div style="width:80%;height:40px;text-align:center;margin: 0 auto;margin-top:5%;background:red;color:white;line-height: 40px;border-radius: 10px;font-weight: bold;">创建会员卡</div>
	</a>
  </c:when>
  <c:when test="${!empty str}">
	<a href="<%=path%>/ServletInOut?p0=A-user-search&p1=user_table_search&p2=">
	<div style="width:80%;height:40px;text-align:center;margin: 0 auto;margin-top:5%;background:#FFCC01;color:white;line-height: 40px;border-radius: 10px;font-weight: bold;">查看会员卡</div>
	</a>
  </c:when>
</c:choose>
</body>
</html>