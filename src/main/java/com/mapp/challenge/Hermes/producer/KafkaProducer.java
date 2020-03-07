package com.mapp.challenge.Hermes.producer;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;

@Log4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    public void sendGreeting(String greeting) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, String.valueOf(new Random(System.currentTimeMillis()).nextInt()) , greeting);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message successfully");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message");
            }
        });
    }
}
