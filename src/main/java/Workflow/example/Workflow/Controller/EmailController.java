package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.entity.EmailRequest;
import workflow.example.workflow.service.EmailService;

@RestController
@RequestMapping("/api/email")
@Tag(name = "Email", description = "CRUD Email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String text = emailRequest.getText();

        emailService.sendEmail(to, subject, text);
    }

}
