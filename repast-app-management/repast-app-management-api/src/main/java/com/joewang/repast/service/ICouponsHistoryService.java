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
 * @author zxz
 * @ClassName: ICouponsHistoryService
 * @Description:
 * @date: create in 2020/3/13 16:46
 * @since JDK 1.8
 */
@FeignClient(value = "memberinfo-interface" ,contextId = "CouponsHistory")
public interface ICouponsHistoryService {


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



    /**
     * @Description:
     *      查询个人所拥有的优惠券
     * @author: zxz
     * @date: 2020/3/13 14:14
     * @param: [memberId, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectCouponsByMemberId")
    ResultData selectCouponsByMemberId(@RequestParam("memberId") Integer memberId, @RequestParam("token") String token);

    /**
     * @Description:
     *      查询是否领取上限
     * @author: zxz
     * @date: 2020/3/13 13:37
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/insertCouponsHistory")
    Boolean insertCouponsHistory(@RequestBody CouponHistory couponHistory, @RequestParam("token") String token);

    /**
     * @Description:
     *      修改优惠券状态
     * @author: zxz
     * @date: 2020/3/13 13:37
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/updateCouponsUseType")
    Boolean updateCouponsUseType(@RequestBody CouponHistory couponsHistory, @RequestParam("token") String token);

    /**
     * @Description:
     *      定时修改优惠券的状态
     * @author: zxz
     * @date: 2020/3/13 23:24
     * @param: []
     * @return: java.lang.Integer
     */
    @PostMapping("/updateUseStatus")
    Boolean updateUseStatus() ;

    /**
     * @Description:
     *      逻辑删除优惠券
     * @author: zxz
     * @date: 2020/3/14 20:07
     * @param: [id, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/deleteCoupons")
    Boolean deleteCoupons(@RequestParam("id") Long id, @RequestParam("token") String token);


}
