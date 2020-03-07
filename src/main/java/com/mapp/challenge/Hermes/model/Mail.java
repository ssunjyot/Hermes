package com.mapp.challenge.Hermes.model;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private URI attachment;
}
