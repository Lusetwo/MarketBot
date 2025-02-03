package com.market.bot.security;

import com.market.bot.dto.SysUser;
import com.market.bot.dto.SysUserLogin;
import com.market.bot.enums.LoginStatusEnum;
import com.market.bot.repository.UserLoginRepository;
import com.market.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //获取Ip地址
        String ipAddress = request.getRemoteAddr();

        //获取失败的用户信息
        String username = request.getParameter("username");

        //记录登录失败的信息
        SysUser user = userRepository.findByUsername(username);
        SysUserLogin userLogin = new SysUserLogin();
        userLogin.setUser(user);
        userLogin.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
        userLogin.setLoginIp(ipAddress);
        userLogin.setLoginStatus(String.valueOf(LoginStatusEnum.FAILURE.getCode()));
        userLoginRepository.save(userLogin);

        response.sendRedirect("/login?error"); // 登录失败后的跳转
    }
}
