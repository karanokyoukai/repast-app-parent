/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/13 20:36
 * @Description
 **/
package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.model.InterHistory;
import com.joewang.repast.service.InterHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterHistoryController extends BaseController {

    @Autowired
    private InterHistoryService interHistoryService;

    /*
     * @author Zero
     * @description 查询用户的积分历史变化
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:40
     * @return java.util.List<com.aaa.lee.repast.model.IntegrationHisttroy>
     * @throws
     **/
    @PostMapping("/selectInterHistory")
    public List<InterHistory> selectInterHistory(@RequestParam(value = "id") long id){
        return interHistoryService.selectInterHistory(id);
    }

    /*
     * @author Zero
     * @description 新增记录用户积分变化
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:41
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertInterHistory")
    public Boolean insertInterHistory(@RequestBody InterHistory interHistory){
        return interHistoryService.insertInterHistory(interHistory);
    }

    /*
     * @author Zero
     * @description 修改当前用户的积分历史变化 //TOOD 超级用户后台修改
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:43
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateInterHistory")
    public Boolean updateInterHistory(@RequestBody InterHistory interHistory){
        return interHistoryService.updateInterHistory(interHistory);
    }

}