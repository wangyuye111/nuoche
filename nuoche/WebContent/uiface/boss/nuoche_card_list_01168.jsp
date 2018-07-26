<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath()+"/uiface";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<style>
	.td-manage .ml-5 .Hui-iconfont {
		font-size: 18px;
	}
</style>
<title>会员管理</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 挪车卡管理 
	<span class="c-gray en">&gt;</span> 用户挪车卡
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c">
		<div class="mt-20">
			<div class="text-c">
					ID号：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输入用户ID号" id="searchtxt" name="searchtext">	
				<!-- <span>性别:</span>
				<select class="input-text" style="width:150px" id="searchname" name="">
					<option></option>
					<option value="男">男</option>
					<option value="女">女</option>
					<option value="1">是vip</option>
					<option value="0">不是vip</option>
				</select> -->
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜用户</button>
			</div>	
		</div>
		
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<!-- <span>注:财富记录为男用户充值记录，魅力记录为女用户收礼记录</span> -->
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width=20>序号</th>
				<th width="20">ID</th>
				<th width="40">头像</th>
				<th width="40">昵称</th>
				<th width="40">车牌号</th>
				<th width="40">手机号</th>
				<th width="40">问候语</th>
				<th width="40">二维码图片</th>
				<th width="40">生成时间</th>
				
			</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
		<!-- a.id,a.nickname,a.head_photo,b.number_plate,b.phone,b.greetings,b.code_photo,b.move_time -->		
					<td>${status.count}</td>
					<td>${map['ids']}</td>
					<td><img alt="" src="${map['head_photo']}" style="width:80px"></td>
					<td>${map['nickname']}</td>
					<td>${map['number_plate']}</td>
					<td>${map['phone']}</td>
					<td>${map['greetings']}</td>
					<%-- <td>${map['code_photo']}</td> --%>
					<td><img alt="" src="${map['code_photo']}" style="width:80px"></td>
					<td>${map['move_time']}</td>
					
					
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span> ，共 <span id="total">${pageNo[1]}</span>条</div>
	
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
		<a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a>
		<span>
			<a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0">   
				<span id="currentpage">${pageNo[4]}</span>
			</a>
		</span>
		<a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a>
	</div>
	
	</div>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<!-- <script type="text/javascript" src="<%=path%>1/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>1/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>  -->
<script type="text/javascript" src="<%=path%>/boss/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

var totalpage = Number('${pageNo[0]}');
$(function(){
	// 上一页
	$("#DataTables_Table_0_previous").click(function() {
		var currentpage = Number($("#currentpage").html());
		
		if(currentpage <= 1) {
			alert('当前已经是第一页');
			return;
		}
		
		fresh_page(currentpage - 1);
	});
	
	// 下一页
	$("#DataTables_Table_0_next").click(function() {
		var currentpage = Number($("#currentpage").html());
		
		if(currentpage >= totalpage) {
			alert('当前已经是最后一页');
			return;
		}
		
		fresh_page(currentpage + 1);
	});
	
	//搜索符合条件的所有的记录显示出来，用ajax
	$("#searchbtn").click(function(){
		/* var startdate = $("#datemin").val();
		var enddate = $("#datemax").val(); */
		/* var sele_condition = $("#searchname").val(); */
		var mil_id = $("#searchtxt").val();
		/* 1:获取输入的性别 */
		/* 2:获取输入的起始时间和结束时间 */
		/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
		//2种搜索条件,第一种满足就用第一种刷新；第二种满足就用第二种刷新
		//if(mil_id != ''){
			fresh_page(1);
		/* }else if(sele_condition != ''){
			fresh_page(1);
		}
		else if(sele_condition==''){
			fresh_page(1);
		}  */
	});
});

/*刷新列表*/
function fresh_page(pageIndex){
	/* var startdate = $("#datemin").val();
	var enddate = $("#datemax").val(); */
	/* var sele_condition = $("#searchname").val(); */
	var mil_id = $("#searchtxt").val();
	
	/* 获取输入的性别 */
	/* 获取输入的起始时间和结束时间 */
	/* 固定死判断条件，如性别，时间段：开始时间---结束时间 */
	$.ajax({
		cache: true,
		type: "POST",
		<%-- url: "<%=path%>/ar?p0=A-boss-search&p1=fabu&p2="+pageIndex+"&p3=&p4=&p5="+sele_condition+"&p6=tojson&p7="+mil_id , --%>
		url:"<%=path%>/ar?p0=A-boss-search&p1=nuoche_card&p2="+pageIndex+"&p3=tojson&p4="+mil_id,
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data){
			var json=eval("("+data+")");
			var content = '';
			for(var i = 0; i < json.length-1; i++) {
				 content +='<tr class="text-c">'
					+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
					+'<td>'+json[i].ids+'</td>'
					+'<td><img alt="" src="'+json[i].head_photo+'" style="width:80px"></td>'
					+'<td>'+json[i].nickname+'</td>'
					
					+'<td>'+json[i].number_plate+'</td>'
					+'<td>'+json[i].phone+'</td>' 
					+'<td>'+json[i].greetings+'</td>'
					+'<td><img alt="" src="'+json[i].code_photo+'" style="width:80px"></td>'
					+'<td>'+json[i].move_time+'</td>';
			}
			$("#list-content").html(content);
			totalpage = Number(json[json.length-1].totlePage);
			$("#pagefirst").html(Number(json[json.length-1].current)+1);
			$("#pagelast").html(json[json.length-1].lastcount);
			$("#total").html(json[json.length-1].totle);
			$("#currentpage").html(json[json.length-1].pagenum);
		},
	});
}




</script> 
</body>
</html>