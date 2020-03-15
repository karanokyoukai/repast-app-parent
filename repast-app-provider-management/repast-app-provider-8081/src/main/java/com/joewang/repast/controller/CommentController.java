package com.joewang.repast.controller;


import com.joewang.repast.model.Comment;
import com.joewang.repast.service.CommentService;
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
 * @date: create in 2020/3/13 20:12
 * @since JDK 1.8
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @Description:
     *      通过商品查询该商品的评价
     * @author: zxz
     * @date: 2020/3/13 20:18
     * @param: [productId, token]
     * @return: java.util.List<com.aaa.zxz.repast.model.Comment>
     */
    @PostMapping("/selectCommentByProductId")
    public List<Comment> selectCommentByProductId(@RequestParam("productId") Long productId, @RequestParam("token") String token){
        return commentService.selectCommentByProductId(productId, token);
    }

    /**
     * @Description:
     *      对商品或订单进行评价
     * @author: zxz
     * @date: 2020/3/13 19:26
     * @param: [comment, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/insertCommentByProductId")
    public Boolean insertCommentByProductId(@RequestBody Comment comment,@RequestParam("token") String token){
        return commentService.insertCommentByProductId(comment, token);
    }


}
