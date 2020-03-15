package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.CommentReplay;
import com.joewang.repast.service.ICommentReplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CommentReplayControllerr
 * @Description:
 * @date: create in 2020/3/14 13:05
 * @since JDK 1.8
 */
@RestController
@Api(value = "评价回复", tags = "评价回复接口(提供商品的评价回复)")
public class CommentReplayControllerr extends BaseController {

    @Autowired
    private ICommentReplayService commentReplayService;

    /**
     * @Description:
     *      通过评价id查询该评价的回复
     * @author: zxz
     * @date: 2020/3/14 13:07
     * @param: [commentId, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectAllCommentReplay")
    @ApiOperation(value = "查询商品评价回复", notes = "根据不同的商品查询不同的评价回复")
    public ResultData selectAllCommentReplay(@RequestParam("commentId") Long commentId, @RequestParam("token") String token){
        List<CommentReplay> commentReplays = commentReplayService.selectAllCommentReplay(commentId, token);
        if (commentReplays != null){
            return operationSuccess(commentReplays);
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      对回复表进行添加
     * @author: zxz
     * @date: 2020/3/14 13:09
     * @param: [commentReplay, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/insertCommentReplay")
    @ApiOperation(value = "新增商品评价回复", notes = "根据不同的商品新增不同的评价回复")
    public ResultData insertCommentReplay(@RequestBody CommentReplay commentReplay, @RequestParam("token")String token){
        Boolean resultBoolean = commentReplayService.insertCommentReplay(commentReplay, token);
        if (resultBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }



}
