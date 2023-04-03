package com.my.project.claim_service.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Roles extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
