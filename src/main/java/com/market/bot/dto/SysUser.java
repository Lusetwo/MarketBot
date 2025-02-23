package com.market.bot.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String username;

    private String password;

    private String headImgUrl;

    private String telephone;

    private String email;

    private String enabled;

    private Timestamp lastLoginTime;

    private String lastLoginIp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<SysRole> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", enabled='" + enabled + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", roles=" + roles +
                '}';
    }
}
