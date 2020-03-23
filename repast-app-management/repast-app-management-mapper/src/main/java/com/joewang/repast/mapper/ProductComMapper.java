package com.joewang.repast.mapper;

import com.joewang.repast.model.ProductCom;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface ProductComMapper extends Mapper<ProductCom> {
    ProductCom selectPcomById(Long productId);
}