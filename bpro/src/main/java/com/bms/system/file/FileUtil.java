package com.bms.system.file;

import com.bms.system.inteface.Constant;
import com.bms.system.util.DateTimeUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 文件操作工具类
 */
public class FileUtil
{

    public static String getWebPath()
    {
        String classPath = "";
        try
        {
            classPath = URLDecoder.decode(FileUtil.class.getResource("").toString(), "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        classPath = classPath.replace("/", File.separator).replace("\\", File.separator);
        classPath = classPath.substring(classPath.indexOf(File.separator) + 1);
        classPath = classPath.substring(0, classPath.length() - 1);
        String className = FileUtil.class.toString();

        while (className.contains("."))
        {
            classPath = classPath.substring(0, classPath.lastIndexOf(File.separator));
            className = className.substring(className.indexOf(".") + 1);
        }

        classPath = classPath.substring(0, classPath.lastIndexOf(File.separator));
        String path = classPath.substring(0, classPath.indexOf("WEB-INF"));
        if (isWindowsOS())
        {//win
            return path;
        }
        else
        {

            return "/" + path;
        }
    }

    /**
     * 获取文件的存储路径
     *
     * @return 路径
     */
    public static String getSystemPath()
    {
        String str = (getWebPath() + "html/fileimg/" + DateTimeUtil.getCurrentTime() + "/")
                .replace("/", File.separator).replace("\\", File.separator);
        return isWindowsOS() ? str : ("/" + str);
    }

    public static String getUploadPath(String subName)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getWebPath())
                .append("upload")
                .append(File.separator)
                .append(subName)
                .append(File.separator)
                .append(DateTimeUtil.getCurrentTime())
                .append(File.separator);
        String str = buffer.toString().replace("/", File.separator).replace("\\", File.separator);
        return isWindowsOS() ? str : ("/" + str);
    }

    /**
     * 扫描文件
     *
     * @param file 需要的扫描的文件
     * @return list
     */
    public static List<String> getPageCode(File file)
    {
        List<String> list = new ArrayList<String>();
        Scanner in = null;
        try
        {
            in = new Scanner(file);
            while (in.hasNextLine())
            {
                String str = in.nextLine();

                if (null != str && !str.isEmpty())
                {
                    list.add(str.trim().split("[\\p{Space}]+")[0]);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != in)
                in.close();
        }
        return list;
    }

    /**
     * 判断是否是windows平台
     *
     * @return true/false
     */
    public static boolean isWindowsOS()
    {
        boolean isWindwos = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows"))
        {
            isWindwos = true;
        }
        return isWindwos;
    }

    /**
     * 上传文件
     *
     * @param imgFile SpringMVC multipart
     * @return 文件在服务器上的相对路径
     */
    public static String uploadFile(MultipartFile imgFile)
    {
        String filePath = "";
        String fileName = DateTimeUtil.getCurrentDateTime();
        File file;

        try
        {
            file = new File(FileUtil.getSystemPath());
            if (!file.exists() && !file.isDirectory())
            {
                boolean flag = file.mkdirs();
            }

            String fileType = imgFile.getOriginalFilename();

            // 获取文件扩展名
            fileType = fileType.substring(fileType.lastIndexOf("."), fileType.length());

            InputStream stream = imgFile.getInputStream();
            OutputStream bos = new FileOutputStream(FileUtil.getSystemPath() + fileName + fileType);

            filePath = Constant.WEB_IMG_PATH + DateTimeUtil.getCurrentTime() + "/" + fileName + fileType;

            int bytesRead = 0;
            byte[] buffer = new byte[81920];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
            {
                bos.write(buffer, 0, bytesRead);
            }

            bos.close();
            stream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return filePath;
    }

    public static String uploadFile(MultipartFile imgFile, String subName)
    {
        int flag = -1;
        String fileName = DateTimeUtil.getCurrentDateTime();
        File file;

        try
        {
            file = new File(FileUtil.getUploadPath(subName));
            if (!file.exists() && !file.isDirectory())
            {
                file.mkdirs();
            }

            String fileType = imgFile.getOriginalFilename();

            // 文件扩展名
            fileType = fileType.substring(fileType.lastIndexOf("."), fileType.length());

            InputStream stream = imgFile.getInputStream();
            OutputStream bos = new FileOutputStream(FileUtil.getUploadPath(subName) + fileName + fileType);

            // 上传
            flag = IOUtils.copy(stream, bos);
            if (flag > 0){
                return fileName+fileType;
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 删除文件
     *
     * @param filePath 文件的路径
     * @return 删除操作是否成功
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        if (filePath != null)
        {
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
            File file = new File(FileUtil.getSystemPath() + fileName);
            if (file.exists())
            {
                flag = file.delete();
            }
        }

        return flag;
    }
    public static boolean deleteFile(String filePath, String subName)
    {
        boolean flag = false;
        if (filePath != null)
        {
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
            File file = new File(FileUtil.getUploadPath(subName) + fileName);
            if (file.exists())
            {
                flag = file.delete();
                if (!flag){
                    // 图片正被使用，强行删除
                    System.gc();
                    flag = file.delete();
                }
            }
        }

        return flag;
    }
}
