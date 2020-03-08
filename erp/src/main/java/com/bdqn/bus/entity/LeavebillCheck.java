package com.bdqn.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author chen
 * @since 2020-02-12
 */
@TableName("bus_leavebill_check")
public class LeavebillCheck implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 审批意见编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 审批回复内容
     */
    @TableField("replyContent")
    private String replyContent;

    /**
     * 审批人
     */
    @TableField("checkUserId")
    private Integer checkUserId;

    /**
     * 审批时间
     */
    @TableField("checkTime")
    private Date checkTime;

    /**
     * 请假单id
     */
    @TableField("leavebillId")
    private Integer leavebillId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Integer checkUserId) {
        this.checkUserId = checkUserId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getLeavebillId() {
        return leavebillId;
    }

    public void setLeavebillId(Integer leavebillId) {
        this.leavebillId = leavebillId;
    }

    @Override
    public String toString() {
        return "LeavebillCheck{" +
        "id=" + id +
        ", replyContent=" + replyContent +
        ", checkUserId=" + checkUserId +
        ", checkTime=" + checkTime +
        ", leavebillId=" + leavebillId +
        "}";
    }
}
