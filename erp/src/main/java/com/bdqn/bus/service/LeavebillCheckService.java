package com.bdqn.bus.service;

import com.bdqn.bus.entity.LeavebillCheck;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2020-02-12
 */
public interface LeavebillCheckService extends IService<LeavebillCheck> {
    /**
     * 新增审批记录
     * @param leavebillCheck
     * @param state
     * @return
     * @throws Exception
     */
    boolean save(LeavebillCheck leavebillCheck, Integer state) throws Exception;
}
