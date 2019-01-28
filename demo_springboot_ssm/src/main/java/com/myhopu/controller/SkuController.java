package com.myhopu.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.myhopu.entity.Sku;
import com.myhopu.service.impl.SkuServiceImpl;





@Controller
@RequestMapping("/sku")
public class SkuController extends BaseController<Sku> {
	@Resource
	SkuServiceImpl skuServiceimpl;
	@Override
	@RequestMapping("/selectAll")
	@ResponseBody
	public Map<String, Object> selectAll(Integer page, Integer limit, Sku t) {
		try {
			map = skuServiceimpl.findAll(page, limit, t);
			map.put("code", SUCCESSCODE);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return putMsgToJsonString(FAILCODE, FAILMSG, FAILCOUNT, null);
		}
	}
	@RequestMapping("/selectattname")
	@ResponseBody
	public Map<String, Object> selectattname(Sku m) {
			
		map = skuServiceimpl.selectattname(m.getGoods().getGoodsId());
		map.put("code", SUCCESSCODE);
		return map;
	}
	@RequestMapping("/save")
	@ResponseBody
	public String save(Sku entity,HttpServletRequest request,@RequestParam("file1") MultipartFile file1)throws IllegalStateException, IOException {
		try {
	            MultipartFile file = file1;
	            
	            // 保存文件
	            if (!file.isEmpty()) {
	                try {
	                	String path = request.getServletContext().getRealPath("/upload");
	                	String filename = file.getOriginalFilename();
	                	String filePath =path + filename;
	                    File saveDir = new File(filePath);
	                    if (!saveDir.getParentFile().exists())
	                        saveDir.getParentFile().mkdirs();
	                    long filename1=System.currentTimeMillis();
	                    filename = path + File.separator +filename1 + filename.substring(filename.lastIndexOf("."));
	                    file.transferTo(new File(filename));
	                    System.out.println(filename);
	                    entity.setPhotoUrl("/upload/"+filename1+ filename.substring(filename.lastIndexOf(".")));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            
	  
			return skuServiceimpl.add(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	

}
