<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath()+"/uiface";	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no">
<title>订座</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/style.css" />
<link href="<%=path%>/lib/My97DatePicker/4.8/skin/WdatePicker.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer_mobile/layer.js"></script>
	
<style>
	.seat_useless {
		background:#aaa;
		width:100px;
		height:100px;
		line-height:100px;
		display:inline-block;
		margin-top:10px;
		margin-left:10px;
		text-align:center;
		position:relative;
	}
	
	.seat_usable {
		background:#0d0;
		width:100px;
		height:100px;
		line-height:100px;
		display:inline-block;
		margin-top:10px;
		margin-left:10px;
		text-align:center;
		position:relative;
	}
	
	.seat_selected {
		background:url(<%=path%>/img/select.png);
		width:50px;
		height:50px;
		position:absolute;
		bottom:0px;
		right:0px;
		background-size:100%;
	}
</style>

<script type="text/javascript">
var date = '';
var section = '';
var curSeat;
var curId;
var curPeoples;
var curSeatName;
var enableCnt = 0;	// 可预订桌子数量
var peopleCnt;
var canAddChair = '否';	// 是否能添加椅子
var hasBaby = '否';
var hasBabyChair = '否';	//　是否有未成年人
var hasPrivateWaiter = '否';	// 是否需要私人服务员
var hasPark = '否';		//　是否需要停车位
var tel;
var orderdish = '否';
var arrivetime;
$(document).ready(function(){

	$("#select_date").change(function() {
		dateSecChange('date');
	});
	
	$("#select_section").change(function() {
		dateSecChange('section');
	});
	
	$("#submit_seat").click(function() {
		if(date == '') {
			alert('请选择日期');
			return;
		}
		if(section == '') {
			alert('请选择时间段');
			return;
		}
		if(enableCnt <= 0) {
			alert('本时间段桌子已预订完毕');
			return;
		}
		
		if(curId == undefined) {
			alert('请选择桌子');
			return;
		}
		
		$.ajax({
			cache: true,
			type: "POST",
			url:"<%=path%>/seat?p0=A-user-search&p1=tablelist&p2=tojson&p3="+date+"&p4="+section,
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success: function(data) {
				
				var json=eval("("+data+")");
				
				var content = '';
				
				enableCnt = 0;	// 可预订桌的数量 清零
				var floor;
				for(var i = 0; i < json.length; i++) {
					console.log('floor:'+floor+','+json[i].floor);
					if(floor != json[i].floor) {
						content += '<div style="width:100%;height:2px;background:black;margin-top:10px;"></div><div>'+json[i].floor+'楼</div>';
						floor = json[i].floor;
					}
					if(json[i].seat_id == 'null') {
						content += '<div class="seat_usable" onclick="selseat(this,'+json[i].id+',\''+json[i].seat_name+'\')">'+json[i].seat_name+'<div class=""></div></div>';
						enableCnt += 1;	//　可预订桌的数量累加1
					} else {
						content += '<div class="seat_useless">'+json[i].seat_name+'</div>';
					}
				}
				
				/* <c:if test="${curfloor != map['floor']}">
			 	<div style="width:100%;height:2px;background:black;margin-top:10px;"></div>
			 	<div>${map['floor']}楼</div>
			 	<c:set var="curfloor" value="${map['floor']}"/>
			 	</c:if>
				<div class="seat_useless">${map['seat_name']}(${map['peoples']}人)<div class=""></div></div> */
				
				$("#table-list").html(content);
			}
		});
	});
});

function dateSecChange(com) {
	
	if(com == 'date') {
		date = $("#select_date").val();
	} else if(com == 'section') {
		section = $("#select_section").val();
	}
	
	if(date != '' && section != '') {
		$.ajax({
			cache: true,
			type: "POST",
			url:"<%=path%>/seat?p0=A-user-search&p1=tablelist&p2=tojson&p3="+date+"&p4="+section,
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success: function(data) {
				
				var json=eval("("+data+")");
				
				var content = '';
				
				enableCnt = 0;	// 可预订桌的数量 清零
				var floor;
				for(var i = 0; i < json.length; i++) {
					if(floor != json[i].floor) {
						content += '<div style="width:100%;height:2px;background:black;margin-top:10px;"></div><div>'+json[i].floor+'楼</div>';
						floor = json[i].floor;
					}
					if(json[i].seat_id == 'null') {
						content += '<div class="seat_usable" onclick="selseat(this,'+json[i].id+',\''+json[i].seat_name+'\','+json[i].peoples+')">'+json[i].seat_name+'('+json[i].peoples+'人)<div class=""></div></div>';
						enableCnt += 1;	//　可预订桌的数量累加1
					} else {
						content += '<div class="seat_useless">'+json[i].seat_name+'('+json[i].peoples+'人)</div>';
					}
				}
				
				$("#table-list").html(content);
			}
		});
	}
}

function selseat(seat,id,name,pcnt) {
	
	if(curId != id) {
		$(seat).find("div").attr("class","seat_selected");
		if(curSeat != undefined) {
			$(curSeat).find("div").attr("class","");
			curSeat = undefined;
			curId = undefined;
		}
		curSeat = seat;
		curId = id;
		curSeatName = name;
		
		layer.open({
			type:0,
			shadeClose: false,
			content:'<div>'
						+'<div>您选择的餐桌为: '+name+'</div>'
						+'<div>请输入用餐人数:<input type="text" id="people_cnt" value=""></div>'
					+'</div>',
			btn:['确定','取消'],
			yes:function() {
				var cnt = $("#people_cnt").val();
				if(cnt == '') {
					return;
				}
				peopleCnt = cnt;
				if(pcnt < cnt) {	// 如果填写人数大于桌子容纳人数
					layer.open({
						type:0,
						shadeClose: false,
						content:'是否可以临时加椅子?',
						btn:['是','否'],
						yes:function() {
							canAddChair = '是';
							
							hasbaby();	// 是否有婴儿判断
						},
						no:function(index) {
							
							layer.close(index);	// 不能加椅子就重新判断
						}
					});
				} else {
					hasbaby();	// 是否有婴儿判断
				}
			},
			no:function() {
				hasbaby();	// 是否有婴儿判断
			}
		});
		
		
	} else {
		$(seat).find("div").attr("class","");
		curSeat = undefined;
		curId = undefined;
	} 
	
}

function hasbaby() {
	// 是否包含未成年人就餐
	layer.open({
		type:0,
		shadeClose:false,
		content:'是否包含未成年人就餐?',
		btn:['是','否'],
		yes:function() {
			hasBaby = '是';
			layer.open({
				type:0,
				shadeClose:false,
				content:'是否需要准备儿童/婴儿专用座位?',
				btn:['是','否'],
				yes:function() {
					hasBabyChair = '是';
					haswaiter();	// 是否专职服务员判断
				},
				no:function() {
					hasBabyChair = '否';
					haswaiter();	// 是否专职服务员判断
				}
			});
		},
		no:function() {
			hasBaby = '否';
			hasBabyChair = '否';	// 没有儿童不需要准备婴儿座椅
			haswaiter();	// 是否专职服务员判断
		}
	});
}

function haswaiter() {
	// 是否需要专职服务员
	layer.open({
		type:0,
		shadeClose:false,
		content:'是否需要专职服务员?',
		btn:['是','否'],
		yes:function() {
			hasPrivateWaiter = '是';
			haspark();
		},
		no:function() {
			hasPrivateWaiter = '否';
			haspark();
		}
	});
}

function haspark() {
	// 是否需要停车位
	layer.open({
		type:0,
		shadeClose:false,
		content:'是否需要停车位?',
		btn:['是','否'],
		yes:function(index) {
			hasPark = '是';
			layer.close(index);
			gettel();
		},
		no:function(index) {
			hasPark = '否';
			layer.close(index);
			gettel();
		}
	});
}

function gettel() {
	layer.open({
		type:0,
		shadeClose:false,
		content:'请输入您的手机号<div><input id="tele" type="text"></div>',
		btn:'确定',
		yes:function() {
			tel = $("#tele").val();
			if(tel != '') {
				orddish();	
			}
		}
	});
}

function orddish() {
	// 是否提前点餐
	layer.open({
		type:0,
		shadeClose:false,
		content:'是否预先点菜',
		btn:['是','否'],
		yes:function(){
			orderdish = '是';
			arrivetime();
		},
		no:function(){
			orderdish = '否';
			arrivetime();
		}
	});
}

function arrivetime() {
	// 预计到达时间
	layer.open({
		type:0,
		shadeClose:false,
		content:'预计到达时间<div><input id="arrive_time" type="text"></div>',
		btn:'确定',
		yes:function() {
			arrivetime = $("#arrive_time").val();
			if(arrivetime != '') {
				submitinfo();
			}
		}
	});
}

function submitinfo() {
	//alert($(curSeat).text()+',预订人数: '+peopleCnt+',是否加椅子:'+canAddChair+',是否加宝宝椅子:'+hasBabyChair+',是否停车位:'+hasPark);
	window.location.href = '<%=path%>/seat?p0=A-user-search&p1=tableorderinfo&p2=1&p3='+tel+'&p4='+curId+'&p5='+curSeatName+'&p6='+$("#select_date").val()+'&p7='+section
			+'&p8='+peopleCnt+'&p9='+hasPark+'&p10='+canAddChair+'&p11='+hasBaby+'&p12='+hasBabyChair+'&p13='+hasPrivateWaiter+'&p14='+orderdish+'&p15='+arrivetime;
}
</script>

</head>
<body>
	<div>
		日期: <input type="date"  id="select_date" name="select_date" class="input-text radius" style="width: 120px;">
		时间段: <select class="input-text radius" id="select_section" style="width:100px">
			<option value="">无</option>
			<option>上午</option>
			<option>下午</option>
		</select>
	</div>
	<div id="table-list">
		<c:set var="curfloor" value=""/>
		<c:forEach var="map" items="${reList}" varStatus="status">
		 <c:if test="${curfloor != map['floor']}">
		 	<div style="width:100%;height:2px;background:black;margin-top:10px;"></div>
		 	<div>${map['floor']}楼</div>
		 	<c:set var="curfloor" value="${map['floor']}"/>
		 </c:if>
		<div class="seat_useless">${map['seat_name']}(${map['peoples']}人)<div class=""></div></div>
		</c:forEach> 
   </div>

</body>
</html>