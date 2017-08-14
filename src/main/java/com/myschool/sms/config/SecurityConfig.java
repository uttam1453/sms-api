package com.myschool.sms.config;

import com.myschool.sms.api.auth.service.jwt.JwtAuthenticationTokenFilter;
import com.myschool.sms.api.common.constants.UserRoleEnum;
import com.myschool.sms.config.security.exceptions.AuthenticationExceptionHandler;
import com.myschool.sms.config.security.exceptions.AuthorizationExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * created by lokesh on 05/01/17.
 */

/**
 * This configuration creates a Servlet Filter known as the springSecurityFilterChain which is responsible for
 * all the security (protecting the application URLs, validating submitted username and passwords,
 * redirecting to the log in form, etc) within your application.
 */
@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //exception handlers
    @Autowired
    private AuthenticationExceptionHandler authenticationExceptionHandler;
    @Autowired
    private AuthorizationExceptionHandler authorizationExceptionHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    public SecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //rules for Authentication and Authorization on URL basis
        String[] allowedUrls = {"/sms/","/api/v1/auth/**", "/api/v1/master/**", "/api/v1/public/**"};
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(allowedUrls).permitAll()
                .antMatchers("/api/v1/admin/**").hasAuthority(UserRoleEnum.ADMIN.toString())
                .antMatchers("/api/v1/user/**").hasAuthority(UserRoleEnum.STUDENT.toString())
                .antMatchers("/api/v1/super/admin**").hasAuthority(UserRoleEnum.SUPER_ADMIN.toString())
                .and()
                .anonymous();

        //exceptions and errors handling
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationExceptionHandler) //handler for Authentication failed exception
                .accessDeniedHandler(authorizationExceptionHandler); //handler for Authorization denied exception

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
