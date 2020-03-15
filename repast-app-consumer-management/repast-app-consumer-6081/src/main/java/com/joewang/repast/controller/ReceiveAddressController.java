/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/13 20:59
 * @Description
 **/
package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.ReceiveAddress;
import com.joewang.repast.service.IReceiveAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "收货地址",tags ="收货地址接口(提供有关收货地址的操作)" )
public class ReceiveAddressController extends BaseController {
    @Autowired
    private IReceiveAddress iReceiveAddress;
    /*
     * @author Zero
     * @description 查询当前用户所有的收货地址 根据member_ID进行查询
     * @param  [receiveAddress]
     * @date 2020/3/13 20:54
     * @return java.util.List<com.aaa.lee.repast.model.ReceiveAddress>
     * @throws
     **/
    @PostMapping("/selectAllReceiveAddress")
    @ApiOperation(value = "查询所有", notes = "查询所有的收货地址")
    public ResultData selectAllReceiveAddress(@RequestParam(value = "id") long id){
        List<ReceiveAddress> list = iReceiveAddress.selectAllReceiveAddress(id);
        if (list!=null){
         return super.operationSuccess(list);
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 根据主键id查询收货地址
     * @param  [id]
     * @date 2020/3/15 17:53
     * @return com.joewang.repast.model.ReceiveAddress
     * @throws
     **/
    @PostMapping("/selectByKeyReceiveAddress")
    @ApiOperation(value = "查询ID", notes = "查询指定的收货地址")
    public ResultData selectByKeyReceiveAddress(@RequestParam(value = "id") long id){
        ReceiveAddress receiveAddress = iReceiveAddress.selectByKeyReceiveAddress(id);
        if (receiveAddress!=null){
            return super.operationSuccess(receiveAddress);
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 修改当前用户的收货地址 根据ID进行修改
     * @param  [receiveAddress]
     * @date 2020/3/13 20:55
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateReceiveAddress")
    @ApiOperation(value = "修改操作", notes = "修改指定的收货地址")
    public ResultData updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress){
        Boolean state = iReceiveAddress.updateReceiveAddress(receiveAddress);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 新增当前用户的收货地址
     * @param  [receiveAddress]
     * @date 2020/3/13 20:56
     * @return java.lang.Boolean
     * @throws
     **/
    @ApiOperation(value = "新增操作", notes = "添加收货地址")
    @PostMapping("/insertReceiveAddress")
    public ResultData insertReceiveAddress(@RequestBody ReceiveAddress receiveAddress){
        Boolean state = iReceiveAddress.insertReceiveAddress(receiveAddress);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 删除当前用户的收货地址 根据ID
     * @param  [receiveAddress]
     * @date 2020/3/13 20:56
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteReceiveAddress")
    @ApiOperation(value = "删除操作", notes = "删除指定的收货地址")
    public ResultData deleteReceiveAddress(@RequestParam(value = "id") long id){
        Boolean state = iReceiveAddress.deleteReceiveAddress(id);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}