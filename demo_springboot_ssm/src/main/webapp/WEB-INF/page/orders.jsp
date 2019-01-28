<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
  <script type="text/javascript" src="res/layui/layui.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <style type="text/css">

		.info span{
			float: right;
			
		}
		button{
			float: right;
			margin-bottom: 40px;
		}
	</style>
  <script type="text/javascript">var webName="${request.pageContext.contextPath }"; var ordersId="${param['ordersId']}"
	  layui.config({
		    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
		  }).use(['mm','jquery','element','car'],function(){
		    var mm = layui.mm,$ = layui.$,element = layui.element,car = layui.car;
		    
		    // 模版导入数据
		     var html = demo.innerHTML,
		     table = document.getElementById('table');
		     mm.request({
		       url: 'orders/selectOrders',
		       data:{"ordersId":ordersId},
		       success : function(res){
		    	   table.innerHTML = mm.renderHtml(html,res)
		       },
		       error: function(res){
		         console.log(res);
		       }
		     })
		});
  </script>
</head>
<body>
  <div class="site-nav-bg">
    <div class="site-nav w1200">
      <p class="sn-back-home">
        <i class="layui-icon layui-icon-home"></i>
        <a href="commodity.html">首页</a>
      </p>
      <div class="sn-quick-menu">
        <div class="login"><a href="${request.pageContext.contextPath }/model/index">登录</a></div>
        <div class="sp-cart"><a href="${request.pageContext.contextPath }/model/shopcart">购物车</a><span>${sessionScope.shopcart==null?'':sessionScope.shopcart }</span></div>
      </div>
    </div>
  </div>



  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="#" title="母婴商城">
            <img src="../res/static/img/logo.png">
          </a>
        </h1>
        <div class="mallSearch">
          <form action="" class="layui-form" novalidate>
            <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
            <button class="layui-btn" lay-submit lay-filter="formDemo">
                <i class="layui-icon layui-icon-search"></i>
            </button>
            <input type="hidden" name="" value="">
          </form>
        </div>
      </div>
    </div>
  </div>


  <div class="content content-nav-base shopcart-content">
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="commodity.html" class="active">所有商品</a>
            <a href="buytoday.html">今日团购</a>
            <a href="information.html">母婴资讯</a>
            <a href="about.html">关于我们</a>
          </div>
        </div>
      </div>
    </div>
	</div>
	
  <div id="table" style="width: 70%;margin: 0px auto;margin-top: 10px;">
	
</div>

<script type="text/html" id="demo">
		<h2><i>确认收货地址:</i></h2>
		<blockquote style="margin-top: 10px;" class="layui-elem-quote layui-quote-nm">{{d.recipientInfo.recipientAddress}}  {{d.recipientInfo.phone}}<input type="hidden" id="recipientId" value="{{d.recipientInfo.recipientId}}"> <a href="#" style="float: right;color: #0000FF;">修改地址</a></blockquote>
		<hr />
		<h2><i>确认订单信息:</i></h2>
		<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>商品名称</th>
      <th>商品属性</th>
      <th>单价</th>
	  <th>数量</th>
	  <th>折扣</th>
	  <th>小计</th>
    </tr> 
  </thead>
  <tbody>
	{{# layui.each(d.data.odersTitleBlockList,function(index,item){}} 
		 <tr>
      <td><div class="item-cont">
						<img  src="{{item.sku.photoUrl}}" />
						<div class="title">{{item.sku.goods.goodsName}}</div>
			</div></td>
      <td>{{# layui.each(item.attrValueList,function(index,item1){}} 
			{{item1.attrValue}}
		  {{# }); }}
	  </td>
      <td>{{item.sku.price}}</td>
	  <td>{{item.number}}</td>
	  <td>{{item.sku.goods.discountNum==null?'':item.sku.goods.discountNum+'折'}}</td>
	  <td>{{item.sumPrice}}</td>
    </tr>
	{{# }); }}
			<tr>
				<td colspan="6" class="info">
					<span><i>实付款：</i>￥<big style="color: orangered;"><span>{{d.data.totalPrice}}</span></big></span><br /><br />
					<span><i>寄送至：</i><sapn>{{d.recipientInfo.recipientAddress}}</sapn></span><br /><br />
					<span><i>收货人：</i><span>{{d.recipientInfo.recipientOfficer}}&nbsp;<i>{{d.recipientInfo.phone}}</i></span></span><br /><br />
				</td>
			</tr>
  </tbody>
</table>
<button class="layui-btn layui-btn-warm layui-btn-lg">提交订单</button>
<hr /> 
</script>
<script type="text/javascript" src="js/orders.js"></script>
</body>
</html>