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
  <script type="text/javascript">var webName="${request.pageContext.contextPath }";</script>
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
        <div class="sp-cart"><a href="${request.pageContext.contextPath }/model/shopcart">购物车</a><span>${sessionScope.shopcart==null?'':sessionScope.shopcart }</span></div>
      </div>
    </div>
  </div>

  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="#" title="母婴商城">
            <img src="res/static/img/logo.png">
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
  <div class="content content-nav-base commodity-content">
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
    <div class="commod-cont-wrap">
      <div class="commod-cont w1200 layui-clear">
        <div class="left-nav">
          <div class="title">所有分类</div>
          <div class="list-box" id="list-box">
					
          </div>
        </div>
      	<span id="typeName">全部商品<input type="hidden"></span>&nbsp;<span class="layui-badge"><span id="typeNum"></span>件</span>
        <div class="right-cont-wrap">
          <div class="right-cont">
            <div class="sort layui-clear">
              <a class="active" href="javascript:;" event = 'volume'>销量<input type="hidden" value="volume"></a>
              <a href="javascript:;" event = 'price'>价格<input type="hidden" value="price"></a>
              <a href="javascript:;" event = 'newprod'>新品<input type="hidden" value="newprod"></a>
              <a href="javascript:;" event = 'collection'>收藏<input type="hidden" value="collection"></a>
            </div>
            <div class="prod-number" style="margin-top: 20px" >
            </div>
						
            <div class="cont-list layui-clear" id="list-cont">
              
              
            </div>
            <!-- 模版引擎导入 -->
             <script type="text/html" id="demo">
              {{# layui.each(d.data,function(index,item){}}    
                <div class="item">
                  <div class="img">
                    <a href="javascript:;"><img src="{{item.defaultPhotoUrl}}"><input type="hidden" value="{{item.goodsId==null?0:item.goodsId}}"></a>
                  </div>
                  <div class="text">
                    <p class="title">{{item.goodsName}}</p>
                    <p class="price">
                      <span class="pri">￥{{item.price}}</span>
                      <span class="nub">{{item.supSales}}付款</span>
                    </p>
                  </div>
                </div>
              {{# }); }}
            </script> 
            <div id="demo0" style="text-align: center;"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
<script type="text/javascript" src="js/commodity.js"></script>
</body>
</html>