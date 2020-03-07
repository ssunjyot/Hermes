package com.mapp.challenge.Hermes;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Log4j
public class MailSenderApp {

	public static void main(String[] args) {
		SpringApplication.run(MailSenderApp.class, args);
	}

	@PostConstruct
	public void postConstruct(){
		log.info("SpringBoot Application Started!");
	}

}
