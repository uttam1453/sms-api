package com.myschool.sms.api.common.validator.impl;

/**
 * Created by lokesh on 11/01/17.
 */

import com.myschool.sms.api.common.validator.FieldMatch;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class FieldMatchImpl implements ConstraintValidator<FieldMatch, Object> {
    public static final Logger LOGGER = LoggerFactory.getLogger(FieldMatchImpl.class);
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext ctx) {
        try {
            final String[] firstObj = BeanUtils.getArrayProperty(value, firstFieldName);
            final String[] secondObj = BeanUtils.getArrayProperty(value, secondFieldName);
            boolean isEquals = (firstObj == null && secondObj == null) || (firstObj != null && Arrays.toString(firstObj).equals(Arrays.toString(secondObj)));
            if (!isEquals) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                        //ctx.buildConstraintViolationWithTemplate("{com.appster.common.validation.model.BusinessUserRequiredField.properties}")
                        .addNode(secondFieldName)
                        .addConstraintViolation();
                return false;
            }
            return true;
        } catch (final Exception ignore) {
            LOGGER.error("Error validating field : " + firstFieldName + " and " + secondFieldName, ignore);
        }
        return true;
    }
}