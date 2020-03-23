package com.joewang.repast.mapper;

import com.joewang.repast.model.CartItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface Cart_itemMapper extends Mapper<CartItem> {
    //查询个人用户购物车所有信息
    List<HashMap> selectAllCartItem(Long memberid);
    /*加入购物车前查询用户购物车信息*/
    CartItem selectMemberByMemberIdProId(CartItem cartItem);
    //新增购物车
    Integer addCartPro(CartItem cartItem);
    //修改购物车信息
    Integer upDateCart(CartItem updateCartQuantity);
    /*通过用户id和店铺id清除购物车*/
    Integer clearCart(CartItem cartclear);
    /*删除购物车信息*/
    Integer deleteCart(CartItem selectMemberCartPo);
}