package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.CommentReplayMapper;
import com.joewang.repast.model.CommentReplay;
import com.joewang.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CommentReplayService
 * @Description:
 * @date: create in 2020/3/14 9:26
 * @since JDK 1.8
 */
@Service
public class CommentReplayService extends BaseService<CommentReplay> {

    @Autowired
    private CommentReplayMapper commentReplayMapper;


    @Override
    public Mapper<CommentReplay> getMapper() {
        return commentReplayMapper;
    }

    /**
     * @Description:
     *      通过评价id查询该评价的回复
     * @author: zxz
     * @date: 2020/3/14 9:30
     * @param: [token]
     * @return: java.util.List<com.aaa.zxz.repast.model.CommentReplay>
     */
    public List<CommentReplay> selectAllCommentReplay(Long commentId,String token){

        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token)){
                List<CommentReplay> commentReplays = commentReplayMapper.selectAllCommentReplay(commentId);
                if (commentReplays != null) {
                    return commentReplays;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:
     *      对回复表进行添加
     * @author: zxz
     * @date: 2020/3/14 9:36
     * @param: [commentReplay, token]
     * @return: java.lang.Boolean
     */
    public Boolean insertCommentReplay(CommentReplay commentReplay,String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token)){
                Integer result = commentReplayMapper.insertSelective(commentReplay);
                if (result > 0 ) {
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
