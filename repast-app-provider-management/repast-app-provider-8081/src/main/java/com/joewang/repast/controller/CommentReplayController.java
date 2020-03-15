package com.joewang.repast.controller;


import com.joewang.repast.model.CommentReplay;
import com.joewang.repast.service.CommentReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CommentReplayController
 * @Description:
 * @date: create in 2020/3/14 9:37
 * @since JDK 1.8
 */
@RestController
public class CommentReplayController {

    @Autowired
    private CommentReplayService commentReplayService;

    /**
     * @Description:
     *      通过评价id查询该评价的回复
     * @author: zxz
     * @date: 2020/3/14 9:30
     * @param: [token]
     * @return: java.util.List<com.aaa.zxz.repast.model.CommentReplay>
     */
    @PostMapping("/selectAllCommentReplay")
    public List<CommentReplay> selectAllCommentReplay(@RequestParam("commentId") Long commentId, @RequestParam("token") String token){
        return commentReplayService.selectAllCommentReplay(commentId, token);
    }

    /**
     * @Description:
     *      对回复表进行添加
     * @author: zxz
     * @date: 2020/3/14 9:36
     * @param: [commentReplay, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/insertCommentReplay")
    public Boolean insertCommentReplay(@RequestBody CommentReplay commentReplay, @RequestParam("token")String token){
        return commentReplayService.insertCommentReplay(commentReplay, token);
    }

}
