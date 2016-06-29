/*
 * 文件名：Constant.java
 * 版权：Copyright 2006-2013 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Constant.java
 * 修改人：lxh
 * 修改时间：2013年11月4日
 * 修改内容：新增
 */
package com.bms.system.inteface;

import com.bms.system.file.FileUtil;


public interface Constant
{
    String PROJECT_NAME = "bms";
    String EXTJS_GENERATE_CODE_PATH = "D:/" + Constant.PROJECT_NAME + "GenerateCode/extjsCode/app/";
    String JAVA_GENERATE_CODE_PATH = "D:/" + Constant.PROJECT_NAME + "GenerateCode/javaCode/app/";
    String JAVA_PACKAGE_NAME = "com." + Constant.PROJECT_NAME;

    String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/pxun?useUnicode=true&amp;characterEncoding=UTF-8";
    String USERNAME = "root";
    String PWD = "root";

    public static String WEB_IMG_PATH = "html/fileimg/";
    public static String FILE_PATH = "upload/";
    public static String PLASMID_FILE_PATH = "upload/plasmid/";
    public static String PLASMID_MTA_PATH = "upload/plasmid_mta/";
    public static String FACE_IMG_PATH = "upload/face/";

//    String HOST = "http://localhost:8080/";

    String HOST = "http://120.25.176.38/";
    String ADMIN_HOST = "http://120.25.176.38/tUser/ts_login.l";

    String AUDITE_ADMIN_ROLE_ID = "b41e1ec8de6c4739aa2dca672de34b31";

    String AUTH_WMS_USER = "wms_web_user";

    String MENU_LIST = "menu_list";

    String LOGIN_TIME = "login_time";

    String LOGIN_ACTION = "/login/wmsLogin.action";

    String OPTIONAUTH_ACTION = "/login/optionauth.action";

    String TOKEN_ACTION = "/login/token.action";

    String OPTIONAUTH_ACTION_LIST = "optionauthlist";

    String VERIFICATION_CODE = "verification_code";

    String SHA_256 = "SHA-256";

    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_SHORT = "yyyy-MM-dd";
    String DATE_FORMAT_SIMPLE = "yyyy年MM月";
    String DATE_FORMAT_YEAR_MONTH = "yyyy-MM";

    String SUCCESS_MSG = "操作成功";
    String FAILURE_MSG = "操作失败";

    boolean TRUE_FLAG = true;
    boolean FAILURE_FLAG = false;

    String DEFAULTLOCALE = "zh_cn";
    String DEFAULT_LOCALE = "zh_CN";
    String[] locales = new String[]{"", "zh_cn", "en"};

    // 每次质粒存储成功添加10积分
    int INCREMENT = 10;
    // 分享成功添加20积分
    int SHARE_INTEGRAL = 20;

    /**
     * 用户功能位
     */
    String FUNC_POS = "0000000000000000000000000000000000000000000000000000000000000000";

    String REQUEST_TOKEN = "request_token";

    String TOKEN = "token";

    String SQLSERVER_DRIVER = "net.sourceforge.jtds.jdbc.Driver";

    /**
     * 邮件发送配置
     */
    String SMTP_HOST = "smtp.163.com";
    String SEND_FROM = "yaoshunyusw@163.com";
    String USER_NAME = "yaoshunyusw@163.com";
    String PASSWORD = "peiyanlin123";

    /**
     * 微信appid，secret
     */
    String APPID = "wxb31f7dfffaafb45a";
    String SECRET = "054b2ab868aff32082804ba7ef0e6d34";

    /**
     * 最大质粒申请数量
     */
    int MAX_APPLY_NUM = 10;

    /**
     * html2pdf
     */
    String PLASMID_PROFILE_IMG_PATH = "file:///D:/VCS/SVN/bms/target/bms/upload/plasmid/";
    String PLASMID_PDF_PATH = FileUtil.getWebPath() + "file/pdfs/";
    String FONTS = "simsun.ttc";

    /**
     * 申请的最大处理时间，96小时
     */
    int LIMIT_HOUR = +96;
}
