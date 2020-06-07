package com.atguan.crowdfunding.controller;


import com.atguan.crowdfunding.bean.TRole;
import com.atguan.crowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TRoleController {

    @Autowired
    TRoleService roleService;

    @RequestMapping("/role/index")
    public String index() {

        return "role/index";
    }

    @ResponseBody
    @RequestMapping("/role/doAdd")
    public String doAdd(TRole role) {
        roleService.saveTRole(role);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/role/getRoleById")
    public TRole getRoleById(Integer id) {
        return roleService.getRoleById(id);
    }


    @ResponseBody
    @RequestMapping("/role/doUpdate")
    public String doUpdate(TRole role) {
        roleService.upadteTRole(role);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/role/doDelete")
    public String doDelete(Integer id) {
        roleService.deleteTRole(id);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/role/loadData")
    public PageInfo<TRole> loadData(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "2") Integer pageSize,
                                    @RequestParam(value = "condition",required = false,defaultValue = "") String condition) {

        PageHelper.startPage(pageNum,pageSize);

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("condition",condition);

        PageInfo<TRole> page = roleService.listRolePage(paramMap);
        return page;
    }

}
