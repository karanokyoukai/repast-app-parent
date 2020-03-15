package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.MemberLevelMapper;
import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName MemberLevelService
 * @Description TODO
 * @Author 13061
 * @Date 2020/3/14 17:07
 * @Version 1.0
 */
@Service
public class MemberLevelService extends BaseService<MemberLevel> {
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    @Override
    public Mapper<MemberLevel> getMapper() {
        return memberLevelMapper;
    }
    /*
     * @Author junzheng Han
     * 查询会员信息
     * @Description @Date 17:13 2020/3/14
     * @Param [member]
     * @return com.joewang.repast.model.MemberLevel
     **/
    public MemberLevel selectMemberLevel(Member member){
        return memberLevelMapper.selectMemberLevel(member.getOpenId());
    }



}
