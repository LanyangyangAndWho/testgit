package com.myhopu.service.impl;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myhopu.entity.Discount;
import com.myhopu.mapper.DiscountMapper;
import com.myhopu.service.DiscountService;


@Service
@Transactional
public class DiscountServiceImpl extends BaseServiceImpl<Discount> implements DiscountService {

	@Autowired
	DiscountMapper discountMapper;
	public Long findRows(String now) {
		// TODO Auto-generated method stub
		return discountMapper.findRow(now);
	}
	@Override
	public List<Discount> buyDesc() {
		// TODO Auto-generated method stu
		list=discountMapper.buyDesc();
		return list;
	}
	@Override
	public Map<String, Object> buytoday(String now, Integer page, Integer limit) {
		// TODO Auto-generated method stub
		map.put("count", findRows(now));
		List<Discount> list = null;
		List<Discount> list1 = null;
		String tomorrowdate=null;
		try {
			tomorrowdate=getTomorrowDate(now);
			map.put("count", findRows(tomorrowdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (page != null) {
			list=discountMapper.buytoday(now,(page - 1) * limit,limit);
			list1=discountMapper.buytoday(tomorrowdate, (page - 1), limit);
		} else {
			list = discountMapper.buytoday(now,null, null);
			list1 = discountMapper.buytoday(tomorrowdate,null, null);
		}
		map.put("data", list);
		map.put("tomorrow", list1);
		return map;
		
	}
	public String getTomorrowDate(String now) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //定义日期格式化的格式
		String classDateString = now;//需要加减的字符串型日期
		Date classDate = format.parse(classDateString);//把字符串转化成指定格式的日期
		Calendar calendar = Calendar.getInstance(); //使用Calendar日历类对日期进行加减
		calendar.setTime(classDate);
		calendar.add(Calendar.DAY_OF_MONTH, + 1);
		classDate = calendar.getTime();//获取加减以后的Date类型日期		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(classDate);
		
	}
	

	

}
