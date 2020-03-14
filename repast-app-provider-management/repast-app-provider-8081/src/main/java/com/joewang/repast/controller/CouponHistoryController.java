package com.joewang.repast.controller;

import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.service.CouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 *      用户优惠券相关的Provider层Controller类
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@RestController
public class CouponHistoryController {
    @Autowired
    private CouponHistoryService service;

    /**
     * @desc: 
     * @author: Joe Wang 
     * @date: 2020/3/14 
     * @param: [memberid]
     * @return: java.util.List<java.util.HashMap> 
     */
    @PostMapping("/selectMyCoupon")
    public List<HashMap> selectMyCoupon(@RequestBody Long memberid){
        return service.selectMyCoupon(memberid);
    }
}
