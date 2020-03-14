package com.joewang.repast.controller;

import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import com.joewang.repast.service.IMemberLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberLevelController
 * @Description TODO
 * @Author 13061
 * @Date 2020/3/14 17:22
 * @Version 1.0
 */
@RestController
@Api(value = "会员信息",tags = "会员信息接口（提供会员信息查询）")
public class MemberLevelController extends BaseController {
    @Autowired
    private IMemberLevel iMemberLevel;
    /*
     * @Author junzheng Han
     * 查询会员信息
     * @Description @Date 17:27 2020/3/14
     * @Param [member]
     * @return com.joewang.repast.base.ResultData
     **/
    @PostMapping("/selectMemberLevel")
    @ApiOperation(value = "查询", notes = "查询会员信息")
    public ResultData selectMemberLevel(Member member){
        MemberLevel memberLevel = iMemberLevel.selectMemberLevel(member);
        System.out.println(memberLevel.toString());
        if (memberLevel!=null){
            return super.operationSuccess(memberLevel);
        }else {
            return super.operationFailed();
        }
    }
}
