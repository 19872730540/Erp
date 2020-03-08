package com.bdqn.sys.service;

import com.bdqn.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-30
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 根据角色id查询菜单编号
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Integer> findRolePermissionByRoleId(int roleId) throws Exception;
}