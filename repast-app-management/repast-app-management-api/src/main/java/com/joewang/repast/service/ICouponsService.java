package com.joewang.repast.service;


import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zxz
 * @ClassName: ICouponsService
 * @Description:
 * @date: create in 2020/3/13 16:47
 * @since JDK 1.8
 */
@FeignClient(value = "memberinfo-interface",contextId = "Coupons")
public interface ICouponsService {

    /**
     * @Description:
     *      通过不同使用类别查询优惠券
     * @author: zxz
     * @date: 2020/3/12 17:19
     * @param: [token, useType]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectCouponsByUseType")
    ResultData selectCouponsByUseType(@RequestParam("token") String token, @RequestParam("useType") String useType);


    /**
     * @Description:
     *      查询优惠券数量是否如果，如果不足不允许领取
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/updateCouponsPublishCount")
    Boolean updateCouponsPublishCount(@RequestBody Coupon coupons, @RequestParam("token") String token);

    /**
     * @Description:
     *      查询优惠券数量是否充足，如果不足不允许领取，限时领取,无失效日期
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/updateCouponsPublishCountAndEnableTime")
    ResultData updateCouponsPublishCountAndEnableTime();



}
