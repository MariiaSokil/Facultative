package org.study.notification;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @ResponseStatus(HttpStatus.ACCEPTED)
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
