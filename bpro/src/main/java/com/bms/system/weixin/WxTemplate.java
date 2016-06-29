package com.bms.system.weixin;

import java.util.Map;

/**
 * @author : zhaoyi
 * @version : 1.0
 * @description :
 * @date : 2015-12-01 12:23
 */
public class WxTemplate
{
    private String template_id;
    private String touser;
    private String url;
    private String topcolor;
    private Map<String, WxTemplateData> data;

    public String getTemplate_id()
    {
        return template_id;
    }

    public void setTemplate_id(String template_id)
    {
        this.template_id = template_id;
    }

    public String getTouser()
    {
        return touser;
    }

    public void setTouser(String touser)
    {
        this.touser = touser;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTopcolor()
    {
        return topcolor;
    }

    public void setTopcolor(String topcolor)
    {
        this.topcolor = topcolor;
    }

    public Map<String, WxTemplateData> getData()
    {
        return data;
    }

    public void setData(Map<String, WxTemplateData> data)
    {
        this.data = data;
    }
}
