package com.joewang.repast.service;

import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "memberinfo-interface", contextId = "MemberCouponLevel")
public interface IMemberLevel {
    /*
     * @Author junzheng Han
     * 查询会员信息
     * @Description @Date 17:19 2020/3/14
     * @Param [member]
     * @return com.joewang.repast.model.MemberLevel
     **/
    @PostMapping("/selectMemberLevel")
    MemberLevel selectMemberLevel(@RequestBody Member member);
}
