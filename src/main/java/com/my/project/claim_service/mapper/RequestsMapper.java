package com.my.project.claim_service.mapper;

import com.my.project.claim_service.dto.user.GetAllDraftsResponseDto;
import com.my.project.claim_service.dto.user.GetAllRequestsResponseDto;
import com.my.project.claim_service.model.Requests;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface RequestsMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),

    })
    GetAllRequestsResponseDto toMapRequestToDto(Requests entity);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),

    })
    GetAllDraftsResponseDto toMapRequestToDraftDto(Requests entity);
}
