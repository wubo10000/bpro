package com.bms.system.bean;

import java.io.Serializable;

/**
 * 分组查询的结果bean
 */
public class ResultGroupBean implements Serializable
{
    private static final long serialVersionUID = 9107912382482223172L;

    private String geneticRoot;
    private String usageDescribtion;
    private Integer numbers;
    private String groupName;

    public String getGeneticRoot()
    {
        return geneticRoot;
    }

    public void setGeneticRoot(String geneticRoot)
    {
        this.geneticRoot = geneticRoot;
    }

    public String getUsageDescribtion()
    {
        return usageDescribtion;
    }

    public void setUsageDescribtion(String usageDescribtion)
    {
        this.usageDescribtion = usageDescribtion;
    }

    public Integer getNumbers()
    {
        return numbers;
    }

    public void setNumbers(Integer numbers)
    {
        this.numbers = numbers;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
}
