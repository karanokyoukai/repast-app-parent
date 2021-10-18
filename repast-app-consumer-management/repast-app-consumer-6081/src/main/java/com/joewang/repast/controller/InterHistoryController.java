/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/13 21:46
 * @Description
 **/
package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.InterHistory;
import com.joewang.repast.service.IInterHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "积分历史",tags ="积分历史接口(提供有关积分的操作)" )
public class InterHistoryController extends BaseController {
    @Autowired
    private IInterHistory repastService;
    /*
     * @author Zero
     * @description 查询用户的积分历史变化
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:40
     * @return java.util.List<com.aaa.lee.repast.model.IntegrationHisttroy>
     * @throws
     **/
    @PostMapping("/selectInterHistory")
    @ApiOperation(value = "查询", notes = "查询用户积分历史")
    public ResultData selectInterHistory(@RequestParam(value = "id") long id){
        List<InterHistory> list =repastService.selectInterHistory(id);
        if (list!=null){
            return super.operationSuccess(list);
        }
        return super.operationFailed();
    };

    /*
     * @author Zero
     * @description 新增记录用户积分变化
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:41
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertIntegrationHisttroy")
    @ApiOperation(value = "新增", notes = "新增积分历史")
    public ResultData insertInterHistory(@RequestBody InterHistory interHistory){
        Boolean state = repastService.insertInterHistory(interHistory);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    };

    /*
     * @author Zero
     * @description 修改当前用户的积分历史变化 //TOOD 超级用户后台修改
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:43
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateIntegrationHisttroy")
    @ApiOperation(value = "修改", notes = "修改积分历史")
    public ResultData updateInterHistory(@RequestBody InterHistory interHistory){
        Boolean state =repastService.updateInterHistory(interHistory);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    };
}