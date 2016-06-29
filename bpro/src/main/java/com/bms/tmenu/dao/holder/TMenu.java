package com.bms.tmenu.dao.holder;

import com.bms.system.bean.BaseHolder;

public class TMenu extends BaseHolder
{
    private static final long serialVersionUID = -502200928029027482L;
    private String id;
    private String name;
    private String description;
    private String url;
    private Integer priority;
    private String parentId;
    private Integer level;
    private String imgUrl;
    private String config;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public Integer getPriority()
    {
        return this.priority;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getParentId()
    {
        return this.parentId;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return this.level;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl()
    {
        return this.imgUrl;
    }

    public void setConfig(String config)
    {
        this.config = config;
    }

    public String getConfig()
    {
        return this.config;
    }


    @Override
    public int hashCode()
    {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof TMenu)
        {
            TMenu bean = (TMenu) obj;
            return bean.getId().equals(this.getId());
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(" id: [ " + this.getId() + " ] ");
        buf.append(" name: [ " + this.getName() + " ] ");
        buf.append(" description: [ " + this.getDescription() + " ] ");
        buf.append(" url: [ " + this.getUrl() + " ] ");
        buf.append(" priority: [ " + this.getPriority() + " ] ");
        buf.append(" parentId: [ " + this.getParentId() + " ] ");
        buf.append(" level: [ " + this.getLevel() + " ] ");
        buf.append(" imgUrl: [ " + this.getImgUrl() + " ] ");
        buf.append(" config: [ " + this.getConfig() + " ] ");
        return buf.toString();
    }

}
