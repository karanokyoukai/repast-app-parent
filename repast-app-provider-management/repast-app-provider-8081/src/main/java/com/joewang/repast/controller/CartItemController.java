package com.joewang.repast.controller;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.CartItemService;
import javafx.scene.chart.ValueAxis;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dawang
 * @date: Created in 2020/3/17 21:05
 * @version: 1.0
 * @description:
 */
@RestController
@EnableScheduling
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    /**
     * @author dawang
     * @date 2020/3/17 21:08
     * @parameter [pageInfos, token]
     * @return com.github.pagehelper.PageInfo<java.util.HashMap>
     * @description 通过用户ID查询购物车、商品、店铺信息
     */
    @PostMapping("/selectAllCartItem")
    public PageInfo<HashMap> selectAllCartItem(@RequestBody PageInfos<Long> pageInfos, @RequestParam(value = "token") String token){
        return cartItemService.selectAllCartItem(pageInfos,token);
    }
    /**
     * @author dawang
     * @date 2020/3/20 17:12
     * @parameter [data, token]
     * @return java.lang.Boolean
     * @description  新增购物车
     */
    @PostMapping("/addCartItem")
    public Boolean addCartItem(@RequestBody Map<String,Object> data,@RequestParam(value = "token") String token){
        return cartItemService.addCartItem(data,token);
    }

    /**
     * @author dawang
     * @date 2020/3/20 17:18
     * @parameter [shopId, token]
     * @return java.lang.Boolean
     * @description 清空购物车
     */
    @PostMapping("/clearCart")
    public  Boolean clearCart(@RequestParam(value = "shopId") Long shopId,@RequestParam(value = "token")  String token){
        return cartItemService.clearCart(shopId,token);
    }
}
