package com.bms.system.util;

public class DataValidation
{

    /**
     * 非空字符串校验
     *
     * @param col String类型
     * @return boolean true:非空;false:空
     */
    public static boolean valCol(String col)
    {
        return null != col && !col.isEmpty();
    }

}
