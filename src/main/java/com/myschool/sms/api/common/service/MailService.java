package com.myschool.sms.api.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource({"classpath:application.properties"})
public class MailService {

    public static final Logger logger = LoggerFactory.getLogger(MailService.class);
    @Autowired
    Environment env;
    private JavaMailSenderImpl mailSender;

    @PostConstruct
    public void config() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(env.getProperty("mail.port", Integer.class));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));

        Properties additionalProperties = new Properties();
        additionalProperties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        additionalProperties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        mailSender.setJavaMailProperties(additionalProperties);
    }

    @Async
    public void sendMail(String to, String subject, String msg)
            throws MessagingException {

        String from = env.getProperty("from_email");
        logger.debug("Sending mail Subject:{},to:{}", subject, to);
        MimeMessage message = mailSender.createMimeMessage();

        message.setSubject(subject);
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(msg, true);
        mailSender.send(message);
        logger.debug("Mail Sent Successfully", subject);
    }
}