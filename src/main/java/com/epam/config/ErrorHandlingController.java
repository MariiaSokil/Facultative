package com.epam.config;

import com.epam.exception.GeneralException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.MyCustomErrorType;
import com.epam.model.MyAppError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.lang.model.type.ErrorType;
import java.lang.invoke.MethodHandle;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<MyAppError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: message {}", ex.getMessage());
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new MyAppError(err.getDefaultMessage(),
                        MyCustomErrorType.VALIDATION_ERROR_TYPE,
                        LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyAppError handleGeneralException(GeneralException ex) {
        log.error("handleGeneralException: message {}", ex.getMessage());
        return new MyAppError(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyAppError handleException(Exception ex) {
        log.error("handleException: message {}", ex.getMessage());
        return new MyAppError(ex.getMessage(), MyCustomErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}
