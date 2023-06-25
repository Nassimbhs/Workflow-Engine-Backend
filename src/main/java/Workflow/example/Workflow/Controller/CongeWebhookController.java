package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Entity.Conge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CongeWebhookController {

    @PostMapping("/webhooks/conges")
    public void receiveCongeWebhook(@RequestBody Conge conge) {

    }

}
