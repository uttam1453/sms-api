package com.appster.sms.api.auth.service.jwt;

import com.appster.sms.entity.SessionEntity;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.repo.SessionRepo;
import com.appster.sms.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userService;
    @Autowired
    private SessionRepo sessionRepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userService.checkEmailExistence(s);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", s));
        } else {
            int deviceId = 0;
            SessionEntity sessionEntity = sessionRepo.findByUserId(user.getId());
            if (sessionEntity != null)
                deviceId = sessionEntity.getId();
            List<GrantedAuthority> authorityList = user.getUserRoles().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getUserRolePK().getMasterRoleEntity().getName()))
                    .collect(Collectors.toList());

            return new JwtUser(
                    user.getId(),
                    user.getPassword(),
                    user.getEmail(),
                    authorityList, deviceId, user.getProfileStatus()
            );
        }
    }
}
