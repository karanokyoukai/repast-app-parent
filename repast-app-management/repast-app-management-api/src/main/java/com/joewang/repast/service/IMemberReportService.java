package com.joewang.repast.service;

import com.joewang.repast.model.MemberReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zxz
 * @ClassName: IMemberReportService
 * @Description:
 * @date: create in 2020/3/24 16:41
 * @since JDK 1.8
 */
@FeignClient(value = "memberinfo-interface", contextId = "MemberReport")
public interface IMemberReportService {

    /*
     * @author Zero
     * @description
     * @param  [memberReport]
     * @date 2020/3/24 16:24
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertMemberReport")
    Boolean insertMemberReport(MemberReport memberReport);

}
