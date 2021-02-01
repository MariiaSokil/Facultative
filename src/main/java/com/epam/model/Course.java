package com.epam.model;

import java.util.Collections;
import java.util.Set;

public class Course {
    private Long id;
    private String title;
    private String topic;
    private int duration;
    private Set<User> students;
    private User teacher;
    private Status status;

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
        this.topic = "Some topic";
        this.duration = 36;
        this.students = Collections.singleton(new User(2L, "Petro", "Petrenko"));
        this.teacher = new User(1L, "Ivan", "Ivanenko", Role.TEACHER);
        this.status = Status.COMING_SOON;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public int getDuration() {
        return duration;
    }

    public Set<User> getStudents() {
        return students;
    }

    public User getTeacher() {
        return teacher;
    }

    public Status getStatus() {
        return status;
    }
}
