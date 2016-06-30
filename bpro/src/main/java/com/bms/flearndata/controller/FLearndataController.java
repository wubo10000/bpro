package com.bms.flearndata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bms.system.bean.BaseController;
import com.bms.system.bean.GridResult;
import com.bms.system.bean.PPException;
import com.bms.system.inteface.Constant;
import com.bms.system.util.SystemUtil;
import com.bms.flearndata.dao.holder.FLearndata;
import com.bms.flearndata.service.FLearndataService;

import com.bms.system.bean.BaseHolder;

@RequestMapping("/fLearndata")
@Controller
public class FLearndataController extends BaseController
{
    private static final Logger log = Logger.getLogger(FLearndataController.class);

    @Autowired
    private FLearndataService fLearndataService;

    @RequestMapping("/getGrid")
    @ResponseBody
    public GridResult getGrid(FLearndata bean)
    {
        log.info("start method getGrid");
        GridResult result = new GridResult();
        int count = fLearndataService.getCountOfSummary(bean);
        bean.setStartRecordNum(getStart(bean, count));
        bean.setPerNumber(bean.getLimit());

        result.setResult(fLearndataService.getListOfSummary(bean));
        result.setTotalCount(count);
        log.info("end method getGrid");
        return result;
    }

    @RequestMapping("/ts_add")
    public GridResult tsAdd(FLearndata bean)
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

                success = fLearndataService.tsInsert(bean) > 0;
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
    public GridResult tsUpdate(FLearndata bean)
    {
        GridResult result = new GridResult();
        boolean success = Constant.FAILURE_FLAG;
        String msg = Constant.FAILURE_MSG;

        try
        {
            if(null != bean && null != bean.getId() && !bean.getId().isEmpty())
            {
                // TODO 其他处理

                success = fLearndataService.tsUpdate(bean) > 0;
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
        List<FLearndata> list = new ArrayList<FLearndata>();

        try
        {
            if (null != ids && ids.length > 0)
            {
                FLearndata fLearndata;
                for(String id : ids)
                {
                    fLearndata = new FLearndata();
                    fLearndata.setId(id);
                    list.add(fLearndata);
                }

                success = fLearndataService.tsDelete(list) > 0;
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
     * 发布文章
     */
    @RequestMapping("/tsAddLearn")
	@ResponseBody
	public GridResult tsAddLearn(@RequestParam(value = "file", required = true) MultipartFile file, FLearndata bean,
			HttpServletRequest request) {
		GridResult result = new GridResult();
		boolean success = Constant.FAILURE_FLAG;
		String msg = Constant.FAILURE_MSG;

		try {
			if (null != bean) {
				bean.setId(SystemUtil.getUUID());

				// TODO 其他字段的添加
				if (file != null) {
					String path = request.getSession().getServletContext().getRealPath("upload");
					String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
					String fileName = System.currentTimeMillis() + type;
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try {
						file.transferTo(targetFile);
						bean.setFilepath("upload/"+fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				success = fLearndataService.tsInsert(bean) > 0;
				if (success) {
					msg = Constant.SUCCESS_MSG;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.setSuccess(success);
		result.setMsg(msg);
		return result;
	}
}
