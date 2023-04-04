package com.my.project.claim_service.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "users_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}))
public class UsersRoles extends BaseEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roleId;

    private Integer status;
}
