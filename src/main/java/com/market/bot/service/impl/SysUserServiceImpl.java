package com.market.bot.service.impl;

import com.market.bot.common.ApiResponse;
import com.market.bot.dto.SysUser;
import com.market.bot.repository.UserRepository;
import com.market.bot.service.SysUserService;
import com.market.bot.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public ApiResponse login(String username, String password) throws Exception {

        //开始时间
        long startTime = System.currentTimeMillis();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication  = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //认证成功 生成token
        String token = jwtUtil.generateToken(authentication.getName());

        long endTime = System.currentTimeMillis();

        return ApiResponse.success(token,endTime - startTime);
    }


}
