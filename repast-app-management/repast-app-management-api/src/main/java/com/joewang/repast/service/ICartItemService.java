package com.joewang.repast.service;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.page.PageInfos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dawang
 * @date: Created in 2020/3/17 21:13
 * @version: 1.0
 * @description:
 */
@FeignClient(value = "memberinfo-interface", contextId = "CartItemService")
public interface ICartItemService {
    @PostMapping("/selectAllCartItem")
    /**
     * @author dawang
     * @date 2020/3/17 21:19
     * @parameter [pageInfos, token]
     * @return com.github.pagehelper.PageInfo<java.util.HashMap>
     * @description 查询用户购物车信息
     */
    PageInfo<HashMap> selectAllCartItem(@RequestBody PageInfos<Long> pageInfos, @RequestParam(value = "token") String token);

    @PostMapping("/addCartItem")
    /**
     * @author dawang
     * @date 2020/3/20 17:13
     * @parameter [data, token]
     * @return java.lang.Boolean
     * @description  新增购物车
     */
     Boolean addCartItem(@RequestBody Map<String,Object> data, @RequestParam(value = "token") String token);
    /**
     * @author dawang
     * @date 2020/3/20 17:18
     * @parameter [shopId, token]
     * @return java.lang.Boolean
     * @description 清空购物车
     */
    @PostMapping("/clearCart")
    Boolean clearCart(@RequestParam(value = "shopId") Long shopId,@RequestParam(value = "token")  String token);
}
