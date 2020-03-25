/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/24 16:22
 * @Description
 **/
package com.joewang.repast.controller;

import com.joewang.repast.model.MemberReport;
import com.joewang.repast.service.MemberReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberReportController {

    @Autowired
    private MemberReportService memberReportService;

    /*
     * @author Zero
     * @description
     * @param  [memberReport]
     * @date 2020/3/24 16:24
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertMemberReport")
    public Boolean insertMemberReport(MemberReport memberReport){
        return memberReportService.insertMemberReport(memberReport);
    }
}