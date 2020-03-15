package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.MemberMapper;
import com.joewang.repast.model.Member;
import com.joewang.repast.redis.RedisService;
import com.joewang.repast.utils.IDUtil;
import com.joewang.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.KdcErrException;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;

/**
 * @description:
 *      用户个人信息相关service层
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Service
public class MemberService extends BaseService<Member> {
    @Autowired
    private MemberMapper memberMapper;


    @Override
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
        System.out.println(member.getUsername());
        System.out.println("-----------------------");
        Integer integer = memberMapper.updateUser(member.getOpenId(), member.getUsername());
        System.out.println(integer);
        return integer;
    }


    /**
     * @Description:
     *      退出登录，清空token
     * @author: zxz
     * @date: 2020/3/13 17:28
     * @param: []
     * @return: java.lang.Boolean
     */
    public Boolean loginOut(String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token) ){
                Integer result = memberMapper.updateToken(token);
                if (result > 0){
                    return true;
                }
            }
        }catch (Exception e){

    /*
     * @author Zero
     * @description 根据ID进行查询个人信息 两表联查member——level
     * @param  [member]
     * @date 2020/3/15 14:55
     * @return com.joewang.repast.model.Member
     * @throws
     **/
    public HashMap selectByKeyMember(long key){
        HashMap map = null;
        try {
             map = memberMapper.selectByKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
     * @author Zero
     * @description 修改个人信息
     * @param  [member]
     * @date 2020/3/15 14:58
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean updateMember(Member member){
        try {
            Integer result = super.update(member);
            if (result>0){
                return true;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

}
