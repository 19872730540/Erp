package com.bdqn.bus.service.impl;

import com.bdqn.bus.dao.LeavebillMapper;
import com.bdqn.bus.entity.Leavebill;
import com.bdqn.bus.entity.LeavebillCheck;
import com.bdqn.bus.dao.LeavebillCheckMapper;
import com.bdqn.bus.service.LeavebillCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-02-12
 */
@Service
@Transactional
public class LeavebillCheckServiceImpl extends ServiceImpl<LeavebillCheckMapper, LeavebillCheck> implements LeavebillCheckService {
    @Resource
    private LeavebillCheckMapper leavebillCheckMapper;

    @Resource
    private  LeavebillMapper leavebillMapper;
    @Override
    public boolean save(LeavebillCheck leavebillCheck, Integer state) throws Exception {
        //修改请假单状态
        //根据请假单id查询请假单详情
        //参数必须是审批表中的请假单id字段
        Leavebill leavebill=leavebillMapper.selectById(leavebillCheck.getLeavebillId());
        //修改状态
        leavebill.setState(state);
        //保存修改
        leavebillMapper.updateById(leavebill);
        return super.save(leavebillCheck);//保存审批记录
    }
}
