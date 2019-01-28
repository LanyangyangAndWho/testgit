package com.myhopu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhopu.service.BaseService;

/**
 * 
 * 基础控制器层
 * 
 * @param <T>
 */
public class BaseController<T> {

	protected Map<String, Object> map = new HashMap<String, Object>();

	protected List<T> list = new ArrayList<T>();

	// 访问数据成功的常量
	protected static final Integer SUCCESSCODE = 0;
	// 访问数据失败的常量
	protected static final Integer FAILCODE = 200;
	// 访问数据失败的数据条数
	protected static final Integer FAILCOUNT = 0;
	// 访问数据失败时的提示
	protected static final String FAILMSG = "数据访问失败";
	// 操作成功的常量
	protected static final String SUCCESS = "success";
	// 操作失败的常量
	protected static final String FAIL = "fail";
	// 操作异常的常量
	protected static final String ERROR = "error";

	// 基础业务层对象
	@Autowired
	protected BaseService<T> baseService;

	/**
	 * @param code  1 成功 0 失败
	 * @param msg   消息内容
	 * @param count 最大条数
	 * @param data  具体内容
	 * @return
	 */
	public Map<String, Object> putMsgToJsonString(int code, String msg, int count, Object data) {
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);
		return map;
	}

	/**
	 * 查询所有
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public Map<String, Object> selectAll(Integer page,Integer limit, T t) {
		try {
			map = baseService.findAll(page, limit, t);
			map.put("code", SUCCESSCODE);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return putMsgToJsonString(FAILCODE, FAILMSG, FAILCOUNT, null);
		}
	}

	/**
	 * 查询单个
	 */
	@RequestMapping("/selectOne")
	@ResponseBody
	public T selectOne(T t) {
		try {
			return baseService.selectByPrimaryKey(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加
	 */
	@RequestMapping("/saveT")
	@ResponseBody
	public String saveT(T entity) {
		try {
			return baseService.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping("/updT")
	@ResponseBody
	public String updT(T entity) {
		try {
			return baseService.updateByPrimaryKey(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(T t) {
		try {
			return baseService.deleteByPrimaryKey(t);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 批量删除
	 */
	@RequestMapping("/deletes")
	@ResponseBody
	public String deletes(Long[] ids) {
		try {
			return baseService.deleteBatch(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

}
