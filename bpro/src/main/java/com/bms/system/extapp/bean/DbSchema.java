package com.bms.system.extapp.bean;

import com.bms.system.extapp.bean.*;

import java.util.List;


public class DbSchema {

	private String tabName;

    private String className;

    private List<Table> list;

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Table> getList() {
		return list;
	}

	public void setList(List<Table> list) {
		this.list = list;
	}
    
    
}
