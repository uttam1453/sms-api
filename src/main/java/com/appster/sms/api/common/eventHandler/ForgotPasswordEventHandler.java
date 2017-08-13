package com.appster.sms.api.common.eventHandler;


import com.appster.sms.api.common.event.ForgotPasswordEvent;
import com.appster.sms.api.common.service.MailService;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;

import java.util.HashMap;
import java.util.Map;

/**
 * created by atul on 26/05/16.
 */
@Component
public class ForgotPasswordEventHandler implements ApplicationListener<ForgotPasswordEvent> {
    private static Logger LOG = LoggerFactory.getLogger("ForgotPasswordEventHandler.class");
    @Autowired
    MailService mailService;
    @Autowired
    private Environment env;
    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void onApplicationEvent(ForgotPasswordEvent event) {
        String resetPassLink = env.getProperty("BASE_URL") + "api/v1/auth/resetPassword/" + event.getVerificationCode() + "&" + event.getUserId();
        String subject = "Bookie - Reset-Password";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", event.getUserName());
        model.put("resetPassLink", resetPassLink);
        String msg = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "templates/emails/passwordReset.vsl", "UTF-8", model);
        try {
            mailService.sendMail(event.getUserMail(), subject, msg);
        } catch (MessagingException e) {
            LOG.error("Errror sending reset mail to " + event.getUserMail() + " error message : " + e.getMessage(), e);
        }
    }
}
