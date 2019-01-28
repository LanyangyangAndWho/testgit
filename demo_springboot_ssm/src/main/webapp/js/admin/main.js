// 配置
layui.config({
	base : './hpModules/' // 扩展模块目录
}).extend({ // 模块别名
	hpTab : 'hpTab/hpTab',
	hpRightMenu : 'hpRightMenu/hpRightMenu',
	hpFormAll : 'hpFormAll/hpFormAll',
});

// JavaScript代码区域
layui.use([ 'element', 'carousel', 'hpTheme', 'hpTab', 'hpLayedit', 'hpRightMenu' ], function() {

	var element = layui.element;
	var carousel = layui.carousel; // 轮播
	var hpTab = layui.hpTab;
	var hpRightMenu = layui.hpRightMenu;
	var hpTheme = layui.hpTheme;
	$ = layui.$;
	$("#0").hide();
	// 初始化主题
	hpTheme.init();
	// 初始化轮播
	carousel.render({
		elem : '#test1',
		width : '100%', // 设置容器宽度
		interval : 1500,
		height : '500px',
		arrow : 'none', // 不显示箭头
		anim : 'fade', // 切换动画方式
	});

	// 初始化 动态tab
	hpTab.init();
	// 右键tab菜单
	hpRightMenu.init();

	if ($("#URLInterceptor").val() == 500) {
		layer.msg("请不要随意修改地址栏", {
			"icon" : 3,
			"time" : 2000
		});
	}

	// 退出
	$("#exit").click(function() {
		layer.confirm('您确定要退出吗？', function(index) {
			exit();
			layer.close(index);
		});
	});

	// 用户退出或者关闭浏览器事件
	function exit() {
		$.ajax({
			type : "post",
			url : "model/logout",
			success : function(data) {
				if (data == "success") {
					layer.msg("您已经安全退出", {
						icon : 6,
						time : 2000
					});
					setTimeout('window.location = "model/loginUI"', 1000);
				} else {
					layer.msg("很遗憾，退出异常", {
						icon : 5,
						time : 2000
					});
				}
			},
			error : function() {
				layer.msg("服务器异常", {
					icon : 2,
					time : 2000
				});
			}
		});
	}

});