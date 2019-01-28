package com.myhopu.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhopu.entity.Goods;

@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
		//查询相似商品
		public List<Goods> selectLikeGoods(@RequestParam("typeId")Integer typeId, @RequestParam("goodsId")Integer goodsId);
		
		List selectTypeId();

}
