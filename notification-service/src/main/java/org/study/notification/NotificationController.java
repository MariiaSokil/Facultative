package org.study.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @GetMapping("/study")
    public String showStudy() {
        return "Study notification";
    }
}
