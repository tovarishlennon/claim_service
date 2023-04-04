package com.my.project.claim_service.dto.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetAllUsersInfoRequestDto {
    private Long id;

    private String username;

    private String name;

    private String email;

    private String phone;

    private Integer status;

    private List<String> roles;
}
