package com.joewang.repast.service;

import com.joewang.repast.model.CommentReplay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zxz
 * @ClassName: ICommentReplayService
 * @Description:
 * @date: create in 2020/3/14 12:54
 * @since JDK 1.8
 */
@FeignClient(value = "memberinfo-interface",contextId = "CommentReplay")
public interface ICommentReplayService {

    /**
     * @Description:
     *      通过评价id查询该评价的回复
     * @author: zxz
     * @date: 2020/3/14 9:30
     * @param: [token]
     * @return: java.util.List<com.aaa.zxz.repast.model.CommentReplay>
     */
    @PostMapping("/selectAllCommentReplay")
    List<CommentReplay> selectAllCommentReplay(@RequestParam("commentId") Long commentId, @RequestParam("token") String token);

    /**
     * @Description:
     *      对回复表进行添加
     * @author: zxz
     * @date: 2020/3/14 9:36
     * @param: [commentReplay, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/insertCommentReplay")
    Boolean insertCommentReplay(@RequestBody CommentReplay commentReplay, @RequestParam("token") String token);

}
