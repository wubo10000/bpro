package com.bms.tconfig.service;

import com.bms.system.service.BaseService;
import com.bms.tconfig.dao.holder.TConfig;

import java.util.List;

public interface TConfigService extends BaseService<TConfig>
{
    List<TConfig> getGroup(TConfig bean);
}
