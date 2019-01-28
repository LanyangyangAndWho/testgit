package com.myhopu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhopu.entity.AttrName;
import com.myhopu.entity.AttrValue;
import com.myhopu.entity.Sku;
import com.myhopu.mapper.SkuMapper;
import com.myhopu.service.SkuService;


@Service
@Transactional
public class SkuServiceImpl extends BaseServiceImpl<Sku> implements SkuService {
	@Autowired
	SkuMapper skuMapper;
	@Override
	public Map<String, Object> findAll(Integer page, Integer limit, Sku t) throws Exception {
		map.put("count", skuMapper.findRows(t));		
		List<Sku> list = null;
		List<Sku> list1 = new ArrayList<Sku>();
		if (page != null) {
			list = skuMapper.findAll((page - 1) * limit, limit, t);
			for(int i=0;i<list.size();i++){
				Sku sku=new Sku();
				Sku sku1=list.get(i);
				Integer skuid = sku1.getSkuId();
				Integer goodsid=sku1.getGoods().getGoodsId();
				List<AttrName> list2=skuMapper.findattrname(goodsid);
				String skuattrs="";
				for(int j=0;j<list2.size();j++){
					AttrName attrnames=list2.get(j);
					Integer attrnameid=attrnames.getAttrNameId();
					String attrname=attrnames.getAttrName();
					List<AttrValue> list3=skuMapper.findattrvalue(attrnameid,skuid);
					String attrvalue="";
					for(int k=0;k<list3.size();k++){
						AttrValue attrvalues=list3.get(k);
						attrvalue=attrvalues.getAttrValue();
						attrname=attrname+":"+attrvalue;
					}
					skuattrs=skuattrs+attrname+" ";
				}
				sku.setSkuId(sku1.getSkuId());
				sku.setGoods(sku1.getGoods());
				sku.setPrice(sku1.getPrice());
				sku.setPhotoUrl(sku1.getPhotoUrl());
				sku.setSkuAttrs(skuattrs);
				sku.setSkuSales(sku1.getSkuSales());
				sku.setStore(sku1.getStore());
				list1.add(sku);
			}			
				
		} else {
			list1 = skuMapper.findAll(null, null, t);
		}
		map.put("data", list1);
		return map;
	}

	


	public Map<String, Object> selectattname(Integer integer) {
		// TODO Auto-generated method stub
		List<AttrName> list = skuMapper.findattrname(integer);		
		map.put("data", list);
		return map;
	}

	public String add(Sku entity) {
		// TODO Auto-generated method stub
		try {
			baseMapper.insert(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> list=skuMapper.maxid();
		Integer skuid=list.get(0);
		String temp=entity.getTemp();
		if("".equals(temp.replaceAll(" ", ""))){
			return SAVESUCCESS;
		}
		String[] a=temp.split(" ");		
		for(int i=0;i<a.length;i++) {
			AttrValue macattrValue=new AttrValue();
			String[] b=a[i].split(":");
			macattrValue.setAttrNameId(Integer.valueOf(b[0]));
			macattrValue.setAttrValue(b[1]);
			macattrValue.setSkuId(skuid);
			skuMapper.addvalue(macattrValue);
		}
		return SAVESUCCESS;
	}
	@Override
	public String deleteByPrimaryKey(Sku t) throws Exception {
		skuMapper.deletefk(t.getSkuId());
		if (baseMapper.deleteByPrimaryKey(t) > 0) {
			return DELSUCCESS;
		} else {
			return FAIL;
		}
	}

	// 批量删除
	@Override
	public String deleteBatch(Long[] ids) throws Exception {
		skuMapper.deleteBatchfk(ids);
		if (baseMapper.deleteBatch(ids) > 0) {
			return DELSUCCESS;
		} else {
			return FAIL;
		}
	}

	
	
}
