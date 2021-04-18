package com.epam.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Course model.
 *
 * @author M.Sokil
 */
@Data @Accessors(chain = true)
@ToString(exclude = {"students"})
@EqualsAndHashCode(exclude = {"students"})
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;


    @NotBlank(message = "Title is mandatory")
    @Size(max = 50)
    @NotNull
    @Column(unique = true)
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category",unique = true)
    private Category category;

    @NotNull
    @Column(name = "duration")
    private int duration;

    @Column(name = "start_date")
    @NotNull
    private LocalDate startDate;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @ManyToMany(mappedBy = "courses")
    private Set<User> students = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher",unique = true)
    private User teacher;

    @NotNull
    @Column(name = "status")
    private Status status;

    @NotNull
    @Column(name = "enrollment")
    private int enrollment;
}
