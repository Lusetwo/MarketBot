package com.market.bot.service.impl;

import com.market.bot.common.Results;
import com.market.bot.dto.SysRole;
import com.market.bot.dto.SysUser;
import com.market.bot.repository.SysRoleRepository;
import com.market.bot.repository.UserRepository;
import com.market.bot.service.SysUserService;
import com.market.bot.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysRoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public Results login(String username, String password) throws Exception {

        //开始时间
        long startTime = System.currentTimeMillis();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication  = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //认证成功 生成token
        String token = jwtUtil.generateToken(authentication.getName());

        long endTime = System.currentTimeMillis();

        return Results.success(token,endTime - startTime);
    }

    /**
     * 给用户分配角色
     */
    @Override
    public SysUser assignRole(Long userId, Long roleId) {

        SysUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        SysRole role  = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("角色不存在"));

        user.getRoles().add(role);
        return userRepository.save(user);
    }


}
