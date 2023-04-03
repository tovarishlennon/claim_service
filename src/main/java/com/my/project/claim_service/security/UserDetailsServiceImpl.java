package com.my.project.claim_service.security;

import com.my.project.claim_service.model.Roles;
import com.my.project.claim_service.model.Users;
import com.my.project.claim_service.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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

        return new PrincipalUser(user.getId(), user.getUsername(), user.getPassword(), user.getUsersRoles(), user.getPhone(), user.getName(), user.getEmail());
    }
}
