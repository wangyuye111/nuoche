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
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<link href="<%=path %>/user/css/style.css" rel="stylesheet" type="text/css" />	
<title>订单详情</title>
</head>
<style>
 html,body{
    background: white;
}
  
   .dingdan{
   margin-left:10px;
    line-height:30px;
    margin-top: 10px;    
   }
   .zuowei{
   margin-left:10px;
   line-height:30px;
   }
   .time{
   margin-left:10px;
   line-height:30px;
   }
   .statut{
   margin-left:10px;
   line-height:30px;
   }
   .zhuangtai{
   margin-left:10px;
   line-height:30px;
   }
   .activites{
    margin-left: 10px;
    line-height: 30px;
    border-top: 1px solid #eee;
    margin-right: 10px;
   }
   .hongshao{
   margin-left:10px;
   line-height:30px;
   }
    .hongmen{
    margin-left:10px;
    line-height:30px;
   }
   .canju{
   margin-left:10px;
    line-height:30px;
   }
   .mifan{
   margin-left:10px;
    line-height:30px;
   }
   .sum{
	margin-left: 10px;
    line-height: 30px;
    border-top: 1px solid #eee;
    margin-right: 10px;
   }
  
  .huiyuan{
  margin-left:10px;
   line-height:30px;
     color:red;
   }
   
  
   
   .zhifu{
    line-height: 35px;
    width: 80%;
    height: 35px;
    background-color: #FD001C;
    margin: 0px auto;
    color: white;
    text-align: center;
    border-radius: 5px;
    /* margin: 0 10%; */
    margin-left: 10%;
    /* margin-right: 10%; */
	margin-top:10px;
	margin-bottom: 20px;
   }
</style>
<body >
<div class="head_height">
订单详情
</div>
<div class="div_head_right" onClick="javascript :history.back(-1);">
 <img src="<%=path %>/user/img/cancel.png" alt="alt" title="title" class="cancel_img"/><br/>取消
</div>
<div class="div_head_left">
 <img src="<%=path %>/user/img/hujiao.png" alt="alt" title="title" class="cancel_img"/><br/>服务员
</div>
	
	<div class="dingdan">
	    订单编号:&nbsp;&nbsp;${reList[0].order_id}
	</div>
	<div class="zuowei">
	    座位号码:&nbsp;&nbsp;${reList[0].floor}${reList[0].seat_number}
	</div>
	<div class="time">
	  下单时间:&nbsp;&nbsp;${reList[0].time}
	</div>
	<div class="statut">
	  付款状态:&nbsp;&nbsp;${reList[0].money_state}
	</div>
	<c:if test="${reList[0].member =='钻石会员'}">	
	<div class="zhuangtai">
	  会员状态:&nbsp;&nbsp;钻石会员
	</div>
	</c:if>
	<c:if test="${reList[0].member =='白金会员'}">	
	<div class="zhuangtai">
	  会员状态:&nbsp;&nbsp;白金会员
	</div>
	</c:if>
	<c:if test="${reList[0].member =='黄金会员'}">	
	<div class="zhuangtai">
	  会员状态:&nbsp;&nbsp;黄金会员
	</div>
	</c:if>
	<c:if test="${reList[0].member !='黄金会员' && reList[0].member !='白金会员' && reList[0].member !='钻石会员'}">	
	<div class="zhuangtai">
	  会员状态:&nbsp;&nbsp;非会员
	</div>
	</c:if>
	<div class="activites">
	  订单内容:
	</div>
	<c:forEach var="map" items="${reList1}">
	<div class="hongshao">
	 ${map['content']}:${map['univalence']}×${map['num']}
	</div>
	<%-- <div class="hongmen">
	  ${map['content']}:&nbsp;&nbsp;${map['univalence']}"×"${map['num']}
	</div>
	<div class="canju">
	  ${map['content']}:&nbsp;&nbsp;${map['univalence']}"×"${map['num']}
	</div>
	<div class="mifan">
	  ${map['content']}:&nbsp;&nbsp;${map['univalence']}"×"${map['num']}
	 </div> --%>
	 </c:forEach>
	 <div class="sum">
	   总计:&nbsp;&nbsp;${reList1[0].sum}
	 </div>
	 <c:if test="${reList1[0].les_membres !='0.0'}">
	 <div class="huiyuan">
	  会员价:&nbsp;&nbsp;${reList1[0].les_membres}
	 </div>
	 </c:if>
	<a href="<%=path%>/ServletInOut?p0=A-order-search&p1=zhifuzhangdan&p2=${reList[0].order_id}">
	   <div class="zhifu">
		 我要支付
	   </div>
	</a>

		

</body>
</html>