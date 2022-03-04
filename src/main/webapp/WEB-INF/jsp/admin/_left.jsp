<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<head>
		<link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
	</head>	
<div id="menu_bar">
	<div><a href="${ pageContext.request.contextPath}/admin/prodlist">> 商品管理</a>	</div>
	<div><a href="${ pageContext.request.contextPath}/admin/getCategory">> 商品类别</a></div>
	<div><a href="${ pageContext.request.contextPath}/admin/ordermanage">> 未发货订单</a></div>
	<div><a href="${ pageContext.request.contextPath}/admin/orderList">> 已发货订单</a></div>
	<div><a href="${ pageContext.request.contextPath}/admin/salasList">> 销售榜单</a></div>
	<div><a href="${ pageContext.request.contextPath }/index.jsp">> 前台主页</a></div>
	<div><a href="${ pageContext.request.contextPath }/index/logout">> 退出</a></div>
</div>		
	
