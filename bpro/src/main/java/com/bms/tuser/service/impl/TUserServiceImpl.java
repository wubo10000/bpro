package com.bms.tuser.service.impl;

import com.bms.system.bean.PPException;
import com.bms.tuser.dao.TUserMapper;
import com.bms.tuser.dao.holder.TUser;
import com.bms.tuser.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tUserService")
public class TUserServiceImpl implements TUserService
{
    @Autowired
    private TUserMapper mapper;

    @Override
    public int tsDelete(TUser bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(TUser bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(TUser bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(TUser bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<TUser> getList(TUser bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<TUser> getListOfSummary(TUser bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int tsDelete(List<TUser> list)
    {
        for (TUser user : list)
        {
            if (mapper.delete(user) < 1)
            {
                throw new PPException("del " + user + " error");
            }
        }
        return 1;
    }
}