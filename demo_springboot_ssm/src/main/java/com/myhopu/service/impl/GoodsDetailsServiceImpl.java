package com.myhopu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhopu.entity.AttrName;
import com.myhopu.entity.AttrValue;
import com.myhopu.entity.Goods;
import com.myhopu.entity.Sku;
import com.myhopu.entity.User;
import com.myhopu.mapper.GoodsDetailsMapper;
import com.myhopu.mapper.GoodsMapper;
import com.myhopu.service.GoodsDetailsService;
import com.myhopu.service.GoodsService;

@Service
@Transactional
public class GoodsDetailsServiceImpl  implements GoodsDetailsService {
	protected Map<String, Object> map = new HashMap<String, Object>();
	@Autowired
	protected GoodsDetailsMapper goodsDetailsMapper;
	@Autowired
	protected GoodsMapper goodsMapper;
	//得到一个商品的详细信息表
	@Override
	public Map<String,Object> selectAllDetail(Integer goodsId,Integer userId) {
		// TODO Auto-generated method stub
		Goods goods=new Goods();
		goods.setGoodsId(goodsId);
		try {
			//查询商品详细信息
			Goods good=new Goods();
			good=goodsDetailsMapper.selectGoodsDetails(goodsId);
			//查询是否收藏了此商品
			if(goodsDetailsMapper.isOrCollect(goodsId, userId)==0) {
				good.setCollect(0);
			}else {
				good.setCollect(1);
			}
			//查询相似商品信息
			List<Goods> goodsList=goodsMapper.selectLikeGoods(good.getGoodsType().getTypeId(), goodsId);
			good.setGoodsLikeList(goodsList);
			//查询sku(库存表)
			List<Sku> skuList=goodsDetailsMapper.selectSkuList(goodsId);
			good.setSkuList(skuList);
			map.put("data",good);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	//添加收藏
	@Override
	public void addCollectGoods(Integer userId, Integer goodsId) {
		// TODO Auto-generated method stub
		goodsDetailsMapper.addCollectGoods(userId, goodsId);
	}
	//取消收藏
	@Override
	public void delCollectGoods(Integer userId, Integer goodsId) {
		// TODO Auto-generated method stub
		goodsDetailsMapper.delCollectGoods(userId, goodsId);
	}
	
}
