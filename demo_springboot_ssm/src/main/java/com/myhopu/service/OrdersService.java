package com.myhopu.service;

import java.util.List;
import java.util.Map;

import com.myhopu.entity.Goods;
import com.myhopu.entity.OrdersTitleBlock;
import com.myhopu.entity.Orders;

public interface OrdersService extends BaseService<Orders> {
	//查询是否存在当前用户的购物车
	public Integer shopcartCheckAndAdd(Orders orders,OrdersTitleBlock odersTitleBlock);
	//查询商品详细信息
	public Map<String,Object> selectDetailsOrders(Orders orders);
	//
	public Integer insert(OrdersTitleBlock ordersTitleBlock);
	//将购物车的详情商品库存修改到订单（购物车结算）
	public Integer updateShopcartSku(Integer skuId,Integer ordersId,Integer userId) ;
}
