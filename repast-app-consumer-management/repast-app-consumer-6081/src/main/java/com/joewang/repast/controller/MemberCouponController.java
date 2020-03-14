package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.page.PageInfos;
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
 *      用户优惠券信息相关的consumer层类
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@RestController
@Api(value = "用户优惠券信息", tags = "用户优惠券信息接口(提供用户优惠券相关操作)")
public class MemberCouponController extends BaseController {
    @Autowired
    private IMemberCoupon memberCoupon;

    /**
     * @desc: 根据用户id查询个人优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: com.joewang.repast.base.ResultData
     */
    @PostMapping("/selectMyCoupon")
    @ApiOperation(value = "查询个人优惠券", notes = "用户执行查询个人优惠券操作")
    public ResultData selectMyCoupon(Long memberid){
        List<HashMap> couponHistories = memberCoupon.selectMyCoupon(memberid);
        return operationSuccess(couponHistories);
    }

    /**
     * @desc: 根据用户id查询个人可领通用优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: com.joewang.repast.base.ResultData
     */
    @PostMapping("/selectConponCouldGetById")
    @ApiOperation(value = "查询个人可领通用优惠券", notes = "用户执行查询个人可领通用优惠券操作")
    public ResultData selectConponCouldGetById(Long memberid){
        return operationSuccess(memberCoupon.selectConponCouldGetById(memberid));
    }

    /**
     * @desc: 跟据用户id查询个人可领通用优惠券并实现分页
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [pageInfos]
     * @return: com.joewang.repast.base.ResultData
     */
    @PostMapping("/selectConponCouldGetByIdPage")
    @ApiOperation(value = "查询个人可领通用优惠券(分页)", notes = "个人可领通用优惠券的分页查询操作")
    public ResultData selectConponCouldGetByIdPage(PageInfos<Long> pageInfos){
        return operationSuccess(memberCoupon.selectConponCouldGetByIdPage(pageInfos));
    }
}
