package com.myhopu.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myhopu.entity.GoodsType;

@Repository
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
	List selectpid();
}
