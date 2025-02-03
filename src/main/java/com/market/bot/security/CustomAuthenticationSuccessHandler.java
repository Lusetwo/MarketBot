package com.market.bot.security;

import com.market.bot.dto.SysUser;
import com.market.bot.dto.SysUserLogin;
import com.market.bot.enums.LoginStatusEnum;
import com.market.bot.repository.UserLoginRepository;
import com.market.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取当前登录用户
        String name = authentication.getName();
        SysUser user = userRepository.findByUsername(name);

        //获取IP地址
        String ipAddress  = request.getRemoteAddr();

        //更新用户的登录时间和IP地址
        user.setLastLoginIp(ipAddress);
        user.setLastLoginTime(Timestamp.valueOf(LocalDateTime.now()));

        //记录登录成功的信息
        SysUserLogin userLogin  =  new SysUserLogin();
        userLogin.setUser(user);
        userLogin.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
        userLogin.setLoginIp(ipAddress);
        userLogin.setLoginStatus(String.valueOf(LoginStatusEnum.SUCCESS.getCode()));
        userLoginRepository.save(userLogin);

        response.sendRedirect("/api/dashboard");
    }
}
