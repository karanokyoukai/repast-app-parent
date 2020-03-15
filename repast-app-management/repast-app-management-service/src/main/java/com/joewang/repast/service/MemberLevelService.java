package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.MemberLevelMapper;
import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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

    /*
     * @author Zero
     * @description 查询所有的等级信息表
     * @param  []
     * @date 2020/3/15 16:19
     * @return java.util.List<com.joewang.repast.model.MemberLevel>
     * @throws
     **/
    public List<MemberLevel> selectAllMemberLevel(){
        List<MemberLevel> list = null ;
        try {
            list =  super.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * @author Zero
     * @description 查询指定的等级的信息
     * @param  [id]
     * @date 2020/3/15 16:20
     * @return com.joewang.repast.model.MemberLevel
     * @throws
     **/
    public MemberLevel selectByKeyMemberLevel(Long id){
        MemberLevel memberLevel =null;
        try {
            memberLevel = super.selectByPrimary(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberLevel;
    }

    /*
     * @author Zero
     * @description 新增会员等级
     * @param  [memberLevel]
     * @date 2020/3/15 18:56
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean insertMemberLevel(MemberLevel memberLevel){
        try {
            Integer result = super.save(memberLevel);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description 修改等级信息内容根据主键 进行修改
     * @param  [memberLevel]
     * @date 2020/3/15 16:23
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean updateMemberLevel(MemberLevel memberLevel){
        try {
            Integer result =super.update(memberLevel);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description 采用逻辑删除，即0有效，1无效
     * @param  [memberLevel]
     * @date 2020/3/15 16:26
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean deleteMemberLevel(long id){
        MemberLevel memberLevel=new MemberLevel();
        memberLevel.setDefaultStatus(1);
        memberLevel.setId(id);
        try {
            Integer result = super.update(memberLevel);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
