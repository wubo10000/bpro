package com.bms.trole.controller;

import com.bms.system.bean.BaseController;
import com.bms.system.bean.GridResult;
import com.bms.system.bean.PPException;
import com.bms.system.util.SystemUtil;
import com.bms.trole.dao.holder.TRole;
import com.bms.trole.service.TRoleService;
import com.bms.tuser.dao.holder.TUser;
import com.bms.tuser.service.TUserService;
import com.bms.tuser.service.impl.TUserServiceImpl;
import com.bms.trolemenu.dao.holder.TRoleMenu;
import com.bms.trolemenu.service.TRoleMenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tRole")
@Controller
public class TRoleController extends BaseController
{
    private static final Logger log = Logger.getLogger(TRoleController.class);


    @Autowired
    private TRoleService tRoleService;
    @Autowired
    private TUserService tUserService;


    @Autowired
    private TRoleMenuService tRoleMenuService;

    @RequestMapping("/ts_getGrid")
    @ResponseBody
    public GridResult tsGetGrid(TRole bean)
    {
        log.info("start method getGrid");

        GridResult result = new GridResult();

        int count = tRoleService.getCountOfSummary(bean);
        bean.setStartRecordNum(getStart(bean, count));
        bean.setPerNumber(bean.getLimit() * bean.getPage());

        result.setResult(tRoleService.getListOfSummary(bean));
        result.setTotalCount(count);
        log.info("end method getGrid");
        return result;
    }

    @RequestMapping("ts_getGridAll")
    @ResponseBody
    public GridResult tsGridAll(TRole bean)
    {
        GridResult result = new GridResult();
        bean.setRoleState(1);
        result.setResult(tRoleService.getList(bean));
        return result;
    }

    /**
     * 更新角色菜单
     * @param ids 菜单id
     * @param roleId 角色id
     * @param response 返回处理json提示信息
     */
    @RequestMapping("/ts_updateRole")
    @ResponseBody
    public void updateTRoleMeun(String ids, String roleId, HttpServletResponse response)
    {
        boolean success = false;
        String msg = "操作失败";
        List<TRoleMenu> list = new ArrayList<TRoleMenu>();

        try
        {

            if (null != roleId && !roleId.isEmpty())
            {
                TRoleMenu roleMenu = new TRoleMenu();
                roleMenu.setRoleId(roleId);
                success = tRoleMenuService.tsDelete(roleMenu) >= 0;

                if (success)
                {
                    String[] strs = ids.split(",");
                    TRoleMenu bean;
                    for (int i = 0; i < strs.length; i++)
                    {
                        bean = new TRoleMenu();
                        bean.setId(SystemUtil.getUUID());
                        bean.setRoleId(roleId);
                        bean.setMenuId(strs[i]);
                        bean.setPriority(i + 1);
                        list.add(bean);
                    }
                    success = tRoleMenuService.insertBatch(list) > 0;
                    if (success)
                    {
                        msg = "操作成功";
                    }
                }
            }
        }
        catch (PPException e)
        {
            log.error("ts_updateRole update is error : " + e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        response.setContentType("text/html");

        msg = "{\"msg\":\"" + msg + "\", \"success\":" + success + "}";
        write(msg, response);
    }

    /**
     * 权限添加
     * @param bean 权限添加封装对象
     * @return 添加操作的json信息
     */
    @RequestMapping("ts_add")
    @ResponseBody
    public GridResult tsAdd(TRole bean)
    {

        GridResult result = new GridResult();
        boolean success = false;
        String msg = "操作失败";

        try
        {
            if (null != bean)
            {
                bean.setId(SystemUtil.getUUID());
                success = tRoleService.tsInsert(bean) > 0;
                if (success)
                {
                    msg = "操作成功";
                }
            }
        }
        catch (Exception e)
        {
            msg = "操作失败";
        }

        result.setMsg(msg);
        result.setSuccess(success);
        return result;
    }

    /**
     * 角色权限删除
     * @param ids
     * @return
     */
    @RequestMapping("/ts_del")
    @ResponseBody
    public GridResult tsdelete(String... ids){
        GridResult result=new GridResult();
        String msg="删除失败";
        boolean success=false;
        List<TRole> troles=new ArrayList<TRole>();
        TRole tRole;
        TUserService userService=new TUserServiceImpl();
        TUser user;
        int count=0;


        try{
            if(ids!=null && ids.length>0){
                for(String id:ids){
                    user=new TUser();
                    user.setRoleId(id);
                    count+=tUserService.getCountOfSummary(user);
                    if(count>0){
                        result.setMsg("用户正在使用，无法删除改角色");
                        result.setSuccess(success);
                        return result;
                    }
                    tRole=new TRole();
                    tRole.setId(id);
                    troles.add(tRole);
                }
            }
            success=tRoleService.tsDelete(troles)>0;
            if(success){
                msg="删除成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setMsg(msg);
        result.setSuccess(success);
        return result;
    }

    /**
     * 角色权限修改
     * @param bean
     * @return
     */
    @RequestMapping("/ts_update")
    @ResponseBody
    public GridResult tsUpdate(TRole bean){
        GridResult result=new GridResult();
        String msg="修改失败";
        boolean success=false;

        try{
            if(bean!=null){
                success=tRoleService.tsUpdate(bean)>0;
            }
            if(success){
                msg="修改成功";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }
}