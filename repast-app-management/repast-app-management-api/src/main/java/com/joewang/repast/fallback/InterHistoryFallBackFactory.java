/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/18 14:40
 * @Description
 **/
package com.joewang.repast.fallback;

import com.joewang.repast.model.InterHistory;
import com.joewang.repast.service.IInterHistory;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class InterHistoryFallBackFactory implements FallbackFactory<IInterHistory> {
    @Override
    public IInterHistory create(Throwable throwable) {
        IInterHistory interHistory = new IInterHistory() {
            @Override
            public List<InterHistory> selectInterHistory(long id) {
                return null;
            }

            @Override
            public Boolean insertInterHistory(InterHistory interHistory) {
                return null;
            }

            @Override
            public Boolean updateInterHistory(InterHistory interHistory) {
                return null;
            }
        };
        return null;
    }
}