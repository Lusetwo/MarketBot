package com.market.bot.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<SysPermission> permissions = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SysPermission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
