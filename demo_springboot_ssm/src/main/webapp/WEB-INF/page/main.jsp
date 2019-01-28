<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>母婴商城管理系统</title>
</head>
<body>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>母婴商城管理系统</title>
<link rel="stylesheet" href="layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="css/back/hp-layui.css" />
<link rel="bookmark"  type="image/x-icon"  href="favicon.ico"/>
<link rel="shortcut icon" href="favicon.ico">
<link rel="icon" href="favicon.ico">
</head>

<body class="layui-layout-body hp-white-theme">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">
				<h3>母婴商城管理系统</h3>
			</div>
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><span class="hp-kd"
					style="color: red"><%=request.getSession().getAttribute("admin")==null?"":request.getSession().getAttribute("admin") %></span></li>
				<li class="layui-nav-item"><a class="name" href="javascript:;">主题<span
						class="layui-nav-more"></span></a>
					<dl class="layui-nav-child layui-anim layui-anim-upbit">
						<dd>
							<a skin="hp-black-theme" class="hp-theme-skin-switch"
								href="javascript:;">低调黑</a>
						</dd>
						<dd>
							<a skin="hp-blue-theme" class="hp-theme-skin-switch"
								href="javascript:;">炫酷蓝</a>
						</dd>
						<dd>
							<a skin="hp-green-theme" class="hp-theme-skin-switch"
								href="javascript:;">原谅绿</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;" id="exit">退出</a>
				</li>
			</ul>
		</div>

		<div class="layui-side hp-left-menu">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item"><a href="javascript:;">导航菜单</a>
						<dl class="layui-nav-child" style="padding-left: 15px">
							<dd>
								<a class="hp-tab-add" hp-href="model/category" href="javascript:;">类别管理</a>
							</dd>
						</dl>
						<dl class="layui-nav-child" style="padding-left: 15px">
							<dd>
								<a class="hp-tab-add" hp-href="model/typeattribute"
									href="javascript:;">类别属性管理
									</a>
							</dd>
						</dl>
						<dl class="layui-nav-child" style="padding-left: 15px">
							<dd>
								<a class="hp-tab-add" hp-href="model/goods"
									href="javascript:;">商品管理
									</a>
							</dd>
						</dl>
						<dl class="layui-nav-child" style="padding-left: 15px">
							<dd>
								<a class="hp-tab-add" hp-href="model/discount"
									href="javascript:;">折扣管理</a>
							</dd>
						</dl>
						<dl class="layui-nav-child" style="padding-left: 15px">
							<dd>
								<a class="hp-tab-add" hp-href="model/sku" href="javascript:;">库存管理</a>
							</dd>
						</dl>
						<dl class="layui-nav-child" style="padding-left: 15px">
							<dd>
								<a class="hp-tab-add" hp-href="model/order" href="javascript:;">订单管理</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<!-- 内容主体区域 -->
		<div class="layui-body">
			<div class="layui-tab hp-tab " style="" lay-filter="hp-tab-filter"
				lay-allowclose="true">
				<ul class="layui-tab-title">
					<li class="layui-this" lay-id="0" >欢迎<%=request.getSession().getAttribute("admin")==null?"":request.getSession().getAttribute("admin") %></li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show"></div>
				</div>
			</div>
		</div>
	</div>
	<%
		request.getSession().removeAttribute("URLInterceptor");
	%>
	<script src="layui/layui.js"></script>
	<script src="js/admin/main.js"></script>
</body>
</html>