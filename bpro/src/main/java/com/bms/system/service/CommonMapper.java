package com.bms.system.service;

import java.util.List;

/**
 * @version: 1.0.0
 * @author: zhaoyi
 * @data: 2016/5/18 18:02
 * @description:
 */
public interface CommonMapper<T>
{
    int delete(T bean);

    int insert(T bean);

    int update(T bean);

    int getCountOfSummary(T bean);

    List<T> getList(T bean);

    List<T> getListOfSummary(T bean);
}
