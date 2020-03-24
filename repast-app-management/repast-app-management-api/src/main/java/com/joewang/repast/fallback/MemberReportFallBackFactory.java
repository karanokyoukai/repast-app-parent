package com.joewang.repast.fallback;

import com.joewang.repast.model.MemberReport;
import com.joewang.repast.service.IMemberReportService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @ClassName: MemberReportFallBackFactory
 * @Description:
 * @date: create in 2020/3/24 16:42
 * @since JDK 1.8
 */
@Component
public class MemberReportFallBackFactory implements FallbackFactory<IMemberReportService>  {


    @Override
    public IMemberReportService create(Throwable throwable) {
        IMemberReportService iMemberReportService = new IMemberReportService() {
            @Override
            public Boolean insertMemberReport(MemberReport memberReport) {
                System.out.println("熔断用户举报");
                return null;
            }
        };
        return null;
    }
}
