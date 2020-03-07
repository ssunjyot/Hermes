package com.mapp.challenge.Hermes.consumer;

import lombok.extern.log4j.Log4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void consume(String message){
        log.info(message);
    }
}
