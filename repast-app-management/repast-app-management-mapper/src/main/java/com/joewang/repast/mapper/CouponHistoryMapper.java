package com.joewang.repast.mapper;

import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CouponHistoryMapper extends Mapper<CouponHistory> {
    //根据用户id查询用户拥有的优惠券
    List<HashMap> selectMemberCoupon(Long memberid);

    // 定时更新用户优惠券状态
    Integer updateMemberCouponState();

    // 根据用户id查询用户可领通用优惠券
    List<Coupon> selectConponCouldGet(Long memberid);
}