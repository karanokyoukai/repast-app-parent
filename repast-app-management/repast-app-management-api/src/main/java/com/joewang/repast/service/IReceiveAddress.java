/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/15 15:30
 * @Description
 **/
package com.joewang.repast.service;

import com.joewang.repast.model.ReceiveAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "memberinfo-interface", contextId = "ReceiveAddressClient")
public interface IReceiveAddress {
    /*
     * @author Zero
     * @description 查询当前用户所有的收货地址 根据member_ID进行查询
     * @param  [receiveAddress]
     * @date 2020/3/13 20:54
     * @return java.util.List<com.aaa.lee.repast.model.ReceiveAddress>
     * @throws
     **/
    @PostMapping("/selectAllReceiveAddress")
    List<ReceiveAddress> selectAllReceiveAddress(@RequestParam(value = "id") long id);

    /*
     * @author Zero
     * @description 根据主键id查询收货地址
     * @param  [id]
     * @date 2020/3/15 17:53
     * @return com.joewang.repast.model.ReceiveAddress
     * @throws
     **/
    @PostMapping("/selectByKeyReceiveAddress")
    ReceiveAddress selectByKeyReceiveAddress(@RequestParam(value = "id") long id);

    /*
     * @author Zero
     * @description 修改当前用户的收货地址 根据ID进行修改
     * @param  [receiveAddress]
     * @date 2020/3/13 20:55
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateReceiveAddress")
    Boolean updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress);

    /*
     * @author Zero
     * @description 新增当前用户的收货地址
     * @param  [receiveAddress]
     * @date 2020/3/13 20:56
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertReceiveAddress")
    Boolean insertReceiveAddress(@RequestBody ReceiveAddress receiveAddress);

    /*
     * @author Zero
     * @description 删除当前用户的收货地址 根据ID
     * @param  [receiveAddress]
     * @date 2020/3/13 20:56
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteReceiveAddress")
    Boolean deleteReceiveAddress(@RequestParam(value = "id") long id);
}
