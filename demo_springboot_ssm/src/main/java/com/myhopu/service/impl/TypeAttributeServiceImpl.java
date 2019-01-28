package com.myhopu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myhopu.entity.GoodsTypeAttr;
import com.myhopu.entity.TypeAttribute;
import com.myhopu.mapper.TypeAttributeMapper;
import com.myhopu.service.TypeAttributeService;


@Service
@Transactional
public class TypeAttributeServiceImpl extends BaseServiceImpl<TypeAttribute> implements TypeAttributeService {
	@Autowired
	TypeAttributeMapper typeAttributeMapper;
	@Override
	public Map<String, Object> findAll(Integer page, Integer limit, TypeAttribute t) throws Exception {
		map.put("count", typeAttributeMapper.findRows(t));		
		List<TypeAttribute> list1 = null;
		List<TypeAttribute> list = new ArrayList<TypeAttribute>();
		if (page != null) {
			list1 = typeAttributeMapper.findAll((page - 1) * limit, limit, t);
			for(int i=0;i<list1.size();i++){				
				TypeAttribute typeattribute1=new TypeAttribute();
				TypeAttribute typeattribute = list1.get(i);
		        Integer id = typeattribute.getTypeId();
		        String typename=typeattribute.getTypeName();
		        List<TypeAttribute> list2 = null;
		        list2=typeAttributeMapper.findattrnames(id);
		        String temp="";
		        for(int j=0;j<list2.size();j++){
		        	TypeAttribute typeattribute2 = list2.get(j);
		            temp=temp+" "+typeattribute2.getAttrName();
		        }
		        typeattribute1.setTypeId(id);
		        typeattribute1.setTypeName(typename);;
		        typeattribute1.setAttrName(temp);
		        list.add(typeattribute1);
		    }
		} else {
			list = typeAttributeMapper.findAll(null, null, t);
		}
		map.put("data", list);
		return map;
	}

	// 查询行数
	@Override
	public Long findRows(TypeAttribute t) throws Exception {
		return baseMapper.findRows(t);
	}

	public Map<String, Object> selecttypeid() {
		// TODO Auto-generated method stub
		List list = typeAttributeMapper.selecttypeid();		
		map.put("data", list);
		return map;
	}

	public Map<String, Object> selectattr() {
		// TODO Auto-generated method stub
		List list = typeAttributeMapper.selectattr();		
		map.put("data", list);
		return map;
	}

	public String insert(GoodsTypeAttr entity) {
		if (typeAttributeMapper.insert(entity) > 0) {
			return SAVESUCCESS;
		} else {
			return FAIL;
		}
	}
	
}
