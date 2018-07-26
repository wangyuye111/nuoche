<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String path = request.getContextPath() + "/uiface";
String adminname ="";
if(request.getSession().getAttribute("admin")!=null&&!"".equals(request.getSession().getAttribute("admin").toString())){
	adminname=request.getSession().getAttribute("admin").toString();
 }else{
	response.sendRedirect(path+"/boss/adminLogin.jsp"); 
 }
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico"/>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台管理系统</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">后台管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<!--<span class="logo navbar-slogan f-l mr-10 hidden-xs">v3.1</span> -->
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<!--  <li><a href="#">切换账户</a></li> -->
						<li><a href="<%=path%>/admin/login.jsp">退出</a></li>
					</ul>
				</li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe611;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					
					
					<li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=user_list&p2=1&p3=tojsp&p4=0" data-title="会员列表" href="javascript:void(0)">会员列表</a></li>
					<%-- <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=shourumingxi&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0" data-title="收入明细" href="javascript:void(0)">收入明细</a></li>
				   <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=zhichumingxi&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0" data-title="支出明细" href="javascript:void(0)">支出明细</a></li> 
				   <li><a data-href="<%=path%>/arr?p0=A-boss-search&p1=nuoche_to_details&p2=1&p3=tojsp&p4=" data-title="挪车明细" href="javascript:void(0)">挪车明细</a></li>
				   <li><a data-href="<%=path%>/xnc?p0=A-boss-search&p1=qiangcheweimingxi&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0" data-title="挪车明细" href="javascript:void(0)">抢车位明细</a></li> --%>
				    <%-- <li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=complaint_search&p2=0&p3=tojsp" data-title="反馈列表" href="javascript:void(0)">反馈列表</a></li>
				     <li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=connectionrate_search&p2=&p3=0&p4=tojsp" data-title="接通率统计" href="javascript:void(0)">接通率统计</a></li> --%>
				</ul>
			</dd>
		</dl>

		<%-- <dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe63a;</i> 账务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=pay_table_search&p2=&p3=1&p4=&p5=&p6=tojsp" data-title="充值记录表" href="javascript:void(0)">充值记录表</a></li>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=cash_withdrawal&p2=1&p3=&p4=&p5=tojsp" data-title="提现明细" href="javascript:void(0)">提现明细</a></li>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=anchor_earnings_search&p2=1&p3=&p4=&p5=tojsp" data-title="主播收益" href="javascript:void(0)">主播收益</a></li>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=anchor_fencheng_search&p2=1&p3=&p4=&p5=tojsp" data-title="主播分成收益" href="javascript:void(0)">主播分成收益</a></li>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=fencheng_search&p2=1&p3=&p4=&p5=tojsp" data-title="用户分成收益" href="javascript:void(0)">用户分成收益</a></li>
				</ul>
			</dd>
		</dl> --%>
		<%-- <dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe6cf;</i> 排行榜<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=tyrants_week_search&p2=0&p3=tojsp" data-title="土豪榜" href="javascript:void(0)">土豪榜</a></li>
					<li><a data-href="<%=path%>/rp?p0=A-boss-search&p1=charm_week_search&p2=0&p3=tojsp" data-title="魅力榜" href="javascript:void(0)">魅力榜</a></li>
				</ul>
			</dd>
		</dl> --%>
		<%-- <dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 明细管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=shourumingxi&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0" data-title="收入明细" href="javascript:void(0)">收入明细</a></li>
				   <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=zhichumingxi&p2=1&p3=&p4=&p5=tojsp&p6=&p7=0" data-title="支出明细" href="javascript:void(0)">支出明细</a></li> 
				   <li><a data-href="<%=path%>/arr?p0=A-boss-search&p1=nuoche_to_details&p2=1&p3=tojsp&p4=" data-title="挪车明细" href="javascript:void(0)">挪车明细</a></li>
				</ul>
			</dd>
		</dl>  --%>
		<%-- <dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 动态管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path%>/ar?a=A-boss-search&b=dynamic&p3=1&p4=&page=tojsp" data-title="动态审核" href="javascript:void(0)">动态审核</a></li> 
				</ul>
			</dd>
		</dl>  --%>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 发布管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <%-- <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=nuoche_card&p2=1&p3=tojsp&p4=" data-title="发布挪车" href="javascript:void(0)">发布挪车</a></li> --%> 
				   <li><a data-href="<%=path%>/xnc?p0=A-boss-search&p1=sefabunuoche&p2=1&p3=tojsp&p4=0" data-title="发布挪车" href="javascript:void(0)">发布挪车</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 提现管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <%-- <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=nuoche_card&p2=1&p3=tojsp&p4=" data-title="发布挪车" href="javascript:void(0)">发布挪车</a></li> --%> 
				   <li><a data-href="<%=path%>/JyServletInOut?mode=A-boss-search&mode2=withdraw_list&empty=&page=1&tojj=jsp" data-title="提现管理" href="javascript:void(0)">提现列表</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 挪车卡管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <%-- <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=nuoche_card&p2=1&p3=tojsp&p4=" data-title="用户挪车卡" href="javascript:void(0)">用户挪车卡</a></li>  --%>
				   <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=apply_card&p2=1&p3=tojsp&p4=" data-title="邮寄挪车卡" href="javascript:void(0)">邮寄挪车卡</a></li>
				   <li><a data-href="<%=path %>/JyServletInOut?mode=A-boss-search&mode2=batchcard_list&empty=&page=1&tojj=jsp" data-title="生成挪车卡" href="javascript:void(0)">挪车卡列表</a></li>
				   
				</ul>
			</dd>
		</dl> 
		
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 广告管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path %>/JyServletInOut?mode=A-boss-search&mode2=ArticleSearch&empty=&page=1" data-title="广告列表" href="javascript:void(0)">广告列表</a></li>
				</ul>
			</dd>
		</dl> 
		
		
		
		
		
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 代理商管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				   <li><a data-href="<%=path %>/JyServletInOut?mode=A-boss-search&mode2=agent_list&empty=&page=1&tojj=jsp" data-title="代理商列表" href="javascript:void(0)">代理商申请列表</a></li>
				</ul>
			</dd>
		</dl>
		
		<%-- <dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe72d;</i> 设置<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
				  <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=tixian_seach" data-title="收费抽成比例设置" href="javascript:void(0)">收费抽成比例</a></li>
                  <li><a data-href="<%=path%>/ar?p0=A-boss-search&p1=jiage_seach" data-title="价格设置" href="javascript:void(0)">价格设置</a></li>
                  <li><a data-href="<%=path%>/arr?p0=A-boss-search&p1=to_editshijian_search" data-title="发单抢单时间设置" href="javascript:void(0)">发单抢单时间设置</a></li>
                  
                  <li><a data-href="<%=path%>/xnc?p0=A-boss-search&p1=duanxin_seach" data-title="短信设置" href="javascript:void(0)">短信设置</a></li>
				</ul>
			</dd>
		</dl> --%>

</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="欢迎页面" data-href="welcome.html">欢迎页面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
	/*$("#min_title_list li").contextMenu('Huiadminmenu', {
		bindings: {
			'closethis': function(t) {
				console.log(t);
				if(t.find("i")){
					t.find("i").trigger("click");
				}		
			},
			'closeall': function(t) {
				alert('Trigger was '+t.id+'\nAction was Email');
			},
		}
	});*/
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 

<!--此乃百度统计代码，请自行删除-->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<!--/此乃百度统计代码，请自行删除-->
</body>
</html>