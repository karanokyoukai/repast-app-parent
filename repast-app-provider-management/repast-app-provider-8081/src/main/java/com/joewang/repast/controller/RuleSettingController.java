/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/14 15:15
 * @Description
 **/
package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.model.RuleSetting;
import com.joewang.repast.service.RuleSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleSettingController extends BaseController {

    @Autowired
    private RuleSettingService ruleSettingService;

    /*
     * @author Zero
     * @description 查询所有的积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:24
     * @return java.util.List<com.aaa.lee.repast.model.RuleSetting>
     * @throws
     **/
    @PostMapping("/selectAllRuleSetting")
    public List<RuleSetting> selectAllRuleSetting(){
        return ruleSettingService.selectAllRuleSetting();
    }

    /*
     * @author Zero
     * @description 根据主键进行查询积分规则
     * @param  [id]
     * @date 2020/3/15 18:35
     * @return com.joewang.repast.model.RuleSetting
     * @throws
     **/
    @PostMapping("/selectByKeyRuleSetting")
    public RuleSetting selectByKeyRuleSetting(@RequestParam(value = "id")long id){
        return ruleSettingService.selectByKeyRuleSetting(id);
    }
    /*
     * @author Zero
     * @description 新增积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:25
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertRuleSetting")
    public Boolean insertRuleSetting(@RequestBody RuleSetting ruleSetting){
        return ruleSettingService.insertRuleSetting(ruleSetting);
    }

    /*
     * @author Zero
     * @description 修改积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:25
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateRuleSetting")
    public Boolean updateRuleSetting(@RequestBody RuleSetting ruleSetting){
        return ruleSettingService.updateRuleSetting(ruleSetting);
    }

    /*
     * @author Zero
     * @description 删除积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:26
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteRuleSetting")
    public Boolean deleteRuleSetting(@RequestParam(value = "id") long id){
        return ruleSettingService.deleteRuleSetting(id);
    }
}