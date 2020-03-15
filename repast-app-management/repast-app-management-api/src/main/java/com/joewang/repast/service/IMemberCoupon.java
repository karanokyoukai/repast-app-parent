package com.joewang.repast.service;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.page.PageInfos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 *      用户优惠券相关api
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@FeignClient(value = "memberinfo-interface", contextId = "MemberCouponClient")
public interface IMemberCoupon {
    /**
     * @desc:
     *      根据用户id查询用户所有优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: java.util.List<java.util.HashMap>
     */
    @PostMapping("/selectMyCoupon")
    List<HashMap> selectMyCoupon(@RequestBody Long memberid);

    /**
     * @desc:
     *      根据用户id查询用户所有优惠券  分页查询
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: java.util.List<java.util.HashMap>
     */
    @PostMapping("/selectMyCouponPage")
    PageInfo<HashMap> selectMyCouponPage(@RequestBody PageInfos<Long> pageInfos);

    /**
     * @desc: 根据用户id查询用户可领通用优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: java.util.List<com.joewang.repast.model.Coupon>
     */
    @PostMapping("/selectConponCouldGetById")
    List<Coupon> selectConponCouldGetById(@RequestBody Long memberid);

    /**
     * @desc: 根据用户id查询用户可领通用优惠券并实现分页
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<com.joewang.repast.model.Coupon>
     */
    @PostMapping("/selectConponCouldGetByIdPage")
    PageInfo<Coupon> selectConponCouldGetByIdPage(@RequestBody PageInfos<Long> pageInfos);


}
