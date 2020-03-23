package com.joewang.repast.mapper;

import com.joewang.repast.model.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface ProductMapper extends Mapper<Product> {
    Product selectProductById(Long productId);
    //8分钟未开启，加入购物车修改商品表库存的数量
    Integer updateStockById(Product product);
}