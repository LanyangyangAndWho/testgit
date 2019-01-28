<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/public/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Category显示页面</title>
<%@ include file="/WEB-INF/page/public/meta.jsp"%>
<script type="text/javascript">var webName="${request.pageContext.contectPath}"</script>
</head>
<body>
	<div style="display: none; margin-top: 20px;" id="updCategoryDiv">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">类别名称</label>
					<div class="layui-input-inline">
						<input type="text" name="typeName" lay-verify="required"
							autocomplete="off" placeholder="请输入类别名称" class="layui-input"
							id="typeName">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				    <label class="layui-form-label" >主体分类</label>
				    <div class="layui-input-block" >
				       <input type="checkbox" checked="" name="switch" lay-skin="switch" id="switch" lay-filter="switchTest" lay-text="不是|是">
				    </div>
		    </div>
			<div class="layui-form-item"  id="father">
			    <label class="layui-form-label">选择类型:</label>
				   <div class="layui-input-inline">
						<select name="typePId" id="typePId" lay-verify="required"
							lay-search>
							<option id="siddef" value="">--请选择类型--</option>
						</select>
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">排序号</label>
					<div class="layui-input-inline">
						<input type="text" name="sortkey" lay-verify="required|number"
							autocomplete="off" placeholder="请输入排序号" class="layui-input"
							id="sortkey">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="submitUpdCategory">立即提交</button>
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
				<input type="text" autocomplete="off" placeholder="搜索类别名称"
					class="layui-input" id="typenameQuery">
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

	<script src="${request.pageContext.contextPath }/js/admin/category.js"></script>
</body>
</html>