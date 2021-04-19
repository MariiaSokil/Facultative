package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MyAppError {
    private String message;
    private MyCustomErrorType errorType;
    private LocalDateTime timeStamp;
}
