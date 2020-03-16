package com.joewang.repast.service;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.model.Collect;
import com.joewang.repast.page.PageInfos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * @author: dawang
 * @date: Created in 2020/3/15 0:30
 * @version: 1.0
 * @description:
 */
@FeignClient(value = "memberinfo-interface", contextId = "MemberCollection")
public interface ICollectionService {
    /**
     * @author dawang
     * @date 2020/3/15 10:16
     * @parameter [pageInfos, token]
     * @return com.joewang.repast.page.PageInfos
     * @description 根据用户id分页查询 收藏记录
     */
    @PostMapping("/selectAllCollectByMemberId")
     PageInfo<HashMap> selectAllCollectByMemberId(@RequestBody PageInfos<Long> pageInfos, @RequestParam(value = "token") String token);


    @PostMapping("/insertShopCollect")
    /**
     * @author dawang
     * @date 2020/3/15 12:10
     * @parameter [collect, token]
     * @return java.lang.Boolean
     * @description 新增店铺收藏
     */
     Boolean insertShopCollect(@RequestBody Collect collect,@RequestParam(value = "token") String token);
    /**
     * @author dawang
     * @date 2020/3/16 15:03
     * @parameter
     * @return
     * @description 新增商品收藏
     */
    @PostMapping("/insertProductCollect")
     Boolean insertProductCollect(@RequestBody Collect collect,@RequestParam(value = "token") String token);

    /**
     * @author dawang
     * @date 2020/3/15 12:44
     * @parameter [shopId]
     * @return java.lang.Boolean
     * @description 取消店铺收藏
     */
    @PostMapping("/updateCollectShopStatus")
     Boolean updateCollectShopStatus(@RequestParam(value = "shopId") Long shopId,@RequestParam(value = "token") String token);

    /**
     * @author dawang
     * @date 2020/3/16 15:33
     * @parameter [productId, token]
     * @return java.lang.Boolean
     * @description 取消商品收藏
     */
    @PostMapping("/updateCollectProductStatus")
     Boolean updateCollectProductStatus(@RequestParam(value = "productId") Long productId,@RequestParam(value = "token") String token);
    /**
     * @author dawang
     * @date 2020/3/16 15:35
     * @parameter []
     * @return java.lang.Boolean
     * @description 定时十二点 商品如果已下架 将收藏状态改为 已收藏 但是商品已下架
     */
    @PostMapping("/updateProductCollectStatus")
     Boolean updateProductCollectStatus();

    /**
     * @author dawang
     * @date 2020/3/16 15:35
     * @parameter []
     * @return java.lang.Boolean
     * @description 定时十二点 店铺如果关门 将收藏状态改为 已收藏
     */
    @PostMapping("/updateShopCollectStatus")
    Boolean updateShopCollectStatus();
}
