package com.joewang.repast.controller;

import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import com.joewang.repast.service.MemberLevelService;
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
 * @Date 2020/3/14 17:14
 * @Version 1.0
 */
@RestController
public class MemberLevelController {
    @Autowired
    private MemberLevelService memberLevelService;
    /*
     * @Author junzheng Han
     * 查询会员信息
     * @Description @Date 17:17 2020/3/14
     * @Param [member]
     * @return com.joewang.repast.model.MemberLevel
     **/
    @PostMapping("/selectMemberLevel")
    public MemberLevel selectMemberLevel(@RequestBody Member member){
        return memberLevelService.selectMemberLevel(member);
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
    public List<MemberLevel> selectAllMemberLevel(){
        return memberLevelService.selectAllMemberLevel();
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
    public MemberLevel selectByKeyMemberLevel(@RequestParam(value = "id") Long id){
       return memberLevelService.selectByKeyMemberLevel(id);
    }

    /*
     * @author Zero
     * @description 新增会员等级信息
     * @param  [memberLevel]
     * @date 2020/3/15 19:00
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertMemberLevel")
    public Boolean insertMemberLevel(@RequestBody MemberLevel memberLevel){
        return memberLevelService.insertMemberLevel(memberLevel);
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
    public Boolean updateMemberLevel(@RequestBody MemberLevel memberLevel){
       return memberLevelService.updateMemberLevel(memberLevel);
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
    public Boolean deleteMemberLevel(@RequestParam(value = "id") long id){
        return memberLevelService.deleteMemberLevel(id);
    }
}
