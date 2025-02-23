package com.market.bot.contorller.auth;

import com.market.bot.common.Results;
import com.market.bot.dto.SysUser;
import com.market.bot.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysUserService userService;

    @PostMapping("/v1/{userId}/assign-role")
    public Results<SysUser> assignRoleToUser(@PathVariable Long userId,
                                             @RequestParam Long roleId) {
        long startTimeMills = System.currentTimeMillis();
        SysUser updatedUser = userService.assignRole(userId, roleId);
        long endTimeMills = System.currentTimeMillis();
        return Results.success(updatedUser,endTimeMills-startTimeMills);
    }


}
