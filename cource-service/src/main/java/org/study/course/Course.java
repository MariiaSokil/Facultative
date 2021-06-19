package org.study.course;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.study.course.category.Category;
import org.study.course.status.Status;
import org.study.course.validator.BasicInfo;
//import org.study.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@Accessors(chain = true)
@ToString(exclude = {"students"})
@EqualsAndHashCode(exclude = {"students"})
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_seq", allocationSize = 1)
    @Column(nullable = false)
    private Long id;


    @NotBlank(message = "Title is mandatory",groups = BasicInfo.class)
    @Size(max = 50, groups = BasicInfo.class)
    @NotNull(groups = BasicInfo.class)
    @Column(unique = true)
    private String title;

    @NotNull(groups = BasicInfo.class)
    @ManyToOne
    @JoinColumn(name = "category",unique = true)
    private Category category;

    @NotNull(groups = BasicInfo.class)
    @Column(name = "duration")
    private int duration;

    @Column(name = "start_date")
    @NotNull(groups = BasicInfo.class)
    private LocalDate startDate;

    @NotNull(groups = BasicInfo.class)
    @Column(name = "price")
    private Integer price;
/*
    @ManyToMany(mappedBy = "courses")
    private Set<User> students = new HashSet<>();*/

//    @NotNull(groups = BasicInfo.class)
//    @ManyToOne
    //@JoinColumn(name = "teacher",unique = true)
    @Column(name = "teacher_id")
    private Long teacher;

    @NotNull(groups = BasicInfo.class)
    @Column(name = "status")
    private Status status;

    @NotNull(groups = BasicInfo.class)
    @Column(name = "enrollment")
    private int enrollment;
}

