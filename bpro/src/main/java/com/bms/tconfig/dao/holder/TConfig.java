package com.bms.tconfig.dao.holder;

import com.bms.system.bean.BaseHolder;

public class TConfig extends BaseHolder
{
    private static final long serialVersionUID = -7915373912666209171L;
    private String id;
    private Integer tKey;
    private String tValue;
    private String tGroup;
    private String tGroupName;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setTKey(Integer tKey)
    {
        this.tKey = tKey;
    }

    public Integer getTKey()
    {
        return this.tKey;
    }

    public void setTValue(String tValue)
    {
        this.tValue = tValue;
    }

    public String getTValue()
    {
        return this.tValue;
    }

    public void setTGroup(String tGroup)
    {
        this.tGroup = tGroup;
    }

    public String getTGroup()
    {
        return this.tGroup;
    }

    public void setTGroupName(String tGroupName)
    {
        this.tGroupName = tGroupName;
    }

    public String getTGroupName()
    {
        return this.tGroupName;
    }


    @Override
    public int hashCode()
    {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof TConfig)
        {
            TConfig bean = (TConfig) obj;
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
        buf.append(" tKey: [ " + this.getTKey() + " ] ");
        buf.append(" tValue: [ " + this.getTValue() + " ] ");
        buf.append(" tGroup: [ " + this.getTGroup() + " ] ");
        buf.append(" tGroupName: [ " + this.getTGroupName() + " ] ");
        return buf.toString();
    }
}
