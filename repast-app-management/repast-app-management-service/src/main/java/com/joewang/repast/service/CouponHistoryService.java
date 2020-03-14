package com.joewang.repast.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.CouponHistoryMapper;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.page.PageInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @desc: 查询用户所拥有的优惠券及优惠券信息 分页查询
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<java.util.List<java.util.HashMap>>
     */
    public PageInfo<HashMap> selectMyCouponPage(PageInfos<Long> pageInfos){
        PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
        List<HashMap> coupons = couponHistoryMapper.selectMemberCoupon(pageInfos.getT());
        PageInfo pageInfo = new PageInfo(coupons);
        return pageInfo;
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

    /**
     * @desc: 分页查询  据用户id查询用户可领通用优惠券
     * @author: Joe Wang
     * @date: 2020/3/14
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<com.joewang.repast.model.Coupon>
     */
    public PageInfo<Coupon> selectConponCouldGetPage(PageInfos<Long> pageInfos){
        System.out.println(pageInfos.getT());
        PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
        List<Coupon> coupons = couponHistoryMapper.selectConponCouldGet(pageInfos.getT());
        PageInfo pageInfo = new PageInfo(coupons);
        return pageInfo;
    }
}
