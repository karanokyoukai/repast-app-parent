package com.joewang.repast.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`ums_collect`")
public class Collect {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商铺ID
     */
    @Id
    @Column(name = "`product_id`")
    private Long productId;

    /**
     * 用户ID
     */
    @Column(name = "`member_id`")
    private Long memberId;

    /**
     * 店铺ID
     */
    @Column(name = "`shop_id`")
    private Long shopId;

    /**
     * 收藏时间
     */
    @Column(name = "`collect_time`")
    private Date collectTime;

    /**
     * 收藏商品状态：0-->收藏商品；1-->收藏的商品下架；2-->取消收藏
     */
    @Column(name = "`collect_product_status`")
    private Integer collectProductStatus;

    /**
     * 收藏商品状态：0-->收藏店铺；1-->收藏的店铺关闭；2-->取消收藏
     */
    @Column(name = "`collect_shop_status`")
    private Integer collectShopStatus;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商铺ID
     *
     * @return product_id - 商铺ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置商铺ID
     *
     * @param productId 商铺ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取店铺ID
     *
     * @return shop_id - 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店铺ID
     *
     * @param shopId 店铺ID
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取收藏时间
     *
     * @return collect_time - 收藏时间
     */
    public Date getCollectTime() {
        return collectTime;
    }

    /**
     * 设置收藏时间
     *
     * @param collectTime 收藏时间
     */
    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    /**
     * 获取收藏商品状态：0-->收藏商品；1-->收藏的商品下架；2-->取消收藏
     *
     * @return collect_product_status - 收藏商品状态：0-->收藏商品；1-->收藏的商品下架；2-->取消收藏
     */
    public Integer getCollectProductStatus() {
        return collectProductStatus;
    }

    /**
     * 设置收藏商品状态：0-->收藏商品；1-->收藏的商品下架；2-->取消收藏
     *
     * @param collectProductStatus 收藏商品状态：0-->收藏商品；1-->收藏的商品下架；2-->取消收藏
     */
    public void setCollectProductStatus(Integer collectProductStatus) {
        this.collectProductStatus = collectProductStatus;
    }

    /**
     * 获取收藏商品状态：0-->收藏店铺；1-->收藏的店铺关闭；2-->取消收藏
     *
     * @return collect_shop_status - 收藏商品状态：0-->收藏店铺；1-->收藏的店铺关闭；2-->取消收藏
     */
    public Integer getCollectShopStatus() {
        return collectShopStatus;
    }

    /**
     * 设置收藏商品状态：0-->收藏店铺；1-->收藏的店铺关闭；2-->取消收藏
     *
     * @param collectShopStatus 收藏商品状态：0-->收藏店铺；1-->收藏的店铺关闭；2-->取消收藏
     */
    public void setCollectShopStatus(Integer collectShopStatus) {
        this.collectShopStatus = collectShopStatus;
    }
}