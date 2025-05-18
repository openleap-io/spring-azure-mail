package com.openleap.io.springazuremail.controller;

import com.openleap.io.springazuremail.controller.dto.MailRequestDto;
import com.openleap.io.springazuremail.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    private final EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendMail(@RequestBody MailRequestDto mailRequest) {
        emailService.sendEmail(mailRequest.getSenderId(), mailRequest.getRecipientList(), mailRequest.getSubject(), mailRequest.getBody());
    }
}
