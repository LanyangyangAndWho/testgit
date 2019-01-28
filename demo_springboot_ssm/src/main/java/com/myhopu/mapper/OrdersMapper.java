package com.myhopu.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.myhopu.entity.AttrValue;
import com.myhopu.entity.Orders;

@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
	//查询商品详细信息
	public Orders selectDetailsOrders(Orders orders);
	//查询商品属性值
	public AttrValue selectOneAttrValue(Integer attrId);
	//将购物车的详情商品库存修改到订单（购物车结算）
	public Integer updateShopcartSku(Integer skuId,Integer ordersId,Integer userId) ;
}
