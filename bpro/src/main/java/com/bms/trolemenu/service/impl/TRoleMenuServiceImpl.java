package com.bms.trolemenu.service.impl;

import java.util.List;

import com.bms.system.bean.PPException;
import com.bms.trolemenu.dao.holder.TRoleMenu;
import com.bms.trolemenu.dao.TRoleMenuMapper;
import com.bms.trolemenu.service.TRoleMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tRoleMenuService")
public class TRoleMenuServiceImpl implements TRoleMenuService
{
    @Autowired
    private TRoleMenuMapper mapper;

    @Override
    public int tsDelete(TRoleMenu bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(TRoleMenu bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(TRoleMenu bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(TRoleMenu bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<TRoleMenu> getList(TRoleMenu bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<TRoleMenu> getMenu(TRoleMenu bean)
    {
        return mapper.getMenu(bean);
    }

    @Override
    public List<TRoleMenu> getListOfSummary(TRoleMenu bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int insertBatch(List<TRoleMenu> list)
    {
        return mapper.insertBatch(list);
    }

    @Override
    public int tsDelete(List<TRoleMenu> list)
    {
        for (TRoleMenu roleMenu : list)
        {
            if (mapper.delete(roleMenu) < 1)
            {
                throw new PPException("del " + roleMenu + " error");
            }
        }
        return 1;
    }
}
