/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/18 14:39
 * @Description
 **/
package com.joewang.repast.fallback;

import com.joewang.repast.model.ReceiveAddress;
import com.joewang.repast.service.IReceiveAddress;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ReceiveAddressFallBackFactory implements FallbackFactory<IReceiveAddress> {
    @Override
    public IReceiveAddress create(Throwable throwable) {
        IReceiveAddress receiveAddress=new IReceiveAddress() {
            @Override
            public List<ReceiveAddress> selectAllReceiveAddress(long id) {
                return null;
            }

            @Override
            public ReceiveAddress selectByKeyReceiveAddress(long id) {
                return null;
            }

            @Override
            public Boolean updateReceiveAddress(ReceiveAddress receiveAddress) {
                return null;
            }

            @Override
            public Boolean insertReceiveAddress(ReceiveAddress receiveAddress) {
                return null;
            }

            @Override
            public Boolean deleteReceiveAddress(long id) {
                return null;
            }
        };
        return null;
    }
}