package com.joewang.repast.service;

import com.joewang.repast.fallback.RepastFallBackFactory;
import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 *      member相关的feign接口
 * @author: Joe Wang
 * @date: 2020-03-11
 */
//@FeignClient(value = "memberinfo-interface", fallbackFactory = RepastFallBackFactory.class, contextId = "MemberInfoClient")
@FeignClient(value = "memberinfo-interface", contextId = "MemberInfoClient")
public interface IRepastService {

    /**
     * @desc: 登录操作接口
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [member]
     * @return: java.lang.Boolean
     */
    @PostMapping("/doLogin")
    Member doLogin(@RequestBody Member member);

    /**
     * @desc: 登录日志保存
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [loginLog]
     * @return: java.lang.Boolean
     */
    @PostMapping("/addLoginLog")
    Boolean addLoginLog(@RequestBody LoginLog loginLog);

    /*
     * @Author junzheng Han
     * 修改昵称
     * @Description @Date 18:15 2020/3/13
     * @Param [member]
     * @return java.lang.Integer
     **/
    @PostMapping("/updatename")
    Integer updateUsername(@RequestBody Member member);


    /**
     * @Description:
     *      退出登录，清空token
     * @author: zxz
     * @date: 2020/3/13 17:28
     * @param: []
     * @return: java.lang.Boolean
     */
    @PostMapping("/loginOut")
    Boolean loginOut(@RequestParam("token") String token);

    /*
     * @author Zero
     * @description 根据ID进行查询个人信息 两表联查member——level
     * @param  [member]
     * @date 2020/3/15 14:55
     * @return com.joewang.repast.model.Member
     * @throws
     **/
    @PostMapping("/selectByKeyMember")
    HashMap selectByKeyMember(@RequestParam(value = "id") long key);

    /*
     * @author Zero
     * @description 修改个人信息
     * @param  [member]
     * @date 2020/3/15 14:58
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateMember")
    Boolean updateMember(@RequestBody Member member);

}
