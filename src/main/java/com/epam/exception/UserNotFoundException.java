package com.epam.exception;

import com.epam.model.MyCustomErrorType;

public class UserNotFoundException extends GeneralException{
    private static final String DEFAULT_MESSAGE = "User is not found!";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public MyCustomErrorType getErrorType() {
        return MyCustomErrorType.DATABASE_ERROR_TYPE;
    }
}
