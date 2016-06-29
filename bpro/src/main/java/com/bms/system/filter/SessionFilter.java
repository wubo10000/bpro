/*
 * 文件名：AuthorityFilter.java
 * 版权：Copyright 2006-2013 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： AuthorityFilter.java
 * 修改人：lxh
 * 修改时间：2013年11月4日
 * 修改内容：新增
 */
package com.bms.system.filter;

import com.bms.system.inteface.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter
{

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest res = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;
        HttpSession session = res.getSession();
        Object obj = session.getAttribute("ts_user");
        String url = res.getRequestURI();

        if (res.getHeader("x-requested-with") != null
                && res.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
        {
            if (null == obj && (!url.contains("/ts_login.l")) && (url.contains("ts_"))
                    && (!url.contains("/ts_logOut.l")))
            {
                rsp.setHeader("sessionstatus", "timeout");
            }
            else
            {
                chain.doFilter(request, response);
            }
        }
        else
        {
            if (null != obj)
            {
                chain.doFilter(request, response);
            }
            else if ((!url.contains("/ts_login.l")) && (url.contains("ts_"))
                    && (!url.contains("/ts_logOut.l")))
            {
                rsp.sendRedirect("http://" + res.getHeader("Host") + "/" + Constant.PROJECT_NAME + "/tUser/ts_login.l");
            }
            else
            {
                chain.doFilter(request, response);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException
    {

        //SicUtil.addTcOperationLog(1, 6, "启动tomcat服务！");
    }

    @Override
    public void destroy()
    {

    }

}
