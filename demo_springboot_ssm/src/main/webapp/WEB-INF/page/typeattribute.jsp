<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="public/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Typeattribute显示页面</title>
<%@ include file="public/meta.jsp"%>
</head>
<body>
	<div style="display: none; margin-top: 20px;" id="updTypeattributeDiv">
		<form class="layui-form" action="">
			<div class="layui-form-item"  >
			    <label class="layui-form-label">选择类型:</label>
				    <div class="layui-input-inline">
						<select name="typeId" id="typeId" lay-verify="required"
							lay-search>
							<option id="siddef" value="">--请选择类型--</option>
						</select>
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">属性:</label>
					<div class="layui-input-block" id="attr">
				    </div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="submitUpdTypeattribute">立即提交</button>
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
			<button class="layui-btn layui-btn-small layui-btn-normal"
				id="saveUI">
				<i class="layui-icon">&#xe608;</i>增加
			</button>
		</div>
		
		<table class="layui-table" id="listTable" lay-filter="list"></table>

		<script type="text/html" id="bar">
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="show"><i class="layui-icon">&#xe609;</i>查看</a>
 	  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
		</script>
	</div>

	<script src="/js/admin/typeattribute.js?<%=System.currentTimeMillis()%>"></script>
</body>
</html>