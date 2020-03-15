package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.CommentMapper;
import com.joewang.repast.model.Comment;
import com.joewang.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CommentService
 * @Description:
 * @date: create in 2020/3/13 19:18
 * @since JDK 1.8
 */
@Service
public class CommentService extends BaseService<Comment> {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public Mapper<Comment> getMapper() {
        return commentMapper;
    }

    /**
     * @Description:
     *      通过的商品查询该商品的评价
     * @author: zxz
     * @date: 2020/3/13 19:26
     * @param: [productId, token]
     * @return: java.util.List<com.aaa.zxz.repast.model.Comment>
     */
    public List<Comment> selectCommentByProductId(Long productId,String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token)){
                List<Comment> comments = commentMapper.selectCommentByProductId(productId);
                if (comments != null){
                    return comments;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:
     *      对商品或订单进行评价
     * @author: zxz
     * @date: 2020/3/13 19:26
     * @param: [comment, token]
     * @return: java.lang.Boolean
     */
    public Boolean insertCommentByProductId(Comment comment,String token){
        try {
            if (token != null && !token.equals("")  && StringUtil.isNotEmpty(token)){
                Integer result = commentMapper.insertSelective(comment);
                if (result > 0 ){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
