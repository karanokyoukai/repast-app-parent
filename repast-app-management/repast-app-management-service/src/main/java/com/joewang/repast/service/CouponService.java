package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.mapper.CouponHistoryMapper;
import com.joewang.repast.mapper.CouponMapper;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.utils.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CouponService
 * @Description:
 * @date: create in 2020/3/15 9:28
 * @since JDK 1.8
 */
@Service
public class CouponService extends BaseService<Coupon> {


    @Autowired
    private CouponMapper couponsMapper;
    @Autowired
    private CouponHistoryMapper couponsHistoryMapper;

    @Override
    public Mapper<Coupon> getMapper() {
        return couponsMapper;
    }

    /**
     * @Description:
     *      查询不同类型的优惠券
     * @author: zxz
     * @date: 2020/3/12 17:05
     * @param: [token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    public ResultData selectCouponsByUseType(String token, String useType){

        ResultData resultData = new ResultData();
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token)){
                List<Coupon> couponList = couponsMapper.selectCouponsByUseType(useType);
                if (couponList != null){
                    resultData.setData(couponList);
                    return resultData;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }

    /**
     * @Description:
     *      查询优惠券数量是否充足，如果不足不允许领取，无日期限制
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    public Boolean updateCouponsPublishCount(Coupon coupon,String token) {
        Integer updateResult = 0;
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token)) {
                synchronized (CouponService.class) {
                    if (coupon.getReceiveCount() < coupon.getPublishCount() ){
                        coupon.setReceiveCount(coupon.getReceiveCount()+1);
                        updateResult= couponsMapper.updateByPrimaryKeySelective(coupon);
                    }
                }
                if (updateResult > 0 ){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Description:
     *      限时领取,每天晚上12点刷新
     * @author: zxz
     * @date: 2020/3/12 20:38
     * @param: [coupons, token]
     * @return: java.lang.Boolean
     */
    public ResultData updateCouponsPublishCountAndEnableTime() {
        ResultData resultData = new ResultData();
        try {
            List<Coupon> coupons = couponsMapper.selectEnableTime();
            if (coupons != null){
                resultData.setData(coupons);
                return resultData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }


}
