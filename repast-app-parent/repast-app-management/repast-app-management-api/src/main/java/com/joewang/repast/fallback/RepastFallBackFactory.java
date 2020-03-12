package com.joewang.repast.fallback;

import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import com.joewang.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {

    public IRepastService create(Throwable throwable) {
        IRepastService repastService = new IRepastService() {
            public Member doLogin(Member member) {
                System.out.println("熔断登录方法");
                return null;
            }

            public Boolean addLoginLog(LoginLog loginLog) {
                System.out.println("熔断日志写入方法");
                return null;
            }
        };
        return repastService;
    }
}
