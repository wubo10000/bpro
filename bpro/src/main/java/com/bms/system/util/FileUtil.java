package com.bms.system.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * @author lxh
 * @version expweb 1.0.0 2013年11月4日
 * @since expweb 1.0.0
 */
public class FileUtil
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(FileUtil.class);

    public static void createFile(String filePath, String content)
    {
        String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));

        File file = newFile(filePath, dirPath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try
        {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(content);
            osw.flush();
        }
        catch (IOException e)
        {
            log.error("", e);
        }
        finally
        {
            if (osw != null)
            {
                try
                {
                    osw.close();
                }
                catch (IOException e)
                {
                    log.error("", e);
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    log.error("", e);
                }
            }

        }

    }

    public static void createFile(String filePath, Properties pro)
    {
        String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));

        File file = newFile(filePath, dirPath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try
        {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            pro.store(osw, "wms");
            osw.flush();
        }
        catch (IOException e)
        {
            log.error("", e);
        }
        finally
        {
            if (osw != null)
            {
                try
                {
                    osw.close();
                }
                catch (IOException e)
                {
                    log.error("", e);
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    log.error("", e);
                }
            }

        }

    }

    public static File newFile(String filePath, String dirPath)
    {
        File dir = new File(dirPath);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists())
        {
            try
            {

                file.createNewFile();
            }
            catch (IOException e)
            {
                log.error("", e);
            }
        }
        return file;
    }
}
