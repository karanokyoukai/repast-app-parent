package com.joewang.repast.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.CollectMapper;
import com.joewang.repast.model.Collect;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.utils.DecideToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: dawang
 * @date: Created in 2020/3/14 23:39
 * @version: 1.0
 * @description:收藏表
 */
@Service
public class CollectService extends BaseService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Mapper getMapper() {
        return collectMapper;
    }

    /**
     * @author dawang
     * @date 2020/3/15 0:25
     * @parameter [pageInfos, token]
     * @return com.joewang.repast.page.PageInfos
     * @description 根据用户ID分页查询收藏
     */
    public PageInfo<HashMap> selectAllCollectByMemberId(PageInfos<Long> pageInfos,String token) {
        Boolean b=DecideToken.decideToken(token);
        if (b){
            PageHelper.startPage(pageInfos.getPageNum(), pageInfos.getPageSize());
            List<HashMap> collectList = collectMapper.selectCollectByMemberId(pageInfos.getT());
            PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(collectList);
            return pageInfo;
        }
       return null;
    }
    /**
     * @author dawang
     * @date 2020/3/15 12:04
     * @parameter [collect, token]
     * @return java.lang.Boolean
     * @description 收藏店铺
     */
    public Boolean insertShopCollect(Collect collect,String token){
        Boolean b=DecideToken.decideToken(token);
        collect.setCollectShopStatus(0);
        if (b){
            Long shopId=collect.getShopId();
            Integer shopStatus=collectMapper.selectShopStatus(shopId);
            if (shopStatus==1&&shopStatus.equals(1)&&shopStatus!=null){
                Integer integer=collectMapper.insert(collect);
                if(integer>0){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * @author dawang
     * @date 2020/3/16 14:41
     * @parameter [collect, token]
     * @return java.lang.Boolean
     * @description 收藏商品
     */
    public Boolean insertProductCollect(Collect collect,String token){
        Boolean b=DecideToken.decideToken(token);
        collect.setCollectProductStatus(0);
        if (b){
            Long productId=collect.getProductId();
            Integer productStatus=collectMapper.selectProductPublishStatus(productId);
            if (productStatus==1&&productStatus!=null&&productStatus.equals(1)){
                Integer integer=collectMapper.insert(collect);
                if(integer>0){
                    return true;
                }
            }
        }
        return false;
    }
    public Boolean updateCollectShopStatus(Long shopId,String token){
        /**
         * @author dawang
         * @date 2020/3/15 12:38
         * @parameter [shopId, token]
         * @return java.lang.Boolean
         * @description 取消店铺收藏
         */
        Boolean b=DecideToken.decideToken(token);
        if (b){
            Integer integer=collectMapper.updateCollectShopStatus(shopId);
            if (integer>0){
                return true;
            }
        }
        return false;
    }

    public Boolean updateCollectProductStatus(Long productId,String token){
        /**
         * @author dawang
         * @date 2020/3/15 12:40
         * @parameter [productId, token]
         * @return java.lang.Boolean
         * @description 取消商品收藏
         */
        Boolean b=DecideToken.decideToken(token);
        if (b){
            Integer integer=collectMapper.updateCollectProductStatus(productId);
            if (integer>0){
                return true;
            }
        }
        return false;
    }
    /**
     * @author dawang
     * @date 2020/3/16 15:26
     * @parameter []
     * @return java.lang.Boolean
     * @description  定时任务 商品如果已下架 将收藏状态改为 已收藏 但是商品已下架
     */
    public Boolean updateProductCollectStatus(){
        Integer integer=collectMapper.updateProductCollectStatus();
        if (integer>0){
            return true;
        }
        return  false;
    }
    /**
     * @author dawang
     * @date 2020/3/16 15:26
     * @parameter []
     * @return java.lang.Boolean
     * @description 定时任务 店铺如果关门 将收藏状态改为 已收藏
     */
    public Boolean updateShopCollectStatus(){
        Integer integer=collectMapper.updateShopCollectStatus();
        if (integer>0){
            return true;
        }
        return false;
    }
}