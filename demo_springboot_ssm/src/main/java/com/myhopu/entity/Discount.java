package com.myhopu.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Discount implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer discountId;

    private Goods goods=new Goods();

    private Integer discountNum;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 后台发送到前台的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 前台发送到后台的格式
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 后台发送到前台的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 前台发送到后台的格式
    private Date endTime;
	public Integer getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(Integer discountNum) {
		this.discountNum = discountNum;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


    
}