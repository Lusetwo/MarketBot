package com.market.bot.repository;

import com.market.bot.dto.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission,Long> {

    @Query(value = "SELECT p.* FROM sys_permission p INNER JOIN sys_role_permission rp ON p.id = rp.permission_id WHERE rp.role_id = :roleId ORDER BY p.sort", nativeQuery = true)
    List<SysPermission> findByRoleId(@Param("roleId") Long roleId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sys_permission (parent_id, name, css, href, type, permission, sort) VALUES (:#{#p.parentId}, :#{#p.name}, :#{#p.css}, :#{#p.href}, :#{#p.type}, :#{#p.permission}, :#{#p.sort})", nativeQuery = true)
    int insertPermission(@Param("p") SysPermission p);

    @Modifying
    @Transactional
    @Query(value = "delete from sys_permission where parent_id = :parentId",nativeQuery = true)
    int deletePermissionByParentId(@Param("parentId") Long parentId);

    @Query(value = "SELECT DISTINCT sp.* FROM sys_role_user sru " +
            "INNER JOIN sys_role_permission srp ON srp.role_id = sru.role_id " +
            "LEFT JOIN sys_permission sp ON srp.permission_id = sp.id " +
            "WHERE sru.user_id = :userId", nativeQuery = true)
    List<SysPermission> findByUserId(@Param("userId") Long userId);

}
