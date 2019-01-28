package com.myhopu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("/model")
public class ModelController {
	
	/**
	 * 通用跳转
	 */
	@RequestMapping(value="/{url}")
	public String findDepatment(@PathVariable String url){
	  return url;
	}
	

}
