<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/public/taglib.jsp"%>
<%@ include file="/WEB-INF/page/public/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>母婴商城</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
    <script type="text/javascript"> var webName="${pageContext.request.contextPath}";</script>
</head>
<body>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>母婴商城</title>
</head>

<body class="layui-layout-body hp-white-theme">
  <div class="site-nav-bg">
    <div class="site-nav w1200">
      <p class="sn-back-home">
        <i class="layui-icon layui-icon-home"></i>
        <a href="#">首页</a>
      </p>
      <div class="sn-quick-menu">
        <div class="login"> <a href="${pageContext.request.contextPath}/model/register">立即注册</a></div>
        <div class="sp-cart"><a href="shopcart.html">购物车</a><span></span></div>
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


  <div class="content content-nav-base  login-content">
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
    <div class="login-bg">
      <div class="login-cont w1200">
        <div class="form-box">
           <form class="layui-form" action="">
            <legend>登录</legend>
            <div class="layui-form-item">
              <div class="layui-inline veri-code">
                <div class="layui-input-inline">
                  <input id="phoneNumber" type="text" name="phoneNumber" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-inline veri-code">
                <div class="layui-input-inline">
                  <input id="userPwd" type="password" name="userPwd" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
              </div>
            </div>
            <div class="layui-form-item login-btn">
              <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">登录</button>
               
              </div>
            </div>
             <div align="right"><a href="${pageContext.request.contextPath}/model/index">手机号登录</a></div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="footer">
    <div class="ng-promise-box">
      <div class="ng-promise w1200">
        <p class="text">
          <a class="icon1" href="javascript:;">7天无理由退换货</a>
          <a class="icon2" href="javascript:;">满99元全场免邮</a>
          <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
        </p>
      </div>
    </div>
    <div class="mod_help w1200">                                     
      <p>
        <a href="javascript:;">关于我们</a>
        <span>|</span>
        <a href="javascript:;">帮助中心</a>
        <span>|</span>
        <a href="javascript:;">售后服务</a>
        <span>|</span>
        <a href="javascript:;">母婴资讯</a>
        <span>|</span>
        <a href="javascript:;">关于货源</a>
      </p>
      <p class="coty">母婴商城版权所有 &copy; 2012-2020</p>
    </div>
  </div>
</body>
<script type="text/javascript">
 layui.config({
      base: '${pageContext.request.contextPath}/js/util' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['jquery','form'],function(){ 
    	 var $ = layui.$,form = layui.form;
    	 form.verify({
   		  pass: [
   		    /^[\S]{6,12}$/
   		    ,'密码必须6到12位，且不能出现空格'
   		  ] 
    	 })
    	form.on('submit(demo1)',function(data){
       	  $.post(webName+"/user/loginbypass",data.field,function(msg){
       		 if(msg=='fail'){
       			 layer.msg('账号或密码错误',{icon:2,time:2000});
       		 }
       		 if(msg=='success'){
       			 window.location.href=webName+"/model/commodity";
       		 } 
       		 if(msg=='admin'){
       			window.location.href=webName+"/model/main";
       		 }
       	 }); 
       	 return false;
        });
    });
    	</script>   	
    
</html>