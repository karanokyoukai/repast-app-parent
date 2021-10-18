package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.InterHistoryMapper;
import com.joewang.repast.model.InterHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/13 16:16
 * @Description 积分历史变化,需要涉及多个模块多张表的共同协助
 **/
@Service
public class InterHistoryService extends BaseService<InterHistory> {

    @Autowired
    private InterHistoryMapper interHistoryMapper;

    @Override
    public Mapper<InterHistory> getMapper() {
        return interHistoryMapper;
    }

    /*
     * @author Zero
     * @description 查询用户的积分历史变化 根据member_ID进行查询
     *   分析得知 用户积分是所有的店铺通用   建议默认按照creat time 进行排序
     * @param  [InterHistory]
     * @date 2020/3/13 20:18
     * @return java.util.List<com.aaa.lee.repast.model.InterHistory>
     * @throws
     **/
    public List<InterHistory> selectInterHistory(long id){
        List<InterHistory> list=null;
        try {
            list = interHistoryMapper.selectInterHistory(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * @author Zero
     * @description 积分历史变化新增表
     * //TOOD 注意要涉及member的积分变化 即选择member表和InterHistory表甚至涉及入了rulesetting表
     * 暂时不需要考虑
     * @param  [InterHistory]
     * @date 2020/3/13 20:26
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean insertInterHistory(InterHistory interHistory){
        try {
            Integer result = super.save(interHistory);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description  修改积分历史变化表
     * //TOOD 多表涉及member和InterHistory 暂时不需要考虑
     * @param  [InterHistory]
     * @date 2020/3/13 20:32
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean updateInterHistory(InterHistory interHistory){
        try {
            Integer result = super.update(interHistory);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description 删除用户积分历史变化表
     * //暂时不需要进行考虑
     * @param  [InterHistory]
     * @date 2020/3/14 15:10
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean deleteInterHistory(long id){
        try {
            Integer result = super.deleteByPrimaryKey(id);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}