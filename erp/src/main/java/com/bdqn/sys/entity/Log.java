package com.bdqn.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-25
 */
@TableName("sys_log")
public class Log implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 日志编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 日志操作类型
     */
    private String type;

    /**
     * 执行人
     */
    private String loginname;

    /**
     * 执行人编号
     */
    private Integer userid;

    /**
     * 登录ip
     */
    private String loginip;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd : HH:mm:ss")
    private Date createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Log{" +
        "id=" + id +
        ", content=" + content +
        ", type=" + type +
        ", loginname=" + loginname +
        ", userid=" + userid +
        ", loginip=" + loginip +
        ", createtime=" + createtime +
        "}";
    }

    public Log() {
    }

    public Log(String content, String type, String loginname, Integer userid, String loginip, Date createtime) {
        this.content = content;
        this.type = type;
        this.loginname = loginname;
        this.userid = userid;
        this.loginip = loginip;
        this.createtime = createtime;
    }
}
