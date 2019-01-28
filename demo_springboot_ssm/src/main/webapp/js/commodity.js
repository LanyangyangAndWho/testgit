 layui.config({
    base: 'res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','laypage','jquery','form'],function(){
      var laypage = layui.laypage,$ = layui.$,form=layui.form,
			mm = layui.mm;
   
		//初始化下拉列表
		$.ajax({	
			type:'get',	
			url:webName+'goodsType/selectAll',	
			dataType:'json',	
			success:function(data){		
				if(data.code==0){
					var str="";
					$.each(data.data,function(index,xx){
						if(xx.typePId==0){
							var id=xx.typeId;
							str+="<dl><dt>"+xx.typeName+"</dt>";
							$.each(data.data,function(index,xx){
								if(xx.typePId!=0&&xx.typePId==id){
									str+="<dd class='dd'><a href='javascript:;'>"+xx.typeName+"<input type='hidden' value="+xx.typeId+"></a></dd>";
								}
							});
							str+="</dl>";
						}
					});
					$("#list-box").append(str);
				}else{
					console.log(data.msg);
				}
			}
		});
   
		
     
		//搜索框点击事件
		form.on('submit(formDemo)',function(data){
			//var jsonData = JSON.stringify(data);// 转成JSON格式
			//var result = $.parseJSON(jsonData);// 转成JSON对象 
			var str='goodsName='+data.field.title;
			getDemoEngine(str,5,1);
			return false;
		});
		$(function(){
			// 模版引擎导入
			getDemoEngine(null,5,1);
		});
		// 模版引擎导入
		function getDemoEngine(data,limit,page){
			if(data==null){
				data='';
			}else{
				data+='&';
			}
			 var html = demo.innerHTML;
			var listCont = document.getElementById('list-cont');
			console.log(layui.router("#/about.html"))
				mm.request({
				url: webName+'goods/selectAll',
				data:data+'limit='+limit+'&page='+page,
				success : function(res){
					$("#typeNum").html(res.count)
					console.log(res)
					listCont.innerHTML = mm.renderHtml(html,res)
					laypage.render({
				        elem: 'demo0'
				        ,count:res.count//数据总数
				        ,limit:5
				        ,jump: function(obj, first){
				        	if(!first){
				        		var html = demo.innerHTML;
				        		var listCont = document.getElementById('list-cont');
				        		console.log(layui.router("#/about.html"))
				        		mm.request({
				    				url: webName+'goods/selectAll',
				    				data:data+'&limit='+obj.limit+'&page='+obj.curr,
				    				success : function(res){
				    					$("#typeNum").html(res.count)
				    					console.log(res)
				    					listCont.innerHTML = mm.renderHtml(html,res)
				    				},
				    				error: function(res){
				    					console.log(res);
				    				}
				    			}) 
				        	}
				        }
				      });
				},
				error: function(res){
					console.log(res);
				}
			}) 
		    
		}
	//分类标签激活事件
	$(document).on('click','.sort a',function(){
		$(this).addClass('active').siblings().removeClass('active');
		var sort=$(".sort [class='active']").children().val();
		var typeId=$("#typeName").children().val();
		var data="sort="+sort+"&goodsType.typeId="+typeId;
		getDemoEngine(data,5,1);
	})
	//树节点展开收拢事件
	$(document).on('click','.list-box dt',function(){
		if($(this).attr('off')){
			$(this).removeClass('active').siblings('dd').show()
			$(this).attr('off','')
		}else{
			$(this).addClass('active').siblings('dd').hide()
			$(this).attr('off',true)
		}
	})
	//点击分类重新渲染模板
	$(document).on('click','.dd a',function(){
		var sort=$(".sort [class='active']").children().val();
		var typeId=$(this).children().val();
		$("#typeName").html($(this).html());
		$("#typeName").children().val(typeId);
		$("#typeNum").html($("#goodsNum").val());
		var data="sort="+sort+"&goodsType.typeId="+typeId;
		getDemoEngine(data,5,1);
		
	})
	//点击图片进入商品详情页面
	$(document).on('click','.img a',function(){
		var data=$(this).children(':input').val();
		window.location.href=webName+"/model/detail?goodsId="+data;
	})
});