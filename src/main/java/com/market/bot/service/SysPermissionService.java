package com.market.bot.service;

import com.alibaba.fastjson.JSONArray;
import com.market.bot.common.Results;
import com.market.bot.dto.SysPermission;

import java.util.List;
import java.util.Optional;

public interface SysPermissionService {
    Results<JSONArray> listAllPermissions();

    Results<SysPermission> listByRoleId(Long roleId);

    Results<SysPermission> getMenuAll();

    Results save(SysPermission sysPermission);

    Optional<SysPermission> getSysPermissionById(Long id);

    Results updateSysPermission(SysPermission sysPermission);

    Results delete(Long id);

    List<SysPermission> getMenu();

    Results getMenu(Long userId);
}
