//Stadiajs文件
layui.use([ 'table', 'form', 'laydate' ], function() {
	var table = layui.table, form = layui.form, laydate = layui.laydate;
	var $ = layui.$;
	var currentPage = 1;
	loadTypeattribute();
	
	// 统一设置弹出层表单项宽度
	$(".layui-form .layui-input-inline").css("width", "300px");
	$(".layui-form .layui-form-label").css("width", "100px");
	
	// 加载表格数据
	function loadTypeattribute(jsonEntity) {
		table.render({
			elem : '#listTable',
			url : '/typeattribute/selectAll',
			height : 'full-200',
			page : true,
			where : jsonEntity,
			limit : 10,
			cols : [ [ {
				checkbox : true
			}, {
				title : '序号',
				align : 'center',
				type : 'numbers'
			}, {
				field : 'typeName',
				title : '类别名称',
				align : 'center',
				sort : true
			}, {
				field : 'attrName',
				title : '属性',
				align : 'center'
			}, {
				fixed : 'right',
				minWidth : 210,
				title : '操作',
				align : 'center',
				toolbar : '#bar'
			} ] ],
			done : function(res, curr, count) {
				currentPage = curr;
			}
		});
	}
	
	// 为添加按钮注册点击事件
	$("#saveUI").click(function() {
		layer.open({
			type : 1,
			title : "添加页面",
			area : ['500px','500px'],
			offset : '20px',
			content : $("#updTypeattributeDiv"),
			success : function() {
				$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked')
				$('#attr').empty();
				// 初始化类别下拉框
				loadTypeSelect();
				
				// 监听提交
				form.on('submit(submitUpdTypeattribute)', function() {
					var arr = new Array();
					var attrIds="";
		            $("input:checkbox[name='attrNameIds']:checked").each(function(i){
		                arr[i] = $(this).val();
		                attrIds=attrIds+" "+arr[i];
		            });
					var jsonEntity = {};
					var entity = $("form").serializeArray()	;				
					$.each(entity, function() {
						jsonEntity[this.name] = this.value;
					});
					jsonEntity["attrNameIds"]=attrIds;
					czTypeattribute("/typeattribute/save", jsonEntity);
					layer.closeAll();
					return false;
				});
			}
		});
	});
	
	// 查看 修改 删除 监听工具条
	table.on('tool(list)', function(obj) {
		var data = obj.data;
		if (obj.event === 'show') {
			layer.alert('查看行：<br>' + JSON.stringify(data))
		} else if (obj.event === 'del') {
			layer.confirm('你确定要删除吗？', function(index) {
				var jsonEntity = {};
				jsonEntity['typeId'] = data.typeId;
				czTypeattribute("/typeattribute/delete", jsonEntity, obj);
				layer.close(index);
			});
		} 
	});

	// 增删改操作
	function czTypeattribute(url, jsonEntity, obj) {
		
		$.ajax({
			type : "post",
			url : url,
			data : jsonEntity,
			success : function(data) {
				if (data == "delSuccess") {
					layer.msg("删除单个成功", {
						"icon" : 6,
						"time" : 2000
					});
					obj.del();
				} else if (data == "saveSuccess") {
					layer.msg("数据添加改成功", {
						"icon" : 6,
						"time" : 2000
					});
					flush(1);
				} else {
					layer.msg("操作失败", {
						"icon" : 2,
						"time" : 2000
					});
				}
			},
			error : function() {
				layer.msg("操作异常", {
					"icon" : 3,
					"time" : 2000
				});
			}
		});
	}

	// 为表格相关按钮注册点击事件
	$('.listTable .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	active = {
			// 获取选中行数据
			getCheckData : function() {
				var checkStatus = table.checkStatus('listTable'), data = checkStatus.data;
				if (data.length < 1) {
					layer.alert('请选择数据')
				} else {
					layer.confirm("你确定要删除所选中的数据吗？", {
						icon : 7,
						title : '提示'
					}, function(index) {
						var params = "";
						for (var i = 0; i < data.length; i++) {
							params += data[i].typeId + ",";
						}
						delManyCategory("/category/deletes", params);
						flush(currentPage);
						layer.close(index);
					});
				}
			}
		};
	// 为刷新按钮注册点击事件
	$("#flush").click(function() {
		flush(currentPage);
	});
	
	// 刷新
	function flush(currentPage) {
		table.reload('listTable', {
			page : {
				curr : currentPage
			},
			where : null
		});
	}

	

	
	// 初始化主体分类下拉框
	function loadTypeSelect(optionValue) {
		$.ajax({
			type : "post",
			url : "/typeattribute/selecttypeid",
			success : function(data) {
				$("#typeId option:not(:first)").remove();
				var data1 = data.data;
				for ( var p in data1) {
					$("#typeId").append("<option " + (data1[p].typeId == optionValue ? "selected" : "") + " value='" + data1[p].typeId + "'>" + data1[p].typeName + "</option>");
				}
				// 初始化属性复选框
				loadattrSelect();
				form.render('select'); // 渲染下拉
			}
		});
	}
	// 初始化属性复选框
	function loadattrSelect() {
		$.ajax({
			type : "post",
			url : "/typeattribute/selectattr",
			success : function(data) {
				var data2 = data.data;
				for ( var p in data2) {					
					$("#attr").append("<input type='checkbox'  id='attrNameIds' name='attrNameIds' title='"+data2[p].attrName+"' value='" + data2[p].attrNameId + "'>");
				}
				form.render('checkbox'); // 渲染下拉
			}
		});
	}
	
	
});