package com.market.bot.service;

import com.market.bot.common.Results;
import com.market.bot.dto.SysUser;

public interface SysUserService {


    Results login(String username, String password) throws Exception;

    SysUser assignRole(Long userId, Long roleId);
}
