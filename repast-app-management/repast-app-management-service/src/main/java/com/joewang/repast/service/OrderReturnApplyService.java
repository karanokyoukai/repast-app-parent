package com.joewang.repast.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.OrderReturnApplyMapper;
import com.joewang.repast.model.OrderReturnApply;
import com.joewang.repast.page.PageInfos;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /**
     * @desc: 根据订单id查询退款申请详情
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderId]
     * @return: com.joewang.repast.model.OrderReturnApply
     */
    public OrderReturnApply selectMyOrderRetuenApplyByOrderId(Long orderId){
        return orderReturnApplyMapper.selectMyOrderRetuenApplyByOrderId(orderId);
    }

    /**
     * @desc: 根据店铺id查询退款申请, 实现分页
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<com.joewang.repast.model.OrderReturnApply>
     */
    public PageInfo<OrderReturnApply> selectOrderReturnByShopId(PageInfos<Long> pageInfos){
        PageHelper.startPage(pageInfos.getPageNum(), pageInfos.getPageSize());
        List<OrderReturnApply> list = orderReturnApplyMapper.selectOrderRetuenByShopId(pageInfos.getT());
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * @desc: 确认通过申请，开始退货流程
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    public Boolean agreeOrderReturn(OrderReturnApply orderReturnApply){
        Integer ifSuccess = orderReturnApplyMapper.agreeOrderReturnById(orderReturnApply);
        if (ifSuccess > 0){
            return true;
        }
        return false;
    }

    /**
     * @desc: 同意退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    public Boolean confirmOrderReturnById(OrderReturnApply orderReturnApply){
        Integer ifSuccess = orderReturnApplyMapper.confirmOrderReturnById(orderReturnApply);
        if (ifSuccess > 0){
            return true;
        }
        return false;
    }

    /**
     * @desc: 拒绝用户退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    public Boolean refuseOrderReturnById(OrderReturnApply orderReturnApply){
        Integer ifSuccess = orderReturnApplyMapper.refuseOrderReturnById(orderReturnApply);
        if (ifSuccess > 0){
            return true;
        }
        return false;
    }
}
