package com.mapp.challenge.Hermes.producer;

import com.mapp.challenge.Hermes.model.Mail;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;

@Log4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Mail> producerKafkaTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    public void sendMail(Mail mail) {
        ListenableFuture<SendResult<String, Mail>> future = producerKafkaTemplate.send(topicName, String.valueOf(new Random(System.currentTimeMillis()).nextInt()) , mail);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Mail>>() {

            @Override
            public void onSuccess(SendResult<String, Mail> result) {
                log.info("Sent mail successfully");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send mail");
            }
        });
    }
}
