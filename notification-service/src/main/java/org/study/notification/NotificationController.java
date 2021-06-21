package org.study.notification;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notificate")
    public void sendEmail(@RequestBody NotificationEvent event) {
        notificationService.sendEmail(event.getRecipient(), event.getSubject(), event.getText());
    }

    @Data
    static class NotificationEvent {
        private String recipient;
        private String subject;
        private String text;
    }
}
