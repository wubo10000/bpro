package com.bms.trole.dao.holder;

import com.bms.system.bean.BaseHolder;

public class TRole extends BaseHolder
{
    private static final long serialVersionUID = -7497261332489856895L;
    private String id;
    private String name;
    private Integer roleState;
    private Integer priority;

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

    public void setRoleState(Integer roleState)
    {
        this.roleState = roleState;
    }

    public Integer getRoleState()
    {
        return this.roleState;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public Integer getPriority()
    {
        return this.priority;
    }


    @Override
    public int hashCode()
    {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof TRole)
        {
            TRole bean = (TRole) obj;
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
        buf.append(" roleState: [ " + this.getRoleState() + " ] ");
        buf.append(" priority: [ " + this.getPriority() + " ] ");
        return buf.toString();
    }

}
