package com.joewang.repast.controller;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.model.Collect;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.HashMap;

/**
 * @author: dawang
 * @date: Created in 2020/3/15 0:24
 * @version: 1.0
 * @description:
 */
@RestController
@EnableScheduling
public class CollecController {
    @Autowired
    private CollectService collectService;
    /**
     * @author dawang
     * @date 2020/3/15 0:27
     * @parameter [pageInfos, token]
     * @return com.joewang.repast.page.PageInfos
     * @description
     */
    @PostMapping("/selectAllCollectByMemberId")
    public PageInfo<HashMap> selectAllCollectByMemberId(@RequestBody PageInfos<Long> pageInfos, @RequestParam(value = "token") String token){
        return collectService.selectAllCollectByMemberId(pageInfos,token);
    }
    /**
     * @author dawang
     * @date 2020/3/15 12:29
     * @parameter [collect, token]
     * @return java.lang.Boolean
     * @description 收藏店铺
     */
    @PostMapping("/insertShopCollect")
    public Boolean insertShopCollect(@RequestBody Collect collect,@RequestParam(value = "token") String token){
        return collectService.insertShopCollect(collect,token);
    }
    /**
     * @author dawang
     * @date 2020/3/16 15:01
     * @parameter [collect, token]
     * @return java.lang.Boolean
     * @description 收藏商品
     */
    @PostMapping("/insertProductCollect")
    public Boolean insertProductCollect(@RequestBody Collect collect,@RequestParam(value = "token") String token){
        return collectService.insertProductCollect(collect,token);
    }
    /**
     * @author dawang
     * @date 2020/3/15 12:42
     * @parameter [productId, token]
     * @return java.lang.Boolean
     * @description 取消商品收藏
     */
    @PostMapping("/updateCollectProductStatus")
    public Boolean updateCollectProductStatus(@RequestParam(value = "productId") Long productId,@RequestParam(value = "token") String token){
        return collectService.updateCollectProductStatus(productId,token);
    }

    /**
     * @author dawang
     * @date 2020/3/15 12:42
     * @parameter [productId, token]
     * @return java.lang.Boolean
     * @description 取消店铺收藏
     */
    @PostMapping("/updateCollectShopStatus")
    public Boolean updateCollectShopStatus(@RequestParam(value = "shopId") Long shopId,@RequestParam(value = "token") String token){
        return collectService.updateCollectShopStatus(shopId,token);
    }
    /**
     * @author dawang
     * @date 2020/3/16 15:29
     * @parameter []
     * @return java.lang.Boolean
     * @description 定时十二点 商品如果已下架 将收藏状态改为 已收藏 但是商品已下架
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @PostMapping("/updateProductCollectStatus")
    public Boolean updateProductCollectStatus(){
        return collectService.updateProductCollectStatus();
    }
    /**
     * @author dawang
     * @date 2020/3/16 15:29
     * @parameter []
     * @return java.lang.Boolean
     * @description 定时十二点 店铺如果关门 将收藏状态改为 已收藏
     */
    @PostMapping("/updateShopCollectStatus")
    public Boolean updateShopCollectStatus(){
        return collectService.updateShopCollectStatus();
    }
}
