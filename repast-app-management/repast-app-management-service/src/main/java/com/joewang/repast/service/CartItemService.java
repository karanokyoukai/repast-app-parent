package com.joewang.repast.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseService;
import com.joewang.repast.base.CommonCart;
import com.joewang.repast.mapper.Cart_itemMapper;
import com.joewang.repast.mapper.ProductComMapper;
import com.joewang.repast.mapper.ProductMapper;
import com.joewang.repast.mapper.SkuStockMapper;
import com.joewang.repast.model.*;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.utils.DecideToken;
import com.joewang.repast.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author: dawang
 * @date: Created in 2020/3/17 20:45
 * @version: 1.0
 * @description: 购物车有关业务
 */
@Service
public class CartItemService extends BaseService {
    @Autowired
    private Cart_itemMapper cart_itemMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductComMapper productComMapper;
    @Autowired
    private CommonCart commonCart;
    //产生异常，一但return就就会跳出for循环
    private Exception exception = new Exception("操作失败");
    /**
     * 设置延时处理
     */
    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);
    @Override
    public Mapper getMapper() {
        return cart_itemMapper;
    }
    /**
     * @author dawang
     * @date 2020/3/17 20:51
     * @parameter [memberId, token]
     * @return java.util.List<java.util.HashMap>
     * @description 通过用户ID查询购物车、商品、店铺信息
     */
    public PageInfo<HashMap> selectAllCartItem(PageInfos<Long> pageInfos, String token){
        Boolean b= DecideToken.decideToken(token);
        if (b){
            PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
            List<HashMap> cartItemList=cart_itemMapper.selectAllCartItem(pageInfos.getT());
            if (cartItemList!=null){
                PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(cartItemList);
                return pageInfo;
            }
        }
        return null;
    }
    /**
     * 购物车商品的添加
     * @param data
     * @return
     */
    @Transactional
    public Boolean addCartItem(Map<String,Object> data, String token){
        //首先验证token
        Member member = checkToken(token);
        Long memberId = member.getId();
        if (null != member){
            //从前台获取商品信息
            List<Map> ListCart = JSONUtil.toList(JSONUtil.toJsonString(data.get("cart")), Map.class);
            if (ListCart.size()>0){
                try {
                    int productServiceStatus = Integer.parseInt(JSONUtil.toJsonString(data.get("productServiceStatus")));
                    //遍历循环页面传过来的数据
                    for (int i = 0; i < ListCart.size(); i++){
                        Map map = ListCart.get(i);
                        int quantity = Integer.parseInt(JSONUtil.toJsonString(map.get("quantity")));
                        long productId = Long.parseLong( JSONUtil.toJsonString(map.get("productId")));
                        long shopId = Long.parseLong(JSONUtil.toJsonString(map.get("shopId")));
                        //查询数据库信息，购物车删除状态码，商品删除状态码，以及库存表中的库存信息
                        CartItem selectMemberCartPo = new CartItem();
                        selectMemberCartPo.setProductId(productId);
                        selectMemberCartPo.setMemberId(memberId);
                        CartItem selectMemberCart = cart_itemMapper.selectMemberByMemberIdProId(selectMemberCartPo);
                        //通过商品id 查询库存数量
                        SkuStock sku = skuStockMapper.selectSkuById(productId);
                        Integer stock = sku.getStock();
                        //通过商品id查询商品表
                        Product product = productMapper.selectProductById(productId);
                        Integer publishStatus = product.getPublishStatus();
                        System.out.println(publishStatus);
                        if (0 != quantity){
                            //页面发送的数据不为0，则说明用户要购买商品
                            if (null != selectMemberCart){
                                //查询数据库不为空，则说明购物车有该商品
                                if (1 == product.getPublishStatus()){
                                    //该商品为上架商品
                                    if (2==productServiceStatus){
                                        //商品服务类型为2，则说明该商品为超市商品，需要考虑库存问题
                                        if (stock >= quantity){
                                            //库存大于购买数量，可以正常购买
                                            /*1 有商品，上架，且购买数量大于0。考虑库存
                                             *2 修改商品数量，以及库存，设置定时如果10分钟不提交订单，则库存数量返回之前修改的数量
                                             *  */
                                            CartItem updateCartQuantity = new CartItem();
                                            updateCartQuantity.setProductId(productId);
                                            updateCartQuantity.setMemberId(memberId);
                                            updateCartQuantity.setQuantity(quantity);
                                            updateCartQuantity.setModifyDate(commonCart.date());
                                            System.out.println(commonCart.date());
                                            Integer upDateCart = cart_itemMapper.upDateCart(updateCartQuantity);
                                            //调用封装，实现对购物车表的修改
                                            commonCart.deleteTimeUpdate(stock,quantity,productId,token);
                                        }else {
                                            //库存小于购买数量，则不可以正常购买，需提示顾客库存不足
                                            System.out.println("库存不足");
                                            throw exception;
                                        }
                                    }else {
                                        //该商品服务类型为0,1，说明该商品为外卖或点餐，需要不考虑库存问题
                                        CartItem updateCartQuantity = new CartItem();
                                        updateCartQuantity.setProductId(productId);
                                        updateCartQuantity.setMemberId(memberId);
                                        updateCartQuantity.setQuantity(quantity);
                                        updateCartQuantity.setModifyDate(commonCart.date());
                                        Integer upDateCart = cart_itemMapper.upDateCart(updateCartQuantity);
                                        if (upDateCart == 0) {
                                            System.out.println("修改购买数量失败");
                                            throw exception;
                                        }
                                    }
                                }else {
                                    //该商品为下架商品，删除购物车信息
                                    Integer deleteCart = cart_itemMapper.deleteCart(selectMemberCartPo);
                                    if (deleteCart == 0){
                                        System.out.println("商品为下架，删除购物车信息成功");
                                        throw exception;
                                    }
                                }
                            }else {
                                //购物车没有数据，但用户却要购买商品
                                if (1==product.getPublishStatus()){
                                    //该商品为上架商
                                    if (2==productServiceStatus){
                                        //该商品为商品需要考虑库存问题，新增购物车信息，库存减去相应的数量，于此同时设置定时，8分钟后不提交订单，则库存返回修改之前数据
                                        System.out.println("开启定时任务");
                                        commonCart.addCartItem(token,productId,shopId,quantity,productServiceStatus);
                                        commonCart.deleteTimeUpdate(stock,quantity,productId,token);
                                    }else {
                                        //该服务类型为01，不考虑库存
                                        String nickName = member.getNickname();
                                        CartItem CartPro= new CartItem();
                                        ProductCom pComAttr = productComMapper.selectPcomById(productId);
                                        //把获取到的属性放进购物车实体类中，把这些属性添加到购物车表中
                                        System.out.println("开启定时任务");
                                        commonCart.addCartItem(token,productId,shopId,quantity,productServiceStatus);
                                    }
                                }else {
                                    //想要购买，购物车也没有商品信息，则提示该商品已下架
                                    System.out.println("商品已下架");
                                    throw exception;
                                }
                            }
                        }else{
                            //页面发送的数据为0，说明用户对于该商品不需要
                            if (null != selectMemberCart){
                                //查询数据库，数据库有信息，则删除购物车信息
                                Integer integer = cart_itemMapper.deleteCart(selectMemberCartPo);
                                if (integer == 0){
                                    System.out.println("删除购物车成功");
                                    throw exception;
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }else{
                System.out.println("前台发送数据为空");
                return false;
            }
        }else {
            System.out.println("您还没登录，请请先登录");
            return false;
        }
        return true;
    }
    /**
     * 清空购物车
     * @param shopId
     * @param token
     * @return
     */
    public Boolean clearCart(Long shopId, String token){
        Member member = checkToken(token);
        if (null != member){ Long memberId = member.getId();
            //通过店铺id和会员id修改删除状态码，来实现提交订单的时候，购物车不显示数据
            //在订单完成后如果想要“再来一单”可以通过修改状态码来实现再次购买
            CartItem cartclear = new CartItem();
            cartclear.setShopId(shopId);
            cartclear.setMemberId(memberId);
            cartclear.setDeleteStatus(1);
            cartclear.setModifyDate(commonCart.date());
            Integer integer = cart_itemMapper.clearCart(cartclear);
            if (integer > 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
