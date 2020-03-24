package com.joewang.repast.fallback;


import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Coupon;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.ICouponsHistoryService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author zxz
 * @ClassName: CouponsHistoryFallBackFactory
 * @Description:
 * @date: create in 2020/3/13 14:56
 * @since JDK 1.8
 */
@Component
public class CouponsHistoryFallBackFactory implements FallbackFactory<ICouponsHistoryService> {
    @Override
    public ICouponsHistoryService create(Throwable throwable) {
        ICouponsHistoryService iCouponsHistoryService = new ICouponsHis toryService() {
            @Override
            public List<HashMap> selectMyCoupon(Long memberid) {
                System.out.println("熔断根据用户id查询用户所有优惠券");
                return null;
            }

            @Override
            public PageInfo<HashMap> selectMyCouponPage(PageInfos<Long> pageInfos) {
                System.out.println("熔断根据用户id查询用户所有优惠券 分页查询");
                return null;
            }

            @Override
            public List<Coupon> selectConponCouldGetById(Long memberid) {
                System.out.println("熔断根据用户id查询用户可领通用优惠券");
                return null;
            }

            @Override
            public PageInfo<Coupon> selectConponCouldGetByIdPage(PageInfos<Long> pageInfos) {
                System.out.println("熔断根据用户id查询用户可领通用优惠券并实现分页");
                return null;
            }

            @Override
            public ResultData selectCouponsByMemberId(PageInfos pageInfos, String token) {
                System.out.println("熔断查询个人所拥有的的优惠券");
                return null;
            }

            @Override
            public Boolean insertCouponsHistory(CouponHistory couponHistory, String token) {
                System.out.println("熔断新增优惠券历史");
                return null;
            }

            @Override
            public Boolean updateCouponsUseType(CouponHistory couponHistory, String token) {
                System.out.println("熔断修改优惠券状态");
                return null;
            }

            @Override
            public Boolean updateUseStatus() {
                System.out.println("熔断定时修改优惠券状态");
                return null;
            }

            @Override
            public Boolean deleteCoupons(Long id, String token) {
                System.out.println("熔断删除优惠券");
                return null;
            }
        };
        return null;
    }
}
