package com.bms.trole.service.impl;

import java.util.List;

import com.bms.system.bean.PPException;
import com.bms.trole.dao.holder.TRole;
import com.bms.trole.dao.TRoleMapper;
import com.bms.trole.service.TRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tRoleService")
public class TRoleServiceImpl implements TRoleService
{
    @Autowired
    private TRoleMapper mapper;

    @Override
    public int tsDelete(TRole bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(TRole bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(TRole bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(TRole bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<TRole> getList(TRole bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<TRole> getListOfSummary(TRole bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int tsDelete(List<TRole> list)
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
