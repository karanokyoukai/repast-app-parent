package com.joewang.repast.mapper;

import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CouponHistoryMapper extends Mapper<CouponHistory> {
    //根据用户id查询用户拥有的优惠券
    List<HashMap> selectMemberCoupon(Long memberid);

    // 定时更新用户优惠券状态
    Integer updateMemberCouponState();

    // 根据用户id查询用户可领通用优惠券
    List<Coupon> selectConponCouldGet(Long memberid);

    /**
     * @Description:
     *      通过用户id查询自己的优惠券
     * @author: zxz
     * @date: 2020/3/13 23:24
     * @param: [memberId]
     * @return: java.util.List<com.aaa.zxz.repast.model.CouponsHistory>
     */
    List<Map> selectByMemberId(Long memberId);

    /**
     * @Description:
     *      定时修改优惠券的状态
     * @author: zxz
     * @date: 2020/3/13 23:24
     * @param: []
     * @return: java.lang.Integer
     */
    Integer updateUseStatus();

    /**
     * @Description:
     *      根据id删除优惠券
     * @author: zxz
     * @date: 2020/3/14 20:09
     * @param: [id]
     * @return: java.lang.Integer
     */
    Integer deleteCoupons(Long id);

}