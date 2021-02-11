package com.epam.model;

/**
 * Role model.
 *
 * @author M.Sokil
 *
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
}
