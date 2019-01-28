package com.myhopu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myhopu.entity.GoodsTypeAttr;
import com.myhopu.entity.TypeAttribute;



@Repository
public interface TypeAttributeMapper extends BaseMapper<TypeAttribute> {

	List<TypeAttribute> findattrnames(Integer id);

	List selecttypeid();

	List selectattr();

	int insert(GoodsTypeAttr t);


}
