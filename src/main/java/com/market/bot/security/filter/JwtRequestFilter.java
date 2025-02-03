package com.market.bot.security.filter;

import com.market.bot.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取请求头中的Authorization
        String token = request.getHeader("Authorization");


        if (token != null && token.startsWith("Bearer ")) {
            //解析JWT
            token = token.substring(7);//去掉Bearer前缀


            //提取用户名
            String username = jwtUtil.extractToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //验证token是否有效
                if (jwtUtil.validateToken(token,username)){
                    //通过用户的用户名加载用户信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    //设置认证信息
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
