package com.market.bot.utils.token;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    //加密密钥
    private String secretKey = "asdfghjklzxcvbnmqwertyui";

    //JWT有效时间
    private long expirationTime = 1000 * 60 * 60;

    //生成token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)//设username为加密主题
                .setIssuedAt(new Date())//设置发行时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))//设置过期时间
                .signWith(SignatureAlgorithm.HS256,secretKey)//使用HS256算法签名
                .compact();
    }

    //解析JWT
    public String extractToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))//使用字节数组验证
                .build()//构建解析器
                .parseClaimsJwt(token) //解析JWT
                .getBody() //获取JWT中的Claims信息
                .getSubject();//提取用户名
    }

    //校验JWT的过期时间
    public boolean isTokenValid(String token) {
        return extractExpiration(token).before(new Date());
    }

    //提取JWT的过期时间
    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)) //使用字节数组验证
                .build()//构造解析器
                .parseClaimsJwt(token)
                .getBody()
                .getExpiration();
    }

    //校验JWT是否有效
    public boolean validateToken(String token,String username) {
        return (username.equals(extractToken(token))&& !isTokenValid(token));
    }
}
