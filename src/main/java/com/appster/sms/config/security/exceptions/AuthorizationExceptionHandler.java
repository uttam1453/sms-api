package com.appster.sms.config.security.exceptions;

import com.appster.sms.api.common.response.envelope.ErrorResponseEnvelope;
import com.appster.sms.api.common.response.meta.ErrorMeta;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class AuthorizationExceptionHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationExceptionHandler.class);

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("Exception", accessDeniedException);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ErrorResponseEnvelope authError = new ErrorResponseEnvelope(new ErrorMeta(403, "You are not allowed for this operation", "ACCESS_DENIED"));
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(authError);
        response.getWriter().write(s);
    }
}