package com.joewang.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "`ums_member_level`")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@Data
public class MemberLevel implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`shop_id`")
    private Long shopId;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`growth_point`")
    private Integer growthPoint;

    /**
     * 是否为默认等级：0->不是；1->是
     */
    @Column(name = "`default_status`")
    private Integer defaultStatus;

    /**
     * 免运费标准
     */
    @Column(name = "`free_freight_point`")
    private BigDecimal freeFreightPoint;

    /**
     * 每次评价获取的成长值
     */
    @Column(name = "`comment_growth_point`")
    private Integer commentGrowthPoint;

    /**
     * 是否有免邮特权
     */
    @Column(name = "`priviledge_free_freight`")
    private Integer priviledgeFreeFreight;

    /**
     * 是否有签到特权
     */
    @Column(name = "`priviledge_sign_in`")
    private Integer priviledgeSignIn;

    /**
     * 是否有评论获奖励特权
     */
    @Column(name = "`priviledge_comment`")
    private Integer priviledgeComment;

    /**
     * 是否有专享活动特权
     */
    @Column(name = "`priviledge_promotion`")
    private Integer priviledgePromotion;

    /**
     * 是否有会员价格特权
     */
    @Column(name = "`priviledge_member_price`")
    private Integer priviledgeMemberPrice;

    /**
     * 是否有生日特权
     */
    @Column(name = "`priviledge_birthday`")
    private Integer priviledgeBirthday;

    @Column(name = "`note`")
    private String note;


}