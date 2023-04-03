package com.my.project.claim_service.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Users extends BaseEntity implements Serializable {

    @Column(unique = true, name = "login")
    private String username;

    @Column(name = "password")
    private String password;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "roleId", fetch = FetchType.EAGER)
    private List<UsersRoles> usersRoles;
}
