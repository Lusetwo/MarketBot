package com.market.bot.service.impl;

import com.market.bot.dto.SysUser;
import com.market.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        //开始时间
        long startTime = System.currentTimeMillis();

        SysUser sysUser = userRepository.findByUsername(username);

        if (sysUser == null){
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails details = User.builder()
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())  // 假设密码已经加密
                .build();
        // 记录请求结束的时间
        long endTime = System.currentTimeMillis();
        return details;
    }
}
