package com.market.bot.service;

import com.market.bot.common.ApiResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysUserService {



    ApiResponse login(String username, String password) throws Exception;
}
