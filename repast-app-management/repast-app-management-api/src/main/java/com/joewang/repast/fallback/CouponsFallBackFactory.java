package com.joewang.repast.fallback;

import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.service.ICouponsService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @ClassName: CouponsFallBackFactory
 * @Description:
 * @date: create in 2020/3/12 20:59
 * @since JDK 1.8
 */

@Component
public class CouponsFallBackFactory implements FallbackFactory<ICouponsService> {
    @Override
    public ICouponsService create(Throwable throwable) {
        ICouponsService iCouponsService = new ICouponsService() {
            @Override
            public ResultData selectCouponsByUseType(String token, String useType) {
                System.out.println("熔断查询优惠券！");
                return null;
            }

            @Override
            public Boolean updateCouponsPublishCount(Coupon coupons, String token) {
                System.out.println("熔断优惠券是否充足！");
                return null;
            }

            @Override
            public ResultData updateCouponsPublishCountAndEnableTime() {
                System.out.println("熔断优惠券是否可以领取！");
                return null;
            }

        };
        return iCouponsService;
    }
}
