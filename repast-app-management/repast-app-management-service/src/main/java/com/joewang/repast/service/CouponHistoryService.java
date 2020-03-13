package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.CouponHistoryMapper;
import com.joewang.repast.model.CouponHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@Service
public class CouponHistoryService extends BaseService<CouponHistory> {
    @Autowired
    private CouponHistoryMapper couponHistoryMapper;

    public Mapper<CouponHistory> getMapper() {
        return couponHistoryMapper;
    }

    public List<HashMap> selectMyCoupon(Long memberid){
        return couponHistoryMapper.selectMemberCoupon(memberid);
    }
}
