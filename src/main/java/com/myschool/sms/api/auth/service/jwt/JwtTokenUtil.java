package com.myschool.sms.api.auth.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.myschool.sms.config.AppConst;

@Component
public class JwtTokenUtil implements Serializable {

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final long serialVersionUID = -3301605591108950415L;
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_AUDIENCE = "audience";
    private static final String CLAIM_KEY_PASS = "pass";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_DEVICE_KEY = "SINGLE_USER_LOGIN";
    private static final String STATUS = "STATUS";

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            LOGGER.error("UserName Not Found -", e);
            username = null;
        }
        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            LOGGER.error("Date parsing problem -", e);
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            LOGGER.error("Audience parsing problem -", e);
            audience = null;
        }
        return audience;
    }

    public String getPasswordFromToken(String token) {
        String pass;
        try {
            final Claims claims = getClaimsFromToken(token);
            pass = (String) claims.get(CLAIM_KEY_PASS);
        } catch (Exception e) {
            LOGGER.error("Password parsing problem -", e);
            pass = null;
        }
        return pass;
    }


    public int getDeviceIdFromToken(String token) {
        int pass;
        try {
            final Claims claims = getClaimsFromToken(token);
            pass = (int) claims.get(CLAIM_DEVICE_KEY);
        } catch (Exception e) {
            LOGGER.error("Password parsing problem -", e);
            pass = 0;
        }
        return pass;
    }

    public boolean getAccountStatusFromToken(String token) {
        boolean status = false;
        try {
            final Claims claims = getClaimsFromToken(token);
            status = (boolean) claims.get(STATUS);
        } catch (Exception e) {
            LOGGER.error("Password parsing problem -", e);

        }
        return status;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(AppConst.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.error("Claims parsing problem -", e);
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + AppConst.EXPIRATION * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_AUDIENCE, "IOS");
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_PASS, user.getPassword());
        claims.put(CLAIM_DEVICE_KEY, user.getDeviceId());
        claims.put(STATUS, user.isActiveFlag());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, AppConst.SECRET)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails, HttpServletResponse servletResponse) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final String password = getPasswordFromToken(token);
        final int deviceId = getDeviceIdFromToken(token);
        if (user.getDeviceId() != deviceId)
            servletResponse.addHeader("INVALID_DEVICE", AppConst.EMPTY_STRING);
        else if (!user.isActiveFlag())
            servletResponse.addHeader("ACCOUNT_STATUS", "INACTIVE");
        return
                username.equals(user.getUsername())
                        && !isTokenExpired(token) && password.equals(user.getPassword())
                        && user.getDeviceId() == deviceId
                        && user.isActiveFlag()
                ;
    }
}