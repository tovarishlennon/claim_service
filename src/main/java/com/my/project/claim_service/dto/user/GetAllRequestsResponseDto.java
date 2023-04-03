package com.my.project.claim_service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetAllRequestsResponseDto {
    private Long id;
    private String text;
    private Integer status;
    private LocalDateTime dateCreated;
}
