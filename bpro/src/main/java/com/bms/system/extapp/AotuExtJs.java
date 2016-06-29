package com.bms.system.extapp;

import com.bms.system.extapp.bean.DbSchema;
import com.bms.system.inteface.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @description 自动生成extjs
 */
public class AotuExtJs
{
    public static void genExtCode()
    {
        List<DbSchema> list = DataBaseUtil.getTablesName(Constant.MYSQL_DRIVER, Constant.URL, Constant.USERNAME, Constant.PWD);

        for (DbSchema dbSchema : list)
        {
            CreateBean.createBean(dbSchema, Constant.EXTJS_GENERATE_CODE_PATH);
            CreateBean.createStore(dbSchema, Constant.EXTJS_GENERATE_CODE_PATH);
            CreateBean.createView(dbSchema, Constant.EXTJS_GENERATE_CODE_PATH);
            CreateBean.createController(dbSchema, Constant.EXTJS_GENERATE_CODE_PATH);
        }
    }

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
        catch (Exception e)
        {
            e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return file;
    }
}