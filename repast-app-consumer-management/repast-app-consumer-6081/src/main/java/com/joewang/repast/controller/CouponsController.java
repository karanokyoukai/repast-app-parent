package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.service.ICouponsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @ClassName: CouponsController
 * @Description:
 * @date: create in 2020/3/12 17:23
 * @since JDK 1.8
 */
@RestController
@Api(value = "优惠券管理", tags = "优惠券接口(提供用户优惠券有关操作)")
public class CouponsController extends BaseController {

    @Autowired
    private ICouponsService couponsService;

    /**
     * @Description:
     *      通过不同使用类别查询优惠券
     * @author: zxz
     * @date: 2020/3/12 17:19
     * @param: [token, useType]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectCouponsByUseType")
    @ApiOperation(value = "查询优惠券", notes = "根据不同的类型查询不同的优惠券")
    public ResultData selectCouponsByUseType(@RequestParam("token") String token, @RequestParam("useType") String useType){
        ResultData resultData = couponsService.selectCouponsByUseType(token, useType);
        if (resultData != null){
            return super.operationSuccess(resultData);
        }
        return super.operationFailed(resultData);
    }

    /**
     * @Description:
     *      查询优惠券数量是否如果，如果不足不允许领取
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: [coupons, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/updateCouponsPublishCount")
    @ApiOperation(value = "查询优惠券", notes = "根据不同的类型查询不同的优惠券")
    public ResultData updateCouponsPublishCount(@RequestBody Coupon coupon, @RequestParam("token") String token){
        Boolean resultBoolean = couponsService.updateCouponsPublishCount(coupon, token);
        if (resultBoolean){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Description:
     *      查询优惠券数量是否充足，如果不足不允许领取，限时领取,无失效日期
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: []
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/updateCouponsPublishCountAndEnableTime")
    @ApiOperation(value = "优惠券定时", notes = "定时开启优惠券任务")
    public ResultData updateCouponsPublishCountAndEnableTime(){
        ResultData resultData = couponsService.updateCouponsPublishCountAndEnableTime();
        if (resultData != null) {
            super.operationSuccess(resultData);
        }
        return super.operationFailed();
    }


}
