package com.bms.flearndata.service.impl;

import java.util.List;

import com.bms.system.bean.PPException;
import com.bms.flearndata.dao.holder.FLearndata;
import com.bms.flearndata.dao.FLearndataMapper;
import com.bms.flearndata.service.FLearndataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fLearndataService")
public class FLearndataServiceImpl implements FLearndataService
{
    @Autowired
    private FLearndataMapper mapper;

    @Override
    public int tsDelete(FLearndata bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(FLearndata bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(FLearndata bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(FLearndata bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<FLearndata> getList(FLearndata bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<FLearndata> getListOfSummary(FLearndata bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int tsDelete(List<FLearndata> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (mapper.delete(list.get(i)) < 1)
            {
                throw new PPException("del " + list.get(i) + " error");
            }
        }
        return 1;
    }
}
