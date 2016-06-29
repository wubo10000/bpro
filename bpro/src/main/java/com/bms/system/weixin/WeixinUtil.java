package com.bms.system.weixin;

import com.bms.system.http.HttpUtil;
import net.sf.json.JSONObject;

import java.net.URLEncoder;

/**
 * @author : zhaoyi
 * @version : 1.0
 * @description :
 * @date : 2015-12-01 11:27
 */
public class WeixinUtil
{
    private static final String APPID = "wx57cbbb43c2c5ed7d";
    private static final String SECRET = "1bc5539ceaf377bf4878f1554aa3bb41";


    public static JSONObject getAccessTocken()
    {
        String get_access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx57cbbb43c2c5ed7d&secret=1bc5539ceaf377bf4878f1554aa3bb41";
        String json = HttpUtil.httpGet(get_access_token_url);

        return JSONObject.fromObject(json);
    }

    public static void getCode()
    {
        String uri = "http://wx.isimcere.com/tms/userinfo/set_code.l";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=APPID&redirect_uri=REDIRECT_URI&" +
                "response_type=code&" +
                "scope=snsapi_base&" +
                "state=1#wechat_redirect";

        url  = url.replace("APPID", urlEnodeUTF8(APPID));
        url  = url.replace("REDIRECT_URI",urlEnodeUTF8(uri));

        HttpUtil.httpGet(url);
    }

    // 授权访问获得openid
    public static String getOpenId(String code)
    {
        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + APPID +
                "&secret=" + SECRET +
                "&code=CODE&grant_type=authorization_code";

        get_access_token_url = get_access_token_url.replace("CODE", code);

        String json = HttpUtil.httpGet(get_access_token_url);
        JSONObject jsonObject = JSONObject.fromObject(json);

        return jsonObject.getString("openid");
    }

    /*public static void sendTemplateMsg(Userinfo userinfo, ApplyRecord record)
    {
        String templateId = "cbCdjKFvqciogAcgXO7nxxD6fOnmYL8CTmTydYNHvWY"; // 模板消息id
        WxTemplate t = new WxTemplate();
        t.setUrl("");
        t.setTouser(userinfo.getOpenid());

        t.setTopcolor("#232323");
        t.setTemplate_id(templateId);
        Map<String,WxTemplateData> m = new HashMap<String,WxTemplateData>();

        // first
        WxTemplateData first = new WxTemplateData();
        first.setColor("#878586");
        first.setValue("您好，您已经预约成功");
        m.put("first", first);

        // 姓名
        WxTemplateData patientName = new WxTemplateData();
        patientName.setColor("#878586");
        patientName.setValue(userinfo.getName());
        m.put("patientName", patientName);

        // 性别
        WxTemplateData patientSex = new WxTemplateData();
        patientSex.setColor("#878586");
        patientSex.setValue(userinfo.getGender()==0?"男":"女");
        m.put("patientSex", patientSex);

        // 预约医院
        WxTemplateData hospitalName = new WxTemplateData();
        hospitalName.setColor("#878586");
        hospitalName.setValue(record.getHospitalName());
        m.put("hospitalName", hospitalName);

        // 预约科室
        WxTemplateData department = new WxTemplateData();
        department.setColor("#878586");
        department.setValue(record.getHospitalOffices());
        m.put("department", department);

        // 预约医生
        WxTemplateData doctorName = new WxTemplateData();
        doctorName.setColor("#878586");
        doctorName.setValue(record.getDoctor());
        m.put("doctor", doctorName);

        // 流水号
        WxTemplateData seq = new WxTemplateData();
        seq.setColor("#878586");
        seq.setValue(record.getConfirmedMsg());
        m.put("seq", seq);

        // 申请时间
        WxTemplateData applyTime = new WxTemplateData();
        applyTime.setColor("#878586");
        applyTime.setValue("申请时间：" + record.getApplyDataStr());
        m.put("remark", applyTime);

        t.setData(m);

        String access_token = getAccessTocken().getString("access_token");
        String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        HttpUtil.httpRequest(sendUrl, "POST", JSONObject.fromObject(t).toString());
    }

    public static void sendAppointmentFailMsg(Userinfo userinfo, ApplyRecord record)
    {
        String templateId = "zea9mrWIMK074vWF6qpM3BWvFU9SinqNadzWDT8ddVI"; // 模板消息id
        WxTemplate t = new WxTemplate();
        t.setUrl("");
        t.setTouser(userinfo.getOpenid());

        t.setTopcolor("#232323");
        t.setTemplate_id(templateId);
        Map<String,WxTemplateData> m = new HashMap<String,WxTemplateData>();

        // first
        WxTemplateData first = new WxTemplateData();
        first.setColor("#878586");
        first.setValue("您好，您的预约未成功，请按提示修改重新预约");
        m.put("first", first);

        // 预约医院
        WxTemplateData hospitalName = new WxTemplateData();
        hospitalName.setColor("#878586");
        hospitalName.setValue(record.getHospitalName());
        m.put("keyword1", hospitalName);

        // 预约科室
        WxTemplateData department = new WxTemplateData();
        department.setColor("#878586");
        department.setValue(record.getHospitalOffices());
        m.put("keyword2", department);

        // 预约医生
        WxTemplateData doctorName = new WxTemplateData();
        doctorName.setColor("#878586");
        doctorName.setValue(record.getDoctor());
        m.put("keyword3", doctorName);

        // 提交时间
        WxTemplateData time = new WxTemplateData();
        time.setColor("#878586");
        time.setValue(record.getApplyDataStr());
        m.put("keyword4", time);

        // 失败原因
        WxTemplateData msg = new WxTemplateData();
        msg.setColor("#878586");
        msg.setValue(record.getConfirmedMsg());
        m.put("keyword5", msg);

        t.setData(m);

        String access_token = getAccessTocken().getString("access_token");
        String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        HttpUtil.httpRequest(sendUrl, "POST", JSONObject.fromObject(t).toString());
    }*/

    public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}