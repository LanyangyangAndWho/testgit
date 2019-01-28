// 表单全屏层
// hpFormAll
layui.define(['layer', 'element', 'table', 'hpConfig'], function(exports) {
	var $ = layui.jquery;
	var layer = layui.layer;
	var element = layui.element;
	var table = layui.table;
	var hpConfig = layui.hpConfig;
	var hpWindow = {
		open: function(elm, url, opt) {
			$(document).on('click', elm, function() {

				if(hpConfig.isAjaxType == 'get') {
					$.get(url, {}, function(str) {
						var param = {
							type: 1,
							content: str,
						}
						$.extend(opt, param)
						layer.open(opt);
					});
				} else {

					$.post(url, {}, function(str) {
						var param = {
							type: 1,
							content: str
						}
						$.extend(opt, param)
						layer.open(opt);
					});

				}

			});
		},
		// 弹出列表层
		openList: function(elm, url, dataObj, callBack, opt) {

			$(document).on('click', elm, function() {
				var param = {
					type: 1,
					tableDom: 'kd-table',
					area: ['auto', 'auto'],
					btn: ['确认', '取消'],
					yes: function(index, layero) {
						var checkStatus = table.checkStatus(paramData.tableDom),
							data = checkStatus.data;
						// 回调函数
						callBack(data, index, layero);
						layer.close(index);
					},
				}
				// 合并参数
				var paramData = $.extend(param, opt, {}) // 后者覆盖前者
				// 兼容静态服务器
				if(hpConfig.isAjaxType == 'get') {
					$.get(url, dataObj, function(str) {
						paramData.type = 1;
						paramData.content = str;
						layer.open(paramData);
					})
				} else {
					$.post(url, dataObj, function(str) {
						paramData.type = 1;
						paramData.content = str;
						layer.open(paramData);
					});
				}

			});
		}

	}

	exports('hpWindow', hpWindow);
})