package com.inqoo.trainingservice.app.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/sendemail")
public class EmailController {
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public String sendEmail() {
        emailService.sendMail(
                "inqootest@gmail.com",
                "test",
                "text",
                true);

        return "E-mail sent!";
    }
}
