package com.bdqn.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus")
public class BusinessController {

    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/toLeaveBillManager")
    public String toIndex(){
        return "business/leavebill/leavebillManager";
    }

    /**
     * 去到我的待审批
     * @return
     */
    @RequestMapping("/toMyCheckManager")
    public String toMyCheckManager(){
        return "business/leavebill/myCheckManager";
    }





}