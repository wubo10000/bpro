package com.bms.system.util;

import com.bms.system.extapp.AotuExtJs;
import com.bms.system.http.HttpUtil;
import com.bms.system.main.QueryTableTest;
import com.bms.tmenu.dao.holder.TMenu;
import com.bms.trolemenu.dao.holder.TRoleMenu;
import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterConfig;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvxh
 * @version sic 1.0.0 2014年9月24日
 * @since sic 1.0.0
 */
public class SystemUtil
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(SystemUtil.class);

    private static final Lock LOCK = new ReentrantLock();

    private static ApplicationContext ctx;

    private static Gson gson = new Gson();

    public static final ExecutorService pool = Executors.newCachedThreadPool();

    public static Lock getLock()
    {
        return LOCK;
    }

    /**
     * 关闭此流并释放与此流关联的所有系统资源。
     *
     * @param obj 是可以关闭的数据源或目标
     */
    public static void close(AutoCloseable obj)
    {
        if (obj != null)
        {
            try
            {
                obj.close();
            }
            catch (Exception e)
            {

                log.error("Failed to close", e);
            }
        }
    }

    /**
     * 时间类型转换为字符串类型
     *
     * @param date   需要转换的时间对象
     * @param format 格式
     * @return 格式化之后的字符串
     */
    public static String dateFormat(Date date, String format)
    {
        if (date == null)
        {
            return "";
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    /**
     * 时间字符串转换为时间类型
     *
     * @param date   需要转换的时间字符串
     * @param format 格式
     * @return 格式化之后的时间对象
     */
    public static Date dateFormat(String date, String format)
    {
        Date time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            time = sdf.parse(date);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        return time;
    }

    private static void initCTX(FilterConfig config)
    {
        ctx = WebApplicationContextUtils.getWebApplicationContext(config
                .getServletContext());
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name)
    {
        return (T) ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz)
    {
        return ctx.getBean(clazz);
    }

    /**
     * 效应字段是否有值
     *
     * @param col 效应字段
     * @return 是否有值
     */
    public static boolean validate(String col)
    {
        boolean temp = false;
        if (null != col && !col.isEmpty())
        {
            temp = true;
        }
        return temp;
    }

    /**
     * 对象转换JSON串
     *
     * @param obj 对象
     * @return JSON对象
     */
    public static String ObjectToJson(Object obj)
    {
        Gson gson = new Gson();
        if (null != obj)
        {
            return gson.toJson(obj);
        }
        return null;
    }

    public static String getJson(List<TMenu> list)
    {
        StringBuffer json = new StringBuffer("[");

        List<TMenu> rootlist = new ArrayList<TMenu>();
        List<TMenu> chaillist = new ArrayList<TMenu>();
        List<TMenu> lastlist = new ArrayList<TMenu>();

        TMenu root;
        for (TMenu menu : list)
        {
            if (menu.getLevel() == 1)
            {
                rootlist.add(menu);
            }
            else if (menu.getLevel() == 2)
            {
                chaillist.add(menu);
            }
            else if (menu.getLevel() == 3)
            {
                lastlist.add(menu);
            }
        }

        for (int i = 0; i < rootlist.size(); i++)
        {
            root = rootlist.get(i);
            String temps = "";

            json.append("{\"text\":\"" + root.getName()
                    + "\",\"priority\":" + root.getPriority()
                    + ",\"iconCls\":\"" + root.getImgUrl() + "\",\"expanded\":"
                    + (i == 0 ? true : false) + ",\"children\":[");

            TMenu tu;
            for (int j = 0; j < chaillist.size(); j++)
            {
                tu = chaillist.get(j);
                boolean isleaf = true;
                String last = "";

                if (root.getId().equals(tu.getParentId()))
                {

                    TMenu tus;
                    for (int k = 0; k < lastlist.size(); k++)
                    {
                        tus = lastlist.get(k);
                        if (tu.getId().equals(tus.getParentId()))
                        {
                            isleaf = false;

                            last = last + "{\"text\":\"" + tus.getName() + "\",\"priority\":" + tus.getPriority() + ","
                                    + "\"leaf\":true,"
                                    + "\"iconCls\":\"" + tus.getImgUrl() + "\","
                                    + "\"config\":\"" + tus.getConfig() + "\"},";
                        }
                    }

                    temps = temps + "{\"text\":\"" + tu.getName() + "\",\"priority\":" + tu.getPriority() + ","
                            + "\"leaf\":" + isleaf
                            + ",\"iconCls\":\"" + tu.getImgUrl() + "\","
                            + "\"config\":\"" + tu.getConfig() + "\",\"children\":[";


                    if (last.length() > 1)
                    {
                        temps = temps + (last.length() > 0 ? (last.substring(0, last.length() - 1)) : "") + "]},";
                    }
                    else
                    {
                        temps = temps + "]},";
                    }
                }
            }

            json.append((temps.length() > 0 ? (temps.substring(0,
                    temps.length() - 1)) : "") + "]},");
        }

        return json.substring(0, json.length() - 1) + "]";
    }

    public static String getTreeJson(List<TMenu> list, List<TRoleMenu> selectMenus)
    {
        StringBuffer json = new StringBuffer("[");

        List<TMenu> rootlist = new ArrayList<TMenu>();
        List<TMenu> chaillist = new ArrayList<TMenu>();
        List<TMenu> lastlist = new ArrayList<TMenu>();

        List<String> selectMenuIds = new ArrayList<String>();
        for (TRoleMenu roleMenu : selectMenus)
        {
            selectMenuIds.add(roleMenu.getMenuId());
        }

        TMenu root = null;
        for (TMenu menu : list)
        {
            if (null != menu && null != menu.getLevel())
            {
                if (menu.getLevel() == 1)
                {
                    rootlist.add(menu);
                }
                else if (menu.getLevel() == 2)
                {
                    chaillist.add(menu);
                }
                else if (menu.getLevel() == 3)
                {
                    lastlist.add(menu);
                }
            }
        }

        for (int i = 0; i < rootlist.size(); i++)
        {
            root = rootlist.get(i);
            String temps = "";

            json.append("{\"text\":\"" + root.getName()
                    + "\",\"id\":\"" + root.getId() + "\",\"priority\":" + root.getPriority()
                    + ",\"iconCls\":\"" + root.getImgUrl() + "\",\"expanded\":"
                    + (i == 0 ? true : false) + ",\"checked\":"+(selectMenuIds.contains(root.getId()))+",\"children\":[");

            for (TMenu tu : chaillist)
            {
                boolean isleaf = true;
                String last = "";

                if (root.getId().equals(tu.getParentId()))
                {

                    for (TMenu tus : lastlist)
                    {
                        if (tu.getId().equals(tus.getParentId()))
                        {
                            isleaf = false;

                            last = last + "{\"text\":\"" + tus.getName() + "\",\"priority\":" + tus.getPriority() + ","
                                    + "\"leaf\":true,\"id\":\"" + tus.getId() + "\","
                                    + "\"iconCls\":\"" + tus.getImgUrl() + "\",\"checked\":"+(selectMenuIds.contains(root.getId()))+","
                                    + "\"config\":\"" + tus.getConfig() + "\"},";
                        }
                    }

                    temps = temps + "{\"text\":\"" + tu.getName() + "\",\"priority\":" + tu.getPriority() + ","
                            + "\"leaf\":" + isleaf + ",\"id\":\"" + tu.getId() + "\""
                            + ",\"iconCls\":\"" + tu.getImgUrl() + "\",\"checked\":"+(selectMenuIds.contains(root.getId()))+","
                            + "\"config\":\"" + tu.getConfig() + "\",\"children\":[";


                    if (last.length() > 1)
                    {
                        temps = temps + (last.length() > 0 ? (last.substring(0, last.length() - 1)) : "") + "]},";
                    }
                    else
                    {
                        temps = temps + "]},";
                    }
                }
            }

            json.append((temps.length() > 0 ? (temps.substring(0,
                    temps.length() - 1)) : "") + "]},");
        }

        return json.substring(0, json.length() - 1) + "]";
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 解析排序json信息
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl)
    {
        Object obj = null;
        if (gson != null)
        {
            obj = gson.fromJson(jsonStr, cl);
        }

        return obj;
    }

    public static void main(String[] args) throws IOException
    {
        // 生成extjs和java代码，勿删
       // AotuExtJs.genExtCode();
        QueryTableTest.genJavaCode();
    }
}