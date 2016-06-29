package com.bms.system.main;

import com.bms.system.bean.DbSchema;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DataBaseUtil;
import com.bms.system.util.JavaBeanUtil;

import java.util.List;

/**
 * @author lvxh
 * @description 根据表结构生成基础代码
 */
public class QueryTableTest
{
    public static void genJavaCode()
    {
        List<DbSchema> list = DataBaseUtil.getTablesName(Constant.MYSQL_DRIVER, Constant.URL, Constant.USERNAME, Constant.PWD);
        for (DbSchema dbSchema : list)
        {
            JavaBeanUtil.createJavaBean(dbSchema, Constant.JAVA_PACKAGE_NAME, Constant.JAVA_GENERATE_CODE_PATH);
            JavaBeanUtil.createController(dbSchema, Constant.JAVA_PACKAGE_NAME, Constant.JAVA_GENERATE_CODE_PATH);
            JavaBeanUtil.createImpl(dbSchema, Constant.JAVA_PACKAGE_NAME, Constant.JAVA_GENERATE_CODE_PATH);
            JavaBeanUtil.createMapper(dbSchema, Constant.JAVA_PACKAGE_NAME, Constant.JAVA_GENERATE_CODE_PATH);
            JavaBeanUtil.createMapperXml(dbSchema, Constant.JAVA_PACKAGE_NAME, Constant.JAVA_GENERATE_CODE_PATH);
            JavaBeanUtil.createService(dbSchema, Constant.JAVA_PACKAGE_NAME, Constant.JAVA_GENERATE_CODE_PATH);
            //JavaBeanUtil.createModifyJsp(dbSchema, jspPath);
            //JavaBeanUtil.createQueryJsp(dbSchema, jspPath, Constant.JAVA_PACKAGE_NAME);
            //JavaBeanUtil.createAddJsp(dbSchema, jspPath);
        }
    }
}