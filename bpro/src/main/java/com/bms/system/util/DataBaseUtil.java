/*
 * 文件名：DbUtil.java
 * 版权：Copyright 2006-2013 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： DbUtil.java
 * 修改人：lxh
 * 修改时间：2013年11月4日
 * 修改内容：新增
 */
package com.bms.system.util;

import com.bms.system.bean.DbSchema;
import com.bms.system.bean.Table;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(DataBaseUtil.class);

    public static boolean SYNC_NDFZ_STATUS = false;

    public static Connection getConnection(String driver, String url,
                                           String userName, String pwd)
    {
        log.debug("connection to database the params is: Driver[" + driver
                + "] url [" + url + " ] userName [" + userName + " ] pwd ["
                + pwd + "]");
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, pwd);
        }
        catch (ClassNotFoundException e)
        {
            log.error("", e);
        }
        catch (SQLException e)
        {
            log.error("", e);
        }
        log.debug("connection to database result is: " + con);
        return con;
    }

    public static ResultSet query(String sql, Connection con, Statement ps)
    {
        ResultSet result = null;
        try
        {
            result = ps.executeQuery(sql);
        }
        catch (SQLException e)
        {
            log.error("", e);
        }

        return result;
    }

    public static ResultSet query(String sql, Connection con, List<Object> list)
    {
        ResultSet result = null;
        PreparedStatement ps = null;
        try
        {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++)
            {
                ps.setObject(i + 1, list.get(i));

            }
            result = ps.executeQuery();
        }
        catch (SQLException e)
        {
            log.error("", e);
        }

        return result;
    }

    public static void close(Object obj)
    {
        if (obj == null)
        {
            return;
        }
        if (obj instanceof Connection)
        {
            try
            {
                ((Connection) obj).close();
            }
            catch (SQLException e)
            {
                log.error("", e);
            }
        }
        else if (obj instanceof Statement)
        {
            try
            {
                ((Statement) obj).close();
            }
            catch (SQLException e)
            {
                log.error("", e);
            }
        }
        else if (obj instanceof ResultSet)
        {
            try
            {
                ((ResultSet) obj).close();
            }
            catch (SQLException e)
            {
                log.error("", e);
            }
        }
    }

    public static List<DbSchema> getTablesName(String driver, String url,
                                               String username, String pwd)
    {
        Connection con = DataBaseUtil.getConnection(driver, url, username, pwd);
        String sql = "show tables";
        //sql = "SELECT table_name FROM user_tables";
        PreparedStatement ps = null;
        ResultSet result = null;
        List<DbSchema> list = new ArrayList<DbSchema>();
        try
        {
            ps = con.prepareStatement(sql);
            result = DataBaseUtil.query(sql, con, ps);
            while (result.next())
            {
                DbSchema bean = new DbSchema();
                bean.setTabName(result.getString(1));
                bean.setClassName(tabNameToClassName(bean.getTabName()));
                bean.setList(getTablesProperty(bean.getTabName(), driver, url,
                        username, pwd));
                log.info(bean.getTabName());
                list.add(bean);
            }
        }
        catch (SQLException e)
        {

            log.error("", e);
        }
        finally
        {
            DataBaseUtil.close(result);
            DataBaseUtil.close(ps);
            DataBaseUtil.close(con);
        }
        return list;
    }

    public static List<Table> getTablesProperty(String tableName,
                                                String driver, String url, String username, String pwd)
    {
        Connection con = DataBaseUtil.getConnection(driver, url, username, pwd);
        String sql = "describe " + tableName;
        //sql = "select COLUMN_NAME,DATA_TYPE,NULLABLE, NULL , DATA_DEFAULT, NULL from user_tab_columns WHERE table_name = '"+tableName+"'";
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Table> list = new ArrayList<Table>();
        try
        {
            ps = con.prepareStatement(sql);
            result = DataBaseUtil.query(sql, con, ps);
            while (result.next())
            {
                Table bean = new Table();
                bean.setColnumName(result.getString(1));
                bean.setIsNull(result.getString(3));
                bean.setKey(result.getString(4));
                bean.setDefaultValue(result.getString(5));
                bean.setExtra(result.getString(6));

                String type = result.getString(2);
                if (type.contains("("))
                {
                    bean.setColnumType(type.substring(0, type.indexOf("(")));
                    try
                    {
                        bean.setLength(Integer.parseInt(type.substring(
                                type.indexOf("(") + 1, type.indexOf(")"))
                                .trim()));
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    bean.setColnumType(type);
                    bean.setLength(0);
                }
                bean.setAttrName(tabNameToAttrName(bean.getColnumName()));
                bean.setAttrType(dbTypeToJavaType(bean.getColnumType()));

                list.add(bean);
            }
        }
        catch (SQLException e)
        {

            log.error("", e);
        }
        finally
        {
            DataBaseUtil.close(result);
            DataBaseUtil.close(ps);
            DataBaseUtil.close(con);
        }
        return list;
    }

    public static String dbTypeToJavaType(String type)
    {
        type = type.toLowerCase();
        if (type.contains("char"))
        {
            return "String";
        }
        if (type.contains("int"))
        {
            return "Integer";
        }
        if (type.contains("date"))
        {
            return "Date";
        }
        if (type.contains("time"))
        {
            return "String";
        }
        if (type.contains("decimal"))
        {
            return "Float";
        }
        if (type.contains("text"))
        {
            return "String";
        }
        else
            return type;
    }

    public static String tabNameToClassName(String tabName)
    {
        StringBuffer buf = new StringBuffer();
        String temp = tabName.toLowerCase();
        String str[] = temp.split("_");
        buf.append(headToUpperCase(str[0]));
        for (int i = 1; i < str.length; i++)
        {
            buf.append(headToUpperCase(str[i]));
        }

        log.debug("tabNameToClassName : [ " + buf.toString() + " ]");
        return buf.toString();
    }

    public static String tabNameToAttrName(String colnumName)
    {
        StringBuffer buf = new StringBuffer();
        String temp = colnumName.toLowerCase();
        String str[] = temp.split("_");
        buf.append(headToLowerCase(str[0]));
        for (int i = 1; i < str.length; i++)
        {
            buf.append(headToUpperCase(str[i]));
        }

        log.debug("tabNameToClassName : [ " + buf.toString() + " ]");
        return buf.toString();
    }

    public static String headToLowerCase(String str)
    {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String headToUpperCase(String str)
    {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
