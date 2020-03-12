package com.joewang.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`ums_member_login_log`")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class LoginLog implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`shop_id`")
    private Long shopId;

    @Column(name = "`open_id`")
    private String openId;

    @Column(name = "`member_id`")
    private Long memberId;

    @Column(name = "`create_time`")
    private String createTime;

    @Column(name = "`ip`")
    private String ip;

    @Column(name = "`province`")
    private String province;

    @Column(name = "`city`")
    private String city;

    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    @Column(name = "`login_type`")
    private Integer loginType;

    /**
     * 操作类型(eg:删除操作，登录操作)
     */
    @Column(name = "`operation_type`")
    private String operationType;

    /**
     * 具体操作事项(eg:普通用户登录，删除用户，添加商品)
     */
    @Column(name = "`operation_name`")
    private String operationName;

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
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取登录类型：0->PC；1->android;2->ios;3->小程序
     *
     * @return login_type - 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * 设置登录类型：0->PC；1->android;2->ios;3->小程序
     *
     * @param loginType 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * 获取操作类型(eg:删除操作，登录操作)
     *
     * @return operation_type - 操作类型(eg:删除操作，登录操作)
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型(eg:删除操作，登录操作)
     *
     * @param operationType 操作类型(eg:删除操作，登录操作)
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * 获取具体操作事项(eg:普通用户登录，删除用户，添加商品)
     *
     * @return operation_name - 具体操作事项(eg:普通用户登录，删除用户，添加商品)
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置具体操作事项(eg:普通用户登录，删除用户，添加商品)
     *
     * @param operationName 具体操作事项(eg:普通用户登录，删除用户，添加商品)
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }
}