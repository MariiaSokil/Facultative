package com.epam.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * Course model.
 * @author M.Sokil
 */
public class Course {
    private Long id;
    private String title;
    private Category category;
    private int duration;
    private LocalDate startDate;
    private Integer price;
    private Set<User> students = new HashSet<>();
    private User teacher;
    private Status status;
    private int enrollment;

    public Course() {
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Category getCategory() {
        return category;
    }
    public int getDuration() {
        return duration;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public Integer getPrice() {
        return price;
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
    public int getEnrollment() { return enrollment; }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setPrice(Integer price) { this.price = price; }
    public void setStudents(Set<User> students) {
        this.students = students;
    }
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setEnrollment(int enrollment) { this.enrollment = enrollment; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", price=" + price +
                ", teacher=" + teacher +
                ", status=" + status +
                ", enrollment=" + enrollment +
                '}';
    }
}
