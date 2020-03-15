package com.joewang.repast.fallback;

import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import com.joewang.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {
    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService iRepastService =new IRepastService() {
            @Override
            public Member doLogin(Member member) {
                return null;
            }

            @Override
            public Boolean addLoginLog(LoginLog loginLog) {
                return null;
            }

            @Override
            public Integer updateUsername(Member member) {
                return null;
            }

            @Override
            public Boolean loginOut(String token) {
                return null;
            }

            @Override
            public HashMap selectByKeyMember(long key) {
                return null;
            }

            @Override
            public Boolean updateMember(Member member) {
                return null;
            }
        };
        return null;
    }
}
