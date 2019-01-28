package com.myhopu.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myhopu.entity.AttrName;
import com.myhopu.entity.AttrValue;
import com.myhopu.entity.Sku;





@Repository
public interface SkuMapper extends BaseMapper<Sku> {

	List<AttrName> findattrname(Integer goodsId);

	List<AttrValue> findattrvalue(Integer attrNameId, Integer skuId);

	List maxid();

	void addvalue(AttrValue attrValue);


	void deletefk(Integer skuId);

	void deleteBatchfk(Long[] ids);




}
