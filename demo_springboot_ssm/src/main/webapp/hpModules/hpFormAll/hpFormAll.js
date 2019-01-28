// 表单全屏层
// hpFormAll
layui.define(['layer', 'element', 'hpConfig'], function(exports) {
	var $ = layui.jquery;
	var layer = layui.layer;
	var element = layui.element;
    var hpConfig = layui.hpConfig;
	var hpFormAll = {
		open: function(elm, url, title) {
			$(document).on('click', elm, function() {

				if(hpConfig.isAjaxType == 'get') {
					$.get(url, {}, function(str) {
						layer.open({
							type: 1,
							content: str,
							maxmin: true,
							shade: 0,
							title: title || '无标题',
							success: function(layero, index) {
								layer.full(index);
								setTimeout(function() {
									layero.find(".layui-layer-min").show();
									layero.find(".layui-layer-maxmin").hide()
								}, 100);
							},
							full: function(layero) {
							},
							min: function(layero) {
								layero.find(".layui-layer-setwin a").eq(1).show();
							},
							restore: function(layero) {
								layero.find(".layui-layer-max").hide();
							},
						});
					});
				} else {
					$.post(url, {}, function(str) {
						layer.open({
							type: 1,
							content: str,
							maxmin: true,
							shade: 0,
							title: title || '无标题',
							success: function(layero, index) {
								layer.full(index);
								setTimeout(function() {
									layero.find(".layui-layer-min").show();
									layero.find(".layui-layer-maxmin").hide()
								}, 100)
							},
							full: function(layero) {
							},
							min: function(layero) {
								layero.find(".layui-layer-setwin a").eq(1).show();
							},
							restore: function(layero) {
								layero.find(".layui-layer-max").hide();
							},
						});
					});
				}

			});

		},
		openOpt: function(elm, url, opt) {
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
		}

	}

	exports('hpFormAll', hpFormAll);
})