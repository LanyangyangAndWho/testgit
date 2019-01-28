//Stadiajs文件
layui.use([ 'table', 'form', 'laydate' ], function() {
	var table = layui.table, form = layui.form, laydate = layui.laydate;
	var $ = layui.$;
	var currentPage = 1;
	loadCategory();
	
	// 统一设置弹出层表单项宽度
	$(".layui-form .layui-input-inline").css("width", "300px");
	$(".layui-form .layui-form-label").css("width", "100px");
	
	// 加载表格数据
	function loadCategory(jsonEntity) {
		table.render({
			elem : '#listTable',
			url : webName+'/goodsType/selectAll',
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
				field : 'typePId',
				title : '父类别ID',
				align : 'center'
			},{
				field : 'sortkey',
				title : '排序号',
				align : 'center'
			},{
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
	form.on('switch(switchTest)', function(data){
	    if(this.checked){
	    	 $("#typePId").attr("lay-verify","required");
	         $("#father").show();
	    }else{
	    	$("#typePId").removeAttr("lay-verify");
	    	$("#typePId").val("0");
	        $("#father").hide();
	            }
	            //do some ajax opeartiopns;
	        });
	// 为添加按钮注册点击事件
	$("#saveUI").click(function() {
		layer.open({
			type : 1,
			title : "添加页面",
			area : ['500px','500px'],
			offset : '20px',
			content : $("#updCategoryDiv"),
			success : function() {
				$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked');
				form.render('checkbox');
				// 初始化主体分类下拉框
				loadCategorySelect();
				// 监听提交
				form.on('submit(submitUpdCategory)', function() {
					var jsonEntity = {};
					var entity = $("form").serializeArray()
					$.each(entity, function() {
						jsonEntity[this.name] = this.value;
					});
					czCategory(webName+"/goodsType/saveT", jsonEntity);
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
				czCategory(webName+"/goodsType/delete", jsonEntity, obj);
				layer.close(index);
			});
		} else if (obj.event === 'edit') {
			layer.open({
				type : 1,
				title : "信息编辑",
				area : ['500px','500px'],
				offset : '20px',
				content : $("#updCategoryDiv"),
				success : function() {
					$("#typeName").val(data.typeName);
					
				    
				    
					
					if(data.typePId==0){
											
						$("#father").hide();
						$("#typePId").val("0");
						$("input[name='switch']").prop("checked",false);
						form.render("checkbox");
						$("#typePId").removeAttr("lay-verify");
						form.render("checkbox");
						
					}else{
						 form.render('checkbox');
						 $("#father").show();
						 $("input[name='switch']").prop("checked",true);
						 form.render("checkbox");
						 $("#typePId").attr("lay-verify","required");
						
					}
					$("#sortkey").val(data.sortkey);
					// 初始化主体分类下拉框
					loadCategorySelect(data.typePId);
					// 监听提交
					form.on('submit(submitUpdCategory)', function() {
						var jsonEntity = {};
						var entity = $("form").serializeArray()
						$.each(entity, function() {
							jsonEntity[this.name] = this.value;
						});
						jsonEntity.typeId = data.typeId;
						czCategory(webName+"/goodsType/updT", jsonEntity, obj);
						layer.closeAll();
						return false;
					});
				}
			});
		}
	});

	// 增删改操作
	function czCategory(url, jsonEntity, obj) {
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
				} else if (data == "updSuccess") {
					flush(currentPage);
					layer.msg("信息修改成功", {
						"icon" : 6,
						"time" : 2000
					});
					
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

	// 监听表格
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
					delManyCategory(webName+"/goodsType/deletes", params);
					flush(currentPage);
					layer.close(index);
				});
			}
		}
	};

	// 批量删除
	function delManyCategory(url, ids) {
		$.ajax({
			type : "post",
			url : url,
			data : {
				'ids' : ids
			},
			success : function(data) {
				if (data == "delSuccess") {
					layer.msg("批量删除数据成功", {
						"icon" : 6,
						"time" : 2000
					});
					flush(currentPage);
				} else {
					layer.msg("批量删除数据失败", {
						"icon" : 2,
						"time" : 2000
					});
				}
			},
			error : function() {
				layer.msg("对不起，服务器异常", {
					"icon" : 3,
					"time" : 2000
				});
			}
		});
	}
	// 初始化主体分类下拉框
	function loadCategorySelect(optionValue) {
		$.ajax({
			type : "post",
			url : webName+"/goodsType/selectpid",
			success : function(data) {
				$("#typePId option:not(:first)").remove();
				var data = data.data;
				for ( var p in data) {
					$("#typePId").append("<option " + (data[p].typeId == optionValue ? "selected" : "") + " value='" + data[p].typeId + "'>" + data[p].typeName + "</option>");
				}
				form.render('select'); // 渲染下拉
			}
		});
	}
	// 为查询按钮注册点击事件
	$("#queryUI").click(function() {
		table.reload('listTable', {
			page : {
				curr : 1
			},
			where: {
				typeName : $("#typenameQuery").val()
            }
		});
	});
	
});