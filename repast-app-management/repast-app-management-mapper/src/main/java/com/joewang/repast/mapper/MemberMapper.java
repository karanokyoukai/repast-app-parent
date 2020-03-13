package com.joewang.repast.mapper;

import com.joewang.repast.model.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MemberMapper extends Mapper<Member> {
    Member selectMemberByOpenID(String openID);
    /*
     * @Author junzheng Han
     * 修改昵称方法
     * @Description @Date 17:37 2020/3/13
     * @Param
     * @return
     **/
    Integer updateUser(@Param("openID") String openID,@Param("username") String username);
}