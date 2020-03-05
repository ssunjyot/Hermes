package com.mapp.challenge.Hermes.controller;

import com.mapp.challenge.Hermes.model.Email;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j
public class MailController {

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String send(@RequestBody Email email) {
        log.info("Rest call came through");
        return "OK";
    }
}