package com.mapp.challenge.Hermes.controller;

import com.mapp.challenge.Hermes.model.Mail;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j
public class MailController {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static final String topic = "test";

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String send(@RequestBody Mail mail) {
        kafkaTemplate.send(topic,"Hi there");

        log.info("Rest call came through");
        return "OK";
    }
}