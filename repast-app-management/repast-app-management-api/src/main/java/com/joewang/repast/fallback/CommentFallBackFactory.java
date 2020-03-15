package com.joewang.repast.fallback;


import com.joewang.repast.model.Comment;
import com.joewang.repast.service.ICommentService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CommentFallBackFactory
 * @Description:
 * @date: create in 2020/3/13 20:21
 * @since JDK 1.8
 */
@Component
public class CommentFallBackFactory implements FallbackFactory<Comment> {
    @Override
    public Comment create(Throwable throwable) {
        ICommentService iCommentService = new ICommentService() {
            @Override
            public List<Comment> selectCommentByProductId(Long productId, String token) {
                System.out.println("熔断查询评价");
                return null;
            }

            @Override
            public Boolean insertCommentByProductId(Comment comment, String token) {
                System.out.println("熔断新增评价");
                return null;
            }
        };
        return null;
    }
}
