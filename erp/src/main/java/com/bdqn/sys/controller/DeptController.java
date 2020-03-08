package com.bdqn.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.sys.entity.Dept;
import com.bdqn.sys.service.DeptService;
import com.bdqn.common.utils.DataGridViewResult;
import com.bdqn.common.utils.JSONResult;
import com.bdqn.common.utils.SystemConstant;
import com.bdqn.common.utils.TreeNode;
import com.bdqn.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-27
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    //加载left.html树菜单
    @RequestMapping("/loadDeptTreeLeft")
    public DataGridViewResult loadDeptTreeLeft(){
        //查询所有部门
        List<Dept> deptList=deptService.list();
        //创建节点集合
        List<TreeNode> treeNodes=new ArrayList<TreeNode>();
        //循环遍历部门集合
        for (Dept dept : deptList) {
            //是否展开
            Boolean spread=dept.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(dept.getId(),dept.getPid(),dept.getTitle(),spread));
        }
        return new DataGridViewResult(treeNodes);
    }

    //加载右边数据表格数据
    @RequestMapping("/deptList")
    public DataGridViewResult findDeptList(DeptVo deptVo){
        //创建分页对象
        IPage<Dept> page=new Page<Dept>(deptVo.getPage(),deptVo.getLimit());
        //创建条件构造器
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        //部门名称查询
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        //地址
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()),"address",deptVo.getAddress());
        //编号
        queryWrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().eq(deptVo.getId()!=null,"pid",deptVo.getId());
        //排序
        queryWrapper.orderByAsc("id");
        //分页查询
        deptService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @PostMapping("/addDept")
    public JSONResult addDept(Dept dept){
        try {
            //设置添加时间
            dept.setCreatetime(new Date());
            //调用新增的方法
            if(deptService.save(dept)){
                //新增成功
                return SystemConstant.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.ADD_ERROR;
    }
    /**
     * 修改部门
     */
    @RequestMapping("/updateDept")
    public JSONResult updateDept(Dept dept){
        try {
            if(deptService.updateById(dept)){
                return SystemConstant.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.UPDATE_ERROR;
    }


    /**
     * 判断当前节点是否有子节点
     * @param id
     * @return
     */
    @RequestMapping("/checkDeptHasChildren")
    public String checkDeptHasChildren(int id){
        Map<String,Object> map=new LinkedHashMap<String, Object>();
        //构建条件对象
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<Dept>();
        //查询父节点下是否有数据
        queryWrapper.eq("pid",id);
        //查询
        List<Dept> deptList = deptService.list(queryWrapper);
        //判断集合是否有数据，有则不能删除
        if(deptList.size()>0){
            map.put(SystemConstant.EXIST,true);//存在
        }else{
            map.put(SystemConstant.EXIST,false);//不存在
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteDept")
    public JSONResult deleteById(int id){
        //删除成功
        if(deptService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }

}

