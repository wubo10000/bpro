package com.bms.flearntype.controller;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.system.bean.BaseController;
import com.bms.system.bean.GridResult;
import com.bms.system.bean.PPException;
import com.bms.system.inteface.Constant;
import com.bms.system.util.SystemUtil;
import com.bms.flearntype.dao.holder.FLearntype;
import com.bms.flearntype.service.FLearntypeService;

import com.bms.system.bean.BaseHolder;

@RequestMapping("/fLearntype")
@Controller
public class FLearntypeController extends BaseController
{
    private static final Logger log = Logger.getLogger(FLearntypeController.class);

    @Autowired
    private FLearntypeService fLearntypeService;

    @RequestMapping("/getGrid")
    @ResponseBody
    public GridResult getGrid(FLearntype bean)
    {
        log.info("start method getGrid");
        GridResult result = new GridResult();
        int count = fLearntypeService.getCountOfSummary(bean);
        bean.setStartRecordNum(getStart(bean, count));
        bean.setPerNumber(bean.getLimit());

        result.setResult(fLearntypeService.getListOfSummary(bean));
        result.setTotalCount(count);
        log.info("end method getGrid");
        return result;
    }

    @RequestMapping("/ts_add")
    public GridResult tsAdd(FLearntype bean)
    {
        GridResult result = new GridResult();
        boolean success = Constant.FAILURE_FLAG;
        String msg = Constant.FAILURE_MSG;

        try
        {
             if (null != bean)
             {
                bean.setId(SystemUtil.getUUID());

                // TODO 其他字段的添加

                success = fLearntypeService.tsInsert(bean) > 0;
                if(success)
                {
                    msg = Constant.SUCCESS_MSG;
                }
              }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping("/ts_update")
    public GridResult tsUpdate(FLearntype bean)
    {
        GridResult result = new GridResult();
        boolean success = Constant.FAILURE_FLAG;
        String msg = Constant.FAILURE_MSG;

        try
        {
            if(null != bean && null != bean.getId() && !bean.getId().isEmpty())
            {
                // TODO 其他处理

                success = fLearntypeService.tsUpdate(bean) > 0;
                if(success)
                {
                    msg = Constant.SUCCESS_MSG;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping("/ts_del")
    public GridResult tsDel(String ... ids)
    {
        GridResult result = new GridResult();
        boolean success = Constant.FAILURE_FLAG;
        String msg = Constant.FAILURE_MSG;
        List<FLearntype> list = new ArrayList<FLearntype>();

        try
        {
            if (null != ids && ids.length > 0)
            {
                FLearntype fLearntype;
                for(String id : ids)
                {
                    fLearntype = new FLearntype();
                    fLearntype.setId(id);
                    list.add(fLearntype);
                }

                success = fLearntypeService.tsDelete(list) > 0;
                if(success)
                {
                    msg = Constant.SUCCESS_MSG;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }
}
