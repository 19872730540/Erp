package com.bdqn.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.bus.entity.Leavebill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2020-02-10
 */
public interface LeavebillService extends IService<Leavebill> {
    /**
     * 分页查询请假单
     * @return
     * @throws Exception
     */
    IPage<Leavebill> findLeaveBillListByPage(IPage<Leavebill> page,LeavebillVo leavebillVo)throws Exception;
}
