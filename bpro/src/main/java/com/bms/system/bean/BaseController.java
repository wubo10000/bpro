/*
 * 文件名：BaseController.java
 * 版权：Copyright 2006-2014 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： BaseController.java
 * 修改人：lvxh
 * 修改时间：2014年5月7日
 * 修改内容：新增
 */
package com.bms.system.bean;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class BaseController extends MultiActionController
{

    protected static final Logger LOG = Logger.getLogger(BaseController.class);

    protected String getMessage(String key)
    {
        ApplicationContext contxt = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        return contxt.getMessage(key, new Object[]{}, Locale.CHINA);
    }

    protected String getMessage(String key, Object[] values)
    {
        ApplicationContext contxt = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        return contxt.getMessage(key, values, Locale.CHINA);
    }

    /**
     * 取得request对象
     */
    protected HttpServletRequest getRequest()
    {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 取得session对象
     */
    protected HttpSession getSession()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request.getSession();
    }

    protected int procesPage(BaseHolder seq, int count)
    {
        int sum;
        //seq.setTotalCount(count);
        //seq.setPerNumber(10);
        if (count / seq.getPage() == 0 || count % seq.getPerNumber() == 0)
        {
            sum = count / seq.getPerNumber();
        }
        else
        {
            sum = count / seq.getPerNumber() + 1;
        }

        seq.setStartRecordNum((seq.getPage() - 1) * seq.getPerNumber());
        
        return sum;
    }
    
    /**
	 * 往页面写入数据
	 */
    public void write(String msg ,HttpServletResponse response){
    	response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.write(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public int getStart(BaseHolder bean, int total) {

		int totalPage = (int) Math.ceil(((double) total / (double) bean
				.getLimit()));
		if (bean.getPage() > totalPage) {
			bean.setPage(bean.getPage() - 1);
		}
        int temp = (bean.getPage() - 1) * bean.getLimit();
		return temp < 0 ? 0 : temp ;
	}

}
