package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.CouponHistoryMapper;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 *      用户优惠券相关service层类
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@Service
public class CouponHistoryService extends BaseService<CouponHistory> {
    @Autowired
    private CouponHistoryMapper couponHistoryMapper;

    public Mapper<CouponHistory> getMapper() {
        return couponHistoryMapper;
    }

    /**
     * @desc: 查询用户所拥有的优惠券及优惠券信息
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: java.util.List<java.util.HashMap>
     */
    public List<HashMap> selectMyCoupon(Long memberid){
        return couponHistoryMapper.selectMemberCoupon(memberid);
    }

    /**
     * @desc: 更新用户券表中过期的优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: []
     * @return: java.lang.Integer
     */
    public Integer updateMemberCouponState(){
        return couponHistoryMapper.updateMemberCouponState();
    }

    /**
     * @desc: 根据用户id查询用户可领通用优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [memberid]
     * @return: java.util.List<com.joewang.repast.model.Coupon>
     */
    public List<Coupon> selectConponCouldGet(Long memberid){
        return couponHistoryMapper.selectConponCouldGet(memberid);
    }
}
