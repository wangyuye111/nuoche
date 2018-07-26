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
<title>挪车管理</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 
		<span class="c-gray en">&gt;</span> 挪车管理 
		<span class="c-gray en">&gt;</span> 挪车明细列表
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
</nav>
<div class="page-container">
		<!-- <div class="mt-20">
			<div class="text-c">
					ID号：
				<input type="text" class="input-text" style="width:250px"  placeholder="请输入用户ID号" id="searchtxt" name="searchtext">	
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜用户</button>
			</div>	
		</div> -->
		
	<div class="mt-20">
	
		<div class="text-c">
          <span>查询方式</span>
          <select id="check1" name="check1" >
            <option value="0" >时间段查询</option>
            <option value="1" >按月查询</option>
            <option value="2" >按年查询</option> 
          </select>
        </div>
	    <div class="text-c" id="xx1">	
		    <span>开始日期:</span><input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" name="">
		    <span>结束日期:</span><input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;" name="">
	    </div>
		<div class="text-c" id="xx2">	
			<input type="text" id="d243" onclick="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate" style="width:120px;"  />
		</div>
		<div class="text-c" id="xx3">	
			<input type="text" id="d244" onclick="WdatePicker({dateFmt:'yyyy'})" class="input-text Wdate" style="width:120px;"  />
		</div>
		
		<div class="text-c" style="margin-top:10px;">			
			<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i> 查询</button>
		</div>  
		<span id="sum1">挪车列表</span>
	</div>
	
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<!-- <span>注:财富记录为男用户充值记录，魅力记录为女用户收礼记录</span> -->
	<table
		class="table table-border table-bordered table-hover table-bg">
		<thead>
		<tr class="text-c">
			<th width="30">序号</th>
			<th width="30">ID</th>
			<th width="40">昵称</th>
			<th width="40">地址</th>
			<th width="40">类型</th>
			<th width="40">时间</th>
		</tr>
		</thead>
		<tbody id="list-content">
			<c:forEach var = "map" items="${reList }" varStatus="status">
				<tr class="text-c">
					<td>${status.count}</td>
					<td>${map['user_id']}</td>
					<td>${map['nickname']}</td>
					<td>${map['address']}</td>
					<td>${map['type']}</td>
					<td>${map['time']}</td>
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
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<!-- <script type="text/javascript" src="<%=path%>/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>  -->
<script type="text/javascript" src="<%=path%>/boss/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

var totalpage = Number('${pageNo[0]}');
$(function(){
	$("#xx2").hide();
	$("#xx3").hide();
	
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
	$("#searchbtn").click(function() {
		var startdate = $("#datemin").val();
		var enddate = $("#datemax").val();
		
		if(startdate=="" && enddate!=""){
			alert('请选择开始时间');
		}else if(enddate=="" && startdate!=""){
			alert('请选择结束时间时间');
		} 
		fresh_page(1);
	});
});

/*刷新列表*/
function fresh_page(pageIndex){
	var mil_id="1";
	var pp="";
	var searchtype="";
	var startdate = $("#datemin").val();
	var enddate = $("#datemax").val();
	if($("option:selected","#check1").val() == '1'){
		pp=$("#d243").val();
		searchtype="月";
		startdate="";
		enddate="";
	}else if($("option:selected","#check1").val() == '2'){
		pp=$("#d244").val();
		searchtype="年";
		startdate="";
		enddate="";
	}else{
		searchtype="时间段";
		startdate=$("#datemin").val();
		enddate=$("#datemax").val();
	}
	
	 $.ajax({
		cache: true,
		type: "POST",
		url:"<%=path%>/arr?p0=A-boss-search&p1=nuoche_details&p2="+pageIndex+"&p3=tojson&p4="+mil_id+"+&p5="+startdate+"&p6="+enddate+"&p7="+pp+"&p8="+searchtype,
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
						+'<td>'+json[i].user_id+'</td>' 
						/* +'<td><img alt="" src="'+json[i].head_photo+'" style="width:80px"></td>' */
						+'<td>'+json[i].nickname+'</td>'
						+'<td>'+json[i].address+'</td>'
						+'<td>'+json[i].type+'</td>' 
						+'<td>'+json[i].time+'</td>'
						+'</tr>';
					
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

function client_geren(title,url,id,w,h){
	layer_show(title,url,w,h);
}

$("#check1").on("change",function(){
	//alert('执行');
	if ($("option:selected",this).val()==2) {
		//alert('执行1');
	    //var a=$("#xx1").html();
		//alert(a);
		$("#xx1").hide();
		$("#xx2").hide();
		$("#xx3").show();
		//pp="年";
	}else if($("option:selected",this).val() == '1'){
		//alert('执行2');
		//var c='<input type="text" id="d243" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="Wdate"/>';
		//$("#xx1").html();
		$("#xx1").hide();
		$("#xx2").show();
		$("#xx3").hide();
		//pp=$("#d243").val();
	}else{
		//alert('执行3');
		$("#xx1").show();
		$("#xx2").hide();
		$("#xx3").hide();
	}
});



</script> 
</body>
</html>