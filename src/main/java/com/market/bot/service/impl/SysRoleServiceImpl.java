package com.market.bot.service.impl;

import com.market.bot.dto.SysPermission;
import com.market.bot.dto.SysRole;
import com.market.bot.repository.SysPermissionRepository;
import com.market.bot.repository.SysRoleRepository;
import com.market.bot.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository roleRepository;

    @Autowired
    private SysPermissionRepository permissionRepository;

    /**
     *
     * 给角色分配权限
     */
    @Override
    public SysRole assignPermission(Long roleId, Long permissionId) {
        SysRole role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("角色不存在"));

        SysPermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("权限不存在"));

        role.getPermissions().add(permission);
        return roleRepository.save(role);
    }

}
