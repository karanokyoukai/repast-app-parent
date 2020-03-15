package com.joewang.repast.service;


import com.joewang.repast.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zxz
 * @ClassName: ICommentService
 * @Description:
 * @date: create in 2020/3/13 20:20
 * @since JDK 1.8
 */
@FeignClient(value = "memberinfo-interface",contextId ="Comment" )
public interface ICommentService {

    /**
     * @Description:
     *      通过商品查询该商品的评价
     * @author: zxz
     * @date: 2020/3/13 20:18
     * @param: [productId, token]
     * @return: java.util.List<com.aaa.zxz.repast.model.Comment>
     */
    @PostMapping("/selectCommentByProductId")
    List<Comment> selectCommentByProductId(@RequestParam("productId") Long productId, @RequestParam("token") String token);

    /**
     * @Description:
     *      对商品或订单进行评价
     * @author: zxz
     * @date: 2020/3/13 19:26
     * @param: [orderId]
     * @return: java.util.List<com.aaa.zxz.repast.model.Comment>
     */
    @PostMapping("/insertCommentByProductId")
    Boolean insertCommentByProductId(@RequestBody Comment comment, @RequestParam("token") String token);
}
