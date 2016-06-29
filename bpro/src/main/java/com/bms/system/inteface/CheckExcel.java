package com.bms.system.inteface;

import java.util.Map;

/**
 * @author : zhaoyi
 * @version : 1.0.0
 * @date : 2016/3/30 14:01
 * @description : 检查excel中每一行的数据是否合法
 */
public interface CheckExcel
{
    /**
     * 返回true合法
     * @param data excel中每一行的数据
     * @return boolean
     */
    public boolean check(Map<String, String> data);
}
