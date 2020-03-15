/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/15 15:30
 * @Description
 **/
package com.joewang.repast.service;

import com.joewang.repast.model.InterHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "memberinfo-interface", contextId = "InterHistoryClient")
public interface IInterHistory {
    /*
     * @author Zero
     * @description 查询用户的积分历史变化
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:40
     * @return java.util.List<com.aaa.lee.repast.model.IntegrationHisttroy>
     * @throws
     **/
    @PostMapping("/selectInterHistory")
    List<InterHistory> selectInterHistory(@RequestParam(value = "id") long id);

    /*
     * @author Zero
     * @description 新增记录用户积分变化
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:41
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertInterHistory")
     Boolean insertInterHistory(@RequestBody InterHistory interHistory);

    /*
     * @author Zero
     * @description 修改当前用户的积分历史变化 //TOOD 超级用户后台修改
     * @param  [integrationHisttroy]
     * @date 2020/3/13 21:43
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateInterHistory")
     Boolean updateInterHistory(@RequestBody InterHistory interHistory);
}
