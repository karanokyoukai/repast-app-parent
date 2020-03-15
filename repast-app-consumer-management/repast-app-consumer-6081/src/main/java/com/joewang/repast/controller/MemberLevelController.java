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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    /*
     * @author Zero
     * @description 查询所有的等级信息表
     * @param  []
     * @date 2020/3/15 16:19
     * @return java.util.List<com.joewang.repast.model.MemberLevel>
     * @throws
     **/
    @PostMapping("/selectAllMemberLevel")
    @ApiOperation(value = "查询", notes = "查询所有等级信息")
    public ResultData selectAllMemberLevel(){
        List<MemberLevel> list= iMemberLevel.selectAllMemberLevel();
        if (list!=null){
            return super.operationSuccess(list);
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 查询指定的等级的信息
     * @param  [id]
     * @date 2020/3/15 16:20
     * @return com.joewang.repast.model.MemberLevel
     * @throws
     **/
    @PostMapping("/selectByKeyMemberLevel")
    @ApiOperation(value = "查询", notes = "查询指定等级信息")
    public ResultData selectByKeyMemberLevel(@RequestParam(value = "id") Long id){
        MemberLevel memberLevel = iMemberLevel.selectByKeyMemberLevel(id);
        if (memberLevel!=null){
            return super.operationSuccess(memberLevel);
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 新增会员等级
     * @param  [memberLevel]
     * @date 2020/3/15 19:05
     * @return com.joewang.repast.base.ResultData
     * @throws
     **/
    @PostMapping("/insertMemberLevel")
    @ApiOperation(value = "新增", notes = "新增等级信息")
    public ResultData insertMemberLevel(@RequestBody MemberLevel memberLevel){
        Boolean state = iMemberLevel.insertMemberLevel(memberLevel);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 修改等级信息内容根据主键 进行修改
     * @param  [memberLevel]
     * @date 2020/3/15 16:23
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateMemberLevel")
    @ApiOperation(value = "修改", notes = "修改等级信息")
    public ResultData updateMemberLevel(@RequestBody MemberLevel memberLevel){
        Boolean state = iMemberLevel.updateMemberLevel(memberLevel);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /*
     * @author Zero
     * @description 采用逻辑删除，即0有效，1无效
     * @param  [memberLevel]
     * @date 2020/3/15 16:26
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteMemberLevel")
    @ApiOperation(value = "删除", notes = "删除等级信息")
    public ResultData deleteMemberLevel(@RequestParam(value = "id") long id){
        Boolean state = iMemberLevel.deleteMemberLevel(id);
        if (state){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
}
