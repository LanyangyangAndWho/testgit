  layui.config({
    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','jquery','element','car'],function(){
    var mm = layui.mm,$ = layui.$,element = layui.element,car = layui.car;
    
    // 模版导入数据
     var html = demo.innerHTML,
     listCont = document.getElementById('list-cont');
     mm.request({
       url: webName+'/orders/selectShopcart',
       success : function(res){
         listCont.innerHTML = mm.renderHtml(html,res)
         element.render();
         car.init()
       },
       error: function(res){
         console.log(res);
       }
     })
		//关闭窗口时触发
		$(window).unload(function(){
			var orderDetailsId=$("#orderDetailsId").val();
			var number=$("[name='number']").val();
			
		});
     //结算按钮
     $(".layui-btn").click(function(){
    	 var arr=new Array();
    	 $.each($('.item-content input:checkbox:checked'),function(index,xx){
    		 arr.push($(this).next().val());
         });
    	 $.ajax({
    		 url:webName+"/orders/shopcartPay",
    		 data:{arr:arr},
    		 type:'post',
    		 traditional:true,
    		 success:function(msg){
    			 alert(msg.data.ordersId)
    			 window.location.href=webName+"/model/orders?ordersId="+msg.data.ordersId;
    		 }
    	 });
     })
});