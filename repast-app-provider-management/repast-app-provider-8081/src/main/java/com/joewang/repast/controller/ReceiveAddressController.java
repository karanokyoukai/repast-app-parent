/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/13 20:35
 * @Description
 **/
package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.model.ReceiveAddress;
import com.joewang.repast.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiveAddressController extends BaseController {
    @Autowired
    private ReceiveAddressService receiveAddressService;

    /*
     * @author Zero
     * @description 查询当前用户所有的收货地址 根据member_ID进行查询
     * @param  [receiveAddress]
     * @date 2020/3/13 20:54
     * @return java.util.List<com.aaa.lee.repast.model.ReceiveAddress>
     * @throws
     **/
    @PostMapping("/selectAllReceiveAddress")
    public List<ReceiveAddress> selectAllReceiveAddress(@RequestParam(value = "id") long id){
        return receiveAddressService.selectAllReceiveAddress(id);
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
    public ReceiveAddress selectByKeyReceiveAddress(@RequestParam(value = "id") long id){
        return receiveAddressService.selectByKeyReceiveAddress(id);
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
    public Boolean updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress){
        return receiveAddressService.updateReceiveAddress(receiveAddress);
    }

    /*
     * @author Zero
     * @description 新增当前用户的收货地址
     * @param  [receiveAddress]
     * @date 2020/3/13 20:56
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertReceiveAddress")
    public Boolean insertReceiveAddress(@RequestBody ReceiveAddress receiveAddress){
        return receiveAddressService.insertReceiveAddress(receiveAddress);
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
    public Boolean deleteReceiveAddress(@RequestParam(value = "id") long id){
        return receiveAddressService.deleteReceiveAddress(id);
    }
}