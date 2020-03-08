package com.bdqn.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.sys.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-10
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     * @throws Exception
     */
    User findUserByUserName(String userName) throws Exception;

    /**
     * 分页查询用户列表
     * @param page  分页对象
     * @param user  用户查询条件
     * @return
     * @throws Exception
     */
    IPage<User> findUserListByPage(IPage<User> page, UserVo user) throws Exception;
}