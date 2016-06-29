package com.bms.system.filter;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageServlet extends HttpServlet
{

    private static final Logger log = Logger.getLogger(ImageServlet.class);

    private static final long serialVersionUID = -5382766069139170499L;

    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        try
        {
            CaptchaSingleton.getInstance().writeCaptchaImage(httpServletRequest, httpServletResponse);
            // log.info("get image success !");
        }
        catch (Exception e)
        {
            log.error("get image error  ! ", e);
        }
    }

    public void init(ServletConfig servletConfig) throws ServletException
    {

        super.init(servletConfig);
    }
}
