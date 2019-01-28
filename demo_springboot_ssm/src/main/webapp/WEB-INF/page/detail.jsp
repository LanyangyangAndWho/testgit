<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
  <script type="text/javascript">var webName="${request.pageContext.contextPath }"; var goodsId="${param['goodsId']}";

  </script>
</head>
<body>
  <div class="site-nav-bg">
    <div class="site-nav w1200">
      <p class="sn-back-home">
        <i class="layui-icon layui-icon-home"></i>
        <a href="#">首页</a>
      </p>
      <div class="sn-quick-menu">
				<div class="login"><a href="${request.pageContext.contextPath }/model/index">登录</a></div>
        <div class="sp-cart"><a href="${request.pageContext.contextPath }/model/shopcart">购物车</a><span></span></div>
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
          </form>
        </div>
      </div>
    </div>
  </div>


  <div class="content content-nav-base datails-content">
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
    <div class="data-cont-wrap w1200">
      <div class="crumb">
        <a href="../html/index.html">首页</a>
        <span>></span>
        <a href="../html/commodity.html">所有商品</a>
        <span>></span>
        <a href="javascript:;">产品详情</a>
      </div>
      <div class="product-intro layui-clear" id="productintro">
				  
      </div>
			<script type="text/html" id="demo1">
				<div class="preview-wrap">
					<a href="javascript:;"><img src="{{d.data.defaultPhotoUrl}}" id="img"></a>
				</div>
				<div class="itemInfo-wrap">
					<div class="itemInfo">
						<div class="title">
							<h4>{{d.data.goodsName}}</h4>
							<span><i class="layui-icon {{d.data.collect==0?'layui-icon-rate':'layui-icon-rate-solid'}}" id="icon">{{d.data.collect==0?'未收藏':'已收藏'}}</i></span>
						</div>
						<div class="summary">
							{{# if (d.data.activitiPrice=== 0) { }}
								<p class="activity"><span>价格</span><strong class="price"><i>￥</i><span id="activity">{{d.data.price}}</span></strong></p>
							{{# } else { }} 
								<p class="reference"><span>参考价</span> <del>￥<span id="reference">{{d.data.price}}</span></del></p>
								<p class="activity"><span>活动价</span><strong class="price"><i>￥</i><span id="activity">{{d.data.activitiPrice}}</span></strong></p>
							{{# } }} 
							<p class="address-box"><span>送&nbsp;&nbsp;&nbsp;&nbsp;至</span><strong class="address">${sessionScope.Address}<input type="hidden" value="${sessionScope.recipientId}"></strong></p>
						</div>
						<div class="choose-attrs">
							{{# layui.each(d.data.attrList,function(index,item){}}  
								<div class="color layui-clear">
									<span class="title">{{item.attrName}}</span> 
										<div class="color-cont">
											{{# layui.each(item.attrValueList,function(index,items){}}
												<span class="btn">{{items.attrValue}}<input type="hidden" value="{{items.skuAttr}}"></span> <input type="hidden" id="skuId">
											{{# }); }}
										</div>
								</div>
							{{# }); }}
							<div class="number layui-clear"><span class="title">数&nbsp;&nbsp;&nbsp;&nbsp;量</span><div class="number-cont"><span class="cut btn">-</span><input maxlength="4" type="" name="number" value="1"><span class="add btn layui-btn-disabled">+</span></div></div>
						</div>
						<div class="choose-btns">
							<button class="layui-btn layui-btn-primary purchase-btn">立刻购买</button>
							<button class="layui-btn  layui-btn-danger car-btn"><i class="layui-icon layui-icon-cart-simple"></i>加入购物车</button>  
						</div>
					</div>
				</div>
			</script>
      <div class="layui-clear" id="layuiclear">
     
      </div>
			<script type="text/html" id="demo2">
				<div class="aside">
					<h4>热销推荐</h4>
					<div class="item-list">
						{{# layui.each(d.data.goodsLikeList,function(index,item){}} 
						<div class="item">
							<a href="javascript:;">
								<input type="hidden"name="goodsId" value="{{d.data.goodsId}}" />
							<img src="{{item.defaultPhotoUrl}}"></a>
							<p><span>{{item.goodsName}}</span><span class="pric">￥{{item.activitiPrice==0?item.price:item.activitiPrice}}</span></p>
						</div>
						{{# }); }}
					</div>
				</div>
				<div class="detail">
					<h4>详情</h4>
					<div class="item" style="color: ;">
						<img src="{{d.data.detailsPhotoUrl}}">
					</div>
				</div>
			</script>
    </div>
  </div>
<script type="text/javascript" src="js/detail.js"></script>
 
</body>
</html>