<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath() + "/../uiface";
String path1 = request.getContextPath()+"/../uiface1";

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
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/boss/static/h-ui.admin/css/style.css" />
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js?t=B37D54V"></script>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>新增文章 - 资讯管理 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form    method="post" action="<%=path%>/JyServletInOut?a=A-boss-add&b=batchcard_add&c=" 
	
	
	 class="form form-horizontal" id="form-article-add"  name="ThisForm"    >
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">起始编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${str}" placeholder="起始编号" id="startnum" name="startnum" readonly>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">总个数：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="1000" placeholder="总个数" id="totlesum" name="totlesum"   />
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">结束编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="结束编号" id="endnum" name="endnum"  onclick="totlesum1()"   readonly>
			</div>
		</div>
		
		
		<input type="hidden" class="input-text" value="0"  id="agentid" name="agentid"  >
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> 
			</div>
		</div>
	</form>
</article>




<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/boss/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/boss/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/boss/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/boss/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=path%>/boss/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="<%=path%>/boss/lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="<%=path%>/boss/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//表单验证
	$("#form-article-add").validate({
		rules:{
			
		},
		
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			

			/* var file=document.getElementById('fileimg');
			if(file.outerHTML){
		         file.outerHTML=file.outerHTML;
			  }
			  else{      //FF
			        file.value="";
			  }
			var a=CKEDITOR.instances.descs.getData();
			$("#descs").val(a); */
			//document.ThisForm.submit();
			
			$("#form-article-add").ajaxSubmit();
			 setTimeout(function () {
				var index = parent.layer.getFrameIndex(window.name);
				//parent.$('.btn-refresh').click();
				parent.location.reload();
				parent.layer.close(index);
				
		    }, 100); 
	    }
	
	});

});


function totlesum1(){
	
	var totlenumber=$("#totlesum").val();
	var startnum=$("#startnum").val();
	
	if(totlenumber==''){
		return;
	}
	if($("#totlesum").val()>0){
		var endnum=Number(startnum)+Number(totlenumber)-1;
		$("#endnum").val(endnum);
	}
}




</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>