package org.study.course.status;

import java.util.Arrays;

/**
 * Status model.
 * @author M.Sokil
 */
public enum Status {
    COMING_SOON(0),ONGOING(1),COMPLETED(2);
    
    private final int id;

    Status(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Status of(int id) {
        return Arrays.stream(Status.values())
                .filter(status -> status.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Wrong argument"));
    }
}
