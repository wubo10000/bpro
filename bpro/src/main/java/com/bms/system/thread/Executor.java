package com.bms.system.thread;

import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

/**
 * @version: 1.0.0
 * @author: zhaoyi
 * @data: 2016/5/16 10:29
 * @description: 线程执行
 */
public class Executor
{
    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    public void exec()
    {
        try
        {
            taskExecutor.execute(new Runnable()
            {
                public void run()
                {
                    //这里编写处理业务代码

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
