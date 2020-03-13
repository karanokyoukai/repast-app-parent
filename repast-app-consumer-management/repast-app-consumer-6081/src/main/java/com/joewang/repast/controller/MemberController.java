package com.joewang.repast.controller;

import com.joewang.repast.annotation.LoginLogAnnotation;
import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Member;
import com.joewang.repast.service.IRepastService;
import com.joewang.repast.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@RestController
@Api(value = "用户信息", tags = "用户信息接口(提供用户相关操作)")
public class MemberController extends BaseController {
    @Autowired
    private IRepastService repastService;

    @PostMapping("/doLogin")
    @ApiOperation(value = "登录", notes = "用户执行登陆操作")
    @LoginLogAnnotation(operationType = "登陆操作", operationName = "普通用户登录")
    public ResultData doLogin(Member member){
        //调用api
        Member mb = repastService.doLogin(member);
        if (mb != null){
            return super.loginSuccess(mb);
        }
        return super.loginFailed();
    }

    @PostMapping("/updatename")
    public ResultData updateUsername(@RequestBody Member member){
        Integer integer = repastService.updateUsername(member);
        if(integer!=null){
            return super.loginFailed();
        }
        return null;
    }


}
