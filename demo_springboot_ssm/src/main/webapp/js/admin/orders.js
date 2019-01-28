//Ordersjs文件
layui.use([ 'table', 'form', 'laydate' ], function() {
	var table = layui.table, form = layui.form, laydate = layui.laydate;
	var $ = layui.$;
	var currentPage = 1;
	loadOrders();

	// 统一设置弹出层表单项宽度
	$(".layui-form .layui-input-inline").css("width", "300px");
	$(".layui-form .layui-form-label").css("width", "100px");

	// 加载表格数据
	function loadOrders(jsonEntity) {
		table.render({
			elem : '#listTable',
			url : '/orders/selectAll',
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
				field : 'username',
				title : '用户名',
				align : 'center',
				sort : true,
			}, {
				field : 'goodsinformation',
				title : '商品信息',
				align : 'center',
				sort : true,
			},{
				field : 'paytime',
				title : '付款时间',
				align : 'center',
				sort : true,
			},  {
				field : 'totalprice',
				title : '应付金额',
				align : 'center',			
			},{
				field : 'recipientinfo',
				title : '收货信息',
				align : 'center',			
			},{
				field : 'number',
				title : '数量',
				align : 'center',			
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


	// 添加快递信息
	table.on('tool(list)', function(obj) {
		var data = obj.data;
	    if (obj.event === 'edit') {
	    	layer.open({
				type : 1,
				title : "添加页面",
				area : '500px',
				offset : '20px',
				content : $("#updOrdersDiv"),
				success : function() {
					$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked')
					// 监听提交
					form.on('submit(submitUpdOrders)', function() {
						var jsonEntity = {};
						var entity = $("form").serializeArray()
						$.each(entity, function() {
							jsonEntity[this.name] = this.value;
						});
						czOrders("/orders/saveT", jsonEntity);
						layer.closeAll();
						return false;
					});
				}
			});
	      }
        });

	// 增删改操作
	function czOrders(url, jsonEntity, obj) {
		$.ajax({
			type : "post",
			url : url,
			data : jsonEntity,
			success : function(data) {
				if (data == "saveSuccess") {
					layer.msg("信息添加成功", {
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
				layer.msg("对不起，服务器异常", {
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
	// 初始化日期选择器
	laydate.render({
		elem : '#begintimeQuery',
		range : true
	});
	// 为查询按钮注册点击事件
	$("#queryUI").click(function() {
		table.reload('listTable', {
			page : {
				curr : 1
			},
			where : {
				begintime1 : $("#begintimeQuery").val().substr(0, 10),
				begintime2 : $("#begintimeQuery").val().substr(13, 10)
			}
		});
	});


	
	
});
