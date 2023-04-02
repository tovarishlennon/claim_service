package com.my.project.claim_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "requests")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Requests extends BaseEntity implements Serializable {

    private String text;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
