package com.joewang.repast.mapper;

import com.joewang.repast.model.Coupon;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouponMapper extends Mapper<Coupon> {

    /**
     * @Description:
     *      查询限领取的数量
     * @author: zxz
     * @date: 2020/3/13 22:45
     * @param: [couponsId]
     * @return: java.lang.Integer
     */
    Integer selectPerLimit(Long couponsId);

    /**
     * @Description:
     *      查询限领取时间大于当前时间的优惠券
     * @author: zxz
     * @date: 2020/3/13 22:47
     * @param: []
     * @return: com.aaa.zxz.repast.model.Coupons
     */
    List<Coupon> selectEnableTime();

    List<Coupon> selectCouponsByUseType(String useStatus);
}