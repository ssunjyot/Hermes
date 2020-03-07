package com.mapp.challenge.Hermes.service;

import com.mapp.challenge.Hermes.model.Mail;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class MailSenderService {
    public void sendMail(Mail mail) {
        log.info("Service will send mail to : " + mail.getRecipient());
    }
}
