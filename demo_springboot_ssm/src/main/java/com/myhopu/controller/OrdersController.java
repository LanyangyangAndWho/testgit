package com.myhopu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.myhopu.entity.Goods;
import com.myhopu.entity.OrdersTitleBlock;
import com.myhopu.entity.RecipientInfo;
import com.myhopu.entity.Orders;
import com.myhopu.entity.Sku;
import com.myhopu.entity.User;
import com.myhopu.service.OrdersService;
import com.myhopu.service.OrdersTitleBlockService;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController<Orders> {
	@Autowired
	private OrdersService ordersService;
	/**
	 * 添加购物车
	 * @param recipientId
	 * @param skuId
	 * @param number
	 * @param session
	 * @return
	 */
	@RequestMapping("/addShopcart")
	@ResponseBody
	public Map<String,Object> addShopcart(Integer recipientId,Integer skuId,Integer number,HttpSession session){
		User user=new User();
		user.setUserId(1002);
		user.setUserName("zhangsan");
		user.setPhoneNumber("18579108671");	
		session.setAttribute("address", "江西省南昌市进贤县");
		session.setAttribute("recipientId","1");
		Orders o=new Orders();
		o.setUser(user);
		o.setState(0);
		OrdersTitleBlock odersTitleBlock=new OrdersTitleBlock();
		odersTitleBlock.setNumber(number);
		Sku sku=new Sku();
		sku.setSkuId(skuId);
		odersTitleBlock.setSku(sku);
		
		ordersService.shopcartCheckAndAdd( o, odersTitleBlock);
		
		return null;
	}
	/**
	 * 添加订单
	 * @param recipientId
	 * @param skuId
	 * @param number
	 * @param session
	 * @return
	 */
	@RequestMapping("/addOrders")
	@ResponseBody
	public Map<String,Object> addOrders(Integer recipientId,Integer skuId,Integer number,HttpSession session){
		User user=new User();
		user.setUserId(1002);
		user.setUserName("zhangsan");
		user.setPhoneNumber("18579108671");	
		session.setAttribute("address", "江西省南昌市进贤县");
		session.setAttribute("recipientId","1");
		Orders o=new Orders();
		o.setUser(user);
		o.setState(1);
		o.setSubmitTime(new Date());
		OrdersTitleBlock odersTitleBlock=new OrdersTitleBlock();
		odersTitleBlock.setNumber(number);
		Sku sku=new Sku();
		sku.setSkuId(skuId);
		odersTitleBlock.setSku(sku);
		try {
			Orders order1=ordersService.selectByPrimaryKey(o);
			if(order1!=null&&order1.getOrdersId()!=null) {
				order1.setState(-1);
				ordersService.updateByPrimaryKey(order1);
			}
			if("saveSuccess".equals(ordersService.insert(o).toString())) {
				Orders order2=ordersService.selectByPrimaryKey(o);
				odersTitleBlock.setOrders(order2);
				if(ordersService.insert(odersTitleBlock)==1){
					Orders orders=ordersService.selectByPrimaryKey(o);
					Integer ordersId=orders.getOrdersId();
					map.put("ordersId", ordersId);
					return map;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 购物车结算
	 * @param arr
	 * @param session
	 * @return
	 */
	@RequestMapping("/shopcartPay")
	@ResponseBody
	public Map<String,Object> shopcartPay(String[] arr,HttpSession session){
		String []str=arr;
		
		User user=new User();
		user.setUserId(1002);
		user.setUserName("zhangsan");
		user.setPhoneNumber("18579108671");	
		session.setAttribute("address", "江西省南昌市进贤县");
		session.setAttribute("recipientId","1");
		Orders o=new Orders();
		o.setUser(user);
		o.setState(1);
		o.setSubmitTime(new Date());
		try {
			Orders order1=ordersService.selectByPrimaryKey(o);
			if(order1!=null&&order1.getOrdersId()!=null) {
				order1.setState(-1);
				ordersService.updateByPrimaryKey(order1);
			}
			if("saveSuccess".equals(ordersService.insert(o).toString())) {
				Orders order2=ordersService.selectByPrimaryKey(o);
				for(String s:str) {
					ordersService.updateShopcartSku(new Integer(s), order2.getOrdersId(), user.getUserId());
				}
				map.put("ordersId", order2.getOrdersId());
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查看订单
	 * @param recipientId
	 * @param skuId
	 * @param number
	 * @param session
	 * @return
	 */
	@RequestMapping("/selectOrders")
	@ResponseBody
	public Map<String,Object> selectOrders(Integer ordersId,HttpSession session){
		User user=new User();
		user.setUserId(1002);
		user.setUserName("zhangsan");
		user.setPhoneNumber("18579108671");
		RecipientInfo recipientInfo=new RecipientInfo();
		recipientInfo.setPhone("18679178671");
		recipientInfo.setRecipientAddress("江西省南昌市进贤县");
		recipientInfo.setRecipientOfficer("张碧风");
		recipientInfo.setRecipientId(1);
		Orders o=new Orders();
		o.setOrdersId(ordersId);
		map=ordersService.selectDetailsOrders(o);
		map.put("code", SUCCESSCODE);
		map.put("recipientInfo", recipientInfo);
		return map;
	}
	
	/**
	 * 查看购物车
	 * @param session
	 * @return
	 */
	@RequestMapping("/selectShopcart")
	@ResponseBody
	public Map<String,Object> selectShopcart(HttpSession session){
		User user=new User();
		user.setUserId(1002);
		user.setUserName("zhangsan");
		user.setPhoneNumber("18579108671");
		RecipientInfo recipientInfo=new RecipientInfo();
		recipientInfo.setPhone("18679178671");
		recipientInfo.setRecipientAddress("江西省南昌市进贤县");
		recipientInfo.setRecipientOfficer("张碧风");
		recipientInfo.setRecipientId(1);
		Orders o=new Orders();
		o.setUser(user);
		o.setState(0);
		try {
			Orders order=baseService.selectByPrimaryKey(o);
			if(order!=null&&order.getOrdersId()!=null) {
				o.setOrdersId(order.getOrdersId());
				map=ordersService.selectDetailsOrders(o);
				map.put("code", SUCCESSCODE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
}
