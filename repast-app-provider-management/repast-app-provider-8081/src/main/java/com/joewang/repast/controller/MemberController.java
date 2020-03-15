package com.joewang.repast.controller;

import com.joewang.repast.mapper.LoginLogMapper;
import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import com.joewang.repast.service.LoginLogService;
import com.joewang.repast.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    /*
     * @Author junzheng Han
     * 修改昵称
     * @Description @Date 18:11 2020/3/13
     * @Param [member]
     * @return java.lang.Integer
     **/
    @PostMapping("/updatename")
    public Integer updateUsername(@RequestBody Member member){
        return memberService.updateUsername(member);
    }


    /**
     * @Description:
     *      退出登录，清空token
     * @author: zxz
     * @date: 2020/3/13 17:28
     * @param: []
     * @return: java.lang.Boolean
     */
    @PostMapping("/loginOut")
    public Boolean loginOut(@RequestParam("token") String token) {
        return memberService.loginOut(token);
    }
    /*
     * @author Zero
     * @description 根据ID进行查询个人信息 两表联查member——level
     * @param  [member]
     * @date 2020/3/15 14:55
     * @return com.joewang.repast.model.Member
     * @throws
     **/
    @PostMapping("/selectByKeyMember")
    public HashMap selectByKeyMember(@RequestParam(value = "id") long key){
        return memberService.selectByKeyMember(key);
    }

    /*
     * @author Zero
     * @description 修改个人信息
     * @param  [member]
     * @date 2020/3/15 14:58
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateMember")
    public Boolean updateMember(@RequestBody Member member){
        return memberService.updateMember(member);

    }

}
