<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>菜单详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;">
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/user/js/jquery-3.1.1.min.js"></script>
<style>
html,body{
    background: white;
}
	/* #fdingbu{
		width:100%;
		height:50px;
		background-color:red;
		line-height: 50px;
		text-align: center;
		color: yellow;
	} */
	.ftubiao{
		font-size:5px;
		float:right;
	}
	.fone{
		/*width:100%;
		height:110px;
		margin-left:6%;*/
    width: 90%;
    height: 110px;
    /* margin-left: 6%; */
    margin: 0 5%;
	}
	
	
	.ftu{
		margin-top:10px;
		width:100px;
		height:100px;
		background-color: red;
		float:left;
	}
	.ftxt{
		margin-top:10px;
		width:38%;
		height:100px;
		float:left;
		font-size: 15px;
		line-height: 25px;
		font-family: "黑体";
		font-weight: bold;
		/*text-align: center;*/
		margin-left:5px;
	}
	.fbtn{
		width:70px;
		height:100px;
		float:left;
		position:relative;
		margin-top:10px;
	}
	
	.fin{
		position:absolute;
		top:40%;
		background-color:#FF0000;
		color:#FFCC01;
		border:0px;
		border-radius: 5px;
		font-size:18px;
		/*width:80%;*/
		left:10%;
		    text-align: center;
	}
	/* .fhr{
		margin-top:150px;
	} */
	hr{
		margin-top:10px;
	}
	.fqueren{
		margin: 0 auto;
		display: block;
		height:40px;
		width:70%;
		font-size:20px;
		color:white;
		background-color:#FF0000;
		margin-top:10px;
		border:0px;
		border-radius: 5px;
		line-height:40px;
		text-align: center;
		margin-bottom: 20px;
	}
	.ftubiao{
		float:right;
	}
	.ftotal{
		width:50%;
		margin:0 auto;
		text-align: center;
	}
	.fasstotal{
		width:50%;
		margin:0 auto;
		color:#FF0000;
		text-align: center;
		margin-top:10px;
	}
	
	.fa{
		font-size:5px;
		color:#FFCC01;
		margin-right:10px;
		text-decoration:none;
	}
	a{ 
text-decoration:none; 
color:white; 
} 
</style>

<script type="text/javascript">
	/* function callback(){
		
	} */
	function remove1(a){
		var ffz = document.getElementById("ffz");
		var id = document.getElementById("gs"+a);
		$.ajax({
			type: 'POST',
			url: '<%=path %>/ServletInOut?mode1=A-buy-delete&mode=remove&user_id=1&mode2='+a,
			data: "",
			dataType: 'json',
			success: function(data){
				//ffz.removeChild("gs"+data.id);
				$("#" + "gs"+data.id).remove();  
				//var to =date;
				//var num =to.indexOf('a');
				document.getElementById("to").innerHTML = data.total;
				document.getElementById("hto").innerHTML = data.htotal; 
				//layer.msg('已移除!',{icon:1,time:1000});
				document.getElementById("alert_ts").style.display="block";
				setTimeout("show()",2000);
			},
			error:function(data) {
				console.log(data.msg);
			},
		});	
		
	}
	function show(){
		document.getElementById("alert_ts").style.display="none"
	}	
	<%-- function sub(a){

		$.ajax({
			type: 'POST',
			url: '<%=path %>/RedpacketInOutUser&mode1=A-user-add&mode1=order_up&&mode2=a',
			dataType: 'json',
			success: function(data){
				layer.msg('已提交!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});	
		
		
	} --%>
	
function tijiao(){
	$.ajax({
		type: 'POST',
		url: '<%=path%>/ServletInOut?p0=A-buy-search&p1=order_panduan&p2=${reList[0].user_id}',
		dataType: 'json',
		success: function(data){
			if(data.result=="已订餐"){
				alert("已订餐");
			}else{
				window.location.href="<%=path%>/ServletInOut?p0=A-buy-add&p1=order_up&p2=${reList[0].user_id}"; 
			}
		},
		error:function(data) {
			alert("请求失败");
		},
	});		
}	
</script>


</head>
<body style="margin:0px;">
<div class="head_height">
菜单详情
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
	<div id="ffz">
		<c:forEach var="map" items="${reList}" varStatus="status">
		
			<!-- 第一个div开始 -->
			<div class="fone" id="gs${map['id1'] }">
				<div class="ftu">
					<img alt="" src="${map['photo'] }" style="width: 100%;">
				</div>
				<div class="ftxt">
					<span style="display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;">${map['name'] }</span><span id="price">${map['price'] }</span><span>X</span><span id="num">${map['num'] }</span>
				</div>
				<div class="fbtn">
					<input type="button" value="移除" class="fin" onclick="remove1(${map['id1'] })"/>
				</div>
			</div>
			<!-- 第一个div结束 -->
			
			
		</c:forEach>
	</div>
	<!-- 蓝色分界线开始 -->
		<hr width="88%" color="#72FFFD" style="height:0.001px;"/>
	<!-- 蓝色分界线结束 -->
	<div class="ftotal">
		<span>总计:</span>
		￥<span id="to">${reList1[0].total}</span>
	</div>

		<c:if test="${reList1[0].htotal!=null}">
			<div class="fasstotal">
				<span>会员价格:</span>
				￥<span id="hto">${reList1[0].htotal}</span>
			</div>
		</c:if>
		
		
		
	<!-- 提交按钮开始 -->
	<a class="fqueren" href=" javascript:void(0)" onclick="tijiao();" <%-- onclick="sub(${reList[0].user_id})" --%>>
	<div class="fquerend">
		<%-- <input type="submit" value="提交订单" class="fqueren" onclick="sub(${reList[0].user_id})"/> --%>
		提交订单
	</div>
	</a>
	<!-- 提交按钮结束 -->
<div style="height:50px;"></div>	
<style>
.alert1_ts{
	position: fixed;
    top: 80%;
    width: 100%;
    text-align: center;
    display:none;
}
.alert1-text{
    background: rgba(0, 0, 0, 0.5);
    border-radius: 20px;
    color: white;
    width:auto;
    display:inline;
    padding:5px 10px;
}
</style>
<div id="alert_ts" class="alert1_ts" style="">
<div class="alert1-text" >已移除</div>
</div>	
</body>
</html>