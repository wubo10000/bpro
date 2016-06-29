package com.bms.trolemenu.service;

import java.util.List;

import com.bms.system.service.BaseService;
import com.bms.trolemenu.dao.holder.TRoleMenu;

public interface TRoleMenuService extends BaseService<TRoleMenu>
{
    List<TRoleMenu> getMenu(TRoleMenu bean);

    int insertBatch(List<TRoleMenu> list);
}
