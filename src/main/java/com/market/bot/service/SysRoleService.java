package com.market.bot.service;

import com.market.bot.dto.SysRole;

public interface SysRoleService {
    SysRole assignPermission(Long roleId, Long permissionId);
}
