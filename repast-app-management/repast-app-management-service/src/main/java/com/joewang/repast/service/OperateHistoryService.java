package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.OperateHistoryMapper;
import com.joewang.repast.model.OperateHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zxz
 * @ClassName: OperateHistoryService
 * @Description:
 * @date: create in 2020/3/20 19:32
 * @since JDK 1.8
 */
@Service
public class OperateHistoryService extends BaseService<OperateHistory> {

    @Autowired
    private OperateHistoryMapper operateHistoryMapper;

    @Override
    public Mapper<OperateHistory> getMapper() {
        return operateHistoryMapper;
    }

    /**
     * @desc: 订单日志保存
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [operateHistory]
     * @return: java.lang.Boolean
     */
    public Boolean addOrderLog(OperateHistory operateHistory){

        try {
            Integer result = operateHistoryMapper.insert(operateHistory);
            if (result > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
}
