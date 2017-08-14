package com.myschool.sms.api.auth.service.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myschool.sms.entity.UserEntity;
import com.myschool.sms.entity.UserSessionDetail;
import com.myschool.sms.repo.SessionRepo;
import com.myschool.sms.repo.UserRepo;

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
            UserSessionDetail sessionEntity = sessionRepo.findByUserId(user.getId());
            if (sessionEntity != null)
                deviceId = sessionEntity.getId();
            List<GrantedAuthority> authorityList = user.getUserRoleMappings().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getRoleMst().getName()))
                    .collect(Collectors.toList());

            return new JwtUser(
                    user.getId(),
                    user.getPassword(),
                    user.getEmailId(),
                    authorityList, deviceId, user.getActiveFlag()
            );
        }
    }
}
