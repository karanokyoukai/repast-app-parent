package com.joewang.repast.fallback;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.model.Collect;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.ICollectionService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: dawang
 * @date: Created in 2020/3/17 21:09
 * @version: 1.0
 * @description:
 */
@Component
public class CollectionFallBackFactory implements FallbackFactory<ICollectionService> {
    @Override
    public ICollectionService create(Throwable cause) {
        ICollectionService iCollectionService= new ICollectionService() {
            @Override
            public PageInfo<HashMap> selectAllCollectByMemberId(PageInfos<Long> pageInfos, String token) {
                System.out.println("查询熔断");
                return null;
            }

            @Override
            public Boolean insertShopCollect(Collect collect, String token) {
                System.out.println("新增店铺熔断");
                return null;
            }

            @Override
            public Boolean insertProductCollect(Collect collect, String token) {
                System.out.println("新增商品熔断");
                return null;
            }

            @Override
            public Boolean updateCollectShopStatus(Long shopId, String token) {
                System.out.println("取消店铺收藏熔断");
                return null;
            }

            @Override
            public Boolean updateCollectProductStatus(Long productId, String token) {
                System.out.println("取消商品收藏熔断");
                return null;
            }

            @Override
            public Boolean updateProductCollectStatus() {
                System.out.println("商品下架，收藏状态修改熔断");
                return null;
            }

            @Override
            public Boolean updateShopCollectStatus() {
                System.out.println("店铺关门，收藏状态修改熔断");
                return null;
            }
        };
        return null;
    }
}
