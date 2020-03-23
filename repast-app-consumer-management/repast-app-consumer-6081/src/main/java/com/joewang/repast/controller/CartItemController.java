package com.joewang.repast.controller;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.ICartItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dawang
 * @date: Created in 2020/3/17 21:18
 * @version: 1.0
 * @description:
 */
@RestController
@Api(value = "购物车信息", tags = "购物车信息接口(提供购物车信息相关操作)")
public class CartItemController extends BaseController {
    @Autowired
    private ICartItemService iCartItemService;
    /**
     * @author dawang
     * @date 2020/3/20 17:14
     * @parameter [pageInfos, token]
     * @return com.joewang.repast.base.ResultData
     * @description 查询用户购物车信息
     */
    @PostMapping("/selectAllCartItem")
    @ApiOperation(value = "查询用户购物车信息", notes = "用户执行查询用户购物车信息操作")
    public ResultData selectAllCartItem(PageInfos<Long> pageInfos,String token){
        PageInfo<HashMap> pageInfo=iCartItemService.selectAllCartItem(pageInfos,token);
        if (pageInfo!=null){
            return super.operationSuccess(pageInfo);
        }
        return super.operationFailed(pageInfo);
    }
    /**
     * @author dawang
     * @date 2020/3/20 17:19
     * @parameter [data, token]
     * @return com.joewang.repast.base.ResultData
     * @description 新增购物车
     */
    @PostMapping("/addCartItem")
    @ApiOperation(value = "新增购物车", notes = "用户执行新增购物车操作")
    public ResultData addCartItem(@RequestBody Map<String,Object> data, @RequestParam(value = "token") String token){
        Boolean b=iCartItemService.addCartItem(data,token);
        if (b){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
    /**
     * @author dawang
     * @date 2020/3/20 17:21
     * @parameter [shopId, token]
     * @return com.joewang.repast.base.ResultData
     * @description 清空购物车
     */
    @PostMapping("/clearCart")
    @ApiOperation(value = "清空购物车", notes = "用户执行清空购物车操作")
    public ResultData clearCart(Long shopId,String token){
        Boolean b=iCartItemService.clearCart(shopId,token);
        if (b){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}
