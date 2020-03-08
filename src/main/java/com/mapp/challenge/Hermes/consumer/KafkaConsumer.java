package com.mapp.challenge.Hermes.consumer;

import com.mapp.challenge.Hermes.model.Mail;
import com.mapp.challenge.Hermes.service.MailSenderService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j
@Component
public class KafkaConsumer {

    @Autowired
    private MailSenderService mailSenderService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Mail mail) throws IOException {
        log.debug("Kafka Consumer got Mail Object");

        mailSenderService.sendMail(mail);
    }
}
