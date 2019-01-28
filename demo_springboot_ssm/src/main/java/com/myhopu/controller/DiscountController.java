package com.myhopu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhopu.entity.Discount;
import com.myhopu.service.DiscountService;


@Controller
@RequestMapping("/discount")
public class DiscountController extends BaseController<Discount> {
	@Resource
	private DiscountService discountService;
	List<Discount> list=new ArrayList<Discount>();
	@RequestMapping("/buytoday")
	@ResponseBody
	public Map<String,Object> buytoday(@PathParam("now") String now,Integer page, Integer limit) throws ParseException {
		map.put("code",SUCCESSCODE);
		map=discountService.buytoday(now,page,limit);
		list=(List<Discount>)discountService.buyDesc();
		map.put("buyDesc", list);
		return map;
	}
	
}
