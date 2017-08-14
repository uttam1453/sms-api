package com.myschool.sms.api.auth.service.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.myschool.sms.entity.UserEntity;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user, int deviceId) {
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
