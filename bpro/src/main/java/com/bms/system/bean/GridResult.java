/*
 * 文件名：GridResult.java
 * 版权：Copyright 2006-2014 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： GridResult.java
 * 修改人：lvxh
 * 修改时间：2014年5月7日
 * 修改内容：新增
 */
package com.bms.system.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GridResult implements Serializable
{

    /**
     * TODO 添加字段注释
     */
    private static final long serialVersionUID = -9184102551165740143L;

    private String code;

    private String paramStr;

    private List<?> result;

    private List<?> rows;

    private List<?> lists;

    private Map<?, ?> mapResult;

    private Object obj;

    private Integer size;

    private String winning;

    private Integer pageCount;

    private Integer totalCount;

    private boolean success = true;

    private String msg = "操作成功";

    private Integer isCompleted;

    public Integer getIsCompleted()
    {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted)
    {
        this.isCompleted = isCompleted;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getCode()
    {
        return code;
    }

    public String getParamStr()
    {
        return paramStr;
    }

    public void setParamStr(String paramStr)
    {
        this.paramStr = paramStr;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

    /**
     * <u>0：真<br>
     * 1：假</u>
     *
     * @param code
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    public List<?> getResult()
    {
        return result;
    }

    public void setResult(List<?> result)
    {
        this.result = result;
    }

    public List<?> getLists()
    {
        return lists;
    }

    public void setLists(List<?> lists)
    {
        this.lists = lists;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public Integer getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(Integer pageCount)
    {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getWinning()
    {
        return winning;
    }

    public void setWinning(String winning)
    {
        this.winning = winning;
    }

    public Map<?, ?> getMapResult()
    {
        return mapResult;
    }

    public void setMapResult(Map<?, ?> mapResult)
    {
        this.mapResult = mapResult;
    }
}
