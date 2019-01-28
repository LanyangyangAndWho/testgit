<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>母婴商城</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/static/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/layui/css/layui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script type="text/javascript">
	var webName = "${pageContext.request.contextPath}";
</script>
<style type="text/css">
.Ori-pic {
	text-decoration: line-through;
}
</style>
</head>
<body>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>母婴商城</title>
</head>

<body>
	<div class="site-nav-bg">
		<div class="site-nav w1200">
			<p class="sn-back-home">
				<i class="layui-icon layui-icon-home"></i> <a href="#">首页</a>
			</p>
			<div class="sn-quick-menu">
				<div class="login">
					<a href="${pageContext.request.contextPath}/model/index">登录</a>
				</div>
				<div class="sp-cart">
					<a href="shopcart.html">购物车</a><span>2</span>
				</div>
			</div>
		</div>
	</div>



	<div class="header">
		<div class="headerLayout w1200">
			<div class="headerCon">
				<h1 class="mallLogo">
					<a href="#" title="母婴商城"> <img src="../res/static/img/logo.png">
					</a>
				</h1>
				<div class="mallSearch">
					<form action="" class="layui-form" novalidate>
						<input type="text" name="title" required lay-verify="required"
							autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
						<button class="layui-btn" lay-submit lay-filter="formDemo">
							<i class="layui-icon layui-icon-search"></i>
						</button>
						<input type="hidden" name="" value="">
					</form>
				</div>
			</div>
		</div>
	</div>

 
	<div class="content content-nav-base buytoday-content">
		<div id="list-cont"></div>
	</div>
	
	<!-- 模版引擎导入 -->
	<script type="text/html" id="demo">
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="commodity.html">所有商品</a>
            <a href=""${pageContext.request.contextPath}/model/buytoday"" class="active">今日团购</a>
            <a href="information.html">母婴资讯</a>
            <a href="about.html">关于我们</a>
          </div>
        </div>
      </div>
    </div>
    <div class="banner-box">
      <div class="banner"></div>
    </div>
    <div class="product-list-box">
      <div class="product-list w1200">  
        <div class="tab-title">
          <a href="javascript:;" class="active tuang" data-content="tuangou">今日团购</a>
          <a href="javascript:;" data-content="yukao">明日预告</a>
        </div>
        <div class="list-cont" cont = 'tuangou'>
          <div class="item-box layui-clear">
              {{# layui.each(d.data,function(index,item){}}
              <div class="item">
                <img src="{{item.goods.defaultPhotoUrl==null?'':item.goods.defaultPhotoUrl}}" alt="">
                <div class="text-box">
                  <p class="title">{{item.goods.goodsName}}</p>
                  <p class="plic">
                    <span class="ciur-pic">￥{{item.goods.activitiPrice}}</span>
                    <span class="Ori-pic">￥{{item.goods.price}}</span>
                    <span class="discount">{{item.discountNum}}折</span>
                  </p>
                </div>
              </div>
			  {{# });}}
          </div>
       <div id="demo0" style="text-align: center;"></div>
        </div>

 <div class="list-cont layui-hide" cont = 'yukao'>
     <div class="item-box layui-clear">
 			 {{# layui.each(d.tomorrow,function(index,item){}}
              <div class="item">
                <img src="{{item.goods.defaultPhotoUrl}}" alt="">
                <div class="text-box">
                  <p class="title">{{item.goods.goodsName}}</p>
                  <p class="plic">
                    <span class="ciur-pic">￥{{item.goods.activitiPrice}}</span>
                    <span class="Ori-pic">￥{{item.goods.price}}</span>
                    <span class="discount">{{item.discountNum}}折</span>
                  </p>
                </div>
              </div>
              {{#})}}
      </div>  
  	  <div id="demo1" style="text-align: center;"></div>
    </div>
</div>
    <div class="footer-wrap">
      <div class="footer w1200">
        <div class="title">
          <h3>团购销量榜</h3>
        </div>
        <div class="list-box layui-clear" id="footerList">
             {{# layui.each(d.buyDesc,function(index,item){}}
              <div class="item">
                <img src="{{item.goods.defaultPhotoUrl}}" alt="">
                <div class="text">
                  <div class="right-title-number">{{index+1}}</div>
                  <div class="commod">
                    <p>{{item.goods.goodsName}}</p>
                    <span>￥ {{item.goods.activitiPrice}}</span>
                  </div>
                </div>
              </div>
              {{# });}}
        </div>
 		
      </div>
</div>
  </script>
	<script>
		//获取当前时间，格式YYYY-MM-DD
		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = year + seperator1 + month + seperator1 + strDate;
			return currentdate;
		}
	
	
		layui.config({
			base : '${pageContext.request.contextPath}/res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
		}).use([ 'mm', 'laypage', 'jquery' ], function() {
			var laypage = layui.laypage, $ = layui.$, mm = layui.mm;

			$('body').on('click', '*[data-content]', function() {
				$(this).addClass('active').siblings().removeClass('active')
				var dataConte = $(this).attr('data-content');
				$('*[cont]').each(function(i, item) {
					var itemCont = $(item).attr('cont');
					console.log(item)
					if (dataConte === itemCont) {
						$(item).removeClass('layui-hide');
					} else {
						$(item).addClass('layui-hide');
					}
				})
					
			})
			var now = getNowFormatDate();
			var curr = 1,limit = 4
			getMM(curr,limit);
		

			function getMM(page,limit) {
				// 模版引擎导入
				var html = demo.innerHTML;
				var listCont = document.getElementById('list-cont');
				mm.request({
					url : webName + '/discount/buytoday?now=' + now + '&limit='+limit+'&page='+curr,
					success : function(res) {
						listCont.innerHTML = mm.renderHtml(html,res);
						laypage.render({
							elem : 'demo0',
							curr : curr,
							limit : limit,
							count : res.count,//数据总数
							jump : function(obj, first) {
								 if (!first) {
									getMM(obj.curr,limit);
								} 
							}
						});
						laypage.render({
							elem : 'demo1',
							curr : page,
							limit : limit,
							count : res.count1,//数据总数
							jump : function(obj, first) {
								 if (!first) {
									getMM(obj.curr,limit);
								} 
							}
						});
					}						
				});
			}

		});
	</script>
</body>
</html>