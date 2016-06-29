package com.bms.system.bean;

import java.io.Serializable;

public class BaseBean implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -8884994594146273036L;

    private String userName;

    private String pwd;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

}
