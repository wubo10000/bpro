package com.bms.tmenu.service;

import java.util.List;

import com.bms.system.service.BaseService;
import com.bms.tmenu.dao.holder.TMenu;

public interface TMenuService extends BaseService<TMenu>
{
    List<TMenu> getListIn(TMenu bean);
}
