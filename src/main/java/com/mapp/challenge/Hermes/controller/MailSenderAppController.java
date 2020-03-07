package com.mapp.challenge.Hermes.controller;

import com.mapp.challenge.Hermes.model.Mail;
import com.mapp.challenge.Hermes.producer.KafkaProducer;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j
public class MailSenderAppController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String send(@RequestBody Mail mail) {
        kafkaProducer.sendGreeting("Hello there");

        log.info("Rest call came through");
        return "OK";
    }
}