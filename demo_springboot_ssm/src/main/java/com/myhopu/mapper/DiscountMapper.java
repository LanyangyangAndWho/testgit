package com.myhopu.mapper;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Repository;

import com.myhopu.entity.Discount;

@Repository
public interface DiscountMapper extends BaseMapper<Discount> {
	List<Discount> buyDesc();
	List<Discount> buytoday(@PathParam("now")String now, @PathParam("currentRecord")Integer currentRecord, @PathParam("limit") Integer limit);
	Long findRow(@PathParam("now")String now);
}
