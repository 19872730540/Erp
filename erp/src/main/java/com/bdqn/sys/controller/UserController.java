package com.bdqn.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.common.utils.*;
import com.bdqn.sys.entity.Log;
import com.bdqn.sys.entity.User;
import com.bdqn.sys.service.LogService;
import com.bdqn.sys.service.UserService;
import com.bdqn.sys.vo.LoginUserVo;
import com.bdqn.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-10
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Resource
    private LogService logService;
    @Resource
    private UserService userService;

    /**
     * 登录
     * @param loginname     用户名
     * @param pwd           密码
     * @param session
     * @return
     */
    @GetMapping("/login")
    public JSONResult login(String loginname, String pwd, HttpSession session, HttpServletRequest request){
        try {
            //获取当前登录主体对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(loginname,pwd);
            //登录
            subject.login(token);
            //获取当前登录对象
            LoginUserVo userVo =(LoginUserVo) subject.getPrincipal();
            //保存session
            session.setAttribute(SystemConstant.LOGINUSER,userVo.getUser());
            //保存日志
            Log log=new Log("用户登录",SystemConstant.LOGIN_ACTION,userVo.getUser().getLoginname()+"-"+userVo.getUser().getName(),
                    userVo.getUser().getId(),request.getRemoteAddr(),new Date());
            logService.save(log);
            //登录成功
            return SystemConstant.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //登录失败，用户名或密码错误
            return SystemConstant.LOGIN_ERROR_PASS;
        }
    }

    /**
     * 查询用户列表
     */
    @RequestMapping("/userList")
    public DataGridViewResult findUserList(UserVo userVo){
        //创建分页对象
        IPage<User> page=new Page<User>(userVo.getPage(),userVo.getLimit());
        try {
            //调用分页查询的方法
            IPage<User> userIPage=userService.findUserListByPage(page,userVo);
            //返回数据
            return new DataGridViewResult(userIPage.getTotal(),userIPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据部门编号查询该部门下的员工列表
     * @param deptid    部门编号
     * @return
     */
    @RequestMapping("/loadUserByDeptId")
    public DataGridViewResult loadUserByDeptId(Integer deptid){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("type",SystemConstant.USER_TYPE_NORMAL);//只查普通用户
        queryWrapper.eq(deptid!=null,"deptid",deptid);//部门编号
        //调用查询方法
        List<User> userList = userService.list(queryWrapper);
        //返回数据
        return new DataGridViewResult(userList);
    }

    /**
     * 验证登录名是否存在
     * @param loginName
     * @return
     */
    @RequestMapping("/checkLoginName")
    public String checkLoginName(String loginName){
        Map<String,Object> map=new LinkedHashMap<String, Object>();
        try {
            QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
            queryWrapper.eq("loginname",loginName);
            //查询用户
            User user=userService.getOne(queryWrapper);
            if(user!=null){
                map.put(SystemConstant.EXIST,true);
                map.put(SystemConstant.MESSAGE,"登录名称已存在,请重新输入");
            }else{
                map.put(SystemConstant.EXIST,false);
                map.put(SystemConstant.MESSAGE,"登录名称可用使用!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public JSONResult addUser(User user){
        try {
            //设置入职时间
            user.setHiredate(new Date());
            //设置盐
            String salt=UUIDUtil.randomUUID();
            user.setSalt(salt);
            //设置密码
            user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));
            //设置用户类型
            user.setType(SystemConstant.USER_TYPE_NORMAL);
            //调用新增方法
            if(userService.save(user)){
                return SystemConstant.ADD_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 根据领导mgrid查询该领导所在的部门
     * @param id
     * @return
     */
    @RequestMapping("/loadUserById")
    public DataGridViewResult loadUserById(Integer id){
        //返回数据
        return new DataGridViewResult(userService.getById(id));
    }
    /**
     * 修改用户
     */
    @RequestMapping("updateUser")
    public JSONResult updateUser(User user){
        try {
            if(userService.updateById(user)){
                return SystemConstant.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.UPDATE_ERROR;
    }

}