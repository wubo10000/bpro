/*
 * 文件名：LogService.java
 * 版权：Copyright 2006-2014 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LogService.java
 * 修改人：lxh
 * 修改时间：2014年1月23日
 * 修改内容：新增
 */
package com.bms.system.inteface;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * TODO 添加类的一句话简单描述。
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author lxh
 * @version expweb 1.0.0 2014年1月23日
 * @since expweb 1.0.0
 */
public interface LogService extends MethodBeforeAdvice, AfterReturningAdvice,
        ThrowsAdvice
{

    public void afterThrowing(Method m, Object[] args, Object target,
                              Exception ex);

}
