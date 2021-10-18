/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/14 15:30
 * @Description
 **/
package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.RuleSetting;
import com.joewang.repast.service.IRuleSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "积分规则",tags ="积分规则接口(提供有关积分规则的操作)" )
public class RuleSettingController extends BaseController {

    @Autowired
    private IRuleSetting repastService;

    /*
     * @author Zero
     * @description 查询所有的积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:24
     * @return java.util.List<com.aaa.lee.repast.model.RuleSetting>
     * @throws
     **/
    @PostMapping("/selectAllRuleSetting")
    @ApiOperation(value = "查询所有", notes = "查询所有积分规则")
    public ResultData selectAllRuleSetting(){
        List<RuleSetting> list = repastService.selectAllRuleSetting();
        if (list!=null){
            return super.operationSuccess(list);
        }
       return super.operationFailed();
    };

    /*
     * @author Zero
     * @description 根据ID查询指定的积分规则
     * @param  [id]
     * @date 2020/3/15 18:40
     * @return com.joewang.repast.base.ResultData
     * @throws
     **/
    @PostMapping("/selectByKeyRuleSetting")
    @ApiOperation(value = "查询单个", notes = "查询指定积分规则")
    public ResultData selectByKeyRuleSetting(@RequestParam(value = "id")long id){
        RuleSetting ruleSetting = repastService.selectByKeyRuleSetting(id);
        if (ruleSetting!=null){
            return super.operationSuccess(ruleSetting);
        }
        return super.operationFailed();
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
    @ApiOperation(value = "新增", notes = "新增积分规则")
    public ResultData insertRuleSetting(@RequestBody RuleSetting ruleSetting){
        Boolean state = repastService.insertRuleSetting(ruleSetting);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    };

    /*
     * @author Zero
     * @description 修改积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:25
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateRuleSetting")
    @ApiOperation(value = "修改", notes = "修改积分规则")
    public ResultData updateRuleSetting(@RequestBody RuleSetting ruleSetting){
        Boolean state =repastService.updateRuleSetting(ruleSetting);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    };

    /*
     * @author Zero
     * @description 删除积分规则
     * @param  [ruleSetting]
     * @date 2020/3/14 15:26
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteRuleSetting")
    @ApiOperation(value = "删除", notes = "删除指定积分规则")
    public ResultData deleteRuleSetting(@RequestParam(value = "id") long id){
        Boolean state =repastService.deleteRuleSetting(id);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    };
}