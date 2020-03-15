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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.joewang.repast.status.LoginStatus.LOGOUT_WRONG;

/**
 * @description:
 *      用户信息相关的consumer层类
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
    public ResultData updateUsername(Member member){
        Integer integer = repastService.updateUsername(member);
        if(integer!=null){
            return super.loginFailed();
        }
        return null;
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
    @ApiOperation(value = "退出登录", notes = "退出登录并清空token")
    public ResultData loginOut(@RequestParam("token") String token){
        Boolean resultBoolean = repastService.loginOut(token);
        if (resultBoolean){
            return super.operationSuccess();
        }
        return super.operationFailed(LOGOUT_WRONG.getMsg());
    }


}
