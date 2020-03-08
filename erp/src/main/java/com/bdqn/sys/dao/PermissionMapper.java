package com.bdqn.sys.dao;

import com.bdqn.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-30
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据菜单id或权限id删除sys_role_permission权限菜单关系表数据
     * @param id
     */
    @Delete("delete from sys_role_permission where pid =#{id}")
    void deleteRolePermissionByPid(Serializable id);


    /**
     * 根据角色id查询当前角色拥有的菜单及权限
     * @param roleId
     * @return
     */
    @Select("select pid from sys_role_permission where rid = #{id}")
    List<Integer> findRolePermissionByRoleId(int roleId) throws Exception;
}
