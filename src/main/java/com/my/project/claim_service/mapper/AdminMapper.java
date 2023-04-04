package com.my.project.claim_service.mapper;

import com.my.project.claim_service.dto.admin.GetAllUsersInfoRequestDto;
import com.my.project.claim_service.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface AdminMapper {
    @Mapping(target = "roles", ignore = true)
    GetAllUsersInfoRequestDto toMapUserDtoFromEntity(Users users);
}
