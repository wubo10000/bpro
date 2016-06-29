package com.bms.trolemenu.dao;

import com.bms.system.service.CommonMapper;
import com.bms.trolemenu.dao.holder.TRoleMenu;

import java.util.List;

public interface TRoleMenuMapper extends CommonMapper<TRoleMenu>
{
    List<TRoleMenu> getMenu(TRoleMenu bean);

    int insertBatch(List<TRoleMenu> list);
}