package com.joewang.repast.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseService;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.mapper.CouponHistoryMapper;
import com.joewang.repast.mapper.CouponMapper;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CouponMapper couponMapper;

    @Override
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
        PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
        List<Coupon> coupons = couponHistoryMapper.selectConponCouldGet(pageInfos.getT());
        PageInfo pageInfo = new PageInfo(coupons);
        return pageInfo;
    }

    /**
     * @Description:
     *      查询个人所拥有的优惠券
     * @author: zxz
     * @date: 2020/3/13 14:14
     * @param: [memberId, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    public ResultData selectCouponsByMemberId(PageInfos<Long> pageInfos, String token){
        ResultData resultData = new ResultData();
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token) ) {
                PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
                List<Map> list = couponHistoryMapper.selectByMemberId(pageInfos.getT());
                PageInfos pageInfos1 = new PageInfos();
                pageInfos1.setT(list);
                if (list != null && list.size()>0){
                    resultData.setData(pageInfos1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }

    /**
     * @Description:
     *      查询是否领取上限
     * @author: zxz
     * @date: 2020/3/13 13:37
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    public Boolean insertCouponsHistory(CouponHistory couponHistory,String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token) ) {
                Integer perLimit = couponMapper.selectPerLimit(couponHistory.getCouponId());
                Integer couponsCount = couponHistoryMapper.selectCount(couponHistory);
                if (couponsCount < perLimit){
                    Integer insertResult = couponHistoryMapper.insert(couponHistory);
                    if (insertResult > 0){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Description:
     *      修改优惠券状态
     * @author: zxz
     * @date: 2020/3/13 13:37
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    public Boolean updateCouponsUseType(CouponHistory couponHistory,String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token) ) {
                Integer result = couponHistoryMapper.updateByPrimaryKey(couponHistory);
                if (result > 0){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Description:
     *      逻辑删除优惠券
     * @author: zxz
     * @date: 2020/3/14 20:07
     * @param: [id, token]
     * @return: java.lang.Boolean
     */
    public Boolean deleteCoupons(Long id,String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token) ) {
                Integer result = couponHistoryMapper.deleteCoupons(id);
                if (result > 0){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    /**
     * @Description:
     *      定时修改优惠券的状态
     * @author: zxz
     * @date: 2020/3/13 23:24
     * @param: []
     * @return: java.lang.Boolean
     */
    public Boolean updateUseStatus(){
        try {
            Integer result = couponHistoryMapper.updateUseStatus();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
