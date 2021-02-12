package com.epam.model;
/**
 * Course model.
 *
 * @author M.Sokil
 *
 */

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class Course {
    private Long id;
    private String title;
    private Category category;
    private int duration;
    private LocalDate startDate;
    private Integer price;
    private Set<User> students;
    private User teacher;
    private Status status;

    public Course() {
    }

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
        this.category = new Category("Some topic");
        this.duration = 36;
        this.students = Collections.singleton(new User(2L, "Petro", "Petrenko"));
        this.teacher = new User(1L, "Ivan", "Ivanenko", Role.TEACHER);
        this.status = Status.COMING_SOON;

        this.price = 1200;
        this.startDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
