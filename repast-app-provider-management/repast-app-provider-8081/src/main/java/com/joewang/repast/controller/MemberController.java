package com.joewang.repast.controller;

import com.joewang.repast.mapper.LoginLogMapper;
import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import com.joewang.repast.service.LoginLogService;
import com.joewang.repast.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 *      Member相关的Provider层Controller类
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * @desc: 调用member服务层执行登陆操作
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [member]
     * @return: java.lang.Boolean
     */
    @PostMapping("/doLogin")
    public Member doLogin(@RequestBody Member member){
        return memberService.doLogin(member);
    }

    @PostMapping("/addLoginLog")
    public Boolean addLoginLog(@RequestBody LoginLog loginLog){
        return loginLogService.addLoginLog(loginLog);
    }

}
