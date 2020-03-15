package com.joewang.repast.controller;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Collect;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.ICollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.HashMap;

/**
 * @author: dawang
 * @date: Created in 2020/3/15 0:33
 * @version: 1.0
 * @description:
 */
@RestController
@Api(value = "收藏信息", tags = "收藏信息接口(提供用户相关操作)")
public class CollectionController extends BaseController {
    @Autowired
    private ICollectionService iCollectionService;
    @PostMapping("/selectAllCollectByMemberId")
    @ApiOperation(value = "查询个人收藏记录", notes = "用户执行查询个人收藏记录操作")
    /**
     * @author dawang
     * @date 2020/3/15 0:50
     * @parameter [pageInfos, token]
     * @return com.joewang.repast.page.PageInfos
     * @description 用户查询个人收藏记录
     */
    public ResultData selectAllCollectByMemberId(PageInfos<Long> pageInfos, String token){
        PageInfo<HashMap> pageInfo=iCollectionService.selectAllCollectByMemberId(pageInfos,token);
        if (pageInfo!=null){
            return super.operationSuccess(pageInfo);
        }
        return super.operationFailed();
    }
    /**
     * @author dawang
     * @date 2020/3/15 12:29
     * @parameter [collect, token]
     * @return java.lang.Boolean
     * @description 收藏店铺 商品
     */
    @PostMapping("/insertShopCollect")
    @ApiOperation(value = "新增个人店铺收藏记录", notes = "用户执行新增个人店铺收藏记录操作")
    public ResultData insertShopCollect(Collect collect,String token){
        Boolean b=iCollectionService.insertShopCollect(collect,token);
        if (b) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }


    /**
     * @author dawang
     * @date 2020/3/15 12:46
     * @parameter [productId, token]
     * @return com.joewang.repast.base.ResultData
     * @description 取消商品收藏
     */
    @PostMapping("/updateCollectProductStatus")
    @ApiOperation(value = "取消商品收藏记录", notes = "用户执行取消商品收藏记录操作")
    public ResultData updateCollectProductStatus(Long productId,String token){
        Boolean b=iCollectionService.updateCollectProductStatus(productId,token);
        if (b) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @author dawang
     * @date 2020/3/15 12:46
     * @parameter [productId, token]
     * @return com.joewang.repast.base.ResultData
     * @description 取消店铺收藏
     */
    @PostMapping("/updateCollectShopStatus")
    @ApiOperation(value = "取消店铺收藏记录", notes = "用户执行取消店铺收藏记录操作")
    public ResultData updateCollectShopStatus(Long shopId,String token){
        Boolean b=iCollectionService.updateCollectShopStatus(shopId,token);
        if (b) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}
