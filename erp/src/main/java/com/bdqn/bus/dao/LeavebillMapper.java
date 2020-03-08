package com.bdqn.bus.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.bus.entity.Leavebill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdqn.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2020-02-10
 */
public interface LeavebillMapper extends BaseMapper<Leavebill> {
    /**
     * 分页查询请假单
     * @param page
     * @param leavebillVo
     * @return
     * @throws Exception
     */
    IPage<Leavebill> findLeaveBillListByPage(@Param("page") IPage<Leavebill> page, @Param("leavebill") LeavebillVo leavebillVo)throws Exception;
}
