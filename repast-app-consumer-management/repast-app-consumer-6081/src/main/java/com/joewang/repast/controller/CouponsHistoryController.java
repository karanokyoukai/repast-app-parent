package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.ICouponsHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @ClassName: CouponsHistoryController
 * @Description:
 * @date: create in 2020/3/13 15:00
 * @since JDK 1.8
 */
@RestController
@Api(value = "个人优惠券管理", tags = "个人优惠券接口(提供用户优惠券有关操作)")
public class CouponsHistoryController extends BaseController {
    
    @Autowired
    private ICouponsHistoryService couponsHistoryService;

    /**
     * @Description:
     *      查询个人所拥有的优惠券
     * @author: zxz
     * @date: 2020/3/13 14:14
     * @param: [memberId, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectCouponsByMemberId")
    @ApiOperation(value = "优惠券管理", notes = "查询个人优惠券")
    public ResultData selectCouponsByMemberId(PageInfos<Long> pageInfos, @RequestParam("token") String token){
        ResultData resultData = couponsHistoryService.selectCouponsByMemberId(pageInfos, token);
        if (resultData != null){
            return operationSuccess(resultData);
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      查询是否领取上限
     * @author: zxz
     * @date: 2020/3/13 13:37
     * @param: [couponsHistory, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/insertCouponsHistory")
    @ApiOperation(value = "个人优惠券管理", notes = "领取优惠券")
    public ResultData insertCouponsHistory(@RequestBody CouponHistory couponHistory, @RequestParam("token") String token){
        Boolean resultBoolean = couponsHistoryService.insertCouponsHistory(couponHistory, token);
        if (resultBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      修改优惠券状态
     * @author: zxz
     * @date: 2020/3/13 13:37
     * @param: [couponsHistory, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/updateCouponsUseType")
    @ApiOperation(value = "优惠券管理", notes = "修改优惠券状态")
    public ResultData updateCouponsUseType(@RequestBody CouponHistory couponHistory,@RequestParam("token") String token){
        Boolean resultBoolean = couponsHistoryService.updateCouponsUseType(couponHistory, token);
        if (resultBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      定时修改优惠券的状态
     * @author: zxz
     * @date: 2020/3/13 23:24
     * @param: []
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/updateUseStatus")
    @ApiOperation(value = "优惠券管理", notes = "定时修改个人优惠券状态")
    public ResultData updateUseStatus() {
        Boolean resultBoolean = couponsHistoryService.updateUseStatus();
        if (resultBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      逻辑删除优惠券
     * @author: zxz
     * @date: 2020/3/14 20:07
     * @param: [id, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/deleteCoupons")
    @ApiOperation(value = "优惠券管理", notes = "删除个人优惠券")
    public ResultData deleteCoupons(@RequestParam("id") Long id,@RequestParam("token") String token){
        Boolean resultBoolean = couponsHistoryService.deleteCoupons(id, token);
        if (resultBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }
    
}
