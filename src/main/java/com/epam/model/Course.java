package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
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
    private Long id;


    @NotBlank(message = "Title is mandatory")
    @Size(max = 50)
    @NotNull
    @Column(unique = true)
    private String title;

    @NotNull
    @NotBlank(message = "Category is mandatory")
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

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<User> students = new HashSet<>();

    //  private User teacher;
    @NotNull
    @Column(name = "status")
    private Status status;

    @NotNull
    @Column(name = "enrollment")
    private int enrollment;
}
