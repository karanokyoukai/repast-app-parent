package com.joewang.repast.mapper;

import com.joewang.repast.model.Comment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {

    /**
     * @Description:
     *      通过商品id查询该商品的评价
     * @author: zxz
     * @date: 2020/3/13 19:35
     * @param: [productId]
     * @return: java.util.List<com.aaa.zxz.repast.model.Comment>
     */
    List<Comment> selectCommentByProductId(Long productId);
}