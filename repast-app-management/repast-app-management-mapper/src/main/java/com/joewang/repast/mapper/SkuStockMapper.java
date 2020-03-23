package com.joewang.repast.mapper;

import com.joewang.repast.model.SkuStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface SkuStockMapper extends Mapper<SkuStock> {
    SkuStock selectSkuById(Long productId);
}