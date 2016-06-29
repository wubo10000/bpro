package com.bms.system.util;

import com.bms.system.bean.DbSchema;
import com.bms.system.bean.Table;

import java.util.*;

/**
 * @author lxh
 * @version expweb 1.0.0 2013年11月4日
 * @since expweb 1.0.0
 */
public class JavaBeanUtil
{

    public static void createProperties(List<DbSchema> list,
                                        String fileAboPath, String filePackage)
    {
        FileUtil.createFile(fileAboPath + "message.properties",
                getProContent(list, filePackage));
    }

    public static void createJavaBean(DbSchema bean, String packgePath,
                                      String filePath)
    {
        String path = filePath + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao.holder." + bean.getClassName();
        path = path.replaceAll("\\.", "/");
        FileUtil.createFile(path + ".java",
                getHolderBeanContent(bean, packgePath));
    }

    public static void createController(DbSchema bean, String packgePath,
                                        String filePath)
    {
        String path = filePath + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".controller." + bean.getClassName();
        path = path.replaceAll("\\.", "/");
        FileUtil.createFile(path + "Controller.java",
                getControllerFram(bean, packgePath));
    }

    public static void createService(DbSchema bean, String packgePath,
                                     String filePath)
    {
        String path = filePath + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".service." + bean.getClassName();
        path = path.replaceAll("\\.", "/");
        FileUtil.createFile(path + "Service.java",
                getServiceBean(bean, packgePath));
    }

    public static void createImpl(DbSchema bean, String packgePath,
                                  String filePath)
    {
        String path = filePath + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".service.impl." + bean.getClassName();
        path = path.replaceAll("\\.", "/");
        FileUtil.createFile(path + "ServiceImpl.java",
                getServiceImplBean(bean, packgePath));
    }

    public static void createMapper(DbSchema bean, String packgePath,
                                    String filePath)
    {
        String path = filePath + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao." + bean.getClassName();
        path = path.replaceAll("\\.", "/");
        FileUtil.createFile(path + "Mapper.java",
                getMapperBean(bean, packgePath));
    }

    public static void createMapperXml(DbSchema bean, String packgePath,
                                       String filePath)
    {
        String path = filePath + "mapper."
                + bean.getClassName().toLowerCase(Locale.getDefault()) + "."
                + bean.getClassName();
        path = path.replaceAll("\\.", "/");
        FileUtil.createFile(path + "Mapper.xml", getMapperXml(bean, packgePath));
    }

    public static void createQueryJsp(DbSchema bean, String filePath,
                                      String packagePath)
    {
        String path = filePath
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + "/view" + bean.getClassName() + ".jsp";
        String script = StrUtil.getQueryScript(bean);
        String condition = StrUtil.getQueryCondition(bean, packagePath);
        String thead = StrUtil.getQueryThead(bean);
        String template = StrUtil.getQueryTemplate(bean);
        String body = StrUtil.getQueryBody(condition, thead, template);
        FileUtil.createFile(path, StrUtil.getFram(script, body));
    }

    public static void createModifyJsp(DbSchema bean, String filePath)
    {
        String path = filePath
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + "/modify" + bean.getClassName() + ".jsp";

        String script = StrUtil.getModifyScript(bean);
        String content = StrUtil.getModifyContent(bean);
        String body = StrUtil.getModifyBody(content);
        FileUtil.createFile(path, StrUtil.getFram(script, body));
    }

    public static void createAddJsp(DbSchema bean, String filePath)
    {
        String path = filePath
                + bean.getClassName().toLowerCase(Locale.getDefault()) + "/add"
                + bean.getClassName() + ".jsp";

        String script = StrUtil.getAddScript(bean);
        String content = StrUtil.getAddContent(bean);
        String body = StrUtil.getAddBody(content);

        FileUtil.createFile(path, StrUtil.getFram(script, body));
    }

    private static String getHolderBeanContent(DbSchema bean, String basePath)
    {
        StringBuffer buf = new StringBuffer();
        // start holder class header
        buf.append("package " + basePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao.holder;");
        buf.append(System.getProperty("line.separator"));
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);
            if ("Date".equals(table.getAttrType()))
            {
                buf.append("import java.util.Date;");
                buf.append(System.getProperty("line.separator"));
                break;
            }
        }
        buf.append("import " + basePath + ".system.bean.BaseHolder;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + basePath + ".system.inteface.Constant;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + basePath + ".system.util.DateUtil;");
        buf.append(System.getProperty("line.separator"));
        // end holder class header

        // holder class name
        buf.append("public class " + bean.getClassName()
                + " extends BaseHolder");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        Random random = new Random();
        buf.append(" private static final long serialVersionUID = "
                + random.nextLong() + "L;");
        buf.append(System.getProperty("line.separator"));

        // holder class attr
        buf.append(getAttrContent(bean));
        buf.append(System.getProperty("line.separator"));

        // holder class get and set method
        buf.append(getGetSetMethodContent(bean));
        buf.append(System.getProperty("line.separator"));

        buf.append(getParentMethod(bean));
        buf.append(System.getProperty("line.separator"));

        buf.append("}");
        buf.append(System.getProperty("line.separator"));

        // log.info(buf.toString());
        return buf.toString();
    }

    private static String getGetSetMethodContent(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);
            buf.append(getSetMethod(table.getAttrName(), table.getAttrType()));
            buf.append(System.getProperty("line.separator"));
            buf.append(getGetMethod(table.getAttrName(), table.getAttrType()));
            buf.append(System.getProperty("line.separator"));
            if ("Date".equals(table.getAttrType()))
            {
                buf.append(getSetMethod(table.getAttrName() + "Str", "String"));
                buf.append(System.getProperty("line.separator"));
                buf.append(getGetMethod(table.getAttrName() + "Str", "String"));
                buf.append(System.getProperty("line.separator"));

                buf.append(getSetMethod(
                        "start" + StrUtil.headToUpperCase(table.getAttrName()),
                        table.getAttrType()));
                buf.append(System.getProperty("line.separator"));
                buf.append(getGetMethod(
                        "start" + StrUtil.headToUpperCase(table.getAttrName()),
                        table.getAttrType()));
                buf.append(System.getProperty("line.separator"));

                buf.append(getSetMethod(
                        "end" + StrUtil.headToUpperCase(table.getAttrName()),
                        table.getAttrType()));
                buf.append(System.getProperty("line.separator"));
                buf.append(getGetMethod(
                        "end" + StrUtil.headToUpperCase(table.getAttrName()),
                        table.getAttrType()));
                buf.append(System.getProperty("line.separator"));

                buf.append(getSetMethod(
                        "start" + StrUtil.headToUpperCase(table.getAttrName())
                                + "Str", "String"));
                buf.append(System.getProperty("line.separator"));
                buf.append(getGetMethod(
                        "start" + StrUtil.headToUpperCase(table.getAttrName())
                                + "Str", "String"));
                buf.append(System.getProperty("line.separator"));

                buf.append(getSetMethod(
                        "end" + StrUtil.headToUpperCase(table.getAttrName())
                                + "Str", "String"));
                buf.append(System.getProperty("line.separator"));
                buf.append(getGetMethod(
                        "end" + StrUtil.headToUpperCase(table.getAttrName())
                                + "Str", "String"));
                buf.append(System.getProperty("line.separator"));
            }
        }
        return buf.toString();
    }

    private static String getAttrContent(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);
            if ("Date".equals(table.getAttrType()))
            {
                buf.append("private " + table.getAttrType() + " "
                        + table.getAttrName() + ";");
                buf.append(System.getProperty("line.separator"));

                buf.append("private String " + table.getAttrName() + "Str;");
                buf.append(System.getProperty("line.separator"));

                buf.append("private " + table.getAttrType() + " start"
                        + StrUtil.headToUpperCase(table.getAttrName()) + ";");
                buf.append(System.getProperty("line.separator"));

                buf.append("private " + table.getAttrType() + " end"
                        + StrUtil.headToUpperCase(table.getAttrName()) + ";");
                buf.append(System.getProperty("line.separator"));

                buf.append("private String start"
                        + StrUtil.headToUpperCase(table.getAttrName()) + "Str;");
                buf.append(System.getProperty("line.separator"));

                buf.append("private String end"
                        + StrUtil.headToUpperCase(table.getAttrName()) + "Str;");
                buf.append(System.getProperty("line.separator"));

            }
            else
            {
                buf.append("private " + table.getAttrType() + " "
                        + table.getAttrName() + ";");
                buf.append(System.getProperty("line.separator"));
            }
        }
        return buf.toString();
    }

    private static String getSetMethod(String attr, String type)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("public void set" + StrUtil.headToUpperCase(attr) + "( "
                + type + " " + attr + " )");
        buf.append(System.getProperty("line.separator"));

        buf.append("{");
        buf.append(System.getProperty("line.separator"));

        buf.append("this." + attr + " = " + attr + ";");
        buf.append(System.getProperty("line.separator"));

        if ("Date".equals(type))
        {
            buf.append("this." + attr + "Str = DateUtil.dateToStr(" + attr
                    + ",Constant.DATE_FORMAT);");
            buf.append(System.getProperty("line.separator"));
        }

        buf.append("}");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getGetMethod(String attr, String type)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("public " + type + " get" + StrUtil.headToUpperCase(attr)
                + "()");
        buf.append(System.getProperty("line.separator"));

        buf.append("{");
        buf.append(System.getProperty("line.separator"));

        buf.append("return this." + attr + ";");
        buf.append(System.getProperty("line.separator"));

        buf.append("}");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getParentMethod(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("@Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("public int hashCode()");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append(" return this.getId().hashCode();");
        buf.append(System.getProperty("line.separator"));
        buf.append("}");
        buf.append(System.getProperty("line.separator"));

        buf.append("@Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("public boolean equals(Object obj)");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append("if(obj instanceof " + bean.getClassName() + ")");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append(" " + bean.getClassName() + " bean = ("
                + bean.getClassName() + ") obj;");
        buf.append(System.getProperty("line.separator"));
        buf.append("if(bean.getId().equals(this.getId()))");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append("   return true;");
        buf.append(System.getProperty("line.separator"));
        buf.append("}");
        buf.append(System.getProperty("line.separator"));
        buf.append(" else");
        buf.append(System.getProperty("line.separator"));
        buf.append(" {");
        buf.append(System.getProperty("line.separator"));
        buf.append("     return false;");
        buf.append(System.getProperty("line.separator"));
        buf.append(" }");
        buf.append(System.getProperty("line.separator"));
        buf.append("}");
        buf.append(System.getProperty("line.separator"));
        buf.append("else");
        buf.append(System.getProperty("line.separator"));
        buf.append(" {");
        buf.append(System.getProperty("line.separator"));
        buf.append("  return false;");
        buf.append(System.getProperty("line.separator"));
        buf.append("  }");
        buf.append(System.getProperty("line.separator"));
        buf.append(" }");
        buf.append(System.getProperty("line.separator"));

        buf.append("@Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("public String toString()");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append("StringBuffer buf =new StringBuffer();");
        buf.append(System.getProperty("line.separator"));
        for (int i = 0; i < bean.getList().size(); i++)
        {
            buf.append("buf.append(\" "
                    + bean.getList().get(i).getAttrName()
                    + ": [ \" + this.get"
                    + StrUtil.headToUpperCase(bean.getList().get(i)
                    .getAttrName()) + "() + \" ] \"); ");
            buf.append(System.getProperty("line.separator"));
            if ("Date".equals(bean.getList().get(i).getAttrType()))
            {
                buf.append("buf.append(\" "
                        + bean.getList().get(i).getAttrName()
                        + "Str: [ \" + this.get"
                        + StrUtil.headToUpperCase(bean.getList().get(i)
                        .getAttrName()) + "Str() + \" ] \"); ");
                buf.append(System.getProperty("line.separator"));

                buf.append("buf.append(\" start"
                        + StrUtil.headToUpperCase(bean.getList().get(i)
                        .getAttrName())
                        + "Str: [ \" + this.getStart"
                        + StrUtil.headToUpperCase(bean.getList().get(i)
                        .getAttrName()) + "Str() + \" ] \"); ");
                buf.append(System.getProperty("line.separator"));

                buf.append("buf.append(\" end"
                        + StrUtil.headToUpperCase(bean.getList().get(i)
                        .getAttrName())
                        + "Str: [ \" + this.getEnd"
                        + StrUtil.headToUpperCase(bean.getList().get(i)
                        .getAttrName()) + "Str() + \" ] \"); ");
                buf.append(System.getProperty("line.separator"));
            }
        }
        buf.append("return buf.toString();");
        buf.append(System.getProperty("line.separator"));
        buf.append(" }");
        buf.append(System.getProperty("line.separator"));
        return buf.toString();
    }

    private static String getControllerFram(DbSchema bean, String pack)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("package ").append(pack).append(".").append(bean.getClassName().toLowerCase(Locale.getDefault())).append(".controller;");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));

        buf.append("import java.util.List;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import java.util.ArrayList;");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));

        buf.append("import org.apache.log4j.Logger;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import org.springframework.beans.factory.annotation.Autowired;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import org.springframework.stereotype.Controller;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import org.springframework.web.bind.annotation.RequestMapping;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import org.springframework.web.bind.annotation.ResponseBody;");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));

        buf.append("import ").append(pack).append(".system.bean.BaseController;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".system.bean.GridResult;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".system.bean.PPException;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".system.inteface.Constant;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".system.util.SystemUtil;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".").append(bean.getClassName().toLowerCase(Locale.getDefault())).append(".dao.holder.").append(bean.getClassName()).append(";");
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".").append(bean.getClassName().toLowerCase(Locale.getDefault())).append(".service.").append(bean.getClassName()).append("Service;");
        buf.append(System.getProperty("line.separator"));
        /*buf.append("import " + pack + ".system.util.PPUtil;");*/
        buf.append(System.getProperty("line.separator"));
        buf.append("import ").append(pack).append(".system.bean.BaseHolder;");
        buf.append(System.getProperty("line.separator"));

        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("@RequestMapping(\"/").append(StrUtil.headToLowerCase(bean.getClassName())).append("\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("@Controller");
        buf.append(System.getProperty("line.separator"));
        buf.append("public class ").append(bean.getClassName()).append("Controller extends BaseController");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append("    private static final Logger log = Logger.getLogger(").append(bean.getClassName()).append("Controller.class);");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Autowired");
        buf.append(System.getProperty("line.separator"));
        buf.append("    private " + bean.getClassName() + "Service "
                + StrUtil.headToLowerCase(bean.getClassName()) + "Service;");
        buf.append(System.getProperty("line.separator"));
        List<String> list = getControllerMethod(bean);
        for (String item : list)
        {
            buf.append(item);
        }

        buf.append(System.getProperty("line.separator"));
        buf.append("}");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();

    }

    private static List<String> getControllerMethod(DbSchema bean)
    {
        List<String> list = new ArrayList<String>();
        //list.add(getInitView(bean));
        list.add(getGrid(bean));
        // list.add(getInitAdd(bean));
        list.add(getAdd(bean));
        // list.add(getInitModify(bean));
        list.add(getModify(bean));
        list.add(getDel(bean));
        // list.add(getDelAll(bean));
        return list;
    }

    private static String getInitView(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/init" + bean.getClassName() + "\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public ModelAndView init" + bean.getClassName() + "()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        //log.info(\"start method init"
                + bean.getClassName() + "\");");

        buf.append(System.getProperty("line.separator"));
        buf.append("        Map<String, BaseHolder> baseMap = new HashMap<String, BaseHolder>();");
        buf.append(System.getProperty("line.separator"));
        // buf.append("        baseMap.put(\"base\", PPUtil.initAuthFlag(getRequest(), getSession()));");

        buf.append(System.getProperty("line.separator"));
        buf.append("        //log.info(\"end method init" + bean.getClassName()
                + "\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return new ModelAndView(\""
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + "/view" + bean.getClassName() + "\",baseMap);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        return buf.toString();
    }

    private static String getGrid(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/getGrid\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @ResponseBody");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public GridResult getGrid(").append(bean.getClassName()).append(" bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        log.info(\"start method getGrid\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        GridResult result = new GridResult();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        int count = ").
                append(StrUtil.headToLowerCase(bean.getClassName())).
                append("Service.getCountOfSummary(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        bean.setStartRecordNum(getStart(bean, count));");
        buf.append(System.getProperty("line.separator"));
        buf.append("        bean.setPerNumber(bean.getLimit());");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setResult(").
                append(StrUtil.headToLowerCase(bean.getClassName())).
                append("Service.getListOfSummary(bean));");
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setTotalCount(count);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        log.info(\"end method getGrid\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return result;");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        return buf.toString();
    }

    public static String getInitAdd(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/initAdd\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public ModelAndView initAdd()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        //log.info(\"start method initAdd\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        //log.info(\"end method initAdd\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return new ModelAndView(\""
                + bean.getClassName().toLowerCase(Locale.getDefault()) + "/add"
                + bean.getClassName() + "\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        return buf.toString();
    }

    private static String getAdd(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/ts_add\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public GridResult tsAdd(" + bean.getClassName()
                + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        GridResult result = new GridResult();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        boolean success = Constant.FAILURE_FLAG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("        String msg = Constant.FAILURE_MSG;");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        try");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("             if (null != bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("             {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                bean.setId(SystemUtil.getUUID());");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("                // TODO 其他字段的添加");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("                success = ")
                .append(StrUtil.headToLowerCase(bean.getClassName())).
                append("Service.tsInsert(bean) > 0;");;
        buf.append(System.getProperty("line.separator"));
        buf.append("                if(success)");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    msg = Constant.SUCCESS_MSG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }");
        buf.append(System.getProperty("line.separator"));
        buf.append("              }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        catch (Exception e)");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            e.printStackTrace();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setSuccess(success);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setMsg(msg);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return result;");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");

        return buf.toString();
    }

    public static String getInitModify(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/initModify\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public ModelAndView initModify(String delFlag)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        //log.info(\"start method initModify\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        Map<String,Object>map = new HashMap<String,Object>();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        " + bean.getClassName() + " bean = new "
                + bean.getClassName() + "();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        bean.setId(Integer.parseInt(delFlag));");
        buf.append(System.getProperty("line.separator"));
        buf.append("        List <" + bean.getClassName() + ">list = "
                + StrUtil.headToLowerCase(bean.getClassName())
                + "Service.getList(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        if(list == null || list.isEmpty())");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            //log.info(\"end method initModify\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            return new ModelAndView(\"global/failed\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        else");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            map.put(\"bean\", list.get(0));");
        buf.append(System.getProperty("line.separator"));
        buf.append("            //log.info(\"end method initModify\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            return new ModelAndView(\""
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + "/modify" + bean.getClassName() + "\",map);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        return buf.toString();
    }

    private static String getModify(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/ts_update\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public GridResult tsUpdate(").append(bean.getClassName()).append(" bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        GridResult result = new GridResult();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        boolean success = Constant.FAILURE_FLAG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("        String msg = Constant.FAILURE_MSG;");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        try");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            if(null != bean && null != bean.getId() && !bean.getId().isEmpty())");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                // TODO 其他处理");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("                success = ").append(StrUtil.headToLowerCase(bean.getClassName())).append("Service.tsUpdate(bean) > 0;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                if(success)");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    msg = Constant.SUCCESS_MSG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }");
        buf.append(System.getProperty("line.separator"));
        buf.append("            }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        catch (Exception e)");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            e.printStackTrace();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setSuccess(success);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setMsg(msg);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return result;");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        return buf.toString();
    }

    public static String getDel(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("    @RequestMapping(\"/ts_del\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public GridResult tsDel(String ... ids)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        GridResult result = new GridResult();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        boolean success = Constant.FAILURE_FLAG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("        String msg = Constant.FAILURE_MSG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("        List<").append(bean.getClassName()).append("> list = new ArrayList<").append(bean.getClassName()).append(">();");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        try");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));

        buf.append("            if (null != ids && ids.length > 0)");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {");
        buf.append(System.getProperty("line.separator"));

        buf.append("                ").append(bean.getClassName()).append(" ").append(StrUtil.headToLowerCase(bean.getClassName())).append(";");
        buf.append(System.getProperty("line.separator"));
        buf.append("                for(String id : ids)");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    ").append(StrUtil.headToLowerCase(bean.getClassName())).append(" = new ").append(bean.getClassName()).append("();");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    ").append(StrUtil.headToLowerCase(bean.getClassName())).append(".setId(id);");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    list.add(").append(StrUtil.headToLowerCase(bean.getClassName())).append(");");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("                success = ").append(StrUtil.headToLowerCase(bean.getClassName())).append("Service.tsDelete(list) > 0;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                if(success)");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    msg = Constant.SUCCESS_MSG;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }");
        buf.append(System.getProperty("line.separator"));
        buf.append("            }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        catch (Exception e)");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            e.printStackTrace();");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setSuccess(success);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        result.setMsg(msg);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return result;");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        return buf.toString();
    }

    private static String getServiceBean(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("package " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".service;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import com.bms.system.service.BaseService;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao.holder." + bean.getClassName() + ";");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("public interface " + bean.getClassName() + "Service extends BaseService<"+ bean.getClassName() +"> {");
        buf.append(System.getProperty("line.separator"));
        buf.append("}");
        buf.append(System.getProperty("line.separator"));
        return buf.toString();
    }

    private static String getServiceImplBean(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("package " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".service.impl;");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("import java.util.List;");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + packgePath + ".system.bean.PPException;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao.holder." + bean.getClassName() + ";");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao." + bean.getClassName() + "Mapper;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".service." + bean.getClassName() + "Service;");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("import org.springframework.beans.factory.annotation.Autowired;");
        buf.append(System.getProperty("line.separator"));
        buf.append("import org.springframework.stereotype.Service;");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("@Service(\"" + StrUtil.headToLowerCase(bean.getClassName())
                + "Service\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("public class " + bean.getClassName()
                + "ServiceImpl implements " + bean.getClassName() + "Service");
        buf.append(System.getProperty("line.separator"));
        buf.append("{");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Autowired");
        buf.append(System.getProperty("line.separator"));
        buf.append("    private " + bean.getClassName() + "Mapper mapper;");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public int tsDelete(" + bean.getClassName() + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return mapper.delete(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public int tsUpdate(" + bean.getClassName() + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return mapper.update(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public int tsInsert(" + bean.getClassName() + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return mapper.insert(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public int getCountOfSummary(" + bean.getClassName()
                + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return mapper.getCountOfSummary(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public List<" + bean.getClassName() + "> getList("
                + bean.getClassName() + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return mapper.getList(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public List<" + bean.getClassName()
                + "> getListOfSummary(" + bean.getClassName() + " bean)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return mapper.getListOfSummary(bean);");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("");
        buf.append(System.getProperty("line.separator"));
        buf.append("    @Override");
        buf.append(System.getProperty("line.separator"));
        buf.append("    public int tsDelete(List<" + bean.getClassName()
                + "> list)");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("        for (int i = 0; i < list.size(); i++)");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            if (mapper.delete(list.get(i)) < 1)");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                throw new PPException(\"del \" + list.get(i) + \" error\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        return 1;");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("}");
        buf.append(System.getProperty("line.separator"));
        return buf.toString();
    }

    private static String getMapperBean(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("package " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao;");
        buf.append(System.getProperty("line.separator"));

        buf.append("import com.bms.system.service.CommonMapper;");
        buf.append(System.getProperty("line.separator"));

        buf.append("import " + packgePath + "."
                + bean.getClassName().toLowerCase(Locale.getDefault())
                + ".dao.holder." + bean.getClassName() + ";");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));

        buf.append("public interface ").append(bean.getClassName()).append("Mapper").append(" extends CommonMapper<").append(bean.getClassName()).append(">").append(" {");
        buf.append(System.getProperty("line.separator"));

        buf.append("}");
        buf.append(System.getProperty("line.separator"));
        return buf.toString();
    }

    private static String getMapperXml(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        buf.append(System.getProperty("line.separator"));

        buf.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
        buf.append("<mapper namespace=\"")
                .append(packgePath)
                .append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.")
                .append(bean.getClassName())
                .append("Mapper\">");
        buf.append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));

        buf.append(getMapperResult(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getBaseColnumList(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getWhereClause(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getInsert(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getUpdate(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getDelete(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getList(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getCountOfSummary(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append(getListOfSummary(bean, packgePath));
        buf.append(System.getProperty("line.separator"));

        buf.append("</mapper>  ");
        return buf.toString();
    }

    private static Object getListOfSummary(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<select id=\"getListOfSummary\" resultMap=\"BaseResultMap\" parameterType=\"")
                .append(packgePath)
                .append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.")
                .append(bean.getClassName())
                .append("\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\tselect <include refid=\"Base_Colnum_List\" /> ");
        buf.append("from " + bean.getTabName());
        buf.append(" <include refid=\"Where_Clause\" />");
        buf.append(System.getProperty("line.separator"));

        buf.append("\t<if test=\"sortField != null and sortField != ''\"> ");
        buf.append(System.getProperty("line.separator"));
        buf.append("\t\torder by ${sortField} ${sortOrder} ");
        buf.append(System.getProperty("line.separator"));
        buf.append("\t</if> ");
        buf.append(System.getProperty("line.separator"));
        buf.append("\tlimit ${startRecordNum},${perNumber} ");
        buf.append(System.getProperty("line.separator"));
        buf.append("</select> ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static Object getCountOfSummary(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<select id=\"getCountOfSummary\" resultType=\"int\" parameterType=\"")
                .append(packgePath).append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.")
                .append(bean.getClassName()).append("\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\tselect count(1) from ").append(bean.getTabName());
        buf.append(" <include refid=\"Where_Clause\" />");
        buf.append(System.getProperty("line.separator"));

        buf.append("</select> ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static Object getList(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<select id=\"getList\"  resultMap=\"BaseResultMap\" parameterType=\"")
                .append(packgePath)
                .append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.")
                .append(bean.getClassName())
                .append("\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\tselect <include refid=\"Base_Colnum_List\" /> from ");
        buf.append(bean.getTabName());
        buf.append(System.getProperty("line.separator"));

        buf.append("\t<include refid=\"Where_Clause\" />");
        buf.append(System.getProperty("line.separator"));
        buf.append("</select> ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getDelete(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<delete id=\"delete\" parameterType=\"")
                .append(packgePath).append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.")
                .append(bean.getClassName())
                .append("\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\tdelete from ")
                .append(bean.getTabName())
                .append(" <include refid=\"Where_Clause\" />");
        buf.append(System.getProperty("line.separator"));

        buf.append("</delete>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getUpdate(DbSchema bean, String packgePath)
    {

        StringBuffer buf = new StringBuffer();
        buf.append("<update id=\"update\" parameterType=\"")
                .append(packgePath)
                .append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.")
                .append(bean.getClassName())
                .append("\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\tupdate ").append(bean.getTabName()).append(" set");
        buf.append(System.getProperty("line.separator"));

        buf.append("\t<trim suffixOverrides=\",\">");
        buf.append(System.getProperty("line.separator"));

        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);

            if (!"id".equals(table.getAttrName()))
            {
                if ("String".equals(table.getAttrType()))
                {
                    buf.append("\t\t<if test=\"")
                            .append(table.getAttrName())
                            .append(" != null and ")
                            .append(table.getAttrName())
                            .append(" != ''\">");
                    buf.append(System.getProperty("line.separator"));
                }
                else
                {
                    buf.append("\t\t<if test=\"")
                            .append(table.getAttrName())
                            .append(" != null \">");
                    buf.append(System.getProperty("line.separator"));
                }

                buf.append("\t\t\t");
                if (i == (bean.getList().size() - 1))
                {
                    buf.append(table.getColnumName())
                            .append("=#{")
                            .append(table.getAttrName()).append("}");
                }
                else
                {
                    buf.append(table.getColnumName())
                            .append("=#{")
                            .append(table.getAttrName())
                            .append("},");
                }
                buf.append(System.getProperty("line.separator"));

                buf.append("\t\t</if>");
                buf.append(System.getProperty("line.separator"));
            }
        }
        buf.append("\t</trim>");
        buf.append(System.getProperty("line.separator"));
        buf.append("\twhere ID=#{id}");
        buf.append(System.getProperty("line.separator"));

        buf.append("</update>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();

    }

    private static String getInsert(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<insert id=\"insert\" parameterType=\"").append(packgePath)
                .append(".").append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.").append(bean.getClassName()).append("\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\tinsert into ").append(bean.getTabName()).append("( ");
        buf.append("<include refid=\"Base_Colnum_List\" />");
        buf.append(" ) values( ");
        buf.append(System.getProperty("line.separator"));
        buf.append("\t\t");
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);
            if (i == (bean.getList().size() - 1))
            {
                buf.append("#{").append(table.getAttrName()).append("}");
            }
            else
            {
                buf.append("#{").append(table.getAttrName()).append("},");
            }
        }

        buf.append(System.getProperty("line.separator"));
        buf.append("\t)");
        buf.append(System.getProperty("line.separator"));

        buf.append("</insert>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getWhereClause(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<sql id=\"Where_Clause\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\twhere 1=1");
        buf.append(System.getProperty("line.separator"));
        buf.append("\t<trim suffixOverrides=\",\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("\t\t<if test=\"keyName != null and keyName != ''\">\n\t\t\t<![CDATA[ and NAME like '%${keyName}%' ]]>\n\t\t</if>");
        buf.append(System.getProperty("line.separator"));
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);

            if ("String".equals(table.getAttrType()))
            {
                buf.append("\t\t<if test=\"").append(table.getAttrName())
                        .append(" != null and ").append(table.getAttrName()).append(" != ''\">");
                buf.append(System.getProperty("line.separator"));

                buf.append("\t\t\tand ").append(table.getColnumName())
                        .append(" = #{").append(table.getAttrName()).append("}");
                buf.append(System.getProperty("line.separator"));
                buf.append("\t\t</if>");
                buf.append(System.getProperty("line.separator"));
            }
            else if ("Date".equals(table.getAttrType()))
            {
                String strTimeStr = "start" + StrUtil.headToUpperCase(table.getAttrName()) + "Str";
                String endTimeStr = "end" + StrUtil.headToUpperCase(table.getAttrName()) + "Str";

                buf.append("\t\t<if test=\"")
                        .append(strTimeStr)
                        .append(" != null and ")
                        .append(strTimeStr)
                        .append(" != '' and ")
                        .append(endTimeStr)
                        .append(" != null and ")
                        .append(endTimeStr)
                        .append(" != '' \">");
                buf.append(System.getProperty("line.separator"));

                buf.append("\t\t\t<![CDATA[ AND  ")
                        .append(table.getColnumName())
                        .append(" >= #{")
                        .append(strTimeStr)
                        .append("} and  ")
                        .append(table.getColnumName())
                        .append(" <= #{")
                        .append(endTimeStr)
                        .append("}]]>");
                buf.append(System.getProperty("line.separator"));
                buf.append("\t\t</if>");
                buf.append(System.getProperty("line.separator"));

            }
            else
            {
                buf.append("\t\t<if test=\"").append(table.getAttrName()).append(" != null \">");
                buf.append(System.getProperty("line.separator"));

                buf.append("\t\t\tand ").append(table.getColnumName()).append(" = #{").append(table.getAttrName()).append("}");
                buf.append(System.getProperty("line.separator"));
                buf.append("\t\t</if>");
                buf.append(System.getProperty("line.separator"));
            }

        }
        buf.append("\t</trim>");
        buf.append(System.getProperty("line.separator"));
        buf.append("</sql>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getBaseColnumList(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<sql id=\"Base_Colnum_List\">");
        buf.append(System.getProperty("line.separator"));
        buf.append("\t");
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);
            if (i == (bean.getList().size() - 1))
            {
                buf.append(table.getColnumName());
            }
            else
            {
                buf.append(table.getColnumName()).append(",");

            }
        }
        buf.append(System.getProperty("line.separator"));
        buf.append("</sql>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static String getMapperResult(DbSchema bean, String packgePath)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<resultMap id=\"BaseResultMap\" type=\"").append(packgePath).append(".")
                .append(bean.getClassName().toLowerCase(Locale.getDefault()))
                .append(".dao.holder.").append(bean.getClassName()).append("\">");
        buf.append(System.getProperty("line.separator"));
        for (int i = 0; i < bean.getList().size(); i++)
        {
            Table table = bean.getList().get(i);
            buf.append("\t<result column=\"").append(table.getColnumName())
                    .append("\" property=\"").append(table.getAttrName()).append("\" />");
            buf.append(System.getProperty("line.separator"));
        }
        buf.append("</resultMap>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    private static Properties getProContent(List<DbSchema> list,
                                            String filePackage)
    {
        Properties pro = new Properties();
        pro.put(filePackage + ".system.query", "query");
        pro.put(filePackage + ".system.create", "create");
        pro.put(filePackage + ".system.modify", "modify");
        pro.put(filePackage + ".system.delete", "delete");
        pro.put(filePackage + ".system.goback", "goback");
        pro.put(filePackage + ".system.reset", "reset");
        pro.put(filePackage + ".system.cancle", "cancle");
        pro.put(filePackage + ".system.confirm", "confirm");
        pro.put(filePackage + ".system.failed", "failed");
        pro.put(filePackage + ".system.success", "success");
        pro.put(filePackage + ".system.login", "login");
        pro.put(filePackage + ".system.logout", "logout");
        pro.put(filePackage + ".system.auth", "auth");
        pro.put(filePackage + ".system.skip", "skip");
        pro.put(filePackage + ".system.auth.code", "auth code");
        pro.put(filePackage + ".system.page", "page");
        pro.put(filePackage + ".system.loading", "loading, please later...");
        pro.put(filePackage + ".system.condition", "condition");
        pro.put(filePackage + ".system.operation", "operation");
        pro.put(filePackage + ".system.select.delete.record",
                "Please select need to delete the record");
        pro.put("com.bms.menu.home", "home");
        pro.put("com.bms.menu.system", "system");
        pro.put("com.bms.menu.document", "document");
        pro.put("com.bms.menu.usermanager", "usermanager");
        pro.put("com.bms.menu.user", "user");
        pro.put("com.bms.menu.role", "role");
        pro.put("com.bms.menu.menu", "menu");

        for (DbSchema aList : list)
        {
            List<Table> tableList = aList.getList();
            for (Table aTableList : tableList)
            {
                pro.put(filePackage
                        + "."
                        + aList.getClassName()
                        .toLowerCase(Locale.getDefault())
                        + "."
                        + aTableList.getAttrName()
                        .toLowerCase(Locale.getDefault()), aTableList.getAttrName().toLowerCase(Locale.getDefault()));
            }
        }
        return pro;
    }
}
