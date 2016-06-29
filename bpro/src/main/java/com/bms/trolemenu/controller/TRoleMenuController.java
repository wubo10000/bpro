package com.bms.trolemenu.controller;

import com.bms.system.bean.BaseController;
import com.bms.trolemenu.service.TRoleMenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tRoleMenu")
@Controller
public class TRoleMenuController extends BaseController
{
    private static final Logger log = Logger.getLogger(TRoleMenuController.class);

    @Autowired
    private TRoleMenuService tRoleMenuService;

}
