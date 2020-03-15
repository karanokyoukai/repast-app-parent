package com.joewang.repast.service;

import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "memberinfo-interface", contextId = "MemberCouponLevel")
public interface IMemberLevel {
    /*
     * @Author junzheng Han
     * 查询会员信息
     * @Description @Date 17:19 2020/3/14
     * @Param [member]
     * @return com.joewang.repast.model.MemberLevel
     **/
    @PostMapping("/selectMemberLevel")
    MemberLevel selectMemberLevel(@RequestBody Member member);


    /*
     * @author Zero
     * @description 查询所有的等级信息表
     * @param  []
     * @date 2020/3/15 16:19
     * @return java.util.List<com.joewang.repast.model.MemberLevel>
     * @throws
     **/
    @PostMapping("/selectAllMemberLevel")
    List<MemberLevel> selectAllMemberLevel();

    /*
     * @author Zero
     * @description 查询指定的等级的信息
     * @param  [id]
     * @date 2020/3/15 16:20
     * @return com.joewang.repast.model.MemberLevel
     * @throws
     **/
    @PostMapping("/selectByKeyMemberLevel")
    MemberLevel selectByKeyMemberLevel(@RequestParam(value = "id") Long id);

    /*
     * @author Zero
     * @description 新增会员等级信息
     * @param  [memberLevel]
     * @date 2020/3/15 19:00
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/insertMemberLevel")
    Boolean insertMemberLevel(@RequestBody MemberLevel memberLevel);

    /*
     * @author Zero
     * @description 修改等级信息内容根据主键 进行修改
     * @param  [memberLevel]
     * @date 2020/3/15 16:23
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updateMemberLevel")
    Boolean updateMemberLevel(@RequestBody MemberLevel memberLevel);

    /*
     * @author Zero
     * @description 采用逻辑删除，即0有效，1无效
     * @param  [memberLevel]
     * @date 2020/3/15 16:26
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deleteMemberLevel")
    Boolean deleteMemberLevel(@RequestParam(value = "id") long id);
}
