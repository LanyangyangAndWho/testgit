<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/public/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Discount显示页面</title>
<%@ include file="/WEB-INF/page/public/meta.jsp"%>
</head>
<body>
	<div style="display: none; margin-top: 20px;" id="updDiscountDiv">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">商品名称</label>
					<div class="layui-input-inline">
						<input type="hidden" name="goods.goodsId" id="goodsId">
						<input type="text" placeholder="--请选择商品--" autocomplete="off" class="layui-input" id="goodsName">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">折扣</label>
					<div class="layui-input-inline">
						<input type="text" name="discountNum" lay-verify="required|number"
							autocomplete="off" placeholder="请输入折扣" class="layui-input"
							id="discountNum">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">折扣开始时间</label>
					<div class="layui-input-inline">
						<input type="text" name="startTime" id="startTime"
							lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off"
							 class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">折扣结束时间</label>
					<div class="layui-input-inline">
						<input type="text" name="endTime" id="endTime"
							lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off"
							class="layui-input" >
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit=""
						lay-filter="submitUpdDiscount">立即提交</button>
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
		<div class="layui-btn-group listTable">
			<button class="layui-btn layui-btn-danger" data-type="getCheckData">
				<i class="layui-icon">&#xe640;</i>批量删除
			</button>
		</div>
		<div class="layui-btn-group listTable">
			<button class="layui-btn layui-btn-small layui-btn-normal"
				id="saveUI">
				<i class="layui-icon">&#xe608;</i>增加
			</button>
		</div>
		<div style="float: right">
			<div class="layui-input-inline">
				<input class="layui-input" id="goodsnameQuery" type="text"
					placeholder="搜商品名称">
			</div>
			<div class="layui-btn-group listTable">
				<button class="layui-btn layui-btn-small" id="queryUI">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
		<table class="layui-table" id="listTable" lay-filter="list"></table>
		
		<script type="text/html" id="bar">
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="show"><i class="layui-icon">&#xe609;</i>查看</a>
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>修改</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
		</script>
	</div>

	<script src="/js/admin/discount.js?<%=System.currentTimeMillis()%>"></script>
	<script src="/js/admin/tableSelect.js?<%=System.currentTimeMillis()%>"></script>
</body>
</html>