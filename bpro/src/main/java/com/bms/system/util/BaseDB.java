package com.bms.system.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseDB
{

    private static final Logger log = Logger.getLogger(BaseDB.class);

    private DBManager manager;

    private PreparedStatement statement;

    public BaseDB(DBManager manager)
    {
        this.manager = manager;
        // this.conn = manager.getConnection().getConnection();
    }

    public DBManager getManager()
    {
        return manager;
    }

    public void setManager(DBManager manager)
    {
        this.manager = manager;
    }

    public PreparedStatement getStatement()
    {
        return statement;
    }

    public void setStatement(PreparedStatement statement)
    {
        this.statement = statement;
    }

    /**
     * 获取结果集
     *
     * @param sql
     * @return HashMap
     */
    public ResultSet getResult(String sql, Object[] obj)
    {
        Connection conn = null;
        ResultSet rs = null;
        try
        {

            conn = manager.getConnection().getConnection();
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++)
            {
                statement.setObject(i + 1, obj[i]);
            }
            rs = statement.executeQuery();

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            if (statement != null)
            {
                SicUtil.close(statement);
            }
            manager.release(conn);
        }
        return rs;
    }

    /**
     * 获取结果集 获取多行数据
     *
     * @param sql
     * @return HashMap
     */
    public List<HashMap<String, Object>> getLists(String sql, Object[] obj)
    {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        Connection conn = null;
        ResultSet rs = null;
        try
        {
            conn = manager.getConnection().getConnection();
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++)
            {
                statement.setObject(i + 1, obj[i]);
            }
            rs = statement.executeQuery();
            if (null != rs)
            {

                while (rs.next())
                {
                    HashMap<String, Object> tmp = new HashMap<String, Object>();
                    ResultSetMetaData meta = rs.getMetaData();
                    int len = meta.getColumnCount();
                    for (int j = 1; j <= len; j++)
                    {
                        String colName = meta.getColumnName(j);
                        try
                        {
                            String val = rs.getString(colName);
                            if (val == null)
                            {
                                val = "";
                            }
                            tmp.put(colName, val);
                        }
                        catch (Exception ex)
                        {
                            log.error("字段获取失败 name=" + colName, ex);

                        }
                    }
                    list.add(tmp);
                }
            }

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            if (rs != null)
            {
                SystemUtil.close(rs);
            }
            if (statement != null)
            {
                SystemUtil.close(statement);
            }
            if (conn != null)
            {
                manager.release(conn);
            }
        }
        return list;
    }

    /**
     * 获取结果集 获取单行数据
     */
    public HashMap<String, Object> getOne(String sql, Object[] obj)
    {

        List<HashMap<String, Object>> list = getLists(sql, obj);
        if (list != null && !list.isEmpty())
        {
            return list.get(0);
        }
        return null;

    }

}
