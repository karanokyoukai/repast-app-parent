package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.MemberMapper;
import com.joewang.repast.model.Member;
import com.joewang.repast.redis.RedisService;
import com.joewang.repast.utils.IDUtil;
import com.joewang.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Service
public class MemberService extends BaseService<Member> {
    @Autowired
    private MemberMapper memberMapper;


    public Mapper<Member> getMapper() {
        return memberMapper;
    }

    /**
     * @desc: 登录方法
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [member]
     * @return: java.lang.Boolean
     */
    public Member doLogin(Member member){
        //判断用户是否为null
        if (member != null && member.getOpenId() != null && StringUtil.isNotEmpty(member.getOpenId())){
            //再次通过openid判断是否是新用户
            System.out.println(member.getOpenId());
            Member mb = memberMapper.selectMemberByOpenID(member.getOpenId());
            System.out.println(mb);
            String token = IDUtil.getUUID() + member.getOpenId();
            try {
                //判断查询到的用户是否为空
                if (mb != null){
                    //说明不是新用户，之前一定登陆过，只修改token即可
                    mb.setToken(token);
                    Integer updateResult = super.update(mb);
                    //判断是否更新成功
                    if (updateResult > 0){
                        return mb;
                    }
                }else {
                    //mb为空，是新用户，插入
                    member.setToken(token);
                    Integer insertResult = super.save(member);
                    //判断是否新增成功
                    if (insertResult > 0){
                        return member;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /*
     * @Author junzheng Han
     * 修改昵称方法
     * @Description @Date 18:08 2020/3/13
     * @Param [member]
     * @return java.lang.Integer
     **/
    public Integer updateUsername(Member member){
        return memberMapper.updateUser(member.getOpenId(),member.getUsername());
    }
}
