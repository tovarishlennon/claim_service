package com.my.project.claim_service.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class Users  extends BaseEntity implements Serializable {

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private List<UsersRoles> usersRoles;
}
