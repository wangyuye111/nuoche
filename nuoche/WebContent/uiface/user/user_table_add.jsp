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
<title>创建会员卡</title>
<style type="text/css">
.span_name{
    width: 95px;
    display: -webkit-inline-box;
    text-align: -webkit-right;
        font-size: 16px;
}
.tijao_add{
    background-color: red;
    width: 150px;
    height: 40px;
    border-radius: 5px;
    border: 0px;
    color: white;
    font-size: 20px;
    margin-top: 20px;
}
html,body{
    background: white;
}
input{
text-align: center;
}
</style>
<script type="text/javascript">
function tijiao() {
	var tele = document.getElementById("telephone").value;   
	if (document.ThisForm.username.value == "") {
		alert("请输入会员名称");
		return false;
	}else if(document.ThisForm.weixinnumber.value == ""){
		alert("请输入微信号");
		return false;
	}else if(document.ThisForm.telephone.value == ""){
		alert("请输入手机号码");
		return false;
	}else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(tele))){
		alert("手机号码输入不正确");
		return false;
	}else if(document.ThisForm.card_password.value == ""){
		alert("请输入会员卡密码");
		return false;
	}else if(document.ThisForm.qr_password.value == ""){
		alert("请输入确认密码");
		return false;
	}else if(document.ThisForm.qr_password.value != document.ThisForm.card_password.value){
		alert("密码输入不一致");
		return false;
	}else{
		document.ThisForm.submit();
	}
	
}
</script>
</head>
<body>
<div class="head_height">
查看会员卡
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
<form method="post" action="<%=path %>/ServletInOut?mode=A-user-add&p1=user_table_add" id="ThisForm" name="ThisForm">
<table  style="margin:0 auto; width:80%; font-size:20px;margin-top:20px">
	<c:forEach var="map" items="${reList}">
	<input type="hidden" value="${map['id']}" id="uesrid" name="uesrid"/>
	<tr>
	<td><sapn class="span_name">会员卡号:</sapn>
	<input type="text" value="${map['card_number']}" 
		id="card_number" name="card_number" style="border-radius: 5px; width:50%;" readonly="readonly"/>
	</td>
	</tr>
	<tr >
	<td><sapn class="span_name">会员名称:</sapn>
	<input type="text" value="" 
		id="username" name="username" style="border-radius: 5px;width:50%;"/>
	</td>
	</tr>
	<tr >
	<td><sapn class="span_name">微信号:</sapn>
	<input type="text" value="" 
		id="weixinnumber" name="weixinnumber" style="border-radius: 5px;width:50%;"/>
	</td>
	</tr>
	<tr >
	<td><sapn class="span_name">电话号码:</sapn>
	<input type="text" value="" 
		id="telephone" name="telephone" style="border-radius: 5px;width:50%;"/>
	</td>
	</tr>
	<tr >
	<td><sapn class="span_name">会员卡密码:</sapn>
	<input type="password" value="" 
		id="card_password" name="card_password" style="border-radius: 5px;width:50%;"/>
	</td>
	</tr>
	<tr>
	<td><sapn class="span_name">确认密码:</sapn>
	<input type="password" id="qr_password" value=""  style="border-radius: 5px;width:50%;"/>
	</td>
	</tr>
	<tr>
	<td colspan="3" style="text-align:center">
	<input type="button"  class="tijao_add"
			value="提交" onclick="tijiao()" />
	</td>
	</tr>
	</c:forEach>
	</table>
</form>

<script>

</script>
</body>
</html>