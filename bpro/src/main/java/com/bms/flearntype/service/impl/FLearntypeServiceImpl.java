package com.bms.flearntype.service.impl;

import java.util.List;

import com.bms.system.bean.PPException;
import com.bms.flearntype.dao.holder.FLearntype;
import com.bms.flearntype.dao.FLearntypeMapper;
import com.bms.flearntype.service.FLearntypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fLearntypeService")
public class FLearntypeServiceImpl implements FLearntypeService
{
    @Autowired
    private FLearntypeMapper mapper;

    @Override
    public int tsDelete(FLearntype bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(FLearntype bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(FLearntype bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(FLearntype bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<FLearntype> getList(FLearntype bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<FLearntype> getListOfSummary(FLearntype bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int tsDelete(List<FLearntype> list)
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
