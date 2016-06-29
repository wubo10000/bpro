package com.bms.tconfig.controller;

import com.bms.system.bean.BaseController;
import com.bms.system.bean.GridResult;
import com.bms.system.bean.PPException;
import com.bms.system.inteface.Constant;
import com.bms.system.util.SystemUtil;
import com.bms.tconfig.dao.holder.TConfig;
import com.bms.tconfig.service.TConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequestMapping("/tConfig")
@Controller
public class TConfigController extends BaseController
{
    private static final Logger log = Logger.getLogger(TConfigController.class);

    @Autowired
    private TConfigService tConfigService;

    @RequestMapping("ts_getGridAll")
    @ResponseBody
    public GridResult tsGridAll(TConfig bean)
    {
        GridResult result = new GridResult();
        result.setResult(tConfigService.getList(bean));
        return result;
    }

    @RequestMapping("/ts_getGrid")
    @ResponseBody
    public GridResult tsGetGrid(TConfig bean)
    {
        log.info("start method tsGetGrid");
        GridResult result = new GridResult();
        int count = tConfigService.getCountOfSummary(bean);
        bean.setStartRecordNum(getStart(bean, count));
        bean.setPerNumber(bean.getLimit());

        bean.setSortField("T_KEY");
        bean.setSortOrder("ASC");
        result.setResult(tConfigService.getListOfSummary(bean));
        result.setTotalCount(count);
        log.info("end method tsGetGrid");
        return result;
    }

    @RequestMapping("ts_getGridAll2")
    @ResponseBody
    public GridResult tsGridAll2(TConfig bean)
    {
        GridResult result = new GridResult();
        List<TConfig> configList = tConfigService.getList(bean);
        if (null != configList && configList.size() > 0)
        {
            for (Iterator<TConfig> iterator = configList.iterator(); iterator.hasNext(); )
            {
                TConfig config = iterator.next();
                if (1 == config.getTKey() && "IMAGE_USE_TYPE".equals(config.getTGroup()))
                {
                    iterator.remove();
                }
            }
        }
        result.setResult(configList);
        return result;
    }

    @RequestMapping("ts_getGridAllGroup")
    @ResponseBody
    public GridResult tsGridAllGroup(TConfig bean)
    {
        GridResult result = new GridResult();

        List<TConfig> configList = tConfigService.getGroup(bean);
        for (TConfig config : configList)
        {
            String temp = config.getTGroup() + "|" + config.getTGroupName();
            config.setTGroup(temp);
        }

        result.setResult(configList);
        return result;
    }

    @RequestMapping("/ts_add")
    @ResponseBody
    public GridResult tsAdd(TConfig bean)
    {
        GridResult result = new GridResult();
        boolean success = Constant.FAILURE_FLAG;
        String msg = Constant.FAILURE_MSG;
        TConfig config;

        try
        {
            if (null != bean)
            {
                bean.setId(SystemUtil.getUUID());

                String tempTGroupName = bean.getTGroupName();
                String[] tGroupAndName = tempTGroupName.split("\\|");

                bean.setTGroup(tGroupAndName[0]);
                bean.setTGroupName(tGroupAndName[1]);

                config = new TConfig();
                config.setTGroup(bean.getTGroup());
                config.setStartRecordNum(0);
                config.setPerNumber(1);
                config.setSortField("T_KEY");
                config.setSortOrder("desc");
                List<TConfig> list = tConfigService.getListOfSummary(config);
                if (null != list && list.size() > 0)
                {
                    config = list.get(0);
                    int temp = config.getTKey() + 1;

                    bean.setTKey(999 == config.getTKey() ? 1 : temp);
                }

                success = tConfigService.tsInsert(bean) > 0;
                if (success)
                {
                    if (999 == config.getTKey())
                    {
                        config = new TConfig();
                        config.setTKey(999);
                        config.setTGroup(bean.getTGroup());
                        tConfigService.tsDelete(config);

                        tConfigService.tsDelete(config);
                    }
                    msg = Constant.SUCCESS_MSG;
                }
            }
        }
        catch (PPException e)
        {
            log.error("TConfig add is error : " + e);
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping("/ts_addGroup")
    @ResponseBody
    public GridResult tsAddGroup(TConfig bean)
    {
        GridResult result = new GridResult();
        boolean success = false;
        String msg = "操作失败";

        try
        {
            if (null != bean)
            {
                bean.setTKey(999);
                bean.setId(SystemUtil.getUUID());

                success = tConfigService.tsInsert(bean) > 0;
                if (success)
                {
                    msg = "操作成功";
                }
            }
        }
        catch (PPException e)
        {
            log.error("TConfig add is error : " + e);
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping("/ts_update")
    @ResponseBody
    public GridResult tsUpdate(TConfig bean)
    {
        GridResult result = new GridResult();
        boolean success = false;
        String msg = "操作失败";

        try
        {
            if (null != bean && null != bean.getId() && !bean.getId().isEmpty() && null != bean.getTGroupName())
            {
                success = tConfigService.tsUpdate(bean) > 0;

                if (success)
                {
                    msg = "操作成功";
                }
            }
        }
        catch (PPException e)
        {
            log.error("TConfig update is error : " + e);
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping("/ts_sysconfupdate")
    @ResponseBody
    public GridResult tsSysconfUpdate(TConfig bean)
    {
        GridResult result = new GridResult();
        boolean success = false;
        String msg = "操作失败";

        try
        {
            if (null != bean && null != bean.getId() && !bean.getId().isEmpty())
            {

                success = tConfigService.tsUpdate(bean) > 0;

                if (success)
                {
                    msg = "操作成功";
                }
            }
        }
        catch (PPException e)
        {
            log.error("tsSysconfUpdate update is error : " + e);
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping("/ts_del")
    @ResponseBody
    public GridResult tsDel(String... ids)
    {
        GridResult result = new GridResult();
        List<TConfig> userList = new ArrayList<>();
        boolean success = false;
        String msg = "操作失败";

        try
        {
            if (null != ids && ids.length > 0)
            {
                TConfig video;
                for (String id : ids)
                {
                    video = new TConfig();
                    video.setId(id);
                    userList.add(video);
                }
                success = tConfigService.tsDelete(userList) > 0;
                if (success)
                {
                    msg = "操作成功";
                }
            }
        }
        catch (PPException e)
        {
            log.error("TConfig del is error : " + e);
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }
}