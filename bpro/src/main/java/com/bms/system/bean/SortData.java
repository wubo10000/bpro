package com.bms.system.bean;

import java.io.Serializable;

/**
 *
 * Created by zhy on 2015/8/31.
 */
public class SortData implements Serializable
{
    private static final long serialVersionUID = -3489539933836737805L;

    private String property;
    private String direction;

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    @Override
    public String toString()
    {
        return "SortData{" +
                "property='" + property + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
