package com.bdqn.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdqn.sys.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-24
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> findUserListByPage(@Param("page") IPage<User> page, @Param("user")UserVo user)throws Exception;
}
