package com.joewang.repast.mapper;

import com.joewang.repast.model.MemberLevel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface MemberLevelMapper extends Mapper<MemberLevel> {
    MemberLevel selectMemberLevel(String openID);
}