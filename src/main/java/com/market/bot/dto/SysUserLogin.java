package com.market.bot.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SysUserLogin {

    @Id
    private Integer id;

    //private Integer userId;

    private Timestamp loginTime;

    private String loginIp;

    private String loginStatus;

    @ManyToOne(fetch = FetchType.LAZY)  // 多个登录记录对应一个用户
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "SysUserLogin{" +
                "id=" + id +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                ", user=" + user +
                '}';
    }
}
