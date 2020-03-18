package com.joewang.repast.controller;

import com.joewang.repast.model.OrderReturnApply;
import com.joewang.repast.service.OrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 *      退款相关provider层controller
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@RestController
public class OrderReturnApplyController {
    @Autowired
    private OrderReturnApplyService orderReturnApplyService;

    /**
     * @desc: 用户申请退款方法
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply]
     * @return: boolean
     */
    @PostMapping("/insertOrderReturnApply")
    public boolean insertOrderReturnApply(@RequestBody OrderReturnApply orderReturnApply){
        return orderReturnApplyService.insertOrderReturnApply(orderReturnApply);
    }
}
