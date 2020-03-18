package com.joewang.repast.mapper;

import com.joewang.repast.model.OrderReturnApply;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderReturnApplyMapper extends Mapper<OrderReturnApply> {
    List<OrderReturnApply> selectOrderRetuenByShopId(Long shopId);
    OrderReturnApply selectMyOrderRetuenApplyByOrderId(Long orderId);
    Integer agreeOrderReturnById(OrderReturnApply orderReturnApply);
    Integer confirmOrderReturnById(OrderReturnApply orderReturnApply);
    Integer refuseOrderReturnById(OrderReturnApply orderReturnApply);
}