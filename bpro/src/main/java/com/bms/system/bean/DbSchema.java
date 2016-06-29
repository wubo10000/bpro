/*
 * 文件名：DbSchema.java
 * 版权：Copyright 2006-2013 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： DbSchema.java
 * 修改人：lxh
 * 修改时间：2013年11月4日
 * 修改内容：新增
 */
package com.bms.system.bean;

import java.util.List;

public class DbSchema
{

    private String tabName;

    private String className;

    private List<Table> list;

    public String getTabName()
    {
        return tabName;
    }

    public void setTabName(String tabName)
    {
        this.tabName = tabName;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public List<Table> getList()
    {
        return list;
    }

    public void setList(List<Table> list)
    {
        this.list = list;
    }

}
