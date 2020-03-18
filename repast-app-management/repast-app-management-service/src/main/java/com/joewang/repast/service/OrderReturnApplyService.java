package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.OrderReturnApplyMapper;
import com.joewang.repast.model.OrderReturnApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.joewang.repast.staticstatus.StaticCode.FORMAT_DATE;

/**
 * @description:
 *      退款相关服务service类
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@Service
public class OrderReturnApplyService extends BaseService<OrderReturnApply> {
    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;

    public Mapper<OrderReturnApply> getMapper() {
        return orderReturnApplyMapper;
    }

    /**
     * @desc: 用户申请退货服务
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply]
     * @return: java.lang.Boolean
     */
    public Boolean insertOrderReturnApply(OrderReturnApply orderReturnApply){
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
        Date date = null;
        try {
            date = df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderReturnApply.setStatus(0);
        orderReturnApply.setCreateTime(date);
        int insertResult = orderReturnApplyMapper.insertSelective(orderReturnApply);
        if (insertResult > 0){
            return true;
        }
        return false;
    }
}
