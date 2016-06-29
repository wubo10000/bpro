package com.bms.system.service;

import java.util.List;

/**
 * @version: 1.0.0
 * @author: zhaoyi
 * @data: 2016/5/18 17:46
 * @description:
 */
public interface BaseService<T>
{
    int tsDelete(T bean);

    int tsDelete(List<T> list);

    int tsInsert(T bean);

    int tsUpdate(T bean);

    int getCountOfSummary(T bean);

    List<T> getList(T bean);

    List<T> getListOfSummary(T bean);
}
