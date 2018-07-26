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
<title>查看会员卡</title>
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.span_name{
    width: 95px;
    display: -webkit-inline-box;
    text-align: -webkit-right;
        font-size: 16px;
}
html,body{
    background: white;
}
input{
text-align: center;
}
</style>
</head>
<body>
<div class="head_height">
查看会员卡
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
<table  style="margin:0 auto; width:80%; font-size:20px;margin-top:20px">
	<c:forEach var="map" items="${reList}">
	<tr >
	<td ><sapn class="span_name">会员卡号:</sapn>
	<input type="text" value="${map['card_number']} " 
		 readonly="readonly" style="border-radius: 5px;width:50%;"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">会员名称:</sapn>
	<input type="text" value="${map['username']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">微信号:</sapn>
	<input type="text" value="${map['weixinnumber']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">电话号码:</sapn>
	<input type="text" value="${map['telephone']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">消费积分:</sapn>
	<input type="text" value="${map['expenditure_integral']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">已换积分:</sapn>
	<input type="text" value="${map['exchange_ integral']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">积分余额:</sapn>
	<input type="text" value="${map['remaining_integral']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">消费记录:</sapn>
	<input type="text" value="${map['record']}" 
		 readonly="readonly" style="border-radius: 5px;width:50%"/>
	</td>
	</tr>

	<tr>
	<td><sapn class="span_name">会员等级:</sapn>
            		<input type="text" value="${map['member']}" 
		 readonly="readonly" style="/*background:yellow;*/
			 border-radius: 5px;width:50%"/>

	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">充值余额:</sapn>
	<input type="text" value="${map['remaining']}" 
		name="card_number" readonly="readonly" style="border-radius: 
		5px;width:50%"/>
	</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>