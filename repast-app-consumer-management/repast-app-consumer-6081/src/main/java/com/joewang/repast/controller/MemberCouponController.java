package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.service.IMemberCoupon;
import com.joewang.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@RestController
@Api(value = "用户优惠券信息", tags = "用户优惠券信息接口(提供用户优惠券相关操作)")
public class MemberCouponController extends BaseController {
    @Autowired
    private IMemberCoupon memberCoupon;

    @PostMapping("/selectMyCoupon")
    @ApiOperation(value = "查询个人优惠券", notes = "用户执行查询个人优惠券操作")
    public ResultData selectMyCoupon(Long memberid){
        List<HashMap> couponHistories = memberCoupon.selectMyCoupon(memberid);
        return operationSuccess(couponHistories);
    }
}
