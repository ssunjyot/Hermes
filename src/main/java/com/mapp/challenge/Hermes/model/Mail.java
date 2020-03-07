package com.mapp.challenge.Hermes.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import lombok.extern.log4j.Log4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import java.net.URI;
import java.util.Map;

@Getter
@Setter
@Log4j
public class Mail implements Serializer<Mail>, Deserializer<Mail> {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private URI attachment;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Empty
    }

    @Override
    public byte[] serialize(String topic, Mail object) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] serializedObject=null;
        try {
            serializedObject = mapper.writeValueAsString(object).getBytes();
        }catch (Exception e) {
            log.error("Serialization failed");
            e.printStackTrace();
        }
        return serializedObject;
    }

    @Override
    public void close() {
        //Empty
    }

    @Override
    public Mail deserialize(String topic, byte[] serializedObject) {
        ObjectMapper mapper = new ObjectMapper();
        Mail object = null;
        try {
            object = mapper.readValue(serializedObject, Mail.class);
        } catch (Exception e) {
            log.error("Deserialization failed");
            e.printStackTrace();
        }
        return object;
    }
}