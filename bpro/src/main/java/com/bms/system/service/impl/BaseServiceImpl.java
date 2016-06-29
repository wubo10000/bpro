package com.bms.system.service.impl;

import com.bms.system.service.BaseService;

import java.util.List;

/**
 * @version: 1.0.0
 * @author: zhaoyi
 * @data: 2016/5/18 17:58
 * @description:
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>
{
    @Override
    public int tsDelete(T bean)
    {
        return 0;
    }

    @Override
    public int tsDelete(List<T> list)
    {
        return 0;
    }

    @Override
    public int tsInsert(T bean)
    {
        return 0;
    }

    @Override
    public int tsUpdate(T bean)
    {
        return 0;
    }

    @Override
    public int getCountOfSummary(T bean)
    {
        return 0;
    }

    @Override
    public List<T> getList(T bean)
    {
        return null;
    }

    @Override
    public List<T> getListOfSummary(T bean)
    {
        return null;
    }
}
