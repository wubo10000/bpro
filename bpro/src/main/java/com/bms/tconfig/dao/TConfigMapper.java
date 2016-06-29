package com.bms.tconfig.dao;

import com.bms.system.service.CommonMapper;
import com.bms.tconfig.dao.holder.TConfig;

import java.util.List;

public interface TConfigMapper extends CommonMapper<TConfig>
{
    List<TConfig> getGroup(TConfig bean);
}
