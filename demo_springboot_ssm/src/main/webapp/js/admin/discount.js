//Ordersjs文件
layui.use([ 'table', 'form', 'laydate' ], function() {
	var table = layui.table, form = layui.form, laydate = layui.laydate;
	var $ = layui.$;
	var currentPage = 1;
	loadDiscount();
	laydate.render({
	    elem: '#startTime'
	});
	laydate.render({
	    elem: '#endTime'
	});
	// 统一设置弹出层表单项宽度
	$(".layui-form .layui-input-inline").css("width", "300px");
	$(".layui-form .layui-form-label").css("width", "100px");

	// 加载表格数据
	function loadDiscount(jsonEntity) {
		table.render({
			elem : '#listTable',
			url : '/discount/selectAll',
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
				field : 'goodsName',
				title : '商品名称',
				align : 'center',
				sort : true,
				templet : '<div>{{d.goods.goodsName}}</div>'
			}, {
				field : 'discountNum',
				title : '折扣',
				align : 'center'
			}, {
				field : 'startTime',
				title : '折扣开始时间',
				align : 'center'
			},{
				field : 'endTime',
				title : '折扣结束时间',
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

	// 为添加按钮注册点击事件
	$("#saveUI").click(function() {
		layer.open({
			type : 1,
			title : "添加页面",
			area : [ '500px', '400px' ],
			offset : '20px',
			content : $("#updDiscountDiv"),
			success : function() {
				$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked')
				// 初始化商品tableSelect
				loadGoodsSelect();
				// 监听提交
				form.on('submit(submitUpdDiscount)', function() {
					var jsonEntity = {};
					var entity = $("form").serializeArray()
					$.each(entity, function() {
						jsonEntity[this.name] = this.value;
					});
					czDiscount("/discount/saveT", jsonEntity);
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
				jsonEntity['discountId'] = data.discountId;
				czDiscount("/discount/delete", jsonEntity, obj);
				layer.close(index);
			});
		} else if (obj.event === 'edit') {
			layer.open({
				type : 1,
				title : "信息编辑",
				area : '500px',
				offset : '20px',
				content : $("#updDiscountDiv"),
				success : function() {
					$("#goodsId").val(data.goods.goodsId);
					$("#discountNum").val(data.discountNum);
					$("#startTime").val(data.startTime);
					$("#endTime").val(data.endTime);
					$("#goodsName").val(data.goods.goodsName);
					$("#goodsName").attr("ts-selected", data.goods.goodsName);
					// 初始化门票tableSelect
					loadGoodsSelect(data.goods.goodsName);
					// 监听提交
					form.on('submit(submitUpdDiscount)', function() {
						var jsonEntity = {};
						var entity = $("form").serializeArray()
						$.each(entity, function() {
							jsonEntity[this.name] = this.value;
						});
						jsonEntity.discountId = data.discountId;
						czDiscount("/discount/updT", jsonEntity, obj);
						layer.closeAll();
						return false;
					});
				}
			});
		}
	});

	// 增删改操作
	function czDiscount(url, jsonEntity, obj) {
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
					layer.msg("信息修改成功", {
						"icon" : 6,
						"time" : 2000
					});
					flush(currentPage);
				} else if (data == "saveSuccess") {
					layer.msg("数据添加成功", {
						"icon" : 6,
						"time" : 2000
					});
					flush(1);
				} else if (data == "NumOfTicketsIs0") {
					layer.msg("数据添加失败", {
						"icon" : 5,
						"time" : 2000
					});
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
						params += data[i].discountId + ",";
					}
					delManyDiscount("/discount/deletes", params);
					flush(currentPage);
					layer.close(index);
				});
			}
		}
	};

	// 批量删除
	function delManyDiscount(url, ids) {
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
	
	// 初始化门票tableSelect
	function loadGoodsSelect() {
		var tableSelect = layui.tableSelect;
		tableSelect.render({
			elem: '#goodsName',
			checkedKey: 'goodsName',
			searchKey: 'goodsName',
			table: {
				url : '/goods/selectAll',
				cols: [[
					{ type: 'radio' },
					{ field: 'goodsId', title: 'ID' },
					{ field: 'goodsName', title: '商品名称' },
					{ field: 'addTimes', title: '上架时间' }
				]]
			},
			done: function (elem, data) {
				var NEWJSON = [];
				var TID = [];
				layui.each(data.data, function (index, item) {
					NEWJSON.push(item.goodsName);
					TID.push(item.goodsId);
				})
				elem.val(NEWJSON.join(","));
				$("#goodsId").val(TID.join(","));
				
				// tableSelect.js中z-index:66666666改为19891016，要不然会挡住tips，tableSelect关闭后没有自动关闭提示
				$(".layui-table-tips").hide();
			}
		})
	}
	// 为查询按钮注册点击事件
	$("#queryUI").click(function() {
		table.reload('listTable', {
			page : {
				curr : 1
			},
			where: {
				'goods.goodsName' : $("#goodsnameQuery").val()
            }
		});
	});

});
