package com.myhopu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhopu.entity.Goods;
import com.myhopu.entity.RecipientInfo;
import com.myhopu.entity.User;
import com.myhopu.service.GoodsDetailsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/goodsDetails")
public class GoodsDetailsController{
	protected Map<String, Object> map = null;

	@Autowired
	private GoodsDetailsService goodsDetailsService;
	//得到一个商品的详细信息表
	@RequestMapping("selectAllDetail")
	@ResponseBody
	public Map<String,Object> selectAllDetail(Integer goodsId,HttpServletRequest request) {
		try {
			map=new HashMap<String, Object>();
			HttpSession session=request.getSession();
			User user=new User();
			user.setUserId(1002);
			user.setUserName("zhangsan");
			user.setPhoneNumber("18579108671");	
			session.setAttribute("address", "江西省南昌市进贤县");
			session.setAttribute("recipientId","1");
			map=goodsDetailsService.selectAllDetail(goodsId,user.getUserId());
			map.put("code",0);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	//添加收藏
	@RequestMapping("addCollectGoods")
	@ResponseBody
	public Map<String,Object> addCollectGoods(Integer goodsId,HttpSession session) {
		try {
			map=new HashMap<String, Object>();
			User user=new User();
			user.setUserId(1002);
			user.setUserName("zhangsan");
			user.setPhoneNumber("18579108671");	
			goodsDetailsService.addCollectGoods(user.getUserId(), goodsId);
			map.put("code", 0);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("code", 1);
			return map;
		}
		
	}
	//取消收藏
	@RequestMapping("delCollectGoods")
	@ResponseBody
	public  Map<String,Object> delCollectGoods(Integer goodsId,HttpSession session) {
		try {
			map=new HashMap<String, Object>();
			User user=new User();
			user.setUserId(1002);
			user.setUserName("zhangsan");
			user.setPhoneNumber("18579108671");	
			goodsDetailsService.delCollectGoods(user.getUserId(), goodsId);
			map.put("code",0);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("code", 1);
			return map;
		}
		
	}
}
