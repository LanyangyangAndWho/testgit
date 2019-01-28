package com.myhopu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myhopu.entity.Goods;
import com.myhopu.service.GoodsService;
import com.myhopu.service.impl.GoodsServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods> {
	@Resource
	GoodsService goodsService;
	
	@RequestMapping("/selecttypeid")
	@ResponseBody
	public Map<String, Object> selectTypeId() {
		
		map = goodsService.selecttypeid();
		map.put("code", SUCCESSCODE);
		return map;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(Goods entity,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,HttpServletRequest request)throws IllegalStateException, IOException {
		try {
	            MultipartFile file = file1;
	            MultipartFile file3 = file2;
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
	                    entity.setDefaultPhotoUrl("/upload/"+filename1+ filename.substring(filename.lastIndexOf(".")));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (!file3.isEmpty()) {
	                try {
	                	String path = request.getServletContext().getRealPath("/upload");
	                	String filename = file3.getOriginalFilename();
	                	String filePath =path + filename;
	                    File saveDir = new File(filePath);
	                    if (!saveDir.getParentFile().exists())
	                        saveDir.getParentFile().mkdirs();
	                    long filename1=System.currentTimeMillis();
	                    filename = path + File.separator +filename1 + filename.substring(filename.lastIndexOf("."));
	                    file3.transferTo(new File(filename));
	                    System.out.println(filename);
	                    entity.setDetailsPhotoUrl("/upload/"+filename1+ filename.substring(filename.lastIndexOf(".")));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	  
			return goodsService.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public String upd(Goods entity,HttpServletRequest request,@RequestParam("file1") MultipartFile file9,@RequestParam("file2") MultipartFile file10)throws IllegalStateException, IOException {
		try {
	            MultipartFile file = file9;
	            MultipartFile file3 = file10;
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
	                    entity.setDefaultPhotoUrl("/upload/"+filename1+ filename.substring(filename.lastIndexOf(".")));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (!file3.isEmpty()) {
	                try {
	                	String path = request.getServletContext().getRealPath("/upload");
	                	String filename = file3.getOriginalFilename();
	                	String filePath =path + filename;
	                    File saveDir = new File(filePath);
	                    if (!saveDir.getParentFile().exists())
	                        saveDir.getParentFile().mkdirs();
	                    long filename1=System.currentTimeMillis();
	                    filename = path + File.separator +filename1 + filename.substring(filename.lastIndexOf("."));
	                    file3.transferTo(new File(filename));
	                    System.out.println(filename);
	                    entity.setDetailsPhotoUrl("/upload/"+filename1+ filename.substring(filename.lastIndexOf(".")));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	  
			return goodsService.updateByPrimaryKey(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
