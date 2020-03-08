package com.bdqn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.sys.entity.Permission;
import com.bdqn.sys.entity.Role;
import com.bdqn.sys.service.PermissionService;
import com.bdqn.sys.service.RoleService;
import com.bdqn.common.utils.DataGridViewResult;
import com.bdqn.common.utils.JSONResult;
import com.bdqn.common.utils.SystemConstant;
import com.bdqn.common.utils.TreeNode;
import com.bdqn.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-31
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @RequestMapping("/roleList")
    public DataGridViewResult roleList(RoleVo roleVo){
        //创建分页对象
        IPage<Role> page = new Page<Role>(roleVo.getPage(),roleVo.getLimit());
        //创建条件构造器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        //权限名称查询
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRolecode()),"rolecode",roleVo.getRolecode());
        //权限编码
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRolename()),"rolename",roleVo.getRolename());
        //分页查询
        roleService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }
    @PostMapping("/addRole")
    public JSONResult addPermission(Role role){
        role.setCreatetime(new Date());
        try {
            //调用新增的方法
            if(roleService.save(role)){
                //新增成功
                return SystemConstant.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.ADD_ERROR;
    }

    @PostMapping("/updateRole")
    public JSONResult updatePermission(Role  role){
        try {
            //调用修改的方法
            if(roleService.updateById(role)){
                //修改成功
                return SystemConstant.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.UPDATE_ERROR;
    }


    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //删除成功
        if(roleService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }

    @RequestMapping("/initPermissionByRoleId")
    public DataGridViewResult initPermissionByRoleId(int roleId){
        try {
            //创建条件构造器
            QueryWrapper<Permission> queryWrapper=new QueryWrapper<Permission>();
            //查询所有菜单权限列表
            List<Permission> permissionList=permissionService.list(queryWrapper);
            //根据角色id查询当前角色拥有的菜单及权限
            List<Integer> currentPermissionIds=permissionService.findRolePermissionByRoleId(roleId);
            //根据查询出来的菜单编号去查询菜单权限
            List<Permission> currentPermissiions=new ArrayList<Permission>();
            //判断当前角色是否拥有的权限菜单id
            if(currentPermissionIds!=null && currentPermissionIds.size()>0){
                //如果拥有某些权限菜单,则根据查询出来的菜单编号去查询菜单权限数据
                queryWrapper.in("id",currentPermissionIds);
                currentPermissiions=permissionService.list(queryWrapper);
            }
            //构建TreeNode对象
            List<TreeNode> treeNodes=new ArrayList<TreeNode>();
            //循环遍历所有菜单权限列表
            for (Permission p1 : permissionList) {
                //定义变量,保存菜单是否被选中
                String checkArr="0";//不选中
                //内层循环当前角色拥有的菜单及权限,判断当前角色拥有哪些菜单和权限,将拥有的权限选中
                for (Permission p2 : currentPermissiions) {
                    //比较权限编号是否相等,相等则表示当前角色拥有这些菜单和权限,并将其选中
                    if(p1.getId()==p2.getId()){
                        checkArr="1";//选中
                        break;
                    }
                }
                //是否展开
                Boolean spread=(p1.getOpen()==null || p1.getOpen()==1)? true : false;
                //将选中的菜单节点放到TreeNodes集合中
                treeNodes.add(new TreeNode(p1.getId(),p1.getPid(),p1.getTitle(),spread,checkArr));
            }
            return new DataGridViewResult(treeNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/saveRolePermission")
    public JSONResult saveRolePermission(int rid,String ids){
        try {
            //保存
            if(roleService.saveRolePermission(rid,ids)){
                //权限分配成功
                return SystemConstant.DISTRIBUTE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //权限分配失败
        return SystemConstant.DISTRIBUTE_ERROR;
    }
}

