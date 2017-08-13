package com.appster.sms.api.auth.service.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.appster.sms.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int id;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final int deviceId;
    private final UserEntity.ACCOUNT_STATUS accountStatus;

    public JwtUser(int id, String password, String email, Collection<? extends GrantedAuthority> authorities, int deviceId, UserEntity.ACCOUNT_STATUS accountStatus) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.deviceId = deviceId;
        this.accountStatus = accountStatus;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity.ACCOUNT_STATUS getAccountStatus() {
        return accountStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getDeviceId() {
        return deviceId;
    }
}
