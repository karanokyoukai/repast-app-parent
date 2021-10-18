/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/18 14:47
 * @Description
 **/
package com.joewang.repast.fallback;

import com.joewang.repast.model.Member;
import com.joewang.repast.model.MemberLevel;
import com.joewang.repast.service.IMemberLevel;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MemberLevelFallBackFactory implements FallbackFactory<IMemberLevel> {
    @Override
    public IMemberLevel create(Throwable throwable) {
        IMemberLevel memberLevel= new IMemberLevel() {
            @Override
            public MemberLevel selectMemberLevel(Member member) {
                return null;
            }

            @Override
            public List<MemberLevel> selectAllMemberLevel() {
                return null;
            }

            @Override
            public MemberLevel selectByKeyMemberLevel(Long id) {
                return null;
            }

            @Override
            public Boolean insertMemberLevel(MemberLevel memberLevel) {
                return null;
            }

            @Override
            public Boolean updateMemberLevel(MemberLevel memberLevel) {
                return null;
            }

            @Override
            public Boolean deleteMemberLevel(long id) {
                return null;
            }
        };
        return null;
    }
}