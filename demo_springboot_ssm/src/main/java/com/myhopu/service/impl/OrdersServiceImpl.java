package com.myhopu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhopu.entity.AttrValue;
import com.myhopu.entity.Goods;
import com.myhopu.entity.OrdersTitleBlock;
import com.myhopu.entity.Orders;
import com.myhopu.entity.User;
import com.myhopu.mapper.GoodsMapper;
import com.myhopu.mapper.OrdersMapper;
import com.myhopu.mapper.OrdersTitleBlockMapper;
import com.myhopu.service.BaseService;
import com.myhopu.service.GoodsService;
import com.myhopu.service.OrdersService;
import com.myhopu.service.OrdersTitleBlockService;

@Service
@Transactional
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {
	@Autowired
	private OrdersTitleBlockMapper ordersTitleMapper;
	@Autowired
	private OrdersMapper ordersDetailsMapper;
	@Override
	public Integer shopcartCheckAndAdd(Orders orders,OrdersTitleBlock odersTitleBlock) {
		try {
			if( baseMapper.findRows(orders)==1) {
				Orders order= baseMapper.selectByPrimaryKey(orders);
				odersTitleBlock.setOrders(order);
				if(ordersTitleMapper.findRows(odersTitleBlock)==1) {
					OrdersTitleBlock orderTitle=ordersTitleMapper.selectByPrimaryKey(odersTitleBlock);
					odersTitleBlock.setNumber(orderTitle.getNumber()+odersTitleBlock.getNumber());
					ordersTitleMapper.updateByPrimaryKey(odersTitleBlock);
					return 1;
				}else {
					ordersTitleMapper.insert(odersTitleBlock);
					return 1;
				}
			}else {
				if(baseMapper.insert(orders)==1) {
					Orders order= baseMapper.selectByPrimaryKey(orders);
					odersTitleBlock.setOrders(order);
					ordersTitleMapper.insert(odersTitleBlock);
					return 1;
				}
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	@Override
	public Map<String, Object> selectDetailsOrders(Orders orders) {
		// TODO Auto-generated method stub
		Orders order=ordersDetailsMapper.selectDetailsOrders(orders);
		for(int i=0;i<order.getOdersTitleBlockList().size();i++) {
			List<AttrValue> list=new ArrayList<AttrValue>();;
			AttrValue attrValue=null;
			for(int a=0;a<order.getOdersTitleBlockList().get(i).getSku().getSkuAttrs().split(",").length;a++) {
				Integer attrId=new Integer(order.getOdersTitleBlockList().get(i).getSku().getSkuAttrs().split(",")[a].split(":")[1]);
				attrValue=new AttrValue();
				attrValue=ordersDetailsMapper.selectOneAttrValue(attrId);

				list.add(attrValue);
			}
			order.getOdersTitleBlockList().get(i).setAttrValueList(list);
		}
		map.put("data",order);
		return map;
	}
	@Override
	public Integer insert(OrdersTitleBlock ordersTitleBlock) {
		// TODO Auto-generated method stub
		try {
			return ordersTitleMapper.insert(ordersTitleBlock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//将购物车的详情商品库存修改到订单（购物车结算）
	@Override
	public Integer updateShopcartSku(Integer skuId, Integer ordersId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
