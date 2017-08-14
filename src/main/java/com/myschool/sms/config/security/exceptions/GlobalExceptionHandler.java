package com.myschool.sms.config.security.exceptions;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.myschool.sms.api.common.AppException;
import com.myschool.sms.api.common.excecption.FacebookTokenValidationException;
import com.myschool.sms.api.common.response.envelope.ErrorResponseEnvelope;
import com.myschool.sms.api.common.response.meta.ErrorMeta;
import com.myschool.sms.api.common.response.meta.FacebookErrorMeta;
import com.myschool.sms.config.AppConst;
import com.stripe.exception.StripeException;

/**
 * created by lokesh on 19/11/16.
 */
@ControllerAdvice
@PropertySource({"classpath:error_messages.properties"})
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private Environment env;

    @ExceptionHandler
    public ResponseEntity handleServerError(Exception e) {
        LOGGER.error("Server Error - ", e);
        ErrorMeta errorMeta = new ErrorMeta(500, "SERVER_ERROR", "SERVER_ERROR");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleSQLServerError(SQLException e) {
        LOGGER.error("SQL Error - ", e);
        ErrorMeta errorMeta = new ErrorMeta(600, "SQL_SERVER_ERROR", "SQL_SERVER_ERROR");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity handle404Error(NoHandlerFoundException e) {
        LOGGER.error("404 Error - ", e);
        ErrorMeta errorMeta = new ErrorMeta(404, "No Such Method Found", "NOT_FOUND");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity handleBindError(MethodArgumentNotValidException e) {
        LOGGER.warn("Validation Failed Exception - ", e);
        String message = buildBadRequestMsg(e.getBindingResult());
        ErrorMeta errorMeta = new ErrorMeta(400, message, "VALIDATION_FAILED");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity handleBindError(BindException e) {
        LOGGER.warn("Param Binding Exception - ", e);
        String message = buildBadRequestMsg(e.getBindingResult());
        ErrorMeta errorMeta = new ErrorMeta(400, message, "PARAM_BINDING_EXCEPTION");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity handleServerError(AppException e) {
        LOGGER.debug("AppException {}", e.getErrorCode());
        String[] errorCode = env.getProperty(e.getErrorCode()).split("\\|");
        ErrorMeta errorMeta = new ErrorMeta(Integer.parseInt(errorCode[0]), errorCode[1], "API_ERROR");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler(FacebookTokenValidationException.class)
    public ResponseEntity handleFBTokenValidatonError(FacebookTokenValidationException fbex) {
        LOGGER.debug("AppException {}", fbex.getErrorCode());
        String[] errorCode = env.getProperty(fbex.getErrorCode()).split("\\|");
        ErrorMeta errorMeta = new FacebookErrorMeta(Integer.parseInt(errorCode[0]), errorCode[1], "TOKEN_VALIDATION", fbex.getFbErrorCode(), fbex.getMessage(), fbex.getToken());
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity handleStripeError(StripeException e) {
        ErrorMeta errorMeta = new ErrorMeta(401, e.getMessage(), "STRIPE_API_ERROR");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }

    private String buildBadRequestMsg(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        StringBuilder errorMessageBuilder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            String field = error.getField();
            String defaultMessage = error.getDefaultMessage();
            errorMessageBuilder.append(field).append(":").append(defaultMessage);
            errorMessageBuilder.append(",");
        }
        String message = errorMessageBuilder.toString().replaceAll(",$", AppConst.EMPTY_STRING);
        return message;
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleRequestParameterError(MissingServletRequestParameterException e) {
        LOGGER.warn("Request Param Exception - ", e.getMessage());
        ErrorMeta errorMeta = new ErrorMeta(400,e.getMessage(), "REQUEST_PARAM_MISSING_EXCEPTION");
        ErrorResponseEnvelope errorResponseEnvelope = new ErrorResponseEnvelope(errorMeta);
        return new ResponseEntity<>(errorResponseEnvelope, HttpStatus.OK);
    }
}
