package com.appster.sms.api.auth.service.jwt;

import com.appster.sms.entity.UserEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user, int deviceId) {
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
