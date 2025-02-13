package com.market.bot.contorller.auth;

import com.market.bot.common.ApiResponse;
import com.market.bot.common.enums.HttpStatusEnum;
import com.market.bot.utils.token.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/v1/login")
    public ApiResponse login(@RequestParam String username, @RequestParam String password) {
        long start = System.currentTimeMillis();
        try{

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = jwtUtil.generateToken(userDetails.getUsername());

            long end = System.currentTimeMillis();
            return ApiResponse.success(token,end-start);
        }catch (Exception e){
            long end = System.currentTimeMillis();
            return ApiResponse.error(HttpStatusEnum.UNAUTHORIZED.getDescription(),end-start);
        }
    }

}
