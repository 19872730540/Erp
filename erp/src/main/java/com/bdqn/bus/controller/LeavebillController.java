package com.bdqn.bus.controller;


import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.bus.entity.Leavebill;
import com.bdqn.bus.service.LeavebillService;
import com.bdqn.bus.vo.LeavebillVo;
import com.bdqn.common.utils.DataGridViewResult;
import com.bdqn.common.utils.JSONResult;
import com.bdqn.common.utils.SystemConstant;
import com.bdqn.sys.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/bus/leavebill")
public class LeavebillController {
    @Resource
    private LeavebillService leavebillService;

    @RequestMapping("/leavebillList")
    public DataGridViewResult leavebillList(LeavebillVo leavebillVo, HttpSession session){
        //获取当前登录用户
        User user= (User) session.getAttribute(SystemConstant.LOGINUSER);
        //设置当前登录用户
        leavebillVo.setUserid(user.getId());
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

    @RequestMapping("/addLeavebill")
    public JSONResult addLeavebill(Leavebill leavebill,HttpSession session){
        //请假人
        User user= (User) session.getAttribute(SystemConstant.LOGINUSER);
        leavebill.setUserid(user.getId());
        //请假时间
        leavebill.setCreatetime(new Date());
        //判断当前是保存还是提交操作
        if(leavebill.getState()!=null){//保存
            leavebill.setState(SystemConstant.LEAVE_CREATE_STATE);
        }else{
            leavebill.setState(SystemConstant.LEAVE_CHECKING_STATE);//待审批
            leavebill.setCommittime(new Date());//提交时间
        }
        if(leavebillService.save(leavebill)){
            return SystemConstant.ADD_SUCCESS;
        }else{
            return SystemConstant.ADD_ERROR;
        }
    }

    /**
     * 修改请假单
     * @param leavebill
     * @return
     */
    @RequestMapping("/updateLeavebill")
    public JSONResult updateLeavebill(Leavebill leavebill){
        //点击保存设置状态为0(新创建)
        if(leavebill.getState()!=null){
            leavebill.setState(SystemConstant.LEAVE_CREATE_STATE );
        }else{//点击提交设置状态为待审核
            leavebill.setState(SystemConstant.LEAVE_CHECKING_STATE);
            leavebill.setCommittime(new Date());//请假单提交时间
        }
        if(leavebillService.updateById(leavebill)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 批量删除请假单
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        try {
            //拆分成字符串数组
            String [] idsStr=ids.split(",");
            //将字符串数组转换成list集合进行批量删除
            if(leavebillService.removeByIds(Arrays.asList(idsStr))){
                return SystemConstant.DELETE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.DELETE_ERROR;
    }
}

