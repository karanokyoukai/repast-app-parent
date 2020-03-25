package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.MemberReport;
import com.joewang.repast.service.IMemberReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @ClassName: MemberReportController
 * @Description:
 * @date: create in 2020/3/24 16:43
 * @since JDK 1.8
 */
@RestController
public class MemberReportController extends BaseController {

    @Autowired
    private IMemberReportService memberReportService;


    /*
     * @author Zero
     * @description
     * @param  [memberReport]
     * @date 2020/3/24 16:24
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertMemberReport")
    public ResultData insertMemberReport(MemberReport memberReport){
        Boolean aBoolean = memberReportService.insertMemberReport(memberReport);
        if (aBoolean){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}
