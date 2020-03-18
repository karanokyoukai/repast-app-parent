package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.OrderReturnApply;
import com.joewang.repast.service.IOrderReturnApply;
import com.joewang.repast.utils.DecideToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 *      退款相关consumer层controller
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@RestController
@Api(value = "退款相关", tags = "退款业务相关接口")
public class OrderReturnApplyController extends BaseController {
    @Autowired
    private IOrderReturnApply iOrderReturnApply;

    /**
     * @desc: 用户申请退款
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply, token]
     * @return: com.joewang.repast.base.ResultData
     */
    @PostMapping("/insertOrderReturnApply")
    @ApiOperation(value = "退款申请", notes = "用户发起退款申请操作")
    public ResultData insertOrderReturnApply(OrderReturnApply orderReturnApply, @RequestParam("token") String token){
        if (DecideToken.decideToken(token)){
            Boolean ifSuccess = iOrderReturnApply.insertOrderReturnApply(orderReturnApply);
            if (ifSuccess){
                return operationSuccess("申请成功");
            }
            return operationFailed("申请失败");
        }
        return operationFailed("请重新登录");
    }
}
