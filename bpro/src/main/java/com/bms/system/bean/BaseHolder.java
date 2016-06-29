/*
 * 文件名：BaseHolder.java
 * 版权：Copyright 2006-2013 lvxh Tech. Co. Ltd. All Rights Reserved. 
 * 描述： BaseHolder.java
 * 修改人：lxh
 * 修改时间：2013年9月30日
 * 修改内容：新增
 */
package com.bms.system.bean;

import java.io.Serializable;

public class BaseHolder implements Serializable
{

    private static final long serialVersionUID = -4101486224613889506L;

    private int delflag;

    /**
     * index of record, use to display line number
     */
    private int page;
    private int start;
    /**
     * numbers for per page
     */
    private int perNumber;

    /**
     * sorting field for sorting
     */
    private String sortField;

    /**
     * sorting order for sorting. eg asc or desc
     */
    private String sortOrder;

    /**
     * start page for pagination
     */
    private int startRecordNum;
    private int endRecordNum;

    private int limit;

    private Integer[] inClass;
    private String[] inIds;

    /**
     * 搜索名称
     */

    private String keyName;
    
    private String groupField;
    
    private int totalCount;

    private String sort;

    public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public int getLimit() {
		return limit<=0?1:limit;
	}

	public void setLimit(int limit) {
		this.limit = limit<=0?1:limit;
	}
    
    public int getDelflag()
    {
        return delflag;
    }

    public int getPage()
    {
        return page;
    }

    public int getPerNumber()
    {
        return perNumber;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public String getSortField()
    {
        return sortField;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public int getStartRecordNum()
    {
        return startRecordNum;
    }

    public void setDelflag(int delflag)
    {
        this.delflag = delflag;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setPerNumber(int perNumber)
    {
        this.perNumber = perNumber;
    }

    public void setSortField(String sortField)
    {
        this.sortField = sortField;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public void setStartRecordNum(int startRecordNum)
    {
        this.startRecordNum = startRecordNum;
    }

    private Integer loginUserId;

    public Integer getLoginUserId()
    {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId)
    {
        this.loginUserId = loginUserId;
    }

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
//		try {
//			this.keyName = new String(keyName.getBytes("ISO-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		this.keyName = keyName;
	}

    public Integer[] getInClass()
    {
        return inClass;
    }

    public void setInClass(Integer[] inClass)
    {
        this.inClass = inClass;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public int getEndRecordNum()
    {
        return endRecordNum;
    }

    public void setEndRecordNum(int endRecordNum)
    {
        this.endRecordNum = endRecordNum;
    }

    public String[] getInIds()
    {
        return inIds;
    }

    public void setInIds(String[] inIds)
    {
        this.inIds = inIds;
    }
}
