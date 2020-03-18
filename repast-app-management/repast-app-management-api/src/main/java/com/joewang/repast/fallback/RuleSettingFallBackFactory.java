/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/18 14:38
 * @Description
 **/
package com.joewang.repast.fallback;

import com.joewang.repast.model.RuleSetting;
import com.joewang.repast.service.IRuleSetting;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleSettingFallBackFactory implements FallbackFactory<IRuleSetting> {
    @Override
    public IRuleSetting create(Throwable throwable) {
        IRuleSetting ruleSetting=new IRuleSetting() {
            @Override
            public List<RuleSetting> selectAllRuleSetting() {
                return null;
            }

            @Override
            public RuleSetting selectByKeyRuleSetting(long id) {
                return null;
            }

            @Override
            public Boolean insertRuleSetting(RuleSetting ruleSetting) {
                return null;
            }

            @Override
            public Boolean updateRuleSetting(RuleSetting ruleSetting) {
                return null;
            }

            @Override
            public Boolean deleteRuleSetting(long id) {
                return null;
            }
        };
        return null;
    }
}