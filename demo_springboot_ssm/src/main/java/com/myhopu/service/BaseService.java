package com.myhopu.service;

import java.util.Map;

public interface BaseService<T> {
	
	// 查询所有
	Map<String, Object> findAll(Integer page, Integer limit, T t) throws Exception;
	
	// 查询行数
	Long findRows(T t) throws Exception;
	
	// 查询单个
	T selectByPrimaryKey(T t) throws Exception;
	
	// 添加
	String insert(T t) throws Exception;

	// 修改
	String updateByPrimaryKey(T t) throws Exception;

	// 删除
	String deleteByPrimaryKey(T t) throws Exception;

	// 批量删除
	String deleteBatch(Long[] ids) throws Exception;

}
