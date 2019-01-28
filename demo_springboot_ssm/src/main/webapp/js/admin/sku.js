//Ordersjs文件
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
	loadSku();
	// 统一设置弹出层表单项宽度
	$(".layui-form .layui-input-inline").css("width", "300px");
	$(".layui-form .layui-form-label").css("width", "100px");

	// 加载表格数据
	function loadSku(jsonEntity) {
		table.render({
			elem : '#listTable',
			url : '/sku/selectAll',
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
				field : 'goodsname',
				title : '商品名称',
				align : 'center',
				sort : true,
				templet : '<div>{{d.macgoods.goodsname}}</div>'
			}, {
				field : 'skuattrs',
				title : '属性',
				align : 'center'
			}, {
				field : 'price',
				title : '价格',
				align : 'center'
			},{
				field : 'store',
				title : '库存',
				align : 'center'
			},{
				field : 'skusales',
				title : '销量',
				align : 'center'
			},{
				field : 'photourl',
				title : '图片',
				align : 'center',
				templet : "<div  ><img  src='{{d.photourl}}' /></div>",
				
				
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
			area :  '500px',
			offset : '20px',
			content : $("#updSkuDiv"),
			success : function() {
				$(':input', 'form').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked')
				// 初始化商品tableSelect
				loadGoodsSelect();
				$("#imgPr1").attr('src', "/upload/default.jpg");
				$("#add").empty();
				$("#photourl").uploadPreview({ Img: "imgPr1", Width: 200, Height: 200 });
				// 监听提交
				form.on('submit(submitUpdSku)', function() {
					var entity1 = $("form").serializeArray();
					var temp="";
					var re = /^[0-9]+.?[0-9]*$/;
					$.each(entity1, function() {
						var a=this.name;
						if(re.test(a) ){
							temp=temp+a+":"+this.value+" ";
							
						}
					});

					$("#add").empty();
					$("#add").append("<input type='hidden' name='temp' value='' id='temp'/>");
					$("#temp").val(temp);
					var entity = new FormData(document.getElementById("up"));
					czSku("/sku/save", entity);
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
				jsonEntity['skuid'] = data.skuid;
				czSkus("/sku/delete", jsonEntity, obj);
				layer.close(index);
			});
		} 
	});
	//加载属性名
	function attrname(url, jsonEntity){
		$.ajax({
			type : "post",
			url : url,
			data : jsonEntity,
			success : function(data) {
				$("#add").empty();
				var data1 = data.data;
				for ( var p in data1) {
					$("#add").append("<div class='layui-form-item'><div class='layui-inline'><label class='layui-form-label'>"+data1[p].attrname+"</label><div class='layui-input-inline'><input type='text' name='"+data1[p].attrnameid+"' lay-verify='required|number'autocomplete='off' placeholder='属性值' class='layui-input'></div></div></div>");
				}
				form.render();
			}
		});
	}
	// 删除操作
	function czSkus(url, entity, obj) {
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
	//增操作
	function czSku(url, entity, obj) {
		$.ajax({
			type : "post",
			url : url,
			data : entity,
			processData : false, 			
			contentType : false,
			success : function(data) {
				 if (data == "saveSuccess") {
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
						params += data[i].skuid + ",";
					}
					delManySku("/sku/deletes", params);
					flush(currentPage);
					layer.close(index);
				});
			}
		}
	};

	// 批量删除
	function delManySku(url, ids) {
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
	
	// 初始化商品tableSelect
	function loadGoodsSelect() {
		var tableSelect = layui.tableSelect;
		tableSelect.render({
			elem: '#goodsname',
			checkedKey: 'goodsname',
			searchKey: 'goodsname',
			table: {
				url : '/goods/selectAll',
				cols: [[
					{ type: 'radio' },
					{ field: 'goodsid', title: 'ID' },
					{ field: 'goodsname', title: '商品名称' },
					{ field: 'addtime', title: '上架时间' }
				]]
			},
			done: function (elem, data) {
				var NEWJSON = [];
				var TID = [];
				layui.each(data.data, function (index, item) {
					NEWJSON.push(item.goodsname);
					TID.push(item.goodsid);
				})
				elem.val(NEWJSON.join(","));
				$("#goodsid").val(TID.join(","));		
				// tableSelect.js中z-index:66666666改为19891016，要不然会挡住tips，tableSelect关闭后没有自动关闭提示
				$(".layui-table-tips").hide();
				var jsonEntity = {};
				jsonEntity['macgoods.goodsid']=$("#goodsid").val();
			    attrname("/sku/selectattname",jsonEntity);
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
				'macgoods.goodsname' : $("#goodsnameQuery").val()
            }
		});
	});

});
