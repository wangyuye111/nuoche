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
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;">
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<title>呼叫服务员</title>
<style type="text/css">
html,body{
    background: white;
}
</style>
</head>
<body>
<div class="head_height">
呼叫服务员
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
<!-- <div style="margin:0 auto;width:80%;height:%40;font-size:20px;margin-top:20px;"> -->
<div style="margin:0 auto;width:82%;/*height:40%;*/font-size:20px;margin-top:20px;font-size:14px;line-height: 30px;">
<div style="width:50%;float:left; display:inline;">
催促上菜&nbsp<label"><input type="checkbox"
		style="width:20px;height:20px;position: relative;top: 4px;" name="box" value="催促上菜"/></label>
</div>
<div style="width:50%;float:left;display: inline;">
添加水/饮料&nbsp<label"><input type="checkbox"
		style="width:20px;height:20px;position: relative;top: 4px;" name="box" value="添加水/饮料"/></label>
</div>
<div style="width:50%;float:left; display:inline;">
要求上菜&nbsp<label"><input type="checkbox"
		style="width:20px;height:20px" name="box" value="要求上菜"/></label>
		</div>
<div style="width:50%;float:left;display: inline;">
		餐具座位问题&nbsp<label"><input type="checkbox"
		style="width:20px;height:20px;position: relative;top: 4px;" name="box" value="餐具座位问题"/></label>
		</div>
<div style="width:50%;float:left; display:inline;">
		服务问题&nbsp<label"><input type="checkbox"
		style="width:20px;height:20px;position: relative;top: 4px;" name="box" vaule="服务问题"/></label>
		</div>
<div style="width:50%;float:left;display: inline; ">
		座位号&nbsp<input type="text"style="width:50%;
		height:20px;border-radius: 5px;"
		value="${reList[0].seat_number }" />
		</div>
<div style="width:100%;float:left;display: inline; ">
	其他问题&nbsp
	<input type="text"style="width:60%;
	height:20px;border-radius: 5px;"/>
	</div>
</div>
<div style="height:40px;padding:20px 10px;clear: both;margin: 0 10%;">
<input type="button"  value="发送" 
		style="width:40%; height:35px; font-size:20px;
		text-align:center;background:red;color:white;
		border-radius: 8px;border: 0px;"/>
<input type="button" value="呼叫"
		style="width:40%; height:35px; float:right;
		font-size:20px;text-align:center;background:red;
		color:white;border-radius: 8px;border: 0px;"/>
</div>
<script type="text/javascript" src="<%=path %>/lib/jquery/1.9.1/jquery.min.js"></script> 

<script type="text/javascript">
function getcheckbox(){
	var arr=document.getElementsByName("box");
	var a = [];
	for(i in arr){
	if(arr[i].checked){
	a.push(arr[i].value);}
	}
	
}

</script>
</body>
</html>