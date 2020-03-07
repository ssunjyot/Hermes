package com.mapp.challenge.Hermes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderApplicationConfig {

    @Value(value = "${mail.host}")
    private String mailHost;

    @Value(value = "${mail.user.name}")
    private String mailUserName;

    @Value(value = "${mail.user.password}")
    private String mailUserPassword;

    @Value(value = "${mail.port}")
    private Integer mailPort;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailer = new JavaMailSenderImpl();
        mailer.setHost(mailHost);
        mailer.setPort(mailPort);
        mailer.setUsername(mailUserName);
        mailer.setPassword(mailUserPassword);

        Properties mailProperties = mailer.getJavaMailProperties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.debug", "true");

        return mailer;
    }
}
