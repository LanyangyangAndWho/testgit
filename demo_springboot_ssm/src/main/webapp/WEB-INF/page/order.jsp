<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/public/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders显示页面</title>
<%@ include file="/WEB-INF/page/public/meta.jsp"%>
</head>
<body>
	<div style="display: none; margin-top: 20px;" id="updOrdersDiv">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">运单号码</label>
					<div class="layui-input-inline">
						<input type="text" name="courierid" lay-verify="required|number"
							autocomplete="off" placeholder="运单号码" class="layui-input"
							id="courierid">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit=""
						lay-filter="submitUpdOrders">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>

	<div style="margin: 20px;">
		<div class="layui-btn-group listTable">
			<button class="layui-btn" id="flush">
				<i class="layui-icon">&#xe669;</i>刷新
			</button>
		</div>
		<div style="float: right">
			<div class="layui-input-inline">
				<input class="layui-input" id="begintimeQuery" type="text"
					placeholder="搜索付款日期">
			</div>
			<div class="layui-btn-group listTable">
				<button class="layui-btn layui-btn-small" id="queryUI">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
		<table class="layui-table" id="listTable" lay-filter="list"></table>
		
		<script type="text/html" id="bar">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>确认收货</a>
		</script>
	</div>

	<script src="/js/orders.js?<%=System.currentTimeMillis()%>"></script>
	<script src="/js/tableSelect.js?<%=System.currentTimeMillis()%>"></script>
</body>
</html>