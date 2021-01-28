package com.epam.service;

import com.epam.model.Student;

import java.util.Arrays;
import java.util.List;

public class StudentService {
    /*StudentService instance;

    public StudentService() {
    }

    public StudentService getInstance() {
        if(instance==null)
            instance = new StudentService();
        return instance;
    }*/

    public List<Student> getStudents() {
        return Arrays.asList(
                new Student(1, "John", "Doe"),
                new Student(2, "Jane", "Goodall"),
                new Student(3, "Max", "Born")
        );
    }

    public Student getStudent() {
        return new Student(1, "John", "Doe");
    }

}



