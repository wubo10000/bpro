package com.bms.tmenu.service.impl;

import com.bms.system.bean.PPException;
import com.bms.tmenu.dao.TMenuMapper;
import com.bms.tmenu.dao.holder.TMenu;
import com.bms.tmenu.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tMenuService")
public class TMenuServiceImpl implements TMenuService
{
    @Autowired
    private TMenuMapper mapper;

    @Override
    public int tsDelete(TMenu bean)
    {
        return mapper.delete(bean);
    }

    @Override
    public int tsUpdate(TMenu bean)
    {
        return mapper.update(bean);
    }

    @Override
    public int tsInsert(TMenu bean)
    {
        return mapper.insert(bean);
    }

    @Override
    public int getCountOfSummary(TMenu bean)
    {
        return mapper.getCountOfSummary(bean);
    }

    @Override
    public List<TMenu> getList(TMenu bean)
    {
        return mapper.getList(bean);
    }

    @Override
    public List<TMenu> getListIn(TMenu bean)
    {
        return mapper.getListIn(bean);
    }

    @Override
    public List<TMenu> getListOfSummary(TMenu bean)
    {
        return mapper.getListOfSummary(bean);
    }

    @Override
    public int tsDelete(List<TMenu> list)
    {
        for (TMenu menu : list)
        {
            if (mapper.delete(menu) < 1)
            {
                throw new PPException("del " + menu + " error");
            }
        }
        return 1;
    }
}
