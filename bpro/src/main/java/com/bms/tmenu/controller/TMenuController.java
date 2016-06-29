package com.bms.tmenu.controller;

import com.bms.system.bean.BaseController;
import com.bms.system.util.SystemUtil;
import com.bms.tmenu.dao.holder.TMenu;
import com.bms.tmenu.service.TMenuService;
import com.bms.trolemenu.dao.holder.TRoleMenu;
import com.bms.trolemenu.service.TRoleMenuService;
import com.bms.tuser.dao.holder.TUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/tMenu")
@Controller
public class TMenuController extends BaseController
{
    private static final Logger log = Logger.getLogger(TMenuController.class);

    @Autowired
    private TMenuService tMenuService;

    @Autowired
    private TRoleMenuService tRoleMenuService;

    @RequestMapping("/ts_getMenu")
    @ResponseBody
    public void getMenu(HttpServletResponse response)
    {
        String roleId = "";
        String menuId = "";

        TUser tuser = (TUser) getSession().getAttribute("ts_user");
        if (null != tuser)
        {
            roleId = tuser.getRoleId();
            TRoleMenu tRoleMenu = new TRoleMenu();
            tRoleMenu.setRoleId(roleId);
            List<TRoleMenu> list = tRoleMenuService.getList(tRoleMenu);

            for (TRoleMenu roleMenu : list)
            {
                menuId = menuId + roleMenu.getMenuId() + ",";
            }

            menuId = menuId.length() > 0 ? menuId.substring(0, menuId.length() - 1) : "";
            menuId = "'" + menuId.replace(",", "','") + "'";
            TMenu tm = new TMenu();
            tm.setId(menuId);

            List<TMenu> listMenu = tMenuService.getListIn(tm);
            String tmp = SystemUtil.getJson(listMenu);

            write(tmp, response);
        }
    }

    @RequestMapping("/ts_getTreeGrid")
    @ResponseBody
    public void getTreeGrid(HttpServletResponse response, TMenu bean)
    {
        String tmp = "";
        String roleId = getRequest().getParameter("roleId");
        if (null == roleId || "".equals(roleId))
        {
            write(tmp,response);
        }
        else
        {
            // 查询该角色已经拥有的菜单
            TRoleMenu roleMenu = new TRoleMenu();
            roleMenu.setRoleId(roleId);

            List<TRoleMenu> roleMenus = tRoleMenuService.getMenu(roleMenu);

            List<TMenu> listMenu = tMenuService.getListIn(bean);
            tmp = SystemUtil.getTreeJson(listMenu, roleMenus);
            write(tmp,response);
        }
    }
}
