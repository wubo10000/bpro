package com.bms.tconfig.service.impl;

import com.bms.system.bean.PPException;
import com.bms.tconfig.dao.TConfigMapper;
import com.bms.tconfig.dao.holder.TConfig;
import com.bms.tconfig.service.TConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tConfigService")
public class TConfigServiceImpl implements TConfigService
{
    @Autowired
    private TConfigMapper mapper;

    @Override
    public int tsDelete(TConfig bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(TConfig bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(TConfig bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(TConfig bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<TConfig> getList(TConfig bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<TConfig> getListOfSummary(TConfig bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public List<TConfig> getGroup(TConfig bean)
    {
        return mapper.getGroup(bean);
    }

    @Override
    public int tsDelete(List<TConfig> list)
    {
        for (TConfig config : list)
        {
            if (mapper.delete(config) < 1)
            {
                throw new PPException("del " + config + " error");
            }
        }
        return 1;
    }
}
