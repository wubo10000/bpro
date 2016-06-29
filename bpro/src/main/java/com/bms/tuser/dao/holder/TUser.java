package com.bms.tuser.dao.holder;

import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;

import java.util.Date;

public class TUser extends BaseHolder
{
    private static final long serialVersionUID = -8243304529673727049L;
    private String id;
    private String loginName;
    private String name;
    private String password;
    private String tel;
    private String phone;
    private String eMail;
    private Date dateCreated;
    private String dateCreatedStr;
    private Date startDateCreated;
    private Date endDateCreated;
    private String startDateCreatedStr;
    private String endDateCreatedStr;
    private Date lastLoginTime;
    private String lastLoginTimeStr;
    private Date startLastLoginTime;
    private Date endLastLoginTime;
    private String startLastLoginTimeStr;
    private String endLastLoginTimeStr;
    private Integer tState;
    private String roleId;
    private String tImg;
    private String roleName;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginName()
    {
        return this.loginName;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getTel()
    {
        return this.tel;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setEMail(String eMail)
    {
        this.eMail = eMail;
    }

    public String getEMail()
    {
        return this.eMail;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
        this.dateCreatedStr = DateUtil.dateToStr(dateCreated, Constant.DATE_FORMAT);
    }

    public Date getDateCreated()
    {
        return this.dateCreated;
    }

    public void setDateCreatedStr(String dateCreatedStr)
    {
        this.dateCreatedStr = dateCreatedStr;
    }

    public String getDateCreatedStr()
    {
        return this.dateCreatedStr;
    }

    public void setStartDateCreated(Date startDateCreated)
    {
        this.startDateCreated = startDateCreated;
        this.startDateCreatedStr = DateUtil.dateToStr(startDateCreated, Constant.DATE_FORMAT);
    }

    public Date getStartDateCreated()
    {
        return this.startDateCreated;
    }

    public void setEndDateCreated(Date endDateCreated)
    {
        this.endDateCreated = endDateCreated;
        this.endDateCreatedStr = DateUtil.dateToStr(endDateCreated, Constant.DATE_FORMAT);
    }

    public Date getEndDateCreated()
    {
        return this.endDateCreated;
    }

    public void setStartDateCreatedStr(String startDateCreatedStr)
    {
        this.startDateCreatedStr = startDateCreatedStr;
    }

    public String getStartDateCreatedStr()
    {
        return this.startDateCreatedStr;
    }

    public void setEndDateCreatedStr(String endDateCreatedStr)
    {
        this.endDateCreatedStr = endDateCreatedStr;
    }

    public String getEndDateCreatedStr()
    {
        return this.endDateCreatedStr;
    }

    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
        this.lastLoginTimeStr = DateUtil.dateToStr(lastLoginTime, Constant.DATE_FORMAT);
    }

    public Date getLastLoginTime()
    {
        return this.lastLoginTime;
    }

    public void setLastLoginTimeStr(String lastLoginTimeStr)
    {
        this.lastLoginTimeStr = lastLoginTimeStr;
    }

    public String getLastLoginTimeStr()
    {
        return this.lastLoginTimeStr;
    }

    public void setStartLastLoginTime(Date startLastLoginTime)
    {
        this.startLastLoginTime = startLastLoginTime;
        this.startLastLoginTimeStr = DateUtil.dateToStr(startLastLoginTime, Constant.DATE_FORMAT);
    }

    public Date getStartLastLoginTime()
    {
        return this.startLastLoginTime;
    }

    public void setEndLastLoginTime(Date endLastLoginTime)
    {
        this.endLastLoginTime = endLastLoginTime;
        this.endLastLoginTimeStr = DateUtil.dateToStr(endLastLoginTime, Constant.DATE_FORMAT);
    }

    public Date getEndLastLoginTime()
    {
        return this.endLastLoginTime;
    }

    public void setStartLastLoginTimeStr(String startLastLoginTimeStr)
    {
        this.startLastLoginTimeStr = startLastLoginTimeStr;
    }

    public String getStartLastLoginTimeStr()
    {
        return this.startLastLoginTimeStr;
    }

    public void setEndLastLoginTimeStr(String endLastLoginTimeStr)
    {
        this.endLastLoginTimeStr = endLastLoginTimeStr;
    }

    public String getEndLastLoginTimeStr()
    {
        return this.endLastLoginTimeStr;
    }

    public void setTState(Integer tState)
    {
        this.tState = tState;
    }

    public Integer getTState()
    {
        return this.tState;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleId()
    {
        return this.roleId;
    }

    public void setTImg(String tImg)
    {
        this.tImg = tImg;
    }

    public String getTImg()
    {
        return this.tImg;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public int hashCode()
    {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof TUser)
        {
            TUser bean = (TUser) obj;
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
        buf.append(" loginName: [ " + this.getLoginName() + " ] ");
        buf.append(" name: [ " + this.getName() + " ] ");
        buf.append(" password: [ " + this.getPassword() + " ] ");
        buf.append(" tel: [ " + this.getTel() + " ] ");
        buf.append(" phone: [ " + this.getPhone() + " ] ");
        buf.append(" eMail: [ " + this.getEMail() + " ] ");
        buf.append(" dateCreated: [ " + this.getDateCreated() + " ] ");
        buf.append(" dateCreatedStr: [ " + this.getDateCreatedStr() + " ] ");
        buf.append(" startDateCreatedStr: [ " + this.getStartDateCreatedStr() + " ] ");
        buf.append(" endDateCreatedStr: [ " + this.getEndDateCreatedStr() + " ] ");
        buf.append(" lastLoginTime: [ " + this.getLastLoginTime() + " ] ");
        buf.append(" lastLoginTimeStr: [ " + this.getLastLoginTimeStr() + " ] ");
        buf.append(" startLastLoginTimeStr: [ " + this.getStartLastLoginTimeStr() + " ] ");
        buf.append(" endLastLoginTimeStr: [ " + this.getEndLastLoginTimeStr() + " ] ");
        buf.append(" tState: [ " + this.getTState() + " ] ");
        buf.append(" roleId: [ " + this.getRoleId() + " ] ");
        buf.append(" tImg: [ " + this.getTImg() + " ] ");
        buf.append(" roleName: [ " + this.getRoleName()+ " ] ");
        return buf.toString();
    }

}
