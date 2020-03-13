package com.joewang.repast.controller;

import com.joewang.repast.model.CouponHistory;
import com.joewang.repast.service.CouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@RestController
public class CouponHistoryController {
    @Autowired
    private CouponHistoryService service;

    @PostMapping("/selectMyCoupon")
    public List<CouponHistory> selectMyCoupon(@RequestBody Long memberid){
        return service.selectMyCoupon(memberid);
    }
}
