package com.joewang.repast.controller;

import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import com.joewang.repast.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberLevelController
 * @Description TODO
 * @Author 13061
 * @Date 2020/3/14 17:14
 * @Version 1.0
 */
@RestController
public class MemberLevelController {
    @Autowired
    private MemberLevelService memberLevelService;
    /*
     * @Author junzheng Han
     * 查询会员信息
     * @Description @Date 17:17 2020/3/14
     * @Param [member]
     * @return com.joewang.repast.model.MemberLevel
     **/
    @PostMapping("/selectMemberLevel")
    public MemberLevel selectMemberLevel(@RequestBody Member member){
        return memberLevelService.selectMemberLevel(member);
    }

}
