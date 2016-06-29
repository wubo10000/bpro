package com.bms.system.util;

import com.bms.system.util.ConnectionPool;
import com.bms.system.util.ConnectionPool.PooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager
{

    private ConnectionPool connectionPool;

    public void close()
    {
        try
        {
            connectionPool.closeConnectionPool();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public DBManager(String allURL, String driver)
    {
        this.connectionPool = new ConnectionPool(driver, allURL);
    }

    public PooledConnection getConnection()
    {

        return connectionPool.getConnection();
    }

    public Connection getConnections()
    {

        return getConnection().getConnection();
    }

    public void release(Connection conn)
    {
        connectionPool.returnConnection(conn);
    }

}
