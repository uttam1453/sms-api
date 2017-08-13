package com.appster.sms.config.security.exceptions;

import com.appster.sms.api.common.response.envelope.ErrorResponseEnvelope;
import com.appster.sms.api.common.response.meta.ErrorMeta;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

@Component
@PropertySource({"classpath:error_messages.properties"})
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionHandler.class);

    private static final long serialVersionUID = -8970718410437077606L;

    @Autowired
    private Environment env;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'auth currentPage' to redirect to
        logger.error("Exception", authException);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String errorMessage = authException.getMessage();
        int errorCode = 401;
        if (response.containsHeader("INVALID_DEVICE")) {
            String[] error = env.getProperty("USER_LOGGED_OUT").split("\\|");
            errorMessage = error[1];
            errorCode = Integer.parseInt(error[0]);
        } else if (response.containsHeader("ACCOUNT_STATUS")) {
            String[] error = env.getProperty("ACCOUNT_INACTIVE").split("\\|");
            errorMessage = error[1];
            errorCode = Integer.parseInt(error[0]);
        }
        ErrorResponseEnvelope authError = new ErrorResponseEnvelope(new ErrorMeta(errorCode, errorMessage, "AUTH_ERROR"));
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(authError);
        response.getWriter().write(s);
    }
}