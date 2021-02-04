package com.epam;

public enum SQLConstant {
    PROPERTIES_FILE_PATH ( "app.properties"),


    SQL_INSERT_USER ( "INSERT INTO users VALUES (DEFAULT, ?)")


;
    private String value;

    SQLConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
