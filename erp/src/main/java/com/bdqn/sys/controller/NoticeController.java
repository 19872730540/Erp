package com.bdqn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.sys.entity.Notice;
import com.bdqn.sys.entity.User;
import com.bdqn.sys.service.NoticeService;
import com.bdqn.common.utils.DataGridViewResult;
import com.bdqn.common.utils.JSONResult;
import com.bdqn.common.utils.SystemConstant;
import com.bdqn.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/sys/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/noticeList")
    public DataGridViewResult findNoticeList(NoticeVo noticeVo){
        //创建分页对象
        IPage<Notice> iPage=new Page<Notice>(noticeVo.getPage(),noticeVo.getLimit());
        //创建条件构造器
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<Notice>();
        //标题查询
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        //发布人
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        //开始时间
        queryWrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        //结束时间
        queryWrapper.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        //排序
        queryWrapper.orderByDesc("createtime");
        //分页查询
        noticeService.page(iPage,queryWrapper);
        //返回数据
        return new DataGridViewResult(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * 添加公告
     * @param notice
     * @param session
     * @return
     */
    @RequestMapping("/addNotice")
    public JSONResult addNotice(Notice notice, HttpSession session){
        User user= (User) session.getAttribute(SystemConstant.LOGINUSER);
        notice.setOpername(user.getName());
        notice.setCreatetime(new Date());
        if(noticeService.save(notice)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }
    /**
     * 修改公告
     */
    @RequestMapping("/updateNotice")
    public JSONResult updateNotice(Notice notice){
        //保存公告
        if(noticeService.updateById(notice)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }
    /**
     * 删除公告
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        if(noticeService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    /**
     * 批量删除
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        try {
            //拼接成数组
            String [] idsStr=ids.split(",");
            //删除
            if(noticeService.removeByIds(Arrays.asList(idsStr))){//将string数组转换为list集合进行批量删除并判断
                //删除成功
                return SystemConstant.DELETE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }

}

