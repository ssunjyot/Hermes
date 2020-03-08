package com.mapp.challenge.hermes.controller;

import com.mapp.challenge.hermes.model.Mail;
import com.mapp.challenge.hermes.producer.KafkaProducer;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j
public class MailSenderController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String send(@RequestBody Mail mail) {
        log.info("Rest call came through");
        kafkaProducer.sendMail(mail);
        return "OK";
    }
}