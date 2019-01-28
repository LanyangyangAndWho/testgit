layui.config({
    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','jquery','form'],function(){
      var mm = layui.mm,$ = layui.$,form=layui.form;
			var arr=new Array();
			getDemoEngine(null);
			//模版引擎导入
			function getDemoEngine(data){				
      var html = demo1.innerHTML;
			var html2=demo2.innerHTML;
      var productintro = document.getElementById('productintro');
			var layuiclear = document.getElementById('layuiclear');
      	mm.request({
      	url: webName+'goodsDetails/selectAllDetail',
      	data:"goodsId="+goodsId,
      	success : function(res){
      		console.log(res)
      		productintro.innerHTML = mm.renderHtml(html,res);
					layuiclear.innerHTML=mm.renderHtml(html2,res);
					arr=Object.values(res.data.skuList);
					var arr1=new Array();
					arr1=arr[0].skuAttrs.split(",");
					if(arr1.length==1){
						$(".color-cont [value='"+arr1[0]+"']").parent().css('opacity','0.3');
						$(".color-cont [value='"+arr1[0]+"']").parent().removeClass('active');
					}
					console.log(arr);
      	},
      	error: function(res){
      		console.log(res);
      	}
      }) 
			}
			//点击收藏按钮
			$(document).on('click','#icon',function(){
				if($(this).html()=='未收藏'){
					addOrDelCollect(0);
					$(this).html('已收藏');
					$(this).removeClass('layui-icon-rate').addClass('layui-icon-rate-solid');
					
				}else{
					addOrDelCollect(1);
					$(this).html('未收藏');
					$(this).removeClass('layui-icon-rate-solid').addClass('layui-icon-rate');
					
				}
			});
			//向用户添加/删除收藏
			function addOrDelCollect(result){
				var goodsId= $("[name='goodsId']").val();
				if(result==0){
					$.get(webName+'goodsDetails/addCollectGoods',{"goodsId":goodsId},function(msg){
						if(msg.code==0){
							layer.msg("添加收藏成功！",{icon: 1,time:2000});
						}else{
							layer.msg("添加收藏失败！",{icon: 2,time:2000});
						}
					});
				}else{
					$.get(webName+'goodsDetails/delCollectGoods',{"goodsId":goodsId},function(msg){
						if(msg.code==0){
							layer.msg("取消收藏成功！",{icon: 1,time:2000});
						}else{
							layer.msg("取消收藏失败！",{icon: 2,time:2000});
						}
					});
				}
			}
			var sum=0;
			//规格标签激活事件
			$(document).on('click','.color-cont span',function(){
				if($(this).css('opacity')==1){
					$(this).addClass('active').siblings().removeClass('active');
				}
				var sku=new String($(this).children().val());
				for(var i=0;i<arr.length;i++){
					if(arr[i].skuAttrs.split(",")[0]==sku){
						if(arr[i].photoUrl!=null){
							$("#img").attr('src',arr[i].photoUrl);
						}
						if(arr[i].store==0&&arr[i].skuAttrs.length>3){
								$(".color-cont [value='"+arr[i].skuAttrs.split(",")[1]+"']").parent().css('opacity','0.3');
								$(".color-cont [value='"+arr[i].skuAttrs.split(",")[1]+"']").parent().removeClass('active');
						}else{
								$(".color-cont [value='"+arr[i].skuAttrs.split(",")[1]+"']").parent().css('opacity','1');
						}
						
					}
					
				} 
				
				var str="";
				$(".active :input").each(function(index,xx){
					if(str==""){
						str+=xx.value;
						
					}else{
						str+=','+xx.value;
					}
				
				});
				for(var i=0;i<arr.length;i++){
					if(arr[i].skuAttrs==str){
						sum=arr[i].store;
						$("#skuId").val(arr[i].skuId);
						if(arr[i].activitiPrice==0){
							$("#activity").html(arr[i].price);
						}else{
							$("#reference").html(arr[i].price);
							$("#activity").html(arr[i].activitiPrice);
						}
						
					}
				}
				$(".number-cont :input").val(1);
			});
			//购买数量按钮事件
			
			$(document).on('click','.number-cont .btn',function(){
			var cur = $('.number-cont input').val();
			if($(this).hasClass('add')){
				if(cur<sum){
					cur++;
				}
			}else{
				if(cur > 1){
					cur--;
				}  
			}
			$('.number-cont input').val(cur)
			});
			
		 	$(document).on('onkeyup','.number-cont .btn',function(){
				if(this.value.length==1){
					this.value=this.value.replace(/[^1-9]/g,'')
				}else{
					this.value=this.value.replace(/\D/g,'')
				}
			});
			
			$(document).on('onafterpaste','.number-cont .btn',function(){
				if(this.value.length==1){
					this.value=this.value.replace(/[^1-9]/g,'')
				}else{
					this.value=this.value.replace(/\D/g,'')
				}
			}); 
			//点击图片进入商品详情页面
			$(document).on('click','.item a',function(){
				var data='goodsId='+$(this).children(':input').val();
				window.location.href='../html/details.html?'+data;
			})
			
			//添加购物车
			$('.product-intro').on('click','.car-btn',function(){
				if($(".active :input").length==arr[0].skuAttrs.split(",").length){
					var recipientId=$(".address").children().val();
					var skuId=$("#skuId").val();
					var number=$("[name='number']").val();
					if(skuId==""||skuId==null){
						layer.msg('未知错误，请刷新页面！',{icon:3,time:2000});
						return;
					}
					if(recipientId==""||recipientId==null){
						layer.msg('请填写收货信息！',{icon:3,time:2000});
						return;
					}
					$.post(webName+"orders/addShopcart",{"recipientId":recipientId,"skuId":skuId,"number":number},function(msg){
						if(msg==0){
							layer.msg('添加购物车成功！',{icon:1,time:2000});
						}else{
							layer.msg('添加购物车失败！',{icon:2,time:2000});
						}
					});
				}else{
					layer.msg('请选择商品规格！',{icon:2,time:2000});
				}
			});
			//购买商品
			$('.product-intro').on('click','.purchase-btn',function(){
				if($(".active :input").length==arr[0].skuAttrs.split(",").length){
				var recipientId=$(".address").children().val();
				var skuId=$("#skuId").val();
				var number=$("[name='number']").val();
				if(skuId==""||skuId==null){
					layer.msg('未知错误，请刷新页面！',{icon:3,time:2000});
					return;
				}
				if(recipientId==""||recipientId==null){
					layer.msg('请填写收货信息！',{icon:3,time:2000});
					return;
				}
				$.post(webName+'orders/addOrders',{"recipientId":recipientId,"skuId":skuId,"number":number},function(data){
					window.location.href=webName+"/model/orders?ordersId="+data.ordersId;
				});
				}else{
					layer.msg('请选择商品规格！',{icon:2,time:2000});
				}
			});
  });