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

<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/boss/static/h-ui.admin/css/style.css" />

<title>图片上传审核</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 动态管理 <span class="c-gray en">&gt;</span> <!-- 文章列表  --><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="mt-20">
			<div class="text-c">	
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>审核状态:</span>
				<select class="input-text" style="width:150px" id="searchname" name="searchname">
					<option></option>
					<option value="未通过">未通过</option>
					<option value="待审核">待审核</option>
					<option value="已通过">已通过</option>
				</select>
				<button type="submit" class="btn btn-success radius" id="searchbtn" name=""><i class="Hui-iconfont"></i>搜索</button>
			</div>	
			
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper ">
	<form id="memberForm">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="30">序号</th>
				<th width="40">用户ID</th>
				<th width="40">图片</th>
				<th width="40">昵称</th>
				<th width="40">动态内容</th>
				<th width="40">审核状态</th>
				<th width="40">拒绝理由</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody id="list-content" >
		<c:forEach var="map" items="${reList}" varStatus="status">
			<tr class="text-c">
				<td>${status.count}</td>
				<td>${map['update_id']}</td>
				<td><a href="javascript:;"  onclick="system_category_edit('图片查看','<%=path %>/ar?p0=A-boss-search&p1=album_list&p2=${map['id'] }',800,600)" style="width:50px;height:50px;color:blue;">点击查看图片</a></td>
				<%-- <td>${map['image'] }</td> --%>
				<%-- <td>${map['isHeadpic'] }</td> --%>
				<td>${map['nickname'] }</td>
				<td>${map['title'] }</td>
				<td>${map['status'] }</td>
				<td>
				<c:if test="${map['refusal'] == '' }">无</c:if>
				<c:if test="${map['refusal'] != '' }">${map['refusal']}</c:if>
				</td>
				<td class="td-manage">
				<c:if test="${map['status'] == '待审核' }">
				  <a title="通过" href="javascript:;" onclick="system_category_edit1(${map['id']},${map['update_id']})" class="ml-5" style="text-decoration:none;color:green;">通过</a>  
				   <a title="不通过" href="javascript:;" onclick="go(${map['id']},${map['update_id']})" class="ml-5" style="text-decoration:none;color:red;">不通过</a>	 
				</c:if>
				<c:if test="${map['status'] == '未通过' }">
					<span style="color:red;">未通过</span>
				</c:if>
				<c:if test="${map['status'] == '已通过' }">
					<span style="color:green;">已通过</span>
				</c:if>
				</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	</form>
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 <span id="pagefirst">${pageNo[2]+1}</span> 到 <span id="pagelast">${pageNo[3]}</span> ，共 <span id="total">${pageNo[1]}</span>条</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate" ><a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a><span><a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0"><span id="currentpage">${pageNo[4]}</span></a></span><a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a></div>
	</div>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/boss/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">

var totalpage = Number('${pageNo[0]}');
 //刷新列表     
function fresh_page(pageIndex) {
	var name = document.getElementById("searchname").value;
	//alert(name);
	//var name = $("#searchname").val();
		$.ajax({
			cache:true,
			type:"post",
			url: "<%=path %>/ar?a=A-boss-search&b=dynamic&p3="+pageIndex+"&p4="+name+"&page=tojson",
			<%-- url: "<%=path %>/ar?a=A-boss-search&b=dynamic&p3="+pageIndex+"&p4="+name+"&page=tojsp", --%>
			async: true,
			error: function(request) {
				alert("提交失败 ");
			},
			success:function(data){
				var json=eval("("+data+")");
				var content = '';
				for(var i = 0;i<json.length-1;i++){
					var str1='';
					var str2='';
					/* if(json[i].status=='待审核'){
						str1+='<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id+','+json[i].update_id+') "  class="ml-5" style="text-decoration:none;color:green;">通过</a>'
						+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id+','+json[i].update_id+')" class="ml-5" style="text-decoration:none;color:red;">未通过</a>';
					}else if(json[i].status=='未通过'){
						str1='<span style="color:red;">未通过</span>';
					}else if(json[i].status=='已通过'){
						str1='<span style="color:green;">已通过</span>';
					} */
					
					if(json[i].status=='待审核'){
						str1+='<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id+','+json[i].user_id+') "  class="ml-5" style="text-decoration:none;color:green;">通过</a>'
						+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id+','+json[i].user_id+')" class="ml-5" style="text-decoration:none;color:red;">未通过</a>';
					}else if(json[i].status=='未通过'){
						str1='<span style="color:red;">未通过</span>';
					}else if(json[i].status=='已通过'){
						str1='<span style="color:green;">已通过</span>';
					}
					
					
					if(json[i].refusal==''){
						str2='<td>无</td>';
					}else{
						str2='<td>'+json[i].refusal+'</td>';
					}
					
					content += '<tr class = "text-c">'
							+'<td>' +(Number(json[json.length-1].current)+1+i)+'</td>'
							+'<td>'+json[i].update_id+'</td>'
							+'<td><a href="javascript:;" onclick="system_category_edit(\'图片查看\',\'<%=path %>/ar?p0=A-boss-search&p1=album_list&p2='+json[i].id+'\',\'600\',\'510\')"  style="width:50px;height:50px;color:blue;">点击查看图片</a></td>'
							/* +'<td>'+json[i].isHeadpic+'</td>' */
							+'<td>'+json[i].nickname+'</td>'
							+'<td>'+json[i].title+'</td>'
						    +'<td>'+json[i].status+'</td>'
						    +str2
						    +'<td class="td-manage">'
						    +str1
						    +'</td>'
						   /*  +'<td class="td-manage">'
							+'<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id+','+json[i].update_id+') "  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">通过</i></a>'
							+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id+','+json[i].update_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">未通过</i></a>'
							+'</td>' */
						    /* +str2
						    +'<td class="td-manage">'
							+str1
							+'</td>' */
						    ;
							/* +'<td>'+json[i].exchange_price+'</td>' */
							/* +'<td class="td-manage">'
							+'<a title="通过" href="javascript:;" onclick="system_category_edit1('+json[i].id1+','+json[i].id+') "  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">通过</i></a>'
							+'<a title="不通过" href="javascript:;" onclick="go('+json[i].id1+','+json[i].id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">未通过</i></a>'
							+'</td>' */
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

var bool;
//上一页
$("#DataTables_Table_0_previous").click(function() {
	var currentpage = Number($("#currentpage").html());
		if(currentpage <= 1) {
			alert('当前已经是第一页');
			return;
		}	
		fresh_page(currentpage-1);
	
});

// 下一页
$("#DataTables_Table_0_next").click(function() {
	var currentpage = Number($("#currentpage").html());
		
		if(currentpage >= totalpage) {
			alert('当前已经是最后一页');
			return;
		} 
		fresh_page(currentpage+1);
	
});

function client_del(obj,id){
	
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<%=path%>/ar?p0=A-boss-delete&p1=del_baby&p2='+id,
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('已删除!',{icon:1,time:1000});
				setTimeout(function() { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('提交失败');
			},
		});		
	});
}
//模糊查询
$("#searchbtn").click(function(){
	fresh_page(1)
	
	
	
	
	
	
});
function system_category_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
function system_category_edit1(id,userid){
	//layer_show(title,url,w,h);
	
	$.ajax({
		type: 'POST',
		url:'<%=path%>/ar?p0=A-boss-mod&p1=album_checkpass&p2='+id+'&p3='+userid,
		success: function(data){
			/*$(obj).parents("tr").remove();*/
			layer.msg('操作成功',{icon:1,time:1000});
			setTimeout(function () { 
				javascript:location.replace(location.href);
		    }, 1000);
		},
		error:function(data) {
			alert('提交失败');
		},
	});			
}
function go(id,userid){
	age = prompt("请输入未通过原因","");
	layer.confirm('确认输入以上内容？',function(index){

		$.ajax({
			type:'POST',
			url: '<%=path%>/ar?p0=A-boss-mod&p1=album_checknopass&p2='+id+'&p4='+userid,
			<%-- url: '<%=path%>/rp?p0=A-boss-mod&p1=album_checknopass&p2='+id+'&p4='+userid, --%>
			type:'POST',
            dataType:'JSON',
            data:{'age':age},
			success: function(data){
				/*$(obj).parents("tr").remove();*/
				layer.msg('操作成功',{icon:1,time:1000});
				setTimeout(function () { 
					javascript:location.replace(location.href);
			    }, 1000);
			},
			error:function(data) {
				alert('通过失败');
			},
		});		
	 });
}

function system_category_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>