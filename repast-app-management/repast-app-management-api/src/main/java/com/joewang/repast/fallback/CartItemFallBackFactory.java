package com.joewang.repast.fallback;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.model.CartItem;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.ICartItemService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dawang
 * @date: Created in 2020/3/17 21:16
 * @version: 1.0
 * @description:
 */
@Component
public class CartItemFallBackFactory implements FallbackFactory<CartItem> {
    @Override
    public CartItem create(Throwable cause) {
        ICartItemService iCartItemService=new ICartItemService() {
            @Override
            public PageInfo<HashMap> selectAllCartItem(PageInfos<Long> pageInfos, String token) {
                System.out.println("查询用户购物车熔断");
                return null;
            }

            @Override
            public Boolean addCartItem(Map<String, Object> data, String token) {
                System.out.println("新增购物车熔断");
                return null;
            }

            @Override
            public Boolean clearCart(Long shopId, String token) {
                System.out.println("清空购物车熔断");
                return null;
            }
        };
        return null;
    }
}
