package com.bdqn.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.bus.entity.Leavebill;
import com.bdqn.bus.entity.LeavebillCheck;
import com.bdqn.bus.service.LeavebillCheckService;
import com.bdqn.bus.service.LeavebillService;
import com.bdqn.bus.vo.LeavebillVo;
import com.bdqn.common.utils.DataGridViewResult;
import com.bdqn.common.utils.JSONResult;
import com.bdqn.common.utils.SystemConstant;
import com.bdqn.sys.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.channels.Pipe;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-02-12
 */
@RestController
@RequestMapping("/bus/leavebillcheck")
public class LeavebillCheckController {
    @Resource
    private LeavebillCheckService leavebillCheckService;

    @Resource
    private LeavebillService leavebillService;

    @RequestMapping("/unCheckLeaveBillList")
    public DataGridViewResult unCheckLeaveBillList(HttpSession session, LeavebillVo leavebillVo){
        //获取当前登录用户
        User user= (User) session.getAttribute(SystemConstant.LOGINUSER);
        //设置当前登录用户为审批请假单的用户
        leavebillVo.setCheckPerson(user.getId());
        //待审批状态
        leavebillVo.setState(SystemConstant.LEAVE_CHECKING_STATE);
        //创建分页对象
        IPage<Leavebill> page=new Page<Leavebill>(leavebillVo.getPage(),leavebillVo.getLimit());
        try {
            //调用方法
            leavebillService.findLeaveBillListByPage(page,leavebillVo);
            //返回数据
            return new DataGridViewResult(page.getTotal(),page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/checkLeavebill")
    public JSONResult checkLeavebill(LeavebillCheck leavebillCheck, HttpSession session,Integer state){
        User user= (User) session.getAttribute(SystemConstant.LOGINUSER);
        leavebillCheck.setCheckUserId(user.getId());//审批人
        leavebillCheck.setCheckTime(new Date());//审批时间
        try {
            if(leavebillCheckService.save(leavebillCheck,state)){
                return SystemConstant.CHECK_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.CHECK_ERROR;
    }
}

