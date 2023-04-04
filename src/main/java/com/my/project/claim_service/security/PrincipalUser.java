package com.my.project.claim_service.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.project.claim_service.constant.RoleStatuses;
import com.my.project.claim_service.model.UsersRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties({"password"})
public class PrincipalUser extends User {
    private Long id;
    private List<UsersRoles> usersRoles;
    private String username;
    private String phone;
    private String name;
    private String email;

    public PrincipalUser(Long id, String username, String password, List<UsersRoles> usersRoles, String phone, String name, String email) {
        super(username, password, usersRoles.stream().map(r -> (new SimpleGrantedAuthority(r.getRoleId().getName()))).collect(Collectors.toList()));
        this.id = id;
        this.username = username;
        this.usersRoles = usersRoles;
        this.phone = phone;
        this.name = name;
        this.email = email;
    }
}

