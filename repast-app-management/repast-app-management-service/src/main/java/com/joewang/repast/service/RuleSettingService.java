/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/13 16:16
 * @Description 积分成长规则表，初步判断只涉及单表的增删改查和其他无关
 **/
package com.joewang.repast.service;


import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.RuleSettingMapper;
import com.joewang.repast.model.RuleSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class RuleSettingService extends BaseService<RuleSetting> {

    @Autowired
    private RuleSettingMapper ruleSettingMapper;

    @Override
    public Mapper getMapper() {
        return ruleSettingMapper;
    }

    /*
     * @author Zero
     * @description 查询所有的积分成长规则 积分通用不和任何店铺进行绑定
     * @param  [ruleSetting]
     * @date 2020/3/14 9:59
     * @return java.util.List<com.aaa.lee.repast.model.RuleSetting>
     * @throws
     **/
    public List<RuleSetting> selectAllRuleSetting(){
        List<RuleSetting> list = null;
        try {
            list = super.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * @author Zero
     * @description 根据主键id进行查询想要的积分规则进行匹配
     * @param  [id]
     * @date 2020/3/15 18:24
     * @return com.joewang.repast.model.RuleSetting
     * @throws
     **/
    public RuleSetting selectByKeyRuleSetting(long id){
        RuleSetting ruleSetting = null;
        try {
            ruleSetting = super.selectByPrimary(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleSetting;
    }

    /*
     * @author Zero
     * @description 新增积分成长规则
     * @param  [ruleSetting]
     * @date 2020/3/14 14:58
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean insertRuleSetting(RuleSetting ruleSetting){
        try {
            Integer result = super.save(ruleSetting);
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
     * @description 修改积分成长规则
     * @param  [ruleSetting]
     * @date 2020/3/14 14:58
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean updateRuleSetting(RuleSetting ruleSetting){
        try {
            Integer result = super.update(ruleSetting);
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
     * @description  删除积分成长规则 请谨慎使用 不建议使用
     * @param  [ruleSetting]
     * @date 2020/3/14 14:59
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean deleteRuleSetting(long id){
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