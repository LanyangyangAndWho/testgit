package com.myhopu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhopu.entity.GoodsType;
import com.myhopu.service.GoodsTypeService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController extends BaseController<GoodsType> {
	@Autowired
	GoodsTypeService goodsTypeService;
	@RequestMapping("/selectpid")
	@ResponseBody
	public Map<String, Object> selectpid() {
		
		map = goodsTypeService.selectpid();
		map.put("code", SUCCESSCODE);
		return map;
	}
}
