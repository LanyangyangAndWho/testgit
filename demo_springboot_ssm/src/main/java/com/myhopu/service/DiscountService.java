package com.myhopu.service;

import java.util.List;
import java.util.Map;

import com.myhopu.entity.Discount;

public interface DiscountService extends BaseService<Discount> {
	Long findRows(String now);

	List<Discount> buyDesc();

	Map<String, Object> buytoday(String now, Integer page, Integer limit);
}
