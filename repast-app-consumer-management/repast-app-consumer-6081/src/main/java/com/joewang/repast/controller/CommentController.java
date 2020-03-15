package com.joewang.repast.controller;


import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Comment;
import com.joewang.repast.service.ICommentService;
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
 * @ClassName: CommentController
 * @Description:
 * @date: create in 2020/3/13 20:23
 * @since JDK 1.8
 */
@RestController
@Api(value = "评价", tags = "评价接口(提供商品的评价)")
public class CommentController extends BaseController {

    @Autowired
    private ICommentService commentService;

    /**
     * @Description:
     *      通过商品查询该商品的评价
     * @author: zxz
     * @date: 2020/3/13 20:18
     * @param: [productId, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/selectCommentByProductId")
    @ApiOperation(value = "查询商品评价", notes = "根据不同的商品查询不同的评价")
    public ResultData selectCommentByProductId(@RequestParam("productId") Long productId, @RequestParam("token") String token){
        List<Comment> comments = commentService.selectCommentByProductId(productId, token);
        if (comments != null){
            return operationSuccess(comments);
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      通过商品进行评价
     * @author: zxz
     * @date: 2020/3/14 13:00
     * @param: [comment, token]
     * @return: com.aaa.zxz.repast.base.ResultData
     */
    @PostMapping("/insertCommentByProductId")
    @ApiOperation(value = "新增商品评价", notes = "根据不同的商品新增不同的评价")
    public ResultData insertCommentByProductId(@RequestBody Comment comment, @RequestParam("token") String token){
        Boolean resultBoolean = commentService.insertCommentByProductId(comment, token);
        if (resultBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }

}
