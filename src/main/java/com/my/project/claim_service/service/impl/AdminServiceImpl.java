package com.my.project.claim_service.service.impl;

import com.my.project.claim_service.constant.ResultCodes;
import com.my.project.claim_service.constant.RoleStatuses;
import com.my.project.claim_service.dto.admin.GetAllUsersInfoRequestDto;
import com.my.project.claim_service.dto.admin.SetRoleToUseResponseDto;
import com.my.project.claim_service.dto.exception.ApiException;
import com.my.project.claim_service.mapper.AdminMapper;
import com.my.project.claim_service.model.Roles;
import com.my.project.claim_service.model.Users;
import com.my.project.claim_service.model.UsersRoles;
import com.my.project.claim_service.repository.RolesRepository;
import com.my.project.claim_service.repository.UsersRepository;
import com.my.project.claim_service.repository.UsersRolesRepository;
import com.my.project.claim_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UsersRepository usersRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final AdminMapper adminMapper;
    private final RolesRepository rolesRepository;

    @Value("${role.name.oper}")
    private String opr;

    @Value("${role.name.adm}")
    private String adm;

    @Value("${role.name.usr}")
    private String usr;

    @Override
    public List<GetAllUsersInfoRequestDto> getAllUsers(String name) {
        List<Users> allUsersByStatus = usersRepository.findAllByUsersWithRoles("USER", name);

        List<GetAllUsersInfoRequestDto> requestDtos = new ArrayList<>();
        for (Users user : allUsersByStatus) {
            GetAllUsersInfoRequestDto mappedDto = adminMapper.toMapUserDtoFromEntity(user);
            mappedDto.setRoles(user.getUsersRoles().stream().map(io -> io.getRoleId().getName()).collect(Collectors.toList()));
            requestDtos.add(mappedDto);
        }
        return requestDtos;
    }

    @Override
    public SetRoleToUseResponseDto setRoleOperatorToUser(Long userId) {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "User not found!"));
        List<String> collect = users.getUsersRoles().stream().map(o -> o.getRoleId().getName()).collect(Collectors.toList());

        if (collect.contains(adm)) {
            throw new ApiException(ResultCodes.FAIL, "User with given id is ADMIN");
        } else if (collect.contains(opr)) {
            throw new ApiException(ResultCodes.FAIL, "User already has role OPERATOR");
        }

        // так как по ТЗ было сказано, что оператор не может создавать заявки, редактировать их, то я принял решение удалять права USER
        // после этого у пользователя с новоназначенной ролью ОПЕРАТОР не будет доступа к методам юзера, что соответствует условиям ТЗ
        UsersRoles userRole = usersRolesRepository.findByUserId_IdAndRoleId_Name(userId, usr).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "Role USER was not found with corresponding user_id!"));

        userRole.setStatus(RoleStatuses.NON_ACTIVE.getCode());

        usersRolesRepository.save(userRole);

        Roles operator = rolesRepository.findByName(opr).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "Role OPERATOR was not found with corresponding name!"));

        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setUserId(users);
        usersRoles.setRoleId(operator);
        usersRoles.setStatus(RoleStatuses.ACTIVE.getCode());
        usersRolesRepository.save(usersRoles);

        return new SetRoleToUseResponseDto(userId, ResultCodes.OK.getCode(), "Role OPERATOR was assigned!");
    }
}
