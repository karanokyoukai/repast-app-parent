package com.joewang.repast.service;

import com.joewang.repast.model.OrderReturnApply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 *      退款相关接口
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@FeignClient(value = "memberinfo-interface",contextId = "OrderReturnApplyInterface")
public interface IOrderReturnApply {

    /**
     * @desc: 用户申请退款
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply, token]
     * @return: boolean
     */
    @PostMapping("/insertOrderReturnApply")
    boolean insertOrderReturnApply(@RequestBody OrderReturnApply orderReturnApply);
}
