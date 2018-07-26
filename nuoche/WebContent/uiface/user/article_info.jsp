<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<%--声明我要使用C标签--%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath() + "/../uiface";
String path1 = request.getContextPath()+"/../uiface1";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<title>${reList[0]['name']}</title>
</head>
<style type="text/css">

.rich_media_meta_list img {
    max-width: 100%!important;
}
</style>
<body>

<div class="rich_media_meta_list" >${reList[0]['referral']}</div>


</body>
</html>