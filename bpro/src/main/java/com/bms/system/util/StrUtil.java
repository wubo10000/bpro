/*
 * 文件名：StrUtil.java
 * 版权：Copyright 2006-2013 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： StrUtil.java
 * 修改人：lxh
 * 修改时间：2013年11月4日
 * 修改内容：新增
 */
package com.bms.system.util;

import com.bms.system.bean.DbSchema;
import com.bms.system.bean.Table;
import com.bms.system.inteface.Constant;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;


public class StrUtil
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(StrUtil.class);

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

    public static String html(String content)
    {

        if (content == null)
            return "";

        String html = content;

        html = StringUtils.replace(html, "'", "&apos;");

        html = StringUtils.replace(html, "\"", "&quot;");

        html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格

        // html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格

        html = StringUtils.replace(html, "<", "&lt;");

        html = StringUtils.replace(html, ">", "&gt;");

        return html;

    }

    public static String getFram(String script, String body)
    {
        StringBuffer buf = new StringBuffer();
        // 文件头
        buf.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%> ");
        buf.append(System.getProperty("line.separator"));
        buf.append("<%@ include file=\"/decorators/taglib.jsp\"%>");
        buf.append(System.getProperty("line.separator"));
        buf.append("<html>");
        buf.append(System.getProperty("line.separator"));

        // script脚本
        buf.append("<script language=\"javascript\"> ");
        buf.append(System.getProperty("line.separator"));
        buf.append(script);
        buf.append(System.getProperty("line.separator"));
        buf.append("</script>");
        buf.append(System.getProperty("line.separator"));

        // body体
        buf.append("<body>");
        buf.append(System.getProperty("line.separator"));
        buf.append(body);
        buf.append(System.getProperty("line.separator"));
        buf.append("</body>");
        buf.append(System.getProperty("line.separator"));
        buf.append("</html>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();

    }

    public static String getQueryScript(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();

        buf.append("    var jsonMyPagination;");
        buf.append(System.getProperty("line.separator"));
        buf.append("    var myparams; ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    $(function()         ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#expaccordion\").accordion(     ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                header: \"h3\"                                                              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            });              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\":text:first\").focus();              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            initEvent();     ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $('.ui-button-exp').button();  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            var likebox=$(\":checkbox[name=listcheck]\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            var allcheck=$(\":checkbox[name=allcheck]\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(allcheck).bind(\"click\",function()");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                if($(allcheck).attr(\"checked\"))");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    $(\":checkbox[name=listcheck]\").each(function(){");
        buf.append(System.getProperty("line.separator"));
        buf.append("if( $(this).attr(\"disabled\") !=\"disabled\" )");
        buf.append("{");

        buf.append("                        $(this).attr(\"checked\",true);");
        buf.append("}");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    });");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }");
        buf.append(System.getProperty("line.separator"));
        buf.append("                else");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    $(\":checkbox[name=listcheck]\").each(function(){");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        $(this).attr(\"checked\",false);");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    });");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }");
        buf.append(System.getProperty("line.separator"));
        buf.append("            }); ");
        buf.append(System.getProperty("line.separator"));

        buf.append("$('.form_datetime').datetimepicker({");
        buf.append(System.getProperty("line.separator"));
        buf.append("    language:  'zh-CN',");
        buf.append(System.getProperty("line.separator"));
        buf.append("     weekStart: 1,");
        buf.append(System.getProperty("line.separator"));
        buf.append("    todayBtn:  1,");
        buf.append(System.getProperty("line.separator"));
        buf.append("    autoclose: 1,");
        buf.append(System.getProperty("line.separator"));
        buf.append("    todayHighlight: 1,");
        buf.append(System.getProperty("line.separator"));
        buf.append("    startView: 2,");
        buf.append(System.getProperty("line.separator"));
        buf.append("    forceParse: 0,");
        buf.append(System.getProperty("line.separator"));
        buf.append("    showMeridian: 1");
        buf.append(System.getProperty("line.separator"));
        buf.append(" });");
        buf.append(System.getProperty("line.separator"));

        buf.append("        });    ");
        buf.append(System.getProperty("line.separator"));
        // buf.append("    $(document).ready(function() ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("    {");
        // // buf.append(System.getProperty("line.separator"));
        // //
        // buf.append("        $(\"#queryForm\").validationEngine('attach', ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("        {");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("            bindMethod:\"live\",");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("            onValidationComplete: function(form, status)");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("            {");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                if(status)");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                    {");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                    var params = $(\"#queryForm\").serialize();                                   ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                    params = decodeURI(params);                                                   ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                    myparams = params;  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                    initEvent();  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("                    }");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("            }");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("        })");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("    });          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function initEvent()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            jsonMyPagination = $(\"#expdatagrid\").myPagination(                            ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                cssStyle: \"exp\",                                                          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                panel:       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    {        ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        tipInfo_on: true,                                                   ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        tipInfo: '&nbsp;&nbsp;<fmt:message key=\"com.web.system.skip\"/>{input}/{sumPage}<fmt:message key=\"com.web.system.page\"/>',                       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        tipInfo_css:                                                        ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                width: '25px',                                                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                height: \"20px\",                                             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                border: \"2px solid #f0f0f0\",                                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                padding: \"0 0 0 5px\",                                       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                margin: \"0 5px 0 5px\",                                      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                color: \"#666\"");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            }");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    },       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                debug: false,");
        buf.append(System.getProperty("line.separator"));
        buf.append("                ajax:       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                   {        ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        on: true,                                                           ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        url: '<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/getGrid.action\"/>',             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        dataType: 'json',                                                   ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        param: myparams,                                                    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        ajaxStart: function()                                               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                           {");
        buf.append(System.getProperty("line.separator"));
        buf.append("                               ZENG.msgbox.show(\" <fmt:message key='com.web.system.loading'/>\", 6, 10000);     ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                           },                                                              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        ajaxStop: function()                                                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            { ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                setTimeout(function()                                         ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                  {                                                           ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                      ZENG.msgbox.hide();                                     ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                  }, 120000);                                                 ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            },                                                              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        callback: function(data)                                            ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            {");
        buf.append(System.getProperty("line.separator"));
        buf.append("          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                               $(\"#mytab tbody\").setTemplateElement(\"Template-List\").processTemplate(data);                                                           ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                               $('#mytab tbody').addClass('TrHover');                     ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                               $(\"#mytab tbody tr\").addClass(\"odd\");                  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                               $(\"#mytab tbody tr:even\").addClass(\"even\");            ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                               $('#mytab tbody tr').click(function()                      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                    {                                                         ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                        $(\".Expselected\").removeClass(\"Expselected\");     ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                        $(this).toggleClass(\"Expselected\");                 ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                    });                                                       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                ZENG.msgbox.hide();                                       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            }");
        buf.append(System.getProperty("line.separator"));
        buf.append("                }          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          });             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function query()       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          {                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("var params = $(\"#queryForm\").serialize();    ");
        buf.append(System.getProperty("line.separator"));
        buf.append(" params = decodeURI(params);        ");
        buf.append(System.getProperty("line.separator"));
        buf.append("myparams = params;   ");
        buf.append(System.getProperty("line.separator"));
        buf.append(" initEvent();   ");
        buf.append(System.getProperty("line.separator"));

        buf.append(System.getProperty("line.separator"));
        buf.append("          }                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function add()       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        $(\"#queryForm\").attr(\"action\",\"<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/add.action\"/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        $(\"#queryForm\").submit();");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function delall()       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        var delId = \"\";  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        $(\":checkbox[name=listcheck]\").each(function()");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            if($(this).attr(\"checked\"))");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {");
        buf.append(System.getProperty("line.separator"));
        buf.append("               delId=delId+\"|\"+$(this).val();");
        buf.append(System.getProperty("line.separator"));
        buf.append("            }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        });  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        if(delId == \"\")");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("         alert(\"<fmt:message key='com.web.select.del.colunm'/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }");
        buf.append(System.getProperty("line.separator"));
        buf.append("        else");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#optionFlag\").val(delId);");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#queryForm\").attr(\"action\",\"<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/del.action\"/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#queryForm\").submit();      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function del(id)       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        var delId = \"|\"+id;  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        $(\"#optionFlag\").val(delId);");
        buf.append(System.getProperty("line.separator"));
        buf.append("        $(\"#queryForm\").attr(\"action\",\"<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/del.action\"/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("        $(\"#queryForm\").submit();          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }   ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function modify(id)       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        {  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#optionFlag\").val(\"0\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("             $(\"#id\").val(id);");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#queryForm\").attr(\"action\",\"<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/modify.action\"/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("            $(\"#queryForm\").submit();          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        }               ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String getQueryBody(String condition, String thead,
                                      String template)
    {
        StringBuffer buf = new StringBuffer();
        // buf.append(System.getProperty("line.separator"));
        // buf.append("    <form id=\"queryForm\" name=\"queryForm\" method=\"post\" action=\"\">  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("        <input type=\"hidden\" id=\"delFlag\" name=\"delFlag\" />  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("    </form>             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    <form id=\"queryForm\" name=\"queryForm\" method=\"post\" action=\"javascript:void(0);\"> ");
        buf.append(System.getProperty("line.separator"));
        buf.append("<input type=\"hidden\" id=\"optionFlag\" name=\"optionFlag\" />  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("<input type=\"hidden\" id=\"id\" name=\"id\" />  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("      <table width=\"100%\" id=\"tab\">                                                       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        <thead >               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          <tr>");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <th colspan=\"6\" style=\"font-size:14px; text-shadow:700;    text-align: left;\"><fmt:message key=\"com.web.query.param\"/></th>    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </tr>                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        </thead>               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        <tbody>                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          <tr>");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <td class=\"one\">id:</td>                                                  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <td colspan=\"2\" class=\"two\"><input class=\"validate[optional,custom[integer]]\" type=\"text\" id=\"id\" name=\"id\"  value=\"\" /></td>                                          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <td colspan=\"3\" style=\"text-align: right;\"> ");
        buf.append(System.getProperty("line.separator"));
        buf.append("              <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.query'/>\" onclick=\"query();\"/>&nbsp;              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("              <c:if test=\"${base.addflag == 1}\">                    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("              <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.create'/>\" onclick=\"add();\"/>&nbsp;                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("              </c:if><c:if test=\"${base.delflag == 1}\">                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("              <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.delete'/>\" onclick=\"delall();\"/>&nbsp;      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("              </c:if><input type=\"reset\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.reset'/>\" />&nbsp;                          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </td>                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </tr>              ");

        // 查询条件
        buf.append(condition);
        buf.append(System.getProperty("line.separator"));

        buf.append(System.getProperty("line.separator"));
        buf.append("        </tbody>               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("      </table>");
        buf.append(System.getProperty("line.separator"));
        buf.append("    </form>   ");
        buf.append(System.getProperty("line.separator"));
        buf.append("      <table width=\"100%\" id=\"mytab\" class=\"t1\">                                        ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          <thead>  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <th width=\"10px;\"><input type=\"checkbox\" name=\"allcheck\" value=\"\"/></th>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <th>id</th>");
        buf.append(System.getProperty("line.separator"));
        buf.append(thead);
        // buf.append(System.getProperty("line.separator"));

        buf.append(System.getProperty("line.separator"));
        buf.append("            <th width=\"60px;\"><fmt:message key=\"com.web.system.option\"/></th>");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </thead>             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          <tbody>              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </tbody>             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("      </table>");
        buf.append(System.getProperty("line.separator"));
        buf.append("    <div id=\"expdatagrid\"></div>                                                          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    <!-- Templates -->       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    <p style=\"display:none\">                                                              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        <textarea id=\"Template-List\" rows=\"0\" cols=\"0\">                               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            <!--               ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {#template MAIN} ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {#foreach $T.result as Result}                                              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    <tr>");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      <td><input type=\"checkbox\" name=\"listcheck\" value=\"{$T.Result.id}\"/></td>");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      <td>{$T.Result.id}</td>");
        buf.append(System.getProperty("line.separator"));

        buf.append(template);
        buf.append(System.getProperty("line.separator"));

        buf.append(System.getProperty("line.separator"));
        buf.append("                      <td><c:if test=\"${base.delflag == 1}\">");
        buf.append(System.getProperty("line.separator"));

        buf.append("<a href=\"#\" onclick=\"del({$T.Result.id})\"><fmt:message key='com.web.system.delete'/></a>&nbsp;</c:if>");
        buf.append(System.getProperty("line.separator"));
        buf.append("<c:if test=\"${base.modifyflag == 1}\">");
        buf.append(System.getProperty("line.separator"));
        buf.append("<a href=\"#\" onclick=\"modify({$T.Result.id})\"><fmt:message key='com.web.system.modify'/></a></c:if></td>");
        buf.append(System.getProperty("line.separator"));
        buf.append("                    </tr>              ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                {#/for}      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("            {#/template MAIN}");
        buf.append(System.getProperty("line.separator"));
        buf.append("            -->                ");
        buf.append(System.getProperty("line.separator"));
        buf.append("        </textarea>          ");
        buf.append(System.getProperty("line.separator"));
        buf.append("    </p>    ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();

    }

    public static String getQueryCondition(DbSchema beans, String packagePath)
    {

        List<Table> list = new ArrayList<Table>();
        StringBuffer buf = new StringBuffer();
        for (int i = 1; i < beans.getList().size(); i++)
        {
            Table table = beans.getList().get(i);
            if ("Date".equals(table.getAttrType()))
            {
                Table start = new Table();
                Table end = new Table();
                start.setAttrName("start"
                        + StrUtil.headToUpperCase(table.getAttrName()) + "Str");
                start.setColnumType("Date");
                end.setAttrName("end"
                        + StrUtil.headToUpperCase(table.getAttrName()) + "Str");
                end.setColnumType("Date");
                list.add(start);
                list.add(end);
            }
            else
            {
                list.add(beans.getList().get(i));
            }
        }
        for (int i = 1; i < list.size(); i++)
        {
            if (i % 2 != 0)
            {
                buf.append(System.getProperty("line.separator"));
                buf.append("         <tr class=\"tr_edd\">");
            }
            buf.append(System.getProperty("line.separator"));
            buf.append("           <td class=\"one\"><fmt:message key=\""
                    + packagePath
                    + "."
                    + beans.getClassName().toLowerCase(Locale.getDefault())
                    + "."
                    + list.get(i).getAttrName()
                    .toLowerCase(Locale.getDefault()) + "\"/>:</td>");
            buf.append(System.getProperty("line.separator"));
            buf.append("           <td class=\"two\" colspan=\"2\">");
            buf.append(System.getProperty("line.separator"));
            if ("Date".equals(list.get(i).getColnumType()))
            {
                buf.append("<div class=\"control-group\" style=\"height: 30px;\">");
                buf.append(System.getProperty("line.separator"));
                buf.append("<div class=\"controls input-append date form_datetime\" data-date=\"\" data-date-format=\""
                        + Constant.DATE_FORMAT
                        + "\" data-link-field=\""
                        + list.get(i).getAttrName()
                        + "\" style=\"color:#000000;\">");
                buf.append(System.getProperty("line.separator"));
                buf.append("    <input size=\"16\" type=\"text\" value=\"\" readonly style=\"width:140px\">");
                buf.append(System.getProperty("line.separator"));
                buf.append("    <span class=\"add-on\"><i class=\"icon-remove\"></i></span>");
                buf.append(System.getProperty("line.separator"));
                buf.append("    <span class=\"add-on\"><i class=\"icon-th\"></i></span>");
                buf.append(System.getProperty("line.separator"));
                buf.append("    <input type=\"hidden\" id=\""
                        + list.get(i).getAttrName() + "\" name=\""
                        + list.get(i).getAttrName() + "\" value=\"\" />");
                buf.append(System.getProperty("line.separator"));
                buf.append("</div>");
                buf.append(System.getProperty("line.separator"));
                buf.append(" </div>");
            }
            else
            {
                buf.append("             <input type=\"text\" id=\""
                        + list.get(i).getAttrName() + "\" name=\""
                        + list.get(i).getAttrName()
                        + "\" class=\"validate[optional]\" value=\"\" />");
            }
            buf.append(System.getProperty("line.separator"));
            buf.append("</td>");
            buf.append(System.getProperty("line.separator"));
            if (i % 2 == 0)
            {
                buf.append("         </tr>");

            }
        }
        if (!buf.toString().endsWith("</tr>"))
        {
            buf.append("         </tr>");
        }
        return buf.toString();
    }

    public static String getQueryThead(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 1; i < bean.getList().size(); i++)
        {
            buf.append("            <th>" + bean.getList().get(i).getAttrName()
                    + "</th>");
            buf.append(System.getProperty("line.separator"));
        }

        return buf.toString();
    }

    public static String getQueryTemplate(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 1; i < bean.getList().size(); i++)
        {
            if ("Date".equals(bean.getList().get(i).getAttrType()))
            {
                buf.append("                      <td>{$T.Result."
                        + bean.getList().get(i).getAttrName() + "Str}</td>");
                buf.append(System.getProperty("line.separator"));
            }
            else
            {
                buf.append("                      <td>{$T.Result."
                        + bean.getList().get(i).getAttrName() + "}</td>");
                buf.append(System.getProperty("line.separator"));
            }

        }

        return buf.toString();
    }

    public static String getModifyScript(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append("    $(function()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("      $(\"#queryForm\").validationEngine();");
        buf.append(System.getProperty("line.separator"));
        buf.append("      $('.ui-button-exp').button();");
        buf.append(System.getProperty("line.separator"));
        buf.append("    });");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function save()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("  $(\"#optionFlag\").val(\"1\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("     $(\"#queryForm\").attr(\"action\",\"<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/modify.action\"/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("     $(\"#queryForm\").submit();");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function cancle()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("     location.href = '<c:url value=\"init"
                + bean.getClassName() + ".action\"/>';");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }                        ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String getModifyBody(String content)
    {
        StringBuffer buf = new StringBuffer();
        // buf.append(System.getProperty("line.separator"));
        // buf.append("      <form id=\"queryForm\" name=\"queryForm\" method=\"post\" action=\"\">  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("           <input type=\"hidden\" id=\"delFlag\" name=\"delFlag\" />  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("      </form>   ");
        buf.append(System.getProperty("line.separator"));
        buf.append("      <form id=\"queryForm\" name=\"queryForm\" method=\"post\" action=\"\">  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("<input type=\"hidden\" id=\"optionFlag\" name=\"optionFlag\" />    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("<input type=\"hidden\" id=\"token\" name=\"token\" value=\"${token}\"/>");
        buf.append(System.getProperty("line.separator"));
        buf.append("<input type=\"hidden\" id=\"id\" name=\"id\" value=\"${bean.id}\" />");

        buf.append(System.getProperty("line.separator"));
        buf.append("          <table width=\"100%\" id=\"tab\">       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                <thead >      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      <tr>");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            <th colspan=\"3\" style=\"font-size:14px; text-shadow:700;    text-align: left;\"><fmt:message key=\"com.web.system.modify\"/><fmt:message key=\"com.web.system.account.info\"/></th>    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      </tr>       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                </thead>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                <tbody>       ");

        buf.append(content);

        buf.append("                      <tr>       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        <td colspan=\"3\" class=\"two\" align=\"center\">");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.save'/>\" onclick=\"save();\"/>&nbsp;                                                                             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.goback'/>\" onclick=\"cancle();\"/>&nbsp;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            <input type=\"reset\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.reset'/>\" />&nbsp;                                    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                        </td>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      </tr>  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("             </tbody>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </table>");
        buf.append(System.getProperty("line.separator"));
        buf.append("    </form>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String getModifyContent(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        for (int i = 0; i < bean.getList().size(); i++)
        {
            if (!"Date".equals(bean.getList().get(i).getAttrType()))
            {
                buf.append("                      <tr>");
                buf.append(System.getProperty("line.separator"));
                buf.append("                        <td class=\"one\">"
                        + bean.getList().get(i).getAttrName() + ":</td>  ");
                buf.append(System.getProperty("line.separator"));
                buf.append("                        <td colspan=\"2\" class=\"two\"><input type=\"text\" id=\""
                        + bean.getList().get(i).getAttrName()
                        + "\" name=\""
                        + bean.getList().get(i).getAttrName()
                        + "\"  class=\"validate[optional,custom[integer]]\" value=\"${bean."
                        + bean.getList().get(i).getAttrName()
                        + "}\" /></td>    ");
                buf.append(System.getProperty("line.separator"));
                buf.append(System.getProperty("line.separator"));
                buf.append("                      </tr>  ");
            }
        }
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String getAddScript(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        buf.append("    $(function()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("           $(\"#queryForm\").validationEngine();");
        buf.append(System.getProperty("line.separator"));
        buf.append("      $('.ui-button-exp').button();");
        buf.append(System.getProperty("line.separator"));
        buf.append("    });");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function save()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("$(\"#optionFlag\").val(\"1\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("     $(\"#queryForm\").attr(\"action\",\"<c:url value=\"/"
                + StrUtil.headToLowerCase(bean.getClassName())
                + "/add.action\"/>\");");
        buf.append(System.getProperty("line.separator"));
        buf.append("     $(\"#queryForm\").submit();");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }");
        buf.append(System.getProperty("line.separator"));
        buf.append("    function cancle()");
        buf.append(System.getProperty("line.separator"));
        buf.append("    {");
        buf.append(System.getProperty("line.separator"));
        buf.append("     location.href = '<c:url value=\"init"
                + bean.getClassName() + ".action\"/>';");
        buf.append(System.getProperty("line.separator"));
        buf.append("    }           ");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String getAddBody(String content)
    {
        StringBuffer buf = new StringBuffer();
        // buf.append(System.getProperty("line.separator"));
        // buf.append("      <form id=\"queryForm\" name=\"queryForm\" method=\"post\" action=\"\">  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("           <input type=\"hidden\" id=\"delFlag\" name=\"delFlag\" />  ");
        // buf.append(System.getProperty("line.separator"));
        // buf.append("      </form>   ");
        buf.append(System.getProperty("line.separator"));
        buf.append("      <form id=\"queryForm\" name=\"queryForm\" method=\"post\" action=\"\">  ");
        buf.append(System.getProperty("line.separator"));
        buf.append(" <input type=\"hidden\" id=\"optionFlag\" name=\"optionFlag\" />  ");
        buf.append(System.getProperty("line.separator"));
        buf.append(" <input type=\"hidden\" id=\"token\" name=\"token\" value=\"${token}\"/>");

        buf.append(System.getProperty("line.separator"));
        buf.append("          <table width=\"100%\" id=\"tab\">       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                <thead >      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      <tr>");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            <th colspan=\"3\" style=\"font-size:14px; text-shadow:700;    text-align: left;\"><fmt:message key='com.web.system.create'/><fmt:message key='com.wms.system.account'/></th>    ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      </tr>       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                </thead>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                <tbody>       ");
        buf.append(System.getProperty("line.separator"));
        buf.append(content);
        buf.append(System.getProperty("line.separator"));
        buf.append("                      <tr>       ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            <td colspan=\"3\" class=\"two\" align=\"center\">");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.save'/>\" onclick=\"save();\"/>&nbsp;                                                                             ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                <input type=\"button\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.goback'/>\" onclick=\"cancle();\"/>&nbsp;");
        buf.append(System.getProperty("line.separator"));
        buf.append("                                <input type=\"reset\" class=\"ui-button-exp\" value=\"<fmt:message key='com.web.system.reset'/>\" />&nbsp;      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                            </td>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("                      </tr>  ");
        buf.append(System.getProperty("line.separator"));
        buf.append("             </tbody>      ");
        buf.append(System.getProperty("line.separator"));
        buf.append("          </table>");
        buf.append(System.getProperty("line.separator"));
        buf.append("    </form>");
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String getAddContent(DbSchema bean)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(System.getProperty("line.separator"));
        for (int i = 0; i < bean.getList().size(); i++)
        {
            if (!"Date".equals(bean.getList().get(i).getAttrType()))
            {
                buf.append("                      <tr>");
                buf.append(System.getProperty("line.separator"));
                buf.append("                        <td class=\"one\">"
                        + bean.getList().get(i).getAttrName() + ":</td>  ");
                buf.append(System.getProperty("line.separator"));
                buf.append("                        <td colspan=\"2\" class=\"two\"><input type=\"text\" id=\""
                        + bean.getList().get(i).getAttrName()
                        + "\" name=\""
                        + bean.getList().get(i).getAttrName()
                        + "\"  class=\"validate[optional,custom[integer]]\" value=\"\" /></td>    ");
                buf.append(System.getProperty("line.separator"));
                buf.append(System.getProperty("line.separator"));
                buf.append("                      </tr>  ");
            }
        }
        buf.append(System.getProperty("line.separator"));

        return buf.toString();
    }

    public static String isNull(Object obj)
    {
        if (null != obj)
        {
            return obj.toString();
        }

        return "";
    }

    public static String returnPicUrl(String relativeUrl)
    {
        if (null != relativeUrl && !relativeUrl.equals(""))
        {
            return relativeUrl.replace("..", "http://wx.isimcere.com/tms");
        }
        return "";
    }

    public static void main(String[] args)
    {
        String dburl = "jdbc:jtds:sqlserver://192.168.1.106:1433;DatabaseName=nt_intelcourt_zy;user=sa;password=sa";

        System.out.println(dburl.substring(dburl.indexOf("//") + 2,
                dburl.length()));
    }

    /**
     * 字符串utf8编码
     *
     * @param str
     * @return
     */
    public static String urlEnodeUTF8(String str)
    {
        String result = str;
        try
        {
            result = URLEncoder.encode(str, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param hash byte[]
     * @return
     * @Description 字符串加密辅助方法
     * @author temdy
     * @Date 2015-06-19
     */
    public static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
