package com.joewang.repast.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`pms_comment_replay`")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
public class CommentReplay implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`comment_id`")
    private Long commentId;

    @Column(name = "`member_nick_name`")
    private String memberNickName;

    @Column(name = "`member_icon`")
    private String memberIcon;

    @Column(name = "`content`")
    private String content;

    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 评论人员类型；0->会员；1->管理员
     */
    @Column(name = "`type`")
    private Integer type;

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
     * @return comment_id
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * @return member_nick_name
     */
    public String getMemberNickName() {
        return memberNickName;
    }

    /**
     * @param memberNickName
     */
    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName == null ? null : memberNickName.trim();
    }

    /**
     * @return member_icon
     */
    public String getMemberIcon() {
        return memberIcon;
    }

    /**
     * @param memberIcon
     */
    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon == null ? null : memberIcon.trim();
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取评论人员类型；0->会员；1->管理员
     *
     * @return type - 评论人员类型；0->会员；1->管理员
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置评论人员类型；0->会员；1->管理员
     *
     * @param type 评论人员类型；0->会员；1->管理员
     */
    public void setType(Integer type) {
        this.type = type;
    }
}