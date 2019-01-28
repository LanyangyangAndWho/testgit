package com.myhopu.mapper;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import com.myhopu.entity.Goods;
import com.myhopu.entity.Sku;


@Repository
public interface GoodsDetailsMapper {
	//此商品是否被收藏
	public Long isOrCollect(@RequestParam("goodsId")Integer goodsId,@RequestParam("userId")Integer userId);
	//查询商品详情信息
	public Goods selectGoodsDetails(Integer goodsId);
	//查询sku(库存表)
	public List<Sku> selectSkuList(Integer goodsId);
	//添加收藏
	public void addCollectGoods(Integer userId, Integer goodsId);
	//取消收藏
	public void delCollectGoods(Integer userId, Integer goodsId);
}
