//Goodsjs文件
layui.use([ 'table', 'form', 'laydate' ], function() {
	var table = layui.table, form = layui.form, laydate = layui.laydate;
	var $ = layui.$;
	$.fn.extend({
	    uploadPreview: function (opts) {
	        var _self = this,
	            _this = $(this);
	        opts = $.extend({
	            Img: this.Img,
	            Width: 200,
	            Height: 200,
	            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
	            Callback: function () {}
	        }, opts || {});
	        _self.getObjectURL = function (file) {
	            var url = null;
	            if (window.createObjectURL != undefined) {
	                url = window.createObjectURL(file)
	            } else if (window.URL != undefined) {
	                url = window.URL.createObjectURL(file)
	            } else if (window.webkitURL != undefined) {
	                url = window.webkitURL.createObjectURL(file)
	            }
	            return url
	        };
	        _this.change(function () {
	            if (this.value) {
	                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
	                	layer.msg("选择文件错误,图片类型必须是 gif ,  jpeg ,  jpg ,  bmp ,  png 中的一种", {
	    					"icon" : 3,
	    					"time" : 2000
	    				});
	                    this.value = "";
	                    return false
	                }else {
	                    $("#"+ opts.Img).attr('src', _self.getObjectURL(this.files[0]))
	                }
	                opts.Callback()
	            }else{
	            	 $("#" + opts.Img).removeAttr('src');
	            	 $("#"+ opts.Img).attr('src', "/upload/default.jpg")
	            }
	        })
	    }
	});
	var currentPage = 1;
	loadGoods();
	laydate.render({
		    elem: '#addTimes'
		});
	// 统一设置弹出层表单项宽度
	$(".layui-form .layui-input-inline").css("width", "300px");
	$(".layui-form .layui-form-label").css("width", "100px");
	
	// 加载表格数据
	function loadGoods(jsonEntity) {
		table.render({
			elem : '#listTable',
			url : '/goods/selectAll',
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
				sort : true
			}, {
				field : 'typeId',
				title : '类别ID',
				align : 'center',
				templet :"<div>{{d.goodsType.typeId}}</div>"
			}, {
				field : 'price',
				title : '价格 ',
				align : 'center',
				sort : true
			},{
				field : 'addTimes',
				title : '上架时间 ',
				align : 'center',
				sort : true
			},{
				field : 'defaultPhotoUrl',
				title : '商品主图片 ',
				align : 'center',
				templet : "<div><img src='{{d.defaultPhotoUrl}}' /></div>"
			},{
				field : 'detailsPhotoUrl',
				title : '商品详情图片 ',
				align : 'center',
				templet : "<div><img src='{{d.detailsPhotoUrl}}'  /></div>"
			},   {
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
			area : '500px',
			offset : '20px',
			content : $("#updGoodsDiv"),
			success : function() {
				$("#id").empty();
				$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked')
				// 初始化主体分类下拉框
				loadTypeSelect();
				$("#imgPr1").attr('src', "/upload/default.jpg");
				$("#imgPr2").attr('src', "/upload/default.jpg");
				$("#defaultPhotoUrl").uploadPreview({ Img: "imgPr1", Width: 200, Height: 200 });
				$("#detailsPhotoUrl").uploadPreview({ Img: "imgPr2", Width: 200, Height: 200 });
				
				// 监听提交
				form.on('submit(submitUpdGoods)', function() {
					var entity = new FormData(document.getElementById("up"));				
					czGoods("/goods/save", entity);
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
				var entity = {};
				entity['goodsId'] = data.goodsId;
				alert(data.goodsId)
				czGood("/goods/delete", entity, obj);
				layer.close(index);
			});
		} else if (obj.event === 'edit') {
			layer.open({
				type : 1,
				title : "信息编辑",
				area : '500px',
				offset : '20px',
				content : $("#updGoodsDiv"),
				success : function() {
					$("#id").empty();
					$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked');
					$("#id").append("<input type='hidden' name='goodsId' value='"+data.goodsId+"'/>");
					$("#goodsName").val(data.goodsName);
					$("#price").val(data.price);
					$("#addTimes").val(data.addTimes);
					$("#imgPr1").attr('src',data.defaultPhotoUrl );
					$("#imgPr2").attr('src',data.detailsPhotoUrl );
					// 初始化主体分类下拉框
					loadTypeSelect(data.typeId);
					$("#defaultPhotoUrl").uploadPreview({ Img: "imgPr1", Width: 200, Height: 200 });
					$("#detailsPhotoUrl").uploadPreview({ Img: "imgPr2", Width: 200, Height: 200 });
					// 监听提交
					form.on('submit(submitUpdGoods)', function() {
						var entity = new FormData(document.getElementById("up"));	
						czGoods("/goods/upd", entity, obj);
						layer.closeAll();
						return false;
					});
				}
			});
		}
	});

	// 删除操作
	function czGood(url, entity, obj) {
		$.ajax({
			type : "post",
			url : url,
			data : entity,			
			success : function(data) {
				if (data == "delSuccess") {
					layer.msg("删除单个成功", {
						"icon" : 6,
						"time" : 2000
					});
					obj.del();
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
	//增改操作
	function czGoods(url, entity, obj) {
		$.ajax({
			type : "post",
			url : url,
			data : entity,
			processData : false, 			
			contentType : false,
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
					flush(1);
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
						params += data[i].goodsId + ",";
					}
					delManyGoods("/goods/deletes", params);
					flush(currentPage);
					layer.close(index);
				});
			}
		}
	};

	// 批量删除
	function delManyGoods(url, ids) {
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
	function loadTypeSelect(optionValue) {
		$.ajax({
			type : "post",
			url : "/goods/selecttypeid",
			success : function(data) {
				$("#typeId option:not(:first)").remove();
				var data1 = data.data;
				for ( var p in data1) {
					$("#typeId").append("<option " + (data1[p].typeId == optionValue ? "selected" : "") + " value='" + data1[p].typeId + "'>" + data1[p].typeName + "</option>");
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
				goodsName : $("#goodsnameQuery").val()
            }
		});
	});
	
});