package com.market.bot.repository;

import com.market.bot.dto.SysUserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<SysUserLogin, Long> {
}
