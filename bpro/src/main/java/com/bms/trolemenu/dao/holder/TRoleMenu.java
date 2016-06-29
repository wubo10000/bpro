package com.bms.trolemenu.dao.holder;

import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;

public class TRoleMenu extends BaseHolder
{
    private static final long serialVersionUID = -3493457683677518359L;
    private String id;
    private String roleId;
    private String menuId;
    private Integer priority;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleId()
    {
        return this.roleId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuId()
    {
        return this.menuId;
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
        if (obj instanceof TRoleMenu)
        {
            TRoleMenu bean = (TRoleMenu) obj;
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
        buf.append(" roleId: [ " + this.getRoleId() + " ] ");
        buf.append(" menuId: [ " + this.getMenuId() + " ] ");
        buf.append(" priority: [ " + this.getPriority() + " ] ");
        return buf.toString();
    }

}
