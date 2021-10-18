/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/15 15:29
 * @Description
 **/
package com.joewang.repast.service;

import com.joewang.repast.model.RuleSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "memberinfo-interface", contextId = "IRuleSettingClient")
public interface IRuleSetting {
    /*
     * @author Zero
     * @description 查询所有的积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:24
     * @return java.util.List<com.aaa.lee.repast.model.RuleSetting>
     * @throws
     **/
    @PostMapping("/selectAllRuleSetting")
    List<RuleSetting> selectAllRuleSetting();

    /*
     * @author Zero
     * @description 根据主键进行查询积分规则
     * @param  [id]
     * @date 2020/3/15 18:35
     * @return com.joewang.repast.model.RuleSetting
     * @throws
     **/
    @PostMapping("/selectByKeyRuleSetting")
    RuleSetting selectByKeyRuleSetting(@RequestParam(value = "id")long id);

    /*
     * @author Zero
     * @description 新增积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:25
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertRuleSetting")
    Boolean insertRuleSetting(@RequestBody RuleSetting ruleSetting);

    /*
     * @author Zero
     * @description 修改积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:25
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateRuleSetting")
    Boolean updateRuleSetting(@RequestBody RuleSetting ruleSetting);

    /*
     * @author Zero
     * @description 删除积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:26
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteRuleSetting")
    Boolean deleteRuleSetting(@RequestParam(value = "id") long id);
}
