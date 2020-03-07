package com.mapp.challenge.Hermes.service;

import com.mapp.challenge.Hermes.model.Mail;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Log4j
@Service
@EnableRetry
public class MailSenderService {

    @Autowired
    private JavaMailSender mailer;

    @Retryable(backoff = @Backoff(delayExpression = "#{${sender.retry.delay}}"),maxAttemptsExpression = "#{${sender.retries}}")
    @Async
    public String sendMail(Mail mail) {
        MimeMessage mailWithAttachment = mailer.createMimeMessage();

        try {
            File attachment = new File(FileUtils.getTempDirectory(),"tmp");
            FileUtils.copyURLToFile(mail.getAttachment().toURL(), attachment);

            MimeMessageHelper helper = new MimeMessageHelper(mailWithAttachment, true);

            helper.setFrom(mail.getSender());
            helper.setTo(mail.getRecipient());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody());
            helper.addAttachment(attachment.getName(), attachment);
        } catch (Exception e) {
            log.error("Exception! Retrying..");
            e.printStackTrace();
        }
        mailer.send(mailWithAttachment);

        log.info("Mail was sent to : " + mail.getRecipient());

        return "Successful";
    }
}
