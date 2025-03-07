package com.emailsystem.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emailsystem.api.service.EmailService;
import com.emailsystem.api.model.Template;
import com.emailsystem.api.model.User;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        User user = emailRequest.getUser();
        Template template = emailRequest.getTemplate();
        emailService.sendEmail(user, template);
        return ResponseEntity.ok("Email sent successfully");
    }

    public static class EmailRequest {
        private User user;
        private Template template;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Template getTemplate() {
            return template;
        }

        public void setTemplate(Template template) {
            this.template = template;
        }
    }
}
