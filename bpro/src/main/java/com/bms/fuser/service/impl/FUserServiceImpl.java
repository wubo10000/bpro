package com.bms.fuser.service.impl;

import java.util.List;

import com.bms.system.bean.PPException;
import com.bms.fuser.dao.holder.FUser;
import com.bms.fuser.dao.FUserMapper;
import com.bms.fuser.service.FUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fUserService")
public class FUserServiceImpl implements FUserService
{
    @Autowired
    private FUserMapper mapper;

    @Override
    public int tsDelete(FUser bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(FUser bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(FUser bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(FUser bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<FUser> getList(FUser bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<FUser> getListOfSummary(FUser bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int tsDelete(List<FUser> list)
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
