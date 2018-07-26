<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String path = request.getContextPath() + "/uiface";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/boss/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<style>
.td-manage .ml-5 .Hui-iconfont {
	font-size: 18px;
}
</style>
<title>用户消费记录</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		用户管理 <span class="c-gray en">&gt;</span> 用户消费记录 <a
			class="btn btn-success radius r btn-refresh"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="page-container">
		<div class="text-c">
			<!--
		日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		--><!-- 
			<input type="text" class="input-text" style="width: 250px"
				placeholder="请输入充值卡ID" id="searchtxt" name="">
			<button type="submit" class="btn btn-success radius" id="searchbtn"
				name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button> -->
		</div>
		<%-- <div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <!-- <a href="javascript:;" onclick="clients_del()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  -->

				<!--<a href="javascript:;"
				onclick="client_add('添加充值卡','boss/good_add.jsp','600','510')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加充值卡</a>-->

			</span> <span class="r">共有数据：<strong>${pageNo[1]}</strong> 条
			</span>
		</div> --%>
		<div class="mt-20">
			<div class="dataTables_wrapper">
				<table
					class="table table-border table-bordered table-hover table-bg">
					<thead>
						<tr class="text-c">
							<th width=20>序号</th>
							<th width="20">ID</th>
							<th width="50">充值卡ID</th>
							<th width="50">充值面额</th>
							<th width="50">充值卡密码</th>
							<th width="50">充值卡添加时间</th>
							<th width="50">是否已被充值</th>
							<th width="50">充值此卡用户ID</th>
							<!-- <th width="100">操作</th> -->
						</tr>
					</thead>
					<tbody id="list-content">
						<c:forEach var="map" items="${reList}" varStatus="status">
							<tr class="text-c">
								<td>${status.count}</td>
								<td>${map['id']}</td>
								<td>${map['cardid']}</td>
								<td>${map['format']}</td>
								<td>${map['password']}</td>
								<td>${map['addtime']}</td>
								<td>${map['isrecharged']}</td>
								<td>${map['recharge_user']}</td>
								<%-- <td>${map['gift_price']}个娃娃可换</td>
								<td class="td-manage"><a title="编辑" href="javascript:;"
									onclick="client_edit('编辑','<%=path%>/cp?p0=A-boss-search&p1=gift_id_search&p2=${map['id']}','','','510')"
									class="ml-5" style="text-decoration: none"><i
										class="Hui-iconfont">&#xe6df;</i></a> <a title="删除"
									href="javascript:;"
									onclick="client_del(this,${map['gift_id']})" class="ml-5"
									style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="dataTables_info" id="DataTables_Table_0_info"
					role="status" aria-live="polite">
					显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span>
					，共 <span id="total">${pageNo[1]}</span>条
				</div>
				<div class="dataTables_paginate paging_simple_numbers"
					id="DataTables_Table_0_paginate">
					<a class="paginate_button previous disabled"
						aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0"
						id="DataTables_Table_0_previous">上一页</a><span><a
						class="paginate_button current" aria-controls="DataTables_Table_0"
						data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span><a
						class="paginate_button next disabled"
						aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0"
						id="DataTables_Table_0_next">下一页</a>
				</div>
			</div>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<!-- <script type="text/javascript" src="<%=path%>/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>  -->
	<script type="text/javascript"
		src="<%=path%>/boss/lib/laypage/1.2/laypage.js"></script>
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
	
	// 搜礼品名称
	$("#searchbtn").click(function() {
		var searchtxt = $("#searchtxt").val();
		
		if(searchtxt == '') {
			alert('请输入要搜索的卡号!');
			return;
		}
		
		fresh_page(1);
	});
});

/*刷新列表*/
function fresh_page(pageIndex) {
	var searchtxt = $("#searchtxt").val();
	$.ajax({
		cache: true,
		type: "POST",
		url:"<%=path%>/recharge?p0=A-boss-search&p1=recharge_search&p2="+pageIndex+"&p3=tojson",
		async: true,
		error: function(request) {
			alert("提交失败 ");
		},
		success: function(data) {
			var json=eval("("+data+")");
			var content = '';
			for(var i=0;i<json.length-1; i++) {
				content +='<tr class="text-c">'
						+'<td>'+(Number(json[json.length-1].current)+1+i)+'</td>'
						+'<td>'+json[i].id+'</td>'
						+'<td>'+json[i].carid+'</td>'
						+'<td>'+json[i].format+'</td>'
						+'<td>'+json[i].password+'</td>'
						+'<td>'+json[i].addtime+'</td>'
						+'<td>'+json[i].isrecharged+'</td>'
						+'<td>'+json[i].recharge_user+'</td>'
						<%-- +'<td>'+json[i].gift_price+'个娃娃可换</td>'
						+'<td class="td-manage">'
						+'<a title="编辑" href="javascript:;" onclick="client_edit(\'编辑\',\'<%=path%>/zww?p0=A-boss-search&p1=gift_id_search&p2='+json[i].id+'\',\'\',\'\',\'510\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
						+'<a title="删除" href="javascript:;" onclick="client_del(this,'+json[i].id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>'
						+'</td>' --%>;
			}
			$("#list-content").html(content);
			totalpage = Number(json[json.length-1].totlePage);
			$("#pagefirst").html(Number(json[json.length-1].current)+1);
			$("#pagelast").html(json[json.length-1].lastcount);
			$("#total").html(json[json.length-1].totle);
			$("#currentpage").html(json[json.length-1].pagenum);
		}
	});
}
/* /*用户-添加*/
function client_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*用户-停用*/
/* function proj_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="proj_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
} */

 /*用户-启用*/
/*function proj_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="proj_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
} */
/*用户-编辑*/
function client_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
/* function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
} */
/*用户-删除*/
function client_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/cp?p0=A-boss-delete &p1=gift_del&p2='+gift_id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('已删除!',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('提交失败');
			},
		});		
	});
}

</script>
</body>
</html>