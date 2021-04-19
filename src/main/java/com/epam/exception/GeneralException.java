package com.epam.exception;

import com.epam.model.MyCustomErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GeneralException extends RuntimeException{
    private MyCustomErrorType errorType;

    public GeneralException() {
    }

    public GeneralException(String message) {
        super(message);
    }

    public MyCustomErrorType getErrorType() {
        return MyCustomErrorType.FATAL_ERROR_TYPE;
    }
}
