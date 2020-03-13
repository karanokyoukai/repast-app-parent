package com.joewang.repast.service;

import com.joewang.repast.model.CouponHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@FeignClient(value = "memberinfo-interface", contextId = "MemberCouponClient")
public interface IMemberCoupon {
    @PostMapping("/selectMyCoupon")
    List<HashMap> selectMyCoupon(@RequestBody Long memberid);
}
