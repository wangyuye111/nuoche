<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;">
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />	
<script src="<%=path %>/user/js/jquery-3.1.1.min.js"></script>
<title>菜品详情</title>
<!-- <link rel="stylesheet" type="text/css" href="css/myHotDishes.css"> -->

	<style>
	
		.theadcancletext{
			width:80px;
			height:30px;
			margin:opx;
			padding:0px;
			color:#FFCB01;
			text-align:center;
			line-height:30px;
			/* 垂直居中的行高要和该div的高度一致 */
			border:1px solid red;
		}
		
		.theadcanclebutton{
			width:30px;
			height:30px;
			margin:0px;
			padding:0px;
			font-size:10px;
			color:#FFCB01;
			text-align:center;
			
			border:1px solid #38BD21;
		}
		
		/* tbody部分 */
		.tbody{
			width:100%;
			height:70%;
			background-color:#FFFFFF;
			margin:0px auto;
			padding:0px;
			border:0px;
			color:#FFFFFF;
		}
		.introduce{
			width:85%;
			height:55%;
			margin:0px auto;
			padding:0px;
			position:relative;
			    display: flex;
			padding-top: 25px;
		}
		
		/* 加载菜品图片，和介绍详情,在introduce区域内 */
		.dishesImage{
			width:150px;
			height:150px;
			/*position: absolute;
			left:10%;
			top:12.5%;*/
			background-color:#998E8B;
		}
		.dishesPrice{
			/*width:35%;*/
			/*height:37%;*/
			/*position: absolute;
			top:13%;
			right:2%;*/
			background-color:#FFFFFF;
		}
		.dishesIntroduce{
			/*width:35%;
			height:37%;*/
			/*position: absolute;
			bottom: 12%;
			right:2%;*/
			background-color:#FFFFFF;
		}
		
		.behavior{
			width:100%;
			height:35%;
			background-color:#FFFFFF;
		}
		
		.behavior1{
			width:100%;
			height:25%;
			position:relative;
		}
		
		.behaviorchoose{
			width:20%;
			height:33px;
			border:0px;
			position: absolute;
			top: 0;
			left: 10%;
			border-radius: 5px;
			font-size:16px;
			color:#FFFFFF;
			background-color:#FF0000;
		}
		
		.behaviorshare{
			width:33%;
			height:33px;
			margin:0px auto;
			padding:0px;
			border:0px;
			text-align:center;
			line-height:35px;
			position: absolute;
			top: 0;
			left: 32.5%;
			border-radius: 5px;
			font-size:16px;
			color:#FFFFFF;
			text-decoration:none;
			background-color:#FF0000;
		}
		
		.behaviornumber{
			width:20%;
			height:33px;
			margin:0;
			padding:0;
			border:1px solid #000000;
			vertical-align:middle;
			position: absolute;
			top: 0;
			left: 68%;
			border-radius: 5px;
			background-color:#FFFFFF;
			padding-left:3px;
		}
		
		.behaviornumberadd{
		    width: 20px;
		    height: 50%;
		    margin: 0px;
		    padding: 0px;
		    border: 0px;
		    position: absolute;
		    top:0;
		    left: 56%;
		    background-color: #FFFFFF;
		}
		.behaviornumberreduce{
			width: 20px;
		    height: 50%;
		    margin: 0px;
		    padding: 0px;
		    border: 0px;
		    position: absolute;
		    bottom:0;
		    left: 56%;
		    background-color: #FFFFFF;
		}
		.behaviornumberrecieve{
		    color: #000000;
		    margin: 0px;
		    padding: 0px;
		    border: 0px;
		    /*position: absolute;
		    bottom: 10%;*/
		    background-color: #FFFFFF;
		}
		.behaviornumbertext{
		    font-size: 13px;
		    color: #000000;
		    margin: 0px;
		    padding: 0px;
		    border: 0px;
		    /*position: absolute;
		    bottom: 15%;
		    left: 23%;*/
		    background-color: #FFFFFF;
		}
		
		.behavior2{
			width:100%;
			height:50%;
			margin:0px;
			padding:0px;
			line-height:54.5938px;
			text-align:center;
			position:relative;
		}
		
		.behaviorcheck{
			width:80%;
			height:33px;
			margin:0px;
			padding:0px;
			line-height:33px;
			border:0px;
			position: absolute;
			top:25%;
			left:10%;
			border-radius: 5px;
			font-size:16px;
			color:#FFFFFF;
			text-decoration:none;
			/* 移动去掉控件剩余宽度的一半 */
			background-color:#FF0000;
		}
		
		.behavior3{
			width:100%;
			height:25%;
			margin:0px;
			padding:0px;
			text-align:center;
			position:relative;
		}
		
		.behaviorsub{
			width:80%;
			height:33px;
			margin:0px;
			padding:0px;
			border:0px;
			line-height:33px;
			
			position: absolute;
			left:10%;
			
			border-radius: 5px;
			font-size:16px;
			color:#FFFFFF;
			text-decoration:none;
			background-color:#FF0000;
		}
		
	</style>
	<!-- 点击上下箭头，会增加份数的数量值 -->
	<script>	
	<!-- 点击上箭头，会增加份数的数量值 -->
		function addNum(){
			var num = document.getElementById("numFen").innerHTML;
			num ++;
			document.getElementById("numFen").innerHTML = num;
		}
		
		 function reduceNum(){
			var num = document.getElementById("numFen").innerHTML;
			if(num <= 1){
				document.getElementById("numFen").innerHTML = 1;
			}else if(num > 1){
				num --;
				document.getElementById("numFen").innerHTML = num;
			}
		} 
		
		/* 点击选择按钮，把数量值存入Shopping_trolley表中*/
		function choose(){
			var num = document.getElementById("numFen").innerHTML;
			var caiId = ${reList[0].id };
			$.ajax({
					type: 'POST',
					/* 本页面的hotDishes访问 地址，model2表示的是Shopping_trolley的User_id，
					model3是Shopping_trolley表中cai_Id，model4是 Shopping_trolley表中的Num*/
					url: '<%=path %>/ServletInOut?mode0=A-red-add&mode1=hotDishes&model2=1&model3='+caiId+'&model4='+num,
					data: "",
					dataType: 'json',
					success: function(data){
						if(data.result == 'success'){
							//alert("已选择");
							document.getElementById("alert_ts").style.display="block";
							setTimeout("show()",2000);
						}
					},
					error:function(data) {
						console.log(data.msg);
					},
				});	
		}
		function show(){
			document.getElementById("alert_ts").style.display="none"
		}		
	</script>
</head>
<body >
<div class="head_height">
菜品详情
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
	
	<div class="tbody">
		<div class="introduce">
		<!-- 加载数据 -->
			<img src="${reList[0].photo }" class="dishesImage" />
			<div style="padding-left: 10px;">
			<div class="dishesPrice">
				<span style="font-size:16px;color:#3B3B3B;">门市价：${reList[0].price }元</span><br/>
				<!-- 如果不是会员就隐藏2行会员价的详情 -->
				<c:if test="${reList[1].number_level !='非会员'}">	
				<span style="font-size:15px;color:#3B3B3B;">会员价：${reList[0].price * reList[1].number_level_discounts}元</span><br/>
				<span style="font-size:10px;color:#3B3B3B;">(您是本店${reList[1].number_level }，享受${reList[1].number_level_discounts_in }折优惠)</span>
				</c:if>
			</div>
			<div class="dishesIntroduce">
				<span style="font-size:15px;color:#3B3B3B;">菜品介绍：</span>
				<span style="font-size:10px;color:#3B3B3B;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;">${reList[0].introduced }</span>
				<span></span>
			</div>
			</div>
		</div>
		<div class="behavior">
			<div class="behavior1">
				<input class="behaviorchoose" type="button" name="" value="选择" onclick="choose()"/>
				<a class="behaviorshare" href="#">分享到朋友圈</a>
				<!-- <input class="behaviorshare" type="submit" name="" value="分享到朋友圈"/> -->
				<div class="behaviornumber" name="behaviorchoosenumber">
					<span id="numFen" class="behaviornumberrecieve" name="number" style="font-size: 20px;line-height: 35px;">1</span><span class="behaviornumbertext">份</span>
					<input class="behaviornumberadd" type="button" name="" value="+" onclick="addNum()"/>
					<input class="behaviornumberreduce" type="button" name="" value="-" onclick="reduceNum()"/>
				</div>
			</div>
			<div class="behavior2">
				<a class="behaviorcheck" href="<%=path %>/ServletInOut?mode=A-user-search&p1=card_number_search&id=">查看会员状况</a>
			</div>
			<div class="behavior3">
				<a class="behaviorsub" href="<%=path %>/ServletInOut?mode=A-buy-search&p11=diancai&id=">提交订单</a>
			</div>
		</div>
	</div>
	
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
<div class="alert1-text" >添加成功</div>
</div>
</body>
</html>