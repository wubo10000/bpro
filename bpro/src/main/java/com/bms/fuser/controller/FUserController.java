package com.bms.fuser.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.Src;

import com.bms.system.bean.BaseController;
import com.bms.system.bean.GridResult;
import com.bms.system.bean.PPException;
import com.bms.system.inteface.Constant;
import com.bms.system.util.SicUtil;
import com.bms.system.util.SystemUtil;
import com.bms.fuser.dao.holder.FUser;
import com.bms.fuser.service.FUserService;

import com.bms.system.bean.BaseHolder;

@RequestMapping("/fUser")
@Controller
public class FUserController extends BaseController
{
    private static final Logger log = Logger.getLogger(FUserController.class);

    @Autowired
    private FUserService fUserService;

    @RequestMapping("/getGrid")
    @ResponseBody
    public GridResult getGrid(FUser bean)
    {
        log.info("start method getGrid");
        GridResult result = new GridResult();
        int count = fUserService.getCountOfSummary(bean);
        bean.setStartRecordNum(getStart(bean, count));
        bean.setPerNumber(bean.getLimit());

        result.setResult(fUserService.getListOfSummary(bean));
        result.setTotalCount(count);
        log.info("end method getGrid");
        return result;
    }

    @RequestMapping("/ts_add")
    public GridResult tsAdd(FUser bean)
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

                success = fUserService.tsInsert(bean) > 0;
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
    public GridResult tsUpdate(FUser bean)
    {
        GridResult result = new GridResult();
        boolean success = Constant.FAILURE_FLAG;
        String msg = Constant.FAILURE_MSG;

        try
        {
            if(null != bean && null != bean.getId() && !bean.getId().isEmpty())
            {
                // TODO 其他处理

                success = fUserService.tsUpdate(bean) > 0;
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
        List<FUser> list = new ArrayList<FUser>();

        try
        {
            if (null != ids && ids.length > 0)
            {
                FUser fUser;
                for(String id : ids)
                {
                    fUser = new FUser();
                    fUser.setId(id);
                    list.add(fUser);
                }

                success = fUserService.tsDelete(list) > 0;
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
    
    
    /**
     * 报名
     */
    @RequestMapping("/tsRegister")
    @ResponseBody
    public GridResult tsRegister(FUser bean){
    	GridResult result = new GridResult();
    	result.setSuccess(false);
    	FUser selectbean = new FUser();
    	selectbean.setCardcode(bean.getCardcode());
    	if(fUserService.getList(selectbean).size()>0){
    		result.setSuccess(false);
    		result.setMsg("身份证号已注册");
    	}else{
    		bean.setId(SystemUtil.getUUID());
        	bean.setUsercode(SicUtil.getRandomString(8));
        	bean.setCreatetime(new Date());
        	int i = fUserService.tsInsert(bean);
        	if(i==1){
        		result.setSuccess(true);
        		result.setMsg("报名成功");
        	}else{
        		result.setSuccess(false);
        		result.setMsg("操作失败");
        	}
    	}
    	return result;
    }
}
