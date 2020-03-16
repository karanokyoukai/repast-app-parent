package com.joewang.repast.mapper;

import com.joewang.repast.model.Collect;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CollectMapper extends Mapper<Collect> {
    //根据用户id查询收藏记录
    List<HashMap> selectCollectByMemberId(Long memberid);

    //取消商品收藏
    Integer updateCollectProductStatus(Long productid);

    //取消店铺收藏
    Integer updateCollectShopStatus(Long shopid);

    //查询商品上下架状态
    Integer selectProductPublishStatus(Long productid);
    //查询店铺营业状态
    Integer selectShopStatus(Long shopid);

    //商品如果已下架 将收藏状态改为 已收藏 但是商品已下架
    Integer updateProductCollectStatus();
    //店铺关门 讲收藏状态改为 已收藏 但是商品已下架
    Integer updateShopCollectStatus();
}