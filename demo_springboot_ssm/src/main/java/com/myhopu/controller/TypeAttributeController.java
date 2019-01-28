package com.myhopu.controller;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.myhopu.entity.GoodsTypeAttr;
import com.myhopu.entity.TypeAttribute;
import com.myhopu.service.impl.TypeAttributeServiceImpl;




@Controller
@RequestMapping("/typeattribute")
public class TypeAttributeController extends BaseController<TypeAttribute> {
	@Resource
	TypeAttributeServiceImpl typeAttributeServiceimpl;
	@Override
	@RequestMapping("/selectAll")
	@ResponseBody
	public Map<String, Object> selectAll(Integer page, Integer limit, TypeAttribute t) {
		try {
			map = typeAttributeServiceimpl.findAll(page, limit, t);
			map.put("code", SUCCESSCODE);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return putMsgToJsonString(FAILCODE, FAILMSG, FAILCOUNT, null);
		}
	}
	
	@RequestMapping("/selecttypeid")
	@ResponseBody
	public Map<String, Object> selecttypeid() {
		
		map = typeAttributeServiceimpl.selecttypeid();
		map.put("code", SUCCESSCODE);
		return map;
	}
	
	@RequestMapping("/selectattr")
	@ResponseBody
	public Map<String, Object> selectattr() {
		
		map = typeAttributeServiceimpl.selectattr();
		map.put("code", SUCCESSCODE);
		return map;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(GoodsTypeAttr entity) {
		try {
			String string = entity.getAttrNameIds();
			if(string=="") {
				return ERROR;
			}
			String[] parts = string.split(" ");
			for(int i=0;i<parts.length;i++) {
				if(parts[i].trim().isEmpty()) {
					continue;
				}
				int j=Integer.parseInt(parts[i]);
				entity.setAttrNameId(j);
				typeAttributeServiceimpl.insert(entity);
			}
			return "saveSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
