package com.my.project.claim_service.security;

import com.my.project.claim_service.constant.RoleStatuses;
import com.my.project.claim_service.model.Roles;
import com.my.project.claim_service.model.Users;
import com.my.project.claim_service.model.UsersRoles;
import com.my.project.claim_service.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        List<UsersRoles> activeRoles = mapActiveRoles(user.getUsersRoles());

        return new PrincipalUser(user.getId(), user.getUsername(), user.getPassword(), activeRoles, user.getPhone(), user.getName(), user.getEmail());
    }

    // тут я вывожу список только активных ролей у юзера
    // это сделано для того, чтобы переназначать роли от юзера к оператору для деактивации прав юзера
    // да и в целом можно в будущем включать и выключать права доступа, что будет являться механизмом мягкого удаления
    private List<UsersRoles> mapActiveRoles(List<UsersRoles> usersRoles) {
        List<UsersRoles> activeRole = new ArrayList<>();
        for (UsersRoles r : usersRoles) {
            if (Objects.equals(r.getStatus(), RoleStatuses.ACTIVE.getCode())) {
                activeRole.add(r);
            }
        }
        return activeRole;
    }
}
