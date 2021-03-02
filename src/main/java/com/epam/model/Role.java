package com.epam.model;

import java.util.Arrays;

/**
 * Role model.
 * @author M.Sokil
 */

public enum Role {
    STUDENT(0), TEACHER(1), ADMIN(2);

    private final int id;

    Role(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role of(int id) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Wrong argument"));
    }
}
