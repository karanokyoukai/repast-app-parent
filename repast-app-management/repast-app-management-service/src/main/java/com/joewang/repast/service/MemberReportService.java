/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/24 16:16
 * @Description
 **/
package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.MemberReportMapper;
import com.joewang.repast.model.MemberReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
@Service
public class MemberReportService extends BaseService<MemberReport> {

    @Autowired
    private MemberReportMapper memberReportMapper;

    @Override
    public Mapper getMapper() {
        return memberReportMapper;
    }

    /*
     * @author Zero
     * @description 新增用户举报
     * @param  [memberReport]
     * @date 2020/3/24 16:21
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean insertMemberReport(MemberReport memberReport){
        try {
            Integer result = super.save(memberReport);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}