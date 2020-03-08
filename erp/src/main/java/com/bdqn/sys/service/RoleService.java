package com.bdqn.sys.service;

import com.bdqn.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-31
 */
public interface RoleService extends IService<Role> {
    /**
     * 保存角色和权限的关系
     * @param rid   角色id
     * @param ids   权限列表
     * @return
     * @throws Exception
     */
    boolean saveRolePermission(int rid, String ids) throws Exception;
}
