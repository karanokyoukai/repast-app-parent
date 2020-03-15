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
}