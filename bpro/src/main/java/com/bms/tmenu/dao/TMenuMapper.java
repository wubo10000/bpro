package com.bms.tmenu.dao;

import java.util.List;

import com.bms.system.service.CommonMapper;
import com.bms.tmenu.dao.holder.TMenu;

public interface TMenuMapper extends CommonMapper<TMenu>
{
    List<TMenu> getListIn(TMenu bean);
}
