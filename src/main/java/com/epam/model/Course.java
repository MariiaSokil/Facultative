package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
/**
 * Course model.
 * @author M.Sokil
 */
@Builder
@Data
@ToString(exclude = {"students"})
@EqualsAndHashCode(exclude = {"students"})
@Entity
@Table(name = "courses")
public class Course {
    @Id
    private Long id;
    @Column(nullable=false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    private int duration;
    @Column(name="start_date")
    private LocalDate startDate;
    private Integer price;
    @JsonIgnore
    @ManyToMany(mappedBy="courses")
    private Set<User> students = new HashSet<>();
  //  private User teacher;
    private Status status;
    private int enrollment;
}
