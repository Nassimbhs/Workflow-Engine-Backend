package workflow.example.workflow.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import workflow.example.workflow.entity.Conge;

@RestController
public class CongeWebhookController {

    @PostMapping("/webhooks/conges")
    public void receiveCongeWebhook(@RequestBody Conge conge) {

    }

}
