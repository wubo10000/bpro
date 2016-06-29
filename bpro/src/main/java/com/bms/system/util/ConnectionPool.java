package com.bms.system.util;

import net.sourceforge.jtds.jdbc.ConnectionJDBC3;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;

public class ConnectionPool
{

    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    /** 数据库链接驱动 */
    private String jdbcDriver = "";

    /** 数据库完整URL */
    private String allURl = "";

    /** 测试的表名，默认为空 */
    private String testTable = "";

    /** 连接池的初始大小 */
    private Integer initialConnections = 10;

    /** 连接池自动增长的大小 */
    private Integer incrementalConnections = 5;

    /** 连接池的最大连接数 */
    private Integer maxConnections = 50;

    /** 存放连接池中数据库连接的向量 */
    private Vector<PooledConnection> connections = null;

    public ConnectionPool(String jdbcDriver, String allURl)
    {

        this.allURl = allURl;
        this.jdbcDriver = jdbcDriver;
        try
        {
            createPool();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 返回连接池的初始大小
     * 
     * @return 初始连接池中可获得的连接数量
     */
    public Integer getInitialConnections()
    {
        return this.initialConnections;
    }

    /**
     * 链接池的初始化大小
     * 
     */
    public void setInitialConnections(Integer initialConnections)
    {
        this.initialConnections = initialConnections;
    }

    /**
     * 返回连接池的自动增长的大小
     * 
     * @return 连接池自动增长的大小
     */
    public Integer getIncrementalConnections()
    {
        return this.incrementalConnections;
    }

    /**
     * 设置连接池自动增加的大小
     * 
     */
    public void setIncrementalConnections(Integer incrementalConnections)
    {
        this.incrementalConnections = incrementalConnections;
    }

    /**
     * 返回连接池中最大的可用连接数量
     * 
     * @return 连接池中最大的可用连接数量
     */
    public int getMaxConnections()
    {
        return this.maxConnections;
    }

    /**
     * 设置连接池中最大可用的连接数量
     * 
     */
    public void setMaxConnections(Integer maxConnections)
    {
        this.maxConnections = maxConnections;
    }

    /**
     * 获取测试数据库表的名字
     * 
     * @return 测试数据库表的名字
     */
    public String getTestTable()
    {
        return this.testTable;
    }

    /**
     * 设置测试表的名字
     * 
     * @param testTable
     *            <i>String 测试表的名字</i>
     */
    public void setTestTable(String testTable)
    {
        this.testTable = testTable;
    }

    /**
     * 创建一个数据库连接池，连接池中的可用连接的数量采用类成员
     * 
     * initialConnections 中设置的值
     */
    public synchronized void createPool() throws Exception
    {

        if (connections != null)
        {
            return;
        }

        Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
        DriverManager.registerDriver(driver); // 注册 JDBC 驱动程序

        connections = new Vector<PooledConnection>();
        createConnections(this.initialConnections);
        log.debug("create pool");
    }

    /**
     * 创建由 numConnections 指定数目的数据库连接 , 并把这些连接
     * 
     * 放入 connections 向量中
     * 
     * @param numConnections
     *            要创建的数据库连接的数目
     */
    private void createConnections(int numConnections) throws SQLException
    {

        for (int x = 0; x < numConnections; x++)
        {

            // log.info(this.connections.size() + "," + this.maxConnections);
            if (this.maxConnections > 0
                    && this.connections.size() >= this.maxConnections)
            {
                log.info("连接数己经达到最大");
                break;
            }

            try
            {
                connections.addElement(new PooledConnection(newConnections()));
                log.debug(" 数据库连接己创建 ......" + allURl);
            }
            catch (SQLException e)
            {
                log.error(" 创建数据库连接失败！ " + e.getMessage() + " url : "
                        + this.allURl);
            }
        }
    }

    private Connection newConnections() throws SQLException
    {

        Connection conn = (Connection) DriverManager.getConnection(allURl);
        if (connections.size() == 0)
        {

            DatabaseMetaData metaData = conn.getMetaData();

            Integer driverMaxConnections = metaData.getMaxConnections();

            if (driverMaxConnections > 0
                    && this.maxConnections > driverMaxConnections)
            {
                this.maxConnections = driverMaxConnections;
            }
        }
        return conn;
    }

    /**
     * 通过调用 getFreeConnection() 函数返回一个可用的数据库连接 ,
     * 
     * 如果当前没有可用的数据库连接，并且更多的数据库连接不能创
     * 
     * 建（如连接池大小的限制），此函数等待一会再尝试获取。
     * 
     * @return 返回一个可用的数据库连接对象
     */
    public synchronized PooledConnection getConnection()
    {
        PooledConnection conn = null;
        // 确保连接池己被创建
        if (connections != null)
        {
            conn = getFreeConnection(); // 获得一个可用的数据库连接
            /*
             * // 如果目前没有可以使用的连接，即所有的连接都在使用中 if (conn == null) { // 等一会再试 //
             * wait(250); //conn = getFreeConnection(); // 重新再试，直到获得可用的连接，如果 }
             */
        }

        return conn;// 返回获得的可用的连接
    }

    /**
     * 
     * 本函数从连接池向量 connections 中返回一个可用的的数据库连接，如果
     * 
     * 当前没有可用的数据库连接，本函数则根据 incrementalConnections 设置
     * 
     * 的值创建几个数据库连接，并放入连接池中。
     * 
     * 如果创建后，所有的连接仍都在使用中，则返回 null
     * 
     * @return 返回一个可用的数据库连接
     */
    public void print()
    {
        log.info("total connection:" + connections.size());
        int i = 1;
        for (PooledConnection conn : connections)
        {
            log.error("---" + i + ":" + conn.isBusy());
        }
    }

    private PooledConnection getFreeConnection()
    {
        PooledConnection conn = null;
        try
        {
            conn = findFreeConnection();
            if (conn == null)
            {
                // 如果目前连接池中没有可用的连接
                // 创建一些连接
                log.error("目前连接池中没有可用的连接,创建一些连接 ");
                createConnections(incrementalConnections);
                // 重新从池中查找是否有可用连接
                conn = findFreeConnection();

                if (conn == null)
                {
                    // 如果创建连接后仍获得不到可用的连接，则返回 null
                    return null;
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return conn;
    }

    /**
     * 
     * 查找连接池中所有的连接，查找一个可用的数据库连接，
     * 
     * 如果没有可用的连接，返回 null
     * 
     * @return 返回一个可用的数据库连接
     */
    private PooledConnection findFreeConnection() throws SQLException
    {
        // 获得连接池向量中所有的对象
        for (int i = 0; i < connections.size(); i++)
        {
            PooledConnection pc = connections.elementAt(i);
            if (!pc.isBusy())
            {
                // 如果此对象不忙，则获得它的数据库连接并把它设为忙
                Connection conn = pc.getConnection();
                pc.setBusy(true);
                // 测试此连接是否可用
                if (!isValid(conn))
                {
                    // 如果此连接不可再用了，则创建一个新的连接，
                    // 并替换此不可用的连接对象，如果创建失败，删除该无效连接，遍历下一个不忙连接
                    try
                    {
                        conn = newConnections();
                        pc.setConnection(conn);
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                        connections.remove(i--);
                        continue;
                    }
                }

                return pc;
            }
        }
        return null;
    }

    /**
     * 测试一个连接是否可用，如果不可用，关掉它并返回 false
     * 
     * 否则可用返回 true
     * 
     * @param conn
     *            需要测试的数据库连接
     * 
     * @return 返回 true 表示此连接可用， false 表示不可用
     */
    private boolean isValid(Connection conn)
    {
        try
        {
            if (conn instanceof ConnectionJDBC3)
            {
                return !conn.isClosed();
            }
            /*else if (conn instanceof JDBC4Connection)
            {
                return conn.isValid(3000);
            }*/
            else
                return false;

        }
        catch (SQLException e)
        {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 
     * 此函数返回一个数据库连接到连接池中，并把此连接置为空闲。
     * 
     * 所有使用连接池获得的数据库连接均应在不使用此连接时返回它。
     * 
     */
    public synchronized void returnConnection(Connection conn)
    {
        // 确保连接池存在，如果连接没有创建（不存在），直接返回
        if (connections == null)
        {
            log.error(" 连接池不存在，无法返回此连接到连接池中 !");
            return;
        }

        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();

        // 遍历连接池中的所有连接，找到这个要返回的连接对象
        while (enumerate.hasMoreElements())
        {
            pConn = (PooledConnection) enumerate.nextElement();

            if (conn == pConn.getConnection())
            {

                // 找到了 , 设置此连接为空闲状态
                pConn.setBusy(false);
                break;
            }
        }
    }

    /**
     * 刷新连接池中所有的连接对象
     */
    public synchronized void refreshConnections() throws SQLException
    {

        // 确保连接池己创新存在
        if (connections == null)
        {
            log.error(" 连接池不存在，无法刷新 !");
            return;
        }

        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();

        while (enumerate.hasMoreElements())
        {

            // 获得一个连接对象
            pConn = (PooledConnection) enumerate.nextElement();

            // 如果对象忙则等 2 秒 ,2秒后直接刷新
            if (pConn.isBusy())
            {
                wait(2000);
            }

            // 关闭此连接，用一个新的连接代替它。
            closeConnection(pConn.getConnection());
            pConn.setConnection(newConnections());
            pConn.setBusy(false);
        }

    }

    /**
     * 关闭连接池中所有的连接，并清空连接池。
     */
    public synchronized void closeConnectionPool() throws SQLException
    {

        if (connections == null)
        {
            log.error(" 连接池不存在，无法关闭 !");
            return;
        }

        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();

        while (enumerate.hasMoreElements())
        {

            pConn = (PooledConnection) enumerate.nextElement();
            if (pConn.isBusy())
            {
                wait(500);
            }
            closeConnection(pConn.getConnection());
            connections.removeElement(pConn);
        }

        connections = null;
    }

    /**
     * 关闭一个数据库连接
     * 
     */
    private void closeConnection(Connection conn)
    {

        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            log.error(" 关闭数据库连接出错： " + e.getMessage());
        }
    }

    /**
     * 使程序等待给定的毫秒数
     * 
     */
    private void wait(int mSeconds)
    {

        try
        {
            Thread.sleep(mSeconds);
            log.info("mSeconds : " + mSeconds);
        }
        catch (InterruptedException e)
        {
            log.error("eeeeee: ====> " + e.getMessage());
        }

    }

    /**
     * 内部使用的用于保存连接池中连接对象的类
     * 
     * 此类中有两个成员，一个是数据库的连接，另一个是指示此连接是否
     * 
     * 正在使用的标志。
     */
    class PooledConnection
    {

        /** 数据库连接 */
        private Connection connection = null;

        /** 此连接是否正在使用的标志，默认没有正在使用 */
        private boolean busy;

        /**
         * 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
         * 
         * @param connection
         */
        PooledConnection(Connection connection)
        {
            this.connection = connection;
        }

        ResultSet executeQuery(String sql) throws SQLException
        {
            return connection.createStatement().executeQuery(sql);
        }

        int executeUpdate(String sql) throws SQLException
        {
            return connection.createStatement().executeUpdate(sql);
        }

        Connection getConnection()
        {
            return connection;
        }

        void setConnection(Connection connection)
        {
            this.connection = connection;
        }

        boolean isBusy()
        {
            return busy;
        }

        void setBusy(boolean busy)
        {
            this.busy = busy;
        }

        void close()
        {
            busy = false;
        }
    }

}
