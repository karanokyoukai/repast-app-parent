package com.joewang.repast.mapper;

import com.joewang.repast.model.Member;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MemberMapper extends Mapper<Member> {
    Member selectMemberByOpenID(String openID);
}