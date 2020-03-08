package com.bdqn.sys.dao;

import com.bdqn.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-31
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色id删除sys_role_permission关系表数据
     * @param id    角色id
     */
    @Delete("delete from sys_role_permission where rid=#{id}")
    void deleteRolePermissionByRoleId(Serializable id);

    /**
     * 根据角色id删除sys_role_user关系表数据
     * @param id    角色id
     */
    @Delete("delete from sys_role_user where rid=#{id}")
    void deleteRoleUserByRoleId(Serializable id);


    @Insert("insert into sys_role_permission (rid,pid) values (#{rid},#{pid})")
    void insertRolePermission(@Param("rid") int rid,@Param("pid") String pid)throws Exception;
}
