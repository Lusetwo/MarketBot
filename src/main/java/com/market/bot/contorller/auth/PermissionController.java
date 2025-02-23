package com.market.bot.contorller.auth;

import com.alibaba.fastjson.JSONArray;
import com.market.bot.common.Results;
import com.market.bot.dto.SysPermission;
import com.market.bot.dto.SysRole;
import com.market.bot.service.SysPermissionService;
import com.market.bot.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/permission")
@Tag(name = "菜单管理", description = "提供用户相关的 API 接口")
public class PermissionController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping("/{roleId}/assign-permission")
    public Results<SysRole> assignPermission(@PathVariable("roleId") Long roleId,
                                             @PathVariable("permissionId") Long permissionId) {
        long startTimeMills = System.currentTimeMillis();
        SysRole updatedRole = roleService.assignPermission(roleId, permissionId);
        long endTimeMills = System.currentTimeMillis();
        return Results.success(updatedRole, endTimeMills - startTimeMills);
    }

    @RequestMapping(value = "/listAllPermission", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @Operation(summary = "获取所有权限值", description = "获取所有菜单的权限值")//描述
    public Results<JSONArray> listAllPermission(){
        return permissionService.listAllPermissions();
    }

    @RequestMapping(value = "/listAllPermissionByRoleId",method = RequestMethod.GET)
    @ResponseBody
    @Operation(summary = "获取角色权限",description = "根据角色Id去查询拥有的权限")
    public Results<SysPermission> listAllPermissionByRoleId(Long roleId){
        log.info(getClass().getName() + " listAllPermissionByRoleId " + roleId);
        return permissionService.listByRoleId(roleId);
    }

    @GetMapping("/menuAll")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:query')")
    @Operation(summary = "获取所有权限值",description = "获取所有菜单的权限值")
    public Results getMenuAll(){
        return permissionService.getMenuAll();
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:add')")
    @Operation(summary = "添加菜单",description = "跳转到菜单信息新增页面")
    public String addPermission(Model model){
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Operation(summary = "添加菜单",description = "保存新增菜单信息")
    public Results<SysPermission> savePermission(@RequestBody SysPermission sysPermission){
        return permissionService.save(sysPermission);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    @Operation(summary = "编辑页面",description = "跳转到编辑菜单信息页面")
    public String editPermission(Model model,SysPermission permission){
        model.addAttribute("sysPermission",permissionService.getSysPermissionById(permission.getId()));
        return "permission/permission-add";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:edit')")
    @Operation(summary = "更新菜单信息",description = "保存用户编辑完的菜单信息")
    public Results editPermission(@RequestBody SysPermission sysPermission){
        return permissionService.updateSysPermission(sysPermission);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:del')")
    @Operation(summary = "删除菜单", description = "根据菜单Id去删除菜单")//描述
    public Results deletePermission(SysPermission sysPermission) {
        return permissionService.delete(sysPermission.getId());
    }

    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    @ResponseBody
    @Operation(summary = "获取菜单",description = "获取用户所属角色下的菜单")
    public Results<SysPermission> getMenu(Long userId){
        return permissionService.getMenu(userId);
    }
}
