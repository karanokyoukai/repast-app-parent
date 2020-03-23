package com.joewang.repast.base;

import com.joewang.repast.mapper.*;
import com.joewang.repast.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author: dawang
 * @date: Created in 2020/3/20 15:12
 * @version: 1.0
 * @description:购物车的封装
 */
@Service
public class CommonCart {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Cart_itemMapper cart_itemMapper;
    @Autowired
    private ProductComMapper productComMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;
    /**
     * 延时10分钟修改封装
     */
    public  boolean deleteTimeUpdate(Integer stock,Integer count,Long productId,String token){
        ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(5);
        Member member=memberMapper.selectMemberByToken(token);
        Long memberId=member.getId();
        //修改商品表中的库存
        Integer finallStrok=stock-count;
        Product product=new Product();
        product.setId(productId);
        product.setStock(finallStrok);
        Integer coutProduct=productMapper.updateStockById(product);
        if (coutProduct>0){
            System.out.println("修改商品和库存表的库存成功");
            //设置定时8分钟后库存返回修改之前的数据
            mScheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    //通过购物车id查询是否生成订单
                    System.out.println("开始定时");
                    CartItem cartItem=new CartItem();
                    cartItem.setProductId(productId);
                    cartItem.setMemberId(memberId);
                    CartItem  selectMemberCart=cart_itemMapper.selectMemberByMemberIdProId(cartItem);
                    if(1!=selectMemberCart.getDeleteStatus()){
                        product.setId(productId);
                        product.setStock(stock);
                        Integer countBackbackSkuProduct = productMapper.updateStockById(product);
                        if (countBackbackSkuProduct>0){
                            System.out.println("10分钟没有生成订单，库存恢复之前的数量");
                        }else {
                            System.out.println("库存恢复之前的数量异常");
                        }
                    }
                }
            },1, TimeUnit.MINUTES);
            return true;
        }else {
            System.out.println("修改商品和库存表的库存异常");
            return false;
        }
    }
    /**
     * 新增购物车封装
     */
    public Boolean addCartItem(String token,Long productId,Long shopId,Integer count,Integer productServiceStatus){
        Member member = memberMapper.selectMemberByToken(token);
        Long memberId = member.getId();
        String username=member.getUsername();
        CartItem cartItem=new CartItem();
        ProductCom productCom=productComMapper.selectPcomById(productId);
        //通过商品id查询该商品的上下架,以及添加时数据的获取都需要用到该方法
        Product product = productMapper.selectProductById(productId);
        SkuStock sku=skuStockMapper.selectSkuById(productId);
        Integer stock = sku.getStock();
        cartItem.setProductId(productId);
        cartItem.setProductSkuId(sku.getId());
        cartItem.setMemberId(memberId);
        cartItem.setShopId(shopId);
        cartItem.setQuantity(count);
        cartItem.setPrice(product.getPrice());
        cartItem.setSp1(sku.getSp1());
        cartItem.setSp2(sku.getSp2());
        cartItem.setSp3(sku.getSp3());
        cartItem.setProductPic(product.getPic());
        cartItem.setProductName(product.getName());
        cartItem.setProductSubTitle(product.getSubTitle());
        cartItem.setProductSkuCode(sku.getSkuCode());
        cartItem.setMemberNickname(username);
        cartItem.setCreateDate(date());
        cartItem.setDeleteStatus(0);
        cartItem.setProductCategoryId(product.getProductCategoryId());
        cartItem.setProductBrand(product.getBrandName());
        cartItem.setProductSn(product.getProductSn());
        cartItem.setProductAttr(productCom.getProductAttribute());
        cartItem.setProductServiceStatus(productServiceStatus);
        Integer addCart=cart_itemMapper.addCartPro(cartItem);
        if (addCart>0){
            return true;
        }
        return false;
    }
    /**
     * 时间日期的封装
     * @return
     */
    public Date date(){
        Date date1 = new Date();
        String formatDate = null;
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
        formatDate = dFormat.format(date1);
        SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date strD = null ;
        try {
            strD = lsdStrFormat.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strD;
    }
}
