package com.mapp.challenge.Hermes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
}
