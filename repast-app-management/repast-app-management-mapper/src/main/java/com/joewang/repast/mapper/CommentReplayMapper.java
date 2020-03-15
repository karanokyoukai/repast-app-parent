package com.joewang.repast.mapper;

import com.joewang.repast.model.CommentReplay;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentReplayMapper extends Mapper<CommentReplay> {

    /**
     * @Description:
     *      查询所有回复并根据日期倒序排序
     * @author: zxz
     * @date: 2020/3/14 9:22
     * @param: []
     * @return: java.util.List<com.aaa.zxz.repast.model.CommentReplay>
     */
    List<CommentReplay> selectAllCommentReplay(Long commentId);
}