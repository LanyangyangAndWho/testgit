package com.myhopu.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 方法名与mybatis映射文件中的SQL ID对应
 */
public interface BaseMapper<T> {

	// 查询所有
	List<T> findAll(@Param("currentRecord") Integer currentRecord, @Param("limit") Integer limit, @Param("t") T t)
			throws Exception;

	// 查询行数
	Long findRows(@Param("t") T t) throws Exception;

	// 查询单个
	T selectByPrimaryKey(@Param("t") T t) throws Exception;

	// 添加
	Integer insert(T t) throws Exception;

	// 修改
	Integer updateByPrimaryKey(@Param("t") T t) throws Exception;

	// 删除
	Integer deleteByPrimaryKey(@Param("t") T t) throws Exception;

	// 批量删除
	Integer deleteBatch(Object[] id) throws Exception;

}
