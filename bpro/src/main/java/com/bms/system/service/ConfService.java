package com.bms.system.service;

import java.util.Map;

public class ConfService {
	/**
	 * 管理员url 需要做session校验
	 */
	private Map<String, String> saUrlMap;

	/**
	 * 用户自助管理功能 需要做session校验
	 */
	private Map<String, String> userUrlMap;

	public Map<String, String> getSaUrlMap() {
		return saUrlMap;
	}

	public void setSaUrlMap(Map<String, String> saUrlMap) {
		this.saUrlMap = saUrlMap;
	}

	public Map<String, String> getUserUrlMap() {
		return userUrlMap;
	}

	public void setUserUrlMap(Map<String, String> userUrlMap) {
		this.userUrlMap = userUrlMap;
	}

}