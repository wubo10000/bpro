/*
 * 文件名：LogServiceImpl.java
 * 版权：Copyright 2006-2014 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LogServiceImpl.java
 * 修改人：lxh
 * 修改时间：2014年1月23日
 * 修改内容：新增
 */
package com.bms.system.inteface.impl;

import com.bms.system.inteface.LogService;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class LogServiceImpl implements LogService
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(LogServiceImpl.class);

    @Override
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable
    {
        log.info("arter return");
    }

    @Override
    public void afterThrowing(Method method, Object[] args, Object target,
            Exception ex)
    {
        log.info("arter Throwing");
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable
    {
        log.info("before");
    }

}
