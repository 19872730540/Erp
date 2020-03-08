package com.bdqn.common.utils;

public interface SystemConstant {


    /**
     * 当前登录用户的key
     */
    String LOGINUSER = "loginUser";

    /**
     * 判断是否存在
     */
    String EXIST="exist";

    /**
     * 成功
     */
    Boolean OK = true;

    /**
     * 失败
     */
    Boolean ERROR = false;


    /**
     * 登录成功
     */
    JSONResult LOGIN_SUCCESS = new JSONResult(SystemConstant.OK,"登录成功");
    /**
     * 登录失败，用户名或密码错误
     */
    JSONResult LOGIN_ERROR_PASS = new JSONResult(SystemConstant.ERROR,"登录失败,用户名或密码错误");

    /**
     * 类型为菜单：用于首页左侧导航栏
     */
    String TYPE_MENU = "menu";

    /**
     * 类型为权限
     */
    String TYPE_PERMISSION ="permission" ;

    /**
     * 菜单是否展开，1展开
     */
    Integer OPEN_TRUE = 1;

    /**
     * 菜单是否展开，0不展开
     */
    Integer OPEN_FALSE = 0;

    /**
     * 角色为超级管理员
     */
    Integer SUPERUSER = 0;

    /**
     * 普通用户
     */
    Integer USER_TYPE_NORMAL=1;

    /**
     * 加密次数
     */
    Integer HASHITERATIONS=2;




    /**
     * 验证信息
     */
    String MESSAGE="message";

    /**
     * 登录操作
     */
    String LOGIN_ACTION="登录操作";
    /**
     * 注销操作
     */
    String LOGOUT_ACTION="注销操作";
    /**
     * 查询操作
     */
    String SEARCH_ACTION="查询操作";
    /**
     * 更新操作
     */
    String UPDATE_ACTION="更新操作";
    /**
     * 添加操作
     */
    String ADD_ACTION="添加操作";
    /**
     * 删除操作
     */
    String DELETE_ACTION="删除操作";

    /**
     * 默认密码
     */
    String DEFAULT_PWD="123456";



    /**
     * 删除成功
     */
    JSONResult DELETE_SUCCESS = new JSONResult(SystemConstant.OK,"删除成功");
    /**
     * 删除失败
     */
    JSONResult DELETE_ERROR = new JSONResult(SystemConstant.ERROR,"删除失败");
    /**
     * 添加成功
     */
    JSONResult ADD_SUCCESS = new JSONResult(SystemConstant.OK,"添加成功");
    /**
     * 添加失败
     */
    JSONResult ADD_ERROR = new JSONResult(SystemConstant.ERROR,"添加失败");
    /**
     * 修改成功
     */
    JSONResult UPDATE_SUCCESS = new JSONResult(SystemConstant.OK,"修改成功");
    /**
     * 修改失败
     */
    JSONResult UPDATE_ERROR = new JSONResult(SystemConstant.ERROR,"修改失败");

    /**
     * 权限分配成功
     */
    JSONResult DISTRIBUTE_SUCCESS = new JSONResult(SystemConstant.OK,"权限分配成功");

    /**
     * 权限分配失败
     */
    JSONResult DISTRIBUTE_ERROR = new JSONResult(SystemConstant.ERROR,"权限分配失败");

    /**
     * 请假单新创建状态码
     */
    Integer LEAVE_CREATE_STATE=0;
    /**
     * 请假单待审核状态码
     */
    Integer LEAVE_CHECKING_STATE=1;
    /**
     * 请假单审核通过状态码
     */
    Integer LEAVE_AGREE_STATE=2;
    /**
     * 请假单不通过状态码
     */
    Integer LEAVE_DISAGREE_STATE=3;

    /**
     * 审核成功
     */
    JSONResult CHECK_SUCCESS =new JSONResult(true,"审核成功") ;
    /**
     * 审核失败
     */
    JSONResult CHECK_ERROR =new JSONResult(true,"审核失败") ;
}