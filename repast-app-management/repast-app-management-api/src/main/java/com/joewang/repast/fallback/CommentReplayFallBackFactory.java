package com.joewang.repast.fallback;

import com.joewang.repast.model.CommentReplay;
import com.joewang.repast.service.ICommentReplayService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zxz
 * @ClassName: CommentReplayFallBackFactory
 * @Description:
 * @date: create in 2020/3/14 12:54
 * @since JDK 1.8
 */
@Component
public class CommentReplayFallBackFactory implements FallbackFactory<ICommentReplayService> {
    @Override
    public ICommentReplayService create(Throwable throwable) {
        ICommentReplayService iCommentReplayService = new ICommentReplayService() {
            @Override
            public List<CommentReplay> selectAllCommentReplay(Long commentId, String token) {
                System.out.println("熔断查询回复方法");
                return null;
            }

            @Override
            public Boolean insertCommentReplay(CommentReplay commentReplay, String token) {
                System.out.println("熔断新增回复方法");
                return null;
            }
        };
        return null;
    }
}
