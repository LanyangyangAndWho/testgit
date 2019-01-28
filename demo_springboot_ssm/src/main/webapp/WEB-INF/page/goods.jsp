<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="public/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Goods显示页面</title>
<%@ include file="public/meta.jsp"%>
<style type="text/css">
img{
 width:auto;
 height:auto;
 max-width:100%;
 max-height:100%;
}
 </style>
</head>
<body>
	<div style="display: none; margin-top: 20px;" id="updGoodsDiv">
		<form class="layui-form" action="" id="up" enctype="multipart/form-data">
		<div id="id"></div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">商品名称</label>
					<div class="layui-input-inline">
						<input type="text" name="goodsName" lay-verify="required"
							autocomplete="off" placeholder="请输入商品名称" class="layui-input"
							id="goodsName">
					</div>
				</div>
			</div>			
			<div class="layui-form-item"  >
			    <label class="layui-form-label">选择类型:</label>
				    <div class="layui-input-inline">
						<select name="goodsType.typeId" id="typeId" lay-verify="required"
							lay-search class="layui-input">
							<option id="siddef" value="">--请选择类型--</option>
						</select>
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">商品价格</label>
					<div class="layui-input-inline">
						<input type="text" name="price" lay-verify="required|number"
							autocomplete="off" placeholder="请输入商品价格" class="layui-input"
							id="price">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">上架时间</label>
					<div class="layui-input-inline">
						<input type="text" name="addTimes" id="addTimes"
							lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">商品主图片</label>
					<div class="layui-input-inline">
						<div style="padding-top: 20px;"><img id="imgPr1" width="120" height="120" /></div>
						<input type="file"  name="file1" id="defaultPhotoUrl" lay-verify="required" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">商品详情图片</label>
					<div class="layui-input-inline">
						<div style="padding-top: 20px;"><img id="imgPr2" width="120" height="120" /></div>
						<input type="file" id="detailsPhotoUrl" name="file2" lay-verify="required" class="layui-input"/>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<div class="layui-input-block">
					<button class="layui-btn" lay-submit=""lay-filter="submitUpdGoods">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
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

	<script src="/js/admin/goods.js?<%=System.currentTimeMillis()%>"></script>
	
</body>
</html>