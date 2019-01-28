package com.myhopu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.myhopu.entity.Goods;
import com.myhopu.mapper.BaseMapper;
import com.myhopu.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	protected Map<String, Object> map = new HashMap<String, Object>();

	protected List<T> list = new ArrayList<T>();

	// 操作成功的常量
	protected static final String SUCCESS = "success";
	// 删除操作成功的常量
	protected static final String DELSUCCESS = "delSuccess";
	// 修改操作成功的常量
	protected static final String UPDSUCCESS = "updSuccess";
	// 添加操作成功的常量
	protected static final String SAVESUCCESS = "saveSuccess";
	// 操作成功的常量
	protected static final String FAIL = "fail";

	@Autowired
	protected BaseMapper<T> baseMapper;

	// 查询所有
	@Override
	public Map<String, Object> findAll(Integer page, Integer limit, T t) throws Exception {
		map.put("count", baseMapper.findRows(t));
		List<T> list = null;
		if (page != null) {
			list = baseMapper.findAll((page - 1) * limit, limit, t);
		} else {
			list = baseMapper.findAll(null, null, t);
		}
		map.put("data", list);
		return map;
	}

	// 查询行数
	@Override
	public Long findRows(T t) throws Exception {
		return baseMapper.findRows(t);
	}

	// 查询一个
	@Override
	public T selectByPrimaryKey(T t) throws Exception {
		return baseMapper.selectByPrimaryKey(t);
	}

	// 添加
	@Override
	public String insert(T t) throws Exception {
		if (baseMapper.insert(t) > 0) {
			return SAVESUCCESS;
		} else {
			return FAIL;
		}
	}

	// 修改
	@Override
	public String updateByPrimaryKey(T t) throws Exception {
		if (baseMapper.updateByPrimaryKey(t) > 0) {
			return UPDSUCCESS;
		} else {
			return FAIL;
		}
	}

	// 删除
	@Override
	public String deleteByPrimaryKey(T t) throws Exception {
		if (baseMapper.deleteByPrimaryKey(t) > 0) {
			return DELSUCCESS;
		} else {
			return FAIL;
		}
	}

	// 批量删除
	@Override
	public String deleteBatch(Long[] ids) throws Exception {
		if (baseMapper.deleteBatch(ids) > 0) {
			return DELSUCCESS;
		} else {
			return FAIL;
		}
	}

}
