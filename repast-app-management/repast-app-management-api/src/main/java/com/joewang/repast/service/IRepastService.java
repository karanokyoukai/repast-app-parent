package com.joewang.repast.service;

import com.joewang.repast.fallback.RepastFallBackFactory;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description:
 *      member相关的feign接口
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@FeignClient(value = "memberinfo-interface", fallbackFactory = RepastFallBackFactory.class, contextId = "MemberInfoClient")
public interface IRepastService {

    /**
     * @desc: 登录操作接口
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [member]
     * @return: java.lang.Boolean
     */
    @PostMapping("/doLogin")
    Member doLogin(@RequestBody Member member);

    /**
     * @desc: 登录日志保存
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [loginLog]
     * @return: java.lang.Boolean
     */
    @PostMapping("/addLoginLog")
    Boolean addLoginLog(@RequestBody LoginLog loginLog);

    /**
     * @desc: 用户优惠券查询
     * @author: Joe Wang
     * @date: 2020/3/13
     * @param: [memberid]
     * @return: java.util.List<com.joewang.repast.model.CouponHistory>
     */
//    @PostMapping("/selectMyCoupon")
//    List<CouponHistory> selectMyCoupon(@RequestBody Long memberid);

}
