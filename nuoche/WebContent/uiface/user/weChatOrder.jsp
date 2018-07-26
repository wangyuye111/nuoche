<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	String path = request.getContextPath() + "/uiface";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;">
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />	
<title>微信点餐</title>
	
	<style>
html,body{
background: white;
}
		.dishes{	
    width: 40%;
    height: 80px;
    margin: 0px;
    padding: 0px;
    border: 2px solid #000000;
    text-align: center;
    line-height: 80px;
    border-radius: 5px;
    font-size: 30px;
    color: #FFAC06;
    text-decoration: none;
    background-color: #FF0000;
    /* width: 40%; */
    display: block;
    float: left;
    /* margin: 20px 4%; */
    margin-top: 20px;
    margin-left: 6%;
		}
		/* #dishesmove1{
			position: absolute;
			bottom:70%;
			left:5%;
		} */
		#dishesmove2{
			position: absolute;
			bottom:70%;
			right:5%;
		}
		#dishesmove3{
			position: absolute;
			bottom:54%;
			left:5%;
		}
		#dishesmove4{
			position: absolute;
			bottom:54%;
			right:5%;
		}
		#dishesmove5{
			position: absolute;
			bottom:38%;
			left:5%;
		}
		#dishesmove6{
			position: absolute;
			bottom:38%;
			right:5%;
		}
		.orderdishes{
			width:100%;
			height:400px;
		}
	</style>

</head>
<body margin:0px auto;>
<div class="head_height">
微信点餐
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
	<div class="orderdishes">                                                          <!-- userid是上一个页面传给我的 -->
	<!-- 微信点餐页面发请求调Dishes页面之后，Dishes页面要根据菜品类别category进行查询菜的所有记录，找出第一条记录显示到Dishes页面，通过加减按键，发送ajax请求更新页面对应的菜品数据 -->
		<c:forEach var="map" items="${reList}">
			<a id="dishesmove1" class="dishes" href="<%=path%>/ServletInOut?p0=A-red-search&p1=Dishes&p2=1&p3=${map['id']}&p4=">${map['dish_class_name']}</a>
		</c:forEach>                          <!-- p0:--; p1:Dishes; p2:userid;  p3:dish_class:菜品分类表的id,这个id对应菜品种类 -->
		<%-- <a id="dishesmove2" class="dishes" href="<%=path%>/rp?p0=A-user-search&p1=Dishes&p2=userid&p3=hot-dishes">热菜</a>
		<a id="dishesmove3" class="dishes" href="<%=path%>/rp?p0=A-user-search&p1=Dishes&p2=userid&p3=sea-food">海鲜</a>
		<a id="dishesmove4" class="dishes" href="<%=path%>/rp?p0=A-user-search&p1=Dishes&p2=userid&p3=soup">汤类</a>
		<a id="dishesmove5" class="dishes" href="<%=path%>/rp?p0=A-user-search&p1=Dishes&p2=userid&p3=staple-food">主食</a>
		<a id="dishesmove6" class="dishes" href="<%=path%>/rp?p0=A-user-search&p1=Dishes&p2=userid&p3=drinks">酒水饮料</a> --%>
	</div>
<div style="height:50px;clear: both;"></div>
</body>
</html>