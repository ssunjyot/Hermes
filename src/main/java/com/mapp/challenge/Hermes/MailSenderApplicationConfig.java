package com.mapp.challenge.Hermes;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Properties;

@Log4j
@Configuration
@EnableSwagger2
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

    @Bean
    public Docket api() {
        log.info("Swagger config loaded.");
        return new Docket(
                DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Hermes"
                , "A Micro Service for sending emails asynchronously."
                , "1.0"
                , "https://github.com/ssunjyot/Hermes"
                , new Contact("Sunjyot", "https://github.com/ssunjyot", "\"sunjyotsinghanand@gmail.com\"")
                , ""
                , ""
                , new ArrayList<VendorExtension>());

        return apiInfo;
    }

}
