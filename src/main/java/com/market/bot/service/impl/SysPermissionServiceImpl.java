package com.market.bot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.market.bot.common.Results;
import com.market.bot.dto.SysPermission;
import com.market.bot.repository.SysPermissionRepository;
import com.market.bot.service.SysPermissionService;
import com.market.bot.utils.CheckFieldUtil;
import com.market.bot.utils.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static final Logger log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);
    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    /**
     *查找全部权限
     * @return
     */
    @Override
    public Results<JSONArray> listAllPermissions() {
        long start = System.currentTimeMillis();
        log.info(getClass().getName()+"listAllPermissions");
        List<SysPermission> lSysPermission = sysPermissionRepository.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName()+".setPermissionsTree(?,?,?)");
        TreeUtil.setPermissionsTree(0,lSysPermission,array);
        long end = System.currentTimeMillis();
        return Results.success(array,end-start);
    }

    /**
     * 根据角色Id查找
     * @param roleId
     * @return
     */
    @Override
    public Results<SysPermission> listByRoleId(Long roleId) {
        long start = System.currentTimeMillis();
        log.info(getClass().getName()+"listByRoleId");
        List<SysPermission> byRoleId = sysPermissionRepository.findByRoleId(roleId);
        log.info(getClass().getName()+".setPermissionsTree(?,?,?)");
        long end = System.currentTimeMillis();
        return Results.success((SysPermission) byRoleId,end-start);
    }

    /**
     * 查找全部菜单
     * @return
     */
    @Override
    public Results<SysPermission> getMenuAll() {
        long start = System.currentTimeMillis();
        log.info(getClass().getName()+"getMenuAll");
        List<SysPermission> lSysPermission = sysPermissionRepository.findAll();
        long end = System.currentTimeMillis();
        return Results.success((SysPermission) lSysPermission,end-start);
    }

    /**
     * 插入菜单
     * @param sysPermission
     * @return
     */
    @Override
    public Results save(SysPermission sysPermission) {
        long start = System.currentTimeMillis();
        log.info(getClass().getName()+"save");
        int i = sysPermissionRepository.insertPermission(sysPermission);
        long end = System.currentTimeMillis();
        if (i>0){
            return Results.success(sysPermission,end-start);
        }else {
            return Results.error("插入失败",end-start);
        }
    }

    @Override
    public Optional<SysPermission> getSysPermissionById(Long id) {
        long start = System.currentTimeMillis();
        Optional<SysPermission> byId = sysPermissionRepository.findById(id);
        long end = System.currentTimeMillis();
        return byId;
    }

    @Override
    public Results updateSysPermission(SysPermission sysPermission) {
        long start = System.currentTimeMillis();
        Optional<SysPermission> byId = sysPermissionRepository.findById(sysPermission.getId());
        if (!byId.isPresent()){
            long end  = System.currentTimeMillis();
            log.error("更新失败：未找到id");
            return Results.error("更新失败：未找到id",end-start);
        }
        SysPermission permission = byId.get();

        //只拷贝非null的字段到permission 避免覆盖已有数据
        BeanUtils.copyProperties(sysPermission, CheckFieldUtil.getNullPropertyNames(sysPermission));

        //保存更新后的实体
        SysPermission updatePermission = sysPermissionRepository.save(permission);

        if (updatePermission!=null){
            long end = System.currentTimeMillis();
            return Results.success(updatePermission,end-start);
        }else {
            long end = System.currentTimeMillis();
            return Results.error(null,end-start);
        }
    }

    @Override
    public Results delete(Long id) {
        long start = System.currentTimeMillis();
        sysPermissionRepository.deleteById(id);
        sysPermissionRepository.deletePermissionByParentId(id);
        long end = System.currentTimeMillis();
        return Results.success(null,end-start);
    }

    @Override
    public List<SysPermission> getMenu(){
        return sysPermissionRepository.findAll();
    }

    @Override
    public Results getMenu(Long userId){
        long start = System.currentTimeMillis();
        List<SysPermission> byUserId = sysPermissionRepository.findByUserId(userId);
        byUserId = byUserId.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        log.info(getClass().getName()+".setPermissionsTree(?,?,?)");
        TreeUtil.setPermissionsTree(0,byUserId,array);
        long end = System.currentTimeMillis();
        return Results.success(array,end-start);

    }
}
