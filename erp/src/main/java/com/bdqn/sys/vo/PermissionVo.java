package com.bdqn.sys.vo;

import com.bdqn.sys.entity.Permission;

/**
 * 权限扩展类
 */
public class PermissionVo extends Permission {
    private Integer page;//当前页码
    private Integer limit;//每页显示数量

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}