package com.joewang.repast.controller;


import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @ClassName: CouponsController
 * @Description:
 * @date: create in 2020/3/12 17:13
 * @since JDK 1.8
 */
@RestController
public class CouponsController {

    @Autowired
    private CouponService couponService;

    /**
     * @Description:
     *      根据使用类别查询不同的优惠券
     * @author: zxz
     * @date: 2020/3/12 17:17
     * @param: [token, useType]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectCouponsByUseType")
    public ResultData selectCouponsByUseType(@RequestParam("token") String token, @RequestParam("useType") String useType){
        return couponService.selectCouponsByUseType(token, useType);
    }

    /**
     * @Description:
     *      查询优惠券数量是否如果，如果不足不允许领取
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/updateCouponsPublishCount")
    public Boolean updateCouponsPublishCount(@RequestBody Coupon coupon, @RequestParam("token") String token){
        return couponService.updateCouponsPublishCount(coupon,token);
    }

    /**
     * @Description:
     *      限时领取,每天晚上12点刷新
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param:
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/updateCouponsPublishCountAndEnableTime")
    @Scheduled(cron = "0 0 0 * * ?")
    public ResultData updateCouponsPublishCountAndEnableTime(){
        return couponService.updateCouponsPublishCountAndEnableTime();
    }

}
