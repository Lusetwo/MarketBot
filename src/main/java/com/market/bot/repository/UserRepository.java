package com.market.bot.repository;

import com.market.bot.dto.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
