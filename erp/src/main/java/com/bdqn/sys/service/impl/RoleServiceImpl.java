package com.bdqn.sys.service.impl;

import com.bdqn.sys.entity.Role;
import com.bdqn.sys.dao.RoleMapper;
import com.bdqn.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Override
    public boolean removeById(Serializable id) {
        //根据角色id删除sys_role_permission关系表数据
        roleMapper.deleteRolePermissionByRoleId(id);
        //根据角色id删除sys_role_user关系表数据
        roleMapper.deleteRoleUserByRoleId(id);
        return super.removeById(id);
    }

    @Override
    public boolean saveRolePermission(int rid, String ids) throws Exception {
        try {
            //先删除sys_role_permission关系表的数据
            //根据角色id删除sys_role_permission
            this.getBaseMapper().deleteRolePermissionByRoleId(rid);
            //保存分配的权限信息
            String [] idsStr=ids.split(",");
            //循环保存
            for(int i=0;i<idsStr.length;i++){
                this.getBaseMapper().insertRolePermission(rid,idsStr[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
