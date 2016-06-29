package com.bms.tuser.controller;

import com.bms.system.bean.BaseController;
import com.bms.system.bean.BaseHolder;
import com.bms.system.bean.GridResult;
import com.bms.system.bean.PPException;
import com.bms.system.util.DataValidation;
import com.bms.system.util.SicUtil;
import com.bms.system.util.SystemUtil;
import com.bms.tuser.dao.holder.TUser;
import com.bms.tuser.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RequestMapping("/tUser")
@Controller
public class TUserController extends BaseController
{
    @Autowired
    private TUserService tUserService;

    /**
     * 管理后台登录接口
     *
     * @param bean 登录封装的对象
     * @return 后台首页视图/登录视图和错误信息
     */
    @RequestMapping("/ts_login")
    public ModelAndView TSLogin(TUser bean)
    {
        Map<String, Object> baseMap = new HashMap<String, Object>();
        TUser tuser = new TUser();

        if (null != bean && DataValidation.valCol(bean.getLoginName())
                && DataValidation.valCol(bean.getPassword()))
        {
            tuser.setLoginName(bean.getLoginName());
            tuser.setPassword(SicUtil.getSHA256(bean.getPassword()));

            List<TUser> user = tUserService.getList(tuser);

            if (null != user && user.size() == 1)
            {
                TUser tUser = user.get(0);
                getSession().removeAttribute("ts_user");
                getSession().setAttribute("ts_user", tUser);

                tUser.setLastLoginTime(new Date());

                tUserService.tsUpdate(tUser);

                return new ModelAndView("redirect:../web/system/index.jsp", baseMap);
            }
            else
            {
                baseMap.put("msg", "账号或密码错误");
            }
        }

        return new ModelAndView("login", baseMap);
    }

    /**
     * 后台退出接口
     *
     * @return 登录视图
     */
    @RequestMapping("/ts_logOut")
    public ModelAndView logOut()
    {
        Map<String, BaseHolder> baseMap = new HashMap<String, BaseHolder>();
        getSession().removeAttribute("ts_user");
        return new ModelAndView("login", baseMap);
    }

    /**
     * 获取后台用户数据，分页显示
     *
     * @param bean 参数对象
     * @return GridResult
     */
    @RequestMapping("/ts_getGrid")
    @ResponseBody
    public GridResult tsGetGrid(TUser bean)
    {
        GridResult result = new GridResult();
        int count = tUserService.getCountOfSummary(bean);
        bean.setStartRecordNum(getStart(bean, count));
        bean.setPerNumber(bean.getLimit());

        result.setResult(tUserService.getListOfSummary(bean));
        result.setTotalCount(count);
        return result;
    }

    /**
     * 添加用户
     *
     * @param bean 参数对象
     * @return GridResult
     */
    @RequestMapping("/ts_add")
    @ResponseBody
    public GridResult add(TUser bean)
    {
        GridResult result = new GridResult();
        boolean success = false;
        String msg = "用户添加失败";
        try
        {
            bean.setId(SystemUtil.getUUID());
            bean.setPassword(SicUtil.getSHA256(bean.getPassword()));

            Date date = new Date();
            bean.setDateCreated(date);
            bean.setLastLoginTime(date);

            success = tUserService.tsInsert(bean) > 0;
            if (success)
            {
                msg = "用户添加成功";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        result.setMsg(msg);
        result.setSuccess(success);
        return result;
    }

    @RequestMapping("ts_update")
    @ResponseBody
    public void tsUpdateUser(TUser bean, HttpServletResponse response)
    {
        boolean successs = false;
        String msg = "操作失败";
        try
        {
            if (null != bean && null != bean.getId() && !bean.getId().isEmpty())
            {
                TUser tmp = new TUser();
                tmp.setId(bean.getId());
                List<TUser> list = tUserService.getList(tmp);
                if (null != list && list.size() > 0)
                {
                    if (!list.get(0).getPassword().equals(bean.getPassword()))
                    {
                        bean.setPassword(SicUtil.getSHA256(bean.getPassword()));
                    }
                }

                successs = tUserService.tsUpdate(bean) > 0 ;
                if (successs)
                {
                    msg = "操作成功";
                }
            }
        }
        catch (PPException e)
        {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        msg = "{\"msg\":\"" + msg + "\", \"success\":" + successs + "}";
        write(msg, response);
    }

    /**
     * 用户删除
     *
     * @param ids id集合
     * @return GridResult
     */
    @RequestMapping("/ts_del")
    @ResponseBody
    public GridResult delete(String... ids)
    {
        GridResult result = new GridResult();
        List<TUser> users = new ArrayList<TUser>();
        String msg = "用户删除失败";
        boolean success = false;
        TUser user;
        try
        {
            if (null != ids)
            {
                for (String id : ids)
                {
                    user = new TUser();
                    user.setId(id);
                    users.add(user);
                }
                success = tUserService.tsDelete(users) > 0;
                if (success)
                {
                    msg = "用户删除成功";
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        result.setMsg(msg);
        result.setSuccess(success);
        return result;
    }
}